/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.pojo.inventory;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gonzalo Torres
 */
public class Manufacturer {

    private Long id;
    private String name;
    private List<Model> models;

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

    public List<Model> getModels() {
	return models;
    }

    public void setModels(List<Model> models) {
	this.models = models;
    }

    public Manufacturer() {
    }

    public Manufacturer(Manufacturer mnf) {
        this.id = mnf.getId();
        this.name = mnf.getName();
        this.models = mnf.getModels() == null ? new ArrayList<>() : mnf.getModels();
    }

    @Override
    public boolean equals(Object other) {
	return (other != null && getClass() == other.getClass() && id != null)
		? id.equals(((Manufacturer) other).id)
		: (other == this);
    }

    @Override
    public int hashCode() {
	return (id != null)
		? (getClass().hashCode() + id.hashCode())
		: super.hashCode();
    }
}
