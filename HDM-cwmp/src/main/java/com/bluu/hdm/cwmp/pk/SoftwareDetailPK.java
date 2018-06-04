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
public class SoftwareDetailPK {

    public java.lang.Integer hwid;
    public String version;

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public SoftwareDetailPK() {
    }

    public SoftwareDetailPK(Integer hwid, String version) {
        this.version = version;
        this.hwid = hwid;
    }

    @Override
    public boolean equals(java.lang.Object otherOb) {

        if (this == otherOb) {
            return true;
        }
        if (!(otherOb instanceof SoftwareDetailPK)) {
            return false;
        }
        SoftwareDetailPK other = (SoftwareDetailPK) otherOb;
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
