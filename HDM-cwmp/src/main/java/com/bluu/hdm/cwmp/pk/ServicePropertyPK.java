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
public class ServicePropertyPK {

    public Integer serviceid;
    public String name;

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public ServicePropertyPK() {
    }

    public ServicePropertyPK(Integer parentId, String name) {
        this.serviceid = parentId;
        this.name = name;
    }

    @Override
    public boolean equals(java.lang.Object otherOb) {

        if (this == otherOb) {
            return true;
        }
        if (!(otherOb instanceof ServicePropertyPK)) {
            return false;
        }
        ServicePropertyPK other = (ServicePropertyPK) otherOb;
        return ((serviceid == null ? other.serviceid == null : serviceid == other.serviceid)
                && (name == null ? other.name == null : name.equals(other.name)));
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return ((serviceid == null ? 0 : serviceid.hashCode())
                ^ (name == null ? 0 : name.hashCode()));
    }
}
