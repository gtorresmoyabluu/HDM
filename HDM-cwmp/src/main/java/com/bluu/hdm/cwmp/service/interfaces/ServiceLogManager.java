/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.service.interfaces;

import com.bluu.hdm.cwmp.entity.ServiceLog;
import java.util.List;

/**
 *
 * @author Gonzalo Torres
 */
public interface ServiceLogManager extends GenericManager<ServiceLog, Long> {

    public List<ServiceLog> searchLogBySerial(String serialNumber);
}
