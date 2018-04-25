/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.dao.implement;

import com.bluu.hdm.rest.dao.interfaces.IAccessDAO;
import com.bluu.hdm.rest.entity.AccessEntity;
import com.bluu.hdm.rest.entity.RoleEntity;
import com.bluu.hdm.rest.repository.AccessRepository;
import com.bluu.hdm.rest.repository.RoleRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gonzalo Torres
 */
@Service
@Transactional
public class AccessDAOImpl implements IAccessDAO {

    private static Logger logger = LogManager.getLogger(AccessDAOImpl.class.getName());
    @Autowired
    AccessRepository accessRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public AccessEntity save(AccessEntity access) {
	AccessEntity acc = new AccessEntity();
	try {
	    acc = accessRepository.save(access);
	} catch (NonTransientDataAccessException ex) {
	    logger.error(String.format("Error: %s", ex.getMessage()));
	}
	return acc;
    }

    @Override
    public Optional<AccessEntity> findById(Long id) {
	return accessRepository.findById(id);
    }

    @Override
    public List<AccessEntity> findAll() {
	return accessRepository.findAll();
    }

    @Override
    public boolean deleteById(Long id) {
	accessRepository.deleteById(id);
	return !accessRepository.existsById(id);
    }

    @Override
    public boolean isExist(Long id) {
	return accessRepository.existsById(id);
    }

    @Override
    public void deleteAll() {
	accessRepository.deleteAll();
    }

    @Override
    public void addAccessRole(long id_access, long id_role) {
	AccessEntity access = accessRepository.getOne(id_access);
	access.setRoleEntitySet(new HashSet<RoleEntity>() {
	    {
		add(roleRepository.getOne(id_role));
	    }
	});
	accessRepository.save(access);
    }

    @Override
    public List<AccessEntity> findAllByRoleID(long id_role) {
	List<AccessEntity> list = new ArrayList<>();
	RoleEntity search = roleRepository.findById(id_role).get();
	if (search != null) {
	    search.getAccessEntitySet().forEach((access) -> {
		list.add(access);
	    });
	}
	return list;
    }
}
