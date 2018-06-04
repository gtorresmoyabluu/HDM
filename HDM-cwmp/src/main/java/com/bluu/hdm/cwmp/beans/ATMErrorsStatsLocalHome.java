/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.beans;

import com.bluu.hdm.cwmp.pk.ATMErrorsStatsPK;
import java.sql.Timestamp;
import java.util.Collection;
import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

/**
 *
 * @author Gonzalo Torres
 */
public interface ATMErrorsStatsLocalHome extends EJBLocalHome {

    ATMErrorsStatsLocal findByPrimaryKey(ATMErrorsStatsPK key) throws FinderException;

    ATMErrorsStatsLocal create(Integer hostid, Timestamp time, int type) throws CreateException;

    ATMErrorsStatsLocal create(Integer hostid, Timestamp time, int type,
            Timestamp intervalStart,
            Long ATUCCRCErrors, Long ATUCFECErrors, Long ATUCHECErrors, Long CellDelin,
            Long CRCErrors, Long FECErrors, Long HECErrors, Long ErroredSecs,
            Long InitErrors, Long InitTimeouts, Long LinkRetrain, Long LossOfFraming,
            Long ReceiveBlocks, Long SeverelyErroredSecs, Long TransmitBlocks,
            Long LossOfPower, Long LossOfSignal) throws CreateException;

    Collection<ATMErrorsStatsLocal> findByTimeBeforeAndHost(Integer hostid, Timestamp tm) throws FinderException;

    ATMErrorsStatsLocal findByHostAndTime(Integer host, Timestamp time) throws FinderException;
}