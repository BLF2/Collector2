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
                                 String majorityClass,@RequestParam("userPhone")String userPhone){
        ModelAndView model = new ModelAndView();
        UserInfo userInfo = new UserInfo();
        userInfo.setUserNum(userNum);
        userInfo.setUserPswd(userPswd);
        userInfo.setUserMajorityClass(majorityClass);
        userInfo.setUserNote("0");
        userInfo.setUserName(userName);
        userInfo.setUserPhone(userPhone);
        UserRoleInfo userRoleInfo = null;
        if(Consts.MONITOR_ROLE_NAME.equals(identity))
            userRoleInfo = userRoleService.queryUserRoleInfoByUserRoleId(Consts.PRIMARY_ROLE_ID);
        else if(Consts.PRIMARY_ROLE_NAME.equals(identity))
            userRoleInfo = userRoleService.queryUserRoleInfoByUserRoleId(Consts.MONITOR_ROLE_ID);
        else{
            model.setStatus(HttpStatus.BAD_REQUEST);
            return model;
        }
        userInfo.setUserRoleInfo(userRoleInfo);
        try {
            userService.insertUserInfo(userInfo);
        }catch (RuntimeException re){
            re.printStackTrace();
            model.addObject(Consts.ERROR_MESSAGE_FOR_PAGE,Consts.DATABASE_ERROR);
            model.setViewName("error");
            return  model;
        }

    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ModelAndView login(@RequestParam("loginId") String loginId,
                              @RequestParam("loginPswd")String loginPswd,
                              ModelAndView model){
        UserInfo userInfo = userService.queryUserInfoByUserNum(loginId);
        if(userInfo == null){
            model.addObject("loginError","用户名不存在");
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

    public ModelAndView returnError(String errorMessage){
        ModelAndView model = new ModelAndView();
        model.setViewName("error");
        errorMessage += "，3秒后回到<a href='/'>主页</a>.";
        model.addObject(Consts.ERROR_MESSAGE_FOR_PAGE,errorMessage);
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        response.setHeader("refresh","3;url=/");
        return model;
    }
    public ModelAndView returnIndexByRole(UserInfo userInfo){
        ModelAndView model = new ModelAndView();
        if(userInfo == null){
            model.addObject("loginError","未登录");
            model.setViewName("index");
            return model;
        }else if(userInfo.getUserRoleInfo() == null){
            model.setViewName("index");
        }else if(userInfo.getUserRoleInfo().getUserRoleId() == null){
            model.setViewName("index");
        }

        String userRoleName = userInfo.getUserRoleInfo().getUserRoleName();
        if(userRoleName.equals(Consts.ADMIN_ROLE_NAME)){
            model.setViewName("adminmanager");
        }else if(userRoleName.equals(Consts.MONITOR_ROLE_NAME)){
            model.setViewName("monitormanager");
        }else{
            model.setViewName("primarymanager");
        }
        return model;
    }
}
