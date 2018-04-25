/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.rest;

import java.util.List;
import javax.ws.rs.core.MultivaluedMap;

/**
 *
 * @author Gonzalo Torres
 * @param <T>
 */
public interface IConsumeREST<T> {

    T postRestAPI(String operation, MultivaluedMap params, Object postBody);

    T getRestAPI(String operation, MultivaluedMap params);

    List<T> getListRestAPI(String operation, MultivaluedMap params);

    T putRestAPI(String operation, MultivaluedMap params, Class<?> clazz, Object postBody);

    T delRestAPI(String operation, MultivaluedMap params);

    T getToken(String username, String password);
}
