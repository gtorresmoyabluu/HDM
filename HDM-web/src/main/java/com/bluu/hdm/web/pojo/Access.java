/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.pojo;

import java.util.List;

/**
 *
 * @author Gonzalo Torres
 */
public class Access {

    private Long id;
    private String description;
    private String code;
    private Long parent;
    private String icon;
    private List<Access> child;
    private List<Role> roles;

    public String getIcon() {
	return icon;
    }

    public void setIcon(String icon) {
	this.icon = icon;
    }

    public List<Access> getChild() {
	return child;
    }

    public void setChild(List<Access> child) {
	this.child = child;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public Long getParent() {
	return parent;
    }

    public void setParent(Long parent) {
	this.parent = parent;
    }

    public List<Role> getRoles() {
	return roles;
    }

    public void setRoles(List<Role> roles) {
	this.roles = roles;
    }

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

    @Override
    public boolean equals(Object other) {
	return (other != null && getClass() == other.getClass() && id != null)
		? id.equals(((Access) other).id)
		: (other == this);
    }

    @Override
    public int hashCode() {
	return (id != null)
		? (getClass().hashCode() + id.hashCode())
		: super.hashCode();
    }
}
