/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.service.interfaces;

import com.bluu.hdm.cwmp.entity.PolicyHistory;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Gonzalo Torres
 */
public interface PolicyHistoryManager extends GenericManager<PolicyHistory, Long> {

    public List<PolicyHistory> getPolicyHistory(String serialNo, Integer status);

    public Map searchPolicyHistory(String policyId, String deviceId, Long start, Long limit);

    public void deletePolicyHistory(String deviceIdList);
  
}
