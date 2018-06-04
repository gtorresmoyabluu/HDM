/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.ws.response;

import com.bluu.hdm.cwmp.model.DeviceInfo;

/**
 *
 * @author Gonzalo Torres
 */
public class DeviceInfoResponse extends BasicResponse {

    private DeviceInfo deviceInfo;

    public DeviceInfo getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
    }
}
