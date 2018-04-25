/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.dao.implement;

import com.bluu.hdm.rest.dao.interfaces.IModelDAO;
import com.bluu.hdm.rest.entity.ModelEntity;
import com.bluu.hdm.rest.repository.ModelRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gonzalo Torres
 */
@Service
@Transactional
public class ModelDAOImpl implements IModelDAO {

    private static Logger logger = LogManager.getLogger(ModelDAOImpl.class.getName());

    @Autowired
    ModelRepository modelRepository;

    @Override
    public ModelEntity save(ModelEntity m) {
	ModelEntity model = new ModelEntity();
	try {
	    model = modelRepository.save(m);
	} catch (NonTransientDataAccessException ex) {
	    logger.error(String.format("Error: %s", ex.getMessage()));
	}
	return model;
    }

    @Override
    public Optional<ModelEntity> findById(Long id) {
	return modelRepository.findById(id);
    }

    @Override
    public List<ModelEntity> findAll() {
	return modelRepository.findAll();
    }

    @Override
    public boolean deleteById(Long id) {
	modelRepository.deleteById(id);
	return !modelRepository.existsById(id);
    }

    @Override
    public boolean isExist(Long id) {
	return modelRepository.existsById(id);
    }

    @Override
    public void deleteAll() {
	modelRepository.deleteAll();
    }
}
