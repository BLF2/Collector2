package net.blf2.dao;

import net.blf2.entity.ClassInfo;
import net.blf2.entity.UserInfo;

import java.util.List;

/**
 * Created by blf2 on 17-3-30.
 * 班级操作接口
 */
public interface IClassDao {
    boolean insertClassInfo(ClassInfo classInfo);
    boolean updateClassInfo(ClassInfo classInfo);
    boolean deleteClassInfoByClassId(String classId);
    boolean deleteClassInfoByClassIds(List<String> classIds);
    ClassInfo queryClassInfoByClassId(String classId);
    List<ClassInfo> queryClassInfoAll();
    ClassInfo queryClassInfoByMonitorId(String monitorId);
    String queryClassIdByMonitorId(String monitorId);
    List<UserInfo> queryUserInfosByMojrityClass(String majorityClass);
}
