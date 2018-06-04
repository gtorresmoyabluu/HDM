/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.rest;

import java.util.List;

/**
 *
 * @author Gonzalo Torres
 * @param <T>
 */
public interface IConsumeREST<T> {

    T postRestAPI(String operation, Object postBody);

    T getRestAPI(String operation);

    List<T> getListRestAPI(String operation);

    T putRestAPI(String operation, Class<?> clazz, Object postBody);
}
