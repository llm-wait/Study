package com.startest.wm.service.impl;

import com.startest.wm.dao.TaskInfoDao;
import com.startest.wm.pojo.wm_task_info;
import com.startest.wm.service.TaskInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @公司 北京星天科技
 * @author 杨世凯
 * @创建时间 2020-04-10-8:25
 * @描述 任务单逻辑接口实现类
 */
@Service
public class TaskInfoImpl implements TaskInfoService {
    private final TaskInfoDao taskInfoDao;

    public TaskInfoImpl(TaskInfoDao taskInfoDao) {
        this.taskInfoDao = taskInfoDao;
    }

    @Override
    public List<wm_task_info> selectTaskInfo(wm_task_info taskInfo) {
        return taskInfoDao.selectTaskInfo(taskInfo);
    }

    @Override
    public int insertTaskInfo(wm_task_info taskInfo) {
        return taskInfoDao.insertTaskInfo(taskInfo);
    }

    @Override
    public int updateTaskInfo(wm_task_info taskInfo) {
        return taskInfoDao.updateTaskInfo(taskInfo);
    }

    @Override
    public int deleteTaskInfoByTaskInfoId(String taskInfoId) {
        return taskInfoDao.deleteTaskInfoByTaskInfoId(taskInfoId);
    }

    @Override
    public int deleteTaskInfoByTaskIndexId(String taskIndexId) {
        return taskInfoDao.deleteTaskInfoByTaskIndexId(taskIndexId);
    }
}
