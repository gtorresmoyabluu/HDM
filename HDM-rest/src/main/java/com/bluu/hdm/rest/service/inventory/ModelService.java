/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.service.inventory;

import com.bluu.hdm.rest.dao.interfaces.inventory.IModelDAO;
import com.bluu.hdm.rest.entity.ModelEntity;
import com.bluu.hdm.rest.vo.inventory.ModelVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
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
@RequestMapping("/v1/models")
public class ModelService {

    private static Logger logger = LogManager.getLogger(ModelService.class.getName());
    @Autowired
    IModelDAO modelService;

    @Autowired
    ObjectMapper mapper;

    /**
     * Add Model
     *
     * @param mRequest
     * @param ucBuilder
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Void> save(@RequestBody ModelVO mRequest, UriComponentsBuilder ucBuilder) {
        if (mRequest.getId() != null) {
            if (modelService.isExist(mRequest.getId())) {
                logger.error("A Model with name " + mRequest.getName() + " already exist");
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        }

        ModelEntity entity = mapper.convertValue(mRequest, ModelEntity.class);
        modelService.save(entity);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/find/{id}").buildAndExpand(entity.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    /**
     * Update Model
     *
     * @param mRequest
     * @return
     */
    @RequestMapping(value = "/upd", method = RequestMethod.PUT)
    public ResponseEntity<ModelVO> update(@RequestBody ModelVO mRequest) {
        System.out.println("Updating Model " + mRequest.getId());
        if (!modelService.isExist(mRequest.getId())) {
            logger.error("Model with id " + mRequest.getId() + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ModelEntity entity = mapper.convertValue(mRequest, ModelEntity.class);
        modelService.save(entity);
        return new ResponseEntity<>(mRequest, HttpStatus.OK);
    }

    @RequestMapping(value = "/all/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ModelVO>> getAll(@PathVariable(value = "id") long id) {
        List<ModelVO> response = new ArrayList<>();
        try {
            List<ModelEntity> source = modelService.findAll()
                    .stream()
                    .filter(m -> m.getManufacturer().getId() == id)
                    .collect(Collectors.toList());
            if (source.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);// You many decide to return
                // HttpStatus.NOT_FOUND
            } else {
                source.forEach((roleEntity) -> {
                    response.add(mapper.convertValue(roleEntity, ModelVO.class));
                });
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (IllegalArgumentException ex) {
            logger.error(String.format("Error: %s", ex.getMessage()));
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);// You many decide to return HttpStatus.NOT_FOUND
    }

    @RequestMapping(value = "/allModels", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ModelVO>> getAllModels() {
        List<ModelVO> response = new ArrayList<>();
        try {
            List<ModelEntity> source = modelService.findAllByOrderByidManufacturerAsc();
            if (source.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);// You many decide to return
                // HttpStatus.NOT_FOUND
            } else {
                source.forEach((roleEntity) -> {
                    response.add(mapper.convertValue(roleEntity, ModelVO.class));
                });
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (IllegalArgumentException ex) {
            logger.error(String.format("Error: %s", ex.getMessage()));
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);// You many decide to return HttpStatus.NOT_FOUND
    }

    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelVO> findById(@PathVariable(value = "id") long id) {
        try {
            ModelEntity source = modelService.findById(id).get();
            if (source != null) {
                return new ResponseEntity<>(mapper.convertValue(source, ModelVO.class), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException ex) {
            logger.error(String.format("Error: %s", ex.getMessage()));
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/getModelName/{id}/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelVO> getModelName(@PathVariable(value = "id") Long id, @PathVariable(value = "name") String name) {
        try {
            ModelEntity source = modelService.findByManufacturerIdAndName(id, name).orElse(null);
            if (source != null) {
                return new ResponseEntity<>(mapper.convertValue(source, ModelVO.class), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException ex) {
            logger.error(String.format("Error: %s", ex.getMessage()));
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
