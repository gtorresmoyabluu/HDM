/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.beans;

import com.bluu.hdm.cwmp.pk.PropertyPK;
import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

/**
 *
 * @author Gonzalo Torres
 */
public interface PropertyLocalHome extends EJBLocalHome {

    PropertyLocal findByPrimaryKey(PropertyPK key) throws FinderException;

    PropertyLocal create(Integer parentId, Integer type, String name, String value) throws CreateException;
}
