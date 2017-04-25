package net.blf2.service;

import net.blf2.entity.RuleInfo;

import java.util.List;

/**
 * Created by blf2 on 17-4-13.
 * 权限service
 */
public interface IRuleService {
    void insertRuleInfo(RuleInfo ruleInfo);
    void deleteRuleInfoByRuleId(String ruleId);
    void deleteRuleInfoByRuleIds(List<String> ruleIds);
    void updateRuleInfo(RuleInfo ruleInfo);
    RuleInfo queryRuleInfoByRuleId(String ruleId);
    List<RuleInfo> queryRuleInfoAll();
}
