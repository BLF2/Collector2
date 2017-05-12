package net.blf2.controller;

import net.blf2.entity.ClassInfo;
import net.blf2.service.IClassService;
import net.blf2.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
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
        classNumNameMap.put("1","软件1302");
        classNumNameMap.put("2","软件1303");
        model.setViewName("register");
        model.addObject("MajorityClassMap",classNumNameMap);
        return model;
    }
}
