/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.beans;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.jms.JMSException;
import javax.naming.NamingException;

/**
 *
 * @author Gonzalo Torres
 */
public interface CPELocalHome extends EJBLocalHome {

    CPELocal create() throws CreateException, NamingException, JMSException;
}
