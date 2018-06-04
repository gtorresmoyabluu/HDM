/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.repository.firmware;

import com.bluu.hdm.rest.entity.FirmwareEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gonzalo Torres
 */
@Repository("firmwareRepository")
public interface FirmwareRepository extends JpaRepository<FirmwareEntity, Long> {

    Optional<FirmwareEntity> findByname(String name);
}
