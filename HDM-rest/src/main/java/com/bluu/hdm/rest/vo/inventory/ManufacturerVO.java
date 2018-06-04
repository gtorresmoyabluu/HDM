/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.vo.inventory;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Set;

/**
 *
 * @author Gonzalo Torres
 */
public class ManufacturerVO {

    private Long id;
    private String name;
    private Set<ModelVO> modelEntitySet;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    @JsonProperty(value = "models")
    public Set<ModelVO> getModelEntitySet() {
	return modelEntitySet;
    }

    public void setModelEntitySet(Set<ModelVO> modelEntitySet) {
	this.modelEntitySet = modelEntitySet;
    }

}
