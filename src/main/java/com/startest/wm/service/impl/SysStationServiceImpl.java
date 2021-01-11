package com.startest.wm.service.impl;

import com.startest.wm.dao.SysStationDao;
import com.startest.wm.pojo.sys_station;
import com.startest.wm.service.SysStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.jsf.FacesContextUtils;

import java.util.List;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-04-02 10:35
 * @描述 系统成绩设置操作服务接口实现类
 **/
@Service
public class SysStationServiceImpl implements SysStationService {

    @Autowired
    SysStationDao sysStationDao;

    @Override
    public int addStation(sys_station sysStation) {
        return sysStationDao.insertStation(sysStation);
    }

    @Override
    public int deleteStationByID(String stationID) {
        return sysStationDao.deleteStationByID(stationID);
    }

    @Override
    public int deleteStationByName(String stationName) {
        return sysStationDao.deleteStationByName(stationName);
    }

    @Override
    public int updateStation(sys_station sysStation) {
        return sysStationDao.updateStation(sysStation);
    }

    @Override
    public sys_station getStationByID(String stationID) {
        return sysStationDao.queryStationByID(stationID);
    }

    @Override
    public List<sys_station> getStationByName(String stationName) {
        return sysStationDao.queryStationByName(stationName);
    }

    @Override
    public List<sys_station> getAllStation() {
        return sysStationDao.queryStationAll();
    }

    @Override
    public boolean isStationExist(String stationID, String stationName) {
        return sysStationDao.isStationExist(stationID, stationName) > 0 ? true : false;
    }

    @Override
    public boolean isStationExistUsers(String stationID) {
        return sysStationDao.isStationExistUsers(stationID) > 0 ? true : false;
    }
}
