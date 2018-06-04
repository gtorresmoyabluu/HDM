/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.vo.administration;

import com.bluu.hdm.rest.entity.ConfigurationEntity;
import com.bluu.hdm.rest.util.CustomDateDeserializer;
import com.bluu.hdm.rest.util.CustomDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Gonzalo Torres
 */
public class ConfigurationVO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @JsonIgnore
    @Autowired
    ObjectMapper mapper = new ObjectMapper();
    
    private String fieldType;
    private LinkedHashMap<String, String> fieldValues;
    private Long id;
    private String dataKey;
    private String listType;
    private List<String> listValues;
    private String strFieldValues;

    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date ttCreation;
    
    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date ttModification;
    
    private String dataValue;
    
    private ClientVO client;

    // Values used by the getListValues method through reflection
    @SuppressWarnings("unused")
    @JsonIgnore
    private final String[] wifi = {"wifi", "always"};
    @SuppressWarnings("unused")
    @JsonIgnore
    private final String[] database = {"mysql", "oracle", "postgresql"};
    @SuppressWarnings("unused")
    @JsonIgnore
    private final String[] icmp = {"java", "linux", "solaris", "windows", "macosx"};
    @SuppressWarnings("unused")
    @JsonIgnore
    private final String[] int_ext = {"true", "false"};
    @SuppressWarnings("unused")
    @JsonIgnore
    private final String[] language = {"es_ES", "en_EN"};

    public ConfigurationVO() {
    }
    
    public ConfigurationVO(Object[] conf) {
        id = ((Number) (conf[0])).longValue();
        dataKey = (String)conf[1];
        dataValue = (String)conf[2];
        fieldType = (String)conf[3];
        strFieldValues = (String)conf[4];
        ttCreation = (Date)conf[5];
        ttModification = (Date)conf[6];
        client = new ClientVO(((Number) (conf[8])).longValue());

        if (strFieldValues != null && strFieldValues.length() > 1) {
            if (strFieldValues.contains(":")) {
                fieldValues = new LinkedHashMap<>();
                final String[] arr = strFieldValues.split(";");
                for (final String element : arr) {
                    final String[] arr_ = element.split(":");
                    fieldValues.put(arr_[1], arr_[0]);
                }
            } else {
                getListValues(strFieldValues);
            }
        }
    }

    public ConfigurationVO(ConfigurationEntity conf) {
        id = conf.getId();
        dataKey = conf.getDataKey();
        dataValue = conf.getDataValue();
        fieldType = conf.getFieldType();
        strFieldValues = conf.getFieldValues();
        ttCreation = conf.getTtCreation();
        ttModification = conf.getTtModification();
        client = mapper.convertValue(conf.getClient(), ClientVO.class);

        if (conf.getFieldValues() != null && conf.getFieldValues().length() > 1) {
            if (conf.getFieldValues().contains(":")) {
                fieldValues = new LinkedHashMap<>();
                final String[] arr = conf.getFieldValues().split(";");
                for (final String element : arr) {
                    final String[] arr_ = element.split(":");
                    fieldValues.put(arr_[1], arr_[0]);
                }
            } else {
                getListValues(conf.getFieldValues());
            }
        }
    }

    public void getListValues(String name) {
        listType = name;
        String[] data = null;
        try {
            data = (String[]) this.getClass().getDeclaredField(name).get(this);
        } catch (final IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException e) {
            data = new String[0];
        }
        listValues = new ArrayList<>(Arrays.asList(data));
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public LinkedHashMap<String, String> getFieldValues() {
        return fieldValues;
    }

    public void setFieldValues(LinkedHashMap<String, String> fieldValues) {
        this.fieldValues = fieldValues;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getListType() {
        return listType;
    }

    public void setListType(String listType) {
        this.listType = listType;
    }

    public List<String> getListValues() {
        return listValues;
    }

    public void setListValues(List<String> listValues) {
        this.listValues = listValues;
    }

    public String getStrFieldValues() {
        return strFieldValues;
    }

    public void setStrFieldValues(String strFieldValues) {
        this.strFieldValues = strFieldValues;
    }

    public Date getTtCreation() {
        return ttCreation;
    }

    public void setTtCreation(Date ttCreation) {
        this.ttCreation = ttCreation;
    }

    public Date getTtModification() {
        return ttModification;
    }

    public void setTtModification(Date ttModification) {
        this.ttModification = ttModification;
    }

    public String getDataKey() {
        return dataKey;
    }

    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
    }

    public String getDataValue() {
        return dataValue;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }

    public ClientVO getClient() {
        return client;
    }

    public void setClient(ClientVO client) {
        this.client = client;
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

}
