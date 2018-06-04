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
public class ConfigurationPK {

    public java.lang.Integer hwid;
    public java.lang.String name;

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public ConfigurationPK() {
    }

    public ConfigurationPK(Integer hwid, String name) {
        this.hwid = hwid;
        this.name = name;
    }

    @Override
    public boolean equals(java.lang.Object otherOb) {

        if (this == otherOb) {
            return true;
        }
        if (!(otherOb instanceof ConfigurationPK)) {
            return false;
        }
        ConfigurationPK other = (ConfigurationPK) otherOb;
        return ((hwid == null ? other.hwid == null : hwid == other.hwid)
                && (name == null ? other.name == null : name.equals(other.name)));
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return ((hwid == null ? 0 : hwid.hashCode())
                ^ (name == null ? 0 : name.hashCode()));
    }
}
