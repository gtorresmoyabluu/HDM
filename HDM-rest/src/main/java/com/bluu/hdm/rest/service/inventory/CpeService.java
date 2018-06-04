/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.service.inventory;

import com.bluu.hdm.rest.dao.interfaces.firmware.IFirmwareDAO;
import com.bluu.hdm.rest.dao.interfaces.inventory.IManufacturerDAO;
import com.bluu.hdm.rest.dao.interfaces.inventory.IModelDAO;
import com.bluu.hdm.rest.entity.CpeEntity;
import com.bluu.hdm.rest.entity.FirmwareEntity;
import com.bluu.hdm.rest.entity.ManufacturerEntity;
import com.bluu.hdm.rest.entity.ModelEntity;
import com.bluu.hdm.rest.vo.inventory.CpeVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.bluu.hdm.rest.dao.interfaces.inventory.ICpeDAO;
import com.bluu.hdm.rest.vo.firmware.FirmwareVO;
import javax.persistence.NoResultException;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Marco Valdés
 */
@RestController
@RequestMapping("/v1/deviceviewer")
public class CpeService {

    private static Logger logger = LogManager.getLogger(CpeService.class.getName());

    // private HashMap<String, String> configurations;
    @Autowired
    ICpeDAO deviceService;

    @Autowired
    IModelDAO modelService;

    @Autowired
    IManufacturerDAO manufacturerService;

    @Autowired
    IFirmwareDAO firmwareService;

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
    public ResponseEntity<List<CpeVO>> getAll() throws IOException {

        List<CpeVO> response = new ArrayList<>();
        try {
            List<CpeEntity> source = deviceService.findAll();
            if (source.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);// You many decide to return
                // HttpStatus.NOT_FOUND
            } else {
                source.forEach((CpeEntity deviceEntity) -> {
                    ModelEntity model = modelService.findById(deviceEntity.getIdModel().getId()).get();
                    ManufacturerEntity manu = manufacturerService.findById(model.getManufacturer().getId()).get();

                    model.setManufacturer(manu);
                    deviceEntity.setIdModel(model);
                    CpeVO cpe = mapper.convertValue(deviceEntity, CpeVO.class);
                    Set<FirmwareVO> firms = null;
                    if (model.getFirmwares() != null) {
                        firms = new HashSet<>();
                        for (FirmwareEntity entity : model.getFirmwares()) {
                            firms.add(mapper.convertValue(entity,FirmwareVO.class));
                        }
                        cpe.getIdModel().setFirmwares(firms);
                    }
                    response.add(cpe);
                });

                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (IllegalArgumentException ex) {
            logger.error(String.format("Error: %s", ex.getMessage()));
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);// You many decide to return HttpStatus.NOT_FOUND
    }

    @RequestMapping(value = "/find/{idDevice}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<CpeVO> findById(@PathVariable(value = "idDevice") String idDevice) {
        logger.info("LLEGUE A BUSCAR POR ID");

        try {
            CpeEntity source = deviceService.findByidDevice(idDevice).get();
            if (source != null) {
                return new ResponseEntity<>(mapper.convertValue(source, CpeVO.class), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException ex) {
            logger.error(String.format("Error: %s", ex.getMessage()));
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/cwmpFault/{cwmpUser}/{faultCode}/{faultString}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void cwmpFault(@PathVariable(value = "cwmpUser") String cwmpUser, @PathVariable(value = "faultCode") String faultCode, @PathVariable(value = "faultString") String faultString) {
        try {
            CpeEntity cpe = (CpeEntity) deviceService.createNamedQuery("CpeEntity.findByUniqueConnectionUser").setParameter("usercomr", cwmpUser).getSingleResult();
            cpe.cwmpFault("Fault " + faultCode + ": " + faultString);
        } catch (NoResultException ex) {
            logger.error("Error (cwmpRegisterFault) " + cwmpUser + ": " + ex.getMessage());
        } catch (Exception ex) {
            logger.error("Error (cwmpRegisterFault) " + cwmpUser + ": " + ex.getMessage());
        }
    }

    @RequestMapping(value = "/cwmpResetUser/{cwmpId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void cwmpResetUser(@PathVariable(value = "cwmpId") String cwmpId) {
        try {
            CpeEntity cpe = (CpeEntity) deviceService.createNamedQuery("CpeEntity.findByCpeId").setParameter("cpeid", cwmpId).getSingleResult();
            cpe.cwmpResetUser();
        } catch (Exception ex) {
            logger.error("Error (cwmpInform): " + ex.getMessage());
        }
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
