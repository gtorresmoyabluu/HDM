/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.dao.implement.inventory;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.stereotype.Service;
import com.bluu.hdm.rest.dao.interfaces.inventory.ITemplateDAO;
import com.bluu.hdm.rest.entity.TemplateEntity;
import com.bluu.hdm.rest.repository.inventory.TemplateRepository;

/**
 *
 * @author Marco Valdés
 */
@Service
@Transactional
public class TemplateDAOImpl implements ITemplateDAO {

    private static Logger logger = LogManager.getLogger(TemplateDAOImpl.class.getName());
    
    @Autowired
    TemplateRepository templateRepository;

    @Override
    public Optional<TemplateEntity> findTemplateByname(String name) {
        return templateRepository.findTemplateByname(name);
    }

    @Override
    public TemplateEntity save(TemplateEntity entity) {

        TemplateEntity template = new TemplateEntity();
        try {
            template = templateRepository.save(entity);
        } catch (NonTransientDataAccessException ex) {
            logger.error(String.format("Error: %s", ex.getMessage()));
        }
        return template;

    }

    @Override
    public Optional<TemplateEntity> findById(Long id) {
        return templateRepository.findById(id);
    }

    @Override
    public List<TemplateEntity> findAll() {
        return templateRepository.findAll();
    }

    @Override
    public boolean deleteById(Long id) {
        if (templateRepository.existsById(id)) {
            templateRepository.deleteById(id);
            return true;
        } else {
            logger.error(String.format("Template ID %s not exists!", id));
            return false;
        }
    }

    @Override
    public boolean isExist(Long id) {
        return templateRepository.existsById(id);
    }

    @Override
    public void deleteAll() {
        templateRepository.deleteAll();
    }

}
