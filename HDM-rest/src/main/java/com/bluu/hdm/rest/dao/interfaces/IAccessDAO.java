/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.dao.interfaces;

import com.bluu.hdm.rest.entity.AccessEntity;
import java.util.List;
import com.bluu.hdm.rest.dao.generic.IGenericDAO;
import org.springframework.data.jpa.repository.query.Procedure;

/**
 *
 * @author Gonzalo Torres
 */
public interface IAccessDAO extends IGenericDAO<AccessEntity, Long> {

    void addAccessRole(long id_access, long id_role);

    List<AccessEntity> findAllByRoleID(long id_role);

    @Procedure("AccessEntity.getAccessParent")
    List<AccessEntity> getAccessParent(Long id_role);

    @Procedure("AccessEntity.getAccessChild")
    List<AccessEntity> getAccessChild(Long parent, Long id_role);

    @Procedure("AccessEntity.getAccessByRolId")
    List<AccessEntity> getAccessByIdRol(Long id_role);

    @Procedure("AccessEntity.getAccessChildByIdRol")
    List<AccessEntity> getAccessChildByIdRol(Long parent, Long id_role);

    @Procedure("AccessEntity.setAccessToRol")
    void setAccessToRol(Long id_role, Long child, Long parent);
}
