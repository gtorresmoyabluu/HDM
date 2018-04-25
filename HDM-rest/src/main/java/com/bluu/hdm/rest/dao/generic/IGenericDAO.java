/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.dao.generic;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Gonzalo Torres
 * @param <T>
 * @param <ID>
 */
public interface IGenericDAO<T, ID extends Serializable> {

    T save(T entity);

    Optional<T> findById(ID id);

    List<T> findAll();

    boolean deleteById(ID id);

    boolean isExist(ID id);

    void deleteAll();
}
