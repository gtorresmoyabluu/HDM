package com.bluu.hdm.web.exception;

import java.io.Serializable;

public class CwmpException extends Exception implements Serializable {

    public CwmpException(String err) {
        super(err);
    }
}