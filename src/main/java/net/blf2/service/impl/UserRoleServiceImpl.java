package net.blf2.service.impl;

import net.blf2.dao.IUserRoleDao;
import net.blf2.entity.RuleInfo;
import net.blf2.entity.UserRoleInfo;
import net.blf2.exception.DeleteException;
import net.blf2.exception.InsertException;
import net.blf2.exception.UpdateException;
import net.blf2.service.IUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by blf2 on 17-4-13.
 * UserRoleService的实现类
 */
@Service("UserRoleServiceImpl")
@Transactional(rollbackFor = RuntimeException.class)
public class UserRoleServiceImpl implements IUserRoleService {
    @Resource
    private IUserRoleDao userRoleDao;

    @Override
    public void inserUserRoleInfo(UserRoleInfo userRoleInfo) {
        try {
            userRoleDao.insertUserRoleInfo(userRoleInfo);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new InsertException(this);
        }
    }

    @Override
    public void deleteUserRoleInfoByUserRoleId(String userRoleId) {
        try {
            userRoleDao.deleteUserRoleInfoByUserRoleId(userRoleId);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new DeleteException(this);
        }
    }

    @Override
    public void deleteUserRoleInfoByUserRoleIds(List<String> userRoleIds) {
        try {
            userRoleDao.deleteUserRoleInfoByUserRoleIds(userRoleIds);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new DeleteException(this);
        }
    }

    @Override
    public void updateUserRoleInfoBy(UserRoleInfo userRoleInfo) {
        try {
            userRoleDao.updateUserRoleInfo(userRoleInfo);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new UpdateException(this);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public UserRoleInfo queryUserRoleInfoByUserRoleId(String userRoleId) {
        return userRoleDao.queryUserRoleInfoByUserRoleId(userRoleId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserRoleInfo> querUserRoleInfoAll() {
        return userRoleDao.queryUserRoleInfoAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RuleInfo> queryRuleInfosByUserRoleId(String userRoleId) {
        return userRoleDao.queryRuleInfosByUserRoleId(userRoleId);
    }

    /**
     * key must include userRoleId and ruleIds
     * value are String and List<String>
     * @param paramMap
     */
    @Override
    public void insertRuleInfosToUserRole(Map<String, Object> paramMap) {
        try {
            userRoleDao.insertRuleInfosToUserRole(paramMap);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new InsertException(this);
        }
    }

    /**
     * key must include userRoleId and ruleIds
     * value are String and List<String>
     * @param paramMap
     */
    @Override
    public void deleteRuleInfosFromUserRole(Map<String, Object> paramMap) {
        try {
            userRoleDao.deleteRuleInfosFromUserRole(paramMap);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new DeleteException(this);
        }
    }
}
