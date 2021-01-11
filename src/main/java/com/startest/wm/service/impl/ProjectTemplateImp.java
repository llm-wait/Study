package com.startest.wm.service.impl;

import com.startest.wm.dao.ProjectTemplateDao;
import com.startest.wm.pojo.wm_project_template;
import com.startest.wm.service.ProjectTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-06-29-13:41
 * @描述 项目模板服务接口实现
 */
@Service
public class ProjectTemplateImp implements ProjectTemplateService {
    @Autowired
    private ProjectTemplateDao projectTemplateDao;

    @Override
    public List<wm_project_template> getProjectTemplateInfoList(wm_project_template template) {
        return projectTemplateDao.selectProjectTemplateInfoList(template);
    }

    @Override
    public wm_project_template getProjectTemplateInfoByID(String id) {
        return projectTemplateDao.getProjectTemplateInfoByID(id);
    }

    @Override
    public int insertProjectTemplateInfo(wm_project_template template) {
        return projectTemplateDao.insertProjectTemplateInfo(template);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public int insertProjectTemplateByList(List<wm_project_template> list) {
        return projectTemplateDao.insertProjectTemplateByList(list);
    }

    @Override
    public int updateProjectTemplateInfo(wm_project_template template) {
        return projectTemplateDao.updateProjectTemplateInfo(template);
    }

    @Override
    public int updateProjectTemplateFile(String template_id, String filename, String template_update, String template_upoper, String filePath) {
        return projectTemplateDao.updateProjectTemplateFile(template_id, filename, template_update, template_upoper, filePath);
    }

    @Override
    public int deleteProjectTemplateInfo(String strID) {
        return projectTemplateDao.deleteProjectTemplateInfo(strID);
    }
}
