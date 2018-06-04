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
public class Node extends TreeNode{

	private ArrayList<TreeNode> nextNode = new ArrayList<TreeNode>();
	
	TreeNode parentNode = new TreeNode();
	
	public Node(String nameID) {
		super(nameID);
		this.type = TreeNode.NODE;
		// TODO Auto-generated constructor stub
	}

	public ArrayList<TreeNode> getNextNode() {
		return nextNode;
	}

	public void setNextNode(ArrayList<TreeNode> next) {
		this.nextNode = next;
	}

	public TreeNode getParentNode() {
		return parentNode;
	}

	public void setParentNode(TreeNode parent) {
		this.parentNode = parent;
	}

	public TreeNode getChildNode(String idNode){
		for(int i=0;i<nextNode.size();i++){
			if(nextNode.get(i).getNameID().equals(idNode))return nextNode.get(i);			
		}		
		return null;
	}
}
