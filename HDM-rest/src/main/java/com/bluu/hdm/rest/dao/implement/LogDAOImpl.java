/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.dao.implement;

import com.bluu.hdm.rest.dao.interfaces.ILogDAO;
import com.bluu.hdm.rest.entity.LogEntity;
import com.bluu.hdm.rest.repository.administration.LogRepository;
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
 * @author Marco Valdés
 */
@Service
@Transactional
public class LogDAOImpl implements ILogDAO {

    private static Logger logger = LogManager.getLogger(LogDAOImpl.class.getName());
    @Autowired
    LogRepository logRepository;
  
    @Override
    public LogEntity save(LogEntity entity) {
        
        LogEntity log = new LogEntity();
	try {
	    log = logRepository.save(entity);
	} catch (NonTransientDataAccessException ex) {
	    logger.error(String.format("Error: %s", ex.getMessage()));
	}
	return log;
        
    }

    @Override
    public Optional<LogEntity> findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LogEntity> findAll() {
        return logRepository.findAll();
    }

    @Override
    public boolean deleteById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isExist(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
