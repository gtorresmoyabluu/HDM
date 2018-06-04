/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.beans;

import com.bluu.hdm.cwmp.pk.ServicePropertyPK;
import java.util.Collection;
import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

/**
 *
 * @author Gonzalo Torres
 */
public interface ServicePropertyLocalHome extends EJBLocalHome {

    ServicePropertyLocal findByPrimaryKey(ServicePropertyPK key) throws FinderException;

    ServicePropertyLocal create(Integer serviceid, String name, String value) throws CreateException;

    Collection<ServicePropertyLocal> findByServiceId(Integer serviceid) throws FinderException;
}
