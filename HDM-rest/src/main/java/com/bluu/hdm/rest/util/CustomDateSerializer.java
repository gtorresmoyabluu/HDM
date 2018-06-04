/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Gonzalo Torres
 */
public class CustomDateSerializer extends StdSerializer<Date> {

    private SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US);

    public CustomDateSerializer() {
	this(null);
    }

    public CustomDateSerializer(Class t) {
	super(t);
    }

    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider arg2) throws IOException {
	gen.writeString(formatDate.format(value));
    }

}
