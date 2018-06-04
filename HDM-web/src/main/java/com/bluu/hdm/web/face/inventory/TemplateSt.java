/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.face.inventory;

import com.bluu.sch.trb.enums.ConnectivityProtocolEnm;
import com.bluu.sch.trb.exception.SerException;
import com.bluu.hdm.web.pojo.inventory.TemplateHDM;
import com.bluu.hdm.web.rest.FactoryRest;
import com.bluu.hdm.web.util.GetClassUtils;
import com.bluu.sch.trb.pojo.Template;
import com.bluu.sch.trb.serial.SerialMgr;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
@LocalBean
@Singleton(name = TemplateSt.BEAN_NAME)
@SuppressWarnings("serial")
public class TemplateSt implements Serializable {

    public static final String BEAN_NAME = "templates";
    private static final boolean STRICT_MAPPING = false;
    private static final char[] HEXADECIMAL = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private transient Logger logger = Logger.getLogger(TemplateSt.class);

    private SchLogger schLogger;

    private ObjectMapper mapper = new ObjectMapper();

    private SerialMgr serMgr = new SerialMgr(schLogger);
    private Map<String, Template> tptMap;
    private Template masterTpt;

    private Template certifTpt;

    public void clear() {
        tptMap = null;
        masterTpt = null;
    }

    public void registerTemplate(TemplateHDM template) {
        try {

            //administrationDbBean.register(template);
            String oldMstTpt = masterTpt == null ? null : masterTpt.getFile();
            String oldCrtTpt = certifTpt == null ? null : certifTpt.getFile();

            Template newTpt = loadTemplate(template.getFile());

            if (newTpt.isMaster() && (oldMstTpt != null) && !template.getFile().equals(oldMstTpt)) {
                // deleteTemplate(administrationDbBean.findTemplateByName(oldMstTpt));
            } else if (newTpt.isCertification() && (oldCrtTpt != null) && !template.getFile().equals(oldCrtTpt)) {
                // deleteTemplate(administrationDbBean.findTemplateByName(oldCrtTpt));
            }

        } catch (Exception ex) {
            logger.error("Error loading template " + template.getFile() + ", " + ex.getMessage());
            deleteTemplate(template);
        }
    }

    public void deleteTemplate(TemplateHDM template) {
        try {
            if (template != null) {
                if (masterTpt != null && masterTpt.getFile().equalsIgnoreCase(template.getFile())) {
                    masterTpt = null;
                } else if (certifTpt != null && certifTpt.getFile().equalsIgnoreCase(template.getFile())) {
                    certifTpt = null;
                }

                // administrationDbBean.deleteTemplate(template);
                if (tptMap != null) {
                    tptMap.remove(template.getFile());
                }
            }
        } catch (Exception ex) {
            logger.error("Error deleting template " + template.getFile() + ", " + ex.getMessage());
        }
    }

    public Template getMasterTemplate() {
        return masterTpt == null ? null : new Template(masterTpt);
    }

    public Template getCertificationTemplate() {
        return certifTpt == null ? null : new Template(certifTpt);
    }

    public Template getTemplate(String tptName) throws Exception {
        return ((tptMap != null) && (tptName != null) && tptMap.containsKey(tptName)) ? new Template(tptMap.get(tptName))
                : null;
    }

    public ArrayList<Template> getTemplateList(String role) {
        ArrayList<Template> result = new ArrayList<>();
        for (Template tpt : tptMap.values()) {
            if (tpt.getRole().equalsIgnoreCase(role)) {
                result.add(new Template(tpt, tpt.getFile()));
            }
        }
        return result;
    }

    public ArrayList<Template> getTemplateList(String role, ConnectivityProtocolEnm protocol) {
        ArrayList<Template> result = new ArrayList<>();
        for (Template tpt : tptMap.values()) {
            if (tpt.getRole().equalsIgnoreCase(role) && (tpt.getConnectivity() != null)
                    && (tpt.getConnectivity().getProtocol() != null) && (tpt.getConnectivity().getProtocol() == protocol)) {
                result.add(new Template(tpt, tpt.getFile()));
            }
        }
        return result;
    }

    public ArrayList<Template> getTemplateList(ConnectivityProtocolEnm protocol) {
        ArrayList<Template> result = new ArrayList<>();
        for (Template tpt : tptMap.values()) {
            if ((tpt.getConnectivity() != null) && (tpt.getConnectivity().getProtocol() != null) && (tpt.getConnectivity().getProtocol() == protocol)) {
                result.add(new Template(tpt, tpt.getFile()));
            }
        }
        return result;
    }

    private Template loadTemplate(String tptName) throws Exception {
        TemplateHDM tmp = mapper.convertValue(FactoryRest.getInstance().getRestAPI(String.format("%s/findByname/%s", BEAN_NAME, tptName)), TemplateHDM.class);
        Template tpt = checkFormat(tmp.getContent(), tptName);
        if (tpt.isMaster()) {
            masterTpt = tpt;
        } else if (tpt.isCertification()) {
            certifTpt = tpt;
        } else {
            if (tptMap == null) {
                tptMap = new LinkedHashMap<>();
            }

            tptMap.put(tpt.getFile(), tpt);
        }

        logger.info("Template " + tptName + " loaded");
        return tpt;
    }

    public Template checkFormat(String fileContent, String fileName) throws SerException {
        return serMgr.loadTemplate(fileContent, fileName);
    }

    public void loadTemplates() {
        tptMap = new LinkedHashMap<>();
        
        List<TemplateHDM> list = (List<TemplateHDM>) (Object) GetClassUtils.castToList(
                TemplateHDM.class,
                FactoryRest.getInstance().getListRestAPI(String.format("%s/all", BEAN_NAME))
        );
        for (TemplateHDM tptName : list) {
            try {
                loadTemplate(tptName.getFile());
            } catch (Exception ex) {
                logger.error("Error loading template: " + ex.getMessage());
            }
        }
    }
}
