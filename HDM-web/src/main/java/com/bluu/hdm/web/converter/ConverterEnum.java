package com.bluu.hdm.web.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "converterEnum")
public class ConverterEnum implements Converter {

    private static final String ATTRIBUTE_ENUM_TYPE = "ConverterEnum.enumType";

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        final Class<Enum> enumType = (Class<Enum>) component.getAttributes().get(ATTRIBUTE_ENUM_TYPE);
        try {
            return Enum.valueOf(enumType, value);
        } catch (final IllegalArgumentException e) {
            throw new ConverterException(new FacesMessage("Value is not an enum of type: " + enumType));
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof Enum) {
            component.getAttributes().put(ATTRIBUTE_ENUM_TYPE, value.getClass());
            return ((Enum<?>) value).name();
        }
        
        throw new ConverterException(new FacesMessage("Value is not an enum: " + value.getClass()));
    }

}
