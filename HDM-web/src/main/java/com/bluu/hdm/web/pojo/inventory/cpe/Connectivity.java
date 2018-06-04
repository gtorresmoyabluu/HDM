/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.pojo.inventory.cpe;


import com.bluu.hdm.web.pojo.inventory.cpe.Usrpwd;
import com.bluu.hdm.web.enums.ConnectivityProtocolEnm;
import java.io.Serializable;
import java.util.ArrayList;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.core.Commit;

public class Connectivity implements Serializable {

    @Attribute(name = "protocol")
    private ConnectivityProtocolEnm protocol;
    @Attribute(name = "port", required = false)
    private int port = 0; // 22 SSH, 23 Telnet, 161 SNMP
    @Attribute(name = "snmpversion", required = false)
    private String snmpver = "2c";
    @Attribute(name = "connecttimeout", required = false)
    private int contimeout = 3000;
    @Attribute(name = "sessiontimeout", required = false)
    private int sestimeout = 30000;
    @Attribute(name = "requesttimeout", required = false)
    private int reqtimeout = 30000;
    @Attribute(name = "gettimeout", required = false)
    private int gettimeout = 5000;
    @Attribute(name = "settimeout", required = false)
    private int settimeout = 10000;
    @Attribute(name = "queuetimeout", required = false)
    private int quetimeout = 0;
    @Attribute(name = "ratelimit", required = false)
    private int ratelimit = 0;
    @Attribute(name = "rocommunity", required = false)
    private String rocommunity = "public";
    @Attribute(name = "rwcommunity", required = false)
    private String rwcommunity = "public";
    @ElementList(name = "usrpwds", required = false)
    private ArrayList<Usrpwd> usrPwdLst = new ArrayList<>();
    @ElementList(name = "endpoints", required = false)
    private ArrayList<String> endpointLst = new ArrayList<>();

    public Connectivity() {
    }

    @Commit
    public void build() {
        if ((port == 0) && (protocol != null)) {
            switch (protocol) {
                case ssh:
                    port = 22;
                    break;
                case telnet:
                    port = 23;
                    break;
                case snmp:
                    port = 161;
            }
        }
    }

    public int getConnectTimeout() {
        return contimeout;
    }

    public int getGetTimeout() {
        return gettimeout;
    }

    public int getPort() {
        return port;
    }

    public ConnectivityProtocolEnm getProtocol() {
        return protocol;
    }

    public int getQueueTimeout() {
        return quetimeout;
    }

    public int getRateLimit() {
        return ratelimit;
    }

    public int getRequestTimeout() {
        return reqtimeout;
    }

    public String getROCommunity() {
        return rocommunity;
    }

    public String getRWCommunity() {
        return rwcommunity;
    }

    public int getSessionTimeout() {
        return sestimeout;
    }

    public int getSetTimeout() {
        return settimeout;
    }

    public int getSnmpVersion() {
        switch (snmpver) {
            case "1":
                return 0;
            case "2c":
                return 1;
            default:
                return 3;
        }
    }

    public ArrayList<Usrpwd> getUserPwdList() {
        return usrPwdLst;
    }

    public ArrayList<String> getEndpointList() {
        return endpointLst;
    }

    @Override
    public String toString() {
        String result = "Con Protocol: " + protocol + ", Port: " + port + ", SNMPVer: " + snmpver + ", ROCommunity: "
                + rocommunity + ", RWCommunity: " + rwcommunity + ", ConnectTimeout: " + contimeout + ", RequestTimeout: "
                + reqtimeout + ", SessionTimeout: " + sestimeout + ", GetTimeout: " + gettimeout + ", SetTimeout: "
                + settimeout + ", Ratelimit: " + ratelimit + ", QueueTimeout: " + quetimeout;
        if (!usrPwdLst.isEmpty()) {
            result += "\nUsers/Pwds:";
            for (Usrpwd usp : usrPwdLst) {
                result += "\n- " + usp;
            }
        }
        if (!endpointLst.isEmpty()) {
            result += "\nEndpoints:";
            for (String ept : endpointLst) {
                result += "\n- " + ept;
            }
        }
        return result;
    }
}
