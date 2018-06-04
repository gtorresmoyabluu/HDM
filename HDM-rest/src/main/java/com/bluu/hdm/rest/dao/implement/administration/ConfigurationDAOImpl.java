/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.dao.implement.administration;

import com.bluu.hdm.rest.dao.interfaces.administration.IConfigurationDAO;
import com.bluu.hdm.rest.entity.ConfigurationEntity;
import com.bluu.hdm.rest.repository.administration.ConfigurationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gonzalo Torres
 */
@Service
@Transactional
public class ConfigurationDAOImpl implements IConfigurationDAO {

    private static Logger logger = LogManager.getLogger(ConfigurationDAOImpl.class.getName());
    @Autowired
    private ConfigurationRepository configRepository;

    @Autowired
    ObjectMapper mapper;
    
    @Autowired
    EntityManager em;

    @Override
    public ConfigurationEntity save(ConfigurationEntity entity) {
        ConfigurationEntity obj = new ConfigurationEntity();
        try {
            obj = configRepository.save(entity);
        } catch (NonTransientDataAccessException ex) {
            logger.error(String.format("Error: %s", ex.getMessage()));
        }
        return obj;
    }

    @Override
    public Optional<ConfigurationEntity> findById(Long id) {
        return configRepository.findById(id);
    }

    @Override
    public List<ConfigurationEntity> findAll() {
        return configRepository.findAll();
    }

    @Override
    public boolean deleteById(Long id) {
        configRepository.deleteById(id);
        return !configRepository.existsById(id);
    }

    @Override
    public boolean isExist(Long id) {
        return configRepository.existsById(id);
    }

    @Override
    public void deleteAll() {
        configRepository.deleteAll();
    }

    @Override
    public List<ConfigurationEntity> findByIdClient(Long clientId) {
        try {
            return configRepository.findByClientId(clientId);
        } catch (NullPointerException ex) {
            logger.error(String.format("Error en findByIdClient: %s", ex.getMessage()));
            return new ArrayList<>(0);
        }
    }

    @Override
    public void setInitConfigClient(Long id_client) {
        StoredProcedureQuery query = em.createNamedStoredProcedureQuery("initConfClient");
        query.setParameter("p_IdClient", id_client);
        query.execute();
    }

    @Override
    public List<ConfigurationEntity> getConfigByClient(Long id_category, Long id_client) {
        StoredProcedureQuery query = em.createNamedStoredProcedureQuery("getConfigByClient");
        query.setParameter("pCategory", id_category);
        query.setParameter("pClient", id_client);
        List<Object[]> rows = query.getResultList();

        List<ConfigurationEntity> result = new ArrayList<>(rows.size());
        for (Object[] row : rows) {
            result.add(new ConfigurationEntity(row));
        }
        return result;
    }
}
