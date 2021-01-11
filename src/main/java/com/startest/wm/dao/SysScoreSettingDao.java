package com.startest.wm.dao;

import com.startest.wm.pojo.sys_score_setting;

import java.util.List;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-04-02 10:12
 * @描述 考评成绩配置数据库映射
 **/
public interface SysScoreSettingDao {
    /**
     * @Description: 新增考评成绩设置
     * @Param: [scoreSetting]
     * @return: int
     **/  
    int insertScoreSetting(sys_score_setting scoreSetting);

    /**
     * @Description: 删除考评成绩设置
     * @Param: [id]
     * @return: int
     **/  
    int deleteScoreSetting(String id);
    
    /**
     * @Description: 更新考评成绩设置
     * @Param: [scoreSetting]
     * @return: int
     **/  
    int updateScoreSetting(sys_score_setting scoreSetting);

    /**
     * @Description: 根据ID查询考评成绩设置
     * @Param: [id]
     * @return: com.startest.wm.pojo.score_setting
     **/  
    sys_score_setting queryScoreSettingByID(String id);

    /**
     * @Description: 根据年份查询考评成绩设置
     * @Param: [year]
     * @return: com.startest.wm.pojo.score_setting
     **/  
    sys_score_setting queryScoreSettingByYear(Integer year);

    /**
     * @Description: 查询所有考评成绩设置
     * @Param: []
     * @return: java.util.List<com.startest.wm.pojo.score_setting>
     **/  
    List<sys_score_setting> queryAllScoreSetting();
}
