/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.vo.firmware;

import com.bluu.hdm.rest.util.CustomDateDeserializer;
import com.bluu.hdm.rest.util.CustomDateSerializer;
import com.bluu.hdm.rest.vo.inventory.ManufacturerVO;
import com.bluu.hdm.rest.vo.inventory.ModelVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;

/**
 *
 * @author Gonzalo Torres
 */
public class FirmwareVO {

    private Long id;
    private String name;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date creationDate;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date updateDate;
    
    private String urlFTP;
    private ModelVO idModel;

    public Long getId() {
	return id;
    }

    public FirmwareVO() {
    }

    public FirmwareVO(String name, String model, String manufacturer) {
        this.name = name;
        this.idModel = new ModelVO();
        this.idModel.setId(Long.parseLong(model));
        ManufacturerVO mnf = new ManufacturerVO();
        mnf.setId(Long.parseLong(manufacturer));
        this.idModel.setManufacturer(mnf);
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    
}
