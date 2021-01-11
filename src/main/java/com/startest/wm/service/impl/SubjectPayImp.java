package com.startest.wm.service.impl;

import com.startest.wm.dao.SubjectPayDao;
import com.startest.wm.pojo.wm_subject_pay;
import com.startest.wm.service.SubjectPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-06-29-13:40
 * @描述 科目支出服务接口实现
 */
@Service
public class SubjectPayImp implements SubjectPayService {
    @Autowired
    private SubjectPayDao subjectPayDao;

    @Override
    public List<wm_subject_pay> getSubjectPayInfoList(wm_subject_pay pay) {
        return subjectPayDao.selectSubjectPayInfoList(pay);
    }

    @Override
    public wm_subject_pay getSubjectPayInfoByID(String payID) {
        return subjectPayDao.getSubjectPayInfoByID(payID);
    }

    @Override
    public int insertSubjectPayInfo(wm_subject_pay pay) {
        return subjectPayDao.insertSubjectPayInfo(pay);
    }

    @Override
    public int updateSubjectPayInfo(wm_subject_pay pay) {
        return subjectPayDao.updateSubjectPayInfo(pay);
    }

    @Override
    public int deleteSubjectPayInfo(String strID) {
        return subjectPayDao.deleteSubjectPayInfo(strID);
    }
}
