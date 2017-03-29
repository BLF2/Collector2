package net.blf2.dao;

import net.blf2.dao.IRuleDao;
import net.blf2.dao.IUserRoleDao;
import net.blf2.entity.RuleInfo;
import net.blf2.entity.UserRoleInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(IUserRoleDaoTest.class);
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
        List<RuleInfo> ruleInfoListToIds = userRoleInfo1.getRuleInfoList();
        List<String> ruleIds1 = new LinkedList<>();
        for(RuleInfo ruleInfo : ruleInfoListToIds){
            ruleIds1.add(ruleInfo.getRuleId());
        }
        insertParamMap.put("ruleIds", ruleIds1);
        userRoleDao.insertRuleInfosToUserRole(insertParamMap);

        UserRoleInfo queryUserRoleInfo = userRoleDao.queryUserRoleInfoByUserRoleId(userRoleInfo1.getUserRoleId());
        Assert.assertNotEquals(queryUserRoleInfo, null);
        Assert.assertEquals(queryUserRoleInfo.getUserRoleId(), userRoleInfo1.getUserRoleId());
        Assert.assertEquals(queryUserRoleInfo.getUserRoleName(),userRoleInfo1.getUserRoleName());
        Assert.assertEquals(queryUserRoleInfo.getRuleInfoList().size(),userRoleInfo1.getRuleInfoList().size());

        userRoleInfo1.setUserRoleName("primary");
        userRoleDao.updateUserRoleInfo(userRoleInfo1);
        UserRoleInfo queryUpdUserRole = userRoleDao.queryUserRoleInfoByUserRoleId(userRoleInfo1.getUserRoleId());
        Assert.assertNotEquals(queryUpdUserRole, null);
        Assert.assertEquals(queryUpdUserRole.getUserRoleName(), userRoleInfo1.getUserRoleName());

        UserRoleInfo userRoleInfo2 = new UserRoleInfo();
        userRoleInfo2.setUserRoleName("monitor");
        userRoleInfo2.setUserRoleId(UUID.randomUUID().toString());
        userRoleInfo2.setUserRoleNote("testUserRoleMonitor");
        userRoleInfo2.setRuleInfoList(Arrays.asList(ruleInfo1, ruleInfo2));
        userRoleDao.insertUserRoleInfo(userRoleInfo2);
        Map<String,Object>insertMap2 = new HashMap<>();
        insertMap2.put("userRoleId", userRoleInfo2.getUserRoleId());
        List<RuleInfo> ruleInfoListToIds2 = userRoleInfo2.getRuleInfoList();
        List<String> ruleIds2 = new LinkedList<>();
        for(RuleInfo ruleInfo : ruleInfoListToIds2){
            ruleIds2.add(ruleInfo.getRuleId());
        }
        insertMap2.put("ruleIds", ruleIds2);
        userRoleDao.insertRuleInfosToUserRole(insertMap2);

        UserRoleInfo userRoleInfo3 = new UserRoleInfo();
        userRoleInfo3.setUserRoleName("monitor");
        userRoleInfo3.setUserRoleId(UUID.randomUUID().toString());
        userRoleInfo3.setUserRoleNote("testUserRoleMonitor2");
        userRoleInfo3.setRuleInfoList(Arrays.asList(ruleInfo1, ruleInfo2));
        userRoleDao.insertUserRoleInfo(userRoleInfo3);
        Map<String,Object>insertMap3 = new HashMap<>();
        insertMap3.put("userRoleId", userRoleInfo3.getUserRoleId());
        List<RuleInfo> ruleInfoListToIds3 = userRoleInfo3.getRuleInfoList();
        List<String> ruleIds3 = new LinkedList<>();
        for(RuleInfo ruleInfo : ruleInfoListToIds3){
            ruleIds3.add(ruleInfo.getRuleId());
        }
        insertMap3.put("ruleIds", ruleIds3);
        userRoleDao.insertRuleInfosToUserRole(insertMap3);

        List<UserRoleInfo> userRoleInfoList = userRoleDao.queryUserRoleInfoAll();
        Assert.assertNotEquals(userRoleInfoList, null);
        int sum2 = 0;
        for(UserRoleInfo iUserRole : userRoleInfoList){
            if(iUserRole.getUserRoleId().equals(userRoleInfo1.getUserRoleId()))
                sum2++;
            else if (iUserRole.getUserRoleId().equals(userRoleInfo2.getUserRoleId())){
                sum2++;
            }
            else if(iUserRole.getUserRoleId().equals(userRoleInfo3.getUserRoleId())){
                sum2++;
            }
        }
        Assert.assertEquals(userRoleInfoList.size(), sum2);

        UserRoleInfo queryUserRoleById = userRoleDao.queryUserRoleInfoByUserRoleId(userRoleInfo1.getUserRoleId());
        Assert.assertEquals(userRoleInfo1.getUserRoleName(), queryUserRoleById.getUserRoleName());

        List<RuleInfo>userRuleInfoListById = userRoleDao.queryRuleInfosByUserRoleId(userRoleInfo1.getUserRoleId());
        Assert.assertNotEquals(userRuleInfoListById, null);
        Assert.assertEquals(userRuleInfoListById.size(), userRoleInfo1.getRuleInfoList().size());
        Map<String,Object>delParamMap = new HashMap<>();
        delParamMap.put("userRoleId", userRoleInfo2.getUserRoleId());
        delParamMap.put("ruleIds", Arrays.asList(ruleInfo2.getRuleId()));
        List<RuleInfo> lsList = new LinkedList<>();
        for(RuleInfo ruleInfo : userRoleInfo2.getRuleInfoList()){
            lsList.add(ruleInfo);
        }
        Assert.assertTrue(lsList.remove(ruleInfo2));
        userRoleInfo2.setRuleInfoList(lsList);
        userRoleDao.deleteRuleInfosFromUserRole(delParamMap);
        List<RuleInfo> delQueryRuleInfoList = userRoleDao.queryRuleInfosByUserRoleId(userRoleInfo2.getUserRoleId());
        Assert.assertNotEquals(delQueryRuleInfoList, null);
        for(RuleInfo ruleInfo : delQueryRuleInfoList){
            System.out.println(ruleInfo.getRuleName());
            logger.info(ruleInfo.getRuleName());
        }
        Assert.assertEquals(delQueryRuleInfoList.size(), userRoleInfo2.getRuleInfoList().size());

        userRoleDao.deleteUserRoleInfoByUserRoleId(userRoleInfo2.getUserRoleId());
        UserRoleInfo delUserRoleInfo = userRoleDao.queryUserRoleInfoByUserRoleId(userRoleInfo2.getUserRoleId());
        Assert.assertEquals(delUserRoleInfo, null);

        userRoleDao.deleteUserRoleInfoByUserRoleIds(Arrays.asList(userRoleInfo1.getUserRoleId(), userRoleInfo3.getUserRoleId()));
        List<UserRoleInfo> queryAllUserRoleInfo = userRoleDao.queryUserRoleInfoAll();
        Assert.assertNotEquals(queryAllUserRoleInfo,null);
        Assert.assertEquals(queryAllUserRoleInfo.size(), 0);
    }
}
