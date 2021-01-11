package com.startest.wm.service;

import com.startest.wm.pojo.wm_project_files;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-06-29-13:37
 * @描述 项目文档服务接口
 */
public interface ProjectFilesService {
    /**
     * 检索
     * @param proFiles
     * @return
     */
    List<wm_project_files> getProjectFilesInfoList(wm_project_files proFiles);

    /**
     * @Description: 根据文档ID获取文档
     * @Param: [proFileID]
     * @return: com.startest.wm.pojo.wm_project_files
     **/  
    wm_project_files getProjectFilesInfoByID(String proFileID);

    /**
     * 新增
     * @param proFiles
     * @return
     */
    int insertProjectFilesInfo(wm_project_files proFiles);

    /**
     * 修改
     * @param proFiles
     * @return
     */
    int updateProjectFilesInfo(wm_project_files proFiles);

    /**
     * 修改上传路径
     * @param proFiles
     * @return
     */
    int updateProjectFilesPath(wm_project_files proFiles);

    /**
     * 删除
     * @param strID
     * @return
     */
    int deleteProjectFilesInfo(String strID);
}
