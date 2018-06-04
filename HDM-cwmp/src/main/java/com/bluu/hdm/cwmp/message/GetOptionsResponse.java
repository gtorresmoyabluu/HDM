/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.message;

import java.util.ArrayList;
import java.util.Iterator;
import javax.xml.soap.*;

/**
 *
 * @author Gonzalo Torres
 */
public class GetOptionsResponse extends Message {

    public class OptionStruct {

        public String OptionName;
        public String VoucherSN;
        public int State;
        public int Mode;
        public String StartDate;
        public String ExpirationDate;
        public boolean IsTransferable;
    }

    public GetOptionsResponse() {
        name = "GetOptionsResponse";
    }

    @Override
    protected void createBody(SOAPBodyElement body, SOAPFactory spf) throws SOAPException {
    }

    @Override
    protected void parseBody(SOAPBodyElement body, SOAPFactory spf) throws SOAPException {
        Iterator pi = getRequestChildElement(spf, body, "OptionList").getChildElements(spf.createName("OptionStruct"));
        Name nameOptionName = spf.createName("OptionName");
        Name nameVoucherSN = spf.createName("VoucherSN");
        Name nameState = spf.createName("State");
        Name nameMode = spf.createName("Mode");
        Name nameStartDate = spf.createName("StartDate");
        Name nameExpirationDate = spf.createName("ExpirationDate");
        Name nameIsTransferable = spf.createName("IsTransferable");

        OptionList = new ArrayList<>();

        while (pi.hasNext()) {
            SOAPElement option = (SOAPElement) pi.next();
            OptionStruct o = new OptionStruct();
            o.OptionName = getRequestElement(option, nameOptionName);
            o.VoucherSN = getRequestElement(option, nameVoucherSN);
            o.State = Integer.parseInt(getRequestElement(option, nameState));
            o.Mode = Integer.parseInt(getRequestElement(option, nameMode));
            o.StartDate = getRequestElement(option, nameStartDate);
            o.ExpirationDate = getRequestElement(option, nameExpirationDate);
            o.IsTransferable = Boolean.parseBoolean(getRequestElement(option, nameIsTransferable));
            OptionList.add(o);
        }
    }
    public ArrayList<OptionStruct> OptionList;
}
