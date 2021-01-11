package com.startest.wm.utils;

import com.startest.wm.config.exception.LoginException;
import com.startest.wm.pojo.sys_login;
import javax.servlet.http.HttpServletRequest;


/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-05-14-14:58
 * @描述 全局用户信息
 */
public class GlobalLoginInfoUtil {

    /**
     * @Description: 获取当前登录用户信息
     * @Param: [request]
     * @return: com.startest.wm.pojo.sys_login
     **/
    public static sys_login getLoginUserInfo(HttpServletRequest request) throws LoginException {
        sys_login sysLogin = (sys_login) (request.getSession().getAttribute("syslogin"));
        if (null == sysLogin) {
            throw new LoginException("系统未登录或登录账户过期！");
        }
        return sysLogin;
    }

    /**
     * @Description: 判断是否是管理员登录
     * @Param: [request]
     * @return: boolean
     **/
    public static boolean isAdminLogin(HttpServletRequest request) throws LoginException {
        sys_login sysLogin = (sys_login) (request.getSession().getAttribute("syslogin"));
        if (null == sysLogin) {
            throw new LoginException("系统未登录或登录账户过期！");
        } else {
            return sysLogin.getRole_name().contains("管理员");
        }
    }
}
