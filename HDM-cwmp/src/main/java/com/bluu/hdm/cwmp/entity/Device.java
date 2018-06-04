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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.stereotype.Indexed;

/**
 *
 * @author Gonzalo Torres
 */
@Entity
@Table(name = "device")
@Indexed
public class Device extends BaseObject implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String mac;
    private String serialNumber;

    private DeviceModel deviceModel;
    private Area area;
    private Set<PolicyHistory> policiesHistory;

    private String cpu;
    private String ram;
    private String rom;
    private String firmwareVersion;
    private String cpeUsername;
    private String cpePassword;
    private int cpeStatus;
    private int firmwareStatus;

    private String ipAddress;
    private String stbUsername;
    private String stbPassword;
    private String homepageUrl;
    private String upgradeUrl;
    private String connectionReq;
    private String productClass;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    @Column(name = "mac", length = 45)
    public String getMac() {
        return mac;
    }

    @Column(name = "serial_number", length = 45)
    public String getSerialNumber() {
        return serialNumber;
    }

    @Column(name = "cpu", length = 45)
    public String getCpu() {
        return cpu;
    }

    @Column(name = "ram", length = 45)
    public String getRam() {
        return ram;
    }

    @Column(name = "rom", length = 45)
    public String getRom() {
        return rom;
    }

    @Column(name = "firmware_version", length = 100)
    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    @Column(name = "cpe_username", length = 45)
    public String getCpeUsername() {
        return cpeUsername;
    }

    @Column(name = "cpe_password", length = 45)
    public String getCpePassword() {
        return cpePassword;
    }

    @Column(name = "cpe_status", length = 45)
    public int getCpeStatus() {
        return cpeStatus;
    }

    @Column(name = "firmware_status", length = 45)
    public int getFirmwareStatus() {
        return firmwareStatus;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id", nullable = false)
    public DeviceModel getDeviceModel() {
        return deviceModel;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id", nullable = true)
    public Area getArea() {
        return area;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "device")
    public Set<PolicyHistory> getPoliciesHistory() {
        return policiesHistory;
    }

    @Column(name = "ip_address", length = 45)
    public String getIpAddress() {
        return ipAddress;
    }

    @Column(name = "stb_username", length = 45)
    public String getStbUsername() {
        return stbUsername;
    }

    @Column(name = "stb_password", length = 45)
    public String getStbPassword() {
        return stbPassword;
    }

    @Column(name = "homepage_url", length = 255)
    public String getHomepageUrl() {
        return homepageUrl;
    }

    @Column(name = "upgrade_url", length = 255)
    public String getUpgradeUrl() {
        return upgradeUrl;
    }

    @Column(name = "connection_request", length = 255)
    public String getConnectionReq() {
        return connectionReq;
    }

    @Column(name = "product_class", length = 255)
    public String getProductClass() {
        return productClass;
    }

    //setter
    public void setId(Long id) {
        this.id = id;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setDeviceModel(DeviceModel deviceModel) {
        this.deviceModel = deviceModel;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public void setRom(String rom) {
        this.rom = rom;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public void setCpeUsername(String cpeUsername) {
        this.cpeUsername = cpeUsername;
    }

    public void setCpePassword(String cpePassword) {
        this.cpePassword = cpePassword;
    }

    public void setCpeStatus(int cpeStatus) {
        this.cpeStatus = cpeStatus;
    }

    public void setFirmwareStatus(int firmwareStatus) {
        this.firmwareStatus = firmwareStatus;
    }

    public void setPoliciesHistory(Set<PolicyHistory> policiesHistory) {
        this.policiesHistory = policiesHistory;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setStbUsername(String stbUsername) {
        this.stbUsername = stbUsername;
    }

    public void setStbPassword(String stbPassword) {
        this.stbPassword = stbPassword;
    }

    public void setHomepageUrl(String homepageUrl) {
        this.homepageUrl = homepageUrl;
    }

    public void setUpgradeUrl(String upgradeUrl) {
        this.upgradeUrl = upgradeUrl;
    }

    public void setConnectionReq(String connectionReq) {
        this.connectionReq = connectionReq;
    }

    public void setProductClass(String productClass) {
        this.productClass = productClass;
    }

    @Override
    public String toString() {
        return "id: " + id + ", serialNumber: " + serialNumber;
    }

    @Override
    public boolean equals(Object o) {
        return false; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        return 1; //To change body of generated methods, choose Tools | Templates.
    }

}