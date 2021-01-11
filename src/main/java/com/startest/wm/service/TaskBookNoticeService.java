package com.startest.wm.service;

import com.startest.wm.pojo.wm_book_notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-10-12-13:25
 * @描述 书表通告业务逻辑接口
 */
public interface TaskBookNoticeService {

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
    Boolean deleteBookNoticeProductQualityInfo(String strID);
}
