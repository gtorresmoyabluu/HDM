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
public interface DataModelLocalHome extends EJBLocalHome {

    DataModelLocal findByPrimaryKey(String key) throws FinderException;

    DataModelLocal create(String key) throws CreateException;

    public DataModelLocal create(String name,
                                 String type,
                                 long min, 
                                 long max, 
                                 int length, 
                                 String description, 
                                 String version,
                                 String def, 
                                 boolean writable, 
                                 String trname) throws CreateException;

    int getCount();

    DataModelLocal findByName(String name) throws FinderException;

    Collection<DataModelLocal> findAll() throws FinderException;

    Collection<String> getChildNames(String parent, boolean fqdn);

    DataModelLocal lookupByName(String name) throws FinderException;

    String[] getObjectNames();

    public Collection<String> findChildNames(String parent) throws FinderException;
}
