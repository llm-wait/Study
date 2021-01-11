package com.startest.wm.service.impl;

import com.startest.wm.dao.ProjectFilesDao;
import com.startest.wm.pojo.wm_project_files;
import com.startest.wm.service.ProjectFilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-06-29-13:40
 * @描述 项目文件服务接口实现
 */
@Service
public class ProjectFilesImp implements ProjectFilesService {
    @Autowired
    private ProjectFilesDao projectFilesDao;

    @Override
    public List<wm_project_files> getProjectFilesInfoList(wm_project_files proFiles) {
        return projectFilesDao.selectProjectFilesInfoList(proFiles);
    }

    @Override
    public wm_project_files getProjectFilesInfoByID(String proFileID) {
        return projectFilesDao.getProjectFilesInfoByID(proFileID);
    }

    @Override
    public int insertProjectFilesInfo(wm_project_files proFiles) {
        return projectFilesDao.insertProjectFilesInfo(proFiles);
    }

    @Override
    public int updateProjectFilesInfo(wm_project_files proFiles) {
        return projectFilesDao.updateProjectFilesInfo(proFiles);
    }

    @Override
    public int updateProjectFilesPath(wm_project_files proFiles) {
        return projectFilesDao.updateProjectFilesPath(proFiles);
    }

    @Override
    public int deleteProjectFilesInfo(String strID) {
        return projectFilesDao.deleteProjectFilesInfo(strID);
    }
}
