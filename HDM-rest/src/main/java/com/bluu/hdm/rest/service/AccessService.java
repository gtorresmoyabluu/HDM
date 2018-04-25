/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.service;

import com.bluu.hdm.rest.dao.interfaces.IAccessDAO;
import com.bluu.hdm.rest.dao.interfaces.IRoleDAO;
import com.bluu.hdm.rest.entity.AccessEntity;
import com.bluu.hdm.rest.entity.RoleEntity;
import com.bluu.hdm.rest.vo.AccessVO;
import com.bluu.hdm.rest.vo.RoleVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
@RequestMapping("/v1/access")
public class AccessService {

    private static Logger logger = LogManager.getLogger(RoleService.class.getName());
    @Autowired
    IAccessDAO accessService;

    @Autowired
    IRoleDAO roleService;

    @Autowired
    ObjectMapper mapper;

    private static PageRequest pageRequest;

    /**
     * Add new Access
     *
     * @param accessRequest
     * @param ucBuilder
     * @return ResponseEntity<>
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Void> save(@RequestBody AccessVO accessRequest, UriComponentsBuilder ucBuilder) {
	if (accessRequest.getId() != null) {
	    if (accessService.isExist(accessRequest.getId())) {
		logger.error("A Access with name " + accessRequest.getDescription() + " already exist");
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	    }
	}

	AccessEntity entity = mapper.convertValue(accessRequest, AccessEntity.class);
	for (RoleVO rol : accessRequest.getRoleEntitySet()) {
	    RoleEntity role = mapper.convertValue(rol, RoleEntity.class);
	    entity.setRoleEntitySet(new HashSet<RoleEntity>() {
		{
		    add(role);
		}
	    });
	}

	accessService.save(entity);

	HttpHeaders headers = new HttpHeaders();
	headers.setLocation(ucBuilder.path("/find/{id}").buildAndExpand(entity.getId()).toUri());
	return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    /**
     * Update Access
     *
     * @param accessRequest
     * @return
     */
    @RequestMapping(value = "/upd", method = RequestMethod.PUT)
    public ResponseEntity<AccessVO> updateUser(@RequestBody AccessVO accessRequest) {
	if (accessRequest.getId() != null) {
	    if (!accessService.isExist(accessRequest.getId())) {
		logger.error("Access with id " + accessRequest.getId() + " not found");
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	    AccessEntity entity = mapper.convertValue(accessRequest, AccessEntity.class);
	    accessService.save(entity);
	    return new ResponseEntity<>(accessRequest, HttpStatus.OK);
	} else {
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
    }

    /**
     * Get All Access
     *
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<AccessVO>> getAll() {
	List<AccessVO> response = new ArrayList<>();
	try {
	    List<AccessEntity> source = accessService.findAll();
	    if (source.isEmpty()) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);// You many decide to return
		// HttpStatus.NOT_FOUND
	    } else {
		for (AccessEntity roleEntity : source) {
		    response.add(mapper.convertValue(roleEntity, AccessVO.class));
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	} catch (IllegalArgumentException ex) {
	    logger.error(String.format("Error: %s", ex.getMessage()));
	}
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);// You many decide to return HttpStatus.NOT_FOUND
    }

    /**
     * Search Access By ID
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<AccessVO> findById(@PathVariable(value = "id") long id) {
	try {
	    AccessEntity source = accessService.findById(id).get();
	    if (source != null) {
		return new ResponseEntity<>(mapper.convertValue(source, AccessVO.class), HttpStatus.OK);
	    } else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	} catch (IllegalArgumentException ex) {
	    logger.error(String.format("Error: %s", ex.getMessage()));
	}
	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Delete Access By ID
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<AccessVO> delete(@PathVariable(value = "id") long id) {
	try {
	    AccessEntity source = accessService.findById(id).get();
	    if (source != null) {
		accessService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	    } else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	} catch (Exception ex) {
	    logger.error(String.format("Error: %s", ex.getMessage()));
	}
	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Remove All Access
     *
     * @return
     */
    @RequestMapping(value = "/delAll", method = RequestMethod.DELETE)
    public ResponseEntity<AccessVO> deleteAllAccess() {
	logger.info("Deleting All Access");
	accessService.deleteAll();
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
