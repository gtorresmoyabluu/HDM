/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.main;

import com.bluu.hdm.cwmp.service.ACSServiceImpl;
import javax.xml.ws.Endpoint;
import org.apache.log4j.Logger;

/**
 *
 * @author Gonzalo Torres
 */
public class ACSWebServicePublisher {

    public static final String WS_HOSTNAME_KEY = "nms_ws.hostname";
    public static final String WS_PORT_KEY = "nms_ws.port";
    public static final String WS_CONTEXT_KEY = "nms_ws.context";
    protected static final Logger logger = Logger.getLogger(ACSWebServicePublisher.class.getSimpleName());
    protected static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        publishWebservice();
    }

    public static void publishWebservice() {
        String hostName = System.getProperty(WS_HOSTNAME_KEY, "0.0.0.0");
        String port = System.getProperty(WS_PORT_KEY, "8099");
        String context = System.getProperty(WS_CONTEXT_KEY, "/acsService");
        StringBuilder sb = new StringBuilder();

        sb.append("http://")
                .append(hostName)
                .append(":")
                .append(port)
                .append(context);
        //.append("?wsdl");
        String serviceAddress = sb.toString();
//        Endpoint.publishWebservice(serviceAddress, new ComplexImpl());

        try {
            logger.info("Webservice Address: " + serviceAddress);
            Endpoint.publish(serviceAddress,
                    new ACSServiceImpl());
            logger.info("ACS Webservice started !");
        } catch (Exception ex) {
            logger.error("Can't start ACS Webservice: " + serviceAddress + ", error: " + ex.getMessage(), ex);
        }
    }
}
