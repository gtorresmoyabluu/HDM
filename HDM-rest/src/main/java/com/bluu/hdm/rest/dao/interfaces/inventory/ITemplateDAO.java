/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.dao.interfaces.inventory;

import com.bluu.hdm.rest.dao.generic.IGenericDAO;
import com.bluu.hdm.rest.entity.TemplateEntity;
import java.util.Optional;

/**
 *
 * @author Marco Valdés
 */
public interface ITemplateDAO extends IGenericDAO<TemplateEntity, Long> {

    Optional<TemplateEntity> findTemplateByname(String name);
}
