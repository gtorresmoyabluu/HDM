package com.bluu.hdm.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Conversor que reduce el tamaño de la cadena recibida a 100 caracteres. Si recibe un parámetro que indica la longitud
 * a reducir, se realizará de acuerdo con el valor de este parámetro.
 *
 */
@FacesConverter(value = "converterText")
public class ConverterText implements Converter {

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
	if (value != null) { return component.getAttributes().get(value); }
	return null;
    }

    @Override
    public String getAsString(FacesContext ctx, UIComponent component, Object obj) {

	int def = 100;

	if (obj != null && !"".equals(obj)) {

	    String id = null;

	    final boolean isFile = (component.getAttributes().get("file") != null ? Boolean.valueOf(component.getAttributes().get("file").toString()) : false);
	    final Object truncateLength = component.getAttributes().get("truncateLength");

	    if (truncateLength != null) {
		def = (Integer.valueOf(truncateLength.toString()));
	    }

	    if (obj.toString().length() > def) {
		String ext = "";
		if (isFile) {
		    ext = obj.toString().substring(obj.toString().lastIndexOf("."), obj.toString().length());
		}

		id = obj.toString().substring(0, def - (3 + ext.length())) + "..." + ext;
	    } else {
		id = obj.toString();
	    }
	    return id;
	}
	return null;
    }

}
