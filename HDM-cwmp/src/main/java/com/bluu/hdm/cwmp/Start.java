/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp;

import com.bluu.hdm.cwmp.main.ACSServletPublisher;
import com.bluu.hdm.cwmp.main.ACSWebServicePublisher;

/**
 *
 * @author Gonzalo Torres
 */
public class Start {
    public static void main(String[] args) throws Exception {
        //BasicConfigurator.configure();
        ACSServletPublisher.publishACS();
        ACSWebServicePublisher.publishWebservice();
    }
}
