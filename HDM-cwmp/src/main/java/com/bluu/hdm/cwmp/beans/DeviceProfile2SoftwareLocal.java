/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.beans;

import javax.ejb.EJBLocalObject;

/**
 *
 * @author Gonzalo Torres
 */
public interface DeviceProfile2SoftwareLocal extends EJBLocalObject {

    public final static String AUTOUPDATE = "automatic";
    public final static String NOUPDATE = "noupdate";

    String getProfileName();

    void setProfileName(String profileName);

    Integer getHwid();

    void setHwid(Integer hwid);

    String getVersion();

    void setVersion(String version);
    /*
    SoftwareLocal getFirmware();
    void setFirmware(SoftwareLocal s);
     */
}
