/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.model;

/**
 *
 * @author Gonzalo Torres
 */
public class DeviceInterface {
    
    private String name;
    private String ifName;
    private String ifGroupKey; // --> FilterBridgeReference
    private String ifReference;
    private String ifType;
    private String ifKey;
    private String filterKey;
    private String externalIp;
    private String connectionType;
    private String vlanId;

    public String getIfGroupKey() {
        return ifGroupKey;
    }

    public void setIfGroupKey(String ifGroupKey) {
        this.ifGroupKey = ifGroupKey;
    }

    public String getIfReference() {
        return ifReference;
    }

    public void setIfReference(String ifReference) {
        this.ifReference = ifReference;
    }

    public String getIfType() {
        return ifType;
    }

    public void setIfType(String ifType) {
        this.ifType = ifType;
    }

    public String getFilterKey() {
        return filterKey;
    }

    public void setFilterKey(String filterKey) {
        this.filterKey = filterKey;
    }

    public String getIfKey() {
        return ifKey;
    }

    public void setIfKey(String ifKey) {
        this.ifKey = ifKey;
    }

    public String getIfName() {
        return ifName;
    }

    public void setIfName(String ifName) {
        this.ifName = ifName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExternalIp() {
        return externalIp;
    }

    public void setExternalIp(String externalIp) {
        this.externalIp = externalIp;
    }

    public String getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }

    public String getVlanId() {
        return vlanId;
    }

    public void setVlanId(String vlanId) {
        this.vlanId = vlanId;
    }
}
