/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.vendors;

import com.bluu.hdm.cwmp.conf.Configurator;

/**
 *
 * @author Gonzalo Torres
 */
public class Broadcomm extends Vendor {

    private static String PROVISIONING_CODE = "provisioningCode=\"";

    public static boolean DetectFromConfig(String cfg) {
        return (cfg.contains("<psitree>") && cfg.contains("<tr69c "));
    }

    @Override
    public String UpdateConfig(String filename, String name, String version, String cfg) {
        StringBuilder r = new StringBuilder(cfg);
        int s = r.indexOf(PROVISIONING_CODE);
        if (s != -1) {
            int e = r.indexOf("\"", s + PROVISIONING_CODE.length());
            if (e != -1) {
                r.replace(s, e, Configurator.getProvisioningCode(name, version));
                return r.toString();
            }
        }
        return null;
    }
}
