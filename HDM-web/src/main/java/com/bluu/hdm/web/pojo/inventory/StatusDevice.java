/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.pojo.inventory;

/**
 *
 * @author Gonzalo Torres
 */
public class StatusDevice {

    private Long id;
    private String name;

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
		? id.equals(((StatusDevice) other).id)
		: (other == this);
    }

    @Override
    public int hashCode() {
	return (id != null)
		? (getClass().hashCode() + id.hashCode())
		: super.hashCode();
    }

}
