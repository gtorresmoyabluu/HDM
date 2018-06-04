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
public class BrigdingGroup {

    private String index;
    private String BridgeKey;
    private String BridgeName;
    private String BridgeEnable;
    
    private DeviceInterface deviceInterface;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getBridgeEnable() {
        return BridgeEnable;
    }

    public void setBridgeEnable(String BridgeEnable) {
        this.BridgeEnable = BridgeEnable;
    }

    public String getBridgeKey() {
        return BridgeKey;
    }

    public void setBridgeKey(String BridgeKey) {
        this.BridgeKey = BridgeKey;
    }

    public String getBridgeName() {
        return BridgeName;
    }

    public void setBridgeName(String BridgeName) {
        this.BridgeName = BridgeName;
    }

    public String getBridgeStatus() {
        return BridgeStatus;
    }

    public void setBridgeStatus(String BridgeStatus) {
        this.BridgeStatus = BridgeStatus;
    }

    public String getVlanId() {
        return vlanId;
    }

    public void setVlanId(String vlanId) {
        this.vlanId = vlanId;
    }
    private String BridgeStatus;
    private String vlanId;
}
