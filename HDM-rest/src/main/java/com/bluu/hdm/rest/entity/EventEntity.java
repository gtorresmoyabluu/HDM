/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gonzalo Torres
 */
@Entity
@Table(name = "event")
public class EventEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Size(max = 255)
    @Column(name = "gravity")
    private String gravity;
    @Size(max = 255)
    @Column(name = "event_date")
    private String eventDate;
    @Size(max = 255)
    @Column(name = "event_calse")
    private String eventCalse;
    @Size(max = 255)
    @Column(name = "message")
    private String message;

    public EventEntity() {
    }

    public EventEntity(long id) {
	this.id = id;
    }

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public String getGravity() {
	return gravity;
    }

    public void setGravity(String gravity) {
	this.gravity = gravity;
    }

    public String getEventDate() {
	return eventDate;
    }

    public void setEventDate(String eventDate) {
	this.eventDate = eventDate;
    }

    public String getEventCalse() {
	return eventCalse;
    }

    public void setEventCalse(String eventCalse) {
	this.eventCalse = eventCalse;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    @Override
    public String toString() {
	return "com.bluu.hdm.rest.entity.EventEntity[ id=" + id + " ]";
    }

}
