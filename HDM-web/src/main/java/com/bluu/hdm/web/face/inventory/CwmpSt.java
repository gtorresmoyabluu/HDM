/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.face.inventory;

import com.bluu.hdm.web.pojo.inventory.cpe.CwmpTask;
import java.util.HashMap;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import org.apache.log4j.Logger;

/**
 *
 * @author Marco Valdés
 */
@Singleton
@LocalBean
public class CwmpSt {

    private final Logger logger = Logger.getLogger(CwmpSt.class);

    private final HashMap<String, CwmpTask> actUsr2TaskMap = new HashMap<>(), gpnUsr2TaskMap = new HashMap<>(), gpvUsr2TaskMap = new HashMap<>(),
            grmUsr2TaskMap = new HashMap<>(), proUsr2TaskMap = new HashMap<>(), rbtUsr2TaskMap = new HashMap<>(), spvUsr2TaskMap = new HashMap<>(),
            trbUsr2TaskMap = new HashMap<>(), tsiUsr2TaskMap = new HashMap<>(), wcfUsr2TaskMap = new HashMap<>(), wspUsr2TaskMap = new HashMap<>(),
            aobUsr2TaskMap = new HashMap<>(), dobUsr2TaskMap = new HashMap<>(), xmcUsr2TaskMap = new HashMap<>(), xmoUsr2TaskMap = new HashMap<>();

    public void deleteTimedOutObjects() {
        int dropAct = 0, dropAob = 0, dropDob = 0, dropGpm = 0, dropGpv = 0, dropGrm = 0, dropPro = 0, dropRbt = 0,
                dropTrb = 0, dropSpv = 0, dropTsi = 0, dropWcf = 0, dropWsp = 0, dropXmc = 0, dropXmo = 0;
        for (String usr : actUsr2TaskMap.keySet()) {
            CwmpTask task = actUsr2TaskMap.get(usr);
            if ((task != null) && task.isTimedout()) {
                actUsr2TaskMap.remove(usr);
                dropAct++;
            }
        }
        for (String usr : aobUsr2TaskMap.keySet()) {
            CwmpTask task = aobUsr2TaskMap.get(usr);
            if ((task != null) && task.isTimedout()) {
                aobUsr2TaskMap.remove(usr);
                dropAob++;
            }
        }
        for (String usr : dobUsr2TaskMap.keySet()) {
            CwmpTask task = dobUsr2TaskMap.get(usr);
            if ((task != null) && task.isTimedout()) {
                dobUsr2TaskMap.remove(usr);
                dropDob++;
            }
        }
        for (String usr : gpnUsr2TaskMap.keySet()) {
            CwmpTask task = gpnUsr2TaskMap.get(usr);
            if ((task != null) && task.isTimedout()) {
                gpnUsr2TaskMap.remove(usr);
                dropGpm++;
            }
        }
        for (String usr : gpvUsr2TaskMap.keySet()) {
            CwmpTask task = gpvUsr2TaskMap.get(usr);
            if ((task != null) && task.isTimedout()) {
                gpvUsr2TaskMap.remove(usr);
                dropGpv++;
            }
        }
        for (String usr : grmUsr2TaskMap.keySet()) {
            CwmpTask task = grmUsr2TaskMap.get(usr);
            if ((task != null) && task.isTimedout()) {
                grmUsr2TaskMap.remove(usr);
                dropGrm++;
            }
        }
        for (String usr : proUsr2TaskMap.keySet()) {
            CwmpTask task = proUsr2TaskMap.get(usr);
            if ((task != null) && task.isTimedout()) {
                proUsr2TaskMap.remove(usr);
                dropPro++;
            }
        }
        for (String usr : rbtUsr2TaskMap.keySet()) {
            CwmpTask task = rbtUsr2TaskMap.get(usr);
            if ((task != null) && task.isTimedout()) {
                rbtUsr2TaskMap.remove(usr);
                dropRbt++;
            }
        }
        for (String usr : spvUsr2TaskMap.keySet()) {
            CwmpTask task = spvUsr2TaskMap.get(usr);
            if ((task != null) && task.isTimedout()) {
                spvUsr2TaskMap.remove(usr);
                dropSpv++;
            }
        }
        for (String usr : trbUsr2TaskMap.keySet()) {
            CwmpTask task = trbUsr2TaskMap.get(usr);
            if ((task != null) && task.isTimedout()) {
                trbUsr2TaskMap.remove(usr);
                dropTrb++;
            }
        }
        for (String usr : tsiUsr2TaskMap.keySet()) {
            CwmpTask task = tsiUsr2TaskMap.get(usr);
            if ((task != null) && task.isTimedout()) {
                tsiUsr2TaskMap.remove(usr);
                dropTsi++;
            }
        }
        for (String usr : wcfUsr2TaskMap.keySet()) {
            CwmpTask task = wcfUsr2TaskMap.get(usr);
            if ((task != null) && task.isTimedout()) {
                wcfUsr2TaskMap.remove(usr);
                dropWcf++;
            }
        }
        for (String usr : wspUsr2TaskMap.keySet()) {
            CwmpTask task = wspUsr2TaskMap.get(usr);
            if ((task != null) && task.isTimedout()) {
                wspUsr2TaskMap.remove(usr);
                dropWsp++;
            }
        }
        for (String usr : xmcUsr2TaskMap.keySet()) {
            CwmpTask task = xmcUsr2TaskMap.get(usr);
            if ((task != null) && task.isTimedout()) {
                xmcUsr2TaskMap.remove(usr);
                dropXmc++;
            }
        }
        for (String usr : xmoUsr2TaskMap.keySet()) {
            CwmpTask task = xmoUsr2TaskMap.get(usr);
            if ((task != null) && task.isTimedout()) {
                xmoUsr2TaskMap.remove(usr);
                dropXmo++;
            }
        }
        if (dropAct + dropGpm + dropGpv + dropGrm + dropPro + dropRbt + dropTrb + dropTsi + dropWcf > 0) {
            logger.info("Limpieza de Tareas CWMP: " + dropAct + " act, " + dropAob + " aob, " + dropDob + " dob, " + dropGpm + " gpm, "
                    + dropGpv + " gpv, " + dropGrm + " grm, " + dropPro + " pro, " + dropRbt + " rbt, " + dropSpv + " spv," + dropTrb
                    + " trb, " + dropTsi + " tsi, " + dropWcf + " wcf, " + dropWsp + " wsp, " + dropXmc + " xmc, " + dropXmo + " xmo");
        }
    }

    public void dropActionTask(String usr) {
        actUsr2TaskMap.remove(usr);
    }

    public void dropAddObjectTask(String usr) {
        aobUsr2TaskMap.remove(usr);
    }

    public void dropDeleteObjectTask(String usr) {
        dobUsr2TaskMap.remove(usr);
    }

    public void dropGetParamNamesTask(String usr) {
        gpnUsr2TaskMap.remove(usr);
    }

    public void dropGetParamValuesTask(String usr) {
        gpvUsr2TaskMap.remove(usr);
    }

    public void dropGetRpcMethodsTask(String usr) {
        grmUsr2TaskMap.remove(usr);
    }

    public void dropGetWifiConfigTask(String usr) {
        wcfUsr2TaskMap.remove(usr);
    }

    public void dropGetWifiSpectrumTask(String usr) {
        wspUsr2TaskMap.remove(usr);
    }

    public void dropProvisioningTask(String usr) {
        proUsr2TaskMap.remove(usr);
    }

    public void dropRebootTask(String usr) {
        rbtUsr2TaskMap.remove(usr);
    }

    public void dropSetParamValuesTask(String usr) {
        spvUsr2TaskMap.remove(usr);
    }

    public void dropTestInfoTask(String usr) {
        tsiUsr2TaskMap.remove(usr);
    }

    public void dropTroubleshootTask(String usr) {
        trbUsr2TaskMap.remove(usr);
    }

    public void dropXmppCheckTask(String usr) {
        xmcUsr2TaskMap.remove(usr);
    }

    public void dropXmppObjectCreationTask(String usr) {
        xmoUsr2TaskMap.remove(usr);
    }

    public CwmpTask getActionTask(String usr) {
        CwmpTask task = actUsr2TaskMap.get(usr);
        if (task != null) {
            if (task.isTimedout()) {
                actUsr2TaskMap.remove(usr);
                task = null;
            }
        }
        return task;
    }

    public CwmpTask getAddObjectTask(String usr) {
        CwmpTask task = aobUsr2TaskMap.get(usr);
        if (task != null) {
            if (task.isTimedout()) {
                aobUsr2TaskMap.remove(usr);
                task = null;
            }
        }
        return task;
    }

    public CwmpTask getDeleteObjectTask(String usr) {
        CwmpTask task = dobUsr2TaskMap.get(usr);
        if (task != null) {
            if (task.isTimedout()) {
                dobUsr2TaskMap.remove(usr);
                task = null;
            }
        }
        return task;
    }

    public CwmpTask getGetParamNamesTask(String usr) {
        CwmpTask task = gpnUsr2TaskMap.get(usr);
        if (task != null) {
            if (task.isTimedout()) {
                gpnUsr2TaskMap.remove(usr);
                task = null;
            }
        }
        return task;
    }

    public CwmpTask getGetParamValuesTask(String usr) {
        CwmpTask task = gpvUsr2TaskMap.get(usr);
        if (task != null) {
            if (task.isTimedout()) {
                task = null;
                gpvUsr2TaskMap.remove(usr);
            }
        }
        return task;
    }

    public CwmpTask getGetRpcMethodsTask(String usr) {
        CwmpTask task = grmUsr2TaskMap.get(usr);
        if (task != null) {
            if (task.isTimedout()) {
                task = null;
                grmUsr2TaskMap.remove(usr);
            }
        }
        return task;
    }

    public CwmpTask getSetParamValuesTask(String usr) {
        CwmpTask task = spvUsr2TaskMap.get(usr);
        if (task != null) {
            if (task.isTimedout()) {
                task = null;
                spvUsr2TaskMap.remove(usr);
            }
        }
        return task;
    }

    public CwmpTask getGetWifiConfigTask(String usr) {
        CwmpTask task = wcfUsr2TaskMap.get(usr);
        if (task != null) {
            if (task.isTimedout()) {
                task = null;
                wcfUsr2TaskMap.remove(usr);
            }
        }
        return task;
    }

    public CwmpTask getGetWifiSpectrumTask(String usr) {
        CwmpTask task = wspUsr2TaskMap.get(usr);
        if (task != null) {
            if (task.isTimedout()) {
                task = null;
                wspUsr2TaskMap.remove(usr);
            }
        }
        return task;
    }

    public CwmpTask getProvisioningTask(String usr) {
        CwmpTask task = proUsr2TaskMap.get(usr);
        if (task != null) {
            if (task.isTimedout()) {
                task = null;
                proUsr2TaskMap.remove(usr);
            }
        }
        return task;
    }

    public CwmpTask getRebootTask(String usr) {
        CwmpTask task = rbtUsr2TaskMap.get(usr);
        if (task != null) {
            if (task.isTimedout()) {
                task = null;
                rbtUsr2TaskMap.remove(usr);
            }
        }
        return task;
    }

    public CwmpTask getTroubleshootTask(String usr) {
        CwmpTask task = trbUsr2TaskMap.get(usr);
        if (task != null) {
            if (task.isTimedout()) {
                task = null;
                trbUsr2TaskMap.remove(usr);
            }
        }
        return task;
    }

    public CwmpTask getTestInfoTask(String usr) {
        CwmpTask task = tsiUsr2TaskMap.get(usr);
        if (task != null) {
            if (task.isTimedout()) {
                task = null;
                tsiUsr2TaskMap.remove(usr);
            }
        }
        return task;
    }

    public CwmpTask getXmppCheckTask(String usr) {
        CwmpTask task = xmcUsr2TaskMap.get(usr);
        if (task != null) {
            if (task.isTimedout()) {
                task = null;
                xmcUsr2TaskMap.remove(usr);
            }
        }
        return task;
    }

    public CwmpTask getXmppObjectCreationTask(String usr) {
        CwmpTask task = xmoUsr2TaskMap.get(usr);
        if (task != null) {
            if (task.isTimedout()) {
                task = null;
                xmoUsr2TaskMap.remove(usr);
            }
        }
        return task;
    }

    public void setActionTask(String usr, CwmpTask task) {
        actUsr2TaskMap.put(usr, task);
    }

    public void setAddObjectTask(String usr, CwmpTask task) {
        aobUsr2TaskMap.put(usr, task);
    }

    public void setDeleteObjectTask(String usr, CwmpTask task) {
        dobUsr2TaskMap.put(usr, task);
    }

    public void setGetParamNamesTask(String usr, CwmpTask task) {
        gpnUsr2TaskMap.put(usr, task);
    }

    public void setGetParamValuesTask(String usr, CwmpTask task) {
        gpvUsr2TaskMap.put(usr, task);
    }

    public void setGetRpcMethodsTask(String usr) {
        grmUsr2TaskMap.put(usr, new CwmpTask());
    }

    public void setGetWifiConfigTask(String usr) {
        wcfUsr2TaskMap.put(usr, new CwmpTask());
    }

    public void setGetWifiSpectrumTask(String usr) {
        wspUsr2TaskMap.put(usr, new CwmpTask());
    }

    public void setProvisioningTask(String usr, CwmpTask task) {
        proUsr2TaskMap.put(usr, task);
    }

    public void setRebootTask(String usr) {
        rbtUsr2TaskMap.put(usr, new CwmpTask());
    }

    public void setSetParamValuesTask(String usr, CwmpTask task) {
        spvUsr2TaskMap.put(usr, task);
    }

    public void setTroubleshootTask(String usr) {
        trbUsr2TaskMap.put(usr, new CwmpTask());
    }

    public void setTestInfoTask(String usr, CwmpTask task) {
        tsiUsr2TaskMap.put(usr, task);
    }

    public void setXmppCheckTask(String usr, CwmpTask task) {
        xmcUsr2TaskMap.put(usr, task);
    }

    public void setXmppObjectCreationTask(String usr, CwmpTask task) {
        xmoUsr2TaskMap.put(usr, task);
    }
}
