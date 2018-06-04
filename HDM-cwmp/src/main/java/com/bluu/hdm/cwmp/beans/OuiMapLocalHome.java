/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.beans;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

/**
 *
 * @author Gonzalo Torres
 */
public interface OuiMapLocalHome extends EJBLocalHome {

    OuiMapLocal findByPrimaryKey(String oui) throws FinderException;

    OuiMapLocal create(String oui, String mappedoui) throws CreateException;
}