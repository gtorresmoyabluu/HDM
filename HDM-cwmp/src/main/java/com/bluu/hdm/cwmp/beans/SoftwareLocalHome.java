/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.beans;

import com.bluu.hdm.cwmp.pk.SoftwarePK;
import java.util.Collection;
import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

/**
 *
 * @author Gonzalo Torres
 */
public interface SoftwareLocalHome extends EJBLocalHome {

    SoftwareLocal findByPrimaryKey(SoftwarePK key) throws FinderException;

    public SoftwareLocal create(Integer hwid, String version, String minversion, String url) throws CreateException;

    /**
     * 
     * @param hardware
     * @return 
     * @throws javax.ejb.FinderException
     */
    Collection<SoftwareLocal> findByHardware(Integer hardware) throws FinderException;

    /**
     * 
     * @param version
     * @return 
     * @throws javax.ejb.FinderException
     */
    Collection findByVersion(String version) throws FinderException;

    Collection findAll() throws javax.ejb.FinderException;
}
