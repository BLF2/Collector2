package net.blf2.controller;

import net.blf2.entity.ClassInfo;
import net.blf2.entity.TemplateForPage;
import net.blf2.entity.UserInfo;
import net.blf2.entity.UserRoleInfo;
import net.blf2.service.impl.TemplateService;
import net.blf2.util.Consts;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Created by blf2 on 17-5-21.
 * 测试用controller
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @Resource
    private TemplateService templateService;
    @RequestMapping("/toAdmin")
    public ModelAndView toAdmin(){
        UserRoleInfo userRoleInfo = new UserRoleInfo();
        userRoleInfo.setUserRoleId(UUID.randomUUID().toString());
        userRoleInfo.setUserRoleName("admin");

        UserRoleInfo userRoleInfo1 = new UserRoleInfo();
        userRoleInfo1.setUserRoleId(UUID.randomUUID().toString());
        userRoleInfo1.setUserRoleName("monitor");

        UserRoleInfo userRoleInfo2 = new UserRoleInfo();
        userRoleInfo2.setUserRoleId(UUID.randomUUID().toString());
        userRoleInfo2.setUserRoleName("primary");

        ClassInfo classInfo = new ClassInfo();
        classInfo.setClassId(UUID.randomUUID().toString());
        classInfo.setClassGrade("2013");
        classInfo.setClassNum("04");
        classInfo.setMajorityName("软件");
        classInfo.setMajorityClass(classInfo.getMajorityName()+classInfo.getClassGrade()+classInfo.getClassNum());

        UserInfo userInfoM = new UserInfo();
        userInfoM.setUserNum("13110572082");
        userInfoM.setUserRoleInfo(userRoleInfo1);
        userInfoM.setUserPhone("15800499265");
        userInfoM.setUserNote("1");
        userInfoM.setUserMajorityClass(classInfo.getMajorityClass());
        userInfoM.setUserName("BLF3");
        userInfoM.setUserPswd("123456");
        classInfo.setMonitorInfo(userInfoM);

        ClassInfo classInfo1 = new ClassInfo();
        classInfo1.setClassId(UUID.randomUUID().toString());
        classInfo1.setClassGrade("2014");
        classInfo1.setClassNum("07");
        classInfo1.setMajorityName("计科");
        classInfo1.setMajorityClass(classInfo1.getMajorityName()+classInfo1.getClassGrade()+classInfo1.getClassNum());

        UserInfo userInfoM1 = new UserInfo();
        userInfoM1.setUserNum("13110572042");
        userInfoM1.setUserRoleInfo(userRoleInfo1);
        userInfoM1.setUserPhone("18753377309");
        userInfoM1.setUserNote("1");
        userInfoM1.setUserMajorityClass(classInfo.getMajorityClass());
        userInfoM1.setUserName("Hypo");
        userInfoM1.setUserPswd("123456");
        classInfo1.setMonitorInfo(userInfoM1);

        UserInfo userInfo = new UserInfo();
        userInfo.setUserNum("13110572081");
        userInfo.setUserRoleInfo(userRoleInfo);
        userInfo.setUserPhone("15800499264");
        userInfo.setUserNote("1");
        userInfo.setUserMajorityClass("软件1303");
        userInfo.setUserName("BLF2");
        userInfo.setUserPswd("123456");
        List<UserInfo>list = new LinkedList<>();
        list.add(userInfo);
        list.add(userInfoM);
        list.add(userInfoM1);

        List<UserRoleInfo> userRoleInfoList = new LinkedList<>();
        userRoleInfoList.add(userRoleInfo);
        userRoleInfoList.add(userRoleInfo1);
        userRoleInfoList.add(userRoleInfo2);

        List<ClassInfo> classListAll = new LinkedList<>();
        classListAll.add(classInfo);
        classListAll.add(classInfo1);
        classListAll.add(classInfo);
        ModelAndView model = new ModelAndView();
        model.addObject(Consts.USER_LIST_ALL,list);
        model.addObject(Consts.CLASS_LIST_ALL,classListAll);
        model.addObject(Consts.USER_ROLE_LIST_ALL,userRoleInfoList);
        model.addObject(Consts.CONST_APOS,"'");
        return model;
    }
    @RequestMapping("/toDiv")
    public String toDiv(){
        return "divkeyvalue";
    }
    @RequestMapping("/toClassManager")
    public ModelAndView toClassManager(){
        UserRoleInfo userRoleInfo = new UserRoleInfo();
        userRoleInfo.setUserRoleId(UUID.randomUUID().toString());
        userRoleInfo.setUserRoleName("admin");

        UserRoleInfo userRoleInfo1 = new UserRoleInfo();
        userRoleInfo1.setUserRoleId(UUID.randomUUID().toString());
        userRoleInfo1.setUserRoleName("monitor");

        UserRoleInfo userRoleInfo2 = new UserRoleInfo();
        userRoleInfo2.setUserRoleId(UUID.randomUUID().toString());
        userRoleInfo2.setUserRoleName("primary");

        ClassInfo classInfo = new ClassInfo();
        classInfo.setClassId(UUID.randomUUID().toString());
        classInfo.setClassGrade("2013");
        classInfo.setClassNum("04");
        classInfo.setMajorityName("软件");
        classInfo.setMajorityClass(classInfo.getMajorityName()+classInfo.getClassGrade()+classInfo.getClassNum());

        UserInfo userInfoM = new UserInfo();
        userInfoM.setUserNum("13110572082");
        userInfoM.setUserRoleInfo(userRoleInfo1);
        userInfoM.setUserPhone("15800499265");
        userInfoM.setUserNote("1");
        userInfoM.setUserMajorityClass(classInfo.getMajorityClass());
        userInfoM.setUserName("BLF3");
        userInfoM.setUserPswd("123456");
        classInfo.setMonitorInfo(userInfoM);

        ClassInfo classInfo1 = new ClassInfo();
        classInfo1.setClassId(UUID.randomUUID().toString());
        classInfo1.setClassGrade("2014");
        classInfo1.setClassNum("07");
        classInfo1.setMajorityName("计科");
        classInfo1.setMajorityClass(classInfo1.getMajorityName()+classInfo1.getClassGrade()+classInfo1.getClassNum());

        UserInfo userInfoM1 = new UserInfo();
        userInfoM1.setUserNum("13110572042");
        userInfoM1.setUserRoleInfo(userRoleInfo1);
        userInfoM1.setUserPhone("18753377309");
        userInfoM1.setUserNote("1");
        userInfoM1.setUserMajorityClass(classInfo.getMajorityClass());
        userInfoM1.setUserName("Hypo");
        userInfoM1.setUserPswd("123456");
        classInfo1.setMonitorInfo(userInfoM1);

        UserInfo userInfo = new UserInfo();
        userInfo.setUserNum("13110572081");
        userInfo.setUserRoleInfo(userRoleInfo);
        userInfo.setUserPhone("15800499264");
        userInfo.setUserNote("1");
        userInfo.setUserMajorityClass("软件1303");
        userInfo.setUserName("BLF2");
        userInfo.setUserPswd("123456");
        List<UserInfo>list = new LinkedList<>();
        list.add(userInfo);
        list.add(userInfoM);
        list.add(userInfoM1);

        List<UserRoleInfo> userRoleInfoList = new LinkedList<>();
        userRoleInfoList.add(userRoleInfo);
        userRoleInfoList.add(userRoleInfo1);
        userRoleInfoList.add(userRoleInfo2);

        List<ClassInfo> classListAll = new LinkedList<>();
        classListAll.add(classInfo);
        classListAll.add(classInfo1);
        classListAll.add(classInfo);
        ModelAndView model = new ModelAndView();
        model.addObject(Consts.USER_LIST_ALL,list);
        model.addObject(Consts.CLASS_LIST_ALL,classListAll);
        model.addObject(Consts.USER_ROLE_LIST_ALL,userRoleInfoList);
        model.addObject(Consts.CONST_APOS,"'");
        model.setViewName("classinfomanager");
        return model;
    }
    @RequestMapping("/generatePage")
    public ModelAndView generatePage(ModelAndView model){
        TemplateForPage templateForPage = templateService.coverterForTemplateByTemplateId("9ecf17be-bf8a-4481-8325-4c2ccd24aaa3");
        model.addObject(Consts.TEMPLATE_FOR_PAGE,templateForPage);
        model.setViewName("generatesubmit");
        return model;
    }
}
