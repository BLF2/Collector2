package net.blf2.entity;

/**
 * Created by blf2 on 17-3-20.
 * 用户信息
 */
public class UserInfo {
    private String userNum;//学号
    private String userName;//姓名
    private String userPswd;//密码
    private String userPhone;//手机号
    private String userMajorityClass;//主修班级
    private String userNote;//用户备注
    private UserRoleInfo userRoleInfo;//用户角色信息

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPswd() {
        return userPswd;
    }

    public void setUserPswd(String userPswd) {
        this.userPswd = userPswd;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserMajorityClass() {
        return userMajorityClass;
    }

    public void setUserMajorityClass(String userMajorityClass) {
        this.userMajorityClass = userMajorityClass;
    }

    public String getUserNote() {
        return userNote;
    }

    public void setUserNote(String userNote) {
        this.userNote = userNote;
    }

    public UserRoleInfo getUserRoleInfo() {
        return userRoleInfo;
    }

    public void setUserRoleInfo(UserRoleInfo userRoleInfo) {
        this.userRoleInfo = userRoleInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserInfo)) return false;

        UserInfo userInfo = (UserInfo) o;

        if (!getUserNum().equals(userInfo.getUserNum())) return false;
        if (getUserName() != null ? !getUserName().equals(userInfo.getUserName()) : userInfo.getUserName() != null)
            return false;
        if (getUserPswd() != null ? !getUserPswd().equals(userInfo.getUserPswd()) : userInfo.getUserPswd() != null)
            return false;
        if (getUserPhone() != null ? !getUserPhone().equals(userInfo.getUserPhone()) : userInfo.getUserPhone() != null)
            return false;
        if (getUserMajorityClass() != null ? !getUserMajorityClass().equals(userInfo.getUserMajorityClass()) : userInfo.getUserMajorityClass() != null)
            return false;
        if (getUserNote() != null ? !getUserNote().equals(userInfo.getUserNote()) : userInfo.getUserNote() != null)
            return false;
        return !(getUserRoleInfo() != null ? !getUserRoleInfo().equals(userInfo.getUserRoleInfo()) : userInfo.getUserRoleInfo() != null);

    }

    @Override
    public int hashCode() {
        int result = getUserNum().hashCode();
        result = 31 * result + (getUserName() != null ? getUserName().hashCode() : 0);
        result = 31 * result + (getUserPswd() != null ? getUserPswd().hashCode() : 0);
        result = 31 * result + (getUserPhone() != null ? getUserPhone().hashCode() : 0);
        result = 31 * result + (getUserMajorityClass() != null ? getUserMajorityClass().hashCode() : 0);
        result = 31 * result + (getUserNote() != null ? getUserNote().hashCode() : 0);
        result = 31 * result + (getUserRoleInfo() != null ? getUserRoleInfo().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userNum='" + userNum + '\'' +
                ", userName='" + userName + '\'' +
                ", userPswd='" + userPswd + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userMajorityClass='" + userMajorityClass + '\'' +
                ", userNote='" + userNote + '\'' +
                ", userRoleInfo=" + userRoleInfo +
                '}';
    }
}
