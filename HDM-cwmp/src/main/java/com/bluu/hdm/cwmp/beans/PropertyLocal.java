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
public interface PropertyLocal extends EJBLocalObject {
    static final int TYPE_APPLICATION = 1;
    static final int TYPE_PROFILE = 2;
    static final int TYPE_CPE = 3;

    Integer getParentId();

    void setParentId(Integer parentId);

    Integer getType();

    void setType(Integer type);

    String getName();

    void setName(String name);

    String getValue();

    void setValue(String value);
}
