/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.dao.implement.administration;

import com.bluu.hdm.rest.dao.interfaces.administration.IUserDAO;
import com.bluu.hdm.rest.entity.UserEntity;
import com.bluu.hdm.rest.repository.AuthRepository;
import com.bluu.hdm.rest.repository.administration.RoleRepository;
import com.bluu.hdm.rest.repository.administration.UserRepository;
import com.bluu.hdm.rest.util.CryptoUtils;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gonzalo Torres
 */
@Service("userDetailsService")
@Transactional
public class UserDAOImpl implements IUserDAO {

    private static Logger logger = LogManager.getLogger(UserDAOImpl.class.getName());
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthRepository authRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public UserEntity save(UserEntity user) {
	UserEntity usr = new UserEntity();
	try {
	    user.setPassword(CryptoUtils.encrypt(user.getPassword()));
	    usr = userRepository.save(user);
	} catch (NonTransientDataAccessException ex) {
	    logger.error(String.format("Error: %s", ex.getMessage()));
	}
	return usr;
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
	return userRepository.findById(id);
    }

    @Override
    public List<UserEntity> findAll() {
	return userRepository.findAll();
    }

    @Override
    public boolean deleteById(Long id) {
	if (userRepository.existsById(id)) {
	    userRepository.deleteById(id);
	    return true;
	} else {
	    logger.error(String.format("User ID %s not exists!", id));
	    return false;
	}
    }

    @Override
    public boolean isExist(Long id) {
	return userRepository.existsById(id);
    }

    @Override
    public void deleteAll() {
	userRepository.deleteAll();
    }

    @Override
    public UserEntity getUserByfirstname(String username) {
	UserEntity user = authRepository.findByUsername(username);
	return user;
    }

    @Override
    public UserEntity getUserByusername(String username) {
	UserEntity user = userRepository.findByusername(username).get();
	return user;
    }
}
