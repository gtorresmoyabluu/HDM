/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.service;

import com.bluu.hdm.rest.dao.interfaces.IManufacturerDAO;
import com.bluu.hdm.rest.entity.ManufacturerEntity;
import com.bluu.hdm.rest.entity.ModelEntity;
import com.bluu.hdm.rest.vo.ManufacturerVO;
import com.bluu.hdm.rest.vo.ModelVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
@RequestMapping("/v1/manufacturers")
public class ManufacturerService {

    private static Logger logger = LogManager.getLogger(ManufacturerService.class.getName());
    @Autowired
    IManufacturerDAO mService;

    @Autowired
    ObjectMapper mapper;

    private static PageRequest pageRequest;

    /**
     * Add New Manufacturer
     *
     * @param mRequest
     * @param ucBuilder
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Void> save(@RequestBody ManufacturerVO mRequest, UriComponentsBuilder ucBuilder) {
	if (mRequest.getId() != null) {
	    if (mService.isExist(mRequest.getId())) {
		logger.error("A Manufacturer with name " + mRequest.getName() + " already exist");
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	    }
	}

	ManufacturerEntity entity = mapper.convertValue(mRequest, ManufacturerEntity.class);
	mRequest.getModelEntitySet().stream().map((rol) -> mapper.convertValue(rol, ModelEntity.class)).forEachOrdered((model) -> {
	    entity.setModelEntitySet(new HashSet<ModelEntity>() {
		{
		    add(model);
		}
	    });
	});

	mService.save(entity);

	HttpHeaders headers = new HttpHeaders();
	headers.setLocation(ucBuilder.path("/find/{id}").buildAndExpand(entity.getId()).toUri());
	return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    /**
     * Update Manufacturer
     *
     * @param id
     * @param mRequest
     * @return
     */
    @RequestMapping(value = "/upd/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ManufacturerVO> update(@PathVariable("id") long id, @RequestBody ManufacturerVO mRequest) {
	System.out.println("Updating User " + id);
	if (mService.isExist(mRequest.getId())) {
	    logger.error("Manufacturer with id " + id + " not found");
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	ManufacturerEntity entity = mapper.convertValue(mRequest, ManufacturerEntity.class);
	mService.save(entity);
	return new ResponseEntity<>(mRequest, HttpStatus.OK);
    }

    /**
     * Get All Manufacturer
     *
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ManufacturerVO>> getAll() {
	List<ManufacturerVO> response = new ArrayList<>();
	try {
	    List<ManufacturerEntity> source = mService.findAll();
	    if (source.isEmpty()) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);// You many decide to return
		// HttpStatus.NOT_FOUND
	    } else {
		source.forEach((manufacturerEntity) -> {
		    response.add(mapper.convertValue(manufacturerEntity, ManufacturerVO.class));
		});
		return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	} catch (IllegalArgumentException ex) {
	    logger.error(String.format("Error: %s", ex.getMessage()));
	}
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);// You many decide to return HttpStatus.NOT_FOUND
    }

    /**
     * Find Manufacturer By ID
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ManufacturerVO> findById(@PathVariable(value = "id") long id) {
	try {
	    ManufacturerEntity source = mService.findById(id).get();
	    if (source != null) {
		return new ResponseEntity<>(mapper.convertValue(source, ManufacturerVO.class), HttpStatus.OK);
	    } else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	} catch (IllegalArgumentException ex) {
	    logger.error(String.format("Error: %s", ex.getMessage()));
	}
	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Delete Manufacturer By ID
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ManufacturerVO> delete(@PathVariable(value = "id") long id) {
	try {
	    ManufacturerEntity source = mService.findById(id).get();
	    if (source != null) {
		mService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	} catch (Exception ex) {
	    logger.error(String.format("Error: %s", ex.getMessage()));
	}
	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Delete All Manufacturer
     *
     * @return
     */
    @RequestMapping(value = "/delAll", method = RequestMethod.DELETE)
    public ResponseEntity<ManufacturerVO> deleteAllRoles() {
	mService.deleteAll();
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Get All Models to Manufacturer By ID
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/allModels/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ModelVO>> findAllByManufacturerID(@PathVariable(value = "id") long id) {
	List<ModelVO> response = new ArrayList<>();
	try {
	    List<ModelEntity> source = mService.findAllByManufacturerID(id);
	    if (source.isEmpty()) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);// You many decide to return
		// HttpStatus.NOT_FOUND
	    } else {
		source.forEach((modelEntity) -> {
		    response.add(mapper.convertValue(modelEntity, ModelVO.class));
		});
		return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	} catch (IllegalArgumentException ex) {
	    logger.error(String.format("Error: %s", ex.getMessage()));
	}
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);// You many decide to return HttpStatus.NOT_FOUND
    }
}
