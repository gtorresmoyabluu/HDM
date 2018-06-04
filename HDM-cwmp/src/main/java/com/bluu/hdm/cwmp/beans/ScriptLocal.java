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
public interface ScriptLocal extends EJBLocalObject {

    String getName();

    byte[] getScript();

    void setScript(byte[] script);

    String getDescription();

    void setDescription(String description);
}
