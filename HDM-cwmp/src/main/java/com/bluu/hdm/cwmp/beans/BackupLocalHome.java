/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.beans;

import com.bluu.hdm.cwmp.pk.BackupPK;
import java.sql.Timestamp;
import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

/**
 *
 * @author Gonzalo Torres
 */
public interface BackupLocalHome extends EJBLocalHome {

    BackupLocal findByPrimaryKey(BackupPK key) throws FinderException;

    BackupLocal create(Integer hostid, Integer type, Timestamp time, byte[] cfg) throws CreateException;

    Timestamp getTimeOfLastBackup(Integer hostid) throws FinderException;
}
