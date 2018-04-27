/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.pojo;

import com.bluu.hdm.web.util.CustomDateDeserializer;
import com.bluu.hdm.web.util.CustomDateSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gonzalo Torres
 */
public class User implements Serializable {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String password;
    private String passwordConf;
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date creationDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date highDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date lowDate;
    private Role idRole;

    private ObjectMapper mapper = new ObjectMapper();

    private OAuthToken token;

    public User() {
    }

    public User(User user) {
	id = user.getId();
	username = user.getUsername();
	firstname = user.getFirstname();
	lastname = user.getLastname();
	password = user.getPassword();
	email = user.getEmail();
	creationDate = user.getCreationDate();
	highDate = user.getHighDate();
	lowDate = user.getLowDate();
	idRole = user.getIdRole();
    }

    public User(Object[] model) {
	id = ((Number) (model[0])).longValue();
	username = (String) model[1];
	firstname = (String) model[2];
	lastname = (String) model[3];
	password = (String) model[4];
	email = (String) model[5];
	creationDate = (Date) model[6];
	highDate = (Date) model[7];
	lowDate = (Date) model[8];
	try {
	    idRole = new Role(mapper.readValue(model[9].toString(), Object[].class));
	} catch (IOException ex) {
	    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    public OAuthToken getToken() {
	return token;
    }

    public void setToken(OAuthToken token) {
	this.token = token;
    }

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
	return this.creationDate;
    }

    public String getCreationToString() {
	return this.creationDate != null ? SDF.format(this.creationDate) : null;
    }

    public void setCreationDate(Date creationDate) {
	this.creationDate = creationDate;
    }

    public Date getHighDate() {
	return highDate;
    }

    public String getHighDateToString() {
	return this.highDate != null ? SDF.format(this.highDate) : null;
    }

    public void setHighDate(Date highDate) {
	this.highDate = highDate;
    }

    public Date getLowDate() {
	return lowDate;
    }

    public String getLowDateToString() {
	return this.lowDate != null ? SDF.format(this.lowDate) : null;
    }

    public void setLowDate(Date lowDate) {
	this.lowDate = lowDate;
    }

    public Role getIdRole() {
	return idRole;
    }

    public void setIdRole(Role idRole) {
	this.idRole = idRole;
    }

    public String getPasswordConf() {
	return passwordConf;
    }

    public void setPasswordConf(String passwordConf) {
	this.passwordConf = passwordConf;
    }

    @Override
    public boolean equals(Object other) {
	return (other != null && getClass() == other.getClass() && id != null)
		? id.equals(((User) other).id)
		: (other == this);
    }

    @Override
    public int hashCode() {
	return (id != null)
		? (getClass().hashCode() + id.hashCode())
		: super.hashCode();
    }
}
