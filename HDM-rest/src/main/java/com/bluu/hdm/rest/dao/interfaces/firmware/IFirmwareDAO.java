/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.dao.interfaces.firmware;

import com.bluu.hdm.rest.entity.FirmwareEntity;
import com.bluu.hdm.rest.dao.generic.IGenericDAO;
import java.util.Optional;

/**
 *
 * @author Gonzalo Torres
 */
public interface IFirmwareDAO extends IGenericDAO<FirmwareEntity, Long> {

    Optional<FirmwareEntity> findByname(String name);
}
