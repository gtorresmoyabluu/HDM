/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.vo;

import com.bluu.hdm.rest.util.CustomDateDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Gonzalo Torres
 */
public class UserVO implements Serializable {

    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String password;
    private String email;
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date creationDate;
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date highDate;
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date lowDate;
    private RoleVO idRole;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getFirstname() {
	return firstname;
    }

    public void setFirstname(String firstname) {
	this.firstname = firstname;
    }

    public String getLastname() {
	return lastname;
    }

    public void setLastname(String lastname) {
	this.lastname = lastname;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public Date getCreationDate() {
	return creationDate;
    }

    public void setCreationDate(Date creationDate) {
	this.creationDate = creationDate;
    }

    public Date getHighDate() {
	return highDate;
    }

    public void setHighDate(Date highDate) {
	this.highDate = highDate;
    }

    public Date getLowDate() {
	return lowDate;
    }

    public void setLowDate(Date lowDate) {
	this.lowDate = lowDate;
    }

    public RoleVO getIdRole() {
	return idRole;
    }

    public void setIdRole(RoleVO idRole) {
	this.idRole = idRole;
    }

}
