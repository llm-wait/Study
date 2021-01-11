package com.startest.wm.service.impl;

import com.startest.wm.dao.SysEnumDao;
import com.startest.wm.service.SysEnumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-05-28 14:29
 * @描述
 **/
@Service
public class SysEnumServiceImpl implements SysEnumService {

    @Autowired
    SysEnumDao sysEnumDao;

    @Override
    public List<String> queryPortCountry() {
        return sysEnumDao.queryPortCountry();
    }

    @Override
    public List<String> queryPortContient() {
        return sysEnumDao.queryPortContient();
    }

    @Override
    public List<String> queryPortSea() {
        return sysEnumDao.queryPortSea();
    }

    @Override
    public List<String> queryPortDataSource() {
        return sysEnumDao.queryPortDataSource();
    }

    @Override
    public List<String> queryMapPublishUnit() {
        return sysEnumDao.queryMapPublishUnit();
    }
}
