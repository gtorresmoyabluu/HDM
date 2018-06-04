/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.beans;

/**
 *
 * @author Gonzalo Torres
 */
public interface ConfigurationLocalBusiness {
    /*
    void setHardware(Integer hardware);
    
    Integer getHardware();
     */

    HardwareModelLocal getHardware();

    void setHardware(HardwareModelLocal hwmodel);

    void setConfig(byte[] config);

    byte[] getConfig();

    void setName(String name);

    String getName();

    void setFilename(java.lang.String filename);

    java.lang.String getFilename();

    void setVersion(java.lang.String version);

    java.lang.String getVersion();
}
