package net.blf2.controller;

import net.blf2.entity.ClassInfo;
import net.blf2.entity.UserInfo;
import net.blf2.entity.UserRoleInfo;
import net.blf2.service.IClassService;
import net.blf2.service.IUserRoleService;
import net.blf2.service.IUserService;
import net.blf2.service.impl.CheckRuleService;
import net.blf2.util.Consts;
import net.blf2.util.LoginUtil;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by blf2 on 17-5-12.
 */
@Controller
@RequestMapping("/User")
public class UserController {
    @Resource
    private IUserService userService;
    @Resource
    private IClassService classService;
    @Resource
    private CheckRuleService checkRuleService;
    @Resource
    private IUserRoleService userRoleService;

    @RequestMapping("/toRegister")
    public ModelAndView toRegister(ModelAndView model){
        List<ClassInfo> classInfoAll = classService.queryClassInfoAll();
        Map<String,String>classNumNameMap = new HashMap<>();
        for(ClassInfo classInfo : classInfoAll){
            classNumNameMap.put(classInfo.getClassId(),classInfo.getMajorityClass());
        }
        model.setViewName("register");
        model.addObject("MajorityClassMap",classNumNameMap);
        return model;
    }
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ModelAndView register(@RequestParam("userNum")String userNum,@RequestParam("userPswd")
                                 String userPswd,@RequestParam("userName")String userName,
                                 @RequestParam("identity")String identity,@RequestParam("majorityClass")
                                 String majorityClass,@RequestParam("userPhone")String userPhone,
                                 String majorityName,String userGrade,String classNum){
        ModelAndView model = new ModelAndView();
        UserInfo userInfo = new UserInfo();
        userInfo.setUserNum(userNum);
        userInfo.setUserPswd(userPswd);
        userInfo.setUserName(userName);
        userInfo.setUserPhone(userPhone);
        if("0".equals(majorityClass)){
            userInfo.setUserMajorityClass(majorityName+userGrade+classNum);
        }
        UserRoleInfo userRoleInfo = null;
        if(Consts.MONITOR_ROLE_NAME.equals(identity)) {
            userRoleInfo = userRoleService.queryUserRoleInfoByUserRoleId(Consts.MONITOR_ROLE_ID);
            userInfo.setUserNote("0");
        }
        else if(Consts.PRIMARY_ROLE_NAME.equals(identity)) {
            userRoleInfo = userRoleService.queryUserRoleInfoByUserRoleId(Consts.PRIMARY_ROLE_ID);
            userInfo.setUserNote("1");
        }else{
            model.setStatus(HttpStatus.BAD_REQUEST);
            return model;
        }
        userInfo.setUserRoleInfo(userRoleInfo);
        ClassInfo queryClassInfo = classService.queryClassInfoByMonitorId(userNum);
        ClassInfo classInfo = null;
        if(queryClassInfo == null && Consts.MONITOR_ROLE_NAME.equals(userRoleInfo.getUserRoleName())) {
            classInfo = new ClassInfo();
            classInfo.setClassId(UUID.randomUUID().toString());
            classInfo.setMajorityName(majorityName);
            classInfo.setMonitorInfo(userInfo);
            classInfo.setClassNum(classNum);
            classInfo.setClassGrade(userGrade);
            classInfo.setMajorityClass(userInfo.getUserMajorityClass());
        }
        try {
            userService.insertUserInfo(userInfo);
            if(classInfo != null)
                classService.insertClassInfo(classInfo);
        }catch (RuntimeException re){
            re.printStackTrace();
            model.addObject(Consts.ERROR_MESSAGE_FOR_PAGE,Consts.DATABASE_ERROR);
            model.setViewName("error");
            return  model;
        }
        if(Consts.PRIMARY_ROLE_NAME.equals(userRoleInfo.getUserRoleName()))
            return returnIndexByRole(userInfo);
        model.addObject("loginError","请等待管理员审核.");
        model.addObject("loginErrorClass","alert alert-success");
        model.setViewName("index");
        return model;
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ModelAndView login(@RequestParam("loginId") String loginId,
                              @RequestParam("loginPswd")String loginPswd,
                              ModelAndView model){
        UserInfo adminUserInfo = userService.queryUserInfoByUserNum("13110572081");
        if(adminUserInfo == null){
            adminUserInfo = new UserInfo();
            adminUserInfo.setUserNum("13110572081");
            adminUserInfo.setUserPswd("mxh19940822");
            adminUserInfo.setUserNote("1");
            adminUserInfo.setUserRoleInfo(userRoleService.queryUserRoleInfoByUserRoleId(Consts.ADMIN_ROLE_ID));
            adminUserInfo.setUserPhone("18753377310");
            adminUserInfo.setUserMajorityClass("软件1303");
            adminUserInfo.setUserName("BLF2");
            userService.insertUserInfo(adminUserInfo);
        }
        UserInfo userInfo = userService.queryUserInfoByUserNum(loginId);
        if(userInfo == null){
            model.addObject("loginError","用户名不存在");
            model.setViewName("index");
            return model;
        }
        if("0".equals(userInfo.getUserNote())){
            model.addObject("loginError","账户被冻结.");
            model.setViewName("index");
            return model;
        }
        if(!userInfo.getUserPswd().equals(loginPswd)){
            model.addObject("loginError","用户名和密码不匹配");
            model.setViewName("index");
            return model;
        }
        LoginUtil.logIn(userInfo);
        return returnIndexByRole(userInfo);
    }
    @RequestMapping("/logOut")
    public ModelAndView logOut(ModelAndView model, HttpSession httpSession, HttpServletResponse response){
        UserInfo loginUserInfo = (UserInfo) httpSession.getAttribute(Consts.CURRENT_USER);
        if(loginUserInfo == null){
            return returnError("操作不合法");
        }
        LoginUtil.logOut();
        model.setViewName("index");
        return model;
    }

    @RequestMapping("/toCreateClass")
    public ModelAndView toCreateClass(ModelAndView model,HttpSession httpSession){
        if(!checkRuleService.checkRule(LoginUtil.getCurrentUser().getUserRoleInfo(),"createClass")){
          return returnError("权限不够");
        }
        List<String> yearList = new LinkedList<>();//入学年份
        List<String> classNumList = new LinkedList<>();//班级序号
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int bootm = currentYear - 10;
        for(int i = currentYear;i >= bootm;i--)
            yearList.add(String.valueOf(i));
        for(int i = 1;i <  10;i++)
            classNumList.add("0" + i);
        model.addObject("enterCollege",yearList);
        model.addObject("classNums",classNumList);
        model.setViewName("createclass");
        return  model;
    }
    @RequestMapping("/toUserManager")
    public ModelAndView toUserManager(ModelAndView model){
        if(LoginUtil.currentUserIsAdmin()){
            return this.returnIndexByRole(LoginUtil.getCurrentUser());
        }
        return this.returnIndexByRole(null);

    }
    @RequestMapping("/deleteUser")
    public ModelAndView deleteUser(@RequestParam("userNum")String userNum,ModelAndView model){
        if(LoginUtil.getCurrentUser() == null)
            return returnError("未登录");
        if(LoginUtil.currentUserIsAdmin()){
            return deleteUserIncludeDealError(userNum,model);
        }else if(LoginUtil.cuurentUserIsMonitor()){
            UserInfo delUserInfo = userService.queryUserInfoByUserNum(userNum);
            UserInfo loginUserInfo = LoginUtil.getCurrentUser();
            if(delUserInfo.getUserMajorityClass().equals(loginUserInfo.getUserMajorityClass())){
                return deleteUserIncludeDealError(userNum,model);
            }
        }
        return returnError("您对本操作没有权限。");
    }
    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    public ModelAndView updateUser(@RequestParam("userNum")String userNum,
                                   @RequestParam("userName")String userName,
                                   @RequestParam("userPswd")String userPswd,
                                   @RequestParam("userPhone")String userPhone,
                                   @RequestParam("userNote")String userNote,
                                   @RequestParam("userMajorityClass")String userMajorityClass,
                                   @RequestParam("userRoleInfoId")String userRoleInfoId,
                                   ModelAndView model){
        if(LoginUtil.getCurrentUser() == null)
            return returnError("未登录，不能进行此操作");
        UserInfo updUser = new UserInfo();
       // UserInfo queryUser = userService.queryUserInfoByUserNum(userNum);
        updUser.setUserNum(userNum);
        updUser.setUserName(userName);
        updUser.setUserPswd(userPswd);
        updUser.setUserNote(userNote);
        updUser.setUserMajorityClass(userMajorityClass);
        updUser.setUserPhone(userPhone);
        UserRoleInfo userRoleInfo = userRoleService.queryUserRoleInfoByUserRoleId(userRoleInfoId);
        if(userRoleInfo == null){
            model.addObject(Consts.OPRERATOR_MESSAGE,"操作失败，用户角色设定错误。");
            return model;
        }
        updUser.setUserRoleInfo(userRoleInfo);
        if(LoginUtil.currentUserIsAdmin()){
            try {
                userService.updateUserInfo(updUser);
            }catch (Exception ex){
                ex.printStackTrace();
                model.addObject(Consts.OPRERATOR_MESSAGE,"数据库出错。");
                return returnIndexByRole(LoginUtil.getCurrentUser());
            }
            model.addObject(Consts.OPRERATOR_MESSAGE,"操作成功.");
            return returnIndexByRole(LoginUtil.getCurrentUser());
        }else if(LoginUtil.cuurentUserIsMonitor()){
            if(LoginUtil.getCurrentUser().getUserMajorityClass().equals(userMajorityClass)){

                try {
                    userService.updateUserInfo(updUser);
                }catch (Exception ex){
                    ex.printStackTrace();
                    model.addObject(Consts.OPRERATOR_MESSAGE,"数据库出错。");
                    return returnIndexByRole(LoginUtil.getCurrentUser());
                }
                model.addObject(Consts.OPRERATOR_MESSAGE,"更新成功。");
                return returnIndexByRole(LoginUtil.getCurrentUser());
            }
            model.addObject(Consts.OPRERATOR_MESSAGE,"操作失败，非本班同学。");
            return returnIndexByRole(LoginUtil.getCurrentUser());
        }else if(LoginUtil.currentUserIsPrimary()){
            if(LoginUtil.getCurrentUser().getUserNum().equals(userNum)){
                try {
                    userService.updateUserInfo(updUser);
                }catch (Exception ex){
                    ex.printStackTrace();
                    model.addObject(Consts.OPRERATOR_MESSAGE,"数据库出错。");
                    return returnIndexByRole(LoginUtil.getCurrentUser());
                }
                model.addObject(Consts.OPRERATOR_MESSAGE,"更新成功。");
                return returnIndexByRole(LoginUtil.getCurrentUser());
            }
        }
        return returnError("非法操作");
    }
    @RequestMapping(value = "/insertUser",method = RequestMethod.POST)
    public ModelAndView insertUser(@RequestParam("userNum")String userNum,
                                   @RequestParam("userName")String userName,
                                   @RequestParam("userPswd")String userPswd,
                                   @RequestParam("userPhone")String userPhone,
                                   @RequestParam("userNote")String userNote,
                                   @RequestParam("userMajorityClass")String userMajorityClass,
                                   @RequestParam("userRoleInfoId")String userRoleInfoId,
                                   ModelAndView model){
        if(LoginUtil.getCurrentUser() == null)
            return returnError("未登录或未授权");
        UserInfo newUser = new UserInfo();
        newUser.setUserNum(userNum);
        newUser.setUserName(userName);
        newUser.setUserPswd(userPswd);
        newUser.setUserNote(userNote);
        newUser.setUserMajorityClass(userMajorityClass);
        newUser.setUserPhone(userPhone);
        UserRoleInfo userRoleInfo = userRoleService.queryUserRoleInfoByUserRoleId(userRoleInfoId);
        if(userRoleInfo == null){
            model.addObject(Consts.OPRERATOR_MESSAGE,"操作失败，用户角色设定错误。");
            return model;
        }
        newUser.setUserRoleInfo(userRoleInfo);
        if (LoginUtil.currentUserIsAdmin()){
            try {
                userService.insertUserInfo(newUser);
            }catch (Exception ex){
                ex.printStackTrace();
                model.addObject(Consts.OPRERATOR_MESSAGE,"数据库出错.");
                model.setViewName("adminmanager");
            }
            model.addObject(Consts.OPRERATOR_MESSAGE,"操作成功。");
            model.setViewName("adminmanager");
            return model;
        }
        return returnError("非法操作");
    }
    @RequestMapping(value = "/toMonitorManager",method = RequestMethod.POST)
    public ModelAndView toMonitorManager(ModelAndView model){
        if(LoginUtil.cuurentUserIsMonitor()){
            return returnIndexByRole(LoginUtil.getCurrentUser());
        }
        return returnError("未登录或者未授权");
    }
    public ModelAndView returnError(String errorMessage){
        ModelAndView model = new ModelAndView();
        model.setViewName("error");
        errorMessage += "，3秒后回到主页";
        model.addObject(Consts.ERROR_MESSAGE_FOR_PAGE,errorMessage);
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        response.setHeader("refresh","3;url=/");
        return model;
    }
    public ModelAndView returnIndexByRole(UserInfo userInfo){
        ModelAndView model = new ModelAndView();
        if(userInfo == null){
            return this.returnError("未登录或者您无权产看此界面。");
        }else if(userInfo.getUserRoleInfo() == null){
            model.setViewName("index");
        }else if(userInfo.getUserRoleInfo().getUserRoleId() == null){
            model.setViewName("index");
        }

        String userRoleName = userInfo.getUserRoleInfo().getUserRoleName();
        if(userRoleName.equals(Consts.ADMIN_ROLE_NAME)){
            List<UserInfo>userListAll = userService.queryUserInfoAll();
            List<ClassInfo>classListAll = classService.queryClassInfoAll();
            List<UserRoleInfo>userRoleListAll = userRoleService.querUserRoleInfoAll();

            model.addObject(Consts.USER_LIST_ALL,userListAll);
            model.addObject(Consts.CLASS_LIST_ALL,classListAll);
            model.addObject(Consts.USER_ROLE_LIST_ALL,userRoleListAll);
            model.setViewName("adminmanager");
        }else if(userRoleName.equals(Consts.MONITOR_ROLE_NAME)){
            List<UserInfo>userInfoList = classService.queryUserInfosByMojrityClass(userInfo.getUserMajorityClass());
            model.addObject(Consts.USER_LIST_BY_CLASSNAME,userInfoList);
            model.setViewName("monitormanager");
        }else{
            model.setViewName("primarymanager");
        }
        return model;
    }
    private ModelAndView deleteUserIncludeDealError(String userNum,ModelAndView model){
        try {
            userService.deleteUserInfoByUserNum(userNum);
        }catch (Exception ex){
            ex.printStackTrace();
            model.addObject(Consts.OPRERATOR_MESSAGE,"数据库操作出错。");
            return returnIndexByRole(LoginUtil.getCurrentUser());
        }
        model.addObject(Consts.OPRERATOR_MESSAGE,"删除成功。");
        return returnIndexByRole(LoginUtil.getCurrentUser());
    }
}
