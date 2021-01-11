package com.startest.wm.service;

import com.startest.wm.model.TaskDailyModel;
import com.startest.wm.pojo.wm_task_deptcommon;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-05-11-16:32
 * @描述 部门日常任务服务接口
 */
public interface TaskDeptCommonService {
    /**
     * 检索部门日常任务信息
     * @param map
     * @return
     */
    List<TaskDailyModel> getTaskDeptCommonInfo(Map map);

    /**
     * 新增部门日常任务信息
     * @param comTask　　日常任务对象
     * @return 新增数
     */
    int insertTaskDeptCommonInfo(wm_task_deptcommon comTask);

    /**
     * 批量新增部门日常任务信息
     * @param comTaskList
     * @return
     */
    int insertTaskDeptCommonInfoByList(List<wm_task_deptcommon> comTaskList);

    /**
     * 修改部门日常任务信息
     * @param comTaskInfo 日常任务对象
     * @return 修改条数
     */
    int updateTaskDeptCommonInfo(wm_task_deptcommon comTaskInfo);

    /**
     * 删除部门日常任务信息
     * @param strID
     * @return
     */
    int deleteTaskDeptCommonInfo(@Param("strID") String strID);

    /**
     * @Description: 根据ID获取部门日常任务信息
     * @Param: [id]
     * @return: com.startest.wm.pojo.wm_task_deptcommon
     **/
    wm_task_deptcommon getTaskDeptCommonInfoByID(String id);

    /**
     * @Description: 根据任务名获取部门日常任务信息
     * @Param: [taskName]
     * @return: java.util.List<com.startest.wm.pojo.wm_task_deptcommon>
     **/
    List<wm_task_deptcommon> getTaskDeptCommonInfoByName(String taskName);
}
