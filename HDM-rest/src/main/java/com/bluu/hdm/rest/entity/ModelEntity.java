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
    private Long id;
    @Size(max = 255)
    @Column(name = "name")
    private String name;

    @JoinColumn(name = "id_manufacturer", referencedColumnName = "id")
    @ManyToOne
    private ManufacturerEntity manufacturer;

    @JsonIgnore
    @OneToMany(mappedBy = "idModel")
    private Set<CpeEntity> devices;
    
    @JsonIgnore
    @OneToMany(mappedBy = "idModel")    
    private Set<FirmwareEntity> firmwares;

    public ModelEntity() {
    }

    public ModelEntity(Long id) {
        this.id = id;
    }

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

    public ManufacturerEntity getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(ManufacturerEntity manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Set<CpeEntity> getDevices() {
        return devices;
    }

    public void setDevices(Set<CpeEntity> devices) {
        this.devices = devices;
    }

    public Set<FirmwareEntity> getFirmwares() {
        return firmwares;
    }

    public void setFirmwares(Set<FirmwareEntity> firmwares) {
        this.firmwares = firmwares;
    }

    @Override
    public String toString() {
        return "com.bluu.hdm.rest.entity.ModelEntity[ id=" + id + " ]";
    }
}
