/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.tree;

import java.util.ArrayList;

/**
 *
 * @author Gonzalo Torres
 */
public class Leaf extends TreeNode{

	private ArrayList<TreeNode> pre = new ArrayList<TreeNode>();
	
	private String value;
	
	public Leaf(String nameID) {
		super(nameID);
		this.type = TreeNode.LEAF;
		// TODO Auto-generated constructor stub
	}

	public ArrayList<TreeNode> getPre() {
		return pre;
	}

	public void setPre(ArrayList<TreeNode> pre) {
		this.pre = pre;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
