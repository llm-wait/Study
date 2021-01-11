package com.startest.wm.service.impl;

import com.startest.wm.dao.TaskBookNoticeDao;
import com.startest.wm.pojo.wm_book_notice;
import com.startest.wm.service.TaskBookNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-10-12-13:26
 * @描述 书表通告业务逻辑接口实现
 */
@Service
public class TaskBookNoticeImp implements TaskBookNoticeService {

    @Autowired
    private TaskBookNoticeDao taskBookNoticeDao;

    /**
     * 获取航海通告产品质量评价信息
     *
     * @param notice 航海通告产品质量对象
     * @return
     */
    @Override
    public List<wm_book_notice> selectBookNoticeProductQualityList(wm_book_notice notice) {
        return taskBookNoticeDao.selectBookNoticeProductQualityList(notice);
    }

    /**
     * 新增航海通告产品质量评价信息
     *
     * @param notice 航海通告产品质量对象
     * @return
     */
    @Override
    public Boolean insertBookNoticeProductQualityInfo(wm_book_notice notice) {
        return taskBookNoticeDao.insertBookNoticeProductQualityInfo(notice);
    }

    /**
     * 修改航海通告产品质量评价信息
     *
     * @param notice 航海通告产品质量对象
     * @return
     */
    @Override
    public Boolean updateBookNoticeProductQualityInfo(wm_book_notice notice) {
        return taskBookNoticeDao.updateBookNoticeProductQualityInfo(notice);
    }

    /**
     * 删除航海通告产品质量评价信息
     *
     * @param strID 唯一ID
     * @return
     */
    @Override
    public Boolean deleteBookNoticeProductQualityInfo(String strID) {
        return taskBookNoticeDao.deleteBookNoticeProductQualityInfo(strID);
    }
}
