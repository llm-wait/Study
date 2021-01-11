package com.startest.wm.service;

import com.startest.wm.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-03-20 15:51
 * @描述 系统登录服务
 **/
public interface SysLoginService {
    int addSysLogin(sys_login sysLogin);

    int updateSysLogin(sys_login sysLogin);

    int deleteSysLoginByID(@Param("id")String id);

    sys_login querySysLoginByID(@Param("id") String id);

    List<sys_login> querySysLoginByName(@Param("login_name")String login_name);

    sys_login querySysLogin(@Param("login_name")String login_name,@Param("login_pwd") String login_pwd);

    List<sys_login> querySysLoginAll();
}
