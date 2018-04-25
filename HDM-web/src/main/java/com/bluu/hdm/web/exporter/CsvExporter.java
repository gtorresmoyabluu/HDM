package com.bluu.hdm.web.exporter;

import com.bluu.hdm.web.enums.ExporterFormatEnum;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class CsvExporter extends BaseExporter {

    private static final char SEPARATOR = ';';

    CsvExporter(ExporterFormatEnum exporterType) {
	super(exporterType);
    }

    @Override
    protected byte[] getFileContent(DataExporter data) {
	if (!(data instanceof TabularDataExporter)) {
	    throw new RuntimeException("DataExporter inside CsvExporter must be TabularDataExporter");
	}

	try {
	    final StringBuilder sb = new StringBuilder();

	    // Añade los encabezados
	    final List<String> headers = ((TabularDataExporter) data).getHeaders();
	    if (headers != null) {
		sb.append(StringUtils.join(headers, SEPARATOR)).append("\n");
	    }

	    // Añade los datos
	    final List<List<String>> dat = ((TabularDataExporter) data).getData();
	    if (dat != null) {
		for (final List<String> row : ((TabularDataExporter) data).getData()) {
		    if (row != null) {
			final List<String> rowToExport = new ArrayList<>();
			for (String column : row) {
			    if (column.contains("" + SEPARATOR)) {
				column = "\"" + column + "\"";
			    }

			    rowToExport.add(column);
			}

			sb.append(StringUtils.join(rowToExport, SEPARATOR)).append("\n");
		    }
		}
	    }

	    // Devuelve los bytes del contenido del CSV
	    return sb.toString().endsWith("\n") ? sb.toString().substring(0, sb.toString().length() - 1).getBytes("UTF-8")
		    : sb.toString().getBytes("UTF-8");
	} catch (final UnsupportedEncodingException ex) {
	    throw new RuntimeException(ex);
	}

    }
}
