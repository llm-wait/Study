package com.startest.wm.dao;

import com.startest.wm.pojo.wm_quality_makeuser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-10-12-13:32
 * @描述 海图作业员个人工作质量评价信息数据库访问层
 */
@Repository
public interface TaskQualityMakeuserDao {
    /**
     * 海图作业员个人质量评价信息检索
     *
     * @param makeuser 海图作业员个人质量评价信息数据对象
     * @return
     */
    List<wm_quality_makeuser> selectMakeuserChartQualityInfo(wm_quality_makeuser makeuser);

    /**
     * 海图作业员个人质量评价信息新增
     *
     * @param makeuser 海图作业员个人质量评价信息数据对象
     * @return
     */
    Boolean insertMakeuserChartQualityInfo(wm_quality_makeuser makeuser);

    /**
     * 海图作业员个人质量评价信息修改
     *
     * @param makeuser 海图作业员个人质量评价信息数据对象
     * @return
     */
    Boolean updateMakeuserChartQualityInfo(wm_quality_makeuser makeuser);

    /**
     * 海图作业员个人质量评价信息删除
     *
     * @param strID 唯一ID
     * @return
     */
    Boolean deleteMakeuserChartQualityInfo(@Param("strID") String strID);
}
