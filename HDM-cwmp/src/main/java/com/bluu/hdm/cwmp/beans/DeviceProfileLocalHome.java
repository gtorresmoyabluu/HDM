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
public interface DeviceProfileLocalHome extends EJBLocalHome {

    DeviceProfileLocal findByPrimaryKey(String key) throws FinderException;

    DeviceProfileLocal create(String key) throws CreateException;

    Collection findAll() throws FinderException;
}
