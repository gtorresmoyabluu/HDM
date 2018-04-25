/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.pojo;

import java.util.List;

/**
 *
 * @author Gonzalo Torres
 */
public class Model {

    private Long id;
    private String name;
    private long idFirmware;
    private Manufacturer manufacturers;
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

    public Manufacturer getManufacturers() {
	return manufacturers;
    }

    public void setManufacturers(Manufacturer manufacturers) {
	this.manufacturers = manufacturers;
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
