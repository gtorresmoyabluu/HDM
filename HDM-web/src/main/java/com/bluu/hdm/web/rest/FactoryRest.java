/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.rest;

/**
 *
 * @author Gonzalo Torres
 */
public class FactoryRest {
    public static ConsumeREST getInstance() {
        return new ConsumeREST();
    }
}
