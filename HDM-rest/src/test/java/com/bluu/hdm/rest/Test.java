/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest;

import com.bluu.hdm.rest.util.CryptoUtils;

/**
 *
 * @author Gonzalo Torres
 */
public class Test {

//    private static ObjectMapper mapper = new ObjectMapper();
//    private static String jsonRequest = "{\"name\":\"DIR878A1_FW100ENB05_Beta_XMPP.BIN\",\"model\":\"4\",\"manufacturer\":\"4\"}";
//
    public static void main(String[] args) {
    	String password = "serrot";
	String encrypted = CryptoUtils.encodePassword(password);
//        FirmwareVO f = new FirmwareVO();
//        try {
//            JsonNode node = mapper.readTree(jsonRequest);
//            f = new FirmwareVO(node.get("name").textValue(), node.get("model").textValue(), node.get("manufacturer").textValue());
//        } catch (IOException ex) {
//            System.out.println("Error: " + ex.getMessage());
//        }
        System.out.println("Password --> " + encrypted);
    }
}
