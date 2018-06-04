/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.beans;

import javax.ejb.EJBLocalObject;

/**
 *
 * @author Gonzalo Torres
 */
public interface HostPropertyLocal extends EJBLocalObject {

    Integer getParentId();

    //void setParentId(Integer parentId);
    String getName();

    //void setName(String name);
    String getValue();

    void setValue(String value);

    HostsLocal getHost();

    void setHost(HostsLocal host);
}
