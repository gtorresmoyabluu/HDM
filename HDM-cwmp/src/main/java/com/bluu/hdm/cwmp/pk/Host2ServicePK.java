/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.pk;

/**
 *
 * @author Gonzalo Torres
 */
public class Host2ServicePK {

    public Integer hostid;
    public Integer serviceid;
    public Integer instance;

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public Host2ServicePK() {
    }

    public Host2ServicePK(Integer hostId, Integer serviceId, Integer instance) {
        this.hostid = hostId;
        this.serviceid = serviceId;
        this.instance = instance;
    }

    @Override
    public boolean equals(java.lang.Object otherOb) {

        if (this == otherOb) {
            return true;
        }
        if (!(otherOb instanceof Host2ServicePK)) {
            return false;
        }
        Host2ServicePK other = (Host2ServicePK) otherOb;
        return ((hostid == null ? other.hostid == null : hostid == other.hostid)
                && (serviceid == null ? other.serviceid == null : serviceid == other.serviceid)
                && (instance == null ? other.instance == null : instance == other.instance));
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return ((hostid == null ? 0 : hostid.hashCode())
                ^ (serviceid == null ? 0 : serviceid.hashCode())
                ^ (instance == null ? 0 : instance.hashCode()));
    }
}
