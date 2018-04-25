package com.bluu.hdm.web.exporter;

import com.bluu.hdm.web.enums.ExporterFormatEnum;

public class ByteArrayExporter extends BaseExporter {

    public ByteArrayExporter(ExporterFormatEnum exporterType) {
	super(exporterType);
    }

    @Override
    protected byte[] getFileContent(DataExporter data) {
	if (!(data instanceof ByteArrayDataExporter)) {
	    throw new RuntimeException("DataExporter inside ByteArrayExporter must be ByteArrayDataExporter");
	}

	return ((ByteArrayDataExporter) data).getBytes();
    }
}
