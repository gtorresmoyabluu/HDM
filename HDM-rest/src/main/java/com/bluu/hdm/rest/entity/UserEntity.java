/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.entity;

import com.bluu.hdm.rest.util.CustomDateDeserializer;
import com.bluu.hdm.rest.util.CustomDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Gonzalo Torres
 */
@Entity
@Table(name = "user")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Size(max = 255)
    @Column(name = "username")
    private String username;

    @Size(max = 255)
    @Column(name = "firstname")
    private String firstname;

    @Size(max = 255)
    @Column(name = "lastname")
    private String lastname;

    @Size(max = 255)
    @Column(name = "email")
    private String email;

    @Size(max = 255)
    @Column(name = "password", nullable = false)
    private String password;

    @Size(max = 255)
    @Column(name = "backupPswd", nullable = false)
    private String backupPswd;

    @JsonIgnore
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date creationDate;

    @Column(name = "last_login")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date lastLogin;

    @Column(name = "last_modification")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date lastModification;

    @Column(name = "active")
    private int active;

    @JoinColumn(name = "id_role", referencedColumnName = "id")
    @ManyToOne
    private RoleEntity idRole;

    @JoinColumn(name = "id_client", referencedColumnName = "id", nullable = true)
    @ManyToOne
    private ClientEntity idClient;

    public UserEntity() {
    }

    public UserEntity(UserEntity users) {
        this.active = users.active;
        this.email = users.email;
        this.id = users.id;
        this.lastname = users.lastname;
        this.firstname = users.firstname;
        this.password = users.password;
        this.backupPswd = users.backupPswd;
        this.idRole = users.idRole;
        this.idClient = users.idClient;
    }

    public UserEntity(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public RoleEntity getIdRole() {
        return idRole;
    }

    public void setIdRole(RoleEntity idRole) {
        this.idRole = idRole;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Date getLastModification() {
        return lastModification;
    }

    public void setLastModification(Date lastModification) {
        this.lastModification = lastModification;
    }

    public ClientEntity getIdClient() {
        return idClient;
    }

    public void setIdClient(ClientEntity idClient) {
        this.idClient = idClient;
    }

    public String getBackupPswd() {
        return backupPswd;
    }

    public void setBackupPswd(String backupPswd) {
        this.backupPswd = backupPswd;
    }

    @Override
    public String toString() {
        return "com.bluu.hdm.rest.entity.UserEntity[ id=" + id + " ]";
    }
}
