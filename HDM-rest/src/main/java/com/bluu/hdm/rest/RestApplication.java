package com.bluu.hdm.rest;

import com.bluu.hdm.rest.service.LogService;
import com.bluu.hdm.rest.service.firmware.FirmwareService;
import java.io.File;
import javax.servlet.MultipartConfigElement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
@EnableAutoConfiguration
@ComponentScan
public class RestApplication extends SpringBootServletInitializer {

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("200MB");
        factory.setMaxRequestSize("200MB");
        return factory.createMultipartConfig();
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(RestApplication.class);
    }

    public static void main(String[] args) {
        new File(FirmwareService.tmpDir).mkdirs();
        new File(LogService.tmpDir).mkdirs();
        SpringApplication.run(RestApplication.class, args);
    }
}
