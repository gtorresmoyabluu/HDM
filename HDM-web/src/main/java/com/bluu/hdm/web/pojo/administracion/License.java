/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.pojo.administracion;

import com.bluu.hdm.web.enums.LicenseAppEnum;
import com.bluu.hdm.web.enums.LicenseErrorEnum;
import com.bluu.hdm.web.enums.LicenseVersionEnum;
import com.bluu.hdm.web.enums.LicenseWarningEnum;
import com.bluu.hdm.web.util.CryptoUtils;
import com.bluu.hdm.web.util.CustomDateDeserializer;
import com.bluu.hdm.web.util.CustomDateSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

/**
 *
 * @author Gonzalo Torres
 */
public class License {

    private static final SimpleDateFormat SDFOUT = new SimpleDateFormat("dd/MM/yyyy");
    private static final Pattern LICPATT = Pattern.compile("([\\dA-F]{11}-){7}[\\dA-F]{11}");
    private static final long DAYS30 = 2592000000l;

    private Long id;
    private LicenseAppEnum app;
    private String code;    
    private LicenseVersionEnum version;
    private String asociatedIp;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date expirationDate;
    private boolean blocked;

    private String ipAddress;

    private Long cpeThreshold;
    private List<LicenseErrorEnum> errors;
    private List<LicenseWarningEnum> warnings;

    private Client idClient;
    
    public License(String code) {
        try {
            this.code = code;
            String clearLic = CryptoUtils.decrypt(new String(Hex.decodeHex(this.code.replace("-", "").toCharArray())));
            // Obtenemos el límite de cpe (4 bytes)
            final int cpeMult = Integer.parseInt(clearLic.substring(12, 15));
            final int cpeExpt = Integer.parseInt(clearLic.substring(15, 16));
            this.cpeThreshold = (long) (cpeMult * Math.pow(10, cpeExpt));
        } catch (DecoderException ex) {
            Logger.getLogger(License.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public License(String code, Long cpeThreshold) {
        this.code = code;
        this.cpeThreshold = cpeThreshold;
    }

    public License() {
    }

    public Client getIdClient() {
        return idClient;
    }

    public void setIdClient(Client idClient) {
        this.idClient = idClient;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LicenseAppEnum getApp() {
        return app;
    }

    public void setApp(LicenseAppEnum app) {
        this.app = app;
    }

    public LicenseVersionEnum getVersion() {
        return version;
    }

    public void setVersion(LicenseVersionEnum version) {
        this.version = version;
    }

    public List<LicenseWarningEnum> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<LicenseWarningEnum> warnings) {
        this.warnings = warnings;
    }

    public String getAsociatedIp() {
        return asociatedIp;
    }

    public void setAsociatedIp(String asociatedIp) {
        this.asociatedIp = asociatedIp;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public List<LicenseErrorEnum> getErrors() {
        return errors;
    }

    public void setErrors(List<LicenseErrorEnum> errors) {
        this.errors = errors;
    }

    public String getExpDateToString() {
        return expirationDate != null ? new Date().before(expirationDate) ? SDFOUT.format(expirationDate) : "License expired" : "";
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Long getCpeThreshold() {
        return cpeThreshold;
    }

    public void setCpeThreshold(Long cpeThreshold) {
        this.cpeThreshold = cpeThreshold;
    }

    public String getCpeThresholdToString() {
        return cpeThreshold != null ? NumberFormat.getInstance().format(cpeThreshold) : "";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private void init() {
        app = null;
        version = null;
        cpeThreshold = null;
        ipAddress = null;
        expirationDate = null;
        blocked = false;
        errors = new ArrayList<>();
        warnings = new ArrayList<>();
    }

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
            expirationDate = SDFOUT.parse(expDay + "/" + expMth + "/" + expYer);
        } catch (final ParseException ignore) {
            blocked = true;
            errors.add(LicenseErrorEnum.dateDecrypt);
        }

        // Chequeo de Fecha
        if (expirationDate != null) {
            final Date now = new Date();
            final Date dateWarning = new Date(expirationDate.getTime() - DAYS30);
            if (now.after(expirationDate)) {
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

    @Override
    public boolean equals(Object other) {
        return (other != null && getClass() == other.getClass() && code != null)
                ? code.equals(((License) other).code)
                : (other == this);
    }

    @Override
    public int hashCode() {
        return (code != null)
                ? (getClass().hashCode() + code.hashCode())
                : super.hashCode();
    }
}
