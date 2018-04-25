/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Gonzalo Torres
 */
@Entity
@Table(name = "access")
public class AccessEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @Size(max = 255)
    @Column(name = "code")
    private String code;

    @Size(max = 255)
    @Column(name = "icon")
    private String icon;

    @Column(name = "parent")
    private Integer parent;

    @JoinTable(name = "access_role", joinColumns = {
	@JoinColumn(name = "id_access", referencedColumnName = "id")}, inverseJoinColumns = {
	@JoinColumn(name = "id_role", referencedColumnName = "id")})
    @ManyToMany

    @JsonIgnore
    private Set<RoleEntity> roleEntitySet;

    public AccessEntity() {
    }

    public AccessEntity(long id) {
	this.id = id;
    }

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public Integer getParent() {
	return parent;
    }

    public void setParent(Integer parent) {
	this.parent = parent;
    }

    public Set<RoleEntity> getRoleEntitySet() {
	return roleEntitySet;
    }

    public void setRoleEntitySet(Set<RoleEntity> roleEntitySet) {
	this.roleEntitySet = roleEntitySet;
    }

    public String getIcon() {
	return icon;
    }

    public void setIcon(String icon) {
	this.icon = icon;
    }

    @Override
    public String toString() {
	return "com.bluu.hdm.rest.entity.AccessEntity[ id=" + id + " ]";
    }

}
