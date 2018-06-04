/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.conf;

import java.util.HashMap;

/**
 *
 * @author Gonzalo Torres
 */
public class TransferItemMgr {

    private static final int CLEANUP_THREASHOLD = 1000;
    private static HashMap<String, TransferItem> items = new HashMap<String, TransferItem>();

    public static TransferItem getNew(int id, int type) {
        TransferItem it = new TransferItem(id, type);
        items.put(it.getUser(), it);
        return it;
    }

    public static TransferItem find(String user) {
        TransferItem it = items.get(user);
        if (it != null) {
            items.remove(user);
        }
        if (items.size() > CLEANUP_THREASHOLD) {
            for (TransferItem i : items.values()) {
                if (i.isExpired()) {
                    items.remove(i.getUser());
                }
            }
        }
        return it;
    }
}
