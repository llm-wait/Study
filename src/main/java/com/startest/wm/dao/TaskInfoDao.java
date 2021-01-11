package com.startest.wm.dao;

import com.startest.wm.pojo.wm_task_info;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-04-08-14:54
 * @描述 任务单SQL映射
 */
@Repository
public interface TaskInfoDao {
    /**
     * 检索任务单信息
     * @param taskInfo 任务详情对象
     * @return 任务详情集合
     */
    List<wm_task_info> selectTaskInfo(wm_task_info taskInfo);

    /**
     * 插入任务单信息
     * @param taskInfo 任务单详情
     * @return 插入条数
     */
    int insertTaskInfo(wm_task_info taskInfo);

    /**
     * 更新任务单信息
     * @param taskInfo 任务单对象
     * @return 修改条数
     */
    int updateTaskInfo(wm_task_info taskInfo);

    /**
     * 删除任务单信息（通过任务单ID）
     * @param taskInfoId 任务单对象
     * @return 删除条数
     */
    int deleteTaskInfoByTaskInfoId(@Param("taskInfoId") String taskInfoId);

    /**
     * 删除任务单信息（通过任务索引ID）
     * @param taskIndexId 任务索引对象
     * @return 删除结果
     */
    int deleteTaskInfoByTaskIndexId(@Param("taskIndexId") String taskIndexId);

}
