/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.vo.inventory;

import java.util.Date;

/**
 *
 * @author Gonzalo Torres
 */
public class CpeVO {

    private Long id;
    private String idDevice;
    private String nameDevice;
    private String ipDevice;
    private String macDevice;
    private String serialDevice;
    private Date creationDate;
    private Date updateDate;
    private ModelVO idModel;
    private StatusDeviceVO statusDevice;
    private String error;
    private String fechaCreacion;
    private String urlCpe;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
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

    public StatusDeviceVO getStatusDevice() {
	return statusDevice;
    }

    public void setStatusDevice(StatusDeviceVO statusDevice) {
	this.statusDevice = statusDevice;
    }

    public ModelVO getIdModel() {
        return idModel;
    }

    public void setIdModel(ModelVO idModel) {
        this.idModel = idModel;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getUrlCpe() {
        return urlCpe;
    }

    public void setUrlCpe(String urlCpe) {
        this.urlCpe = urlCpe;
    }
    
    
}
