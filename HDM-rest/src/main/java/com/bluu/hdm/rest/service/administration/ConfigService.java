/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.service.administration;

import com.bluu.hdm.rest.dao.interfaces.administration.IClientDAO;
import com.bluu.hdm.rest.dao.interfaces.administration.IConfigurationDAO;
import com.bluu.hdm.rest.entity.ConfigurationEntity;
import com.bluu.hdm.rest.service.generic.IService;
import com.bluu.hdm.rest.vo.administration.ClientVO;
import com.bluu.hdm.rest.vo.administration.ConfigurationVO;
import com.bluu.hdm.rest.vo.DataListVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.lang.reflect.Constructor;
import java.sql.Date;
import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.ResponseBody;
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
    IClientDAO clientService;

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
                logger.error("A with name " + oRequest.getDataKey() + " already exist");
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        }
        ConfigurationEntity entity = mapper.convertValue(oRequest, ConfigurationEntity.class);
        entity.setTtCreation(Date.valueOf(LocalDate.now()));
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
    @RequestMapping(value = "/upd", method = RequestMethod.PUT)
    @Override
    public ResponseEntity<ConfigurationVO> update(@RequestBody ConfigurationVO oRequest) {
        System.out.println("Updating " + oRequest.getId());
        if (!configService.isExist(oRequest.getId())) {
            logger.error("Object with id " + oRequest.getId() + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ConfigurationEntity or = configService.findById(oRequest.getId()).get();
        ConfigurationEntity entity = mapper.convertValue(oRequest, ConfigurationEntity.class);
        entity.setTtModification(Date.valueOf(LocalDate.now()));
        entity.setId_category(or.getId_category());
        entity.setFieldValues(or.getFieldValues());

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
                ConfigurationVO conf = new ConfigurationVO(source);
                conf.setClient(mapper.convertValue(source.getClient(), ClientVO.class));
                return new ResponseEntity<>(conf, HttpStatus.OK);
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
     * @param client
     * @param id
     * @return
     */
    @RequestMapping(value = "/all/{client}/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ConfigurationVO>> getAll(@PathVariable("client") Long client, @PathVariable("id") Long id) {
        List<ConfigurationVO> response = new ArrayList<>();
        try {
            List<ConfigurationEntity> source = configService.getConfigByClient(id, client);
            if (source == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);// You many decide to return
                // HttpStatus.NOT_FOUND
            } else {
                for (ConfigurationEntity entity : source) {
                    ConfigurationVO cnf = new ConfigurationVO(entity);
                    response.add(cnf);
                }
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (IllegalArgumentException ex) {
            logger.error(String.format("Error: %s", ex.getMessage()));
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);// You many decide to return HttpStatus.NOT_FOUND
    }

    @RequestMapping(value = "/all/{client}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public DataListVO getSizeConfig(@PathVariable("client") Long client) {
        List<ConfigurationEntity> source = new ArrayList<>();
        try {
            source = configService.findByIdClient(client);
        } catch (IllegalArgumentException ex) {
            logger.error(String.format("Error: %s", ex.getMessage()));
        }
        return new DataListVO(source.size());
    }

    @RequestMapping(value = "/init", method = RequestMethod.POST)
    public ResponseEntity<Void> initConfig(@RequestBody ClientVO oRequest, UriComponentsBuilder ucBuilder) {
        if (oRequest.getId() != null) {
            if (!clientService.isExist(oRequest.getId())) {
                logger.error("A with name " + oRequest.getName() + " not exist");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        configService.setInitConfigClient(oRequest.getId());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private List<Object> castToList(Class<?> viewClass, List<Object> objList) {
        List<Object> result = new ArrayList<>();
        try {
            Class<?> modelClass = getModelClass(viewClass);
            if (objList == null) {
                result = new ArrayList<>(0);
            } else {
                try {// Se ejecuta la consulta
                    List<Object> list = objList;

                    // Se parsea la respuesta a objetos POJO
                    for (Object model : list) {
                        Constructor<?> constructor = viewClass.getDeclaredConstructor(modelClass);
                        constructor.setAccessible(true);
                        Object modelInt = mapper.convertValue(model, getModelClass(viewClass));
                        result.add(constructor.newInstance(modelInt));
                    }
                } catch (ReflectiveOperationException | IllegalArgumentException | SecurityException ex) {

                    logger.error(String.format("Error: %s", ex.getMessage()));
                    result = new ArrayList<>(0);
                }
            }
        } catch (Exception ex) {
            result = new ArrayList<>(0);
            logger.error(String.format("Error: %s", ex.getMessage()));
        }
        return result;
    }

    public static Class<?> getModelClass(Class<?> viewClass) {
        try {
            return ClassEnum.valueOf(viewClass.getSimpleName()).getClazz();
        } catch (Exception ex) {
            logger.error(String.format("Error: %s", ex.getMessage()));
            return null;
        }
    }
}

enum ClassEnum {
    ConfigurationVO(ConfigurationVO.class);

    Class<?> clazz;

    ClassEnum(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Class<?> getClazz() {
        return clazz;
    }
}
