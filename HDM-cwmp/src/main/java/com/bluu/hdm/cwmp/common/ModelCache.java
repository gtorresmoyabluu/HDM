/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.common;

import com.bluu.hdm.cwmp.entity.DeviceModel;
import com.bluu.hdm.cwmp.service.interfaces.DeviceModelManager;
import com.bluu.hdm.cwmp.utils.BeanUtils;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Gonzalo Torres
 */
public class ModelCache {

    public static Map<String, DeviceModel> mapCacheModel = new HashMap<String, DeviceModel>();
    public static final DeviceModelManager deviceModelManager = BeanUtils.getInstance().getBean("deviceModelManager", DeviceModelManager.class);
    // load cache
}
