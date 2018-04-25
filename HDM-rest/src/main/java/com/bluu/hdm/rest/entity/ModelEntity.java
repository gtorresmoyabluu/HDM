/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Gonzalo Torres
 */
@Entity
@Table(name = "model")
public class ModelEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Size(max = 255)
    @Column(name = "name")
    private String name;

    @Column(name = "if_firmware")
    private long idFirmware;

    @JoinColumn(name = "id_manufacturer", referencedColumnName = "id")
    @ManyToOne
    private ManufacturerEntity idManufacturer;
    @OneToMany(mappedBy = "idModel")
    @JsonIgnore
    private Set<DeviceEntity> deviceEntitySet;
    @OneToMany(mappedBy = "idModel")
    @JsonIgnore
    private Set<FirmwareEntity> firmwareEntitySet;

    public ModelEntity() {
    }

    public ModelEntity(long id) {
	this.id = id;
    }

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public ManufacturerEntity getIdManufacturer() {
	return idManufacturer;
    }

    public void setIdManufacturer(ManufacturerEntity idManufacturer) {
	this.idManufacturer = idManufacturer;
    }

    public Set<DeviceEntity> getDeviceEntitySet() {
	return deviceEntitySet;
    }

    public void setDeviceEntitySet(Set<DeviceEntity> deviceEntitySet) {
	this.deviceEntitySet = deviceEntitySet;
    }

    public Set<FirmwareEntity> getFirmwareEntitySet() {
	return firmwareEntitySet;
    }

    public void setFirmwareEntitySet(Set<FirmwareEntity> firmwareEntitySet) {
	this.firmwareEntitySet = firmwareEntitySet;
    }

    @Override
    public String toString() {
	return "com.bluu.hdm.rest.entity.ModelEntity[ id=" + id + " ]";
    }

    public long getIdFirmware() {
	return idFirmware;
    }

    public void setIdFirmware(long idFirmware) {
	this.idFirmware = idFirmware;
    }

}
