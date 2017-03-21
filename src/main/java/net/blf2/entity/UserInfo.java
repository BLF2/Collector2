package net.blf2.entity;

/**
 * Created by blf2 on 17-3-20.
 * 用户信息
 */
public class UserInfo {
    private String userNum;
    private String userName;
    private String userPswd;
    private String userPhone;
    private String userMajorityClass;
    private String userNote;
    private UserRoleInfo userRoleInfo;

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
}
