/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.beans;

import com.bluu.hdm.cwmp.message.GetParameterNamesResponse;
import com.bluu.hdm.cwmp.message.GetParameterValuesResponse;
import com.bluu.hdm.cwmp.message.GetRPCMethodsResponse;
import com.bluu.hdm.cwmp.message.Message;
import com.bluu.hdm.cwmp.message.SetParameterValues;
import com.bluu.hdm.cwmp.message.SetParameterValuesResponse;

/**
 *
 * @author Gonzalo Torres
 */
public interface CPELocalBusiness {

    Message FactoryReset(HostsLocal host);

    GetRPCMethodsResponse GetRPCMethods(HostsLocal host);

    void RequestCPEConnection(HostsLocal host);

    GetParameterNamesResponse GetParameterNames(HostsLocal host, String path, boolean next);

    GetParameterValuesResponse GetParameterValues(HostsLocal host, String[] names);

    SetParameterValuesResponse SetParameterValues(HostsLocal host, SetParameterValues values);

    public Message Call(HostsLocal host, Message call, long timeout);
}
