package net.blf2.dao;

import net.blf2.dao.IRuleDao;
import net.blf2.dao.IUserRoleDao;
import net.blf2.entity.RuleInfo;
import net.blf2.entity.UserRoleInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by blf2 on 17-3-29.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan(basePackages = "net.blf2.dao")
public class IUserRoleDaoTest {
    @Resource
    private IUserRoleDao userRoleDao;

    @Resource
    private IRuleDao ruleDao;

    @Test
    public void TestIUserRoleDao(){
        RuleInfo ruleInfo1 = new RuleInfo();
        ruleInfo1.setRuleName("testRole1");
        ruleInfo1.setRuleId(UUID.randomUUID().toString());

        RuleInfo ruleInfo2 = new RuleInfo();
        ruleInfo2.setRuleId(UUID.randomUUID().toString());
        ruleInfo2.setRuleName("testRole2");

        RuleInfo ruleInfo3 = new RuleInfo();
        ruleInfo3.setRuleName("testRole3");
        ruleInfo3.setRuleId(UUID.randomUUID().toString());

        ruleDao.insertRuleInfo(ruleInfo1);
        ruleDao.insertRuleInfo(ruleInfo2);
        ruleDao.insertRuleInfo(ruleInfo3);

        UserRoleInfo userRoleInfo1 = new UserRoleInfo();
        userRoleInfo1.setUserRoleId(UUID.randomUUID().toString());
        userRoleInfo1.setUserRoleName("Admin");
        userRoleInfo1.setUserRoleNote("userRole1Note");
        userRoleInfo1.setRuleInfoList(Arrays.asList(ruleInfo1, ruleInfo2, ruleInfo3));

        userRoleDao.insertUserRoleInfo(userRoleInfo1);
        Map<String,Object>insertParamMap = new HashMap<>();
        insertParamMap.put("userRoleId",userRoleInfo1.getUserRoleId());
        insertParamMap.put("ruleIds", userRoleInfo1.getRuleInfoList());
        userRoleDao.insertRuleInfosToUserRole(insertParamMap);

        UserRoleInfo queryUserRoleInfo = userRoleDao.queryUserRoleInfoByUserRoleId(userRoleInfo1.getUserRoleId());
        Assert.assertNotEquals(queryUserRoleInfo, null);
        Assert.assertEquals(userRoleInfo1, queryUserRoleInfo);

        userRoleInfo1.setUserRoleName("primary");
        userRoleDao.updateUserRoleInfo(userRoleInfo1);
        UserRoleInfo queryUpdUserRole = userRoleDao.queryUserRoleInfoByUserRoleId(userRoleInfo1.getUserRoleId());
        Assert.assertNotEquals(queryUpdUserRole, null);
        Assert.assertEquals(queryUpdUserRole, userRoleInfo1);

        UserRoleInfo userRoleInfo2 = new UserRoleInfo();
        userRoleInfo2.setUserRoleName("monitor");
        userRoleInfo2.setUserRoleId(UUID.randomUUID().toString());
        userRoleInfo2.setUserRoleNote("testUserRoleMonitor");
        userRoleInfo2.setRuleInfoList(Arrays.asList(ruleInfo1, ruleInfo2));
        Map<String,Object>insertMap2 = new HashMap<>();
        insertMap2.put("userRoleId", userRoleInfo2.getUserRoleId());
        insertMap2.put("ruleIds", userRoleInfo2.getRuleInfoList());
        userRoleDao.insertRuleInfosToUserRole(insertMap2);

        UserRoleInfo userRoleInfo3 = new UserRoleInfo();
        userRoleInfo3.setUserRoleName("monitor");
        userRoleInfo3.setUserRoleId(UUID.randomUUID().toString());
        userRoleInfo3.setUserRoleNote("testUserRoleMonitor2");
        userRoleInfo3.setRuleInfoList(Arrays.asList(ruleInfo1, ruleInfo2));
        Map<String,Object>insertMap3 = new HashMap<>();
        insertMap3.put("userRoleId", userRoleInfo3.getUserRoleId());
        insertMap3.put("ruleIds", userRoleInfo3.getRuleInfoList());
        userRoleDao.insertRuleInfosToUserRole(insertMap3);

        List<UserRoleInfo> userRoleInfoList = userRoleDao.queryUserRoleInfoAll();
        Assert.assertNotEquals(userRoleInfoList, null);
        int sum2 = 0;
        for(UserRoleInfo iUserRole : userRoleInfoList){
            if(iUserRole.equals(userRoleInfo1))
                sum2++;
            else if (iUserRole.equals(userRoleInfo2)){
                sum2++;
            }
            else if(iUserRole.equals(userRoleInfo3)){
                sum2++;
            }
        }
        Assert.assertEquals(userRoleInfoList.size(), sum2);

        UserRoleInfo queryUserRoleById = userRoleDao.queryUserRoleInfoByUserRoleId(userRoleInfo1.getUserRoleId());
        Assert.assertEquals(userRoleInfo1, queryUserRoleById);

        List<RuleInfo> userRuleInfoListByName = userRoleDao.queryRuleInfosByUserRoleName("monitor");
        Assert.assertNotEquals(userRuleInfoListByName, null);
        Assert.assertEquals(userRoleInfo2.getRuleInfoList(), userRuleInfoListByName);

        List<RuleInfo>userRuleInfoListById = userRoleDao.queryRuleInfosByUserRoleId(userRoleInfo1.getUserRoleId());
        Assert.assertNotEquals(userRuleInfoListById, null);
        Assert.assertEquals(userRuleInfoListById, userRoleInfo1.getRuleInfoList());
        Map<String,Object>delParamMap = new HashMap<>();
        delParamMap.put("userRoleId",userRoleInfo2.getUserRoleId());
        delParamMap.put("ruleIds",Arrays.asList(ruleInfo2));
        userRoleInfo2.getRuleInfoList().remove(ruleInfo2);
        userRoleDao.deleteRuleInfosFromUserRole(delParamMap);
        //TODO：验证删除权限
    }
}
