/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.dao.implement;

import com.bluu.hdm.rest.dao.interfaces.IManufacturerDAO;
import com.bluu.hdm.rest.entity.ManufacturerEntity;
import com.bluu.hdm.rest.entity.ModelEntity;
import com.bluu.hdm.rest.repository.ManufacturerRepository;
import java.util.ArrayList;
import java.util.Iterator;
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
public class ManufacturerDAOImpl implements IManufacturerDAO {

    private static Logger logger = LogManager.getLogger(ManufacturerDAOImpl.class.getName());

    @Autowired
    ManufacturerRepository mRepository;

    @Override
    public ManufacturerEntity save(ManufacturerEntity entity) {
	ManufacturerEntity man = new ManufacturerEntity();
	try {
	    man = mRepository.save(entity);
	} catch (NonTransientDataAccessException ex) {
	    logger.error(String.format("Error: %s", ex.getMessage()));
	}
	return man;
    }

    @Override
    public Optional<ManufacturerEntity> findById(Long id) {
	return mRepository.findById(id);
    }

    @Override
    public List<ManufacturerEntity> findAll() {
	return mRepository.findAll();
    }

    @Override
    public boolean deleteById(Long id) {
	mRepository.deleteById(id);
	return !mRepository.existsById(id);
    }

    @Override
    public boolean isExist(Long id) {
	return mRepository.existsById(id);
    }

    @Override
    public void deleteAll() {
	mRepository.deleteAll();
    }

    @Override
    public List<ModelEntity> findAllByManufacturerID(long id_manufacturer) {
	List<ModelEntity> result = new ArrayList<>();
	List<ManufacturerEntity> list = mRepository.findAll();
	Iterator<ManufacturerEntity> it = list.iterator();
	while (it.hasNext()) {
	    ManufacturerEntity current = it.next();
	    if (current.getId() == id_manufacturer) {
		for (ModelEntity model : current.getModelEntitySet()) {
		    result.add(model);
		}
	    }
	}
	return result;
    }

}
