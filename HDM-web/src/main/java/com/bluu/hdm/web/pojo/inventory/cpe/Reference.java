/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.pojo.inventory.cpe;

import com.bluu.hdm.web.enums.ReferenceTypeEnm;
import java.io.Serializable;
import org.simpleframework.xml.Attribute;


public class Reference implements Serializable {

    @Attribute(name = "id")
    private String id;
    @Attribute(name = "type")
    private ReferenceTypeEnm type;
    @Attribute(name = "role", required = false)
    private String role = "none";
    @Attribute(name = "inverse", required = false)
    private boolean isInverse = false;
    @Attribute(name = "multiple", required = false)
    private boolean isMultiple = false;

    public Reference() {
    }

    public String getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public ReferenceTypeEnm getType() {
        return type;
    }

    public boolean isInverse() {
        return isInverse;
    }

    public boolean isMultiple() {
        return isMultiple;
    }

    @Override
    public String toString() {
        return "Ref Type: " + type + ", Id: " + id + ", Role: " + role
                + ", isInverse: " + isInverse + ", isMultiple: " + isMultiple;
    }
}
