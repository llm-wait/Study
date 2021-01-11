package com.startest.wm.service.impl;

import com.startest.wm.dao.ProjectDeptDao;
import com.startest.wm.dao.ProjectDistributionDao;
import com.startest.wm.model.ProjectDeptModel;
import com.startest.wm.pojo.wm_project_dept;
import com.startest.wm.pojo.wm_project_distribution;
import com.startest.wm.service.ProjectDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-06-24-15:43
 * @描述 项目分配部门服务实现类
 */
@Service
public class ProjectDeptImp implements ProjectDeptService {
    @Autowired
    private ProjectDeptDao projectDeptDao;
    @Autowired
    private ProjectDistributionDao projectDistributionDao;

    @Override
    public List<wm_project_dept> getProjectDeptList(wm_project_dept proDept) {
        return projectDeptDao.selectProjectDeptList(proDept);
    }

    @Override
    public List<wm_project_dept> getProjectDeptTreeList(wm_project_dept proDept) {
        List<wm_project_dept> resultList = new ArrayList<>();
        List<wm_project_dept> list = projectDeptDao.selectProjectDeptList(proDept);
        if (list != null && list.size() > 0) {
            for (wm_project_dept item : list) {
                wm_project_distribution proDis = new wm_project_distribution();
                proDis.setProject_id(item.getProject_id());
                proDis.setDept_id(item.getDept_id());
                item.setList(projectDistributionDao.selectProjectDistributionList(proDis));
                resultList.add(item);
            }
        }
        return resultList;
    }

    @Override
    public boolean isProjectDept(String proID, String deptID) {
        boolean result = false;
        wm_project_dept proDept = new wm_project_dept();
        proDept.setProject_id(proID);
        proDept.setDept_id(deptID);
        List<wm_project_dept> list = projectDeptDao.selectProjectDeptList(proDept);
        if (list != null && list.size() > 0) {
            result = true;
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public int insertProjectDeptInfo(List<wm_project_dept> list) {
        return projectDeptDao.insertProjectDeptInfo(list);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public int updateProjectDeptInfo(ProjectDeptModel model) {
        wm_project_dept pro = new wm_project_dept();
        pro.setProject_id(model.getProject_id());
        projectDeptDao.deleteProjectDeptInfo(pro);
        return projectDeptDao.insertProjectDeptInfo(model.getList());
    }

    @Override
    public int deleteProjectDeptInfo(wm_project_dept proDept) {
        return projectDeptDao.deleteProjectDeptInfo(proDept);
    }
}
