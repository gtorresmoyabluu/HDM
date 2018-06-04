/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.pk;

import java.io.Serializable;

/**
 *
 * @author Gonzalo Torres
 */
public final class SoftwarePK implements Serializable {

    public java.lang.Integer hwid;
    public java.lang.String version;

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public SoftwarePK() {
    }

    public SoftwarePK(Integer hwid, String v) {
        this.hwid = hwid;
        version = v;
    }

    @Override
    public boolean equals(java.lang.Object otherOb) {

        if (this == otherOb) {
            return true;
        }
        if (!(otherOb instanceof SoftwarePK)) {
            return false;
        }
        SoftwarePK other = (SoftwarePK) otherOb;
        return ((hwid == null ? other.hwid == null : hwid == other.hwid)
                && (version == null ? other.version == null : version.equals(other.version)));
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return ((hwid == null ? 0 : hwid.hashCode())
                ^ (version == null ? 0 : version.hashCode()));
    }
}
