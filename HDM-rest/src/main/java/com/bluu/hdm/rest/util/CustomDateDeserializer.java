/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Gonzalo Torres
 */
public class CustomDateDeserializer extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext) throws IOException {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US);
        String date = jsonparser.getText();
        try {
            return formatDate.parse(date);
        } catch (ParseException ex) {
            long timestamp = Long.parseLong(date);
            return new Date(timestamp);
        }
    }
}
