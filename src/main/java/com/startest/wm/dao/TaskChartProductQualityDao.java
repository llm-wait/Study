package com.startest.wm.dao;

import com.startest.wm.enums.EnumTaskQualityState;
import com.startest.wm.pojo.wm_quality_chart;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-10-10-9:44
 * @描述 海图产品质量评价
 */
@Repository
public interface TaskChartProductQualityDao {

    /**
     * 海图产品质量评定信息查询
     *
     * @param chart 查询条件对象
     * @return
     */
    List<wm_quality_chart> selectChartProductQualityList(wm_quality_chart chart);

    /**
     * 新增产品质量评定信息
     *
     * @param chart 产品质量对象
     * @return
     */
    Boolean insertChartProductQualityInfo(wm_quality_chart chart);

    /**
     * 修改产品质量评定信息
     *
     * @param chart 产品质量评定对象
     * @return
     */
    Boolean updateChartProductQualityInfo(wm_quality_chart chart);

    /**
     * 修改统计信息（纸图每工天缺陷值，数字图每工天缺陷值，综合缺陷值，质量评定）
     *
     * @param chart
     * @return
     */
    Boolean updateChartProductQualityDataInfo(wm_quality_chart chart);

    /**
     * 删除产品质量评定信息
     *
     * @param strID
     * @return
     */
    Boolean deleteChartProductQualityInfo(@Param("strID") String strID);

    /**
     * 获取一年内用户产品工天数
     *
     * @param userID 用户ID
     * @param quarterNum 季度
     * @param year 年份
     * @param state 状态：优秀，合格，不合格
     * @return
     */
    Double getUserChartProductTypeWordDays(@Param("userID") String userID,
                                           @Param("quarterNum") String quarterNum,
                                           @Param("year") String year,
                                           @Param("state") String state);
}
