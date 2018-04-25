/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.service;

import com.bluu.hdm.rest.dao.interfaces.IStatusDeviceDAO;
import com.bluu.hdm.rest.entity.StatusDevicesEntity;
import com.bluu.hdm.rest.vo.StatusDeviceVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
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
@RequestMapping("/v1/status")
public class StatusDeviceService {

    private static final Logger logger = LogManager.getLogger(RoleService.class.getName());
    @Autowired
    IStatusDeviceDAO statusService;

    @Autowired
    ObjectMapper mapper;

    /**
     * New Status Device
     *
     * @param statusRequest
     * @param ucBuilder
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Void> save(@RequestBody StatusDeviceVO statusRequest, UriComponentsBuilder ucBuilder) {
	if (statusRequest.getId() != null) {
	    if (statusService.isExist(statusRequest.getId())) {
		logger.error("A Status with name " + statusRequest.getName() + " already exist");
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	    }
	}
	StatusDevicesEntity entity = mapper.convertValue(statusRequest, StatusDevicesEntity.class);
	statusService.save(entity);

	HttpHeaders headers = new HttpHeaders();
	headers.setLocation(ucBuilder.path("/find/{id}").buildAndExpand(entity.getId()).toUri());
	return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    /**
     * Update Status Device
     *
     * @param id
     * @param statusRequest
     * @return
     */
    @RequestMapping(value = "/upd/{id}", method = RequestMethod.PUT)
    public ResponseEntity<StatusDeviceVO> updateStatus(@PathVariable("id") long id, @RequestBody StatusDeviceVO statusRequest) {
	System.out.println("Updating User " + id);
	if (statusService.isExist(statusRequest.getId())) {
	    logger.error("Status with id " + id + " not found");
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	StatusDevicesEntity entity = mapper.convertValue(statusRequest, StatusDevicesEntity.class);
	statusService.save(entity);
	return new ResponseEntity<>(statusRequest, HttpStatus.OK);
    }

    /**
     * Return All Status Device
     *
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<StatusDeviceVO>> getAll() {
	List<StatusDeviceVO> response = new ArrayList<>();
	try {
	    List<StatusDevicesEntity> source = statusService.findAll();
	    if (source.isEmpty()) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);// You many decide to return HttpStatus.NOT_FOUND
	    } else {
		source.forEach((statusEntity) -> {
		    response.add(mapper.convertValue(statusEntity, StatusDeviceVO.class));
		});
		return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	} catch (IllegalArgumentException ex) {
	    logger.error(String.format("Error: %s", ex.getMessage()));
	}
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);// You many decide to return HttpStatus.NOT_FOUND
    }

    /**
     * Find Status Device By ID
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<StatusDeviceVO> findById(@PathVariable(value = "id") long id) {
	try {
	    StatusDevicesEntity source = statusService.findById(id).get();
	    if (source != null) {
		return new ResponseEntity<>(mapper.convertValue(source, StatusDeviceVO.class), HttpStatus.OK);
	    } else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	} catch (IllegalArgumentException ex) {
	    logger.error(String.format("Error: %s", ex.getMessage()));
	}
	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Delete Status Device By ID
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<StatusDeviceVO> delete(@PathVariable(value = "id") long id) {
	try {
	    StatusDevicesEntity source = statusService.findById(id).get();
	    if (source != null) {
		statusService.deleteById(id);
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
     * Delete All Status Device
     *
     * @return
     */
    @RequestMapping(value = "/delAll", method = RequestMethod.DELETE)
    public ResponseEntity<StatusDeviceVO> deleteAllRoles() {
	statusService.deleteAll();
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
