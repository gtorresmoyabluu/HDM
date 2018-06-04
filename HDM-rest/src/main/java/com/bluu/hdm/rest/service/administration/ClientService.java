/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.service.administration;

import com.bluu.hdm.rest.dao.interfaces.administration.IClientDAO;
import com.bluu.hdm.rest.entity.ClientEntity;
import com.bluu.hdm.rest.service.generic.IService;
import com.bluu.hdm.rest.vo.administration.ClientVO;
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
@RequestMapping("/v1/clients")
public class ClientService implements IService<ClientVO, Long> {

    private static final Logger logger = LogManager.getLogger(ClientService.class.getName());
    @Autowired
    IClientDAO clientService;

    @Autowired
    ObjectMapper mapper;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @Override
    public ResponseEntity<Void> save(@RequestBody ClientVO oRequest, UriComponentsBuilder ucBuilder) {
        if (oRequest.getId() != null) {
            if (clientService.isExist(oRequest.getId())) {
                logger.error("Object with code " + oRequest.getId()+ " already exist");
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        }
        
        ClientEntity entity = mapper.convertValue(oRequest, ClientEntity.class);
        clientService.save(entity);

        HttpHeaders headers = new HttpHeaders();
	headers.setLocation(ucBuilder.path("/find/{id}").buildAndExpand(entity.getId()).toUri());
	return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/upd", method = RequestMethod.PUT)
    @Override
    public ResponseEntity<ClientVO> update(@RequestBody ClientVO oRequest) {
        if (!clientService.isExist(oRequest.getId())) {
	    logger.error("Object with id " + oRequest.getId() + " not found");
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
        
	ClientEntity entity = mapper.convertValue(oRequest, ClientEntity.class);
	clientService.save(entity);
	return new ResponseEntity<>(oRequest, HttpStatus.OK);
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
    @Override
    public ResponseEntity<ClientVO> delete(@PathVariable("id") Long id) {
        try {
	    ClientEntity source = clientService.findById(id).get();
	    if (source != null) {
		clientService.deleteById(id);
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
    public ResponseEntity<ClientVO> deleteAll() {
        clientService.deleteAll();
	return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public ResponseEntity<ClientVO> findById(@PathVariable(value = "id") Long id) {
        try {
	    ClientEntity source = clientService.findById(id).get();
	    if (source != null) {
		return new ResponseEntity<>(mapper.convertValue(source, ClientVO.class), HttpStatus.OK);
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
    public ResponseEntity<List<ClientVO>> getAll() {
        List<ClientVO> response = new ArrayList<>();
	try {
	    List<ClientEntity> source = clientService.findAll();
	    if (source.isEmpty()) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);// You many decide to return
		// HttpStatus.NOT_FOUND
	    } else {
		source.forEach((entity) -> {
		    response.add(mapper.convertValue(entity, ClientVO.class));
		});
		return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	} catch (IllegalArgumentException ex) {
	    logger.error(String.format("Error: %s", ex.getMessage()));
	}
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);// You many decide to return HttpStatus.NOT_FOUND
    }
    
    @RequestMapping(value = "/getClientName/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ClientVO> getClientByname(@PathVariable(value = "name") String name) {
	ClientEntity entity = clientService.getClientByname(name.toLowerCase());
	if (entity != null) {
	    ClientVO response = mapper.convertValue(entity, ClientVO.class);
	    return new ResponseEntity<>(response, HttpStatus.OK);
	} else {
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
    }
}
