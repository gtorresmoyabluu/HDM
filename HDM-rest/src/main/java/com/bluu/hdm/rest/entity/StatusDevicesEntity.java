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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Gonzalo Torres
 */
@Entity
@Table(name = "status_devices")
public class StatusDevicesEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    
    @OneToMany(mappedBy = "statusDevice")
    @JsonIgnore
    private Set<CpeEntity> devices;

    public StatusDevicesEntity() {
    }

    public StatusDevicesEntity(long id) {
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

    public Set<CpeEntity> getDevices() {
        return devices;
    }

    public void setDevices(Set<CpeEntity> devices) {
        this.devices = devices;
    }    

    @Override
    public String toString() {
	return "com.bluu.hdm.rest.entity.StatusDevicesEntity[ id=" + id + " ]";
    }

}
