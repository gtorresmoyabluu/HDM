/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.pojo.administracion;

/**
 *
 * @author Gonzalo Torres
 */
public class Role {

    private Long id;
    private String name;

    public Role() {
    }

    public Role(Role rol) {
	this.id = rol.getId();
	this.name = rol.getName();
    }

    public Role(Object[] model) {
	this.id = ((Number) (model[0])).longValue();
	this.name = (String) model[1];
    }

    public Role(Long id, String name) {
	this.id = id;
	this.name = name;
    }

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

    @Override
    public boolean equals(Object other) {
	return (other != null && getClass() == other.getClass() && id != null)
		? id.equals(((Role) other).id)
		: (other == this);
    }

    @Override
    public int hashCode() {
	return (id != null)
		? (getClass().hashCode() + id.hashCode())
		: super.hashCode();
    }
}
