/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.model;

import com.bluu.hdm.web.pojo.Role;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Comparator;
import java.util.HashMap;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Gonzalo Torres
 */
public class LazySorter implements Comparator<Object> {

    private String sortField;
    private boolean isColHashMap;
    private SortOrder sortOrder;
    private ObjectMapper mapper;

    public LazySorter(String sortField, SortOrder sortOrder, boolean isColHashMap) {
	this.sortField = sortField;
	this.sortOrder = sortOrder;
	this.isColHashMap = isColHashMap;
	this.mapper = new ObjectMapper();
    }

    @Override
    public int compare(Object obj1, Object obj2) {
	try {
	    HashMap<String, Object> map1 = (HashMap<String, Object>) obj1;
	    HashMap<String, Object> map2 = (HashMap<String, Object>) obj2;

	    Object value1 = map1.entrySet().stream()
		    .filter(x -> this.sortField.equals(x.getKey()))
		    .map(x -> x.getValue())
		    .findAny().get();

	    Object value2 = map2.entrySet().stream()
		    .filter(x -> this.sortField.equals(x.getKey()))
		    .map(x -> x.getValue())
		    .findAny().get();

	    if (isColHashMap) {
		if (this.sortField.equals("idRole")) {
		    value1 = mapper.convertValue(value1, Role.class).getId();
		    value2 = mapper.convertValue(value2, Role.class).getId();
		}
	    }

	    int value = ((Comparable) value1).compareTo(value2);

	    return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
	} catch (IllegalArgumentException | SecurityException e) {
	    throw new RuntimeException();
	}
    }
}
