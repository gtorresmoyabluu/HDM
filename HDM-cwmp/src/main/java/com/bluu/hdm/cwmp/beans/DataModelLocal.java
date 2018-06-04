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
public interface DataModelLocal extends EJBLocalObject {

    java.lang.String getName();

    String getType();

    void setType(String type);

    long getMin();

    void setMin(long min);

    long getMax();

    void setMax(long max);

    int getLength();

    void setLength(int length);

    byte[] getDescription();

    void setDescription(byte[] description);

    String getVersion();

    void setVersion(String version);

    String getDefaultvalue();

    void setDefaultvalue(String defaut);

    boolean getWritable();

    void setWritable(boolean writable);

    String getTrname();

    void setTrname(String trname);
}
