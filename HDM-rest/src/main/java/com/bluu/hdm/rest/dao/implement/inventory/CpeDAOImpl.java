/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.dao.implement.inventory;

import com.bluu.hdm.rest.entity.CpeEntity;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.stereotype.Service;
import com.bluu.hdm.rest.dao.interfaces.inventory.ICpeDAO;
import com.bluu.hdm.rest.repository.inventory.CpeRepository;
import org.hibernate.query.Query;

/**
 *
 * @author Marco Valdés
 */
@Service
@Transactional
public class CpeDAOImpl implements ICpeDAO {

    private static Logger logger = LogManager.getLogger(CpeDAOImpl.class.getName());
    @Autowired
    CpeRepository deviceRepository;
  
    @Override
    public CpeEntity save(CpeEntity entity) {
        
        CpeEntity device = new CpeEntity();
	try {
	    device = deviceRepository.save(entity);
	} catch (NonTransientDataAccessException ex) {
	    logger.error(String.format("Error: %s", ex.getMessage()));
	}
	return device;
        
    }

    @Override
    public Optional<CpeEntity> findById(Long id) {
        return deviceRepository.findById(id);
    }
    
    @Override
    public List<CpeEntity> findAll() {
        return deviceRepository.findAll();
    }

    @Override
    public boolean deleteById(Long id) {
        if (deviceRepository.existsById(id)) {
	    deviceRepository.deleteById(id);
	    return true;
	} else {
	    logger.error(String.format("Cpe ID %s not exists!", id));
	    return false;
	}
    }

    @Override
    public boolean isExist(Long id) {
       return deviceRepository.existsById(id);
    }

    @Override
    public void deleteAll() {
       deviceRepository.deleteAll();
    }

    @Override
    public Optional<CpeEntity> findByidDevice(String idDevice) {
         return deviceRepository.findByidDevice(idDevice);
    }

    @Override
    public Query createNamedQuery(String name) {
        return null;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
