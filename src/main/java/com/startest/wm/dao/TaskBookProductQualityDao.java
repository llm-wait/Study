package com.startest.wm.dao;

import com.startest.wm.pojo.wm_quality_book;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-10-10-15:21
 * @描述 书表产品质量测评表
 */
@Repository
public interface TaskBookProductQualityDao {

    /**
     * 检索书表产品质量测评信息
     *
     * @param book 书表产品质量测评对象
     * @return
     */
    List<wm_quality_book> selectBookQualityInfoList(wm_quality_book book);


    /**
     * 新增书表产品质量测评信息
     *
     * @param book 书表产品质量测评对象
     * @return
     */
    Boolean insertBookQualityInfo(wm_quality_book book);

    /**
     * 修改书表产品质量测评信息
     *
     * @param book 书表产品质量测评对象
     * @return
     */
    Boolean updateBookQualityInfo(wm_quality_book book);

    /**
     * 修改书表产品质量测评统计信息
     *
     * @param book 书表产品质量测评对象
     * @return
     */
    Boolean updateBookQualityValueInfo(wm_quality_book book);

    /**
     * 删除书表产品质量测评信息
     *
     * @param strID 唯一ID
     * @return
     */
    Boolean deleteBookQualityInfo(@Param("strID") String strID);


    /**
     * 获取一年内用户产品工天数
     *
     * @param userID 用户ID
     * @param quarterNum 季度
     * @param year 年份
     * @param state 状态：优秀，合格，不合格
     * @return
     */
    Double getUserBookProductTypeWordDays(@Param("userID") String userID,
                                           @Param("quarterNum") String quarterNum,
                                           @Param("year") String year,
                                           @Param("state") String state);
}
