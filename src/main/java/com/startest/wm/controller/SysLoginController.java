package com.startest.wm.controller;

import com.startest.wm.pojo.sys_login;
import com.startest.wm.service.SysLoginService;
import com.startest.wm.utils.UUIDGeneratorUtil;
import com.startest.wm.utils.customresponse.RestResponse;
import com.startest.wm.utils.customresponse.RestResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-03-20 15:55
 * @描述 系统登录控制器
 **/
@Api(tags = "系统登录模块相关API")
@RestController
@RequestMapping("/sys/login/")
public class SysLoginController {

    private static final Logger log = LoggerFactory.getLogger(SysLoginController.class);

    @Autowired
    SysLoginService sysLoginService;

    @ApiOperation("系统登录")
    @PostMapping("login")
    public RestResponse<sys_login> login(
            HttpServletRequest request,
            @ApiParam(value = "登录名", required = true) @RequestParam(name = "login_name") String login_name,
            @ApiParam(value = "登录密码", required = true) @RequestParam(name = "login_pwd") String login_pwd
    ) {
        RestResponse<sys_login> response = null;
        try {
            if (login_name == null || "".equals(login_name) || login_pwd == null || "".equals(login_pwd)) {
                response = RestResponseUtil.note("登录名或密码不能为空！");
            } else {
                sys_login sysLogin = sysLoginService.querySysLogin(login_name, login_pwd);
                if (sysLogin != null) {
                    request.getSession().setAttribute("syslogin", sysLogin);
                    //登录成功返回登录用户角色
                    sysLogin.setLogin_pwd("");//过滤敏感信息
                    response = RestResponseUtil.success("登录成功!", sysLogin);
                } else {
                    response = RestResponseUtil.note("用户名或密码不正确！");
                }
            }
        } catch (Exception e) {
            log.error("登录异常：" + e);
            response = RestResponseUtil.note("系统异常，请联系管理员！");
        }
        return response;
    }



    @ApiOperation("系统登出")
    @PostMapping("logout")
    public RestResponse<String> logout(HttpServletRequest request) {
        RestResponse<String> response = null;
        try {
            request.getSession().setAttribute("syslogin", null);
            request.getSession().invalidate();//设置session过期
            response = RestResponseUtil.success("登出成功！");
        } catch (Exception e) {
            log.error("登录异常：" + e);
            response = RestResponseUtil.note("系统异常，请联系管理员！");
        }
        return response;
    }



    @ApiOperation("删除登录账户")
    @PostMapping(value = "deleteSysLogin")
    public RestResponse<String> deleteSysLogin(
            @ApiParam(value = "登录ID", required = true) @RequestParam(name = "id") String id) {
        RestResponse<String> response = null;
        try {
            sys_login sysLogin = sysLoginService.querySysLoginByID(id);
            if (sysLogin != null) {
                int res = sysLoginService.deleteSysLoginByID(id);
                if (res > 0) {
                    response = RestResponseUtil.note("删除成功！");
                } else {
                    response = RestResponseUtil.note("删除失败！");
                }
            } else {
                response = RestResponseUtil.note("此账户不存在！");
            }
        } catch (Exception e) {
            log.error("删除登录账户异常：" + e);
            response = RestResponseUtil.note("系统异常，请联系管理员！");
        }
        return response;
    }



    @ApiOperation("修改登录账户")
    @PostMapping(value = "updateSysLogin")
    public RestResponse<String> updateSysLogin(
            HttpServletRequest request,
            @ApiParam(value = "登录ID", required = true) @RequestParam(name = "id") String id,
            @ApiParam(value = "登录名", required = true) @RequestParam(name = "login_name") String login_name,
            @ApiParam(value = "登录密码", required = true) @RequestParam(name = "login_pwd") String login_pwd) {
        RestResponse<String> response = null;
        try {
            if (login_name == null || "".equals(login_name) || login_pwd == null || "".equals(login_pwd)) {
                response = RestResponseUtil.note("登录名或密码不能为空！");
            } else {
                List<sys_login> sysLoginList = sysLoginService.querySysLoginByName(login_name);
                if (sysLoginList != null && sysLoginList.size() > 0) {
                    for (sys_login login : sysLoginList) {
                        if (!login.getId().equals(id)) {
                            response = RestResponseUtil.note("登录名已存在！");
                            return response;
                        }
                    }
                } else {
                    sys_login sysLogin = new sys_login();
                    sysLogin.setId(id);
                    sysLogin.setLogin_name(login_name);
                    sysLogin.setLogin_pwd(login_pwd);
                    int res = sysLoginService.updateSysLogin(sysLogin);
                    if (res > 0) {
                        request.getSession().setAttribute("syslogin", sysLogin);
                        response = RestResponseUtil.note("修改成功！");
                    } else {
                        response = RestResponseUtil.note("修改失败！");
                    }
                }
            }
        } catch (Exception e) {
            log.error("修改登录账户异常：" + e);
            response = RestResponseUtil.note("系统异常，请联系管理员！");
        }
        return response;
    }



    @ApiOperation("注册登录账户")
    @PostMapping(value = "registerSysLogin")
    public RestResponse<String> registerSysLogin(
            @ApiParam(value = "登录名", required = true) @RequestParam(name = "login_name") String login_name,
            @ApiParam(value = "登录密码", required = true) @RequestParam(name = "login_pwd") String login_pwd,
            @ApiParam(value = "部门ID") @RequestParam(name = "dept_id", required = false) String dept_id,
            @ApiParam(value = "部门名称") @RequestParam(name = "dept_name", required = false) String dept_name,
            @ApiParam(value = "角色名称") @RequestParam(name = "role_name") String role_name) {
        RestResponse<String> response = null;
        try {
            if (login_name == null || "".equals(login_name) || login_pwd == null || "".equals(login_pwd)) {
                response = RestResponseUtil.note("登录名或密码不能为空！");
            } else {
                List<sys_login> sysLoginList = sysLoginService.querySysLoginByName(login_name);
                if (sysLoginList != null && sysLoginList.size() > 0) {
                    response = RestResponseUtil.note("登录名已存在！");
                } else {
                    sys_login sysLogin = new sys_login();
                    sysLogin.setId(UUIDGeneratorUtil.getUUIDWithoutLineAndToLower());
                    sysLogin.setLogin_name(login_name);
                    sysLogin.setLogin_pwd(login_pwd);
                    sysLogin.setRole_name(role_name);
                    if (!"管理员".equals(role_name) || !"超级管理员".equals(role_name)) {
                        sysLogin.setDept_id(dept_id);
                        sysLogin.setDept_name(dept_name);
                    }
                    int res = sysLoginService.addSysLogin(sysLogin);
                    if (res > 0) {
                        response = RestResponseUtil.note("注册成功！");
                    } else {
                        response = RestResponseUtil.note("注册失败！");
                    }
                }
            }
        } catch (Exception e) {
            log.error("注册账户异常：" + e);
            response = RestResponseUtil.note("系统异常，请联系管理员！");
        }
        return response;
    }



    @ApiOperation("获取登录信息")
    @GetMapping(value = "getLoginInfo")
    public RestResponse<sys_login> getLoginInfo(HttpServletRequest request) {
        RestResponse<sys_login> response = null;
        try {
            sys_login sysLogin = (sys_login) request.getSession().getAttribute("syslogin");
            response = RestResponseUtil.success("", sysLogin);
        } catch (Exception e) {
            log.error("系统异常：" + e);
            response = RestResponseUtil.note("系统异常，请联系管理员！");
        }
        return response;
    }
}
