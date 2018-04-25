/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.dao.interfaces;

import com.bluu.hdm.rest.entity.AccessEntity;
import java.util.List;
import com.bluu.hdm.rest.dao.generic.IGenericDAO;

/**
 *
 * @author Gonzalo Torres
 */
public interface IAccessDAO extends IGenericDAO<AccessEntity, Long> {

    void addAccessRole(long id_access, long id_role);

    List<AccessEntity> findAllByRoleID(long id_role);
}
