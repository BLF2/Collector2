package net.blf2.service.impl;

import net.blf2.dao.IUserRoleDao;
import net.blf2.entity.RuleInfo;
import net.blf2.entity.UserRoleInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by blf2 on 17-5-16.
 * 检查权限
 */
@Service("CheckRuleService")
public class CheckRuleService {
    private static Map<String,List<String>> roleRuleMap = new HashMap<>();
    @Resource
    private IUserRoleDao userRoleDao;

    public boolean checkRule(UserRoleInfo userRoleInfo,String ruleName){
        if(userRoleInfo == null || ruleName == null || ruleName.isEmpty())
            return false;
        if(roleRuleMap.containsKey(userRoleInfo.getUserRoleId())){
            List<String> ruleList = roleRuleMap.get(userRoleInfo.getUserRoleId());
            if(ruleList.contains(ruleName))
                return true;
        }
        boolean isExistsInRuleInfos = false;
        List<String> ruleNamesFromRole = new LinkedList<>();
        List<RuleInfo> ruleInfoFromRole= userRoleDao.queryRuleInfosByUserRoleId(userRoleInfo.getUserRoleId());
        for(RuleInfo ruleInfo : ruleInfoFromRole){
            String ruleNameFromDb = ruleInfo.getRuleName();
            if(ruleNameFromDb.equals(ruleName)){
                isExistsInRuleInfos = true;
            }
            ruleNamesFromRole.add(ruleNameFromDb);
        }
        roleRuleMap.put(userRoleInfo.getUserRoleId(),ruleNamesFromRole);
        return isExistsInRuleInfos;
    }
}
