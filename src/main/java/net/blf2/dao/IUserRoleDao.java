package net.blf2.dao;

import net.blf2.entity.RuleInfo;
import net.blf2.entity.UserRoleInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by blf2 on 17-3-27.
 */
public interface IUserRoleDao {
    boolean insertUserRoleInfo(UserRoleInfo userRoleInfo);
    boolean updateUserRoleInfo(UserRoleInfo userRoleInfo);
    boolean deleteUserRoleInfoByUserRoleId(String userRoleId);
    boolean deleteUserRoleInfoByUserRoleIds(List<String> userRoleIds);
    UserRoleInfo queryUserRoleInfoByUserRoleId(String userRoleId);
    List<UserRoleInfo> queryUserRoleInfoAll();
    List<RuleInfo> queryRuleInfosByUserRoleName(String userRoleName);
    List<RuleInfo> queryRuleInfosByUserRoleId(String userRoleId);
    @SuppressWarnings("map include userRoleId(String),ruleIds(List<String>)")
    boolean insertRuleInfosToUserRole(Map<String,Object> paramMap);//map include userRoleId(String),ruleIds(List<String>)
    @SuppressWarnings("map include userRoleId(String),ruleIds(List<String>)")
    boolean deleteRuleInfosFromUserRole(Map<String,Object>paramMap);//map include userRoleId(String),ruleIds(List<String>)
}
