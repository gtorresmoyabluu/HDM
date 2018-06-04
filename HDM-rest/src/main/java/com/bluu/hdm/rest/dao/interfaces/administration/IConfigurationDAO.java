/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.dao.interfaces.administration;

import com.bluu.hdm.rest.dao.generic.IGenericDAO;
import com.bluu.hdm.rest.entity.ConfigurationEntity;
import java.util.List;
import org.springframework.data.jpa.repository.query.Procedure;

/**
 *
 * @author Gonzalo Torres
 */
public interface IConfigurationDAO extends IGenericDAO<ConfigurationEntity, Long> {
    List<ConfigurationEntity> findByIdClient(Long clientId);
    
    @Procedure("ConfigurationEntity.initConfClient")
    void setInitConfigClient(Long id_client);
    
    @Procedure("ConfigurationEntity.getConfigByClient")
    List<ConfigurationEntity> getConfigByClient(Long id_category, Long id_client);
}
