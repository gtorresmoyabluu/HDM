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
public class HostPropertyPK {

    public Integer parentId;
    public String name;

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public HostPropertyPK() {
    }

    public HostPropertyPK(Integer parentId, String name) {
        this.parentId = parentId;
        this.name = name;
    }

    @Override
    public boolean equals(java.lang.Object otherOb) {

        if (this == otherOb) {
            return true;
        }
        if (!(otherOb instanceof HostPropertyPK)) {
            return false;
        }
        HostPropertyPK other = (HostPropertyPK) otherOb;
        return ((parentId == null ? other.parentId == null : parentId == other.parentId)
                && (name == null ? other.name == null : name.equals(other.name)));
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return ((parentId == null ? 0 : parentId.hashCode())
                ^ (name == null ? 0 : name.hashCode()));
    }
}
