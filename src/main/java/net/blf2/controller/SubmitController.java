package net.blf2.controller;

import net.blf2.entity.*;
import net.blf2.service.impl.TemplateService;
import net.blf2.util.Consts;
import net.blf2.util.LoginUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @RequestMapping(value = "/submitDiv")
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
        TemplateForPage templateForPage = templateService.coverterForTemplateByTemplateId(infoTemplateForm.getTemplateId());
        modelAndView.addObject(Consts.TEMPLATE_FOR_PAGE,templateForPage);
        modelAndView.setViewName("generatesubmit");
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
    @RequestMapping("/publish")
    public ModelAndView publish(ModelAndView model, @RequestParam("templateId")String templateId,
                                HttpServletRequest request){
        if(LoginUtil.getCurrentUser() == null || !LoginUtil.cuurentUserIsMonitor())
            return userController.returnError("未登录或者未授权");
        String url = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()
                +"/Submit/submit?templateId="+templateId;
        model.addObject(Consts.SUBMIT_URL,url);
        model.setViewName("monitormanager");
        return model;
    }
    @RequestMapping("/submit")
    public ModelAndView submit(@RequestParam("templateId")String templateId,ModelAndView model){
        UserInfo loginUser = LoginUtil.getCurrentUser();
        if(loginUser == null){
            return userController.returnError("未登录");
        }
        TemplateForPage templateForPage = templateService.coverterForTemplateByTemplateId(templateId);
        if(templateForPage == null){
            model= userController.returnIndexByRole(loginUser);
            model.addObject(Consts.OPRERATOR_MESSAGE,"未找到页面");
            return model;
        }
        model.setViewName("submit");
        model.addObject(Consts.TEMPLATE_FOR_PAGE,templateForPage);
        return model;
    }
    @RequestMapping("/submitData")
    public ModelAndView submitData(@RequestParam("collectedData")String collectedData,
                                   @RequestParam("templateId")String templateId,
                                   @RequestParam("introductionString")String introductionString){
        UserInfo loginUser = LoginUtil.getCurrentUser();
        if(loginUser == null)
            return userController.returnError("未登录");
        ModelAndView model ;
        FormResult formResult = new FormResult();
        formResult.setSubmiterId(loginUser.getUserNum());
        formResult.setSubmitDateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        formResult.setIntroductionString(introductionString);
        String decodeCollectedData = "";
        try {
            decodeCollectedData = java.net.URLDecoder.decode(collectedData,"UTF-8");
        }catch (Exception ex){
            ex.printStackTrace();
            return userController.returnError("无法解码");
        }
        formResult.setFormResultMap(decodeCollectedData);
        formResult.setTemplateId(templateId);
        templateService.insertFormResult(formResult);
        model = userController.returnIndexByRole(loginUser);
        model.addObject(Consts.OPRERATOR_MESSAGE,"提交成功");
        return model;
    }
    @RequestMapping("toMonitorsDiv")
    public ModelAndView toMonitorsDiv(ModelAndView model){
        UserInfo loginUser = LoginUtil.getCurrentUser();
        if(loginUser == null || Consts.PRIMARY_ROLE_NAME.equals(loginUser.getUserRoleInfo().getUserRoleName())){
            return userController.returnError("未登录或者未授权");
        }
        Map<String,Object>queryMap = new HashMap<>();
        queryMap.put("createNum",loginUser.getUserNum());
        List<InfoTemplateForm> infoTemplateFormList = templateService.queryInfoTemplateFormAllByFilter(queryMap);
        model.addObject("infoTemplateFormList",infoTemplateFormList);
        model.setViewName("monitorsdiv");
        return model;
    }
    @RequestMapping("toMyFormResults")
    public ModelAndView toFormResults(@RequestParam("templateId")String templateId,ModelAndView model){
        UserInfo loginUser = LoginUtil.getCurrentUser();
        if(loginUser == null || Consts.PRIMARY_ROLE_NAME.equals(loginUser.getUserRoleInfo().getUserRoleName()))
            return userController.returnError("未登录或者未授权");
        Map<String,Object>queryMap = new HashMap<>();
        queryMap.put(Consts.TEMPLATE_ID,templateId);
        List<FormResult> formResultList = templateService.queryResultByFilter(queryMap);
        model.addObject("formResultList",formResultList);
        model.setViewName("formresults");
        return model;
    }
}
