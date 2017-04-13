package net.blf2.service;

import net.blf2.entity.RuleInfo;
import net.blf2.entity.UserRoleInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by blf2 on 17-4-13.
 * 用户角色Service
 */
public interface IUserRoleService {
    void inserUserRoleInfo(UserRoleInfo userRoleInfo);
    void deleteUserRoleInfoByUserRoleId(String userRoleId);
    void deleteUserRoleInfoByUserRoleIds(List<String> userRoleIds);
    void updateUserRoleInfoBy(UserRoleInfo userRoleInfo);
    UserRoleInfo queryUserRoleInfoByUserRoleId(String userRoleId);
    List<UserRoleInfo> querUserRoleInfoAll();
    List<RuleInfo> queryRuleInfosByUserRoleId(String userRoleId);
    void insertRuleInfosToUserRole(Map<String,Object> paramMap);//map include userRoleId(String),ruleIds(List<String>)
    void deleteRuleInfosFromUserRole(Map<String,Object>paramMap);//map include userRoleId(String),ruleIds(List<String>)
}
