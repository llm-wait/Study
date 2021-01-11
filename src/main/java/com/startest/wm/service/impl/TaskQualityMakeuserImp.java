package com.startest.wm.service.impl;

import com.startest.wm.dao.TaskQualityMakeuserDao;
import com.startest.wm.pojo.wm_quality_makeuser;
import com.startest.wm.service.TaskQualityMakeuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-10-12-15:43
 * @描述 海图作业员质量测评业务接口实现
 */
@Service
public class TaskQualityMakeuserImp implements TaskQualityMakeuserService {

    @Autowired
    private TaskQualityMakeuserDao taskQualityMakeuserDao;

    /**
     * 海图作业员个人质量评价信息检索
     *
     * @param makeuser 海图作业员个人质量评价信息数据对象
     * @return
     */
    @Override
    public List<wm_quality_makeuser> selectMakeuserChartQualityInfo(wm_quality_makeuser makeuser) {
        return taskQualityMakeuserDao.selectMakeuserChartQualityInfo(makeuser);
    }

    /**
     * 海图作业员个人质量评价信息新增
     *
     * @param makeuser 海图作业员个人质量评价信息数据对象
     * @return
     */
    @Override
    public Boolean insertMakeuserChartQualityInfo(wm_quality_makeuser makeuser) {
        return taskQualityMakeuserDao.insertMakeuserChartQualityInfo(makeuser);
    }

    /**
     * 海图作业员个人质量评价信息修改
     *
     * @param makeuser 海图作业员个人质量评价信息数据对象
     * @return
     */
    @Override
    public Boolean updateMakeuserChartQualityInfo(wm_quality_makeuser makeuser) {
        return taskQualityMakeuserDao.updateMakeuserChartQualityInfo(makeuser);
    }

    /**
     * 海图作业员个人质量评价信息删除
     *
     * @param strID 唯一ID
     * @return
     */
    @Override
    public Boolean deleteMakeuserChartQualityInfo(String strID) {
        return taskQualityMakeuserDao.deleteMakeuserChartQualityInfo(strID);
    }
}
