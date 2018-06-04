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
public interface DeviceProfileLocal extends EJBLocalObject {

    java.lang.String getName();

    Integer getInforminterval();

    void setInforminterval(Integer informinterval);

    Integer getDayskeepstats();

    void setDayskeepstats(Integer dayskeepstats);

    Boolean getSavestats();

    void setSavestats(Boolean savestats);

    Boolean getSaveLog();

    void setSaveLog(Boolean saveLog);

    Boolean getSaveParamValues();

    void setSaveParamValues(Boolean saveParamValues);

    Integer getSaveParamValuesInterval();

    void setSaveParamValuesInterval(Integer saveParamValuesInterval);

    Boolean getSaveParamValuesOnChange();

    void setSaveParamValuesOnChange(Boolean saveParamValuesOnChange);

    Boolean getSaveParamValuesOnBoot();

    void setSaveParamValuesOnBoot(Boolean saveParamValuesOnBoot);

    String getScriptname();

    void setScriptname(String scriptname);

    Collection<HostsLocal> getHosts();

    Collection<ProfilePropertyLocal> getProperties();

    String getBaseprofile();

    void setBaseprofile(String baseprofile);
}
