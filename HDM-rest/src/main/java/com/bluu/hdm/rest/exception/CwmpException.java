package com.bluu.hdm.rest.exception;

import java.io.Serializable;

public class CwmpException extends Exception implements Serializable {

    public CwmpException(String err) {
        super(err);
    }
}