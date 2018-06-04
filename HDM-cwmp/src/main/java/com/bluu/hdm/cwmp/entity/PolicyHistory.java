/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.stereotype.Indexed;

/**
 *
 * @author Gonzalo Torres
 */
@Entity
@Table(name = "policy_history")
@Indexed
public class PolicyHistory extends BaseObject implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private Policy policy;
    private Device device;
    private String firmwareOldVersion;
    private String firmwareNewVersion;
    private Timestamp startTime;
    private Timestamp endTime;
    private int status; //0-fail, 1-success, 2- updating
    private String description;
    private String deviceSerialNumber;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "policy_id", nullable = false)
    public Policy getPolicy() {
        return policy;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", nullable = false)
    public Device getDevice() {
        return device;
    }

    @Column(name = "firmware_old_version", length = 45)
    public String getFirmwareOldVersion() {
        return firmwareOldVersion;
    }

    @Column(name = "firmware_new_version", length = 45)
    public String getFirmwareNewVersion() {
        return firmwareNewVersion;
    }

    @Column(name = "start_time")

    public Timestamp getStartTime() {
        return startTime;
    }

    @Column(name = "end_time")
    public Timestamp getEndTime() {
        return endTime;
    }

    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    @Column(name = "device_serial_number")
    public String getDeviceSerialNumber() {
        return deviceSerialNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPolicy(Policy policy) {
        this.policy = policy;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public void setFirmwareOldVersion(String firmwareOldVersion) {
        this.firmwareOldVersion = firmwareOldVersion;
    }

    public void setFirmwareNewVersion(String firmwareNewVersion) {
        this.firmwareNewVersion = firmwareNewVersion;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDeviceSerialNumber(String deviceSerialNumber) {
        this.deviceSerialNumber = deviceSerialNumber;
    }

    @Override
    public String toString() {
        return "id: " + id;
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