package net.blf2.controller;

import net.blf2.entity.ClassInfo;
import net.blf2.entity.UserInfo;
import net.blf2.service.IClassService;
import net.blf2.service.IUserService;
import net.blf2.util.Consts;
import net.blf2.util.LoginUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ModelAndView toClassManager(ModelAndView model){
        if(LoginUtil.getCurrentUser() == null)
            return userController.returnError("未登录或者未授权");
        if(LoginUtil.currentUserIsAdmin() || LoginUtil.cuurentUserIsMonitor()){
            List<ClassInfo>classListAll = classService.queryClassInfoAll();
            List<UserInfo>userListAll = userService.queryUserInfoAll();
            model.addObject(Consts.CLASS_LIST_ALL,classListAll);
            model.addObject(Consts.USER_LIST_ALL,userListAll);
            model.setViewName("classinfomanager");
        }
        return userController.returnError("未授权");
    }
}
