/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Gonzalo Torres
 */
public class ConfigUtils {

    private static Properties prop = new Properties();
    private final static String propFileName = "../etc/app.conf";
    private static InputStream inputStream = null;

    public ConfigUtils() {

    }
    private static ConfigUtils instance = null;

    public static ConfigUtils getInstance() {
        if (instance == null) {
            instance = new ConfigUtils();
        }
        return instance;
    }

    public static void init() throws IOException {
        inputStream = new FileInputStream(propFileName);
        if (inputStream != null) {
            prop.load(inputStream);
        } else {
            throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
        }
    }

    public String getModelName(String modelname) throws IOException {
        // get the property value and print it out
        init();
        String VnptName = prop.getProperty(modelname);
        if (StringUtils.isBlank(VnptName)) {
            VnptName = modelname;
        }
        return VnptName;
    }

    public String getProDefaultGW() throws IOException {
        // get the property value and print it out
        init();
        String gw = prop.getProperty("DEFAULT_GW");
        if (StringUtils.isBlank(gw)) {
            gw = "ppp0.4";
        }
        return gw;
    }

    public String getProDefaultDNS() throws IOException {
        // get the property value and print it out
        init();
        String dns = prop.getProperty("DEFAULT_DNS");
        if (StringUtils.isBlank(dns)) {
            dns = "ppp0.4";
        }
        return dns;
    }

    public static void main(String[] args) {
        try {
            String interval = ConfigUtils.getInstance().getModelName("968380GERG");
            System.out.println(interval);
        } catch (IOException iOException) {
            System.out.println(iOException.getMessage());
        }
    }
}
