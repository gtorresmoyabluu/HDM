/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.interfaces;

import com.bluu.hdm.cwmp.beans.HostsLocal;
import com.bluu.hdm.cwmp.message.Message;
import java.util.Properties;

/**
 *
 * @author Gonzalo Torres
 */
public interface ICpe {

    public Message Call(HostsLocal host, Message call, long timeout);

    public void BackupCWMPTree();

    public void SaveDSLStats();

    public void SaveATMErrorsStats();

    public void SyncParameterValues(Properties vars);
}
