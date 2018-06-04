package com.bluu.hdm.web.model;

import com.bluu.hdm.web.pojo.administracion.Role;
import com.bluu.hdm.web.pojo.inventory.Model;
import com.bluu.hdm.web.rest.ConsumeREST;
import com.bluu.hdm.web.rest.FactoryRest;
import com.bluu.hdm.web.rest.IConsumeREST;
import com.bluu.hdm.web.util.GetClassUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.primefaces.model.SortOrder;

public class APILazyDataModel extends LazyDataModel<Object> implements Serializable {

    private static final long serialVersionUID = 1L;
    private String operationAPI;
    private String filterProperty = "";
    private Logger logger;
    private static ObjectMapper mapper;
    private List<Object> datasource;
    private Class<?> clazz;
    private String beanClazz;

    public APILazyDataModel(Class<?> clazz, String operation, String beanClazz) {
        this.mapper = new ObjectMapper();
        this.operationAPI = operation;
        this.clazz = clazz;
        this.beanClazz = beanClazz;
    }

    private Object getKeyValue(Object object) {
        try {
            Method method = this.clazz.getMethod("getId");
            return method.invoke(object);
        } catch (final IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException ex) {
            logger.error(null, ex);
            return null;
        }
    }

    @Override
    public Object getRowKey(Object object) {
        return String.valueOf(getKeyValue(object));
    }

    @Override
    public Object getRowData(String rowKey) {
        Object object = FactoryRest.getInstance().getRestAPI(String.format("%s/find/%s", this.beanClazz.toLowerCase(), rowKey));
        if (object != null) {
            return mapper.convertValue(object, GetClassUtils.getModelClass(this.clazz));
        }
        return null;
    }

    @Override
    public List<Object> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        boolean isColHasMap = false;
        List<Object> data = new ArrayList<>();
        if (this.datasource == null) {
            this.datasource = mapper.convertValue(
                    FactoryRest.getInstance().getListRestAPI(this.operationAPI),
                    new TypeReference<List<Object>>() {
            });
            if (this.datasource == null) {
                return null;
            }
        }

        //filter
        for (Object obj : this.datasource) {
            boolean match = true;
            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        filterProperty = it.next().trim();
                        Object filterValue = filters.get(filterProperty.trim());
                        String fieldValue = "";
                        HashMap<String, Object> map = (HashMap<String, Object>) obj;

                        if (filterProperty.equals("idRole")) {
                            Object objRole = map.entrySet().stream()
                                    .filter(x -> filterProperty.equals(x.getKey()))
                                    .map(x -> x.getValue())
                                    .findAny().get();

                            Role rol = mapper.convertValue(objRole, Role.class);
                            fieldValue = rol.getName().trim();
                            isColHasMap = true;
                        } else if (filterProperty.equals("idModel")) {
                            Object objModel = map.entrySet().stream()
                                    .filter(x -> filterProperty.equals(x.getKey()))
                                    .map(x -> x.getValue())
                                    .findAny().get();

                            Model model = mapper.convertValue(objModel, Model.class);
                            fieldValue = model.getName().trim();
                            isColHasMap = true;
                        } else {
                            fieldValue = map.entrySet().stream()
                                    .filter(x -> filterProperty.equals(x.getKey()))
                                    .map(x -> x.getValue())
                                    .findAny().get().toString();
                            isColHasMap = false;
                        }
                        if (filterValue == null || fieldValue.contains(filterValue.toString().toLowerCase()) || fieldValue.contains(filterValue.toString().toUpperCase())) {
                            match = true;
                        } else {
                            match = false;
                            isColHasMap = false;
                            break;
                        }
                    } catch (IllegalArgumentException | SecurityException e) {
                        logger.error("Error: " + e.getMessage());
                        match = false;
                        isColHasMap = false;
                    }
                }
            }
            if (match) {
                data.add(obj);
            }
        }
        //sort
        if (sortField != null) {
            data = mapper.convertValue(data, new TypeReference<List<Object>>() {
            });
            if (sortField.equals("idRole")) {
                isColHasMap = true;
            }
            if (sortField.equals("idModel")) {
                isColHasMap = true;
            }
            Collections.sort(data, new LazySorter(sortField, sortOrder, isColHasMap));
        }

        //rowCount
        int dataSize = data.size();

        this.setRowCount(dataSize);
        data = GetClassUtils.castToList(this.clazz, data);

        this.setWrappedData(data);
        //paginate
        if (dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            } catch (IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        } else {
            return data;
        }
    }
}
