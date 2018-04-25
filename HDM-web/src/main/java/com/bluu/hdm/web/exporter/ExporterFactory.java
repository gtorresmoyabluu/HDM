package com.bluu.hdm.web.exporter;

import com.bluu.hdm.web.enums.ExporterFormatEnum;

public class ExporterFactory {

    public static Exporter getInstance(ExporterFormatEnum type) {
	switch (type) {
	    case csv:
		return new CsvExporter(type);
	    case zip:
		return new ByteArrayExporter(type);
	    default:
		throw new RuntimeException("Type not recogniced");
	}
    }

}
