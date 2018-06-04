/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.service.administration;

import com.bluu.hdm.rest.dao.interfaces.administration.ILicenseDAO;
import com.bluu.hdm.rest.entity.ClientEntity;
import com.bluu.hdm.rest.entity.LicenseEntity;
import com.bluu.hdm.rest.service.generic.IService;
import com.bluu.hdm.rest.vo.administration.ClientVO;
import com.bluu.hdm.rest.vo.administration.LicenseVO;
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
@RequestMapping("/v1/license")
public class LicenseService implements IService<LicenseVO, Long> {

    private static final Logger logger = LogManager.getLogger(LicenseService.class.getName());
    @Autowired
    ILicenseDAO licService;

    @Autowired
    ObjectMapper mapper;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @Override
    public ResponseEntity<Void> save(@RequestBody LicenseVO oRequest, UriComponentsBuilder ucBuilder) {
        if (oRequest.getId() != null) {
            if (licService.isExist(oRequest.getId())) {
                logger.error("Object with code " + oRequest.getCode() + " already exist");
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        }
        LicenseVO lic = new LicenseVO(oRequest.getCode());
        lic.doCheck(oRequest.getCpeThreshold());
        LicenseEntity entity = mapper.convertValue(lic, LicenseEntity.class);
        entity.setApp(lic.getApp().getNameAsString());
        entity.setAsociatedIp(lic.getIpAddress());
        entity.setIdClient(mapper.convertValue(oRequest.getIdClient(), ClientEntity.class));
        entity.setExpirationDate(lic.getExpDate());
        licService.save(entity);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/find/{id}").buildAndExpand(entity.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/upd", method = RequestMethod.PUT)
    @Override
    public ResponseEntity<LicenseVO> update(@RequestBody LicenseVO oRequest) {
        if (!licService.isExist(oRequest.getId())) {
            logger.error("Object with id " + oRequest.getId() + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        LicenseVO lic = new LicenseVO(oRequest.getCode());
        lic.doCheck(oRequest.getCpeThreshold());
        LicenseEntity entity = mapper.convertValue(lic, LicenseEntity.class);
        entity.setId(oRequest.getId());
        entity.setApp(lic.getApp().getNameAsString());
        entity.setAsociatedIp(lic.getIpAddress());
        entity.setIdClient(mapper.convertValue(oRequest.getIdClient(), ClientEntity.class));
        entity.setExpirationDate(lic.getExpDate());
        licService.save(entity);
        return new ResponseEntity<>(lic, HttpStatus.OK);
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
    @Override
    public ResponseEntity<LicenseVO> delete(@PathVariable("id") Long id) {
        try {
            LicenseEntity source = licService.findById(id).get();
            if (source != null) {
                licService.deleteById(id);
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
    public ResponseEntity<LicenseVO> deleteAll() {
        licService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public ResponseEntity<LicenseVO> findById(@PathVariable("id") Long id) {
        try {
            LicenseEntity source = licService.findById(id).get();
            if (source != null) {
                return new ResponseEntity<>(mapper.convertValue(source, LicenseVO.class), HttpStatus.OK);
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
    public ResponseEntity<List<LicenseVO>> getAll() {
        List<LicenseVO> response = new ArrayList<>();
        try {
            List<LicenseEntity> source = licService.findAll();
            if (source.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);// You many decide to return
                // HttpStatus.NOT_FOUND
            } else {
                source.forEach((entity) -> {
                    response.add(mapper.convertValue(entity, LicenseVO.class));
                });
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (IllegalArgumentException ex) {
            logger.error(String.format("Error: %s", ex.getMessage()));
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);// You many decide to return HttpStatus.NOT_FOUND
    }

    @RequestMapping(value = "/check/{code}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<LicenseVO> findByCode(@PathVariable("code") String code) {
        try {
            LicenseEntity source = licService.findBycode(code);
            if (source != null) {
                LicenseVO lic = new LicenseVO(source.getCode());
                lic.setId(source.getId());
                lic.doCheck(source.getCpeThreshold());
                lic.setIdClient(mapper.convertValue(source.getIdClient(), ClientVO.class));
                lic.setBlocked(source.getBlocked());
                return new ResponseEntity<>(mapper.convertValue(lic, LicenseVO.class), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException ex) {
            logger.error(String.format("Error: %s", ex.getMessage()));
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
