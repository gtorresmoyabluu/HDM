/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.beans;

import java.util.Collection;
import javax.ejb.EJBLocalObject;

/**
 *
 * @author Gonzalo Torres
 */
public interface ServiceLocal extends EJBLocalObject {

    public static final String TYPE_VOICEPROFILE = "voiceprofile";
    public static final String TYPE_PHONELINE = "phoneline";

    java.lang.Object getId();

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    public abstract Collection<ServicePropertyLocal> getProperties();

    String getType();

    void setType(String type);

    String getDefaultparentservice();

    void setDefaultparentservice(String defaultparentservice);
}
