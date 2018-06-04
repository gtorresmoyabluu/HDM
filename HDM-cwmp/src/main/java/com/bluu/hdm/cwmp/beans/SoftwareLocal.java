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
public interface SoftwareLocal extends javax.ejb.EJBLocalObject, SoftwareLocalBusiness {

    String getFilename();

    void setFilename(String filename);
}
