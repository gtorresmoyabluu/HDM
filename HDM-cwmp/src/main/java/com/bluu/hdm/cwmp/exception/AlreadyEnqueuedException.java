/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.exception;

/**
 *
 * @author Gonzalo Torres
 */
public class AlreadyEnqueuedException extends Exception {

    public AlreadyEnqueuedException() {
    }

    public AlreadyEnqueuedException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        if (super.getMessage() == null || super.getMessage().length() == 0) {
            return "Already enqueued";
        }
        return super.getMessage();
    }
}
