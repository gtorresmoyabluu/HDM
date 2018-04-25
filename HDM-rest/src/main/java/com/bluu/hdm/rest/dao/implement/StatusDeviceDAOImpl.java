/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.dao.implement;

import com.bluu.hdm.rest.dao.interfaces.IStatusDeviceDAO;
import com.bluu.hdm.rest.entity.StatusDevicesEntity;
import com.bluu.hdm.rest.repository.StatusDeviceRepository;
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
public class StatusDeviceDAOImpl implements IStatusDeviceDAO {

    private static Logger logger = LogManager.getLogger(StatusDeviceDAOImpl.class.getName());

    @Autowired
    StatusDeviceRepository statusRepository;

    @Override
    public StatusDevicesEntity save(StatusDevicesEntity status) {
	StatusDevicesEntity entity = new StatusDevicesEntity();
	try {
	    entity = statusRepository.save(status);
	} catch (NonTransientDataAccessException ex) {
	    logger.error(String.format("Error: %s", ex.getMessage()));
	}
	return entity;
    }

    @Override
    public Optional<StatusDevicesEntity> findById(Long id) {
	return statusRepository.findById(id);
    }

    @Override
    public List<StatusDevicesEntity> findAll() {
	return statusRepository.findAll();
    }

    @Override
    public boolean deleteById(Long id) {
	statusRepository.deleteById(id);
	return !statusRepository.existsById(id);
    }

    @Override
    public boolean isExist(Long id) {
	return statusRepository.existsById(id);
    }

    @Override
    public void deleteAll() {
	statusRepository.deleteAll();
    }

}
