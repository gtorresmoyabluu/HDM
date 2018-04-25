/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.pojo;

import java.util.Date;

/**
 *
 * @author Gonzalo Torres
 */
public class Firmware {

    private Long id;
    private String name;
    private Date updateDate;
    private String urlFTP;
    private Model objModel;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
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

    public String getUrlFTP() {
	return urlFTP;
    }

    public void setUrlFTP(String urlFTP) {
	this.urlFTP = urlFTP;
    }

    public Model getObjModel() {
	return objModel;
    }

    public void setObjModel(Model objModel) {
	this.objModel = objModel;
    }

    @Override
    public boolean equals(Object other) {
	return (other != null && getClass() == other.getClass() && id != null)
		? id.equals(((Firmware) other).id)
		: (other == this);
    }

    @Override
    public int hashCode() {
	return (id != null)
		? (getClass().hashCode() + id.hashCode())
		: super.hashCode();
    }
}
