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
public class ProfilePropertyPK {

    public String profilename;
    public String name;

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public ProfilePropertyPK() {
    }

    public ProfilePropertyPK(String profilename, String name) {
        this.profilename = profilename;
        this.name = name;
    }

    @Override
    public boolean equals(java.lang.Object otherOb) {

        if (this == otherOb) {
            return true;
        }
        if (!(otherOb instanceof ProfilePropertyPK)) {
            return false;
        }
        ProfilePropertyPK other = (ProfilePropertyPK) otherOb;
        return ((profilename == null ? other.profilename == null : profilename.equals(other.profilename))
                && (name == null ? other.name == null : name.equals(other.name)));
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return ((profilename == null ? 0 : profilename.hashCode())
                ^ (name == null ? 0 : name.hashCode()));
    }
}
