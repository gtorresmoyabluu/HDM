/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.pojo.inventory;

import com.bluu.hdm.web.pojo.firmware.Firmware;
import com.bluu.hdm.web.pojo.inventory.Manufacturer;
import com.bluu.hdm.web.pojo.inventory.Model;
import com.bluu.hdm.web.pojo.inventory.StatusDevice;
import com.bluu.hdm.web.util.CustomDateDeserializer;
import com.bluu.hdm.web.util.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Marco Valdés
 */
public class Cpe {

    private Long id;
    private String idDevice;
    private String nameDevice;
    private String ipDevice;
    private String macDevice;
    private String serialDevice;
    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date creationDate;
    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date updateDate;
    private Model idModel;
    private StatusDevice statusDevice;
    private Firmware firmware;
    private Manufacturer manufacturer;
    private String error;
    private String fechaCreacion;
     private String urlCpe;
    
    
    public Cpe(){
        
    }
    
    public Cpe(Cpe cpe) {
	
        id = cpe.id;
        idDevice = cpe.idDevice;
        nameDevice = cpe.nameDevice;
        ipDevice = cpe.ipDevice;
        macDevice = cpe.macDevice;
        serialDevice = cpe.serialDevice;
        creationDate = cpe.creationDate;
        updateDate = cpe.updateDate;
        idModel = cpe.idModel;
        statusDevice = cpe.statusDevice;
        firmware = cpe.idModel.getFirmwares().size() > 0 ? cpe.idModel.getFirmwares().get(0) : null;
        manufacturer = cpe.idModel.getManufacturer();
        error = cpe.error;
        fechaCreacion = cpe.fechaCreacion;
        urlCpe = cpe.urlCpe;
    }
    
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

    public StatusDevice getStatusDevice() {
        return statusDevice;
    }

    public void setStatusDevice(StatusDevice statusDevice) {
        this.statusDevice = statusDevice;
    }

    public Firmware getFirmware() {
        return firmware;
    }

    public void setFirmware(Firmware firmware) {
        this.firmware = firmware;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Model getIdModel() {
        return idModel;
    }

    public void setIdModel(Model idModel) {
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
