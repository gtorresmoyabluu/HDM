/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.enums;

/**
 *
 * @author Gonzalo Torres
 */
public enum LicenseAppEnum {
    orizone("Orizone"),
    hdm("HDM"),
    schaman("Schaman");

    private String nameAsString;

    LicenseAppEnum(String nameAsString) {
	this.nameAsString = nameAsString;
    }

    public String getNameAsString() {
	return nameAsString;
    }
}
