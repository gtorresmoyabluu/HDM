/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp;

import com.bluu.hdm.cwmp.beans.DeviceProfileLocal;
import com.bluu.hdm.cwmp.beans.PropertyLocal;
import com.bluu.hdm.cwmp.beans.PropertyLocalHome;
import com.bluu.hdm.cwmp.conf.Ejb;
import com.bluu.hdm.cwmp.conf.Subnet;
import com.bluu.hdm.cwmp.conf.TransferItem;
import com.bluu.hdm.cwmp.conf.TransferItemMgr;
import com.bluu.hdm.cwmp.datamodel.Parser;
import com.bluu.hdm.cwmp.datamodel.StreamProvider;
import com.bluu.hdm.cwmp.pk.PropertyPK;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Gonzalo Torres
 */
public class Application {

    private static final String PROPERTY_OVERRIDESERVERNAME = "overrideServerName";
    private static final String PROPERTY_AUTOCREATECPE = "autoCreateCpe";
    private static final String PROPERTY_FIRMWAREPATH = "firmwarePath";
    private static final String PROPERTY_STUNPORT = "STUNPort";
    private static final String PROPERTY_NONATNET = "NoNATNet";
    private static Application app;
    private TransferItemMgr transferItemMgr = new TransferItemMgr();
    private String overrideServerName = null;
    private String firmwarePath = null;
    private int STUNPort = 0;
    private boolean autoCreateCpe = true;
    private ArrayList<Subnet> NoNATNet = new ArrayList<>();
    private String NoNATNetString = "";

    private void createDefaultProfile() {
        DeviceProfileLocal def;
        try {
            def = Ejb.lookupDeviceProfileBean().findByPrimaryKey("Default");
            if (def.getSaveParamValuesOnBoot() == null) {
                def.setSavestats(true);
                def.setSaveParamValuesOnChange(true);
                def.setSaveParamValuesOnBoot(true);
                def.setSaveParamValuesInterval(7);
            }
        } catch (FinderException ex) {
            try {
                def = Ejb.lookupDeviceProfileBean().create("Default");
                def.setDayskeepstats(7);
                def.setInforminterval(1800);
                def.setSavestats(true);
                def.setSaveParamValuesOnChange(true);
                def.setSaveParamValuesOnBoot(true);
                def.setSaveParamValuesInterval(7);
            } catch (CreateException ex1) {
                Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    public static void init(ServletContextEvent ctx) {
        app = new Application();
        app.createDefaultProfile();
        app.overrideServerName = app.getProperty(PROPERTY_OVERRIDESERVERNAME, null);
        app.autoCreateCpe = app.getPropertyBool(PROPERTY_AUTOCREATECPE, "true");
        app.firmwarePath = app.getProperty(PROPERTY_FIRMWAREPATH, "c:/firmware");
        app.STUNPort = app.getPropertyInt(PROPERTY_STUNPORT, "5060");
        try {
            setNoNATNet(app.getProperty(PROPERTY_NONATNET, ""));
        } catch (UnknownHostException | CreateException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.WARNING, null, ex);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(ctx.getServletContext().getResourceAsStream("/WEB-INF/datamodel/loadlist")));
        String line;
        Parser.setDir("/WEB-INF/datamodel/");
        final ServletContext c = ctx.getServletContext();
        Parser.setStreamProvider(new StreamProvider() {

            @Override
            public InputStream getStream(String name) {
                return c.getResourceAsStream(name);
            }
        });
        try {
            while ((line = reader.readLine()) != null) {
                Parser.Parse(line);
            }
        } catch (IOException | ParserConfigurationException | SAXException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void _destroy(ServletContextEvent ctx) {
    }

    public static void destroy(ServletContextEvent ctx) {
        app._destroy(ctx);
        System.out.println("Application.contextDestroyed " + ctx.getServletContext().getContextPath());
    }

    public static Application getApplication() {
        return app;
    }

    public static TransferItem getNew(int id, int type) {
        return TransferItemMgr.getNew(id, type);
    }

    public static TransferItem find(String user) {
        return TransferItemMgr.find(user);
    }

    public static String getOverrideServerName() {
        return app.overrideServerName;
    }

    public static String getFirmwarePath() {
        String fwbase = app.firmwarePath;
        if (fwbase == null) {
            fwbase = "c:/firmware/";
        }
        String osname = (String) System.getProperty("os.name");
        if (!osname.startsWith("Windows") && fwbase.length() >= 2 && fwbase.charAt(1) == ':') {
            fwbase = fwbase.substring(2);
        }
        if (!fwbase.endsWith(File.separator)) {
            fwbase += File.separator;
        }
//        System.out.println ("fwbase="+fwbase);
        return fwbase;
    }

    public static void setFirmwarePath(String firmwarePath) throws CreateException {
        app.firmwarePath = firmwarePath;
        app.setProperty(PROPERTY_FIRMWAREPATH, firmwarePath);
    }

    private boolean getPropertyBool(String name, String defvalue) {
        String p = getProperty(name, defvalue);
        return Boolean.parseBoolean(p);
    }

    private int getPropertyInt(String name, String defvalue) {
        String p = getProperty(name, defvalue);
        try {
            if (p == null || p.equals("")) {
                return Integer.parseInt(defvalue);
            }
            return Integer.parseInt(p);
        } catch (Exception e) {
            return 0;
        }
    }

    public static void setSTUNport(int STUNPort) throws CreateException {
        app.STUNPort = STUNPort;
        app.setProperty(PROPERTY_STUNPORT, Integer.toString(STUNPort));
    }

    public static int getSTUNport() {
        return app.STUNPort;
    }

    private String getProperty(String name, String defvalue) {
        PropertyLocalHome plh = Ejb.lookupPropertyBean();
        try {
            PropertyLocal pl = plh.findByPrimaryKey(new PropertyPK(0, PropertyLocal.TYPE_APPLICATION, name));
            return pl.getValue();
        } catch (FinderException ex) {
            return defvalue;
        }
    }

    private void setProperty(String name, String value) throws CreateException {
        PropertyLocalHome plh = Ejb.lookupPropertyBean();
        PropertyLocal pl = null;
        try {
            pl = plh.findByPrimaryKey(new PropertyPK(0, PropertyLocal.TYPE_APPLICATION, name));
            pl.setValue(value);
        } catch (FinderException ex) {
            plh.create(0, PropertyLocal.TYPE_APPLICATION, name, value);
        }
    }

    public static void setOverrideServerName(String overrideServerName) throws CreateException {
        app.overrideServerName = overrideServerName;
        app.setProperty(PROPERTY_OVERRIDESERVERNAME, overrideServerName);
    }

    public static String getNoNATNet() {
        return app.NoNATNetString;
    }

    public static void setNoNATNet(String NoNATNet) throws CreateException, UnknownHostException {
        ArrayList<Subnet> a = new ArrayList<>();
        if (!NoNATNet.equals("")) {
            String[] ns = NoNATNet.split(" ");
            for (String n : ns) {
                Subnet s = new Subnet(n);
                a.add(s);
            }
        }
        app.NoNATNet = a;
        app.NoNATNetString = NoNATNet;
        app.setProperty(PROPERTY_NONATNET, NoNATNet);
    }

    public static boolean IsNoNATNetwork(String addr) throws UnknownHostException {
        return Application.IsNoNATNetwork(InetAddress.getByName(addr));
    }

    public static boolean IsNoNATNetwork(InetAddress addr) {
        for (Subnet s : app.NoNATNet) {
            if (s.isInSubnet(addr)) {
                return true;
            }
        }
        return false;
    }

    public static boolean getAutoCreateCpe() {
//        System.out.println ("autoCreateCPE="+app.autoCreateCpe);
        return app.autoCreateCpe;
    }

    public static void setAutoCreateCpe(Boolean autoCreateCpe) throws CreateException {
        app.autoCreateCpe = autoCreateCpe;
        app.setProperty(PROPERTY_AUTOCREATECPE, autoCreateCpe.toString());
    }
}
