package net.blf2.controller;

import net.blf2.entity.ClassInfo;
import net.blf2.entity.UserInfo;
import net.blf2.service.IClassService;
import net.blf2.service.IUserService;
import net.blf2.util.Consts;
import net.blf2.util.LoginUtil;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by blf2 on 17-5-24.
 */
@Controller
@RequestMapping("/Class")
public class ClassController {
    @Resource
    private UserController userController;
    @Resource
    private IClassService classService;
    @Resource
    private IUserService userService;

    @RequestMapping("/toClassManager")
    public ModelAndView toClassManager(ModelAndView model) {
        if (LoginUtil.getCurrentUser() == null)
            return userController.returnError("未登录或者未授权");
        if (LoginUtil.currentUserIsAdmin() || LoginUtil.cuurentUserIsMonitor()) {
            List<ClassInfo> classListAll = classService.queryClassInfoAll();
            List<UserInfo> userListAll = userService.queryUserInfoAll();
            model.addObject(Consts.CLASS_LIST_ALL, classListAll);
            model.addObject(Consts.USER_LIST_ALL, userListAll);
            model.addObject(Consts.CONST_APOS, "'");
            model.setViewName("classinfomanager");
            return model;
        }
        return userController.returnError("未授权");
    }

    @RequestMapping("/updateClass")
    public ModelAndView updateClass(ModelAndView model, @RequestParam("majorityName") String majorityName,
                                    @RequestParam("classGrade") String classGrade,
                                    @RequestParam("classNum") String classNum,
                                    @RequestParam("monitorId") String monitorId,
                                    @RequestParam("editClassId") String classId) {
        UserInfo loginUser = LoginUtil.getCurrentUser();
        if (loginUser == null || !Consts.ADMIN_ROLE_NAME.equals(loginUser.getUserRoleInfo().getUserRoleName()))
            return userController.returnError("未登录或者未授权");
        try {
            ClassInfo classInfo = classService.queryClassInfoByClassId(classId);
            UserInfo userInfo = userService.queryUserInfoByUserNum(monitorId);
            if (classInfo == null || userInfo == null) {
                return userController.returnError("数据错误");
            }
            classInfo.setMajorityName(majorityName);
            classInfo.setMonitorInfo(userInfo);
            classInfo.setClassNum(classNum);
            classInfo.setClassGrade(classGrade);
            classInfo.setMajorityClass(majorityName + classGrade + classNum);
            classService.updateClassInfo(classInfo);
            model.addObject(Consts.OPRERATOR_MESSAGE,"修改成功");
            model.setViewName("classinfomanager");
        }catch (Exception ex){
            ex.printStackTrace();
            model = userController.returnError("数据库出错");
        }finally {
            return model;
        }
    }
    @RequestMapping("deleteClass")
    public ModelAndView deleteClassInfo(ModelAndView model,@RequestParam("classId")String classId){
        UserInfo userInfo = LoginUtil.getCurrentUser();
        if(userInfo == null || !Consts.ADMIN_ROLE_NAME.equals(userInfo.getUserRoleInfo().getUserRoleName())){
            return userController.returnError("未授权或者未登录");
        }
        try {
            ClassInfo classInfo = classService.queryClassInfoByClassId(classId);
            if (classInfo == null)
                model = userController.returnError("提交信息出错");
            classService.deleteClassInfoByClassId(classId);
            model.addObject(Consts.OPRERATOR_MESSAGE,"删除成功");
            model.setViewName("classinfomanager");
        }catch (Exception ex){
            ex.printStackTrace();
            model = userController.returnError("数据库出错");
        }finally {
            return model;
        }

    }
    @RequestMapping(value = "/insertClass",method = RequestMethod.POST)
    public ModelAndView insertClass(ModelAndView model, @RequestParam("majorityName") String majorityName,
                                    @RequestParam("classGrade") String classGrade,
                                    @RequestParam("classNum") String classNum,
                                    @RequestParam("monitorId") String monitorId,
                                    @RequestParam("editClassId") String classId) {
        UserInfo loginUser = LoginUtil.getCurrentUser();
        if (loginUser == null || !Consts.ADMIN_ROLE_NAME.equals(loginUser.getUserRoleInfo().getUserRoleName()))
            return userController.returnError("未登录或者未授权");
        try {
            ClassInfo classInfo = classService.queryClassInfoByClassId(classId);
            UserInfo userInfo = userService.queryUserInfoByUserNum(monitorId);
            if (classInfo == null || userInfo == null) {
                return userController.returnError("数据错误");
            }
            classInfo.setMajorityName(majorityName);
            classInfo.setMonitorInfo(userInfo);
            classInfo.setClassNum(classNum);
            classInfo.setClassGrade(classGrade);
            classInfo.setMajorityClass(majorityName + classGrade + classNum);
            classService.insertClassInfo(classInfo);
            model.addObject(Consts.OPRERATOR_MESSAGE,"增加成功");
            model.setViewName("classinfomanager");
        }catch (Exception ex){
            ex.printStackTrace();
            model = userController.returnError("数据库出错");
        }finally {
            return model;
        }
    }
}

