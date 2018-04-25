/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.dao.interfaces;

import com.bluu.hdm.rest.entity.UserEntity;
import com.bluu.hdm.rest.dao.generic.IGenericDAO;

/**
 *
 * @author Gonzalo Torres
 */
public interface IUserDAO extends IGenericDAO<UserEntity, Long> {

    UserEntity getUserByfirstname(String name);

    UserEntity getUserByusername(String username);
}
