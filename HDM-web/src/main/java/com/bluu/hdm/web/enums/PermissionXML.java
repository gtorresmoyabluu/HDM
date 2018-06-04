package com.bluu.hdm.web.enums;


import com.bluu.hdm.web.pojo.*;
import com.bluu.hdm.web.enums.PermissionXMLTypeEnum;
import java.io.Serializable;
import java.util.LinkedHashMap;

public class PermissionXML implements Serializable {

    private String id;
    private PermissionXMLTypeEnum type;
    private String outcome;
    private String command;
    private String icon;
    private boolean rendered;
    private boolean onlygnoc;
    private LinkedHashMap<String, PermissionXML> submenu;

    public PermissionXML() {
	submenu = new LinkedHashMap<>();
    }

    //Getters && Setters
    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getOutcome() {
	return outcome;
    }

    public void setOutcome(String outcome) {
	this.outcome = outcome;
    }

    public String getCommand() {
	return command;
    }

    public void setCommand(String command) {
	this.command = command;
    }

    public String getIcon() {
	return icon;
    }

    public void setIcon(String icon) {
	this.icon = icon;
    }

    public LinkedHashMap<String, PermissionXML> getSubmenu() {
	return submenu;
    }

    public void setSubmenu(LinkedHashMap<String, PermissionXML> submenu) {
	this.submenu = submenu;
    }

    public PermissionXMLTypeEnum getType() {
	return type;
    }

    public void setType(PermissionXMLTypeEnum type) {
	this.type = type;
    }

    public boolean isRendered() {
	return rendered;
    }

    public void setRendered(boolean rendered) {
	this.rendered = rendered;
    }

    public boolean isOnlygnoc() {
	return onlygnoc;
    }

    public void setOnlygnoc(boolean onlygnoc) {
	this.onlygnoc = onlygnoc;
    }

}
