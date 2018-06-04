/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.beans;

import java.util.Collection;

/**
 *
 * @author Gonzalo Torres
 */
public interface HostsLocal extends javax.ejb.EJBLocalObject, HostsLocalBusiness {

    byte[] getProps();

    void setProps(byte[] props);

    Integer getHwid();

    void setHwid(Integer hwid);

    String getUsername();

    void setUsername(String username);

    String getPassword();

    void setPassword(String password);

    Integer getAuthtype();

    void setAuthtype(Integer authtype);

    HardwareModelLocal getModel();

    void setModel(HardwareModelLocal m);

    String getCustomerid();

    void setCustomerid(String customerid);

    String getConrequser();

    void setConrequser(String conrequser);

    String getConreqpass();

    void setConreqpass(String conreqpass);

    void RequestConnection(int timeout) throws Exception;

    Boolean getCfgforce();

    void setCfgforce(Boolean cfgforce);

    String getProfileName();

    void setProfileName(String profileName);

    Collection<HostPropertyLocal> getProperties();

    void setProperties(Collection<HostPropertyLocal> properties);

    DeviceProfileLocal getProfile();

    Collection<Host2ServiceLocal> getServices();

    Boolean getForcePasswords();

    void setForcePasswords(Boolean forcePasswords);

    Boolean getReboot();

    void setReboot(Boolean reboot);
}
