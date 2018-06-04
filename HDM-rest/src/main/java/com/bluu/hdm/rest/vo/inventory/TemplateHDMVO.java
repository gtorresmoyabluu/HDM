package com.bluu.hdm.rest.vo.inventory;

import com.bluu.hdm.rest.entity.TemplateEntity;
import com.bluu.sch.trb.pojo.Template;
import java.io.Serializable;
import java.util.Date;

public class TemplateHDMVO extends Template implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String content;
    private Date ttcreation;
    private Date ttmodification;

    public TemplateHDMVO() {
    }

    public TemplateHDMVO(TemplateEntity entity) {
        id = entity.getId();
        super.setFile(entity.getName());
        content = entity.getContent();
        ttcreation = entity.getTtcreation();
        ttmodification = entity.getTtmodification();
    }

    public TemplateHDMVO(Object[] model) {
        id = ((Number) model[0]).longValue();
        super.setFile((String) model[1]);
        ttcreation = (Date) model[2];
        ttmodification = (Date) model[3];
    }

    public TemplateHDMVO(TemplateHDMVO template, Template _template) {

        super(_template);

        id = template.getId();
        ttcreation = template.getTtcreation();
        ttmodification = template.getTtmodification();
    }

    public TemplateHDMVO(String fileName, String fileContent) {
        super.setFile(fileName);
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

}
