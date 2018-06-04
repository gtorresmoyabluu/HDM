/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.pojo.inventory;

import com.bluu.hdm.web.pojo.firmware.Firmware;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gonzalo Torres
 */
public class Model {

    private Long id;
    private String name;
    private long idFirmware;
    private Manufacturer manufacturer;
    private List<Device> devices;
    private List<Firmware> firmwares;

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

    public Manufacturer getManufacturer() {
	return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
	this.manufacturer = manufacturer;
    }

    public List<Device> getDevices() {
	return devices;
    }

    public void setDevices(List<Device> devices) {
	this.devices = devices;
    }

    public List<Firmware> getFirmwares() {
	return firmwares;
    }

    public void setFirmwares(List<Firmware> firmwares) {
	this.firmwares = firmwares;
    }

    public Model() {
    }

    public Model(Model model) {
        this.id = model.getId();
        this.name = model.getName();
        this.idFirmware = model.getIdFirmware();
        this.manufacturer = model.getManufacturer();
        this.devices = model.getDevices() == null ? new ArrayList<>() : model.getDevices();
        this.firmwares = model.getFirmwares() == null ? new ArrayList<>() : model.getFirmwares();
    }

    @Override
    public boolean equals(Object other) {
	return (other != null && getClass() == other.getClass() && id != null)
		? id.equals(((Model) other).id)
		: (other == this);
    }

    @Override
    public int hashCode() {
	return (id != null)
		? (getClass().hashCode() + id.hashCode())
		: super.hashCode();
    }
}
