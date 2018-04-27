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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Gonzalo Torres
 */
@Entity
@Table(name = "role")
public class RoleEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy = "roleEntitySet")
    @JsonIgnore
    private Set<AccessEntity> accessEntitySet;
    @OneToMany(mappedBy = "idRole")
    @JsonIgnore
    private Set<UserEntity> userEntitySet;

    public RoleEntity() {
    }

    public RoleEntity(long id) {
	this.id = id;
    }

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    @XmlTransient
    public Set<AccessEntity> getAccessEntitySet() {
	return accessEntitySet;
    }

    public void setAccessEntitySet(Set<AccessEntity> accessEntitySet) {
	this.accessEntitySet = accessEntitySet;
    }

    public Set<UserEntity> getUserEntitySet() {
	return userEntitySet;
    }

    public void setUserEntitySet(Set<UserEntity> userEntitySet) {
	this.userEntitySet = userEntitySet;
    }

    @Override
    public String toString() {
	return "com.bluu.hdm.rest.entity.RoleEntity[ id=" + id + " ]";
    }

}
