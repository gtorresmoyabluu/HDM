/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.dao.interfaces.inventory;

import com.bluu.hdm.rest.dao.generic.IGenericDAO;
import com.bluu.hdm.rest.entity.CpeEntity;
import java.util.Optional;
import org.hibernate.query.Query;

/**
 *
 * @author Marco Valdés
 */
public interface ICpeDAO extends IGenericDAO<CpeEntity, Long> {

     Optional<CpeEntity> findByidDevice(String idDevice);
     Query createNamedQuery(String name);
     
}
