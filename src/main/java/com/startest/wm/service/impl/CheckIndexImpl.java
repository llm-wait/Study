package com.startest.wm.service.impl;

import com.startest.wm.dao.CheckIndexDao;
import com.startest.wm.pojo.wm_check_index;
import com.startest.wm.service.CheckIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-07-09-15:49
 * @描述 质检索引逻辑实现
 */
@Service
public class CheckIndexImpl implements CheckIndexService {
    @Autowired
    public CheckIndexDao checkIndexDao;

    @Override
    public List<wm_check_index> selectCheckIndexInfo(wm_check_index cindex) {
        return checkIndexDao.selectCheckIndexInfo(cindex);
    }

    @Override
    public int insertCheckIndexInfo(wm_check_index cindex) {
        checkIndexDao.deleteCheckIndexInfo(cindex.getTask_index_id());
        return checkIndexDao.insertCheckIndexInfo(cindex);
    }

    @Override
    public int updateCheckIndexInfo(wm_check_index cindex) {
        return checkIndexDao.updateCheckIndexInfo(cindex);
    }

    @Override
    public int deleteCheckIndexInfo(String strId) {
        return checkIndexDao.deleteCheckIndexInfo(strId);
    }
}
