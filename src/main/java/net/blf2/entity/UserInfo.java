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
}
