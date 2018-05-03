/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.service;

import com.bluu.hdm.rest.dao.interfaces.IConfigurationDAO;
import com.bluu.hdm.rest.entity.CategoryEntity;
import com.bluu.hdm.rest.entity.ConfigurationEntity;
import com.bluu.hdm.rest.service.generic.IService;
import com.bluu.hdm.rest.vo.CategoryVO;
import com.bluu.hdm.rest.vo.ConfigurationVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
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
@RequestMapping("/v1/config")
public class ConfigService implements IService<ConfigurationVO, Long> {

    private static Logger logger = LogManager.getLogger(ConfigService.class.getName());
    @Autowired
    IConfigurationDAO configService;

    @Autowired
    ObjectMapper mapper;

    /**
     * Add Configuration
     *
     * @param oRequest
     * @param ucBuilder
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @Override
    public ResponseEntity<Void> save(@RequestBody ConfigurationVO oRequest, UriComponentsBuilder ucBuilder) {
	if (oRequest.getId() != null) {
	    if (configService.isExist(oRequest.getId())) {
		logger.error("A with name " + oRequest.getKey() + " already exist");
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	    }
	}
	ConfigurationEntity entity = mapper.convertValue(oRequest, ConfigurationEntity.class);
	configService.save(entity);

	HttpHeaders headers = new HttpHeaders();
	headers.setLocation(ucBuilder.path("/find/{id}").buildAndExpand(entity.getId()).toUri());
	return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    /**
     * Update Configuration
     *
     * @param id
     * @param oRequest
     * @return
     */
    @RequestMapping(value = "/upd/{id}", method = RequestMethod.PUT)
    @Override
    public ResponseEntity<ConfigurationVO> update(@PathVariable("id") Long id, @RequestBody ConfigurationVO oRequest) {
	System.out.println("Updating " + id);
	if (!configService.isExist(oRequest.getId())) {
	    logger.error("Object with id " + id + " not found");
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	ConfigurationEntity entity = mapper.convertValue(oRequest, ConfigurationEntity.class);
	configService.save(entity);
	return new ResponseEntity<>(oRequest, HttpStatus.OK);
    }

    /**
     * Delete Configuration
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
    @Override
    public ResponseEntity<ConfigurationVO> delete(@PathVariable("id") Long id) {
	try {
	    ConfigurationEntity source = configService.findById(id).get();
	    if (source != null) {
		configService.deleteById(id);
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
     * Delete All Configurations
     *
     * @return
     */
    @RequestMapping(value = "/delAll", method = RequestMethod.DELETE)
    @Override
    public ResponseEntity<ConfigurationVO> deleteAll() {
	configService.deleteAll();
	return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Return Configuration
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public ResponseEntity<ConfigurationVO> findById(@PathVariable("id") Long id) {
	try {
	    ConfigurationEntity source = configService.findById(id).get();
	    if (source != null) {
		return new ResponseEntity<>(mapper.convertValue(source, ConfigurationVO.class), HttpStatus.OK);
	    } else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	} catch (IllegalArgumentException ex) {
	    logger.error(String.format("Error: %s", ex.getMessage()));
	}
	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<List<ConfigurationVO>> getAll() {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Return List Configurations By Category
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/all/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ConfigurationVO>> getAll(@PathVariable("id") Long id) {
	List<ConfigurationVO> response = new ArrayList<>();
	try {
	    List<ConfigurationEntity> source = configService.findAll()
		    .stream()
		    .filter(cnf -> Objects.equals(cnf.getId_category().getId(), id))
		    .collect(Collectors.toList());
	    if (source.isEmpty()) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);// You many decide to return
		// HttpStatus.NOT_FOUND
	    } else {
		source.forEach((objEntity) -> {
		    ConfigurationVO conf = new ConfigurationVO(objEntity);
		    response.add(conf);
		});
		return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	} catch (IllegalArgumentException ex) {
	    logger.error(String.format("Error: %s", ex.getMessage()));
	}
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);// You many decide to return HttpStatus.NOT_FOUND
    }
}
