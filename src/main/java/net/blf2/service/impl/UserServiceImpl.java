package net.blf2.service.impl;

import net.blf2.dao.IUserDao;
import net.blf2.entity.UserInfo;
import net.blf2.exception.DeleteException;
import net.blf2.exception.InsertException;
import net.blf2.exception.UpdateException;
import net.blf2.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by blf2 on 17-4-25.
 *用户service
 */
@Service("UserServiceImpl")
@Transactional(rollbackFor = RuntimeException.class)
public class UserServiceImpl implements IUserService{

    @Resource
    private IUserDao userDao;

    @Override
    public void insertUserInfo(UserInfo userInfo) {
        try {
            userDao.insertUserInfo(userInfo);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new InsertException(this);
        }
    }

    @Override
    public void deleteUserInfoByUserNum(String userNum) {
        try {
            userDao.deleteUserInfoByUserNum(userNum);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new DeleteException(this);
        }
    }

    @Override
    public void deleteUserInfoByUserNums(List<String> userNums) {
        try {
            userDao.deleteUserInfoByUserNums(userNums);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new DeleteException(this);
        }
    }

    @Override
    public void updateUserInfo(UserInfo userInfo) {
        try {
            userDao.updateUserInfo(userInfo);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new UpdateException(this);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public UserInfo queryUserInfoByUserNum(String userNum) {
        return userDao.queryUserInfoByUserNum(userNum);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserInfo> queryUserInfoAll() {
        return userDao.queryUserInfoAll();
    }

    @Override
    @Transactional(readOnly = true)
    public UserInfo queryUserInfoByUserPhone(String userPhone) {
        return userDao.queryUserInfoByUserPhone(userPhone);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserInfo> queryUserInfoByUserRoleId(String userRoleId) {
        return userDao.queryUserInfoByUserRoleId(userRoleId);
    }

    @Override
    @Transactional(readOnly = true)
    public String queryUserNumByUserPhone(String userPhone) {
        return userDao.queryUserNumByUserPhone(userPhone);
    }
}
