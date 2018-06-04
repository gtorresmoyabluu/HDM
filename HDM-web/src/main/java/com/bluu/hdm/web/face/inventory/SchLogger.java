package com.bluu.hdm.web.face.inventory;

import java.io.Serializable;
import org.apache.log4j.Logger;
import com.bluu.sch.trb.logger.TrbLogger;

public class SchLogger extends TrbLogger implements Serializable {

    private static final long serialVersionUID = 1L;

  
    private transient Logger logger;

    @Override
    public void debug(String msg) {
        logger.debug(msg);
    }

    @Override
    public void error(String msg) {
        logger.error(msg);
    }

    @Override
    public void info(String msg) {
        logger.info(msg);
    }

    @Override
    public void warning(String msg) {
        logger.warn(msg);
    }
}
