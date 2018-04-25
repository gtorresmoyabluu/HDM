package com.bluu.hdm.web.converter;

import com.bluu.hdm.web.rest.ConsumeREST;
import com.bluu.hdm.web.rest.IConsumeREST;
import com.bluu.hdm.web.util.AuthorizationUtil;
import com.bluu.hdm.web.util.GetClassUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.ws.rs.core.MultivaluedMap;

@FacesConverter(value = "pojoConverter")
@RequestScoped
public class PojoConverter implements Converter {

    private static final String UIC_ID_SUFFIX = "pojoConv_";
    private static IConsumeREST apiRest;
    private static ObjectMapper mapper;
    private static MultivaluedMap params;

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
	if (obj != null && !"".equals(obj)) {
	    String id;
	    try {
		if ((obj.getClass().getMethod("getId")) != null) {
		    Object o = (obj.getClass().getMethod("getId")).invoke(obj);
		    id = o != null ? o.toString() : null;
		    if (id == null) {
			id = "";
		    }
		    id = UIC_ID_SUFFIX + id.trim();
		    component.getAttributes().put(id, obj.getClass().cast(obj));
		    return id;
		}
	    } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException ignore) {
		throw new RuntimeException(ignore);
	    }
	}
	return null;
    }

    private Object getObject(Class<?> type, String value) {
	String operAPI = "";
	apiRest = new ConsumeREST();
	mapper = new ObjectMapper();
	switch (type.getSimpleName().toLowerCase()) {
	    case "user":
		operAPI = "users";
		break;
	    case "role":
		operAPI = "roles";
		break;
	    default:
		operAPI = type.getSimpleName().toLowerCase();
		break;
	}
	if (!operAPI.isEmpty()) {

	    params = new MultivaluedMapImpl();
	    params.add(
		    "access_token",
		    AuthorizationUtil.getUserSession(FacesContext.getCurrentInstance()).getUser().getToken().getAccess_token()
	    );
	    try {
		Object obj = apiRest.getRestAPI(String.format("%s/find/%s", operAPI, value.replaceAll(UIC_ID_SUFFIX, "")), params);
		Constructor<?> constructor = type.getDeclaredConstructor(GetClassUtils.getModelClass(type));
		constructor.setAccessible(true);
		Object modelInt = mapper.convertValue(obj, GetClassUtils.getModelClass(type));
		return constructor.newInstance(modelInt);
	    } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
		Logger.getLogger(PojoConverter.class.getName()).log(Level.SEVERE, null, ex);
		return null;
	    }
	} else {
	    return null;
	}
    }
}
