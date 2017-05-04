package net.blf2.service.impl;

import net.blf2.dao.IClassDao;
import net.blf2.entity.ClassInfo;
import net.blf2.entity.UserInfo;
import net.blf2.exception.DeleteException;
import net.blf2.exception.InsertException;
import net.blf2.exception.UpdateException;
import net.blf2.service.IClassService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by blf2 on 17-4-28.
 * 班级操作service
 */
@Service("ClassServiceImpl")
@Transactional(rollbackFor = RuntimeException.class)
public class ClassServiceImpl implements IClassService{

    @Resource
    private IClassDao classDao;

    @Override
    public void insertClassInfo(ClassInfo classInfo) {
        try {
            classDao.insertClassInfo(classInfo);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new InsertException(this);
        }
    }

    @Override
    public void updateClassInfo(ClassInfo classInfo) {
        try {
            classDao.updateClassInfo(classInfo);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new UpdateException(this);
        }
    }

    @Override
    public void deleteClassInfoByClassId(String classId) {
        try {
            classDao.deleteClassInfoByClassId(classId);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new DeleteException(this);
        }
    }

    @Override
    public void deleteClassInfoByClassIds(List<String> classIds) {
        try {
            classDao.deleteClassInfoByClassIds(classIds);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new DeleteException(this);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ClassInfo queryClassInfoByClassId(String classId) {
        return classDao.queryClassInfoByClassId(classId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClassInfo> queryClassInfoAll() {
        return classDao.queryClassInfoAll();
    }

    @Override
    @Transactional(readOnly = true)
    public ClassInfo queryClassInfoByMonitorId(String monitorId) {
        return classDao.queryClassInfoByMonitorId(monitorId);
    }

    @Override
    @Transactional(readOnly = true)
    public String queryClassIdByMonitorId(String monitorId) {
        return classDao.queryClassIdByMonitorId(monitorId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserInfo> queryUserInfosByMojrityClass(String majorityClass) {
        return classDao.queryUserInfosByMojrityClass(majorityClass);
    }
}
