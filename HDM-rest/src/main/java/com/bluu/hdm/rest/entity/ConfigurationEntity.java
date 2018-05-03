/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Gonzalo Torres
 */
@Entity
@Table(name = "configuration")
public class ConfigurationEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_category", referencedColumnName = "id")
    private CategoryEntity id_category;

    @NotNull
    @Size(min = 0, max = 64)
    @Column(name = "data_key")
    private String dataKey;

    @NotNull
    @Size(min = 0, max = 256)
    @Column(name = "data_value")
    private String dataValue;

    @Size(max = 10)
    @Column(name = "fieldtype")
    private String fieldType;

    @Size(max = 756)
    @Column(name = "fieldvalues")
    private String fieldValues;

    @NotNull
    @Column(name = "ttcreation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ttCreation;
    @NotNull
    @Column(name = "ttmodification")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ttModification;

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

    public String getDataValue() {
	return dataValue;
    }

    public void setDataValue(String dataValue) {
	this.dataValue = dataValue;
    }

    public String getFieldType() {
	return fieldType;
    }

    public void setFieldType(String fieldType) {
	this.fieldType = fieldType;
    }

    public String getFieldValues() {
	return fieldValues;
    }

    public void setFieldValues(String fieldValues) {
	this.fieldValues = fieldValues;
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

    public CategoryEntity getId_category() {
	return id_category;
    }

    public void setId_category(CategoryEntity id_category) {
	this.id_category = id_category;
    }

}
