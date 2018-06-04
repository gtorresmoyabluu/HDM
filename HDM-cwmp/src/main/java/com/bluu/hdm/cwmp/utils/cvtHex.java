/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.utils;

/**
 *
 * @author Gonzalo Torres
 */
public class cvtHex {

    private static char[] HEX = "0123456789abcdef".toCharArray();

    static public String cvtHex(byte[] data) {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            b.append(HEX[(data[i] >> 4) & 0xf]);
            b.append(HEX[data[i] & 0xf]);
        }
        return b.toString();
    }
}
