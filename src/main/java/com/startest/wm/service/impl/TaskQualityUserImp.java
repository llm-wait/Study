package com.startest.wm.service.impl;

import com.startest.wm.dao.TaskQualityUserDao;
import com.startest.wm.pojo.wm_quality_makeuser;
import com.startest.wm.pojo.wm_quality_user;
import com.startest.wm.service.TaskQualityMakeuserService;
import com.startest.wm.service.TaskQualityUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-10-12-16:08
 * @描述 海图产品校对审查测评业务逻辑实现
 */
@Service
public class TaskQualityUserImp implements TaskQualityUserService {
    @Autowired
    private TaskQualityUserDao taskQualityUserDao;

    /**
     * 检索校对审查质量测评信息
     *
     * @param user 校对审查质量测评信息对象
     * @return
     */
    @Override
    public List<wm_quality_user> selectChartUserQualityInfoList(wm_quality_user user) {
        return taskQualityUserDao.selectChartUserQualityInfoList(user);
    }

    /**
     * 新增校对审查质量测评信息
     *
     * @param user 校对审查质量测评信息对象
     * @return
     */
    @Override
    public Boolean insertChartUserQualityInfoList(wm_quality_user user) {
        return taskQualityUserDao.insertChartUserQualityInfoList(user);
    }

    /**
     * 修改校对审查质量测评信息
     *
     * @param user 校对审查质量测评信息对象
     * @return
     */
    @Override
    public Boolean updateChartUserQualityInfoList(wm_quality_user user) {
        return taskQualityUserDao.updateChartUserQualityInfoList(user);
    }

    /**
     * 新增校对审查质量测评信息
     *
     * @param strID 唯一ID
     * @return
     */
    @Override
    public Boolean deleteChartUserQualityInfoList(String strID) {
        return taskQualityUserDao.deleteChartUserQualityInfoList(strID);
    }
}
