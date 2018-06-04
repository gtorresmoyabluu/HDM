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
public class RebootCommand extends Command {

    public RebootCommand(String serialNumber, String connectionRequestURL, String username, String password) {
        this.serialNumberCPE = serialNumber;
        this.connectionRequestURL = connectionRequestURL;
        this.usernameCPE = username;
        this.passwordCPE = password;
        this.type = Command.TYPE_REBOOT;
    }
    
    public RebootCommand(String serialNumber) {
        this.serialNumberCPE = serialNumber;
        this.usernameCPE = Command.USERNAME_DEFAULT;
        this.passwordCPE = Command.PASSWORD_DEFAULT;
        this.type = Command.TYPE_REBOOT;
    }
}
