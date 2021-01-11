package com.startest.wm.service.impl;

import com.startest.wm.dao.YskTestDao;
import com.startest.wm.pojo.ysk_test;
import com.startest.wm.service.YskTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-07-11-8:38
 * @描述 杨世凯测试
 */
@Service
public class YskTestImp implements YskTestService {
    @Autowired
    private YskTestDao yskTestDao;

    @Override
    public List<ysk_test> getAllList(ysk_test yt) {
        return yskTestDao.selectStudentList(yt);
    }

    @Override
    public int insertInfo(ysk_test yt) {
        return yskTestDao.insertStudentInfo(yt);
    }

    @Override
    public int updateInfo(ysk_test yt) {
        return yskTestDao.updateStudentInfo(yt);
    }

    @Override
    public int deleteInfo(String strID) {
        return yskTestDao.deleteStudentInfo(strID);
    }
}
