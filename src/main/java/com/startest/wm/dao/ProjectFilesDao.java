package com.startest.wm.dao;

import com.startest.wm.pojo.wm_project_files;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-06-28-10:19
 * @描述 项目文件数据库接口
 */
@Repository
public interface ProjectFilesDao {
    /**
     * 检索
     * @param proFiles
     * @return
     */
    List<wm_project_files> selectProjectFilesInfoList(wm_project_files proFiles);

    wm_project_files getProjectFilesInfoByID(String fileID);

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
    int deleteProjectFilesInfo(@Param("strID") String strID);
}
