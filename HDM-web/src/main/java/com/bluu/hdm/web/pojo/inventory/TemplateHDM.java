package com.bluu.hdm.web.pojo.inventory;

import com.bluu.hdm.web.util.CustomDateDeserializer;
import com.bluu.hdm.web.util.CustomDateSerializer;
import com.bluu.sch.trb.pojo.Action;
import com.bluu.sch.trb.pojo.Category;
import com.bluu.sch.trb.pojo.Check;
import com.bluu.sch.trb.pojo.Connectivity;
import com.bluu.sch.trb.pojo.MstSurvey;
import com.bluu.sch.trb.pojo.Parameter;
import com.bluu.sch.trb.pojo.Proof;
import com.bluu.sch.trb.pojo.Test;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.core.Commit;

public class TemplateHDM {

    private Long id;
    private String content;
    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date ttcreation;
    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date ttmodification;

    @Attribute(name = "role")
    private String role;
    @Attribute(name = "os", required = false)
    private String os = "unknown";
    private String operativeSystem;
    @Attribute(name = "manufacturer", required = false)
    private String manufacturer = "unknown";
    @Attribute(name = "model", required = false)
    private String model = "unknown";
    @Element(name = "md5")
    private String md5Written;
    @Element(name = "connectivity", required = false)
    private Connectivity connectivity;
    @Element(name = "survey", required = false)
    private MstSurvey survey;
    @ElementList(name = "actions", required = false)
    private ArrayList<Action> actLst = new ArrayList<>();
    @ElementList(name = "categories", required = false)
    private ArrayList<Category> catLst = new ArrayList<>();
    @ElementList(name = "checks", required = false)
    private ArrayList<Check> chkLst = new ArrayList<>();
    @ElementList(name = "parameters", required = false)
    private ArrayList<Parameter> parLst = new ArrayList<>();
    @ElementList(name = "proofs", required = false)
    private ArrayList<Proof> prfLst = new ArrayList<>();
    @ElementList(name = "tests", required = false)
    private ArrayList<Test> tstLst = new ArrayList<>();
    @ElementMap(name = "configuration", entry = "value", key = "key", attribute = true, required = false)
    private HashMap<String, String> cfgMap = new HashMap<>();
    @ElementMap(name = "autoresponses", entry = "autoresponse", key = "output", attribute = true, required = false)
    private HashMap<String, String> autoRespMap = new HashMap<>();
    private String file = "unknown", md5Read = "unknown";
    private HashMap<String, Action> actMap = new HashMap<>();
    private HashMap<String, Check> chkMap = new HashMap<>();
    private HashMap<String, Parameter> parMap = new HashMap<>();
    private HashMap<String, Proof> prfMap = new HashMap<>();
    private HashMap<String, Test> tstMap = new HashMap<>();
    
    private HashMap<String, String> automaticResponseMap;
    private HashMap<String, String> configurationMap;
    private Boolean certification;
    private ArrayList<String> testStList;
    private Boolean md5Ok;
    private Boolean master;
    private ArrayList<String> actionStList;
    private ArrayList<String> categoryList;
            
    public TemplateHDM() {
    }

    public TemplateHDM(TemplateHDM entity) {

        id = entity.getId();
        file = entity.getFile();
        content = entity.getContent();
        ttcreation = entity.getTtcreation();
        ttmodification = entity.getTtmodification();

        this.role = entity.getRole();
        this.os = entity.getOs();
        operativeSystem = entity.getOs();
        this.manufacturer = entity.getManufacturer();
        this.model = entity.getModel();
        this.md5Read = entity.getMd5Read();
        this.md5Written = entity.getMd5Written();
        this.file = entity.getFile();
        this.connectivity = entity.getConnectivity();
        this.cfgMap = new HashMap<>();
        for (String key : entity.cfgMap.keySet()) {
            cfgMap.put(key, entity.cfgMap.get(key));
        }
        this.autoRespMap = new HashMap<>();
        for (String key : entity.autoRespMap.keySet()) {
            autoRespMap.put(key, entity.autoRespMap.get(key));
        }
        this.survey = entity.survey;
        for (Action act : entity.actLst) {
            Action newAct = new Action(act);
            if (entity.isMaster()) {
                newAct.setDetails(cfgMap);
            }
            actLst.add(newAct);
            actMap.put(newAct.getId(), newAct);
        }
        for (Category cat : entity.catLst) {
            catLst.add(new Category(cat, false));
        }
        for (Check chk : entity.chkLst) {
            Check newChk = new Check(chk);
            chkLst.add(newChk);
            chkMap.put(newChk.getId(), newChk);
        }
        for (Parameter par : entity.parLst) {
            Parameter newPar = new Parameter(par);
            parLst.add(newPar);
            parMap.put(newPar.getId(), newPar);
        }
        for (Proof prf : entity.prfLst) {
            Proof newPrf = new Proof(prf);
            prfLst.add(newPrf);
            prfMap.put(newPrf.getId(), newPrf);
        }
        for (Test tst : entity.tstLst) {
            Test newTst = new Test(tst);
            tstLst.add(newTst);
            tstMap.put(newTst.getId(), newTst);
        }
    }

    public TemplateHDM(Object[] model) {
        super();
        id = ((Number) model[0]).longValue();
        file = ((String) model[1]);
        ttcreation = (Date) model[2];
        ttmodification = (Date) model[3];
    }

    public TemplateHDM(String fileName, String fileContent) {
        file = fileName;
        content = fileContent;
    }

    public Long getId() {
        return id;
    }

    public Date getTtcreation() {
        return ttcreation;
    }

    public Date getTtmodification() {
        return ttmodification;
    }

    public String getContent() {
        return content;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTtcreation(Date ttcreation) {
        this.ttcreation = ttcreation;
    }

    public void setTtmodification(Date ttmodification) {
        this.ttmodification = ttmodification;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Commit
    public void build() {
        if (!cfgMap.isEmpty()) {
            for (String key : cfgMap.keySet()) {
                String val = cfgMap.get(key);
                if (val.startsWith("'") && val.endsWith("'")) {
                    cfgMap.put(key, val.substring(1, val.length() - 1));
                }
            }
        }
        if (!autoRespMap.isEmpty()) {
            for (String key : autoRespMap.keySet()) {
                String val = autoRespMap.get(key);
                if (val.startsWith("'") && val.endsWith("'")) {
                    autoRespMap.put(key, val.substring(1, val.length() - 1));
                }
            }
        }
    }

    public void addConfiguration(String key, String value) {
        cfgMap.put(key, value);
    }

    public boolean containsAction(String id) {
        return actMap.containsKey(id);
    }

    public boolean containsParameter(String id) {
        return parMap.containsKey(id);
    }

    public boolean containsTest(String id) {
        return tstMap.containsKey(id);
    }

    public Action getAction(String actId) {
        return actMap.get(actId);
    }

    public ArrayList<String> getActionStList() {
        ArrayList<String> actStLst = new ArrayList<>();
        for (Action act : actLst) {
            actStLst.add(act.getId());
        }
        Collections.sort(actStLst);
        return actStLst;
    }

    public HashMap<String, String> getAutomaticResponseMap() {
        return autoRespMap;
    }

    public ArrayList<Category> getCatLst() {
        return catLst;
    }

    public void setCatLst(ArrayList<Category> catLst) {
        this.catLst = catLst;
    }

    public Check getCheck(String chkId) {
        return chkMap.get(chkId);
    }

    public String getConfiguration(String key) {
        return cfgMap.get(key);
    }

    public HashMap<String, String> getConfigurationMap() {
        return cfgMap;
    }

    public Connectivity getConnectivity() {
        return connectivity;
    }

    public String getFile() {
        return file;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public String getOperativeSystem() {
        return operativeSystem;
    }

    public void setOperativeSystem(String operativeSystem) {
        this.operativeSystem = operativeSystem;
    }

    public Parameter getParameter(String parId) {
        return parMap.get(parId);
    }

    public Proof getProof(String prfId) {
        return prfMap.get(prfId);
    }

    public String getRole() {
        return role;
    }

    public MstSurvey getSurvey() {
        return survey;
    }

    public Test getTest(String tstId) {
        return tstMap.get(tstId);
    }

    public ArrayList<String> getTestStList() {
        ArrayList<String> tstStLst = new ArrayList<>();
        for (Test tst : tstLst) {
            tstStLst.add(tst.getId());
        }
        Collections.sort(tstStLst);
        return tstStLst;
    }

    public boolean isCertification() {
        return (role != null) && role.equalsIgnoreCase("certification");
    }

    public boolean isMaster() {
        return (role != null) && role.equalsIgnoreCase("master");
    }

    public boolean isMd5Ok() {
        return (md5Read != null) && (md5Written != null) && md5Read.equalsIgnoreCase(md5Written);
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getMd5Written() {
        return md5Written;
    }

    public void setMd5Written(String md5Written) {
        this.md5Written = md5Written;
    }

    public ArrayList<Action> getActLst() {
        return actLst;
    }

    public void setActLst(ArrayList<Action> actLst) {
        this.actLst = actLst;
    }

    public ArrayList<Check> getChkLst() {
        return chkLst;
    }

    public void setChkLst(ArrayList<Check> chkLst) {
        this.chkLst = chkLst;
    }

    public ArrayList<Parameter> getParLst() {
        return parLst;
    }

    public void setParLst(ArrayList<Parameter> parLst) {
        this.parLst = parLst;
    }

    public ArrayList<Proof> getPrfLst() {
        return prfLst;
    }

    public void setPrfLst(ArrayList<Proof> prfLst) {
        this.prfLst = prfLst;
    }

    public ArrayList<Test> getTstLst() {
        return tstLst;
    }

    public void setTstLst(ArrayList<Test> tstLst) {
        this.tstLst = tstLst;
    }

    public HashMap<String, String> getCfgMap() {
        return cfgMap;
    }

    public void setCfgMap(HashMap<String, String> cfgMap) {
        this.cfgMap = cfgMap;
    }

    public HashMap<String, String> getAutoRespMap() {
        return autoRespMap;
    }

    public void setAutoRespMap(HashMap<String, String> autoRespMap) {
        this.autoRespMap = autoRespMap;
    }

    public HashMap<String, Action> getActMap() {
        return actMap;
    }

    public void setActMap(HashMap<String, Action> actMap) {
        this.actMap = actMap;
    }

    public HashMap<String, Check> getChkMap() {
        return chkMap;
    }

    public void setChkMap(HashMap<String, Check> chkMap) {
        this.chkMap = chkMap;
    }

    public HashMap<String, Parameter> getParMap() {
        return parMap;
    }

    public void setParMap(HashMap<String, Parameter> parMap) {
        this.parMap = parMap;
    }

    public HashMap<String, Proof> getPrfMap() {
        return prfMap;
    }

    public void setPrfMap(HashMap<String, Proof> prfMap) {
        this.prfMap = prfMap;
    }

    public HashMap<String, Test> getTstMap() {
        return tstMap;
    }

    public void setTstMap(HashMap<String, Test> tstMap) {
        this.tstMap = tstMap;
    }

    public String getMd5Read() {
        return md5Read;
    }

    public void setMd5Read(String md5Read) {
        this.md5Read = md5Read;
    }

    @Override
    public String toString() {
        String result = "Tpt File: " + file + ", Role: " + role + ", MD5 Read: " + md5Read + ", Written: " + md5Written;
        if (isMaster()) {
            result += "\nTpt Categories:";
            /*for (Category cat : catLst) {
                result += "\n" + cat;
            }*/
            result += "\nTpt Proofs:";
            for (Proof prf : prfLst) {
                result += "\n" + prf;
            }
            result += "\nTpt Actions:";
            for (Action act : actLst) {
                result += "\n" + act;
            }
            result += "\nTpt Survey:";
            if (survey != null) {
                result += survey.toString();
            }
        } else {
            result += "\nTpt OS: " + os + ", Manufacturer: " + manufacturer + ", Model: " + model;
            if (connectivity != null) {
                result += "\nTpt Connectivity:";
                result += "\n" + connectivity;
            }
            result += "\nTpt Parameters:";
            for (Parameter par : parLst) {
                result += "\n" + par;
            }
            if (!cfgMap.isEmpty()) {
                result += "\nTpt Configuration:";
                for (String key : cfgMap.keySet()) {
                    result += "\nKey: " + key + ", Value: " + cfgMap.get(key);
                }
            }
            if (!autoRespMap.isEmpty()) {
                result += "\nTpt Automatic Responses:";
                for (String key : autoRespMap.keySet()) {
                    result += "\nOutput: " + key + ", Response: " + autoRespMap.get(key);
                }
            }
            result += "\nTpt Actions:";
            for (Action act : actLst) {
                result += "\n" + act;
            }
            result += "\nTpt Tests:";
            for (Test tst : tstLst) {
                result += "\n" + tst;
            }
            result += "\nTpt Checks:";
            for (Check chk : chkLst) {
                result += "\n" + chk;
            }
        }
        return result;
    }

    public Boolean getCertification() {
        return certification;
    }

    public void setCertification(Boolean certification) {
        this.certification = certification;
    }

    public Boolean getMd5Ok() {
        return md5Ok;
    }

    public void setMd5Ok(Boolean md5Ok) {
        this.md5Ok = md5Ok;
    }

    public Boolean getMaster() {
        return master;
    }

    public void setMaster(Boolean master) {
        this.master = master;
    }

    public ArrayList<String> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(ArrayList<String> categoryList) {
        this.categoryList = categoryList;
    }
    
    
    
}
