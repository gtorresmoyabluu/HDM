/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.service.generic;

import java.io.Serializable;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Gonzalo Torres
 * @param <T>
 * @param <ID>
 */
public interface IService<T, ID extends Serializable> {

    ResponseEntity<Void> save(@RequestBody T oRequest, UriComponentsBuilder ucBuilder);

    ResponseEntity<T> update(@PathVariable("id") ID id, @RequestBody T oRequest);

    ResponseEntity<T> delete(@PathVariable(value = "id") ID id);

    ResponseEntity<T> deleteAll();

    ResponseEntity<T> findById(@PathVariable(value = "id") ID id);

    ResponseEntity<List<T>> getAll();
}
