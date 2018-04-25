package com.bluu.hdm.web.util.error;

import javax.faces.context.ExceptionHandlerFactory;

import org.apache.log4j.Logger;

public class RuntimeExceptionHandlerFactory extends ExceptionHandlerFactory {

    //@Inject
    private Logger logger;

    private final ExceptionHandlerFactory parent;

    public RuntimeExceptionHandlerFactory(ExceptionHandlerFactory parent) {
	this.parent = parent;
    }

    @Override
    public RuntimeExceptionHandler getExceptionHandler() {
	return new RuntimeExceptionHandler(parent.getExceptionHandler(), logger);
    }
}
