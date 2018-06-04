/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.datamodel;

/**
 *
 * @author Gonzalo Torres
 */
public class Model {

    private static String name;

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Model.name = name;
    }
}
