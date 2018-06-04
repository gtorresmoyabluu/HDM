/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.utils;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Gonzalo Torres
 */
public class Version {

    private static final Logger logger = Logger.getLogger(Version.class.getName());

    /**
     * Creates a new instance of Version
     */
    public Version(String sv) {
        Set(sv);
    }

    void Set(String sv) {
        if (sv == null || sv.equals("")) {
            v = new int[1];
            v[0] = 0;
            return;
        }
        String sva[] = sv.split("\\.");
        v = new int[sva.length];
        boolean fEntireValid = true;
        for (int i = 0; i < sva.length; i++) {
            try {
                v[i] = Integer.parseInt(sva[i]);
            } catch (NumberFormatException ex) {
                fEntireValid = false;
            }
        }
        if (!fEntireValid) {
            Logger.getLogger(getClass().getName()).log(Level.WARN, "Invalid version string: " + sv + " Non numeric elements assumed to be 0");
        }

    }

    public boolean isUptodate(Version ver2) {
        int v2[] = ver2.v;
        int c = (v2.length < v.length) ? v2.length : v.length;
        for (int i = 0; i < c; i++) {
            if (v2[i] < v[i]) {
                return false;
            }
            if (v2[i] > v[i]) {
                return true;
            }
        }
        if (v2.length < v.length) {
            return false;
        }
        return true;
    }
    /*
    static public void validate (FacesContext context, UIComponent toValidate, Object value) {
    if (!Pattern.matches("^[0-9]+(\\.[0-9]+)*", (String)value)) {
    ((UIInput)toValidate).setValid(false);
    context.addMessage(toValidate.getClientId(context), new FacesMessage("Version format must be digits[.digits]"));
    }
    }
     */
    private int[] v;

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < v.length; i++) {
            if (!s.equals("")) {
                s += ".";
            }
            s += v[i];
        }

        return s;
    }
}
