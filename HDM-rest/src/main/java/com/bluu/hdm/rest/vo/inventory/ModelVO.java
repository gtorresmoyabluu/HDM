/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.vo.inventory;

import com.bluu.hdm.rest.vo.firmware.FirmwareVO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;

/**
 *
 * @author Gonzalo Torres
 */
public class ModelVO {

    private Long id;
    private String name;
    private ManufacturerVO manufacturer;
    private Set<CpeVO> devices;
    private Set<FirmwareVO> firmwares;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public ManufacturerVO getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(ManufacturerVO manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Set<FirmwareVO> getFirmwares() {
        return firmwares;
    }

    public void setFirmwares(Set<FirmwareVO> firmwares) {
        this.firmwares = firmwares;
    }

    public Set<CpeVO> getDevices() {
        return devices;
    }

    public void setDevices(Set<CpeVO> devices) {
        this.devices = devices;
    }
    
}
