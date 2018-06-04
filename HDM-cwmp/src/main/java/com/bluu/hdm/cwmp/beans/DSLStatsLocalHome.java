/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.beans;

import com.bluu.hdm.cwmp.pk.DSLStatsPK;
import java.sql.Timestamp;
import java.util.Collection;
import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

/**
 *
 * @author Gonzalo Torres
 */
public interface DSLStatsLocalHome extends EJBLocalHome {

    DSLStatsLocal findByPrimaryKey(DSLStatsPK key) throws FinderException;

    DSLStatsLocal create(Integer hostid, Timestamp time) throws CreateException;

    DSLStatsLocal create(Integer hostid, Timestamp time,
            Integer DownstreamAttenuation,
            Integer DownstreamCurrRate,
            Integer DownstreamMaxRate,
            Integer DownstreamNoiseMargin,
            Integer DownstreamPower,
            Integer UpstreamAttenuation,
            Integer UpstreamCurrRate,
            Integer UpstreamMaxRate,
            Integer UpstreamNoiseMargin,
            Integer UpstreamPower,
            String Status,
            String ModulationType) throws CreateException;

//    Collection findByCpeAndTime(Integer cpeid, Timestamp tmfrom, Timestamp tmto) throws FinderException;
    Collection<DSLStatsLocal> findByCpeAndTime2(Integer cpeid, Timestamp timeFrom, Timestamp timeTo) throws FinderException;

    DSLStatsLocal findByCpeAndTime(Integer cpeid, Timestamp time) throws FinderException;

    Collection<DSLStatsLocal> findByTimeBeforeAndHost(Integer hostid, Timestamp tm) throws FinderException;
}