/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Gonzalo Torres
 */
public class AccessVO {

    private Long id;
    private String description;
    private String code;
    private String icon;
    private Long parent;

    private Boolean active;

    private Long idRole;

    @JsonProperty("child")
    private List<AccessVO> child;
    @JsonIgnore
    private Set<RoleVO> roleEntitySet;

    public Set<RoleVO> getRoleEntitySet() {
	return roleEntitySet;
    }

    public void setRoleEntitySet(Set<RoleVO> roleEntitySet) {
	this.roleEntitySet = roleEntitySet;
    }

    public List<AccessVO> getChild() {
	return child;
    }

    public void setChild(List<AccessVO> child) {
	this.child = child;
    }

    public Long getParent() {
	return parent;
    }

    public void setParent(Long parent) {
	this.parent = parent;
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

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

    public String getIcon() {
	return icon;
    }

    public void setIcon(String icon) {
	this.icon = icon;
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

}
