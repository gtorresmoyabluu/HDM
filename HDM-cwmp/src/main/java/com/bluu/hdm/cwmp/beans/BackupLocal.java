/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.beans;

import java.sql.Timestamp;
import javax.ejb.EJBLocalObject;

/**
 *
 * @author Gonzalo Torres
 */
public interface BackupLocal extends EJBLocalObject {

    public static final int TYPE_VENDOR_INDEPENDANT = 1;
    public static final int TYPE_VENDOR_SPECIFIC = 2;

    Integer getHostid();

    Integer getType();

    Timestamp getTime();

    byte[] getCfg();

    void setCfg(byte[] cfg);
}
