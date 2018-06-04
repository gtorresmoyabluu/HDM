/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.ws.response;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Gonzalo Torres
 */
public abstract class TemplResponse<T> {

    private int errorCode;
    private String message;
    private ArrayList<T> retValue = new ArrayList<T>();

    public TemplResponse() {
        this.errorCode = 0;
        this.message = "(not provided)";
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @XmlTransient
    protected ArrayList<T> getRetValue() {
        return retValue;
    }

    public void setRetValue(ArrayList<T> retValue) {
        this.retValue = retValue;
    }

    public void addRetValue(T retValue) {
        if (retValue != null) {
            this.retValue.add(retValue);
        }
    }
}
