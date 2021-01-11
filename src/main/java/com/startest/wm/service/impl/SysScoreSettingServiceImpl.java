package com.startest.wm.service.impl;

import com.startest.wm.dao.SysScoreSettingDao;
import com.startest.wm.pojo.sys_score_setting;
import com.startest.wm.service.SysScoreSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-04-02 10:34
 * @描述 系统成绩设置操作服务接口实现类
 **/
@Service
public class SysScoreSettingServiceImpl implements SysScoreSettingService {
    @Autowired
    SysScoreSettingDao sysScoreSettingDao;

    @Override
    public int insertScoreSetting(sys_score_setting scoreSetting) {
        return sysScoreSettingDao.insertScoreSetting(scoreSetting);
    }

    @Override
    public int deleteScoreSetting(String id) {
        return sysScoreSettingDao.deleteScoreSetting(id);
    }

    @Override
    public int updateScoreSetting(sys_score_setting scoreSetting) {
        return sysScoreSettingDao.updateScoreSetting(scoreSetting);
    }

    @Override
    public sys_score_setting queryScoreSettingByID(String id) {
        return sysScoreSettingDao.queryScoreSettingByID(id);
    }

    @Override
    public sys_score_setting queryScoreSettingByYear(Integer year) {
        return sysScoreSettingDao.queryScoreSettingByYear(year);
    }

    @Override
    public List<sys_score_setting> queryAllScoreSetting() {
        return sysScoreSettingDao.queryAllScoreSetting();
    }
}
