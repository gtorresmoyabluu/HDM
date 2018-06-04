/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.enums;

import com.bluu.hdm.web.pojo.Log;
import com.bluu.hdm.web.pojo.Message;
import com.bluu.hdm.web.pojo.administracion.Access;
import com.bluu.hdm.web.pojo.administracion.Category;
import com.bluu.hdm.web.pojo.administracion.Client;
import com.bluu.hdm.web.pojo.administracion.Configuration;
import com.bluu.hdm.web.pojo.administracion.License;
import com.bluu.hdm.web.pojo.administracion.Role;
import com.bluu.hdm.web.pojo.administracion.User;
import com.bluu.hdm.web.pojo.firmware.Firmware;
import com.bluu.hdm.web.pojo.inventory.Cpe;
import com.bluu.hdm.web.pojo.inventory.Device;
import com.bluu.hdm.web.pojo.inventory.Manufacturer;
import com.bluu.hdm.web.pojo.inventory.Model;
import com.bluu.hdm.web.pojo.inventory.StatusDevice;
import com.bluu.hdm.web.pojo.inventory.TemplateHDM;

/**
 *
 * @author Gonzalo Torres
 */
public enum ClassEnum {
    Message(Message.class),
    Access(Access.class),    
    Category(Category.class),
    Client(Client.class),
    Configuration(Configuration.class),
    License(License.class),
    Role(Role.class),
    User(User.class),
    Device(Device.class),
    Cpe(Cpe.class),
    Firmware(Firmware.class),
    Manufacturer(Manufacturer.class),
    Model(Model.class),
    StatusDevice(StatusDevice.class),
    TemplateHDM(TemplateHDM.class),
    Log(Log.class);

    Class<?> clazz;

    ClassEnum(Class<?> clazz) {
	this.clazz = clazz;
    }

    public Class<?> getClazz() {
	return clazz;
    }
}
