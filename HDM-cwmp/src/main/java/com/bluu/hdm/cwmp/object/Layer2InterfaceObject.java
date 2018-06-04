/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.object;

import java.util.ArrayList;

/**
 *
 * @author Gonzalo Torres
 */
public class Layer2InterfaceObject {

    public final static String ATM_TYPE = "ATM";
    public final static String PTM_TYPE = "PTM";
    public final static String ETHERNET_TYPE = "ETHERNET";

    private String rootName;
    private String ifName;
    private String type;
    private int instance;

    private ArrayList<SimpleObject> listObject = new ArrayList<SimpleObject>();

    public String getRootName() {
        return rootName;
    }

    public void setRootName(String rootName) {
        this.rootName = rootName;
    }

    public String getIfName() {
        return ifName;
    }

    public void setIfName(String ifName) {
        this.ifName = ifName;
    }

    public int getInstance() {
        return instance;
    }

    public void setInstance(int instance) {
        this.instance = instance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<SimpleObject> getListObject() {
        return listObject;
    }

    public void setListObject(ArrayList<SimpleObject> listObject) {
        this.listObject = listObject;
    }
}
