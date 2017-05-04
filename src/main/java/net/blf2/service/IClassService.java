package net.blf2.service;

import net.blf2.entity.ClassInfo;
import net.blf2.entity.UserInfo;

import java.util.List;

/**
 * Created by blf2 on 17-4-28.
 * 班级操作
 */
public interface IClassService {
    void insertClassInfo(ClassInfo classInfo);//插入班级信息
    void updateClassInfo(ClassInfo classInfo);//更新班级信息
    void deleteClassInfoByClassId(String classId);//根据Id删除班级信息
    void deleteClassInfoByClassIds(List<String> classIds);//根据多个Id删除对应的信息
    ClassInfo queryClassInfoByClassId(String classId);//根据Id查询班级信息
    List<ClassInfo> queryClassInfoAll();//查询所有班级信息
    ClassInfo queryClassInfoByMonitorId(String monitorId);//根据班长Id查询班级信息
    String queryClassIdByMonitorId(String monitorId);//根据班长Id查询班级Id
    List<UserInfo> queryUserInfosByMojrityClass(String majorityClass);//根据专业班级查询本班所有人信息
}
