package com.startest.wm.dao;

import com.startest.wm.pojo.sys_login;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-03-20 15:56
 * @描述 系统登录数据库映射
 **/

//@Repository,在Spring体系中用来标识Dao层的注解，在MyBatis体系中使用@Mapper来标识，使用哪一种都可以
//@Mapper  //启动类上已添加@MapperScan(value = "com.startest.wm.dao")，此处不必每个Mapper都添加Mapper注解
public interface SysLoginDao {

    int addSysLogin(sys_login sysLogin);

    int updateSysLogin(sys_login sysLogin);

    int deleteSysLoginByID(@Param("id") String id);

    sys_login querySysLoginByID(@Param("id") String id);

    List<sys_login> querySysLoginByName(@Param("login_name") String login_name);

    sys_login querySysLogin(@Param("login_name") String login_name, @Param("login_pwd") String login_pwd);

    List<sys_login> querySysLoginAll();
}
