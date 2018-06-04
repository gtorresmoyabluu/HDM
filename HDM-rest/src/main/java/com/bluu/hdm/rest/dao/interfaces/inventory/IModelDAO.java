/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.dao.interfaces.inventory;

import com.bluu.hdm.rest.entity.ModelEntity;
import com.bluu.hdm.rest.dao.generic.IGenericDAO;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Gonzalo Torres
 */
public interface IModelDAO extends IGenericDAO<ModelEntity, Long> {

    List<ModelEntity> findAllByOrderByidManufacturerAsc();

    Optional<ModelEntity> findByManufacturerIdAndName(Long id, String name);
}
