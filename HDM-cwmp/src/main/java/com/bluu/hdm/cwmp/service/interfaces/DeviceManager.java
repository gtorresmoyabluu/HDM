/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.service.interfaces;

import com.bluu.hdm.cwmp.entity.Device;
import java.awt.geom.Area;
import java.util.List;
import java.util.Map;
import javax.security.auth.Policy;

/**
 *
 * @author Gonzalo Torres
 */
public interface DeviceManager extends GenericManager<Device, Long> {

    public Map searchDevice(
            String deviceMAC,
            String deviceSerialNumber,
            String deviceStatus,
            String deviceFirmwareStatus,
            String deviceModel,
            String deviceFirmware,
            String deviceProvince,
            String deviceDistrict,
            List<Area> listArea,
            String ipAddress,
            String username,
            Long start,
            Long limit);

    public Device getDeviceBySerialNumber(String serialNumber);

    //longdq
    public List<Device> getDeviceOfPolicy(Policy p);

    public List<Device> getDeviceFailedOfPolicy(Policy p);

    public List<Device> getDeviceNotUpdate(Policy p);

    public List<Device> getDeviceByIdList(String id);

    public void insertDevice(Device device);
}
