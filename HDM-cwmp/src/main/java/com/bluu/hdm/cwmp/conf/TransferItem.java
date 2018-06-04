/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.conf;

import com.bluu.hdm.cwmp.utils.cvtHex;
import java.util.Calendar;
import java.util.Random;

/**
 *
 * @author Gonzalo Torres
 */
public class TransferItem {

    private static final long LIFE_TIME = 3600 * 1000; // lifetime in milis
    private long timeCreated;
    private String user;
    private String password;
    private Integer idHost;
    private int type;
    public final static int UPLOAD_LOG = 1;
    public final static int UPLOAD_CONFIG = 2;
    public final static int DOWNLOAD_CONFIG = 3;

    public TransferItem(int id, int type) {
        byte[] rb = new byte[8];
        Random r = new Random();
        r.nextBytes(rb);
        user = Integer.toHexString(id) + cvtHex.cvtHex(rb);
        r.nextBytes(rb);
        password = cvtHex.cvtHex(rb);
        this.type = type;
        idHost = id;
        timeCreated = Calendar.getInstance().getTimeInMillis();
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public Integer getHostId() {
        return idHost;
    }

    public boolean isExpired() {
        return (Calendar.getInstance().getTimeInMillis() - timeCreated > LIFE_TIME);
    }
}
