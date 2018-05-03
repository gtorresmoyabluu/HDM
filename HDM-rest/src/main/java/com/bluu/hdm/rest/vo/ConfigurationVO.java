/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.vo;

import com.bluu.hdm.rest.entity.ConfigurationEntity;
import com.bluu.hdm.rest.util.CryptoUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author Gonzalo Torres
 */
public final class ConfigurationVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String fieldType;
    private LinkedHashMap<String, String> fieldValues;
    private Long id;
    private String dataKey;
    private String listType;
    private List<String> listValues;
    private String strFieldValues;
    private Date ttCreation;
    private Date ttModification;
    private String dataValue;

    // Values used by the getListValues method through reflection
    @SuppressWarnings("unused")
    private final String[] wifi = {"wifi", "always"};
    @SuppressWarnings("unused")
    private final String[] database = {"mysql", "oracle", "postgresql"};
    @SuppressWarnings("unused")
    private final String[] icmp = {"java", "linux", "solaris", "windows", "macosx"};
    @SuppressWarnings("unused")
    private final String[] int_ext = {"true", "false"};
    @SuppressWarnings("unused")
    private final String[] language = {"es_ES", "en_EN"};

    public ConfigurationVO() {
    }

    public ConfigurationVO(String key, String value, String fieldType, String fieldValues) {
	this.dataKey = key;
	this.dataValue = value;
	this.fieldType = fieldType;
	strFieldValues = fieldValues;

	if (fieldValues != null && fieldValues.length() > 1) {
	    if (fieldValues.contains(":")) {
		this.fieldValues = new LinkedHashMap<>();
		final String[] arr = fieldValues.split(";");
		for (final String element : arr) {
		    final String[] arr_ = element.split(":");
		    this.fieldValues.put(arr_[1], arr_[0]);
		}
	    } else {
		getListValues(fieldValues);
	    }
	}
    }

    public ConfigurationVO(ConfigurationEntity conf) {

	id = conf.getId();
	dataKey = conf.getDataKey();
	dataValue = conf.getDataValue();
	fieldType = conf.getFieldType();

	if (dataKey.contains("Password") || dataKey.contains("Secret")) {
	    dataValue = (CryptoUtils.decrypt(dataValue));
	}

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

    public String getKey() {
	return dataKey;
    }

    public void setKey(String key) {
	this.dataKey = key;
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

    public String getValue() {
	return dataValue;
    }

    public void setValue(String value) {
	this.dataValue = value;
    }

}
