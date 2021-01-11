package com.startest.wm.controller;

import com.startest.wm.pojo.sys_user;
import com.startest.wm.service.SysDeptService;
import com.startest.wm.utils.MyDateUtils;
import com.startest.wm.utils.customresponse.ExceptionHandleUtil;
import com.startest.wm.utils.customresponse.RestResponse;
import com.startest.wm.utils.customresponse.RestResponseUtil;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-06-18 16:13
 * @描述
 **/
@Api("测试")
@RestController
@RequestMapping("/sys/test/")
public class TestController {


    private static final Logger logger = LoggerFactory.getLogger(Exception.class);

    @Autowired
    SysDeptService sysDeptService;

    @PostMapping("sessionexpire")
    public String testSessionExpire() {
        return MyDateUtils.getCurrentDate();
    }

    @GetMapping("session")
    public RestResponse<Object> testsession(HttpServletRequest request) {
        RestResponse<Object> response = null;
        try {
            //如果过期返回，指定状态码
            if (request.getSession().getAttribute("syslogin") == null) {
                response = RestResponseUtil.note("9999", null);
            } else {
                //返回登录用户信息
                response = RestResponseUtil.success("", request.getSession().getAttribute("syslogin"));
            }
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }

    @GetMapping("testGetDeptUser")
    public void testGetDeptUser() {
        List<sys_user> sysUserList = sysDeptService.getDeptUserList("815f9687-c01c-4fd1-b6bd-ac4e45bbf335",true);
        System.out.println(sysUserList);
    }
}
