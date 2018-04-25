package com.bluu.hdm.web.validator;

import com.bluu.hdm.web.util.MessageUtils;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang3.StringUtils;

/**
 * Validación de entradas que no son nulas o vacías (trim)
 */
@FacesValidator(NotEmptyValidator.BEAN_NAME)
public class NotEmptyValidator implements Validator {

    public static final String BEAN_NAME = "notEmptyValidator";

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
	if (StringUtils.isBlank(o.toString())) {
	    throw new ValidatorException(
		    new FacesMessage(
			    FacesMessage.SEVERITY_ERROR,
			    (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("validator_notempty"),
			     null));
	}
    }
}
