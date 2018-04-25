/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Gonzalo Torres
 */
@Entity
@Table(name = "license")
public class LicenseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "code")
    private String code;
    @Size(max = 255)
    @Column(name = "version")
    private String version;
    @Size(max = 255)
    @Column(name = "asociated_ip")
    private String asociatedIp;
    @Column(name = "expiration_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationDate;

    public LicenseEntity() {
    }

    public LicenseEntity(String code) {
	this.code = code;
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

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (code != null ? code.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof LicenseEntity)) {
	    return false;
	}
	LicenseEntity other = (LicenseEntity) object;
	if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "com.bluu.hdm.rest.entity.LicenseEntity[ code=" + code + " ]";
    }

}
