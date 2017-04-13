package net.blf2.dao;

import net.blf2.entity.RuleInfo;

import java.util.List;

/**
 * Created by blf2 on 17-3-27.
 */
public interface IRuleDao {
    void insertRuleInfo(RuleInfo ruleInfo);
    void updateRuleInfo(RuleInfo ruleInfo);
    void deleteRuleInfoByRuleId(String ruleId);
    void deleteRuleInfoByRuleIds(List<String> ruleIds);
    List<RuleInfo> queryRuleInfoAll();
    RuleInfo queryRuleInfoByRuleId(String ruleId);
}
