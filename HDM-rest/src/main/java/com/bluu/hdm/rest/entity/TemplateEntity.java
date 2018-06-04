package com.bluu.hdm.rest.entity;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
@Table(name = "templates")
public class TemplateEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "name", length = 128)
    private String name;
    @Lob
    @Size(max = 16777215)
    @Column(name = "content")
    private String content;
    @NotNull
    @Column(name = "ttcreation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ttcreation;
    @NotNull
    @Column(name = "ttmodification")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ttmodification;

    public TemplateEntity() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TemplateEntity)) {
            return false;
        }
        final TemplateEntity other = (TemplateEntity) object;
        return !((id == null && other.id != null) || (id != null && !id.equals(other.id)));
    }

}
