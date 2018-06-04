/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.vendors;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gonzalo Torres
 */
public class Vendor {

    protected String hwversion;
    // Update by comparing ProvisiniongCode
    public static final int CFG_UPDATE_METHOD_1 = 1;
    // Update by comparing DeviceInfo.VendorConfigFile.1.Name/Version
    public static final int CFG_UPDATE_METHOD_2 = 2;
    private static HashMap<String, Class> vendors = new HashMap<String, Class>();

    static {
        vendors.put("00147F", Thomson.class);
        vendors.put("0090D0", Thomson.class);
    }

    public String[] CheckConfig(String filename, String name, String version, String cfg) {
        if (cfg.contains("[ env.ini ]")) {
            Thomson v = new Thomson();
            v.hwversion = this.hwversion;
            return v.CheckConfig(filename, name, version, cfg);
        }
        if (Broadcomm.DetectFromConfig(cfg)) {
            // Looks like broadcom rebranded clone
        }
        return null;
    }

    public String UpdateConfig(String filename, String name, String version, String cfg) {
        if (Broadcomm.DetectFromConfig(cfg)) {
            Vendor v = new Broadcomm();
            return v.UpdateConfig(filename, name, version, cfg);
        }
        return null;
    }

    public static Vendor getVendor(String oui, String hardwareClass, String hardwareVersion) {
        try {
            Class c = vendors.get(oui);
//            System.out.println ("Class="+c);
            Vendor v;
            if (c == null) {
                v = new Vendor();
            } else {
                Constructor<?> m = c.getConstructor();
                //          System.out.println ("Constructor="+m);
                v = (Vendor) m.newInstance();
            }
            v.hwversion = hardwareVersion;
            return v;
        } catch (Exception ex) {
            Logger.getLogger(Vendor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Vendor();
    }

    public int getConfigUpdateMethod() {
        return CFG_UPDATE_METHOD_1;
    }
}
