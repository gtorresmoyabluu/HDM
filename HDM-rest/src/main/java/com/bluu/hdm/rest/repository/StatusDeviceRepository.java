/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.repository;

import com.bluu.hdm.rest.entity.StatusDevicesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gonzalo Torres
 */
@Repository("statusRepository")
public interface StatusDeviceRepository extends JpaRepository<StatusDevicesEntity, Long> {

}
