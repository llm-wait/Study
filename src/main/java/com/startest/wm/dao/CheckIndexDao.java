package com.startest.wm.dao;

import com.startest.wm.pojo.wm_check_index;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-07-09-15:26
 * @描述 质检索引Dao层
 */
@Repository
public interface CheckIndexDao {

    /**
     * 获取质检索引信息
     *
     * @param cindex
     * @return
     */
    List<wm_check_index> selectCheckIndexInfo(wm_check_index cindex);

    /**
     * 获取用户一年中技术修改和改成图数量
     *
     * @param userID 用户ID
     * @param year 年份
     * @param type 类型：0：技术修改；1：改成图
     * @param isMain 是否是主要：0：主要负责；1：次要负责
     * @return
     */
    Integer getUserChartEditProductCount(@Param("userID") String userID,
                                         @Param("year") String year,
                                         @Param("type") Integer type,
                                         @Param("isMain") Integer isMain);

    /**
     * 新增质检索引信息
     *
     * @param cindex
     * @return
     */
    int insertCheckIndexInfo(wm_check_index cindex);

    /**
     * 修改质检索引信息
     *
     * @param cindex
     * @return
     */
    int updateCheckIndexInfo(wm_check_index cindex);

    /**
     * 删除质检索引信息
     *
     * @param strID
     * @return
     */
    int deleteCheckIndexInfo(@Param("strID") String strID);
}
