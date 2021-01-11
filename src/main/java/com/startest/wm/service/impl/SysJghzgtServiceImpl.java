package com.startest.wm.service.impl;

import com.startest.wm.dao.SysJghzgtDao;
import com.startest.wm.pojo.sys_jghzgt;
import com.startest.wm.service.SysJghzgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-04-02 10:32
 * @描述 系统机关核准工天操作服务接口实现类
 **/
@Service
public class SysJghzgtServiceImpl implements SysJghzgtService {

    @Autowired
    SysJghzgtDao sysJghzgtDao;

    @Override
    public int insert(sys_jghzgt jghzgt) {
        return sysJghzgtDao.insert(jghzgt);
    }

    @Override
    public int update(sys_jghzgt jghzgt) {
        return sysJghzgtDao.update(jghzgt);
    }

    @Override
    public sys_jghzgt query(Integer year, String deptID) {
        return sysJghzgtDao.query(year, deptID);
    }
}
