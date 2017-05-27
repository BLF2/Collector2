package net.blf2.controller;

import net.blf2.entity.InfoTemplate;
import net.blf2.entity.InfoTemplateForm;
import net.blf2.entity.UserInfo;
import net.blf2.service.impl.TemplateService;
import net.blf2.util.Consts;
import net.blf2.util.LoginUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Created by blf2 on 17-5-25.
 */
@Controller
@RequestMapping("/Submit")
public class SubmitController {
    @Resource
    private TemplateService templateService;
    @Resource
    private UserController userController;

    @RequestMapping("/submitDiv")
    public ModelAndView submitDiv(InfoTemplateForm infoTemplateForm,ModelAndView modelAndView){
        UserInfo loginUser = LoginUtil.getCurrentUser();
        if(loginUser == null || !(Consts.ADMIN_ROLE_NAME.equals(loginUser.getUserRoleInfo().getUserRoleName())
                || Consts.MONITOR_ROLE_NAME.equals(loginUser.getUserRoleInfo().getUserRoleName()))){
           userController.returnError("未登录或者未授权");
        }
        if(infoTemplateForm.getInfoTemplateList()!= null){
            List<InfoTemplate> infoTemplateList = new LinkedList<>();
            for(InfoTemplate it : infoTemplateForm.getInfoTemplateList()){
                if(it != null)
                    infoTemplateList.add(it);
            }
            infoTemplateForm.setInfoTemplateList(infoTemplateList);
        }
        infoTemplateForm.setCreateDateTime(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
        infoTemplateForm.setCreateNum(loginUser.getUserNum());
        infoTemplateForm.setTemplateId(UUID.randomUUID().toString());
        try {
            templateService.insertInfoTemplateForm(infoTemplateForm);
        }catch (Exception ex){
            ex.printStackTrace();
            return userController.returnError("数据库出错");
        }
        modelAndView.setViewName("");
        return modelAndView;
    }
    @RequestMapping("toDivKeyValue")
    public ModelAndView toDivKeyValue(ModelAndView model){
        if(LoginUtil.cuurentUserIsMonitor() || LoginUtil.currentUserIsAdmin()){
            model.setViewName("divkeyvalue");
            return model;
        }
        return userController.returnError("未登录或者未授权");
    }
}
