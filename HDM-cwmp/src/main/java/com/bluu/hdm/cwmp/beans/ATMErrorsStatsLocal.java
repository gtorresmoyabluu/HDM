/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.beans;

import java.sql.Timestamp;
import javax.ejb.EJBLocalObject;

/**
 *
 * @author Gonzalo Torres
 */
public interface ATMErrorsStatsLocal extends EJBLocalObject {

    public Timestamp getTime();

    public Integer getHostid();

    public int getType();

    Timestamp getIntervalStart();

    void setIntervalStart(Timestamp intervalStart);
    static final int TYPE_TOTAL = 1;
    static final int TYPE_SHOWTIME = 2;
    static final int TYPE_CURRENTDAY = 3;
    static final int TYPE_QUARTERHOUR = 4;
    static final int TYPE_LASTSHOWTIME = 5;

    Long getATUCCRCErrors();

    void setATUCCRCErrors(Long ATUCCRCErrors);

    Long getATUCFECErrors();

    void setATUCFECErrors(Long ATUCFECErrors);

    Long getATUCHECErrors();

    void setATUCHECErrors(Long ATUCHECErrors);

    Long getCellDelin();

    void setCellDelin(Long CellDelin);

    Long getCRCErrors();

    void setCRCErrors(Long CRCErrors);

    Long getErroredSecs();

    void setErroredSecs(Long ErroredSecs);

    Long getFECErrors();

    void setFECErrors(Long FECErrors);

    Long getHECErrors();

    void setHECErrors(Long HECErrors);

    Long getInitErrors();

    void setInitErrors(Long InitErrors);

    Long getInitTimeouts();

    void setInitTimeouts(Long InitTimeouts);

    Long getLinkRetrain();

    void setLinkRetrain(Long LinkRetrain);

    Long getLossOfFraming();

    void setLossOfFraming(Long LossOfFraming);

    Long getReceiveBlocks();

    void setReceiveBlocks(Long ReceiveBlocks);

    Long getSeverelyErroredSecs();

    void setSeverelyErroredSecs(Long SeverelyErroredSecs);

    Long getTransmitBlocks();

    void setTransmitBlocks(Long TransmitBlocks);

    Long getLossOfPower();

    void setLossOfPower(Long LossOfPower);

    Long getLossOfSignal();

    void setLossOfSignal(Long LossOfSignal);
}
