/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.conf;

import com.bluu.hdm.cwmp.beans.ATMErrorsStatsLocalHome;
import com.bluu.hdm.cwmp.beans.BackupLocalHome;
import com.bluu.hdm.cwmp.beans.CPELocal;
import com.bluu.hdm.cwmp.beans.CPELocalHome;
import com.bluu.hdm.cwmp.beans.ConfigurationLocalHome;
import com.bluu.hdm.cwmp.beans.DSLStatsLocalHome;
import com.bluu.hdm.cwmp.beans.DataModelLocalHome;
import com.bluu.hdm.cwmp.beans.DeviceProfile2SoftwareLocalHome;
import com.bluu.hdm.cwmp.beans.DeviceProfileLocalHome;
import com.bluu.hdm.cwmp.beans.HardwareModelLocal;
import com.bluu.hdm.cwmp.beans.HardwareModelLocalHome;
import com.bluu.hdm.cwmp.beans.Host2ServiceLocalHome;
import com.bluu.hdm.cwmp.beans.HostPropertyLocalHome;
import com.bluu.hdm.cwmp.beans.HostsLocalHome;
import com.bluu.hdm.cwmp.beans.OuiMapLocalHome;
import com.bluu.hdm.cwmp.beans.ProfilePropertyLocalHome;
import com.bluu.hdm.cwmp.beans.PropertyLocalHome;
import com.bluu.hdm.cwmp.beans.ScriptLocal;
import com.bluu.hdm.cwmp.beans.ScriptLocalHome;
import com.bluu.hdm.cwmp.beans.ServiceLocalHome;
import com.bluu.hdm.cwmp.beans.ServicePropertyLocalHome;
import com.bluu.hdm.cwmp.beans.SoftwareDetailLocalHome;
import com.bluu.hdm.cwmp.beans.SoftwareLocalHome;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;
import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.jms.JMSException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Gonzalo Torres
 */
public class Ejb {

    /**
     * Creates a new instance of Ejb
     */
    public Ejb() {
    }

    static public ConfigurationLocalHome lookupConfigurationBean() {
        try {
            Context c = new InitialContext();
            return (ConfigurationLocalHome) c.lookup("java:comp/env/ejb/ConfigurationBean");
        } catch (NamingException ne) {
            //Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    static public SoftwareLocalHome lookupSoftwareBean() {
        try {
            Context c = new InitialContext();
            SoftwareLocalHome rv = (SoftwareLocalHome) c.lookup("java:comp/env/ejb/SoftwareBean");
            return rv;
        } catch (NamingException ne) {
            //Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    static public SoftwareDetailLocalHome lookupSoftwareDetailBean() {
        try {
            Context c = new InitialContext();
            SoftwareDetailLocalHome rv = (SoftwareDetailLocalHome) c.lookup("java:comp/env/ejb/SoftwareDetailBean");
            return rv;
        } catch (NamingException ne) {
            //Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    static public HostsLocalHome lookupHostsBean() {
        try {
            Context c = new InitialContext();
            HostsLocalHome rv = (HostsLocalHome) c.lookup("java:comp/env/ejb/HostsBean");
            return rv;
        } catch (NamingException ne) {
            //Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    static public CPELocal lookupCPEBean() {
        try {
            Context c = new InitialContext();
            CPELocalHome rv = (CPELocalHome) c.lookup("java:comp/env/ejb/CPEBean");
            return rv.create();
        } catch (CreateException | JMSException | NamingException ne) {
            //Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    static public ScriptLocalHome lookupScriptBean() {
        try {
            Context c = new InitialContext();
            ScriptLocalHome rv = (ScriptLocalHome) c.lookup("java:comp/env/ejb/ScriptBean");
            return rv;
        } catch (NamingException ne) {
            //java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    static public HardwareModelLocalHome lookupHardwareModelBean() {
        try {
            Context c = new InitialContext();
            HardwareModelLocalHome rv = (HardwareModelLocalHome) c.lookup("java:comp/env/ejb/HardwareModelBean");
            return rv;
        } catch (NamingException ne) {
            //java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    static public DSLStatsLocalHome lookupDSLStatsBean() {
        try {
            Context c = new InitialContext();
            DSLStatsLocalHome rv = (DSLStatsLocalHome) c.lookup("java:comp/env/ejb/DSLStatsBean");
            return rv;
        } catch (NamingException ne) {
            //java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    static public ATMErrorsStatsLocalHome lookupATMErrorsStatsBean() {
        try {
            Context c = new InitialContext();
            ATMErrorsStatsLocalHome rv = (ATMErrorsStatsLocalHome) c.lookup("java:comp/env/ejb/ATMErrorsStatsBean");
            return rv;
        } catch (NamingException ne) {
            //java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    static public DeviceProfileLocalHome lookupDeviceProfileBean() {
        try {
            Context c = new InitialContext();
            DeviceProfileLocalHome rv = (DeviceProfileLocalHome) c.lookup("java:comp/env/ejb/DeviceProfileBean");
            return rv;
        } catch (NamingException ne) {
            //java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    static public PropertyLocalHome lookupPropertyBean() {
        try {
            Context c = new InitialContext();
            PropertyLocalHome rv = (PropertyLocalHome) c.lookup("java:comp/env/ejb/PropertyBean");
            return rv;
        } catch (NamingException ne) {
            //java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    static public DataModelLocalHome lookupDataModelBean() {
        try {
            Context c = new InitialContext();
            DataModelLocalHome rv = (DataModelLocalHome) c.lookup("java:comp/env/ejb/DataModelBean");
            return rv;
        } catch (NamingException ne) {
            //java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    static public BackupLocalHome lookupBackupBean() {
        try {
            Context c = new InitialContext();
            BackupLocalHome rv = (BackupLocalHome) c.lookup("java:comp/env/ejb/BackupBean");
            return rv;
        } catch (NamingException ne) {
            //java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    static public DeviceProfile2SoftwareLocalHome lookupDeviceProfile2SoftwareBean() {
        try {
            Context c = new InitialContext();
            DeviceProfile2SoftwareLocalHome rv = (DeviceProfile2SoftwareLocalHome) c.lookup("java:comp/env/ejb/DeviceProfile2SoftwareBean");
            return rv;
        } catch (NamingException ne) {
            //java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    static public ProfilePropertyLocalHome lookupProfilePropertyBean() {
        try {
            Context c = new InitialContext();
            ProfilePropertyLocalHome rv = (ProfilePropertyLocalHome) c.lookup("java:comp/env/ejb/ProfilePropertyBean");
            return rv;
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        }
    }

    static public ServicePropertyLocalHome lookupServicePropertyBean() {
        try {
            Context c = new InitialContext();
            ServicePropertyLocalHome rv = (ServicePropertyLocalHome) c.lookup("java:comp/env/ejb/ServicePropertyBean");
            return rv;
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        }
    }

    static public HostPropertyLocalHome lookupHostPropertyBean() {
        try {
            Context c = new InitialContext();
            HostPropertyLocalHome rv = (HostPropertyLocalHome) c.lookup("java:comp/env/ejb/HostPropertyBean");
            return rv;
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        }
    }

    static public Host2ServiceLocalHome lookupHost2ServiceBean() {
        try {
            Context c = new InitialContext();
            Host2ServiceLocalHome rv = (Host2ServiceLocalHome) c.lookup("java:comp/env/ejb/Host2ServiceBean");
            return rv;
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        }
    }

    static public ServiceLocalHome lookupServiceBean() {
        try {
            Context c = new InitialContext();
            ServiceLocalHome rv = (ServiceLocalHome) c.lookup("java:comp/env/ejb/ServiceBean");
            return rv;
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        }
    }

    static public ScriptLocal lookupScriptBean(String name) throws FinderException {
        return lookupScriptBean().findByPrimaryKey(name);
    }

    static public Map<Integer, HardwareModelLocal> getHardwareModelMap() {
        Hashtable<Integer, HardwareModelLocal> m = new Hashtable<Integer, HardwareModelLocal>();
        HardwareModelLocalHome hh = lookupHardwareModelBean();
        try {
            Collection<HardwareModelLocal> i = hh.findAll();
            for (HardwareModelLocal h : i) {
                m.put((Integer) h.getId(), h);
            }
        } catch (FinderException ex) {
        }

        return m;
    }

    static public OuiMapLocalHome lookupOuiMapBean() {
        try {
            Context c = new InitialContext();
            OuiMapLocalHome rv = (OuiMapLocalHome) c.lookup("java:comp/env/ejb/OuiMapBean");
            return rv;
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        }
    }
}
