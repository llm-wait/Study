package com.startest.wm.dao;

import com.startest.wm.pojo.wm_task_dept;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-04-08-15:19
 * @描述 分配部门映射
 */
@Repository
public interface TaskDeptDao {
    /**
     * 检索任务分配部门信息
     * @param taskDept
     * @return
     */
    List<wm_task_dept> selectTaskDeptInfo(wm_task_dept taskDept);

    /**
     * 插入任务分配部门信息（单个）
     * @param taskDept
     * @return
     */
    int insertTaskDeptInfo(wm_task_dept taskDept);

    /**
     * 批量插入任务分配部门信息（多个）
     * @param list
     * @return
     */
    int insertTaskDeptInfoByList(@Param("list") List<wm_task_dept> list);

    /**
     *更新任务分配部门信息
     * @param taskDept
     * @return
     */
    int updateTaskDeptInfo(wm_task_dept taskDept);

    /**
     *删除任务分配部门信息（通过任务分配部门id）
     * @param taskDeptID
     * @return
     */
    int deleteTaskDeptInfoByTaskDeptID(@Param("taskDeptID") String taskDeptID);

    /**
     *删除任务分配部门信息（通过任务索引id）
     * @param taskIndexID 任务id
     * @return 删除条数
     */
    int deleteTaskDeptInfoByTaskIndexID(@Param("taskIndexID") String taskIndexID);

    /**
     *删除任务分配部门信息（通过任务索引id和部门ID）
     * @param taskIndexID
     * @return
     */
    int deleteTaskDeptInfoByTaskIndexIDAndDeptID(@Param("taskIndexID") String taskIndexID,
                                        @Param("taskDeptID") String taskDeptID);

    /**根据任务id集合，查看是否有已分配的任务
     * @param split 任务id集合
     * @return 已分配的个数
     */
    Integer selectAllocated(@Param("split") String[] split);

    /**跟据传入任务id集合与任务状态,查询任务id集合
     * @param taskIndexIds　任务id集合
     * @param state　任务状态
     * @return 未下达任务id集合
     */
    String[] selectTaskStateByIdsAndState(@Param("taskIndexIds") String[] taskIndexIds,
                             @Param("state") String  state);

    /**根据任务id，删除分配记录信息
     * @param notReleasedIds 　任务id数组
     * @return 删除条数
     */
    int delDeptByIndexId(@Param("notReleasedIds") String[] notReleasedIds);
}
