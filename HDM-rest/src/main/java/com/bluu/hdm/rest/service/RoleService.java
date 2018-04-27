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
import com.bluu.hdm.rest.entity.UserEntity;
import com.bluu.hdm.rest.vo.AccessVO;
import com.bluu.hdm.rest.vo.RoleVO;
import com.bluu.hdm.rest.vo.UserVO;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Comparator;

/**
 *
 * @author Gonzalo Torres
 */
@RestController
@RequestMapping("/v1/roles")
public class RoleService {

    private static Logger logger = LogManager.getLogger(RoleService.class.getName());
    @Autowired
    IRoleDAO roleService;

    @Autowired
    IAccessDAO accessService;

    @Autowired
    ObjectMapper mapper;

    /**
     * Add new Role
     *
     * @param roleRequest
     * @param ucBuilder
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Void> save(@RequestBody RoleVO roleRequest, UriComponentsBuilder ucBuilder) {
	if (roleRequest.getId() != null) {
	    if (roleService.isExist(roleRequest.getId())) {
		logger.error("A role with name " + roleRequest.getName() + " already exist");
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	    }
	}
	RoleEntity entity = mapper.convertValue(roleRequest, RoleEntity.class);
	roleService.save(entity);

	HttpHeaders headers = new HttpHeaders();
	headers.setLocation(ucBuilder.path("/find/{id}").buildAndExpand(entity.getId()).toUri());
	return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    /**
     * Update Role
     *
     * @param id
     * @param roleRequest
     * @return
     */
    @RequestMapping(value = "/upd/{id}", method = RequestMethod.PUT)
    public ResponseEntity<RoleVO> updateUser(@PathVariable("id") long id, @RequestBody RoleVO roleRequest) {
	System.out.println("Updating User " + id);
	if (!roleService.isExist(roleRequest.getId())) {
	    logger.error("Role with id " + id + " not found");
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	RoleEntity entity = mapper.convertValue(roleRequest, RoleEntity.class);
	roleService.save(entity);
	return new ResponseEntity<>(roleRequest, HttpStatus.OK);
    }

    /**
     * Get All Role
     *
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<RoleVO>> getAll() {
	List<RoleVO> response = new ArrayList<>();
	try {
	    List<RoleEntity> source = roleService.findAll();
	    if (source.isEmpty()) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);// You many decide to return
		// HttpStatus.NOT_FOUND
	    } else {
		source.forEach((roleEntity) -> {
		    response.add(mapper.convertValue(roleEntity, RoleVO.class));
		});
		return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	} catch (IllegalArgumentException ex) {
	    logger.error(String.format("Error: %s", ex.getMessage()));
	}
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);// You many decide to return HttpStatus.NOT_FOUND
    }

    /**
     * Find Role By Id
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<RoleVO> findById(@PathVariable(value = "id") long id) {
	try {
	    RoleEntity source = roleService.findById(id).get();
	    if (source != null) {
		return new ResponseEntity<>(mapper.convertValue(source, RoleVO.class), HttpStatus.OK);
	    } else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	} catch (IllegalArgumentException ex) {
	    logger.error(String.format("Error: %s", ex.getMessage()));
	}
	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Remove Role By Id
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<RoleVO> delete(@PathVariable(value = "id") long id) {
	try {
	    RoleEntity source = roleService.findById(id).get();
	    if (source != null) {
		roleService.deleteById(id);
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
     * Remove All Roles
     *
     * @return
     */
    @RequestMapping(value = "/delAll", method = RequestMethod.DELETE)
    public ResponseEntity<RoleVO> deleteAllRoles() {
	roleService.deleteAll();
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Return All Acces to Role
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/access/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<AccessVO>> getAccessByRolID(@PathVariable(value = "id") long id) {
	List<AccessVO> response = new ArrayList<>();
	List<AccessVO> subMenu = new ArrayList<>();
	try {
	    List<AccessEntity> parent = accessService.getAccessByIdRol(id);
	    if (parent.isEmpty()) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);// You many decide to return
		// HttpStatus.NOT_FOUND
	    } else {
		for (AccessEntity entityParent : parent) {
		    AccessVO accessParent = mapper.convertValue(entityParent, AccessVO.class);
		    List<AccessEntity> child = accessService.getAccessChildByIdRol(accessParent.getId(), id);
		    for (AccessEntity entityChild : child) {
			subMenu.add(mapper.convertValue(entityChild, AccessVO.class));
		    }
		    if (subMenu.size() > 0) {
			accessParent.setChild(subMenu);
			subMenu = new ArrayList<>();
		    }
		    response.add(accessParent);
		}
		response.sort(Comparator.comparingLong(AccessVO::getId));
		return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	} catch (IllegalArgumentException ex) {
	    logger.error(String.format("Error: %s", ex.getMessage()));
	}
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);// You many decide to return HttpStatus.NOT_FOUND
    }

    /**
     * Return All Users in Role
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<UserVO>> getUsersByRolID(@PathVariable(value = "id") long id) {
	List<UserVO> response = new ArrayList<>();
	try {
	    List<UserEntity> source = roleService.findAllByRoleID(id);
	    if (source.isEmpty()) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);// You many decide to return
		// HttpStatus.NOT_FOUND
	    } else {
		source.stream().map((entity) -> mapper.convertValue(entity, UserVO.class)).forEachOrdered((user) -> {
		    response.add(user);
		});
		return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	} catch (IllegalArgumentException ex) {
	    logger.error(String.format("Error: %s", ex.getMessage()));
	}
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);// You many decide to return HttpStatus.NOT_FOUND
    }

    @RequestMapping(value = "/getRoleName/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<RoleVO> findByName(@PathVariable(value = "name") String name) {
	try {
	    RoleEntity source = roleService.findByName(name);
	    if (source != null) {
		return new ResponseEntity<>(mapper.convertValue(source, RoleVO.class), HttpStatus.OK);
	    } else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	} catch (IllegalArgumentException ex) {
	    logger.error(String.format("Error: %s", ex.getMessage()));
	}
	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{id}/parent", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<AccessVO>> getAccessParent(@PathVariable(value = "id") long id) {
	List<AccessVO> response = new ArrayList<>();
	try {
	    List<AccessEntity> parent = accessService.getAccessParent(id);
	    if (parent.isEmpty()) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);// You many decide to return
		// HttpStatus.NOT_FOUND
	    } else {
		parent.stream().map((entityParent) -> mapper.convertValue(entityParent, AccessVO.class)).forEachOrdered((accessParent) -> {
		    response.add(accessParent);
		});
		response.sort(Comparator.comparingLong(AccessVO::getId));
		return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	} catch (IllegalArgumentException ex) {
	    logger.error(String.format("Error: %s", ex.getMessage()));
	}
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);// You many decide to return HttpStatus.NOT_FOUND
    }

    @RequestMapping(value = "/{id}/parent/{parent}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<AccessVO>> getAccessChild(@PathVariable(value = "id") long id, @PathVariable(value = "parent") long parent) {
	List<AccessVO> response = new ArrayList<>();
	try {
	    List<AccessEntity> childs = accessService.getAccessChild(parent, id);
	    if (childs.isEmpty()) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);// You many decide to return
		// HttpStatus.NOT_FOUND
	    } else {
		childs.stream().map((entityChild) -> mapper.convertValue(entityChild, AccessVO.class)).forEachOrdered((child) -> {
		    response.add(child);
		});
		response.sort(Comparator.comparingLong(AccessVO::getId));
		return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	} catch (IllegalArgumentException ex) {
	    logger.error(String.format("Error: %s", ex.getMessage()));
	}
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);// You many decide to return HttpStatus.NOT_FOUND
    }

    @RequestMapping(value = "/accessRol", method = RequestMethod.POST)
    public ResponseEntity<Void> addAccessRol(@RequestBody AccessVO accessRequest, UriComponentsBuilder ucBuilder) {
	if (accessRequest.getIdRole() == null && accessRequest.getId() == null && accessRequest.getParent() == null) {
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	Long idRole = accessRequest.getIdRole();
	Long child = (accessRequest.getId() == null ? 0 : accessRequest.getId());
	Long parent = (accessRequest.getParent() == null ? 0 : accessRequest.getParent());

	accessService.setAccessToRol(idRole, child, parent);

	HttpHeaders headers = new HttpHeaders();
	headers.setLocation(ucBuilder.path("/{id}/parent").buildAndExpand(idRole).toUri());
	return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}
