/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.rest;

import java.util.List;
import org.primefaces.model.UploadedFile;

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

    boolean delRestAPI(String operation);

    T getToken(String username, String password);

    T uploadFile(String operation, UploadedFile fileToUpload, Object postBody);
}
