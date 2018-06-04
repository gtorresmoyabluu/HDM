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
public enum LicenseVersionEnum {
    bluu("Bluu"),
    masmovil("MásMóvil"),
    entel("Entel"),
    vtr("VTR");

    private String nameAsString;

    LicenseVersionEnum(String nameAsString) {
	this.nameAsString = nameAsString;
    }

    public String getNameAsString() {
	return nameAsString;
    }
}
