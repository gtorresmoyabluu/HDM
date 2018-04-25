/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.dao.interfaces;

import com.bluu.hdm.rest.entity.ManufacturerEntity;
import com.bluu.hdm.rest.entity.ModelEntity;
import java.util.List;
import com.bluu.hdm.rest.dao.generic.IGenericDAO;

/**
 *
 * @author Gonzalo Torres
 */
public interface IManufacturerDAO extends IGenericDAO<ManufacturerEntity, Long> {

    List<ModelEntity> findAllByManufacturerID(long id_manufacturer);
}
