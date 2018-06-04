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
public class BackupPK {

    public Integer hostid;
    public Integer type;
    public Timestamp time;

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public BackupPK() {
    }

    public BackupPK(Integer hostid, Timestamp time, Integer type) {
        this.hostid = hostid;
        this.time = time;
        this.type = type;
    }

    @Override
    public boolean equals(Object otherOb) {

        if (this == otherOb) {
            return true;
        }
        if (!(otherOb instanceof BackupPK)) {
            return false;
        }
        BackupPK other = (BackupPK) otherOb;
        return ((hostid == null ? other.hostid == null : hostid == other.hostid)
                && (type == null ? other.type == null : type == other.type)
                && (time == null ? other.time == null : time.equals(other.time)));
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return ((hostid == null ? 0 : hostid.hashCode())
                ^ (time == null ? 0 : time.hashCode())
                ^ (type == null ? 0 : type.hashCode()));
    }
}
