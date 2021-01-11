package com.startest.wm.service;

import com.startest.wm.config.exception.LoginException;
import com.startest.wm.enums.task.EnumTaskState;
import com.startest.wm.pojo.sys_dept;
import com.startest.wm.pojo.wm_task_dept;
import com.startest.wm.utils.customresponse.RestResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-04-10-8:23
 * @描述 分配部门逻辑接口
 */
public interface TaskDeptService {
    /**
     * 检索任务分配部门信息
     *
     * @param taskDept
     * @return
     */
    List<wm_task_dept> selectTaskDeptInfo(wm_task_dept taskDept);

    /**
     * 插入任务分配部门信息（单个）
     *
     * @param taskDept
     * @return
     */
    int insertTaskDeptInfo(wm_task_dept taskDept);

    /**
     * 批量插入任务分配部门信息（多个）
     *
     * @param list
     * @return
     */
    int insertTaskDeptInfoByList(List<wm_task_dept> list);

    /**
     * 更新任务分配部门信息(一个任务分配多个部门)
     *
     * @param taskIndexID
     * @param list
     * @return
     */
    int updateTaskDeptInfo(String taskIndexID, List<wm_task_dept> list);

    /**
     * 多个任务分配到一个部门
     * @param list
     * @param dept
     * @return
     */
    int updateTaskDeptInfoByList(List<wm_task_dept> reslist);

    /**
     * 删除任务分配部门信息（通过任务分配部门id）
     *
     * @param taskDeptID
     * @return
     */
    int deleteTaskDeptInfoByTaskDeptID(String taskDeptID);

    /**
     * 删除任务分配部门信息（通过任务索引id）
     *
     * @param taskIndexID
     * @return
     */
    int deleteTaskDeptInfoByTaskIndexID(String taskIndexID);


    /**任务管理——筹划任务——分配作业室（多任务分配多个部门）
     * @param jsonList 任务部门对象集合（包含多个部门，多个任务）
     */
    RestResponse<String> addTaskDeptInfoByList(List<sys_dept> jsonList);

    /**
     * 作业管理——生产任务——发送生产任务　todo 发送不成功　Request请求或种类ID不能为空！
     * @param classID
     * @param taskName
     * @param taskType
     * @param indexId
     * @param state
     * @return
     */
    RestResponse<String> updateTaskIndexStateInfo(HttpServletRequest request, String classID, String taskName, String taskType, String indexId, EnumTaskState state) throws LoginException;

}
