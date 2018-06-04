/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.pojo.administracion;

import com.bluu.hdm.web.util.CryptoUtils;
import com.bluu.hdm.web.util.CustomDateDeserializer;
import com.bluu.hdm.web.util.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private Client client;
    private License license;

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

    public Configuration(String dataKey, String dataValue, String fieldType, String fieldValues) {
        this.dataKey = dataKey;
        this.dataValue = dataValue;
        this.fieldType = fieldType;
        strFieldValues = fieldValues;
    }

    public Configuration(Configuration c) {
        this.fieldType = c.getFieldType();
        this.fieldValues = c.getFieldValues();
        this.id = c.getId();
        this.dataKey = c.getDataKey();
        this.listType = c.getListType();
        this.listValues = c.getListValues();
        this.strFieldValues = c.getStrFieldValues();
        this.ttCreation = c.getTtCreation();
        this.ttModification = c.getTtModification();
        this.dataValue = c.getDataValue();
        this.client = c.getClient();

        if (dataKey.contains("Password") || dataKey.contains("Secret")) {
            dataValue = (CryptoUtils.decrypt(dataValue));
        }
        if (dataKey.contains("license")) {
            if (dataValue.isEmpty()) {
                this.license = new License();
            } else {
                License lic = new License(dataValue);
                lic.doCheck(1);
                this.license = lic;
            }
        }
    }

    public String getFieldValuesKey(String value) {
        String ret = null;
        if (fieldValues != null) {
            for (final String key : fieldValues.keySet()) {
                if (fieldValues.get(key).equals(value)) {
                    ret = key;
                    break;
                }
            }
        }
        return ret;
    }

    public License getLicense() {
        return license;
    }

    public void setLicense(License license) {
        this.license = license;
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

    public String getDataKey() {
        return dataKey;
    }

    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
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

    public String getDataValue() {
        return dataValue;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getTime() {
        try {
            return new SimpleDateFormat("HH:mm").parse(dataValue);
        } catch (ParseException e) {
            return null;
        }
    }

    public void setTime(Date date) {
        dataValue = new SimpleDateFormat("HH:mm").format(date);
        ttModification = new Date();
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
        return !((id == null && other.id != null) || (id != null && !id.equals(other.id)));
    }

}
