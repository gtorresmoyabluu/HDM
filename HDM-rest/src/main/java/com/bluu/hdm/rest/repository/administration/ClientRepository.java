/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.repository.administration;

import com.bluu.hdm.rest.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gonzalo Torres
 */
@Repository("clientRepository")
public interface ClientRepository extends JpaRepository<ClientEntity, Long>{
    ClientEntity getClientByname(String name);
}
