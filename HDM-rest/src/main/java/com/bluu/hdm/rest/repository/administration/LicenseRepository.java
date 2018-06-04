/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.repository.administration;

import com.bluu.hdm.rest.entity.LicenseEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gonzalo Torres
 */
@Repository("licenseRepository")
public interface LicenseRepository extends JpaRepository<LicenseEntity, Long> {
    Optional<LicenseEntity> findBycode(String code);
}
