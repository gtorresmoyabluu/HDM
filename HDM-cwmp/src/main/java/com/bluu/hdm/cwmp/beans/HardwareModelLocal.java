/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.beans;

import java.util.Collection;
import javax.ejb.EJBLocalObject;

/**
 *
 * @author Gonzalo Torres
 */
public interface HardwareModelLocal extends EJBLocalObject {

    java.lang.Object getId();

    java.lang.String getOui();

    void setId(java.lang.Object id);

    void setOui(java.lang.String oui);

    String getHclass();

    void setHclass(String hclass);

    String getDisplayName();

    void setDisplayName(String DisplayName);

    String getManufacturer();

    void setManufacturer(String manufacturer);

    String getVersion();

    void setVersion(String version);

    Collection<SoftwareLocal> getFirmware();

    void setFirmware(Collection m);

    String getProfileToAssign();

    void setProfileToAssign(String profileToAssign);
}
