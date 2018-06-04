/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.service;

import com.bluu.hdm.rest.service.administration.RoleService;
import com.bluu.hdm.rest.dao.interfaces.IMessageDAO;
import com.bluu.hdm.rest.entity.MessageEntity;
import com.bluu.hdm.rest.vo.MessageVO;
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
@RequestMapping("/v1/messages")
public class MessageService {

    private static Logger logger = LogManager.getLogger(RoleService.class.getName());
    @Autowired
    IMessageDAO messageService;

    @Autowired
    ObjectMapper mapper;

    private static PageRequest pageRequest;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Void> save(@RequestBody MessageVO requestMessage, UriComponentsBuilder ucBuilder) {
	if (requestMessage.getId() != null) {
	    if (messageService.isExist(requestMessage.getId())) {
		logger.error("A Data with name " + requestMessage.getDescription() + " already exist");
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	    }
	}
	MessageEntity entity = mapper.convertValue(requestMessage, MessageEntity.class);
	messageService.save(entity);

	HttpHeaders headers = new HttpHeaders();
	headers.setLocation(ucBuilder.path("/find/{id}").buildAndExpand(entity.getId()).toUri());
	return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/upd/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<MessageVO> updateUser(@PathVariable("id") long id, @RequestBody MessageVO msgRequest) {
	System.out.println("Updating Message " + id);
	if (messageService.isExist(msgRequest.getId())) {
	    logger.error("Message with id " + id + " not found");
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	MessageEntity entity = mapper.convertValue(msgRequest, MessageEntity.class);
	messageService.save(entity);
	return new ResponseEntity<>(msgRequest, HttpStatus.OK);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<MessageVO>> getAll() {
	List<MessageVO> response = new ArrayList<>();
	try {
	    List<MessageEntity> source = messageService.findAll();
	    if (source.isEmpty()) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);// You many decide to return
		// HttpStatus.NOT_FOUND
	    } else {
		source.forEach((messageEntity) -> {
		    response.add(mapper.convertValue(messageEntity, MessageVO.class));
		});
		return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	} catch (IllegalArgumentException ex) {
	    logger.error(String.format("Error: %s", ex.getMessage()));
	}
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);// You many decide to return HttpStatus.NOT_FOUND
    }

    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<MessageVO> findById(@PathVariable(value = "id") long id) {
	try {
	    MessageEntity source = messageService.findById(id).get();
	    if (source != null) {
		return new ResponseEntity<>(mapper.convertValue(source, MessageVO.class), HttpStatus.OK);
	    } else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	} catch (IllegalArgumentException ex) {
	    logger.error(String.format("Error: %s", ex.getMessage()));
	}
	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<MessageVO> delete(@PathVariable(value = "id") long id) {
	try {
	    MessageEntity source = messageService.findById(id).get();
	    if (source != null) {
		messageService.deleteById(id);
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
    public ResponseEntity<MessageVO> deleteAllRoles() {
	logger.info("Deleting All Messages");

	messageService.deleteAll();
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/messages/{locale}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<MessageVO>> getUsersByRolID(@PathVariable(value = "locale") String locale) {
	List<MessageVO> response = new ArrayList<>();
	try {
	    List<MessageEntity> source = messageService.getMessagesBylocale(locale);
	    if (source.isEmpty()) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);// You many decide to return
		// HttpStatus.NOT_FOUND
	    } else {
		source.stream().map((entity) -> mapper.convertValue(entity, MessageVO.class)).forEachOrdered((message) -> {
		    response.add(message);
		});
		return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	} catch (IllegalArgumentException ex) {
	    logger.error(String.format("Error: %s", ex.getMessage()));
	}
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);// You many decide to return HttpStatus.NOT_FOUND
    }
}
