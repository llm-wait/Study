package com.startest.wm.dao;

import com.startest.wm.pojo.wm_book_notice;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-10-12-11:22
 * @描述 书表通告数据库访问接口
 */
@Repository
public interface TaskBookNoticeDao {
    /**
     * 获取航海通告产品质量评价信息
     *
     * @param notice 航海通告产品质量对象
     * @return
     */
    List<wm_book_notice> selectBookNoticeProductQualityList(wm_book_notice notice);

    /**
     * 新增航海通告产品质量评价信息
     *
     * @param notice 航海通告产品质量对象
     * @return
     */
    Boolean insertBookNoticeProductQualityInfo(wm_book_notice notice);

    /**
     * 修改航海通告产品质量评价信息
     *
     * @param notice 航海通告产品质量对象
     * @return
     */
    Boolean updateBookNoticeProductQualityInfo(wm_book_notice notice);

    /**
     * 删除航海通告产品质量评价信息
     *
     * @param strID 唯一ID
     * @return
     */
    Boolean deleteBookNoticeProductQualityInfo(@Param("strID")String strID);

}
