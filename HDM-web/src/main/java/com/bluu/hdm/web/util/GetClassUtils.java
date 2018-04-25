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

/**
 *
 * @author Gonzalo Torres
 */
public abstract class GetClassUtils {

    private static ObjectMapper mapper;

    static {
	mapper = new ObjectMapper();
    }

    public static List<Object> castToList(Class<?> viewClass, List<Object> objList) {
	Class<?> modelClass = getModelClass(viewClass);
	List<Object> result = new ArrayList<>();
	try {// Se ejecuta la consulta
	    List<Object> list = objList;

	    // Se parsea la respuesta a objetos POJO
	    for (Object model : list) {
		Constructor<?> constructor = viewClass.getDeclaredConstructor(modelClass);
		constructor.setAccessible(true);
		Object modelInt = mapper.convertValue(model, getModelClass(viewClass));
		result.add(constructor.newInstance(modelInt));
	    }
	} catch (ReflectiveOperationException | IllegalArgumentException | SecurityException e) {
	    result = new ArrayList<>(0);
	}

	return result;
    }

    public static Class<?> getModelClass(Class<?> viewClass) {
	return ClassEnum.valueOf(viewClass.getSimpleName()).getClazz();
    }
}
