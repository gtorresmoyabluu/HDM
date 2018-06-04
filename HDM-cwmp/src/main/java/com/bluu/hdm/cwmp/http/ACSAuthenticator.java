/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.http;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

/**
 *
 * @author Gonzalo Torres
 */
public class ACSAuthenticator extends Authenticator {

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        String ui = this.getRequestingURL().getUserInfo();
        System.out.println("MyAuthenticator: ui=" + ui);
        if (ui == null || ui.equals("")) {
            return super.getPasswordAuthentication();
        }
        String up[] = ui.split(":");
        char[] pc = new char[up[1].length()];
        up[1].getChars(0, up[1].length(), pc, 0);
        PasswordAuthentication pa = new PasswordAuthentication(up[0], pc);
        return pa;
    }
}