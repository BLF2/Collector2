package net.blf2.controller;

import net.blf2.entity.MessageInfo;
import net.blf2.entity.UserInfo;
import net.blf2.service.IMessageService;
import net.blf2.util.Consts;
import net.blf2.util.LoginUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by blf2 on 17-6-6.
 */
@Controller
@RequestMapping("/Message")
public class MessageController {
    @Resource
    private IMessageService messageService;
    @Resource
    private UserController userController;
    @RequestMapping("/toMyMessages")
    public ModelAndView toMyMessage(ModelAndView model){
        UserInfo loginUser = LoginUtil.getCurrentUser();
        if(loginUser == null){
            userController.returnError("未登录");
        }
        if(Consts.PRIMARY_ROLE_NAME.equals(loginUser.getUserRoleInfo().getUserRoleName())){
            List<MessageInfo>messageInfoList = messageService.queryMessageInfosByRecieverId(loginUser.getUserNum());
            model.addObject("messageInfoList",messageInfoList);
        }else if(Consts.MONITOR_ROLE_NAME.equals(loginUser.getUserRoleInfo().getUserRoleName())){
            List<MessageInfo>messageInfoList = messageService.queryMessageInfosByRecieverId(loginUser.getUserNum());
            List<MessageInfo>messageInfoList1 = messageService.queryMessageInfosBySenderId(loginUser.getUserNum());
            messageInfoList.addAll(messageInfoList1);
            model.addObject("messageInfoList",messageInfoList);
        }else if(Consts.ADMIN_ROLE_NAME.equals(loginUser.getUserRoleInfo().getUserRoleName())){
            List<MessageInfo>messageInfoList = messageService.queryMessageInfoAll();
            model.addObject("messageInfoList",messageInfoList);
        }
        model.setViewName("mymessagers");
        return model;
    }
    @RequestMapping("/deleteMessage")
    public ModelAndView deleteMessage(@RequestParam("messageId")String messageId,
                                      ModelAndView model) {
        UserInfo loginUser = LoginUtil.getCurrentUser();
        if (loginUser == null)
            return userController.returnError("未登录或者未授权");
        try {
                if (Consts.PRIMARY_ROLE_NAME.equals(loginUser.getUserRoleInfo().getUserRoleName())) {
                    MessageInfo messageInfo = messageService.queryMessageInfoByMessageId(messageId);
                    if (messageInfo != null && messageInfo.getRecieverId().equals(loginUser.getUserNum())) {
                        messageService.deleteMessageInfoByMessageId(messageId);
                        model.addObject(Consts.OPRERATOR_MESSAGE, "删除成功");
                    }
                } else if (Consts.MONITOR_ROLE_NAME.equals(loginUser.getUserRoleInfo().getUserRoleName())) {
                    MessageInfo messageInfo = messageService.queryMessageInfoByMessageId(messageId);
                    if (messageInfo != null && (messageInfo.getRecieverId().equals(loginUser.getUserNum())
                            || messageInfo.getSenderId().equals(loginUser.getUserNum()))) {
                        messageService.deleteMessageInfoByMessageId(messageId);
                        model.addObject(Consts.OPRERATOR_MESSAGE, "删除成功");
                    }
                } else if (Consts.ADMIN_ROLE_NAME.equals(loginUser.getUserRoleInfo().getUserRoleName())) {
                    MessageInfo messageInfo = messageService.queryMessageInfoByMessageId(messageId);
                    if (messageInfo != null) {
                        messageService.deleteMessageInfoByMessageId(messageId);
                        model.addObject(Consts.OPRERATOR_MESSAGE, "删除成功");
                    }
                }
                model.setViewName("mymessagers");
        }catch (Exception ex){
            model = userController.returnError("数据库操作出错");
        }finally {
            return model;
        }
    }
}
