/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.vendors;

import java.util.ArrayList;

/**
 *
 * @author Gonzalo Torres
 */
public class Thomson extends Vendor {

    private static final int CHECK_WRONG_VALUE = 1;
    private static final int CHECK_MISSING_VAR = 2;

    private void CheckLine(String cfg, String cmd, String var, String value, ArrayList<String> msgs) {
        String s = cmd + "set var=" + var + " value=" + value;
        if (!cfg.contains(s)) {
            if (cfg.contains(s + "\r\n") || cfg.contains(s + "\n")) {
                msgs.add("Wrong value for  \"" + var + "\". Expected '" + s + "'\n");
            } else {
                msgs.add("Missing line \"" + s + "\".\n");
            }
        }
    }

    private int CheckLine(StringBuilder cfg, String cmd, String var, String value) {
        String s = cmd + "set var=" + var + " value=" + value;
        if (cfg.indexOf(s + "\n") == -1 && cfg.indexOf(s + "\r\n") == -1) {
            return (cfg.indexOf(cmd + "set var=" + var + " value=") != -1) ? CHECK_WRONG_VALUE : CHECK_MISSING_VAR;
        }
        return 0;
    }

    private void commonCheckConfig(String name, String version, String cfg, String cmd, ArrayList<String> msgs) {
        CheckLine(cfg, cmd, "CONF_SERVICE", "\"" + name + "\"", msgs);
        CheckLine(cfg, cmd, "CONF_VERSION", version, msgs);
    }

    private String[] commonReturn(ArrayList<String> msgs, boolean sts) {
        if (!msgs.isEmpty()) {
            if (!sts) {
                msgs.add(0, "Problems with configs file [ env.ini ] section");
            }
            msgs.add(msgs.size(), "Unless fixed config update will loop.");
            return msgs.toArray(new String[0]);
        }
        return null;
    }

    private String[] CheckConfigIni(String name, String version, String cfg, ArrayList<String> msgs) {
        commonCheckConfig(name, version, cfg, "", msgs);
        return commonReturn(msgs, false);
    }

    private String[] CheckConfigSts(String filename, String name, String version, String cfg, ArrayList<String> msgs) {
        String h = "TPVERSION=2.0.0 BOARD_NAME=" + hwversion;
        if (!cfg.contains(h)) {
            msgs.add("sts file should start with header \"" + h + "\"");
        }
        if (filename.length() < 5) {
            msgs.add("File name should be at least one char + .sts (" + filename + ")");
        }
        if (filename.length() > 12) {
            msgs.add("File name should be at most 8 char + '.sts'(" + filename + ")");
        }
        commonCheckConfig(name, version, cfg, ":env ", msgs);
        return commonReturn(msgs, true);
    }

    @Override
    public String[] CheckConfig(String filename, String name, String version, String cfg) {
        String fn = filename;
        fn.toLowerCase();
        ArrayList<String> msgs = new ArrayList<String>();
        if (fn.equals("user.ini")) {
            return CheckConfigIni(name, version, cfg, msgs);
        } else if (fn.endsWith(".sts")) {
            return CheckConfigSts(filename, name, version, cfg, msgs);
        } else {
            String[] r = new String[1];
            r[0] = "Invalid config file name: must be either user.ini or xxxxxxxx.sts (x any charachter)";
            return r;
        }
    }

    private boolean UpdateConfigLineIni(StringBuilder cfg, String var, String value) {
        switch (CheckLine(cfg, "", var, value)) {
            case CHECK_WRONG_VALUE:
                UpdateConfigSetValue(cfg, var, value);
                return true;
            case CHECK_MISSING_VAR:
                int i = cfg.indexOf("[ env.ini ]");
                if (i != -1) {
                    i += "[ env.ini ]".length();
                    if (cfg.charAt(i) == '\r') {
                        i++;
                    }
                    if (cfg.charAt(i) == '\n') {
                        i++;
                    }
                    cfg.insert(i, "set var=" + var + " value=" + value + "\r\n");
                }
                return true;
        }
        return false;
    }

    private void UpdateConfigSetValue(StringBuilder cfg, String var, String value) {
        String p = "var=" + var + " value=";
        int s = cfg.indexOf(p) + p.length();
        int e = cfg.indexOf("\r", s);
        if (e == -1) {
            e = cfg.indexOf("\n", s);
        }
        if (e == -1) {
            e = cfg.length();
        }
        cfg.replace(s, e, value);
    }

    private boolean UpdateConfigLineSts(StringBuilder cfg, String var, String value) {
        switch (CheckLine(cfg, ":env ", var, value)) {
            case CHECK_WRONG_VALUE:
                UpdateConfigSetValue(cfg, var, value);
                return true;
            case CHECK_MISSING_VAR:
                cfg.append("\n:env set " + var + "=" + value + "\n");
                return true;
        }
        return false;
    }

    private String UpdateConfigIni(String name, String version, String cfg) {
        StringBuilder r = new StringBuilder(cfg);
        boolean rb = UpdateConfigLineIni(r, "CONF_SERVICE", "\"" + name + "\"");
        if (UpdateConfigLineIni(r, "CONF_VERSION", version) || rb) {
            return r.toString();
        }
        return null;
    }

    private String UpdateConfigSts(String name, String version, String cfg) {
        StringBuilder r = new StringBuilder(cfg);
        boolean rb = UpdateConfigLineSts(r, "CONF_SERVICE", name);
        if (UpdateConfigLineSts(r, "CONF_VERSION", version) || rb) {
            return r.toString();
        }
        return null;
    }

    @Override
    public String UpdateConfig(String filename, String name, String version, String cfg) {
        if (filename.equals("user.ini")) {
            return UpdateConfigIni(name, version, cfg);
        } else if (filename.endsWith(".sts")) {
            return UpdateConfigSts(name, version, cfg);
        }
        return null;
    }

    @Override
    public int getConfigUpdateMethod() {
        return CFG_UPDATE_METHOD_2;
    }
}
