/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.vo;

import java.util.Date;

/**
 *
 * @author Gonzalo Torres
 */
public class FirmwareVO {

    private Long id;
    private String name;
    private Date updateDate;
    private String urlFTP;
    private ModelVO idModel;

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

    public ModelVO getIdModel() {
	return idModel;
    }

    public void setIdModel(ModelVO idModel) {
	this.idModel = idModel;
    }
}
