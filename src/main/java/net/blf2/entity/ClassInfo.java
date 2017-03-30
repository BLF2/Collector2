package net.blf2.entity;

import java.util.List;

/**
 * Created by blf2 on 17-3-30.
 * 班级信息
 */
public class ClassInfo {
    private String classId;//班级Id
    private String majorityName;//专业名称
    private String classGrade;//年级
    private String classNum;//班级号
    private String majorityClass;//专业+年级+班级
    private UserInfo monitorInfo;//班长信息
    private List<UserInfo> classMates;//本班所有同学的信息
    private String classNote;//备注

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }


    public String getClassGrade() {
        return classGrade;
    }

    public void setClassGrade(String classGrade) {
        this.classGrade = classGrade;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public String getMajorityClass() {
        return majorityClass;
    }

    public void setMajorityClass(String majorityClass) {
        this.majorityClass = majorityClass;
    }

    public String getClassNote() {
        return classNote;
    }

    public void setClassNote(String classNote) {
        this.classNote = classNote;
    }

    public UserInfo getMonitorInfo() {
        return monitorInfo;
    }

    public void setMonitorInfo(UserInfo monitorInfo) {
        this.monitorInfo = monitorInfo;
    }

    public List<UserInfo> getClassMates() {
        return classMates;
    }

    public void setClassMates(List<UserInfo> classMates) {
        this.classMates = classMates;
    }

    public String getMajorityName() {
        return majorityName;
    }

    public void setMajorityName(String majorityName) {
        this.majorityName = majorityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClassInfo)) return false;

        ClassInfo classInfo = (ClassInfo) o;

        if (!getClassId().equals(classInfo.getClassId())) return false;
        if (!getMajorityName().equals(classInfo.getMajorityName())) return false;
        if (!getClassGrade().equals(classInfo.getClassGrade())) return false;
        if (!getClassNum().equals(classInfo.getClassNum())) return false;
        if (!getMajorityClass().equals(classInfo.getMajorityClass())) return false;
        if (!getMonitorInfo().equals(classInfo.getMonitorInfo())) return false;
        if (getClassMates() != null ? !getClassMates().equals(classInfo.getClassMates()) : classInfo.getClassMates() != null)
            return false;
        return !(getClassNote() != null ? !getClassNote().equals(classInfo.getClassNote()) : classInfo.getClassNote() != null);

    }

    @Override
    public int hashCode() {
        int result = getClassId().hashCode();
        result = 31 * result + getMajorityName().hashCode();
        result = 31 * result + getClassGrade().hashCode();
        result = 31 * result + getClassNum().hashCode();
        result = 31 * result + getMajorityClass().hashCode();
        result = 31 * result + getMonitorInfo().hashCode();
        result = 31 * result + (getClassMates() != null ? getClassMates().hashCode() : 0);
        result = 31 * result + (getClassNote() != null ? getClassNote().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ClassInfo{" +
                "classId='" + classId + '\'' +
                ", majorityName='" + majorityName + '\'' +
                ", classGrade='" + classGrade + '\'' +
                ", classNum='" + classNum + '\'' +
                ", majorityClass='" + majorityClass + '\'' +
                ", monitorInfo=" + monitorInfo +
                ", classMates=" + classMates +
                ", classNote='" + classNote + '\'' +
                '}';
    }
}
