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
public class DeviceProfile2SoftwarePK {

    public Integer hwid;
    public String profileName;

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public DeviceProfile2SoftwarePK() {
    }

    public DeviceProfile2SoftwarePK(Integer hwid, String profilename) {
        this.hwid = hwid;
        this.profileName = profilename;
    }

    @Override
    public boolean equals(java.lang.Object otherOb) {

        if (this == otherOb) {
            return true;
        }
        if (!(otherOb instanceof DeviceProfile2SoftwarePK)) {
            return false;
        }
        DeviceProfile2SoftwarePK other = (DeviceProfile2SoftwarePK) otherOb;
        return ((hwid == null ? other.hwid == null : hwid == other.hwid)
                && (profileName == null ? other.profileName == null : profileName.equals(other.profileName)));
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return ((hwid == null ? 0 : hwid.hashCode())
                ^ (profileName == null ? 0 : profileName.hashCode()));
    }

    @Override
    public String toString() {
        return "hwid=" + hwid + " name=" + profileName;
    }
}
