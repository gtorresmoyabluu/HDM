/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.stereotype.Indexed;

/**
 *
 * @author Gonzalo Torres
 */
@Entity
@Table(name = "area")
@Indexed
public class Area extends BaseObject implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private Integer areaType;
    private String description;
    private Set<Area> areas;
    private Area area;
    private Set<Device> devices;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    @Column(name = "name", length = 45)
    public String getName() {
        return name;
    }

    @Column(name = "area_type")
    public Integer getAreaType() {
        return areaType;
    }

    @Column(name = "description", length = 45)
    public String getDescription() {
        return description;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "area")
    public Set<Area> getAreas() {
        return areas;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", nullable = false)
    public Area getArea() {
        return area;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "area")
    public Set<Device> getDevices() {
        return devices;
    }

    //setter
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAreaType(Integer areaType) {
        this.areaType = areaType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAreas(Set<Area> areas) {
        this.areas = areas;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public void setDevices(Set<Device> devices) {
        this.devices = devices;
    }

    @Override
    public String toString() {
        return "id: " + id + ", name: " + name;
    }

    @Override
    public boolean equals(Object o) {
        return false; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        return 1; //To change body of generated methods, choose Tools | Templates.
    }

}