/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.service;

import com.bluu.hdm.rest.dao.interfaces.IModelDAO;
import com.bluu.hdm.rest.entity.ModelEntity;
import com.bluu.hdm.rest.vo.ModelVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/v1/models")
public class ModelService {

    private static Logger logger = LogManager.getLogger(ModelService.class.getName());
    @Autowired
    IModelDAO modelService;

    @Autowired
    ObjectMapper mapper;

    /**
     * Add Model
     *
     * @param mRequest
     * @param ucBuilder
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Void> save(@RequestBody ModelVO mRequest, UriComponentsBuilder ucBuilder) {
	if (mRequest.getId() != null) {
	    if (modelService.isExist(mRequest.getId())) {
		logger.error("A Model with name " + mRequest.getName() + " already exist");
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	    }
	}

	ModelEntity entity = mapper.convertValue(mRequest, ModelEntity.class);
	modelService.save(entity);

	HttpHeaders headers = new HttpHeaders();
	headers.setLocation(ucBuilder.path("/find/{id}").buildAndExpand(entity.getId()).toUri());
	return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}
