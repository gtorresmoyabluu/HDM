/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.repository.inventory;

import com.bluu.hdm.rest.entity.ModelEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gonzalo Torres
 */
@Repository("modelRepository")
public interface ModelRepository extends JpaRepository<ModelEntity, Long> {

    List<ModelEntity> findAllByOrderByManufacturerAsc();

    Optional<ModelEntity> findByManufacturerIdAndName(Long id, String name);
}
