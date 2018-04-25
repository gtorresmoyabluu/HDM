package com.bluu.hdm.web.exporter;

import com.bluu.hdm.web.converter.ConverterText;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.component.UIData;
import javax.faces.component.ValueHolder;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.primefaces.component.api.UIColumn;
import org.primefaces.component.column.Column;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.config.PrimeConfiguration;
import org.primefaces.context.RequestContext;

public abstract class DataTableExporter {

    private static final Object UNDEFINED_VALUE = new Object();

    private static List<List<String>> getAllRows(DataTable table) {
	final int first = table.getFirst();
	final int pageRows = table.getRows();
	final int rowCount = table.getRowCount();
	final boolean lazy = table.isLazy();

	final List<List<String>> rows = new ArrayList<>();
	if (lazy) {
	    if (rowCount > 0) {
		table.setFirst(0);
		table.setRows(rowCount);
		table.clearLazyCache();
		table.loadLazyData();
	    }

	    for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
		rows.add(getRow(table, rowIndex));
	    }

	    // restore
	    table.setFirst(first);
	    table.setRows(pageRows);
	    table.setRowIndex(-1);
	    table.clearLazyCache();
	    table.loadLazyData();
	} else {
	    for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
		rows.add(getRow(table, rowIndex));
	    }

	    // restore
	    table.setFirst(first);
	}

	return rows;
    }

    public static String getValueToRender(FacesContext context, UIComponent component, Object value) {
	if (component instanceof ValueHolder) {

	    if (component instanceof EditableValueHolder) {
		EditableValueHolder input = (EditableValueHolder) component;
		Object submittedValue = input.getSubmittedValue();
		PrimeConfiguration config = RequestContext.getCurrentInstance().getApplicationContext().getConfig();

		if (config.isInterpretEmptyStringAsNull() && submittedValue == null && !input.isLocalValueSet()
			&& context.isValidationFailed() && !input.isValid()) {
		    return null;
		} else if (submittedValue != null) {
		    return submittedValue.toString();
		}
	    }

	    ValueHolder valueHolder = (ValueHolder) component;
	    if (value == UNDEFINED_VALUE) {
		value = valueHolder.getValue();
	    }

	    // format the value as string
	    if (value != null) {
		Converter converter = valueHolder.getConverter();
		if (converter == null || converter.getClass().getSimpleName().equals(ConverterText.class.getSimpleName())) {
		    Class<? extends Object> valueType = value.getClass();
		    if (valueType == String.class && !RequestContext.getCurrentInstance().getApplicationContext().getConfig()
			    .isStringConverterAvailable()) {
			String ret = value.toString().replaceAll("\r\n", " ");
			ret = ret.toString().replaceAll("  ", " ");
			return ret;
		    }

		    converter = context.getApplication().createConverter(valueType);
		}

		if (converter != null) {
		    return converter.getAsString(context, component, value);
		}
		return value.toString(); // Use toString as a fallback if there is no explicit or implicit converter
	    }
	    // component is a value holder but has no value
	    return null;
	}

	// component it not a value holder
	return null;
    }

    private static String getColumnValue(UIComponent component) {
	return component == null ? "" : getValueToRender(FacesContext.getCurrentInstance(), component, UNDEFINED_VALUE);
    }

    private static String getColumnValue(List<UIComponent> components) {
	final StringBuilder sb = new StringBuilder();
	for (final UIComponent component : components) {
	    sb.append(getValueToRender(FacesContext.getCurrentInstance(), component, UNDEFINED_VALUE));
	}

	return sb.toString();
    }

    private static List<UIColumn> getColumnsToExport(UIData table) {
	final List<UIColumn> columnsToExport = new ArrayList<>();
	for (final UIComponent component : table.getChildren()) {
	    if ((component instanceof UIColumn) && component.isRendered() && ((UIColumn) component).isVisible()
		    && ((UIColumn) component).isExportable()) {
		columnsToExport.add((UIColumn) component);
	    }
	}

	return columnsToExport;
    }

    public static DataExporter getDataExporter(DataTable table, boolean pageOnly) {
	try {
	    // Obtienen las cabeceras
	    final List<String> header = getHeader(table);

	    // Obtiene los valores
	    final List<List<String>> rows = pageOnly ? getPageRows(table) : getAllRows(table);

	    // Devuelve el objeto a exportar
	    return new TabularDataExporter(header, rows);
	} catch (final Exception ex) {
	    throw new RuntimeException(ex);
	}
    }

    private static List<String> getHeader(DataTable table) {
	final List<String> headers = new ArrayList<>();
	for (final UIColumn column : getColumnsToExport(table)) {
	    headers.add(getColumnValue(((Column) column).getHeader()));
	}

	return headers;
    }

    private static List<List<String>> getPageRows(DataTable table) {
	final int first = table.getFirst();
	int pageRows = table.getRows();
	if (pageRows == 0) {
	    pageRows = table.getRowCount();
	}

	final List<List<String>> rows = new ArrayList<>();
	final int rowsToExport = first + pageRows;
	for (int rowIndex = first; rowIndex < rowsToExport; rowIndex++) {
	    rows.add(getRow(table, rowIndex));
	}

	return rows;
    }

    private static List<String> getRow(DataTable table, int rowIndex) {
	table.setRowIndex(rowIndex);
	if (!table.isRowAvailable()) {
	    return null;
	}

	final List<String> row = new ArrayList<>();
	for (final UIColumn column : getColumnsToExport(table)) {
	    row.add(getColumnValue(column.getChildren()));
	}

	return row;
    }

}
