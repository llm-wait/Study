package com.startest.wm.service.impl;

import com.startest.wm.dao.SysLoginDao;
import com.startest.wm.pojo.sys_login;
import com.startest.wm.service.SysLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-03-20 15:53
 * @描述 系统登录服务实现类
 **/
@Service
public class SysLoginServiceImpl implements SysLoginService {

    @Autowired
    SysLoginDao sysLoginDao;

    @Override
    public int addSysLogin(sys_login sysLogin) {
        return sysLoginDao.addSysLogin(sysLogin);
    }

    @Override
    public int updateSysLogin(sys_login sysLogin) {
        return sysLoginDao.updateSysLogin(sysLogin);
    }

    @Override
    public int deleteSysLoginByID(String id) {
        return sysLoginDao.deleteSysLoginByID(id);
    }

    @Override
    public sys_login querySysLoginByID(String id) {
        return sysLoginDao.querySysLoginByID(id);
    }

    @Override
    public List<sys_login> querySysLoginByName(String login_name) {
        return sysLoginDao.querySysLoginByName(login_name);
    }

    @Override
    public sys_login querySysLogin(String login_name, String login_pwd) {
        return sysLoginDao.querySysLogin(login_name, login_pwd);
    }

    @Override
    public List<sys_login> querySysLoginAll() {
        return sysLoginDao.querySysLoginAll();
    }
}
