/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

/**
 *
 * @author Gonzalo Torres
 */
public class AppConfig {

    private static ResourceLoader resourceLoader = new DefaultResourceLoader();
    
    protected static final StringBuilder sb = new StringBuilder();
    protected static final Logger logger = Logger.getLogger(AppConfig.class);
    protected static final Properties prop = new Properties();

    // ONT Version
    public static final int ONT_V1 = 1;
    public static final int ONT_V2 = 2;
    public static final int ONT_V3 = 3;

    public static final String ONT_V1_WANDEVICE_INDEX = "3";
    public static final String ONT_V2_WANDEVICE_INDEX = "5";

    static {
        initDefault();
        InputStream input = null;
        String appConfigFile = "";
        try {
            appConfigFile = System.getProperty("appConfigFile", resourceLoader.getResource("classpath:app.conf").getURL().getPath());
//        String appConfigFile = System.getProperty("appConfigFile", "/etc/app.conf");
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(AppConfig.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            if (new File(appConfigFile).exists()) {
                input = new FileInputStream(appConfigFile);
                // load a properties file
                prop.load(input);
            } else {
                logger.debug("appConfigFile not Found: " + appConfigFile);
            }
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }

    }

    private static void initDefault() {

    }

    public static Properties getProperties() {
        return prop;
    }

    public static boolean isONTV2(String modelName) {
        String modelNameV2 = prop.getProperty("ONT_V2_MODEL_NAME", "GW040_2015");
        return modelNameV2.equalsIgnoreCase(modelName);
    }

    public static int getDeviceVersion(String modelName) {
        if (isONTV2(modelName)) {
            return AppConfig.ONT_V2;
        } else {
            return AppConfig.ONT_V1;
        }
    }

    public static String getPathWifi() {
        return prop.getProperty("PATH_WIFI", "InternetGatewayDevice.LANDevice.1.WLANConfiguration.");
    }

    public static boolean isDisableWifiVinaphone() {
        String isDisable = prop.getProperty("DISABLE_VINAPHONE_WIFI", "true");
        return Boolean.valueOf(isDisable);
    }

    public static String getKeyCheckPathVinaphoneWifi() {
        return prop.getProperty("KEY_CHECK_WIFI_PATH", "WlSsid");
    }

    public static String getKeyCheckNameVinaphoneWifi() {
        return prop.getProperty("KEY_CHECK_WIFI_NAME", "VinaphoneWifi");
    }

    public static String getWifiName() {
        return prop.getProperty("WIFI_NAME", "COME_WITH_ME");
    }

    public static int getCorePoolSize() {
        int poolSize = Integer.parseInt(prop.getProperty("CORE_POOL_SIZE", "100"));
        return poolSize;
    }

    public static int getMaxPoolSize() {
        int size = Integer.parseInt(prop.getProperty("MAX_POOL_SIZE", "200"));
        return size;
    }

    public static int getQueueSize() {
        int size = Integer.parseInt(prop.getProperty("QUEUE_SIZE", "20000"));
        return size;
    }

    public static int getQueueTimeOut() {
        int poolSize = Integer.parseInt(prop.getProperty("QUEUE_TIME_OUT", "20000"));
        return poolSize;
    }

    public static int getAsyncContextTimeou() {
        int timeout = Integer.parseInt(prop.getProperty("ASYNC_TIMEOUT", "20000"));
        return timeout;
    }

    public static boolean isAutoConfigurationGateway() {
        String isDisable = prop.getProperty("AUTO_CONFIG_GATEWAY", "true");
        return Boolean.valueOf(isDisable);
    }
}
