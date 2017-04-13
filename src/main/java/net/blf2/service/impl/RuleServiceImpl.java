package net.blf2.service.impl;

import net.blf2.dao.IRuleDao;
import net.blf2.entity.RuleInfo;
import net.blf2.exception.DeleteException;
import net.blf2.exception.InsertException;
import net.blf2.exception.UpdateException;
import net.blf2.service.IRuleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by blf2 on 17-4-13.
 * IRuleService的具体实现类
 */
@Service("RuleServiceImpl")
@Transactional(rollbackFor = RuntimeException.class)
public class RuleServiceImpl implements IRuleService {

    @Resource
    private IRuleDao ruleDao;

    public IRuleDao getRuleDao() {
        return ruleDao;
    }

    public void setRuleDao(IRuleDao ruleDao) {
        this.ruleDao = ruleDao;
    }

    @Override
    public void insertRuleInfo(RuleInfo ruleInfo) {
        try {
            ruleDao.insertRuleInfo(ruleInfo);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new InsertException(this);
        }
    }

    @Override
    public void deleteRuleInfoByRuleId(String ruleId) {
        try {
            ruleDao.deleteRuleInfoByRuleId(ruleId);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new DeleteException(this);
        }
    }

    @Override
    public void deleteRuleInfoByRuleIds(List<String> ruleIds) {
        try {
            ruleDao.deleteRuleInfoByRuleIds(ruleIds);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new DeleteException(this);
        }
    }

    @Override
    public void updateRuleInfo(RuleInfo ruleInfo) {
        try {
            ruleDao.updateRuleInfo(ruleInfo);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new UpdateException(this);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public RuleInfo queryRuleInfoByRuleId(String ruleId) {
        return ruleDao.queryRuleInfoByRuleId(ruleId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RuleInfo> queryRuleInfoAll() {
        return ruleDao.queryRuleInfoAll();
    }
}
