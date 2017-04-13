package net.blf2.dao;

import net.blf2.entity.UserInfo;
import net.blf2.entity.UserRoleInfo;

import java.util.List;

/**
 * Created by blf2 on 17-3-21.
 * 用户操作接口
 */
public interface IUserDao {
    void insertUserInfo(UserInfo userInfo);//增加信息
    void deleteUserInfoByUserNum(String userNum);//根据学号删除信息
    void deleteUserInfoByUserNums(List<String> userNums);//根据多个学号删除多条信息
    void updateUserInfo(UserInfo userInfo);//更新信息
    UserInfo queryUserInfoByUserNum(String userNum);//根据学号查询信息
    List<UserInfo> queryUserInfoAll();//查询所有信息
    UserInfo queryUserInfoByUserPhone(String userPhone);//根据手机号查询信息
    List<UserInfo> queryUserInfoByUserRoleId(String userRoleId);//根据权限查询信息
    String queryUserNumByUserPhone(String userPhone);//根据用户手机号查询学号
}
