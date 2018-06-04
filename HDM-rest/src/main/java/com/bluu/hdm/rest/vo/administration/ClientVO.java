/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.vo.administration;

import java.io.Serializable;

/**
 *
 * @author Gonzalo Torres
 */
public class ClientVO implements Serializable{
    private Long id;
    private String name;

    public ClientVO() {
    }

    public ClientVO(Long id) {
        this.id = id;
    }

    public ClientVO(Object[] obj) {
        this.id = ((Number) (obj[0])).longValue();
        this.name = (String)obj[1];
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
