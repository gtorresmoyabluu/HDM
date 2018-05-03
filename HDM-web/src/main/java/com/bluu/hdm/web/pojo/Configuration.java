/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.pojo;

import com.bluu.hdm.web.util.CryptoUtils;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author Gonzalo Torres
 */
public class Configuration implements Serializable {

    private static final long serialVersionUID = 1L;

    private String fieldType;
    private String fieldValues;
    private LinkedHashMap<String, String> fieldValuesLinked;
    private Long id;
    private String key;
    private String listType;
    private List<String> listValues;
    private String strFieldValues;
    private Date ttCreation;
    private Date ttModification;
    private String value;

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

    public Configuration() {
    }

    public Configuration(String key, String value, String fieldType, String fieldValues) {
	this.key = key;
	this.value = value;
	this.fieldType = fieldType;
	strFieldValues = fieldValues;

	if (fieldValues != null && fieldValues.length() > 1) {
	    if (fieldValues.contains(":")) {
		this.fieldValuesLinked = new LinkedHashMap<>();
		final String[] arr = fieldValues.split(";");
		for (final String element : arr) {
		    final String[] arr_ = element.split(":");
		    this.fieldValuesLinked.put(arr_[1], arr_[0]);
		}
	    } else {
		getListValues(fieldValues);
	    }
	}
    }

    public Configuration(Configuration conf) {

	id = conf.getId();
	key = conf.getKey();
	value = conf.getValue();
	fieldType = conf.getFieldType();

	if (key.contains("Password") || key.contains("Secret")) {
	    value = (CryptoUtils.decrypt(value));
	}

	if (conf.getFieldValues() != null && conf.getFieldValues().length() > 1) {
	    if (conf.getFieldValues().contains(":")) {
		fieldValuesLinked = new LinkedHashMap<>();
		final String[] arr = conf.getFieldValues().split(";");
		for (final String element : arr) {
		    final String[] arr_ = element.split(":");
		    fieldValuesLinked.put(arr_[1], arr_[0]);
		}
	    } else {
		getListValues(conf.getFieldValues());
	    }
	}
    }

    public String getFieldType() {
	return fieldType;
    }

//    public LinkedHashMap<String, String> getFieldValues() {
//	return fieldValues;
//    }
    public String getFieldValuesKey(String value) {
	String ret = null;
	if (fieldValues != null) {
	    for (final String key : fieldValuesLinked.keySet()) {
		if (fieldValuesLinked.get(key).equals(value)) {
		    ret = key;
		    break;
		}
	    }
	}
	return ret;
    }

    public Long getId() {
	return id;
    }

    public String getKey() {
	return key;
    }

    public String getListType() {
	return listType;
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

    public List<String> getListValues() {
	return listValues;
    }

    public String getStrFieldValues() {
	return strFieldValues;
    }

    public Date getTtCreation() {
	return ttCreation;
    }

    public Date getTtModification() {
	return ttModification;
    }

    public String getValue() {
	return value;
    }

    public Date getTime() {
	try {
	    return new SimpleDateFormat("HH:mm").parse(value);
	} catch (ParseException e) {
	    return null;
	}
    }

    public void setFieldType(String fieldType) {
	this.fieldType = fieldType;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public void setKey(String key) {
	this.key = key;
    }

    public void setListType(String listType) {
	this.listType = listType;
    }

    public void setListValues(List<String> listValues) {
	this.listValues = listValues;
    }

    public void setStrFieldValues(String strFieldValues) {
	this.strFieldValues = strFieldValues;
    }

    public void setTtCreation(Date ttCreation) {
	this.ttCreation = ttCreation;
    }

    public void setTtModification(Date ttModification) {
	this.ttModification = ttModification;
    }

    public void setValue(String value) {
	this.value = value;
	ttModification = new Date();
    }

    public void setTime(Date date) {
	value = new SimpleDateFormat("HH:mm").format(date);
	ttModification = new Date();
    }

    public String getFieldValues() {
	return fieldValues;
    }

    public void setFieldValues(String fieldValues) {
	this.fieldValues = fieldValues;
    }

    public LinkedHashMap<String, String> getFieldValuesLinked() {
	return fieldValuesLinked;
    }

    public void setFieldValuesLinked(LinkedHashMap<String, String> fieldValuesLinked) {
	this.fieldValuesLinked = fieldValuesLinked;
    }

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (id != null ? id.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	if (!(object instanceof Configuration)) {
	    return false;
	}
	final Configuration other = (Configuration) object;
	if ((id == null && other.id != null) || (id != null && !id.equals(other.id))) {
	    return false;
	}
	return true;
    }

}
