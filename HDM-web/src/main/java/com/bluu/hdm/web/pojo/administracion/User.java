/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.pojo.administracion;

import com.bluu.hdm.web.pojo.OAuthToken;
import com.bluu.hdm.web.util.CryptoUtils;
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
    private String backupPswd;
    private String passwordConf;
    private String email;
    private int active;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date creationDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date lastLogin;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date lastModification;
    
    private Role idRole;
    
    private Client idClient;

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
        backupPswd = CryptoUtils.decrypt(user.getBackupPswd());
        passwordConf = CryptoUtils.decrypt(user.getBackupPswd());
	email = user.getEmail();
	creationDate = user.getCreationDate();
	lastLogin = user.getLastLogin();
	lastModification = user.getLastModification();
	idRole = user.getIdRole();
        idClient = user.getIdClient();
        active = user.getActive();
    }

    public User(Object[] model) {
	id = ((Number) (model[0])).longValue();
	username = (String) model[1];
	firstname = (String) model[2];
	lastname = (String) model[3];
	password = (String) model[4];
	passwordConf = CryptoUtils.decrypt((String) model[12]);
	email = (String) model[5];
	creationDate = (Date) model[6];
	lastLogin = (Date) model[7];
	lastModification = (Date) model[8];
	active = (Integer) model[9];
	try {
	    idRole = new Role(mapper.readValue(model[10].toString(), Object[].class));
	} catch (IOException ex) {
	    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
	}
	try {
	    idClient = new Client(mapper.readValue(model[11].toString(), Object[].class));
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

    public Date getLastLogin() {
	return lastLogin;
    }

    public String getLastLoginToString() {
	return this.lastLogin != null ? SDF.format(this.lastLogin) : null;
    }

    public void setLastLogin(Date lastLogin) {
	this.lastLogin = lastLogin;
    }

    public Date getLastModification() {
	return lastModification;
    }

    public String getLastModificationToString() {
	return this.lastModification != null ? SDF.format(this.lastModification) : null;
    }

    public void setLastModification(Date lastModification) {
	this.lastModification = lastModification;
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

    public Client getIdClient() {
        return idClient;
    }

    public void setIdClient(Client idClient) {
        this.idClient = idClient;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getBackupPswd() {
        return backupPswd;
    }

    public void setBackupPswd(String backupPswd) {
        this.backupPswd = backupPswd;
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
