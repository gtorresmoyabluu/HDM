/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.command;

/**
 *
 * @author Gonzalo Torres
 */
public class DeleteObjectCommand extends Command {

    private String path;

    public DeleteObjectCommand(String serialNumber, String connectionRequestURL, String path, String username, String password) {
        this.serialNumberCPE = serialNumber;
        this.connectionRequestURL = connectionRequestURL;
        this.usernameCPE = username;
        this.passwordCPE = password;
        this.path = path;
        this.type = Command.TYPE_DELETEOBJECT;
    }

    public DeleteObjectCommand(String serialNumber, String path, String username, String password) {
        this.serialNumberCPE = serialNumber;
        this.usernameCPE = username;
        this.passwordCPE = password;
        this.path = path;
        this.type = Command.TYPE_DELETEOBJECT;
    }

    public DeleteObjectCommand(String serialNumber, String path) {
        this.serialNumberCPE = serialNumber;
        this.usernameCPE = Command.USERNAME_DEFAULT;
        this.passwordCPE = Command.PASSWORD_DEFAULT;
        this.path = path;
        this.type = Command.TYPE_DELETEOBJECT;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
