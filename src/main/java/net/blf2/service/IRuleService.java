package net.blf2.service;

import net.blf2.entity.RuleInfo;

import java.util.List;

/**
 * Created by blf2 on 17-4-13.
 * 权限service
 */
public interface IRuleService {
    void insertRuleInfo(RuleInfo ruleInfo);//插入权限信息
    void deleteRuleInfoByRuleId(String ruleId);//删除权限信息根据id
    void deleteRuleInfoByRuleIds(List<String> ruleIds);//删除权限根据多个id
    void updateRuleInfo(RuleInfo ruleInfo);//更新权限
    RuleInfo queryRuleInfoByRuleId(String ruleId);//根据Id查询权限名称
    List<RuleInfo> queryRuleInfoAll();//查询所有权限
}
