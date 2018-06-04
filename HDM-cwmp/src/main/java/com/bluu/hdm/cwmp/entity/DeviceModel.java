/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.stereotype.Indexed;

/**
 *
 * @author Gonzalo Torres
 */
@Entity
@Table(name = "device_model")
@Indexed
public class DeviceModel extends BaseObject implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String description;
    private Set<Device> devices;
    private Set<Firmware> firmwares;
    private Set<Policy> policies;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    @Column(name = "name", length = 100)
    public String getName() {
        return name;
    }

    @Column(name = "description", length = 100)
    public String getDescription() {
        return description;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "deviceModel")
    public Set<Device> getDevices() {
        return devices;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "deviceModel")

    public Set<Firmware> getFirmwares() {
        return firmwares;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "deviceModel")
    public Set<Policy> getPolicies() {
        return policies;
    }

    //setter
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDevices(Set<Device> devices) {
        this.devices = devices;
    }

    public void setFirmwares(Set<Firmware> firmwares) {
        this.firmwares = firmwares;
    }

    public void setPolicies(Set<Policy> policies) {
        this.policies = policies;
    }

    @Override
    public String toString() {
        return "id: " + id + ", name: " + name;
    }

    @Override
    public boolean equals(Object o) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return 0;
    }

}
