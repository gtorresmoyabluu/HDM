/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.vo;

import com.bluu.hdm.rest.enums.LicenseAppEnum;
import com.bluu.hdm.rest.enums.LicenseErrorEnum;
import com.bluu.hdm.rest.enums.LicenseVersionEnum;
import com.bluu.hdm.rest.enums.LicenseWarningEnum;
import com.bluu.hdm.rest.util.CryptoUtils;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

/**
 *
 * @author Gonzalo Torres
 */
public class LicenseVO {

    private static final SimpleDateFormat SDFOUT = new SimpleDateFormat("dd/MM/yyyy");
    private static final Pattern LICPATT = Pattern.compile("([\\dA-F]{11}-){7}[\\dA-F]{11}");
    private static final long DAYS30 = 2592000000l;

    private final String code;
    private LicenseAppEnum app;
    private LicenseVersionEnum version;
    private Long cpeThreshold;
    private Date expDate;
    private String ipAddress;

    private boolean blocked;
    private List<LicenseErrorEnum> errors;
    private List<LicenseWarningEnum> warnings;

    public LicenseVO(String code) {
	this.code = code;
    }

    private void init() {
	app = null;
	version = null;
	cpeThreshold = null;
	ipAddress = null;
	expDate = null;
	blocked = false;
	errors = new ArrayList<>();
	warnings = new ArrayList<>();
    }

    /**
     * La licencia se bloqueará (y tendrá los mensajes de error correspondientes) si:
     * <p>
     * - Se produce un error de cualquier tipo al parsear la licencia - Se ha pasado la fecha de expiración - La IP de la licencia no corresponde con la actual
     * <p>
     * La licencia no se bloqueará y dará los siguientes mensajes si:
     * <p>
     * - Mensajes de error: - Se ha superado el límite de CPEs
     * <p>
     * - Mensajes de warning: - Queda menos de un 10% para alcanzar el límite máximo de CPE - La licencia está cercana a expirar (faltan menos de 30 días)
     *
     * @param cpeCount
     */
    public void doCheck(long cpeCount) {
	init();

	// Comprobación del patrón de la licencia
	if (!LICPATT.matcher(code).matches()) {
	    blocked = true;
	    errors.add(LicenseErrorEnum.pattern);
	}

	if (blocked) {
	    return;
	}

	// Obtenemos la licencia desencriptada
	String clearLic = null;
	try {
	    clearLic = CryptoUtils.decrypt(new String(Hex.decodeHex(code.replace("-", "").toCharArray())));
	} catch (final DecoderException ignore) {
	    blocked = true;
	    errors.add(LicenseErrorEnum.decrypt);
	}

	if (clearLic == null) {
	    blocked = true;
	    if (!errors.contains(LicenseErrorEnum.decrypt)) {
		errors.add(LicenseErrorEnum.decrypt);
	    }
	}

	if (blocked || clearLic == null) {
	    return;
	}

	// Obtenemos la aplicación (1 byte)
	try {
	    app = LicenseAppEnum.values()[Integer.valueOf(clearLic.substring(0, 1))];
	} catch (final NumberFormatException ex) {
	    blocked = true;
	    errors.add(LicenseErrorEnum.app);
	}

	// Obtenemos la versión (3 bytes)
	try {
	    version = LicenseVersionEnum.values()[Integer.valueOf(clearLic.substring(1, 4))];
	} catch (final NumberFormatException ex) {
	    blocked = true;
	    errors.add(LicenseErrorEnum.version);
	}

	// Obtenemos el límite de cpe (4 bytes)
	final int cpeMult = Integer.parseInt(clearLic.substring(12, 15));
	final int cpeExpt = Integer.parseInt(clearLic.substring(15, 16));
	cpeThreshold = (long) (cpeMult * Math.pow(10, cpeExpt));
	if (cpeCount >= cpeThreshold) {
	    errors.add(LicenseErrorEnum.cpeThreshold);
	} else if (cpeCount >= (cpeThreshold * 0.95)) {
	    warnings.add(LicenseWarningEnum.cpeThresholdLess10);
	}

	// Obtenemos la fecha de expiración (5 bytes)
	try {
	    final int expYer = Integer.valueOf(clearLic.substring(18, 20), 16) + 2000;
	    final int expMth = Integer.valueOf(clearLic.substring(20, 21), 16);
	    final int expDay = Integer.valueOf(clearLic.substring(21, 23), 16);
	    expDate = SDFOUT.parse(expDay + "/" + expMth + "/" + expYer);
	} catch (final ParseException ignore) {
	    blocked = true;
	    errors.add(LicenseErrorEnum.dateDecrypt);
	}

	// Chequeo de Fecha
	if (expDate != null) {
	    final Date now = new Date();
	    final Date dateWarning = new Date(expDate.getTime() - DAYS30);
	    if (now.after(expDate)) {
		blocked = true;
		errors.add(LicenseErrorEnum.dateExpired);
	    } else if (now.after(dateWarning)) {
		warnings.add(LicenseWarningEnum.dateCloseToExpire);
	    }
	}

	// Obtenemos la ip (8 bytes)
	try {
	    final String ipaddhx = clearLic.substring(23);
	    final char[] ipaddca = ipaddhx.toCharArray();
	    final byte[] ipaddba = Hex.decodeHex(ipaddca);
	    final InetAddress inetadd = InetAddress.getByAddress(ipaddba);
	    ipAddress = inetadd.getHostAddress();
	} catch (final UnknownHostException | DecoderException ignore) {
	    blocked = true;
	    errors.add(LicenseErrorEnum.ipDecrypt);
	}

	// Chequeo de IP
	if (ipAddress != null) {
	    try {
		final InetAddress ipadd = InetAddress.getLocalHost();
		final String currentIp = ipadd.getHostAddress();
		if (!currentIp.equals(ipAddress)) {
		    blocked = true;
		    errors.add(LicenseErrorEnum.ipChanged);
		}
	    } catch (final UnknownHostException ignore) {
		blocked = true;
		if (!errors.contains(LicenseErrorEnum.ipDecrypt)) {
		    errors.add(LicenseErrorEnum.ipDecrypt);
		}
	    }
	}
    }

    public String getCode() {
	return code;
    }

    public Long getCpeThreshold() {
	return cpeThreshold;
    }

    public String getCpeThresholdToString() {
	return cpeThreshold != null ? NumberFormat.getInstance().format(cpeThreshold) : "";
    }

    public String getIpAddress() {
	return ipAddress;
    }

    public Date getExpDate() {
	return expDate;
    }

    public String getExpDateToString() {
	return expDate != null ? new Date().before(expDate) ? SDFOUT.format(expDate) : "License expired" : "";
    }

    public LicenseVersionEnum getVersion() {
	return version;
    }

    public LicenseAppEnum getApp() {
	return app;
    }

    public boolean isBlocked() {
	return blocked;
    }

    public List<LicenseErrorEnum> getErrors() {
	return errors;
    }

    public List<LicenseWarningEnum> getWarnings() {
	return warnings;
    }
}
