package org.primefaces.ultima.component.menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.primefaces.component.api.AjaxSource;
import org.primefaces.component.api.UIOutcomeTarget;
import org.primefaces.component.menu.AbstractMenu;
import org.primefaces.component.menu.BaseMenuRenderer;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.model.menu.Separator;
import org.primefaces.model.menu.Submenu;
import org.primefaces.util.ComponentUtils;
import org.primefaces.util.WidgetBuilder;

public class UltimaMenuRenderer extends BaseMenuRenderer {

    protected void encodeElement(FacesContext context, AbstractMenu menu, MenuElement element) throws IOException {
	final ResponseWriter writer = context.getResponseWriter();

	if (element.isRendered()) {
	    if (element instanceof MenuItem) {
		final MenuItem menuItem = (MenuItem) element;
		final String menuItemClientId = (menuItem instanceof UIComponent) ? menuItem.getClientId() : menu.getClientId(context) + "_" + menuItem.getClientId();
		final String containerStyle = menuItem.getContainerStyle();
		final String containerStyleClass = menuItem.getContainerStyleClass();

		writer.startElement("li", null);
		writer.writeAttribute("id", menuItemClientId, null);
		writer.writeAttribute("role", "menuitem", null);

		if (containerStyle != null) {
		    writer.writeAttribute("style", containerStyle, null);
		}
		if (containerStyleClass != null) {
		    writer.writeAttribute("class", containerStyleClass, null);
		}

		encodeMenuItem(context, menu, menuItem);

		writer.endElement("li");
	    } else if (element instanceof Submenu) {
		final Submenu submenu = (Submenu) element;
		final String submenuClientId = (submenu instanceof UIComponent) ? ((UIComponent) submenu).getClientId() : menu.getClientId(context) + "_" + submenu.getId();
		final String style = submenu.getStyle();
		final String styleClass = submenu.getStyleClass();

		writer.startElement("li", null);
		writer.writeAttribute("id", submenuClientId, null);
		writer.writeAttribute("role", "menuitem", null);

		if (style != null) {
		    writer.writeAttribute("style", style, null);
		}
		if (styleClass != null) {
		    writer.writeAttribute("class", styleClass, null);
		}

		encodeSubmenu(context, menu, submenu);

		writer.endElement("li");
	    } else if (element instanceof Separator) {
		encodeSeparator(context, (Separator) element);
	    }
	}
    }

    protected void encodeElements(FacesContext context, AbstractMenu menu, List<MenuElement> elements) throws IOException {
	final int size = elements.size();

	for (int i = 0; i < size; i++) {
	    encodeElement(context, menu, elements.get(i));
	}
    }

    protected void encodeItemIcon(FacesContext context, String icon) throws IOException {
	if (icon != null) {
	    final ResponseWriter writer = context.getResponseWriter();

	    writer.startElement("i", null);
	    writer.writeAttribute("class", "material-icons", null);
	    writer.writeText(icon, null);
	    writer.endElement("i");
	}
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void encodeMarkup(FacesContext context, AbstractMenu abstractMenu) throws IOException {
	final UltimaMenu menu = (UltimaMenu) abstractMenu;
	final ResponseWriter writer = context.getResponseWriter();
	final String style = menu.getStyle();
	String styleClass = menu.getStyleClass();
	styleClass = (styleClass == null) ? "ultima-menu clearfix" : "ultima-menu clearfix" + styleClass;

	writer.startElement("ul", menu);
	writer.writeAttribute("id", menu.getClientId(context), "id");
	writer.writeAttribute("class", styleClass, "styleClass");
	if (style != null) {
	    writer.writeAttribute("style", style, "style");
	}

	if (menu.getElementsCount() > 0) {
	    encodeElements(context, menu, menu.getElements());
	}

	writer.endElement("ul");
    }


    @Override
    @SuppressWarnings("deprecation")
    protected void encodeMenuItem(FacesContext context, AbstractMenu menu, MenuItem menuitem) throws IOException {
	final ResponseWriter writer = context.getResponseWriter();
	final String title = menuitem.getTitle();
	final boolean disabled = menuitem.isDisabled();
	final String style = menuitem.getStyle();
	final String styleClass = menuitem.getStyleClass();

	writer.startElement("a", null);
	if (title != null) {
	    writer.writeAttribute("title", title, null);
	}
	if (style != null) {
	    writer.writeAttribute("style", style, null);
	}
	if (styleClass != null) {
	    writer.writeAttribute("class", styleClass, null);
	}

	if (disabled) {
	    writer.writeAttribute("href", "#", null);
	    writer.writeAttribute("onclick", "return false;", null);
	} else {
	    String onclick = menuitem.getOnclick();

	    // GET
	    if (menuitem.getUrl() != null || menuitem.getOutcome() != null) {
		final String targetURL = getTargetURL(context, (UIOutcomeTarget) menuitem);
		writer.writeAttribute("href", targetURL, null);

		if (menuitem.getTarget() != null) {
		    writer.writeAttribute("target", menuitem.getTarget(), null);
		}
	    }
	    // POST
	    else {
		writer.writeAttribute("href", "#", null);

		final UIComponent form = ComponentUtils.findParentForm(context, menu);
		if (form == null) { throw new FacesException("MenuItem must be inside a form element"); }

		String command;
		if (menuitem.isDynamic()) {
		    final String menuClientId = menu.getClientId(context);
		    Map<String, List<String>> params = menuitem.getParams();
		    if (params == null) {
			params = new LinkedHashMap<>();
		    }
		    final List<String> idParams = new ArrayList<>();
		    idParams.add(menuitem.getId());
		    params.put(menuClientId + "_menuid", idParams);

		    command = menuitem.isAjax() ? buildAjaxRequest(context, menu, (AjaxSource) menuitem, form, params) : buildNonAjaxRequest(context, menu, form, menuClientId, params, true);
		} else {
		    command = menuitem.isAjax() ? buildAjaxRequest(context, (AjaxSource) menuitem, form)
		            : buildNonAjaxRequest(context, ((UIComponent) menuitem), form, ((UIComponent) menuitem).getClientId(context), true);
		}

		onclick = (onclick == null) ? command : onclick + ";" + command;
	    }

	    if (onclick != null) {
		writer.writeAttribute("onclick", onclick, null);
	    }
	}

	encodeMenuItemContent(context, menu, menuitem);

	writer.endElement("a");
    }

    @Override
    protected void encodeMenuItemContent(FacesContext context, AbstractMenu menu, MenuItem menuitem) throws IOException {
	final ResponseWriter writer = context.getResponseWriter();
	final String icon = menuitem.getIcon();
	final Object value = menuitem.getValue();

	encodeItemIcon(context, icon);

	if (value != null) {
	    writer.startElement("span", null);
	    writer.writeText(value, "value");
	    writer.endElement("span");
	}
    }

    @Override
    protected void encodeScript(FacesContext context, AbstractMenu abstractMenu) throws IOException {
	final UltimaMenu menu = (UltimaMenu) abstractMenu;
	final String clientId = menu.getClientId(context);
	final WidgetBuilder wb = getWidgetBuilder(context);
	wb.init("Ultima", menu.resolveWidgetVar(), clientId).finish();
    }

    @Override
    protected void encodeSeparator(FacesContext context, Separator separator) throws IOException {
	final ResponseWriter writer = context.getResponseWriter();
	final String style = separator.getStyle();
	String styleClass = separator.getStyleClass();
	styleClass = styleClass == null ? "Separator" : "Separator " + styleClass;

	// title
	writer.startElement("li", null);
	writer.writeAttribute("class", styleClass, null);
	if (style != null) {
	    writer.writeAttribute("style", style, null);
	}

	writer.endElement("li");
    }

    @SuppressWarnings("unchecked")
    protected void encodeSubmenu(FacesContext context, AbstractMenu menu, Submenu submenu) throws IOException {
	final ResponseWriter writer = context.getResponseWriter();
	final String icon = submenu.getIcon();
	final String label = submenu.getLabel();
	final int childrenElementsCount = submenu.getElementsCount();

	writer.startElement("a", null);
	writer.writeAttribute("href", "#", null);
	writer.writeAttribute("class", "ripplelink", null);

	encodeItemIcon(context, icon);

	if (label != null) {
	    writer.startElement("span", null);
	    writer.writeText(label, null);
	    writer.endElement("span");

	    writer.startElement("span", null);
	    writer.writeAttribute("class", "ink animate", null);
	    writer.endElement("span");

	    encodeToggleIcon(context, submenu, childrenElementsCount);
	}

	writer.endElement("a");

	// submenus and menuitems
	if (childrenElementsCount > 0) {
	    writer.startElement("ul", null);
	    writer.writeAttribute("role", "menu", null);
	    encodeElements(context, menu, submenu.getElements());
	    writer.endElement("ul");
	}
    }

    protected void encodeToggleIcon(FacesContext context, Submenu submenu, int childrenElementsCount) throws IOException {
	if (childrenElementsCount > 0) {
	    final ResponseWriter writer = context.getResponseWriter();

	    writer.startElement("i", null);
	    writer.writeAttribute("class", "material-icons", null);
	    writer.write("&#xE313;");
	    writer.endElement("i");
	}
    }

}
