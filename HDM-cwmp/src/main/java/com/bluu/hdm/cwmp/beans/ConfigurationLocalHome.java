/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.beans;

import com.bluu.hdm.cwmp.pk.ConfigurationPK;
import java.util.Collection;
import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

/**
 *
 * @author Gonzalo Torres
 */
public interface ConfigurationLocalHome extends EJBLocalHome {

    ConfigurationLocal findByPrimaryKey(ConfigurationPK key) throws FinderException;

    ConfigurationLocal create(Integer hwid, String name) throws CreateException;

    Collection findAll() throws FinderException;

    Collection findByHwid(Integer hwid) throws FinderException;
}
