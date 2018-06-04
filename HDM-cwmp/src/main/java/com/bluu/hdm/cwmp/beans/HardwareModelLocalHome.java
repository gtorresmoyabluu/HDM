/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.beans;

import java.util.Collection;
import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

/**
 *
 * @author Gonzalo Torres
 */
public interface HardwareModelLocalHome extends EJBLocalHome {

    HardwareModelLocal findByPrimaryKey(java.lang.Object key) throws FinderException;

    HardwareModelLocal create(java.lang.Object key) throws CreateException;

    Collection<HardwareModelLocal> findAll() throws FinderException;

    HardwareModelLocal create(String dname, String manufacturer, String oui, String hclass, String version) throws CreateException;

    HardwareModelLocal findByOuiAndClass(String oui, String hclass) throws FinderException;

    HardwareModelLocal findByOuiAndClassAndVersion(String oui, String hclass, String version) throws FinderException;

    HardwareModelLocal findByClassAndVersion(String clas, String version) throws FinderException;
}
