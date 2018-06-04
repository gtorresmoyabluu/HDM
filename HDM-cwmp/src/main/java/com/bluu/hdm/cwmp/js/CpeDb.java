/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.js;

/**
 *
 * @author Gonzalo Torres
 */
import com.bluu.hdm.cwmp.beans.HostsLocal;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

public class CpeDb extends ScriptableObject {

    private HostsLocal host;

    public CpeDb() {
    }

    public CpeDb(Context cx, Function ctor, HostsLocal host) throws IOException {
        this.host = host;
        Properties p = new Properties();
        byte[] bp = host.getProps();
        if (bp != null) {
            p.load(new ByteArrayInputStream(bp));
            if (p != null) {
                for (Entry e : p.entrySet()) {
                    String k = (String) e.getKey();
                    k.replace('.', '_');
                    //System.out.println ("PROPSAS: "+e.getKey()+"="+e.getValue());
                    put(k, this, e.getValue());
                }
            }
        }
    }

    @Override
    public String getClassName() {
        return "CpeDb";
    }

    public static Scriptable jsConstructor(Context cx, Object[] args, Function ctorObj, boolean inNewExpr) {
        try {
            return new CpeDb(cx, ctorObj, (HostsLocal) args[0]);
        } catch (IOException ex) {
            Logger.getLogger(CpeDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void jsFunction_Save(Context cx, Scriptable thisObj, Object[] args, Function funObj) {
        CpeDb _this = (CpeDb) thisObj;
        Object[] ids = _this.getIds();
        /*
        Properties p = new Properties ();
        for (Object oid : ids) {
        String id = (String)oid;
        p.put(id, (String)CpeDb.getProperty(thisObj, id));
        }
        _this.host.setProps(p);
         */
        String props = "";
        for (Object oid : ids) {
            String id = (String) oid;
            props += id + "=" + (String) CpeDb.getProperty(thisObj, id) + "\n";
        }
        _this.host.setProps(props.getBytes());
    }

    public void setScriptResult(String result) {
    }
}
