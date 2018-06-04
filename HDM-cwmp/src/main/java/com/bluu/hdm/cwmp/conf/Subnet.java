/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.conf;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author Gonzalo Torres
 */
public class Subnet {

    private byte baddr[];
    private byte bmask[];

    public Subnet(String s) throws UnknownHostException {
        String a[] = s.split("/");
        if (a.length != 2) {
            throw new IndexOutOfBoundsException("No mask delimiter / found");
        }
        baddr = InetAddress.getByName(a[0]).getAddress();
        try {
            int maskbits = Integer.parseInt(a[1]);
            bmask = new byte[baddr.length];
            for (int i = 0; i < bmask.length; i++, maskbits -= 8) {
                if (maskbits >= 8) {
                    bmask[i] = (byte) 0xFF;
                } else if (maskbits <= 0) {
                    bmask[i] = 0;
                } else {
                    bmask[i] = (byte) (~(0xFF >> maskbits));
                }
            }
        } catch (NumberFormatException e) {
            bmask = InetAddress.getByName(a[1]).getAddress();
        }
        if (bmask.length != baddr.length) {
            throw new UnknownHostException("Bad subnet");
        }
    }

    public boolean isInSubnet(String s) {
        try {
            return isInSubnet(InetAddress.getByName(s));
        } catch (UnknownHostException ex) {
            return false;
        }
    }

    public boolean isInSubnet(InetAddress a) {
        byte ba[] = a.getAddress();
        if (ba.length != baddr.length) {
            return false;
        }
        for (int i = 0; i < ba.length; i++) {
            if ((ba[i] & bmask[i]) != (baddr[i] & bmask[i])) {
                return false;
            }
        }
        return true;
    }
}
