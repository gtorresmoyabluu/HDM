/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.vo.administration;

import java.io.Serializable;

/**
 *
 * @author Gonzalo Torres
 */
public class CategoryVO implements Serializable {

    private Long id;
    private String icon;
    private String name;
    private boolean showServer;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getIcon() {
	return icon;
    }

    public void setIcon(String icon) {
	this.icon = icon;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public boolean isShowServer() {
	return showServer;
    }

    public void setShowServer(boolean showServer) {
	this.showServer = showServer;
    }

}
