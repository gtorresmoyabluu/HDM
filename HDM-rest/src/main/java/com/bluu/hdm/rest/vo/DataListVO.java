/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.vo;

/**
 *
 * @author Gonzalo Torres
 */
public class DataListVO {
    private int size;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public DataListVO(int size) {
        this.size = size;
    }
    
}
