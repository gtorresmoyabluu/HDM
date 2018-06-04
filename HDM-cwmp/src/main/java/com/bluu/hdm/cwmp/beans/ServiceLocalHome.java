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
public interface ServiceLocalHome extends EJBLocalHome {

    ServiceLocal findByPrimaryKey(Object key) throws FinderException;

    ServiceLocal create() throws CreateException;

    Collection<ServiceLocal> findAll() throws FinderException;

    Collection findByType(final String type) throws FinderException;
}
