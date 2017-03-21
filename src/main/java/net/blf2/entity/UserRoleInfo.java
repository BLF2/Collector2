package net.blf2.entity;

import java.util.List;

/**
 * Created by blf2 on 17-3-20.
 * 用户角色
 */
public class UserRoleInfo {
    private String userRoleId;
    private String userRoleName;
    private String userRoleNote;
    private List<RuleInfo> ruleInfoList;

    public String getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(String userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getUserRoleName() {
        return userRoleName;
    }

    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }

    public String getUserRoleNote() {
        return userRoleNote;
    }

    public void setUserRoleNote(String userRoleNote) {
        this.userRoleNote = userRoleNote;
    }

    public List<RuleInfo> getRuleInfoList() {
        return ruleInfoList;
    }

    public void setRuleInfoList(List<RuleInfo> ruleInfoList) {
        this.ruleInfoList = ruleInfoList;
    }
}
