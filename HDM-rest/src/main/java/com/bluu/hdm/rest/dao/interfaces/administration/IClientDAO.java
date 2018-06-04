/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.dao.interfaces.administration;

import com.bluu.hdm.rest.dao.generic.IGenericDAO;
import com.bluu.hdm.rest.entity.ClientEntity;

/**
 *
 * @author Gonzalo Torres
 */
public interface IClientDAO extends IGenericDAO<ClientEntity, Long>{
    ClientEntity getClientByname(String name);
}
