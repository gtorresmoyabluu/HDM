/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.enums;

import com.bluu.hdm.web.pojo.Access;
import com.bluu.hdm.web.pojo.Role;
import com.bluu.hdm.web.pojo.User;

/**
 *
 * @author Gonzalo Torres
 */
public enum ClassEnum {
    Access(Access.class),
    Role(Role.class),
    User(User.class);

    Class<?> clazz;

    ClassEnum(Class<?> clazz) {
	this.clazz = clazz;
    }

    public Class<?> getClazz() {
	return clazz;
    }
}
