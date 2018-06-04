/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.utils;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 *
 * @author Gonzalo Torres
 */
public class BeanUtils {

    private ApplicationContext context;
    private static BeanUtils beanUtils;
    private final Logger logger = Logger.getLogger(BeanUtils.class);

    private BeanUtils() {
        try {
            String classPath = System.getProperty("cwmp.applicationContext", "classpath:applicationContext-cwmp.xml");
            context = new FileSystemXmlApplicationContext(classPath);
        } catch (BeansException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    public static BeanUtils getInstance() {
        if (beanUtils == null) {
            beanUtils = new BeanUtils();
        }
        return beanUtils;
    }

    public Object getBean(String name) {
        if (context != null) {
            return context.getBean(name);
        }
        return null;
    }

    public <T> T getBean(String name, Class<T> type) {
        if (context != null) {
            return context.getBean(name, type);
        }
        return null;
    }
}
