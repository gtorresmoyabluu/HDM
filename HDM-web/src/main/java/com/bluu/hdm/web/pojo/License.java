/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.pojo;

import java.util.Date;

/**
 *
 * @author Gonzalo Torres
 */
public class License {

    private String code;
    private String version;
    private String asociatedIp;
    private Date expirationDate;

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
