/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.vo;

import java.util.Set;

/**
 *
 * @author Gonzalo Torres
 */
public class ModelVO {

    private Long id;
    private String name;
    private long idFirmware;
    private ManufacturerVO idManufacturer;
    private Set<DeviceVO> deviceEntitySet;
    private Set<FirmwareVO> firmwareEntitySet;

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

    public long getIdFirmware() {
	return idFirmware;
    }

    public void setIdFirmware(long idFirmware) {
	this.idFirmware = idFirmware;
    }

    public ManufacturerVO getIdManufacturer() {
	return idManufacturer;
    }

    public void setIdManufacturer(ManufacturerVO idManufacturer) {
	this.idManufacturer = idManufacturer;
    }

    public Set<DeviceVO> getDeviceEntitySet() {
	return deviceEntitySet;
    }

    public void setDeviceEntitySet(Set<DeviceVO> deviceEntitySet) {
	this.deviceEntitySet = deviceEntitySet;
    }

    public Set<FirmwareVO> getFirmwareEntitySet() {
	return firmwareEntitySet;
    }

    public void setFirmwareEntitySet(Set<FirmwareVO> firmwareEntitySet) {
	this.firmwareEntitySet = firmwareEntitySet;
    }
}
