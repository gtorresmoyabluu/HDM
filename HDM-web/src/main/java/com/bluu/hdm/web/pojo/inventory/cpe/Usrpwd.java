/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.pojo.inventory.cpe;

import java.io.Serializable;
import org.simpleframework.xml.Element;

public class Usrpwd implements Serializable {

    @Element(name = "user")
    private String user;
    @Element(name = "password")
    private String password;

    public Usrpwd() {
    }

    public String getPassword() {
        return password;
    }

    public String getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "User: " + user + ", Password: " + password;
    }
}
