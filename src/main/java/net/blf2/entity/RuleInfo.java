package net.blf2.entity;

/**
 * Created by blf2 on 17-3-20.
 * 权限信息
 */
public class RuleInfo {
    private String ruleId;//权限id
    private String ruleName;//权限名称

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }
}
