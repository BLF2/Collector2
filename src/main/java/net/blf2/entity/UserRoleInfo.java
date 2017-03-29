package net.blf2.entity;

import java.util.List;

/**
 * Created by blf2 on 17-3-20.
 * 用户角色
 */
public class UserRoleInfo {
    private String userRoleId;//用户角色Id
    private String userRoleName;//用户角色名称
    private String userRoleNote;//用户角色备注
    private List<RuleInfo> ruleInfoList;//具有的权限

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRoleInfo)) return false;

        UserRoleInfo that = (UserRoleInfo) o;

        if (!getUserRoleId().equals(that.getUserRoleId())) return false;
        if (!getUserRoleName().equals(that.getUserRoleName())) return false;
        if (getUserRoleNote() != null ? !getUserRoleNote().equals(that.getUserRoleNote()) : that.getUserRoleNote() != null)
            return false;
        return getRuleInfoList().equals(that.getRuleInfoList());

    }

    @Override
    public int hashCode() {
        int result = getUserRoleId().hashCode();
        result = 31 * result + getUserRoleName().hashCode();
        result = 31 * result + (getUserRoleNote() != null ? getUserRoleNote().hashCode() : 0);
        result = 31 * result + getRuleInfoList().hashCode();
        return result;
    }
}
