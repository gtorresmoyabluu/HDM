/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.dao.implement.administration;

import com.bluu.hdm.rest.dao.interfaces.administration.ILicenseDAO;
import com.bluu.hdm.rest.entity.LicenseEntity;
import com.bluu.hdm.rest.repository.administration.LicenseRepository;
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
public class LicenseDAOImpl implements ILicenseDAO {
    
    private static Logger logger = LogManager.getLogger(LicenseDAOImpl.class.getName());
    @Autowired
    private LicenseRepository licenseRepository;
    
    @Override
    public LicenseEntity save(LicenseEntity entity) {
        LicenseEntity object = new LicenseEntity();
        try {
            object = licenseRepository.save(entity);
        } catch (NonTransientDataAccessException ex) {
            logger.error(String.format("Error: %s", ex.getMessage()));
        }
        return object;
    }
    
    @Override
    public Optional<LicenseEntity> findById(Long id) {
        return licenseRepository.findById(id);
    }
    
    @Override
    public List<LicenseEntity> findAll() {
        return licenseRepository.findAll();
    }
    
    @Override
    public boolean deleteById(Long id) {
        licenseRepository.deleteById(id);
        return !licenseRepository.existsById(id);
    }
    
    @Override
    public boolean isExist(Long id) {
        return licenseRepository.existsById(id);
    }
    
    @Override
    public void deleteAll() {
        licenseRepository.deleteAll();
    }
    
    @Override
    public LicenseEntity findBycode(String code) {
        return licenseRepository.findBycode(code).orElse(null);
    }
    
}
