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
public interface OuiMapLocal extends EJBLocalObject {

    String getOui();

    String getMappedoui();

    void setMappedoui(String mappedoui);
}
