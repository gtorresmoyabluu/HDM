/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.conf;

import com.bluu.hdm.cwmp.beans.Host2ServiceLocal;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Gonzalo Torres
 */
public class ServiceInstanceList {

    private class svcentry {

        public Integer svcid;
        public Integer instance;
        public Integer parentsvcid;
        public Integer parentinstance;

        public svcentry(Integer svcid, Integer instance, Integer parentsvcid, Integer parentinstance) {
            this.instance = instance;
            this.parentinstance = parentinstance;
            this.parentsvcid = parentsvcid;
            this.svcid = svcid;
        }
    }
    private final static String INDEX = "{i}";
    ArrayList<svcentry> svcs;

    public ServiceInstanceList(Collection<Host2ServiceLocal> svcicollection) {
        svcs = new ArrayList<svcentry>(svcicollection.size());
        for (Host2ServiceLocal i : svcicollection) {
            svcs.add(new svcentry(i.getServiceid(), i.getInstance(), i.getParentServiceId(), i.getParentServiceInstance()));
        }
    }

    private svcentry find(Host2ServiceLocal svcinstance) {
        return find(svcinstance.getServiceid(), svcinstance.getInstance());
    }

    private svcentry find(Integer svcid, Integer instance) {
        for (svcentry e : svcs) {
            if (e.svcid.equals(svcid) && e.instance.equals(instance)) {
                return e;
            }
        }
        return null;
    }

    public Integer[] getInstancesArray(Host2ServiceLocal svcinstance) {
        ArrayList<Integer> is = new ArrayList<Integer>();

        svcentry e = find(svcinstance);

        while (e != null) {
            is.add(0, e.instance);
            if (e.parentinstance == null) {
                e = null;
            } else {
                e = find(e.parentsvcid, e.parentinstance);
            }
        }

        return is.toArray(new Integer[is.size()]);
    }

    public String mapName(String name, Integer[] ix) {
        StringBuffer b = new StringBuffer(name);
        int start = 0;
        for (int ii = 0; ii < ix.length; ii++) {
            int i = b.indexOf(INDEX, start);
            if (i == -1) {
                break;
            }
            b.replace(i, i + INDEX.length(), ix[ii].toString());
            start = i;
        }
        return b.toString();
    }
}
