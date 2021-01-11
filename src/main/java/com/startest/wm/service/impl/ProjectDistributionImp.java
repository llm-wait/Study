package com.startest.wm.service.impl;

import com.startest.wm.dao.ProjectDistributionDao;
import com.startest.wm.pojo.wm_project_distribution;
import com.startest.wm.service.ProjectDistributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-06-24-14:55
 * @描述 项目分配服务实现类
 */
@Service
public class ProjectDistributionImp implements ProjectDistributionService {
    @Autowired
    private ProjectDistributionDao projectDistributionDao;

    @Override
    public List<wm_project_distribution> getProjectDistributionList(wm_project_distribution dis) {
        return projectDistributionDao.selectProjectDistributionList(dis);
    }

    @Override
    public wm_project_distribution getProjectDistributionByID(String disID) {
        return projectDistributionDao.getProjectDistributionByID(disID);
    }

    @Override
    public int insertProjectDistribution(wm_project_distribution dis) {
        return projectDistributionDao.insertProjectDistribution(dis);
    }

    @Override
    public int updateProjectDistribution(wm_project_distribution dis) {
        return projectDistributionDao.updateProjectDistribution(dis);
    }

    @Override
    public int deleteProjectDistribution(String strID) {
        return projectDistributionDao.deleteProjectDistribution(strID);
    }
}
