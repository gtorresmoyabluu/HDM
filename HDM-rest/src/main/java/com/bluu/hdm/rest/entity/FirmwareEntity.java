/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.entity;

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
@Table(name = "firmware")
public class FirmwareEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @Size(max = 500)
    @Column(name = "url_ftp")
    private String urlFTP;

    @JoinColumn(name = "id_model", referencedColumnName = "id")
    @ManyToOne
    private ModelEntity idModel;

    public FirmwareEntity() {
    }

    public FirmwareEntity(long id) {
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

    public Date getUpdateDate() {
	return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
	this.updateDate = updateDate;
    }

    public ModelEntity getIdModel() {
	return idModel;
    }

    public void setIdModel(ModelEntity idModel) {
	this.idModel = idModel;
    }

    @Override
    public String toString() {
	return "com.bluu.hdm.rest.entity.FirmwareEntity[ id=" + id + " ]";
    }

    public String getUrlFTP() {
	return urlFTP;
    }

    public void setUrlFTP(String urlFTP) {
	this.urlFTP = urlFTP;
    }

}
