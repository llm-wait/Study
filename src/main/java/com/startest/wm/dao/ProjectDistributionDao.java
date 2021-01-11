package com.startest.wm.dao;

import com.startest.wm.pojo.wm_project_distribution;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-06-24-14:37
 * @描述 项目分配接口
 */
@Repository
public interface ProjectDistributionDao {
    /**
     * 检索项目分配信息
     *
     * @param dis
     * @return
     */
    List<wm_project_distribution> selectProjectDistributionList(wm_project_distribution dis);

    wm_project_distribution getProjectDistributionByID(String disID);

    /**
     * 新增项目分配信息
     *
     * @param dis
     * @return
     */
    int insertProjectDistribution(wm_project_distribution dis);

    /**
     * 修改项目分配信息
     *
     * @param dis
     * @return
     */
    int updateProjectDistribution(wm_project_distribution dis);

    /**
     * 删除项目分配信息
     *
     * @param strID
     * @return
     */
    int deleteProjectDistribution(@Param("strID") String strID);
}
