package net.blf2.controller;

import net.blf2.entity.ClassInfo;
import net.blf2.entity.UserInfo;
import net.blf2.service.IClassService;
import net.blf2.service.IUserService;
import net.blf2.util.Consts;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @RequestMapping("/logOut")
    public ModelAndView logOut(ModelAndView model, HttpSession httpSession, HttpServletResponse response){
        UserInfo loginUserInfo = (UserInfo) httpSession.getAttribute(Consts.CURRENT_USER);
        if(loginUserInfo == null){
            response.setHeader("refresh","3;url=/");
            model.setViewName("error");
            model.addObject(Consts.ERROR_MESSAGE_FOR_PAGE,"操作不合法,3秒后跳转到主页...");
            return model;
        }
        httpSession.removeAttribute(Consts.CURRENT_USER);
        model.setViewName("index");
        return model;
    }
}
