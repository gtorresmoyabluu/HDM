/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.dao.implement.administration;

import com.bluu.hdm.rest.dao.interfaces.administration.IClientDAO;
import com.bluu.hdm.rest.entity.ClientEntity;
import com.bluu.hdm.rest.repository.administration.ClientRepository;
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
public class ClientDAOImpl implements IClientDAO{

    private static Logger logger = LogManager.getLogger(ClientDAOImpl.class.getName());
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public ClientEntity save(ClientEntity entity) {
	ClientEntity obj = new ClientEntity();
	try {
	    obj = clientRepository.save(entity);
	} catch (NonTransientDataAccessException ex) {
	    logger.error(String.format("Error: %s", ex.getMessage()));
	}
	return obj;
    }

    @Override
    public Optional<ClientEntity> findById(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public List<ClientEntity> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public boolean deleteById(Long id) {
        clientRepository.deleteById(id);
        return !clientRepository.existsById(id);
    }

    @Override
    public boolean isExist(Long id) {
        return clientRepository.existsById(id);
    }

    @Override
    public void deleteAll() {
        clientRepository.deleteAll();
    }

    @Override
    public ClientEntity getClientByname(String name) {
        return clientRepository.getClientByname(name);
    }
    
}
