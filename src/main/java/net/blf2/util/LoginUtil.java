package net.blf2.util;

import net.blf2.entity.UserInfo;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpSession;

/**
 * Created by blf2 on 17-5-16.
 * 登录信息工具类
 */
public class LoginUtil {
    public static UserInfo getCurrentUser(){
        HttpSession httpSession = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        UserInfo loginInfo = (UserInfo) httpSession.getAttribute(Consts.CURRENT_USER);
        return loginInfo;
    }
    public static void logOut(){
        HttpSession httpSession = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        httpSession.removeAttribute(Consts.CURRENT_USER);
    }
    public static void logIn(UserInfo userInfo){
        HttpSession httpSession = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        httpSession.setAttribute(Consts.CURRENT_USER,userInfo);
    }
}
