/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.beans;

import com.bluu.hdm.cwmp.pk.ProfilePropertyPK;
import java.util.Collection;
import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

/**
 *
 * @author Gonzalo Torres
 */
public interface ProfilePropertyLocalHome extends EJBLocalHome {

    ProfilePropertyLocal findByPrimaryKey(ProfilePropertyPK key) throws FinderException;

    ProfilePropertyLocal create(String profile, String name, String value) throws CreateException;

    Collection<ProfilePropertyLocal> findByProfile(String name) throws FinderException;
}
