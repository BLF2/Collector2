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
    void inserUserRoleInfo(UserRoleInfo userRoleInfo);//插入用户角色
    void deleteUserRoleInfoByUserRoleId(String userRoleId);//删除角色根据Id
    void deleteUserRoleInfoByUserRoleIds(List<String> userRoleIds);//根据多个id删除对应的角色
    void updateUserRoleInfo(UserRoleInfo userRoleInfo);//更新角色
    UserRoleInfo queryUserRoleInfoByUserRoleId(String userRoleId);//根据角色Id查询角色
    List<UserRoleInfo> querUserRoleInfoAll();//查询所有角色
    List<RuleInfo> queryRuleInfosByUserRoleId(String userRoleId);//根据角色查询它所具有的权限
    void insertRuleInfosToUserRole(String userRoleId,List<String>ruleIds);//给某个角色加权限
    void deleteRuleInfosFromUserRole(String userRoleId,List<String>ruleIds);//给某个角色删除权限
}
