/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.pojo;

import java.util.ArrayList;
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
    private String parentName;
    private Boolean active;
    private Long idRole;

    private Access idParent;

    private List<Access> parents;
    private List<Access> child;
    private List<Role> roles;

    public Access() {
	this.parents = new ArrayList<>();
	this.child = new ArrayList<>();
	this.roles = new ArrayList<>();
    }

    public Access(Long id, String description, String code, String icon, String parentName, Long parentId, Access p) {
	this.id = id;
	this.description = description;
	this.code = code;
	this.icon = icon;
	this.parentName = parentName;
	this.parent = parentId;
	this.idParent = p;
    }

    public Access(String description, Boolean active) {
	this.description = description;
	this.active = active;
    }

    public Access(Access acc) {
	this.id = acc.getId();
	this.description = acc.getDescription();
	this.code = acc.getCode();
	this.parent = acc.getParent();
	this.icon = acc.getIcon();
	this.active = acc.getActive();
	this.child = acc.getChild();
    }

    public String getParentName() {
	return parentName;
    }

    public void setParentName(String parentName) {
	this.parentName = parentName;
    }

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

    public Boolean getActive() {
	return active;
    }

    public void setActive(Boolean active) {
	this.active = active;
    }

    public Long getIdRole() {
	return idRole;
    }

    public void setIdRole(Long idRole) {
	this.idRole = idRole;
    }

    public List<Access> getParents() {
	return parents;
    }

    public void setParents(List<Access> parents) {
	this.parents = parents;
    }

    public Access getIdParent() {
	return idParent;
    }

    public void setIdParent(Access idParent) {
	this.idParent = idParent;
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
