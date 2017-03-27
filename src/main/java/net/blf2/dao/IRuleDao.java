package net.blf2.dao;

import net.blf2.entity.RuleInfo;

import java.util.List;

/**
 * Created by blf2 on 17-3-27.
 */
public interface IRuleDao {
    boolean insertRuleInfo(RuleInfo ruleInfo);
    boolean updateRuleInfo(RuleInfo ruleInfo);
    boolean deleteRuleInfoByRuleId(String ruleId);
    boolean deleteRuleInfoByRuleIds(String[] ruleIds);
    List<RuleInfo> queryRuleInfoAll();
    RuleInfo queryRuleInfoByRuleId(String ruleId);
}
