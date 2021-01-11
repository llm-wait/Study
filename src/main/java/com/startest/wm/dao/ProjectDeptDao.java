package com.startest.wm.dao;

import com.startest.wm.pojo.wm_project_dept;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-06-24-15:27
 * @描述 项目分配部门SQL接口
 */
@Repository
public interface ProjectDeptDao {
    /**
     * 检索项目分配部门列表
     * @param proDept
     * @return
     */
    List<wm_project_dept> selectProjectDeptList(wm_project_dept proDept);

    /**
     * 新增项目分配部门信息
     * @param list
     * @return
     */
    int insertProjectDeptInfo(@Param("list") List<wm_project_dept> list);

    /**
     * 删除项目分配部门信息
     * @param proDept
     * @return
     */
    int deleteProjectDeptInfo(wm_project_dept proDept);
}
