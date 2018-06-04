/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.beans;

import java.sql.Timestamp;
import javax.ejb.EJBLocalObject;

/**
 *
 * @author Gonzalo Torres
 */
public interface DSLStatsLocal extends EJBLocalObject {

    public abstract Timestamp getTime();

    public abstract Integer getHostid();

    Integer getDownstreamAttenuation();

    void setDownstreamAttenuation(Integer DownstreamAttenuation);

    Integer getDownstreamCurrRate();

    void setDownstreamCurrRate(Integer DownstreamCurrRate);

    Integer getDownstreamMaxRate();

    void setDownstreamMaxRate(Integer DownstreamMaxRate);

    Integer getDownstreamNoiseMargin();

    void setDownstreamNoiseMargin(Integer DownstreamNoiseMargin);

    Integer getDownstreamPower();

    void setDownstreamPower(Integer DownstreamPower);

    Integer getUpstreamAttenuation();

    void setUpstreamAttenuation(Integer UpstreamAttenuation);

    Integer getUpstreamCurrRate();

    void setUpstreamCurrRate(Integer UpstreamCurrRate);

    Integer getUpstreamMaxRate();

    void setUpstreamMaxRate(Integer UpstreamMaxRate);

    Integer getUpstreamNoiseMargin();

    void setUpstreamNoiseMargin(Integer UpstreamNoiseMargin);

    Integer getUpstreamPower();

    void setUpstreamPower(Integer UpstreamPower);

    String getStatus();

    void setStatus(String Status);

    String getModulationType();

    void setModulationType(String ModulationType);
}
