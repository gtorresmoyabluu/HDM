/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.beans;

import com.bluu.hdm.cwmp.pk.DeviceProfile2SoftwarePK;
import java.util.Collection;
import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

/**
 *
 * @author Gonzalo Torres
 */
public interface DeviceProfile2SoftwareLocalHome extends EJBLocalHome {

    DeviceProfile2SoftwareLocal findByPrimaryKey(DeviceProfile2SoftwarePK key) throws FinderException;

    DeviceProfile2SoftwareLocal create(String profileName, Integer hwid, String version) throws CreateException;

    DeviceProfile2SoftwareLocal findByProfileNameAndHwid(String profileName, Integer hwid) throws FinderException;

    Collection findByProfile(String name) throws FinderException;
}
