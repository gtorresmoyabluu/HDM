/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.dao.implement;

import com.bluu.hdm.rest.dao.interfaces.IFirmwareDAO;
import com.bluu.hdm.rest.entity.FirmwareEntity;
import com.bluu.hdm.rest.repository.FirmwareRepository;
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
public class FirmwareDAOImpl implements IFirmwareDAO {

    private static Logger logger = LogManager.getLogger(FirmwareDAOImpl.class.getName());

    @Autowired
    FirmwareRepository firmwareRepository;

    @Override
    public FirmwareEntity save(FirmwareEntity f) {
	FirmwareEntity firmware = new FirmwareEntity();
	try {
	    firmware = firmwareRepository.save(f);
	} catch (NonTransientDataAccessException ex) {
	    logger.error(String.format("Error: %s", ex.getMessage()));
	}
	return firmware;
    }

    @Override
    public Optional<FirmwareEntity> findById(Long id) {
	return firmwareRepository.findById(id);
    }

    @Override
    public List<FirmwareEntity> findAll() {
	return firmwareRepository.findAll();
    }

    @Override
    public boolean deleteById(Long id) {
	firmwareRepository.deleteById(id);
	return !firmwareRepository.existsById(id);
    }

    @Override
    public boolean isExist(Long id) {
	return firmwareRepository.existsById(id);
    }

    @Override
    public void deleteAll() {
	firmwareRepository.deleteAll();
    }

}
