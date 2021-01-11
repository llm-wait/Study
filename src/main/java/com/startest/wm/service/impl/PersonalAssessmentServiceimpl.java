package com.startest.wm.service.impl;

import com.startest.wm.dao.PersonalAssessmentDao;
import com.startest.wm.pojo.wm_quality_user;
import com.startest.wm.service.qualityevaluation.PersonalAssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName:startestwm
 * @PackageName:com.startest.wm.service.impl
 * @ClassName:PersonalAssessmentServiceimpl
 * @Description: 质量评估实现类
 * @author: skj
 * @date 2020/7/28  15:53
 */
@Service
public class PersonalAssessmentServiceimpl implements PersonalAssessmentService {
    @Autowired
    private PersonalAssessmentDao personalAssessmentDao;


    @Override
    public List<String> selectGroupLeader(String stationName) {
        return personalAssessmentDao.selectGroupLeader(stationName);
    }

    @Override
    public List<String> selectNameByStation(String dept_id, String dept_name) {
        return personalAssessmentDao.selectNameByStation(dept_id, dept_name);
    }

    @Override
    public List<wm_quality_user> personalSelect(wm_quality_user wmQualityUser) {
        return personalAssessmentDao.personalSelect(wmQualityUser);
    }
}
