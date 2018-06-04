/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.beans;

/**
 *
 * @author Gonzalo Torres
 */
public interface SoftwareLocalBusiness {

    public abstract String getVersion();

    public abstract HardwareModelLocal getHardware();

    public abstract void setHardware(HardwareModelLocal hwmodel);

    public abstract String getMinversion();

    public abstract void setMinversion(String minversion);

    public abstract String getUrl();

    public abstract void setUrl(String url);

    void setSize(long size);

    long getSize();

    void setImg(byte[] img);

    byte[] getImg();

    Integer getHwid();

    void setHwid(Integer hardware);
}
