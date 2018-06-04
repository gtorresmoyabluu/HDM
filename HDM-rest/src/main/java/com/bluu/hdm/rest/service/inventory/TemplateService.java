/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.service.inventory;

import com.bluu.hdm.rest.dao.interfaces.inventory.ITemplateDAO;
import com.bluu.hdm.rest.entity.TemplateEntity;
import com.bluu.hdm.rest.vo.inventory.TemplateHDMVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Marco Valdés
 */
@RestController
@RequestMapping("/v1/templates")
public class TemplateService {

    private static Logger logger = LogManager.getLogger(TemplateService.class.getName());

    @Autowired
    ITemplateDAO templateService;

    @Autowired
    ObjectMapper mapper;

    /**
     * Get Find Template By Name
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "/findByname/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<TemplateHDMVO> findTemplateByname(@PathVariable(value = "name") String name) {
        try {
            TemplateEntity ret = templateService.findTemplateByname(name).orElse(null);
            if (ret != null) {
                TemplateHDMVO template = mapper.convertValue(ret, TemplateHDMVO.class);
                template.setFile(ret.getName());
                return new ResponseEntity<>(template, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException ex) {
            logger.error(String.format("Error: %s", ex.getMessage()));
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Get All Templates
     *
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<TemplateHDMVO>> getAll() {
        List<TemplateHDMVO> response = new ArrayList<>();
        try {
            List<TemplateEntity> source = templateService.findAll();
            if (source.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);// You many decide to return
                // HttpStatus.NOT_FOUND
            } else {
                source.forEach((TemplateEntity entity) -> {
                    TemplateHDMVO tmp = mapper.convertValue(entity, TemplateHDMVO.class);
                    tmp.setFile(entity.getName());
                    response.add(tmp);
                });
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (IllegalArgumentException ex) {
            logger.error(String.format("Error: %s", ex.getMessage()));
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);// You many decide to return HttpStatus.NOT_FOUND
    }
}
