/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.pojo.administracion;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gonzalo Torres
 */
public class Category{
    private Long id;
    private List<Configuration> configurationList;
    private String icon;
    private String name;
    private boolean showServer;

    public Category() {
	this.configurationList = new ArrayList<>();
    }

    public Category(Category c) {
	this.id = c.getId();
	this.name = c.getName();
	this.icon = c.getIcon();
	this.showServer = c.isShowServer();
	this.configurationList = c.getConfigurationList();
    }

    public Category(String name, String icon, boolean showServer) {
	this.name = name;
	this.icon = icon;
	this.showServer = showServer;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public List<Configuration> getConfigurationList() {
	return configurationList;
    }

    public String getIcon() {
	return icon;
    }

    public String getName() {
	return name;
    }

    public boolean isShowServer() {
	return showServer;
    }

    public void setConfigurationList(List<Configuration> configurationList) {
	this.configurationList = configurationList;
    }

    public void setIcon(String icon) {
	this.icon = icon;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setShowServer(boolean showServer) {
	this.showServer = showServer;
    }

    @Override
    public boolean equals(Object other) {
        return (other != null && getClass() == other.getClass() && id != null)
		? id.equals(((Category) other).id)
		: (other == this);
    }

    @Override
    public int hashCode() {
	return (id != null)
		? (getClass().hashCode() + id.hashCode())
		: super.hashCode();
    }
}
