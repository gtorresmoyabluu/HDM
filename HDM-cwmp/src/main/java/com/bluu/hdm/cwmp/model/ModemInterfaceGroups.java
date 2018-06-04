/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.model;

import java.util.ArrayList;

/**
 *
 * @author Gonzalo Torres
 */
public class ModemInterfaceGroups {

    private ArrayList<BrigdingGroup> brigdingGroup = new ArrayList<BrigdingGroup>();
    private ArrayList<DeviceInterface> deviceInterfaces = new ArrayList<DeviceInterface>();

    public ArrayList<BrigdingGroup> getBrigdingGroup() {
        return brigdingGroup;
    }

    public void setBrigdingGroup(ArrayList<BrigdingGroup> brigdingGroup) {
        this.brigdingGroup = brigdingGroup;
    }

    public ArrayList<DeviceInterface> getDeviceInterfaces() {
        return deviceInterfaces;
    }

    public void setDeviceInterfaces(ArrayList<DeviceInterface> deviceInterfaces) {
        this.deviceInterfaces = deviceInterfaces;
    }
}
