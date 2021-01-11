package com.startest.wm.service.impl;

import com.startest.wm.dao.ProjectSubjectDao;
import com.startest.wm.pojo.wm_project_subject;
import com.startest.wm.service.ProjectSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-06-28-9:41
 * @描述 科研项目服务接口实现
 */
@Service
public class ProjectSubjectImp implements ProjectSubjectService {
    @Autowired
    private ProjectSubjectDao projectSubjectDao;

    @Override
    public List<wm_project_subject> getProjectSubjectInfoList(wm_project_subject sub) {
        return projectSubjectDao.selectProjectSubjectInfoList(sub);
    }

    @Override
    public wm_project_subject getProjectSubjectInfoByID(String subID) {
        return projectSubjectDao.getProjectSubjectInfoByID(subID);
    }

    @Override
    public int insertProjectSubjectInfo(wm_project_subject sub) {
        return projectSubjectDao.insertProjectSubjectInfo(sub);
    }

    @Override
    public int updateProjectSubjectInfo(wm_project_subject sub) {
        return projectSubjectDao.updateProjectSubjectInfo(sub);
    }

    @Override
    public int deleteProjectSubjectInfo(String strID) {
        return projectSubjectDao.deleteProjectSubjectInfo(strID);
    }
}
