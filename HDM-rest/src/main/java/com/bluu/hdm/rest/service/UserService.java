/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.service;

import com.bluu.hdm.rest.dao.interfaces.IRoleDAO;
import com.bluu.hdm.rest.dao.interfaces.IUserDAO;
import com.bluu.hdm.rest.entity.UserEntity;
import com.bluu.hdm.rest.vo.UserListVO;
import com.bluu.hdm.rest.vo.UserVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Gonzalo Torres
 */
@RestController
@RequestMapping("/v1/users")
public class UserService {

    private static Logger logger = LogManager.getLogger(RoleService.class.getName());
    @Autowired
    IUserDAO userService;

    @Autowired
    IRoleDAO roleService;

    @Autowired
    ObjectMapper mapper;

    /**
     * Add new User
     *
     * @param userRequest
     * @param ucBuilder
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Void> save(@RequestBody UserVO userRequest, UriComponentsBuilder ucBuilder) {
	if (userRequest.getId() != null) {
	    if (userService.isExist(userRequest.getId())) {
		logger.error("A User with name " + userRequest.getFirstname() + " already exist");
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	    }
	}
	UserEntity entity = mapper.convertValue(userRequest, UserEntity.class);
	entity.setCreationDate(java.sql.Date.valueOf(LocalDate.now()));
	entity.setHighDate(java.sql.Date.valueOf(LocalDate.now()));
	userService.save(entity);

	HttpHeaders headers = new HttpHeaders();
	headers.setLocation(ucBuilder.path("/find/{id}").buildAndExpand(entity.getId()).toUri());
	return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    /**
     * Update User
     *
     * @param id
     * @param userRequest
     * @return
     */
    @RequestMapping(value = "/upd/{id}", method = RequestMethod.PUT)
    public ResponseEntity<UserVO> updateUser(@PathVariable("id") long id, @RequestBody UserVO userRequest) {
	System.out.println("Updating User " + id);
	if (!userService.isExist(userRequest.getId())) {
	    logger.error("User with id " + id + " not found");
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	UserEntity entity = mapper.convertValue(userRequest, UserEntity.class);
	entity.setHighDate(java.sql.Date.valueOf(LocalDate.now()));
	userService.save(entity);
	return new ResponseEntity<>(userRequest, HttpStatus.OK);
    }

    /**
     * Get All Users
     *
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserListVO> getAll() {
	UserListVO response = new UserListVO();
	try {
	    List<UserEntity> source = userService.findAll();
	    if (source.isEmpty()) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);// You many decide to return
		// HttpStatus.NOT_FOUND
	    } else {
		source.forEach((UserEntity userEntity) -> {
		    response.add(mapper.convertValue(userEntity, UserVO.class));
		});
		return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	} catch (IllegalArgumentException ex) {
	    logger.error(String.format("Error: %s", ex.getMessage()));
	}
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);// You many decide to return HttpStatus.NOT_FOUND
    }

    /**
     * Find User By Id
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserVO> findById(@PathVariable(value = "id") long id) {
	try {
	    UserEntity source = userService.findById(id).get();
	    if (source != null) {
		return new ResponseEntity<>(mapper.convertValue(source, UserVO.class), HttpStatus.OK);
	    } else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	} catch (IllegalArgumentException ex) {
	    logger.error(String.format("Error: %s", ex.getMessage()));
	}
	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Remove User By Id
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<UserVO> delete(@PathVariable(value = "id") long id) {
	try {
	    UserEntity source = userService.findById(id).get();
	    if (source != null) {
		userService.deleteById(id);
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
     * Remove All Users
     *
     * @return
     */
    @RequestMapping(value = "/delAll", method = RequestMethod.DELETE)
    public ResponseEntity<UserVO> deleteAllRoles() {
	logger.info("Deleting All Roles");

	userService.deleteAll();
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/get/{firstname}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserVO> getUserByfirstname(@PathVariable(value = "firstname") String firstname) {
	UserEntity user = userService.getUserByfirstname(firstname);
	if (user != null) {
	    UserVO response = mapper.convertValue(user, UserVO.class);
	    return new ResponseEntity<>(response, HttpStatus.OK);
	} else {
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
    }

    @RequestMapping(value = "/getUserName/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserVO> getUserByusername(@PathVariable(value = "username") String username) {
	UserEntity user = userService.getUserByusername(username.toLowerCase());
	if (user != null) {
	    UserVO response = mapper.convertValue(user, UserVO.class);
	    return new ResponseEntity<>(response, HttpStatus.OK);
	} else {
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
    }
}
