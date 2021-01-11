package com.startest.wm.service;

import com.startest.wm.pojo.wm_quality_user;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-10-12-16:07
 * @描述 海图产品校对审查测评业务逻辑接口
 */
public interface TaskQualityUserService {

    /**
     * 检索校对审查质量测评信息
     *
     * @param user 校对审查质量测评信息对象
     * @return
     */
    List<wm_quality_user> selectChartUserQualityInfoList(wm_quality_user user);

    /**
     * 新增校对审查质量测评信息
     *
     * @param user 校对审查质量测评信息对象
     * @return
     */
    Boolean insertChartUserQualityInfoList(wm_quality_user user);

    /**
     * 修改校对审查质量测评信息
     *
     * @param user 校对审查质量测评信息对象
     * @return
     */
    Boolean updateChartUserQualityInfoList(wm_quality_user user);

    /**
     * 新增校对审查质量测评信息
     *
     * @param strID 唯一ID
     * @return
     */
    Boolean deleteChartUserQualityInfoList(String strID);

}
