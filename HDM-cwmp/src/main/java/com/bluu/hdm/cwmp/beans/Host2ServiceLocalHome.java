/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.beans;

import com.bluu.hdm.cwmp.pk.Host2ServicePK;
import java.util.Collection;
import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

/**
 *
 * @author Gonzalo Torres
 */
public interface Host2ServiceLocalHome extends EJBLocalHome {

    Host2ServiceLocal findByPrimaryKey(Host2ServicePK key) throws FinderException;

    Host2ServiceLocal create(Integer hostid, Integer serviceid, Integer instance) throws CreateException;

    Collection<Host2ServiceLocal> findByHostId(Integer hostid) throws FinderException;
}