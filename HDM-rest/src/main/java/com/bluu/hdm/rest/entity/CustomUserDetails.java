/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.entity;

import java.util.Arrays;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Gonzalo Torres
 */
public class CustomUserDetails extends UserEntity implements UserDetails {

    public CustomUserDetails(final UserEntity users) {
	super(users);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
	return Arrays.asList(new SimpleGrantedAuthority(String.format("ROLE_%s", getIdRole().getName())));
    }

    @Override
    public String getPassword() {
	return super.getPassword();
    }

    @Override
    public String getUsername() {
	return super.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
	return true;
    }

    @Override
    public boolean isAccountNonLocked() {
	return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
	return true;
    }

    @Override
    public boolean isEnabled() {
	return true;
    }
}
