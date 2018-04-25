package com.bluu.hdm.web.pojo;

import java.io.Serializable;
import java.util.Set;

/**
 * Clase con la información que se almacenará con respecto al usuario conectado
 * durante la duración de la sesión
 */
public class UserSession implements Serializable {

    private static final long serialVersionUID = 1L;

    private User user;
    private Set<String> permissions;

    public UserSession() {
    }

    public UserSession(User user, Set<String> permissions) {
	this.user = user;
	this.permissions = permissions;
    }

    public UserSession(User user) {
	this.user = user;
    }

    public boolean hasPermissionTo(String permissionName) {
	return true;//AdministrationBean.SADMIN_NAME.equals(user.getUsername()) || permissions.contains(permissionName);
    }

    //Getters & Setters
    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public Set<String> getPermissions() {
	return permissions;
    }

    public void setPermissions(Set<String> permissions) {
	this.permissions = permissions;
    }
}
