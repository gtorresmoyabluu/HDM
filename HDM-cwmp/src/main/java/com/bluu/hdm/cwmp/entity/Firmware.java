/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.springframework.stereotype.Indexed;

/**
 *
 * @author Gonzalo Torres
 */
@Entity
@Table(name = "firmware")
@Indexed
public class Firmware extends BaseObject implements Serializable {

    private Long id;
    private String version;
    private DeviceModel deviceModel;
    private Timestamp releaseDate;
    private String releaseNote;
    private String firmwarePath;
    private int deviceUseageNumber;

    private int fwDefault;

    private Set<Policy> policies;

    private String modelName;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    @Column(name = "version", length = 45)
    public String getVersion() {
        return version;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id", nullable = false)
    public DeviceModel getDeviceModel() {
        return deviceModel;
    }

    @Column(name = "release_date")
    public Timestamp getReleaseDate() {
        return releaseDate;
    }

    @Column(name = "release_note")
    public String getReleaseNote() {
        return releaseNote;
    }

    @Column(name = "firmware_path", length = 255)
    public String getFirmwarePath() {
        return firmwarePath;
    }

    @Column(name = "device_usage_number")
    public int getDeviceUseageNumber() {
        return deviceUseageNumber;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "firmware")
    public Set<Policy> getPolicies() {
        return policies;
    }

    @Column(name = "fw_default")
    public int getFwDefault() {
        return fwDefault;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setDeviceModel(DeviceModel deviceModel) {
        this.deviceModel = deviceModel;
    }

    public void setReleaseDate(Timestamp releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setReleaseNote(String releaseNote) {
        this.releaseNote = releaseNote;
    }

    public void setFirmwarePath(String firmwarePath) {
        this.firmwarePath = firmwarePath;
    }

    public void setDeviceUseageNumber(int deviceUseageNumber) {
        this.deviceUseageNumber = deviceUseageNumber;
    }

    public void setPolicies(Set<Policy> policies) {
        this.policies = policies;
    }

    public void setFwDefault(int fwDefault) {
        this.fwDefault = fwDefault;
    }

    @Override
    public String toString() {
        return "id: " + id + ", version: " + version;
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

    @Transient
    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

}
