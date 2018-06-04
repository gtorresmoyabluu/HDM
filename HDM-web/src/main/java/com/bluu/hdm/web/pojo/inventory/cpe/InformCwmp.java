/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.pojo.inventory.cpe;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class InformCwmp implements Serializable{
    
    
    
    private Long eventCode;
    private String idCpe;
    private String fechaCreacion;
    private String reqIpCpe;
    private String userNameCpe;
    private String urlCpe;
    private String manufacturerCpe;
    private String modelsCpe;
    private String firmwareNameCpe;
    private String serialCpe;
    private String connTypeCpe;
    //private Firmware firmwares;

       
    public InformCwmp(){
        
    }

    public Long getEventCode() {
        return eventCode;
    }

    public void setEventCode(Long eventCode) {
        this.eventCode = eventCode;
    }

    public String getIdCpe() {
        return idCpe;
    }

    public void setIdCpe(String idCpe) {
        this.idCpe = idCpe;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getReqIpCpe() {
        return reqIpCpe;
    }

    public void setReqIpCpe(String reqIpCpe) {
        this.reqIpCpe = reqIpCpe;
    }

    public String getUserNameCpe() {
        return userNameCpe;
    }

    public void setUserNameCpe(String userNameCpe) {
        this.userNameCpe = userNameCpe;
    }

    public String getManufacturerCpe() {
        return manufacturerCpe;
    }

    public void setManufacturerCpe(String manufacturerCpe) {
        this.manufacturerCpe = manufacturerCpe;
    }

    public String getModelsCpe() {
        return modelsCpe;
    }

    public void setModelsCpe(String modelsCpe) {
        this.modelsCpe = modelsCpe;
    }

    public String getFirmwareNameCpe() {
        return firmwareNameCpe;
    }

    public void setFirmwareNameCpe(String firmwareNameCpe) {
        this.firmwareNameCpe = firmwareNameCpe;
    }

    public String getSerialCpe() {
        return serialCpe;
    }

    public void setSerialCpe(String serialCpe) {
        this.serialCpe = serialCpe;
    }

    public String getConnTypeCpe() {
        return connTypeCpe;
    }

    public void setConnTypeCpe(String connTypeCpe) {
        this.connTypeCpe = connTypeCpe;
    }

    public String getUrlCpe() {
        return urlCpe;
    }

    public void setUrlCpe(String urlCpe) {
        this.urlCpe = urlCpe;
    }
    
    
    
 }
