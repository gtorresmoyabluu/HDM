/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.beans;

import com.bluu.hdm.cwmp.message.Message;
import javax.ejb.EJBLocalObject;
import javax.jms.JMSException;

/**
 *
 * @author Gonzalo Torres
 */
public interface CPELocal extends EJBLocalObject, CPELocalBusiness {

    Message WaitJmsReply(String filter, long timeout) throws JMSException;
    
}
