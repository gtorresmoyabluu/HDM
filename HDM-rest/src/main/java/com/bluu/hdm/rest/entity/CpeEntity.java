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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "cpe")
public class CpeEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Size(max = 255)
    @Column(name = "id_device")
    private String idDevice;
    @Size(max = 255)
    @Column(name = "name_device")
    private String nameDevice;
    @Size(max = 255)
    @Column(name = "ip_device")
    private String ipDevice;
    @Size(max = 255)
    @Column(name = "mac_device")
    private String macDevice;
    @Size(max = 255)
    @Column(name = "serial_device")
    private String serialDevice;
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @JoinColumn(name = "id_model", referencedColumnName = "id")
    @ManyToOne
    private ModelEntity idModel;
    
    @JoinColumn(name = "status_device", referencedColumnName = "id")
    @ManyToOne
    private StatusDevicesEntity statusDevice;
    
    @Size(max = 255)
    @Column(name = "error")
    private String error;
    
    @Size(max = 255)
    @Column(name = "urlCpe")
    private String urlCpe;
    
    @Column(name = "tterror")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tterror;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ttmodification")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ttmodification;
    
    @JoinColumn(name = "connection", referencedColumnName = "id")
    @ManyToOne
    private ConnectionEntity connection;

    public CpeEntity() {
         final Date now = new Date();
         this.ttmodification = now;
    }

    public CpeEntity(long id) {
	this.id = id;
    }
    
    public void cwmpFault(String msg) {
        this.error = msg;
        this.tterror = new Date();
    }
    
    public void cwmpResetUser() {
        this.connection.cwmpResetUser(idDevice);
        this.ttmodification = new Date();
    }
    
    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public String getIdDevice() {
	return idDevice;
    }

    public void setIdDevice(String idDevice) {
	this.idDevice = idDevice;
    }

    public String getNameDevice() {
	return nameDevice;
    }

    public void setNameDevice(String nameDevice) {
	this.nameDevice = nameDevice;
    }

    public String getIpDevice() {
	return ipDevice;
    }

    public void setIpDevice(String ipDevice) {
	this.ipDevice = ipDevice;
    }

    public String getMacDevice() {
	return macDevice;
    }

    public void setMacDevice(String macDevice) {
	this.macDevice = macDevice;
    }

    public String getSerialDevice() {
	return serialDevice;
    }

    public void setSerialDevice(String serialDevice) {
	this.serialDevice = serialDevice;
    }

    public Date getCreationDate() {
	return creationDate;
    }

    public void setCreationDate(Date creationDate) {
	this.creationDate = creationDate;
    }

    public Date getUpdateDate() {
	return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
	this.updateDate = updateDate;
    }

    public ModelEntity getIdModel() {
	return idModel;
    }

    public void setIdModel(ModelEntity idModel) {
	this.idModel = idModel;
    }

    public StatusDevicesEntity getStatusDevice() {
	return statusDevice;
    }

    public void setStatusDevice(StatusDevicesEntity statusDevice) {
	this.statusDevice = statusDevice;
    }

    public Date getTtmodification() {
        return ttmodification;
    }

    public void setTtmodification(Date ttmodification) {
        this.ttmodification = ttmodification;
    }

    public String getError() {
        return error;
    }

    public void setError(String msg) {
        error = msg;
        if (error == null) {
            tterror = null;
        } else {
            tterror = new Date();
        }
    }

    public String getUrlCpe() {
        return urlCpe;
    }

    public void setUrlCpe(String urlCpe) {
        this.urlCpe = urlCpe;
    }
    public void setConnection(ConnectionEntity connection) {
        this.connection = connection;
    }
    
     public Date getTterror() {
        return tterror;
    }     
    
    @Override
    public String toString() {
	return "com.bluu.hdm.rest.entity.CpeEntity[ id=" + id + " ]";
    }

}
