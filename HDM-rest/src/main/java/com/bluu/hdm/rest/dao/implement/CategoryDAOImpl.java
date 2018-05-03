/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.dao.implement;

import com.bluu.hdm.rest.dao.interfaces.ICategoryDAO;
import com.bluu.hdm.rest.entity.CategoryEntity;
import com.bluu.hdm.rest.repository.CategoryRepository;
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
public class CategoryDAOImpl implements ICategoryDAO {

    private static Logger logger = LogManager.getLogger(CategoryDAOImpl.class.getName());
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryEntity save(CategoryEntity entity) {
	CategoryEntity object = new CategoryEntity();
	try {
	    object = categoryRepository.save(entity);
	} catch (NonTransientDataAccessException ex) {
	    logger.error(String.format("Error: %s", ex.getMessage()));
	}
	return object;
    }

    @Override
    public Optional<CategoryEntity> findById(Long id) {
	return categoryRepository.findById(id);
    }

    @Override
    public List<CategoryEntity> findAll() {
	return categoryRepository.findAll();
    }

    @Override
    public boolean deleteById(Long id) {
	categoryRepository.deleteById(id);
	return !categoryRepository.existsById(id);
    }

    @Override
    public boolean isExist(Long id) {
	return categoryRepository.existsById(id);
    }

    @Override
    public void deleteAll() {
	categoryRepository.deleteAll();
    }

}
