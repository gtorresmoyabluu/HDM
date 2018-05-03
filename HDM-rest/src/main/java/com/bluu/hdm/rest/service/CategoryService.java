/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.service;

import com.bluu.hdm.rest.dao.interfaces.ICategoryDAO;
import com.bluu.hdm.rest.entity.CategoryEntity;
import com.bluu.hdm.rest.service.generic.IService;
import com.bluu.hdm.rest.vo.CategoryVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Gonzalo Torres
 */
@RestController
@RequestMapping("/v1/category")
public class CategoryService implements IService<CategoryVO, Long> {

    private static Logger logger = LogManager.getLogger(CategoryService.class.getName());
    @Autowired
    ICategoryDAO categoryService;

    @Autowired
    ObjectMapper mapper;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @Override
    public ResponseEntity<Void> save(@RequestBody CategoryVO oRequest, UriComponentsBuilder ucBuilder) {
	if (oRequest.getId() != null) {
	    if (categoryService.isExist(oRequest.getId())) {
		logger.error("A with name " + oRequest.getName() + " already exist");
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	    }
	}
	CategoryEntity entity = mapper.convertValue(oRequest, CategoryEntity.class);
	categoryService.save(entity);

	HttpHeaders headers = new HttpHeaders();
	headers.setLocation(ucBuilder.path("/find/{id}").buildAndExpand(entity.getId()).toUri());
	return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/upd/{id}", method = RequestMethod.PUT)
    @Override
    public ResponseEntity<CategoryVO> update(@PathVariable("id") Long id, @RequestBody CategoryVO oRequest) {
	System.out.println("Updating " + id);
	if (!categoryService.isExist(oRequest.getId())) {
	    logger.error("Object with id " + id + " not found");
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	CategoryEntity entity = mapper.convertValue(oRequest, CategoryEntity.class);
	categoryService.save(entity);
	return new ResponseEntity<>(oRequest, HttpStatus.OK);
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
    @Override
    public ResponseEntity<CategoryVO> delete(@PathVariable("id") Long id) {
	try {
	    CategoryEntity source = categoryService.findById(id).get();
	    if (source != null) {
		categoryService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	} catch (Exception ex) {
	    logger.error(String.format("Error: %s", ex.getMessage()));
	}
	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/delAll", method = RequestMethod.DELETE)
    @Override
    public ResponseEntity<CategoryVO> deleteAll() {
	categoryService.deleteAll();
	return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public ResponseEntity<CategoryVO> findById(@PathVariable("id") Long id) {
	try {
	    CategoryEntity source = categoryService.findById(id).get();
	    if (source != null) {
		return new ResponseEntity<>(mapper.convertValue(source, CategoryVO.class), HttpStatus.OK);
	    } else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	} catch (IllegalArgumentException ex) {
	    logger.error(String.format("Error: %s", ex.getMessage()));
	}
	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public ResponseEntity<List<CategoryVO>> getAll() {
	List<CategoryVO> response = new ArrayList<>();
	try {
	    List<CategoryEntity> source = categoryService.findAll();
	    if (source.isEmpty()) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);// You many decide to return
		// HttpStatus.NOT_FOUND
	    } else {
		source.forEach((roleEntity) -> {
		    response.add(mapper.convertValue(roleEntity, CategoryVO.class));
		});
		return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	} catch (IllegalArgumentException ex) {
	    logger.error(String.format("Error: %s", ex.getMessage()));
	}
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);// You many decide to return HttpStatus.NOT_FOUND
    }
}
