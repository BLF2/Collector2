package net.blf2.dao;

import net.blf2.entity.ClassInfo;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by blf2 on 17-4-1.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan(basePackages="net.blf2.dao")
public class IClassDaoTest {
    @Resource
    private IClassDao classDao;
    @Resource
    private IUserDao userDao;
    @Resource
    private IUserRoleDao userRoleDao;
    @Resource
    private IRuleDao ruleDao;

    @Test
    public void TestClassDao(){

        ClassInfo classInfo1 = new ClassInfo();
        classInfo1.setClassId(UUID.randomUUID().toString());
        classInfo1.setClassGrade("2013");
        classInfo1.setClassNum("03");
        classInfo1.setMajorityName("软件工程");
        classInfo1.setMajorityClass(classInfo1.getMajorityName() + classInfo1.getClassGrade() + classInfo1.getClassNum());

        ClassInfo classInfo2 = new ClassInfo();
        classInfo2.setClassGrade("2013");
        classInfo2.setClassNum("02");
        classInfo2.setClassId(UUID.randomUUID().toString());
        classInfo2.setMajorityName("软件工程");
        classInfo2.setMajorityClass(classInfo2.getMajorityName() + classInfo2.getClassGrade() + classInfo2.getClassNum());

        ClassInfo classInfo3 = new ClassInfo();
        classInfo3.setClassGrade("2014");
        classInfo3.setClassNum("03");
        classInfo3.setClassId(UUID.randomUUID().toString());
        classInfo3.setMajorityName("计算机科学与技术");
        classInfo3.setMajorityName(classInfo3.getMajorityName() + classInfo3.getClassGrade() + classInfo3.getClassNum());

        RuleInfo ruleInfo1 = new RuleInfo();
        ruleInfo1.setRuleId(UUID.randomUUID().toString());
        ruleInfo1.setRuleName("create class");


        UserRoleInfo userRoleInfo1 = new UserRoleInfo();
        userRoleInfo1.setUserRoleName("monitor");
        userRoleInfo1.setUserRoleId(UUID.randomUUID().toString());
        userRoleInfo1.setUserRoleNote("monitor note");
        userRoleInfo1.setRuleInfoList(Arrays.asList(ruleInfo1));

        UserRoleInfo userRoleInfo2 = new UserRoleInfo();
        userRoleInfo2.setUserRoleName("primary");
        userRoleInfo2.setUserRoleNote("primary note");
        userRoleInfo2.setUserRoleId(UUID.randomUUID().toString());
        userRoleInfo2.setRuleInfoList(new ArrayList<RuleInfo>());

        UserInfo userInfo1 = new UserInfo();
        userInfo1.setUserPhone("15800499264");
        userInfo1.setUserRoleInfo(userRoleInfo1);
        userInfo1.setUserNote("user note");
        userInfo1.setUserNum("13110572081");
        userInfo1.setUserPswd("mxh19940822");
        userInfo1.setUserMajorityClass(classInfo1.getMajorityClass());
        userInfo1.setUserName("BLF2");

        UserInfo userInfo2 = new UserInfo();
        userInfo2.setUserName("Hypo");
        userInfo2.setUserPhone("18753377309");
        userInfo2.setUserMajorityClass(classInfo2.getMajorityClass());
        userInfo2.setUserPswd("hypo2526");
        userInfo2.setUserNote("note");
        userInfo2.setUserNum("13110572042");
        userInfo2.setUserRoleInfo(userRoleInfo2);

        UserInfo userInfo4 = new UserInfo();
        userInfo4.setUserName("Ball");
        userInfo4.setUserPhone("18753377305");
        userInfo4.setUserMajorityClass(classInfo2.getMajorityClass());
        userInfo4.setUserPswd("ball2526");
        userInfo4.setUserNote("note");
        userInfo4.setUserNum("13110572043");
        userInfo4.setUserRoleInfo(userRoleInfo2);

        classInfo1.setMonitorInfo(userInfo1);
        classInfo2.setMonitorInfo(userInfo2);

        userDao.insertUserInfo(userInfo1);
        userDao.insertUserInfo(userInfo2);
        userDao.insertUserInfo(userInfo4);

        classDao.insertClassInfo(classInfo1);
        classDao.insertClassInfo(classInfo2);
        ClassInfo queryClassInfo = classDao.queryClassInfoByClassId(classInfo1.getClassId());
        Assert.assertNotNull(queryClassInfo);
        Assert.assertEquals(classInfo1.getMajorityClass(), queryClassInfo.getMajorityClass());
        Assert.assertNotNull(queryClassInfo.getMonitorInfo());
        Assert.assertEquals(classInfo1.getMonitorInfo().getUserNum(), queryClassInfo.getMonitorInfo().getUserNum());

        List<ClassInfo> classInfoList = classDao.queryClassInfoAll();
        Assert.assertNotNull(classInfoList);
        Assert.assertEquals(classInfoList.size(), 2);

        String queryClassId = classDao.queryClassIdByMonitorId(classInfo1.getMonitorInfo().getUserNum());
        Assert.assertNotNull(queryClassId);
        Assert.assertEquals(queryClassId, classInfo1.getClassId());

        classInfo1.setClassNum("04");
        classInfo1.setMajorityClass(classInfo1.getMajorityName() + classInfo1.getClassGrade() + classInfo1.getClassNum());
        classDao.updateClassInfo(classInfo1);
        ClassInfo queryUpdClassInfo = classDao.queryClassInfoByClassId(classInfo1.getClassId());
        Assert.assertNotNull(queryUpdClassInfo);
        Assert.assertEquals(queryUpdClassInfo.getMajorityClass(), classInfo1.getMajorityClass());

        ClassInfo queryClassInfoByMid = classDao.queryClassInfoByMonitorId(classInfo1.getMonitorInfo().getUserNum());
        Assert.assertNotNull(queryClassInfoByMid);
        Assert.assertEquals(queryClassInfoByMid.getMajorityClass(), classInfo1.getMajorityClass());

        List<UserInfo> userInfoList = classDao.queryUserInfosByMojrityClass(classInfo2.getMajorityClass());
        Assert.assertNotNull(userInfoList);
        Assert.assertEquals(userInfoList.size(), 2);

        List<UserInfo> userInfoAll = userDao.queryUserInfoAll();
        for(UserInfo userInfo : userInfoAll){
            userDao.deleteUserInfoByUserNum(userInfo.getUserNum());
        }

        classDao.deleteClassInfoByClassId(classInfo1.getClassId());
        ClassInfo queryDelClassInfo = classDao.queryClassInfoByClassId(classInfo1.getClassId());
        Assert.assertEquals(queryDelClassInfo, null);

        classDao.deleteClassInfoByClassIds(Arrays.asList(classInfo2.getClassId(), classInfo3.getClassId()));
        List<ClassInfo> classInfoAll = classDao.queryClassInfoAll();
        Assert.assertNotNull(classInfoAll);
        Assert.assertEquals(classInfoAll.size(),0);
    }
}
