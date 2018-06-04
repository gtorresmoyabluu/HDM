/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.object;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Gonzalo Torres
 */
@XmlType
public class PerformanceJAXBWrapper {
	private ArrayList<SimpleObject> performanceWrapper = new ArrayList<SimpleObject>();

	public ArrayList<SimpleObject> getPerformanceWrapper() {
		return performanceWrapper;
	}

	public void setPerformanceWrapper(ArrayList<SimpleObject> performanceWrapper) {
		this.performanceWrapper = performanceWrapper;
	}
	
	
}
