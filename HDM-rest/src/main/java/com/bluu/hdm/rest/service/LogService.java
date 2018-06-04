/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.service;

import com.bluu.hdm.rest.dao.interfaces.ILogDAO;
import com.bluu.hdm.rest.enums.ProcessLog;
import com.bluu.hdm.rest.vo.LogVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Marco Valdés
 */
@RestController
@RequestMapping("/v1/logviewer")
public class LogService {

    public static Logger logger = LogManager.getLogger(LogService.class.getName());

    public static final String tmpDir = System.getProperty("user.dir") + "/logs/";

    @Autowired
    ILogDAO logService;

    @Autowired
    ObjectMapper mapper;

    /* @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Void> save(@RequestBody LogVO logRequest, UriComponentsBuilder ucBuilder) {
	//if (logRequest.getId() != null) {
	    /*if (logService.isExist(logRequest.getId())) {
		logger.error("Registration" + logRequest.getId() + " already exist");
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	    }*/
    //}
    //LogEntity entity = mapper.convertValue(logRequest, LogEntity.class);
    //logService.save(entity);

    /*HttpHeaders headers = new HttpHeaders();
	//headers.setLocation(ucBuilder.path("/find/{id}").buildAndExpand(entity.getId()).toUri());
	return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
    /**
     * Get All Log
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<LogVO>> getAll() throws IOException {
        List<LogVO> response = new ArrayList<>();
        ProcessLog proc = null;
        try {
            proc = new ProcessLog();
            List<LogVO> source = proc.findAllLogEntries(tmpDir); //, "schaman.log");
            //     List<LogVO> source2 = proc.findAllLogEntries("D://Logs/app_WEB.log");  
            if (source.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);// You many decide to return
            } else {
                source.forEach((logEntity) -> {
                    response.add(mapper.convertValue(logEntity, LogVO.class));
                });

                return new ResponseEntity<>(response, HttpStatus.OK);
            }

        } catch (IllegalArgumentException ex) {
            logger.error(String.format("Error: %s", ex.getMessage()));
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);// You many decide to return HttpStatus.NOT_FOUND
    }

    /* @RequestMapping(value = "/addLogToStack", method = RequestMethod.POST)
      public ResponseEntity<Void> addLogToStack(@RequestBody LogVO logRequest,Stack<LogEntry> entries, UriComponentsBuilder ucBuilder, boolean firstLineReaded, LogSeverity severity,
              LogSeverity minSeverity, String msgFilter, String message, String timestamp, String clazz) {
     
       if (firstLineReaded
            && (severity == LogSeverity.FATAL
                || (severity == LogSeverity.ERROR
                    && (minSeverity == LogSeverity.ERROR || minSeverity == LogSeverity.WARN || minSeverity == LogSeverity.INFO))
                || (severity == LogSeverity.WARN && (minSeverity == LogSeverity.WARN || minSeverity == LogSeverity.INFO))
                || (severity == LogSeverity.INFO && minSeverity == LogSeverity.INFO))
            && (StringUtils.isBlank(msgFilter) || message.toLowerCase().contains(msgFilter.toLowerCase()))) {

            // Si la pila es mayor de 300 saco la primera para no tener nunca más de 300 trazas
            if (entries.size() >= 300) {
                entries.remove(0);
            }

            // Añado a la pila la nueva entrada
            entries.push(new LogEntry(severity, timestamp, clazz, message));
        }
        HttpHeaders headers = new HttpHeaders();
	headers.setLocation(ucBuilder.path("/find/{id}").buildAndExpand(entries).toUri());
	return new ResponseEntity<>(headers, HttpStatus.CREATED);
  
    }*/
}
