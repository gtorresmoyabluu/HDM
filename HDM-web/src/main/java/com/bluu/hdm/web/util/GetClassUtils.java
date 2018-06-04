/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.util;

import com.bluu.hdm.web.enums.ClassEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *
 * @author Gonzalo Torres
 */
public abstract class GetClassUtils {

    private static Logger logger = LogManager.getLogger(GetClassUtils.class.getName());
    private static ObjectMapper mapper = new ObjectMapper();

    public static List<Object> castToList(Class<?> viewClass, List<Object> objList) {
        List<Object> result = new ArrayList<>();
        try {
            Class<?> modelClass = getModelClass(viewClass);
            if (objList == null) {
                result = new ArrayList<>(0);
            } else {
                try {// Se ejecuta la consulta
                    List<Object> list = objList;

                    // Se parsea la respuesta a objetos POJO
                    for (Object model : list) {
                        Constructor<?> constructor = viewClass.getDeclaredConstructor(modelClass);
                        constructor.setAccessible(true);
                        Object modelInt = mapper.convertValue(model, getModelClass(viewClass));
                        result.add(constructor.newInstance(modelInt));
                    }
                } catch (ReflectiveOperationException | IllegalArgumentException | SecurityException ex) {

                    logger.error(String.format("Error: %s", ex.getMessage()));
                    result = new ArrayList<>(0);
                }
            }
        } catch (Exception ex) {
            result = new ArrayList<>(0);
            logger.error(String.format("Error: %s", ex.getMessage()));
        }
        return result;
    }

    public static Object castToClass(Class<?> viewClass, Object objList) {
        Object result = new Object();
        try {
            Class<?> modelClass = getModelClass(viewClass);
            if (objList == null) {
                result = new Object();
            } else {
                try {// Se ejecuta la consulta
                    // Se parsea la respuesta a objetos POJO
                    Constructor<?> constructor = viewClass.getDeclaredConstructor(modelClass);
                    constructor.setAccessible(true);
                    Object modelInt = mapper.convertValue(objList, getModelClass(viewClass));
                    result = constructor.newInstance(modelInt);
                } catch (ReflectiveOperationException | IllegalArgumentException | SecurityException ex) {
                    logger.error(String.format("Error: %s", ex.getMessage()));
                    result = new Object();
                }
            }
        } catch (Exception ex) {
            logger.error(String.format("Error: %s", ex.getMessage()));
        }
        return result;
    }

    public static Class<?> getModelClass(Class<?> viewClass) {
        try {
            return ClassEnum.valueOf(viewClass.getSimpleName()).getClazz();
        } catch (Exception ex) {
            logger.error(String.format("Error: %s", ex.getMessage()));
            return null;
        }
    }
}
