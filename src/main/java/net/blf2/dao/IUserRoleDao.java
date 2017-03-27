package net.blf2.dao;

import net.blf2.entity.RuleInfo;
import net.blf2.entity.UserRoleInfo;

import java.util.List;

/**
 * Created by blf2 on 17-3-27.
 */
public interface IUserRoleDao {
    boolean insertUserRoleInfo(UserRoleInfo userRoleInfo);
    boolean updateUserRoleInfo(UserRoleInfo userRoleInfo);
    boolean deleteUserRoleInfoByUserRoleId(String userRoleId);
    boolean deleteUserRoleInfoByUserRoleIds(String[] userRoleIds);
    UserRoleInfo queryUserRoleInfoByUserRoleId(String userRoleId);
    List<UserRoleInfo> queryUserRoleInfoAll();
    List<RuleInfo> queryRuleInfosByUserRoleName(String userRoleName);
    List<RuleInfo> queryRuleInfosByUserRoleId(String userRoleId);
    boolean insertRuleInfosToUserRole(String userRoleId,String[] ruleId);
    boolean deleteRuleInfosFromUserRole(String userRoleId,String[] ruleId);
}
