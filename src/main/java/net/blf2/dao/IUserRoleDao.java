package net.blf2.dao;

import net.blf2.entity.RuleInfo;
import net.blf2.entity.UserRoleInfo;
import java.util.List;
import java.util.Map;

/**
 * Created by blf2 on 17-3-27.
 */
public interface IUserRoleDao {
    void insertUserRoleInfo(UserRoleInfo userRoleInfo);
    void updateUserRoleInfo(UserRoleInfo userRoleInfo);
    void deleteUserRoleInfoByUserRoleId(String userRoleId);
    void deleteUserRoleInfoByUserRoleIds(List<String> userRoleIds);
    UserRoleInfo queryUserRoleInfoByUserRoleId(String userRoleId);
    List<UserRoleInfo> queryUserRoleInfoAll();
 //   List<RuleInfo> queryRuleInfosByUserRoleName(String userRoleName);
    List<RuleInfo> queryRuleInfosByUserRoleId(String userRoleId);
    void insertRuleInfosToUserRole(Map<String,Object> paramMap);//map include userRoleId(String),ruleIds(List<String>)
    void deleteRuleInfosFromUserRole(Map<String,Object>paramMap);//map include userRoleId(String),ruleIds(List<String>)
}
