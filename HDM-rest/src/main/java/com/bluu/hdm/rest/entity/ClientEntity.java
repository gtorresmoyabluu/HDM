/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Gonzalo Torres
 */
@Entity
@Table(name = "client")
public class ClientEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Size(max = 255)
    @Column(name = "name")
    private String name;

    public ClientEntity() {
    }

    public ClientEntity(long id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public ClientEntity(ClientEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }

    public ClientEntity(long clientId) {
        this.id = clientId;
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
    
    
}
