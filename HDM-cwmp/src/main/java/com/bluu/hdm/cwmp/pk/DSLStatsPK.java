/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.pk;

import java.sql.Timestamp;

/**
 *
 * @author Gonzalo Torres
 */
public class DSLStatsPK {

    public Integer hostid;
    public Timestamp time;

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public DSLStatsPK() {
    }

    public DSLStatsPK(Integer hostid, Timestamp time) {
        this.hostid = hostid;
        this.time = time;
    }

    @Override
    public boolean equals(Object otherOb) {

        if (this == otherOb) {
            return true;
        }
        if (!(otherOb instanceof DSLStatsPK)) {
            return false;
        }
        DSLStatsPK other = (DSLStatsPK) otherOb;
        return ((hostid == null ? other.hostid == null : hostid == other.hostid)
                && (time == null ? other.time == null : time.equals(other.time)));
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return ((hostid == null ? 0 : hostid.hashCode())
                ^ (time == null ? 0 : time.hashCode()));
    }
}
