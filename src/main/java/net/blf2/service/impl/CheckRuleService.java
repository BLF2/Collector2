package net.blf2.util;

import net.blf2.entity.UserRoleInfo;

/**
 * Created by blf2 on 17-5-16.
 * 检查权限
 */
public class CheckRule {
    private static Map<String,List<>>
    public boolean checkRule(UserRoleInfo userRoleInfo,String ruleName){
        if(userRoleInfo == null || ruleName == null || ruleName.isEmpty())
            return false;

    }
}
