/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.pojo.firmware;

import com.bluu.hdm.web.pojo.inventory.Model;
import com.bluu.hdm.web.util.CustomDateDeserializer;
import com.bluu.hdm.web.util.CustomDateSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Gonzalo Torres
 */
public class Firmware implements Serializable {
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
    private Model idModel;

    public Firmware(Firmware f) {
        this.id = f.getId();
        this.name = f.getName();
        this.creationDate = f.getCreationDate();
        this.updateDate = f.getUpdateDate();
        this.urlFTP = f.getUrlFTP();
        this.idModel = f.getIdModel();
    }

    public Firmware() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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

    public Model getIdModel() {
        return idModel;
    }

    public void setIdModel(Model idModel) {
        this.idModel = idModel;
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
