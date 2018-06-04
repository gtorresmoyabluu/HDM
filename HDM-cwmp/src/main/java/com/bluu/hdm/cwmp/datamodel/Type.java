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
public enum Type {

    OBJECT, UNSIGNEDINT, STRING, INT, DATETIME, DATATYPE, BOOLEAN, BASE64, UNDEFINED;

    @Override
    public String toString() {
        if (OBJECT.equals(this)) {
            return "object";
        }
        if (UNSIGNEDINT.equals(this)) {
            return "unsignedInt";
        }
        if (STRING.equals(this)) {
            return "string";
        }
        if (INT.equals(this)) {
            return "int";
        }
        if (DATETIME.equals(this)) {
            return "dateTime";
        }

        if (DATATYPE.equals(this)) {
            return "datatype";
        }
        if (BOOLEAN.equals(this)) {
            return "boolean";
        }
        if (BASE64.equals(this)) {
            return "base64";
        }

        return null;
    }
}
