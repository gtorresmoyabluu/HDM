/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.service.firmware;

import com.bluu.hdm.rest.dao.interfaces.firmware.IFirmwareDAO;
import com.bluu.hdm.rest.dao.interfaces.inventory.IManufacturerDAO;
import com.bluu.hdm.rest.dao.interfaces.inventory.IModelDAO;
import com.bluu.hdm.rest.entity.FirmwareEntity;
import com.bluu.hdm.rest.service.generic.IService;
import com.bluu.hdm.rest.vo.firmware.FirmwareVO;
import com.bluu.hdm.rest.vo.inventory.ManufacturerVO;
import com.bluu.hdm.rest.vo.inventory.ModelVO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Gonzalo Torres
 */
@RestController
@RequestMapping("/v1/firmware")
public class FirmwareService implements IService<FirmwareVO, Long> {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private IFirmwareDAO firmwareService;

    @Autowired
    private IManufacturerDAO manufacturerService;

    @Autowired
    private IModelDAO modelService;

    public static final String tmpDir = System.getProperty("user.dir") + "/tmpFirmwares/";

    private static Logger logger = LogManager.getLogger(FirmwareService.class.getName());
    @Value("${server.ftp.domain}")
    private String ftpDomain;
    @Value("${server.ftp.port}")
    private String ftpPort;
    @Value("${server.ftp.path}")
    private String ftpPath;
    @Value("${server.ftp.user}")
    private String ftpUser;
    @Value("${server.ftp.pswd}")
    private String ftpPasswd;

    @RequestMapping(value = "/upload", headers = ("content-type=multipart/*"), method = RequestMethod.POST)
    public ResponseEntity<Void> save(
            @RequestPart("properties") String oRequestString,
            @RequestParam("file") MultipartFile inputFile,
            UriComponentsBuilder ucBuilder) {
        boolean isUploadFile = false;
        FTPClient ftpClient = new FTPClient();
        ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        FileInputStream fis = null;
        File localFile = null;
        ManufacturerVO manufacturer = new ManufacturerVO();
        ModelVO model = new ModelVO();
        FirmwareVO oRequest = new FirmwareVO();
        try {
            JsonNode node = null;
            try {
                node = mapper.readTree(oRequestString);
            } catch (IOException ex) {
                logger.error(String.format("Error: %s", ex.getMessage()));
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            oRequest = new FirmwareVO(node.get("name").textValue(), node.get("model").textValue(), node.get("manufacturer").textValue());

            manufacturer = mapper.convertValue(manufacturerService.findById(oRequest.getIdModel().getManufacturer().getId()).get(), ManufacturerVO.class);
            model = mapper.convertValue(modelService.findById(oRequest.getIdModel().getId()).get(), ModelVO.class);

            localFile = new File(String.format("%s%s", tmpDir, inputFile.getOriginalFilename()));
            inputFile.transferTo(localFile);
            fis = new FileInputStream(localFile);

            ftpClient.connect(ftpDomain, Integer.parseInt(ftpPort));
            int reply = ftpClient.getReplyCode();
            if (FTPReply.isPositiveCompletion(reply)) {
                logger.info("Conectado Satisfactoriamente");
            } else {
                logger.error("Imposible conectarse al servidor");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            boolean success = ftpClient.login(ftpUser, ftpPasswd);
            if (!success) {
                logger.error("Could not login to the server");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                logger.info("Logged in Server FTP");
            }
            //Verificar si se cambia de directorio de trabajo
            if (!ftpClient.changeWorkingDirectory(ftpPath)) {
                ftpClient.makeDirectory(ftpPath);
                //Cambiar directorio de trabajo
                ftpClient.changeWorkingDirectory(ftpPath);
            }else{
                //Cambiar directorio de trabajo
                ftpClient.changeWorkingDirectory(ftpPath);
            }
            if (!ftpClient.changeWorkingDirectory(manufacturer.getName().replace(" ", "_"))) {
                ftpClient.makeDirectory(manufacturer.getName().replace(" ", "_"));
                //Cambiar directorio de trabajo
                ftpClient.changeWorkingDirectory(manufacturer.getName().replace(" ", "_"));
            }
            else{
                //Cambiar directorio de trabajo
                ftpClient.changeWorkingDirectory(manufacturer.getName().replace(" ", "_"));
            }
            if (!ftpClient.changeWorkingDirectory(model.getName().replace(" ", "_"))) {
                ftpClient.makeDirectory(model.getName().replace(" ", "_"));
                //Cambiar directorio de trabajo
                ftpClient.changeWorkingDirectory(model.getName().replace(" ", "_"));
            }
            else{
                //Cambiar directorio de trabajo
                ftpClient.changeWorkingDirectory(model.getName().replace(" ", "_"));
            }

            //Activar que se envie cualquier tipo de archivo
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            // ftpClient.setFileType(FTP.ASCII_FILE_TYPE);
            BufferedInputStream buffIn = null;
            buffIn = new BufferedInputStream(fis);//Ruta del archivo para enviar
            ftpClient.enterLocalPassiveMode();
            ftpClient.storeFile(localFile.getName(), buffIn);//Ruta completa de alojamiento en el FTP
            buffIn.close(); //Cerrar envio de arcivos al FTP
            isUploadFile = (ftpClient.getReplyCode() == 226); //226 Transfer complete.
        } catch (IOException | IllegalStateException e) {
            logger.error("Error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
                if (localFile.exists()) {
                    localFile.delete();
                }
            } catch (IOException e) {
                logger.error(String.format("Error: %s", e.getMessage()));
                isUploadFile = false;
            }
        }

        if (isUploadFile) {

            FirmwareEntity oSearch = firmwareService.findByname(oRequest.getName()).orElse(null);
            if (oSearch != null) {
                oSearch.setUpdateDate(java.sql.Date.valueOf(LocalDate.now()));
            } else {
                oRequest.setUrlFTP(String.format("ftp://%s/%s/%s/%s/%s", ftpDomain, ftpPath, manufacturer.getName().replace(" ", "_"), model.getName().replace(" ", "_"), localFile.getName()));
                oRequest.setCreationDate(java.sql.Date.valueOf(LocalDate.now()));
            }
            FirmwareEntity entity = oSearch == null ? mapper.convertValue(oRequest, FirmwareEntity.class) : oSearch;
            firmwareService.save(entity);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/find/{id}").buildAndExpand(entity.getId()).toUri());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
            //return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Void> save(FirmwareVO oRequest, UriComponentsBuilder ucBuilder) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResponseEntity<FirmwareVO> update(FirmwareVO oRequest) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResponseEntity<FirmwareVO> delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResponseEntity<FirmwareVO> deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<FirmwareVO> findById(@PathVariable(value = "id") Long id) {
        try {
            FirmwareEntity source = firmwareService.findById(id).get();
            if (source != null) {
                return new ResponseEntity<>(mapper.convertValue(source, FirmwareVO.class), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException ex) {
            logger.error(String.format("Error: %s", ex.getMessage()));
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public ResponseEntity<List<FirmwareVO>> getAll() {
        List<FirmwareVO> response = new ArrayList<>();
        try {
            List<FirmwareEntity> source = firmwareService.findAll();
            if (source.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);// You many decide to return
                // HttpStatus.NOT_FOUND
            } else {
                source.forEach((FirmwareEntity firmwareEntity) -> {
                    FirmwareVO user = mapper.convertValue(firmwareEntity, FirmwareVO.class);
                    response.add(user);
                });
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (IllegalArgumentException ex) {
            logger.error(String.format("Error: %s", ex.getMessage()));
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);// You many decide to return HttpStatus.NOT_FOUND
    }

//    public ObjectMapper getMapper() {
//        mapper = new ObjectMapper();    
//        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
//        return mapper;
//    }
//
//    public void setMapper(ObjectMapper mapper) {
//        this.mapper = mapper;
//    }
}
