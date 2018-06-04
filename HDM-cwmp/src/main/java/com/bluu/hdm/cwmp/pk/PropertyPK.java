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
public class PropertyPK {

    public Integer parentId;
    public Integer type;
    public String name;

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public PropertyPK() {
    }

    public PropertyPK(Integer parentId, Integer type, String name) {
        this.parentId = parentId;
        this.type = type;
        this.name = name;
    }

    @Override
    public boolean equals(java.lang.Object otherOb) {

        if (this == otherOb) {
            return true;
        }
        if (!(otherOb instanceof PropertyPK)) {
            return false;
        }
        PropertyPK other = (PropertyPK) otherOb;
        return ((parentId == null ? other.parentId == null : parentId == other.parentId)
                && (type == null ? other.type == null : type == other.type)
                && (name == null ? other.name == null : name.equals(other.name)));
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return ((parentId == null ? 0 : parentId.hashCode())
                ^ (type == null ? 0 : type.hashCode())
                ^ (name == null ? 0 : name.hashCode()));
    }
}
