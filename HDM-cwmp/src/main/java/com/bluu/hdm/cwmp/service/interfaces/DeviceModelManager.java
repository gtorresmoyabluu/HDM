/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.service.interfaces;

import com.bluu.hdm.cwmp.entity.DeviceModel;

/**
 *
 * @author Gonzalo Torres
 */
public interface DeviceModelManager extends GenericManager<DeviceModel, Long>{
    public DeviceModel getModelByName(String modelName);
}
