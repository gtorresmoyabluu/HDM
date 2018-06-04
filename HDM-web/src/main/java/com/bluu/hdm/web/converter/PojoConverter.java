package com.bluu.hdm.web.converter;

import com.bluu.hdm.web.rest.FactoryRest;
import com.bluu.hdm.web.util.GetClassUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

@FacesConverter(value = "pojoConverter")
@RequestScoped
public class PojoConverter implements Converter {

    private static final String UIC_ID_SUFFIX = "pojoConv_%s_";
    private static ObjectMapper mapper;
    private static Logger logger = LogManager.getLogger(PojoConverter.class.getName());
    
    @Override
    public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            Class<?> type = component.getValueExpression("value").getType(ctx.getELContext());
            Object o = getObject(type, value); // component.getAttributes().get(value);
            return o;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext ctx, UIComponent component, Object obj) {
        if (obj != null && !"".equals(obj) && !(obj instanceof ArrayList)) {
            String id;
            try {
                if ((obj.getClass().getMethod("getId")) != null) {
                    Object o = (obj.getClass().getMethod("getId")).invoke(obj);
                    if(o == null){
                        return null;
                    }
                    id = o != null ? o.toString() : null;
                    if (id == null) {
                        id = "";
                    }
                    Class<?> type = component.getValueExpression("value").getType(ctx.getELContext());
                    id = String.format(UIC_ID_SUFFIX, type.getSimpleName().toLowerCase()) + id.trim();
                    component.getAttributes().put(id, obj.getClass().cast(obj));
                    return id;
                }
            } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException ignore) {
                logger.error(String.format("Error: %s", ignore.getMessage()));
            }
        }
        return null;
    }

    private Object getObject(Class<?> type, String value) {
        String operAPI = "";
        mapper = new ObjectMapper();
        switch (type.getSimpleName().toLowerCase()) {
            case "user":
                operAPI = "users";
                break;
            case "role":
                operAPI = "roles";
                break;
            case "client":
                operAPI = "clients";
                break;
            case "manufacturer":
                operAPI = "manufacturers";
                break;
            case "model":
                operAPI = "models";
                break;
            default:
                operAPI = type.getSimpleName().toLowerCase();
                break;
        }
        if (!operAPI.isEmpty()) {
            try {
                if(value.replaceAll(String.format(UIC_ID_SUFFIX, type.getSimpleName().toLowerCase()), "").equals(value))
                    return null;
                Object obj = FactoryRest.getInstance().getRestAPI(String.format("%s/find/%s", operAPI, value.replaceAll(String.format(UIC_ID_SUFFIX, type.getSimpleName().toLowerCase()), "")));
                Constructor<?> constructor = type.getDeclaredConstructor(GetClassUtils.getModelClass(type));
                constructor.setAccessible(true);
                Object modelInt = mapper.convertValue(obj, GetClassUtils.getModelClass(type));
                return constructor.newInstance(modelInt);
            } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                logger.error(String.format("Error: %s", ex.getMessage()));
                return null;
            }
        } else {
            return null;
        }
    }
}
