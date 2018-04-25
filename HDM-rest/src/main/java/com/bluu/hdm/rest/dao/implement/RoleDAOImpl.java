/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.dao.implement;

import com.bluu.hdm.rest.dao.interfaces.IRoleDAO;
import com.bluu.hdm.rest.entity.RoleEntity;
import com.bluu.hdm.rest.entity.UserEntity;
import com.bluu.hdm.rest.repository.RoleRepository;
import com.bluu.hdm.rest.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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
@Service//("roleService")
@Transactional
public class RoleDAOImpl implements IRoleDAO {

    private static Logger logger = LogManager.getLogger(RoleDAOImpl.class.getName());
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public RoleEntity save(RoleEntity r) {
	RoleEntity role = new RoleEntity();
	try {
	    role = roleRepository.save(r);
	} catch (NonTransientDataAccessException ex) {
	    logger.error(String.format("Error: %s", ex.getMessage()));
	}
	return role;
    }

    @Override
    public Optional<RoleEntity> findById(Long id) {
	return roleRepository.findById(id);
    }

    @Override
    public List<RoleEntity> findAll() {
	return roleRepository.findAll();
    }

    @Override
    public boolean deleteById(Long id) {
	roleRepository.deleteById(id);
	return !roleRepository.existsById(id);
    }

    @Override
    public boolean isExist(Long id) {
	return roleRepository.existsById(id);
    }

    @Override
    public void deleteAll() {
	roleRepository.deleteAll();
    }

    @Override
    public List<UserEntity> findAllByRoleID(long id_role) {
	List<UserEntity> list = new ArrayList<>();
	for (RoleEntity role : roleRepository.findAll().stream().filter(r -> r.getId() == id_role).collect(Collectors.toList())) {
	    for (UserEntity user : role.getUserEntitySet()) {
		list.add(user);
	    }
	}
	return list;
    }

    @Override
    public RoleEntity findByName(String name) {
	return roleRepository.findByname(name).get();
    }
}
