package net.blf2.dao;

import net.blf2.entity.RuleInfo;
import net.blf2.entity.UserInfo;
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
 * Created by blf2 on 17-3-30.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan(basePackages = "net.blf2.dao")
public class IUserDaoTest {
    @Resource
    private IUserDao userDao;
    @Resource
    private IUserRoleDao userRoleDao;
    @Resource
    private IRuleDao ruleDao;

    @Test
    public void TestIUserDao() {
        RuleInfo ruleInfo1 = new RuleInfo();
        ruleInfo1.setRuleId(UUID.randomUUID().toString());
        ruleInfo1.setRuleName("testRule1Name");

        RuleInfo ruleInfo2 = new RuleInfo();
        ruleInfo2.setRuleId(UUID.randomUUID().toString());
        ruleInfo2.setRuleName("testRule2Name");

        RuleInfo ruleInfo3 = new RuleInfo();
        ruleInfo3.setRuleName("testRule3Name");
        ruleInfo3.setRuleId(UUID.randomUUID().toString());

        ruleDao.insertRuleInfo(ruleInfo1);
        ruleDao.insertRuleInfo(ruleInfo2);
        ruleDao.insertRuleInfo(ruleInfo3);

        UserRoleInfo userRoleInfo1 = new UserRoleInfo();
        userRoleInfo1.setUserRoleId(UUID.randomUUID().toString());
        userRoleInfo1.setUserRoleName("admin");
        userRoleInfo1.setRuleInfoList(Arrays.asList(ruleInfo1, ruleInfo2, ruleInfo3));

        UserRoleInfo userRoleInfo2 = new UserRoleInfo();
        userRoleInfo2.setUserRoleId(UUID.randomUUID().toString());
        userRoleInfo2.setUserRoleName("monitor");
        userRoleInfo2.setRuleInfoList(Arrays.asList(ruleInfo2, ruleInfo3));

        UserRoleInfo userRoleInfo3 = new UserRoleInfo();
        userRoleInfo3.setUserRoleId(UUID.randomUUID().toString());
        userRoleInfo3.setUserRoleName("primary");
        userRoleInfo3.setRuleInfoList(Arrays.asList(ruleInfo3));

        userRoleDao.insertUserRoleInfo(userRoleInfo1);
        Map<String, Object> userRoleRuleParam1 = new HashMap<>();
        userRoleRuleParam1.put("userRoleId", userRoleInfo1.getUserRoleId());
        List<String> ruleIds1 = new LinkedList<>();
        for (RuleInfo ruleInfo : userRoleInfo1.getRuleInfoList()) {
            ruleIds1.add(ruleInfo.getRuleId());
        }
        userRoleRuleParam1.put("ruleIds", ruleIds1);
        userRoleDao.insertRuleInfosToUserRole(userRoleRuleParam1);

        userRoleDao.insertUserRoleInfo(userRoleInfo2);
        Map<String, Object> userRoleRuleParam2 = new HashMap<>();
        userRoleRuleParam2.put("userRoleId", userRoleInfo2.getUserRoleId());
        List<String> ruleIds2 = new LinkedList<>();
        for (RuleInfo ruleInfo : userRoleInfo2.getRuleInfoList()) {
            ruleIds2.add(ruleInfo.getRuleId());
        }
        userRoleRuleParam2.put("ruleIds", ruleIds2);
        userRoleDao.insertRuleInfosToUserRole(userRoleRuleParam2);

        userRoleDao.insertUserRoleInfo(userRoleInfo3);
        Map<String, Object> userRoleRuleParam3 = new HashMap<>();
        userRoleRuleParam3.put("userRoleId", userRoleInfo3.getUserRoleId());
        List<String> ruleIds3 = new LinkedList<>();
        for (RuleInfo ruleInfo : userRoleInfo3.getRuleInfoList()) {
            ruleIds3.add(ruleInfo.getRuleId());
        }
        userRoleRuleParam3.put("ruleIds", ruleIds3);
        userRoleDao.insertRuleInfosToUserRole(userRoleRuleParam3);

        UserInfo userInfo1 = new UserInfo();
        userInfo1.setUserPhone("15800499264");
        userInfo1.setUserPswd("123456");
        userInfo1.setUserMajorityClass("软件201303");
        userInfo1.setUserName("孟宪厚");
        userInfo1.setUserNum("13110572081");
        userInfo1.setUserNote("admin");
        userInfo1.setUserRoleInfo(userRoleInfo1);

        UserInfo userInfo2 = new UserInfo();
        userInfo2.setUserPhone("15800499265");
        userInfo2.setUserPswd("123456");
        userInfo2.setUserMajorityClass("软件201302");
        userInfo2.setUserName("陈海波");
        userInfo2.setUserNum("13110572042");
        userInfo2.setUserNote("monitor");
        userInfo2.setUserRoleInfo(userRoleInfo2);

        UserInfo userInfo3 = new UserInfo();
        userInfo3.setUserPhone("15800499266");
        userInfo3.setUserPswd("123456");
        userInfo3.setUserMajorityClass("软件201302");
        userInfo3.setUserName("RokyZone");
        userInfo3.setUserNum("13110572073");
        userInfo3.setUserNote("primary");
        userInfo3.setUserRoleInfo(userRoleInfo3);

        UserInfo userInfo4 = new UserInfo();
        userInfo4.setUserPhone("15800499267");
        userInfo4.setUserPswd("123456");
        userInfo4.setUserMajorityClass("软件201304");
        userInfo4.setUserName("I Dont Known");
        userInfo4.setUserNum("13110572124");
        userInfo4.setUserNote("primary");
        userInfo4.setUserRoleInfo(userRoleInfo3);

        userDao.insertUserInfo(userInfo1);
        userDao.insertUserInfo(userInfo2);
        userDao.insertUserInfo(userInfo3);
        userDao.insertUserInfo(userInfo4);
        UserInfo queryIstUserInfo = userDao.queryUserInfoByUserNum(userInfo1.getUserNum());
        Assert.assertNotEquals(queryIstUserInfo, null);
        Assert.assertEquals(queryIstUserInfo.getUserPhone(), userInfo1.getUserPhone());

        UserInfo queryIsrUserInfo2 = userDao.queryUserInfoByUserPhone(userInfo2.getUserPhone());
        Assert.assertNotEquals(queryIsrUserInfo2, null);
        Assert.assertEquals(queryIsrUserInfo2.getUserNum(), userInfo2.getUserNum());

        String queryUserNum = userDao.queryUserNumByUserPhone(userInfo2.getUserPhone());
        Assert.assertNotEquals(queryUserNum, null);
        Assert.assertEquals(userInfo2.getUserNum(), queryUserNum);

        List<UserInfo> queryUserInfos1 = userDao.queryUserInfoAll();
        Assert.assertNotEquals(queryUserInfos1, null);
        Assert.assertEquals(queryUserInfos1.size(), 4);

        List<UserInfo> queryUserInfos2 = userDao.queryUserInfoByUserRoleId(userRoleInfo3.getUserRoleId());
        Assert.assertNotNull(queryIsrUserInfo2);
        Assert.assertEquals(queryUserInfos2.size(), 2);

        userInfo4.setUserPhone("18753377310");
        userInfo4.setUserPswd("1234567");
        userInfo4.setUserMajorityClass("软件201305");
        userInfo4.setUserName("updateit");
        userInfo4.setUserNote("primary test");
        userInfo4.setUserRoleInfo(userRoleInfo2);
        userDao.updateUserInfo(userInfo4);
        UserInfo queryUpdUserInfo1 = userDao.queryUserInfoByUserNum(userInfo4.getUserNum());
        Assert.assertNotEquals(queryUpdUserInfo1, null);
        Assert.assertEquals(userInfo4.getUserPhone(), queryUpdUserInfo1.getUserPhone());

        userDao.deleteUserInfoByUserNum(userInfo1.getUserNum());
        UserInfo queryDelUserInfo1 = userDao.queryUserInfoByUserNum(userInfo1.getUserNum());
        Assert.assertNull(queryDelUserInfo1);

        List<String> delList = Arrays.asList(userInfo2.getUserNum(), userInfo3.getUserNum(), userInfo4.getUserNum());
        userDao.deleteUserInfoByUserNums(delList);
        List<UserInfo> queryDelUserInfoAll = userDao.queryUserInfoAll();
        Assert.assertNotNull(queryDelUserInfoAll);
        Assert.assertEquals(queryDelUserInfoAll.size(), 0);

        ruleDao.deleteRuleInfoByRuleIds(Arrays.asList(ruleInfo1.getRuleId(), ruleInfo2.getRuleId(), ruleInfo3.getRuleId()));
        userRoleDao.deleteUserRoleInfoByUserRoleIds(Arrays.asList(userRoleInfo1.getUserRoleId(), userRoleInfo2.getUserRoleId(),
                userRoleInfo3.getUserRoleId()));

        Map<String, Object> delUserRoleRule = new HashMap<>();
        delUserRoleRule.put("userRoleId", userRoleInfo1.getUserRoleId());
        delUserRoleRule.put("ruleIds", ruleIds1);
        userRoleDao.deleteRuleInfosFromUserRole(delUserRoleRule);

        delUserRoleRule.put("userRoleId", userRoleInfo2.getUserRoleId());
        delUserRoleRule.put("ruleIds", ruleIds2);
        userRoleDao.deleteRuleInfosFromUserRole(delUserRoleRule);

        delUserRoleRule.put("userRoleId", userRoleInfo3.getUserRoleId());
        delUserRoleRule.put("ruleIds", ruleIds3);
        userRoleDao.deleteRuleInfosFromUserRole(delUserRoleRule);
    }
}
