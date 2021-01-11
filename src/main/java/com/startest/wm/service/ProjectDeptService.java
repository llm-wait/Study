package com.startest.wm.service;

import com.startest.wm.model.ProjectDeptModel;
import com.startest.wm.pojo.wm_project_dept;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-06-24-15:39
 * @描述 项目分配部门服务接口
 */
public interface ProjectDeptService {
    /**
     * 检索项目分配部门列表
     * @param proDept
     * @return
     */
    List<wm_project_dept> getProjectDeptList(wm_project_dept proDept);

    /**
     * 获取项目分配树形列表
     * @param proDept
     * @return
     */
    List<wm_project_dept> getProjectDeptTreeList(wm_project_dept proDept);

    /**
     * 该项目是否已经在部门中分配
     * @param proID
     * @param deptID
     * @return
     */
    boolean isProjectDept(String proID,String deptID);

    /**
     * 新增项目分配部门信息
     * @param list
     * @return
     */
    int insertProjectDeptInfo(List<wm_project_dept> list);

    /**
     * 修改项目分配部门
     * @param model
     * @return
     */
    int updateProjectDeptInfo(ProjectDeptModel model);

    /**
     * 删除项目分配部门信息
     * @param proDept
     * @return
     */
    int deleteProjectDeptInfo(wm_project_dept proDept);
}
