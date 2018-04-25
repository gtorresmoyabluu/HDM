/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.dao.implement;

import com.bluu.hdm.rest.dao.interfaces.IAuthDAO;
import com.bluu.hdm.rest.entity.UserEntity;
import com.bluu.hdm.rest.repository.AuthRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gonzalo Torres
 */
@Service("userCustomService")
public class AuthDAOImpl implements IAuthDAO {

    @Autowired
    private AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	UserEntity user = authRepository.findByUsername(username);
	if (user == null) {
	    throw new UsernameNotFoundException("Invalid username or password.");
	}
	return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }

    private Collection<? extends GrantedAuthority> getAuthority(UserEntity user) {
	List<GrantedAuthority> authorities = new ArrayList<>();
	authorities.add(new SimpleGrantedAuthority(String.format("ROLE_%s", user.getIdRole().getName())));
	return authorities;
    }
}
