package com.bluu.hdm.web.face;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import org.apache.commons.lang3.text.WordUtils;

@SessionScoped
@Named(LocaleFace.BEAN_NAME)
public class LocaleFace implements Serializable {

    public static final String BEAN_NAME = "locale";
    private static final long serialVersionUID = 1L;

    private Locale current;

    @PostConstruct
    public void init() {
	current = new Locale("es");//configurationStBean.getLocale();
    }

    public List<SelectItem> getAvailableLocales() {
	Locale es = new Locale("es");

	List<SelectItem> result = new ArrayList<>();
	result.add(new SelectItem(es, WordUtils.capitalize(es.getDisplayLanguage(es))));

	return result;
    }

    public Locale getCurrent() {
	return current;
    }

    public void setCurrent(Locale locale) {
	current = locale;
	FacesContext.getCurrentInstance().getViewRoot().setLocale(current);
    }

    public void updateLocale() {
	FacesContext.getCurrentInstance().getViewRoot().setLocale(current);
    }
}
