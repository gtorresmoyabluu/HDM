package com.bluu.hdm.rest.vo.inventory;

import com.bluu.hdm.rest.entity.ConnectionEntity;
import java.io.Serializable;



public class ConnectionVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String role;
    private String template;
    private String usercomr;
    private String pwdcomw;
    private String type;
    private String url;

    public ConnectionVO() {
    }

    public ConnectionVO(ConnectionEntity entity) {
        id = entity.getId();
        role = entity.getRole();
        template = entity.getTemplate();
        usercomr = entity.getUsercomr();
        pwdcomw = entity.getPwdcomw();
        type = entity.getType();
        url = entity.getUrl();
    }

    public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getTemplate() {
        return template;
    }

    public String getUsercomr() {
        return usercomr;
    }

    public String getPwdcomw() {
        return pwdcomw;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public void setUsercomr(String usercomr) {
        this.usercomr = usercomr;
    }

    public void setPwdcomw(String pwdcomw) {
        this.pwdcomw = pwdcomw;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
