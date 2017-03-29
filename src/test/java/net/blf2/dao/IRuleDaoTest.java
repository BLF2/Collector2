package net.blf2.dao;

import net.blf2.dao.IRuleDao;
import net.blf2.entity.RuleInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Created by blf2 on 17-3-29.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan(basePackages="net.blf2.dao")
public class IRuleDaoTest {

    @Resource
    private IRuleDao ruleDao;

    public IRuleDao getRuleDao() {
        return ruleDao;
    }

    public void setRuleDao(IRuleDao ruleDao) {
        this.ruleDao = ruleDao;
    }

    @Test
    public void TestRuleDao(){
        RuleInfo ruleInfo1 = new RuleInfo();
        ruleInfo1.setRuleId(UUID.randomUUID().toString());
        ruleInfo1.setRuleName("testName1");

        RuleInfo ruleInfo2 = new RuleInfo();
        ruleInfo2.setRuleName("testName2");
        ruleInfo2.setRuleId(UUID.randomUUID().toString());

        RuleInfo ruleInfo3 = new RuleInfo();
        ruleInfo3.setRuleName("testName3");
        ruleInfo3.setRuleId(UUID.randomUUID().toString());

        try{
            ruleDao.insertRuleInfo(ruleInfo1);
            ruleDao.insertRuleInfo(ruleInfo2);
            ruleDao.insertRuleInfo(ruleInfo3);
            List<RuleInfo> ruleInfoList = ruleDao.queryRuleInfoAll();
            Assert.assertNotEquals(ruleInfoList, null);
            int sum = 0;
            for(RuleInfo iRuleInfo : ruleInfoList){
                if(iRuleInfo.equals(ruleInfo1))
                    sum++;
                else if(iRuleInfo.equals(ruleInfo2))
                    sum++;
                else if (iRuleInfo.equals(ruleInfo3))
                    sum++;
            }
            Assert.assertEquals(ruleInfoList.size(), sum);

            ruleInfo1.setRuleName("testChange");
            ruleDao.updateRuleInfo(ruleInfo1);
            RuleInfo updRuleInfo = ruleDao.queryRuleInfoByRuleId(ruleInfo1.getRuleId());
            Assert.assertNotEquals(updRuleInfo, null);
            Assert.assertEquals(updRuleInfo, ruleInfo1);

            ruleDao.deleteRuleInfoByRuleId(ruleInfo3.getRuleId());
            RuleInfo updRuleInfo3 = ruleDao.queryRuleInfoByRuleId(ruleInfo3.getRuleId());
            Assert.assertEquals(updRuleInfo3,null);

            List<String> ruleIds = new LinkedList<String>();
            for (RuleInfo iRuleInfo : ruleInfoList){
                ruleIds.add(iRuleInfo.getRuleId());
            }

            ruleDao.deleteRuleInfoByRuleIds(ruleIds);

            List<RuleInfo> delRuleInfoList = ruleDao.queryRuleInfoAll();
            Assert.assertNotEquals(delRuleInfoList,null);
            Assert.assertEquals(delRuleInfoList.size(),0);
        }catch (Exception ex){
            ex.printStackTrace();
            Assert.assertTrue(false);
        }

    }
}
