/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.pojo;

import com.bluu.hdm.web.enums.LicenseErrorEnum;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author Gonzalo Torres
 */
public class License {

    private static final SimpleDateFormat SDFOUT = new SimpleDateFormat("dd/MM/yyyy");
    private static final Pattern LICPATT = Pattern.compile("([\\dA-F]{11}-){7}[\\dA-F]{11}");
    private static final long DAYS30 = 2592000000l;

    private String code;
    private String version;
    private String asociatedIp;
    private Date expirationDate;
    private boolean blocked;

    private String ipAddress;

    private Long cpeThreshold;
    private List<LicenseErrorEnum> errors;

    public License(String code) {
	this.code = code;
    }

    public License() {
    }

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

    public String getVersion() {
	return version;
    }

    public void setVersion(String version) {
	this.version = version;
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
