/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.beans;

import java.util.Properties;
import javax.ejb.EJBLocalObject;

/**
 *
 * @author Gonzalo Torres
 */
public interface SoftwareDetailLocal extends EJBLocalObject {

    Integer getHwid();

    String getVersion();

    byte[] getParamNames();

    void setParamNames(byte[] paramNames);

    byte[] getMethods();

    void setMethods(byte[] methods);

    byte[] getVoicecaps();

    void setVoicecaps(byte[] voicecaps);

    Properties getVoiceCaps();
}
