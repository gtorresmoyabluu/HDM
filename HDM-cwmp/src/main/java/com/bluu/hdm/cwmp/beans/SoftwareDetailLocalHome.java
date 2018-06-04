/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.beans;

import com.bluu.hdm.cwmp.pk.SoftwareDetailPK;
import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

/**
 *
 * @author Gonzalo Torres
 */
public interface SoftwareDetailLocalHome extends EJBLocalHome {

    SoftwareDetailLocal findByPrimaryKey(SoftwareDetailPK key) throws FinderException;

    SoftwareDetailLocal create(Integer hwid, String version, byte[] paramNames, byte[] methods) throws CreateException;

    boolean exists(Integer hwid, String version);
    
}
