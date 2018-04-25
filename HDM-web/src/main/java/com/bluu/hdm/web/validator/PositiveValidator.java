package com.bluu.hdm.web.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Validador de inputs que no sean null o vacíos (trim)
 */
@FacesValidator(PositiveValidator.BEAN_NAME)
public class PositiveValidator implements Validator {

    public static final String BEAN_NAME = "positiveValidator";

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
	if (((Long) o) <= 0) {
	    throw new ValidatorException(
		    new FacesMessage(
			    FacesMessage.SEVERITY_ERROR,
			    (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("validator_positive"),
			    null));
	}
    }
}
