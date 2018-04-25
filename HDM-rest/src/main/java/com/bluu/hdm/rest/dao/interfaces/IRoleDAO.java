/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.dao.interfaces;

import com.bluu.hdm.rest.entity.RoleEntity;
import com.bluu.hdm.rest.entity.UserEntity;
import java.util.List;
import com.bluu.hdm.rest.dao.generic.IGenericDAO;

/**
 *
 * @author Gonzalo Torres
 */
public interface IRoleDAO extends IGenericDAO<RoleEntity, Long> {

    List<UserEntity> findAllByRoleID(long id_role);

    RoleEntity findByName(String name);
}
