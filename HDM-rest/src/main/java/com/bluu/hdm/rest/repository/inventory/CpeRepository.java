/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.repository.inventory;

import com.bluu.hdm.rest.entity.CpeEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Marco Valdés
 */
@Repository("deviceRepository")
public interface CpeRepository extends JpaRepository<CpeEntity, Long> {
    
   Optional<CpeEntity> findByidDevice(String idDevice);
}
