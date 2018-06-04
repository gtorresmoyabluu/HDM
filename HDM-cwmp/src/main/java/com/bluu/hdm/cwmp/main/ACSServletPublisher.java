/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.main;

import com.bluu.hdm.cwmp.service.ACSServlet;
import net.bytebuddy.build.EntryPoint;
import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
//import org.eclipse.jetty.server.Server;
//import org.eclipse.jetty.servlet.ServletContextHandler;

/**
 *
 * @author Gonzalo Torres
 */
public class ACSServletPublisher {

    protected static final Logger logger = Logger.getLogger(ACSServletPublisher.class.getSimpleName());
    protected static final StringBuilder sb = new StringBuilder();

    public static final String NMS_ACS_HOSTNAME = "nms_acs.hostname";
    public static final String NMS_ACS_PORT = "nms_acs.port";

    public static void main(String[] args) throws Exception {
        ACSServletPublisher.publishACS();
    }

    public static void publishACS() throws Exception {
        String getPort = System.getProperty(NMS_ACS_PORT, "8098");
        int port = Integer.parseInt(getPort);

        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        servletContextHandler.setContextPath("/");

        Server server = new Server(port);
        server.setHandler(servletContextHandler);

        ServletHolder jerseyServlet = servletContextHandler.addServlet(ACSServlet.class, "/*");
        jerseyServlet.setInitOrder(0);

        // Tells the Jersey Servlet which REST service/class to load.
        jerseyServlet.setInitParameter("jersey.config.server.provider.packages", "com.bluu.hdm.cwmp");

        try {
            server.start();
            server.join();
            sb.setLength(0);
            sb.append("ACS Servlet started, on PORT: ").append(port);
            logger.info(sb);
        } catch (Exception e) {
            logger.error("Error ACSServletPublisher", e);
        }
    }
}
