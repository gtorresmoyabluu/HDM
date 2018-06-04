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
public interface HostsLocalHome extends EJBLocalHome {

    HostsLocal findByPrimaryKey(Object key) throws FinderException;

    public HostsLocal create(Integer hwid, String serialno, String url) throws CreateException;

    /**
     * 
     * @param serialno
     * @return 
     * @throws javax.ejb.FinderException
     */
    Collection<HostsLocal> findBySerialno(String serialno) throws FinderException;

    /**
     * 
     * @param url
     * @return 
     * @throws javax.ejb.FinderException
     */
    Collection findByUrl(String url) throws FinderException;

    Collection findAll() throws FinderException;

    Collection findByPartialSN(Integer hwid, String snprefix) throws FinderException;

    HostsLocal findByIp(java.lang.String ip) throws FinderException;

    HostsLocal findByHwidAndSn(Integer hwid, String sn) throws FinderException;

    Collection findByIpM(String ip) throws FinderException;

    Collection<HostsLocal> findByCustomerId(String customerId) throws FinderException;
}
