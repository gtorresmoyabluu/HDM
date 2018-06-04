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
public class ATMErrorsStatsPK {

    public java.lang.Integer hostid;
    public Timestamp time;
    public int type;

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public ATMErrorsStatsPK() {
    }

    public ATMErrorsStatsPK(Integer hostid, Timestamp time, int type) {
        this.hostid = hostid;
        this.time = time;
        this.type = type;
    }

    @Override
    public boolean equals(java.lang.Object otherOb) {

        if (this == otherOb) {
            return true;
        }
        if (!(otherOb instanceof ATMErrorsStatsPK)) {
            return false;
        }
        ATMErrorsStatsPK other = (ATMErrorsStatsPK) otherOb;
        return ((hostid == null ? other.hostid == null : hostid == other.hostid)
                && (time == null ? other.time == null : time.equals(other.time))
                && (type == other.type));
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return ((hostid == null ? 0 : hostid.hashCode())
                ^ (time == null ? 0 : time.hashCode())
                ^ type);
    }

    @Override
    public String toString() {
        StringBuffer b = new StringBuffer();
        b.append("hostid=");
        b.append(hostid);
        b.append(" time=");
        b.append(time);
        b.append(" type=");
        b.append(type);
        return b.toString();
    }
}
