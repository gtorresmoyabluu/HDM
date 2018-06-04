/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.beans;

import java.sql.Timestamp;

/**
 *
 * @author Gonzalo Torres
 */
public interface HostsLocalBusiness {

    public abstract String getOui();

    public abstract String getSerialno();

    public abstract String getUrl();

    public abstract void setUrl(String url);

    void setConfigname(String configname);

    String getConfigname();

    void setCurrentsoftware(String currentsoftware);

    String getCurrentsoftware();

    void setSfwupdtime(Timestamp sfwupdtime);

    Timestamp getSfwupdtime();

    void setSfwupdres(String sfwupdres);

    String getSfwupdres();

    void setCfgupdres(String cfgupdres);

    String getCfgupdres();

    void setLastcontact(Timestamp lastcontact);

    Timestamp getLastcontact();

    void setCfgupdtime(Timestamp cfgupdtime);

    Timestamp getCfgupdtime();

    void setHardware(java.lang.String hardware);

    java.lang.String getHardware();

    void setCfgversion(java.lang.String cfgversion);

    java.lang.String getCfgversion();

    Object getId();

    public void setId(Object id);
}
