/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gonzalo Torres
 */
public abstract class ConfigUtils {

    private static Properties p = new Properties();
    private static ResourceBundle rb;
    private static final String pathConfigInit = "configinit.properties";
    private InputStream inputStream;
    private File f;

    static {
	try {
	    rb = ResourceBundle.getBundle("com.bluu.hdm.web.props.configinit");

	} catch (Throwable e) {
	    System.out.println(e.getMessage());
	}
    }

    public static String getProperty(String key) {
	return rb.getString(key);
    }

    public static void setProperties() {

    }

    private void loadProperties(String propertiesName) {

	if (p != null) {
	    return;
	}

	// Properties properties = null;
	InputStream inputStream = null;

	inputStream = this.getClass().getResourceAsStream(propertiesName);
	if (inputStream == null) {
	    try {
		throw new Exception(p + "something");
	    } catch (Exception ex) {
		Logger.getLogger(ConfigUtils.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}

	p = new Properties();
	try {
	    p.load(inputStream);
	} catch (IOException e) {
	    try {
		throw new Exception(e);
	    } catch (Exception ex) {
		Logger.getLogger(ConfigUtils.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
    }
}
