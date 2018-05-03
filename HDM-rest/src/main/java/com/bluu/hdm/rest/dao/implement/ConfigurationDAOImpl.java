/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.dao.implement;

import com.bluu.hdm.rest.dao.interfaces.IConfigurationDAO;
import com.bluu.hdm.rest.entity.ConfigurationEntity;
import com.bluu.hdm.rest.repository.ConfigurationRepository;
import java.util.List;
import java.util.Optional;
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
}
