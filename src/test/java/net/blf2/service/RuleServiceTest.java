package net.blf2.service;

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
 * Created by blf2 on 17-5-4.
 * 测试Rule Service
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan(basePackages="net.blf2.dao")
public class RuleServiceTest {
    @Resource
    private IRuleService ruleService;
    @Test
    public void testRuleService(){
        RuleInfo ruleInfo1 = new RuleInfo();
        ruleInfo1.setRuleId(UUID.randomUUID().toString());
        ruleInfo1.setRuleName("Monitor");

        RuleInfo ruleInfo2 = new RuleInfo();
        ruleInfo2.setRuleId(UUID.randomUUID().toString());
        ruleInfo2.setRuleName("Primary");

        RuleInfo ruleInfo3 = new RuleInfo();
        ruleInfo3.setRuleId(UUID.randomUUID().toString());
        ruleInfo3.setRuleName("Admin");

        ruleService.insertRuleInfo(ruleInfo1);
        ruleService.insertRuleInfo(ruleInfo2);
        ruleService.insertRuleInfo(ruleInfo3);
        RuleInfo qRuleInfo1 = ruleService.queryRuleInfoByRuleId(ruleInfo1.getRuleId());
        Assert.assertNotNull(qRuleInfo1);
        Assert.assertEquals(qRuleInfo1.getRuleName(),ruleInfo1.getRuleName());

        ruleInfo1.setRuleName("Admin");
        ruleService.updateRuleInfo(ruleInfo1);
        RuleInfo uRuleInfo = ruleService.queryRuleInfoByRuleId(ruleInfo1.getRuleId());
        Assert.assertNotNull(uRuleInfo);
        Assert.assertEquals(uRuleInfo.getRuleName(),ruleInfo1.getRuleName());

        ruleService.deleteRuleInfoByRuleId(ruleInfo1.getRuleId());
        RuleInfo dRuleInfo = ruleService.queryRuleInfoByRuleId(ruleInfo1.getRuleId());
        Assert.assertNull(dRuleInfo);

        List<RuleInfo> ruleInfoListAll = ruleService.queryRuleInfoAll();
        Assert.assertNotNull(ruleInfoListAll);
        Assert.assertEquals(ruleInfoListAll.size(),2);

        List<String> ruleIds = new LinkedList<>();
        for(RuleInfo ruleInfo : ruleInfoListAll)
            ruleIds.add(ruleInfo.getRuleId());
        ruleService.deleteRuleInfoByRuleIds(ruleIds);

        ruleInfoListAll = ruleService.queryRuleInfoAll();
        Assert.assertNotNull(ruleInfoListAll);
        Assert.assertEquals(ruleInfoListAll.size(),0);
    }
}
