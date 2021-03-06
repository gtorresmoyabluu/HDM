/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.beans;

import com.bluu.hdm.cwmp.pk.HostPropertyPK;
import java.util.Collection;
import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

/**
 *
 * @author Gonzalo Torres
 */
public interface HostPropertyLocalHome extends EJBLocalHome {

    HostPropertyLocal findByPrimaryKey(HostPropertyPK key) throws FinderException;

    HostPropertyLocal create(Integer parentId, String name, String value) throws CreateException;

    Collection<HostPropertyLocal> findByHost(int hostid) throws FinderException;
}