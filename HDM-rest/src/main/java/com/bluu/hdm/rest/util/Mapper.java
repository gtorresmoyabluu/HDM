/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Gonzalo Torres
 */
@Configuration
public class Mapper {

    private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Bean
    ObjectMapper objectMapper() {
	ObjectMapper om = new ObjectMapper();
	om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	om.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
	om.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	om.setDateFormat(df);
	om.getDeserializationConfig().with(df);
	return om;
    }
}
