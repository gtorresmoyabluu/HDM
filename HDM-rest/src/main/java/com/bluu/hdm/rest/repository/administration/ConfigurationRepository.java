/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.repository.administration;

import com.bluu.hdm.rest.entity.ConfigurationEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gonzalo Torres
 */
@Repository("configRepository")
public interface ConfigurationRepository extends JpaRepository<ConfigurationEntity, Long> {
    List<ConfigurationEntity> findByClientId(Long clientId);
}
