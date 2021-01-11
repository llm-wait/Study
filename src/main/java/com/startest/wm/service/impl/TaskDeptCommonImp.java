package com.startest.wm.service.impl;

import com.startest.wm.dao.TaskDeptCommonDao;
import com.startest.wm.model.TaskDailyModel;
import com.startest.wm.pojo.wm_task_deptcommon;
import com.startest.wm.service.TaskDeptCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-05-11-16:33
 * @描述 部门日常任务实现层
 */
@Service
public class TaskDeptCommonImp implements TaskDeptCommonService {
    @Autowired
    private TaskDeptCommonDao taskDeptCommonDao;

    @Override
    public List<TaskDailyModel> getTaskDeptCommonInfo(Map map) {
        return taskDeptCommonDao.selectTaskDeptCommonInfo(map);
    }

    @Override
    public int insertTaskDeptCommonInfo(wm_task_deptcommon comTask) {
        taskDeptCommonDao.insertTaskDeptCommonInfo(comTask);
        return 1;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public int insertTaskDeptCommonInfoByList(List<wm_task_deptcommon> comTaskList) {
        return taskDeptCommonDao.insertTaskDeptCommonInfoByList(comTaskList);
    }

    @Override
    public int updateTaskDeptCommonInfo(wm_task_deptcommon comTaskInfo) {
        return taskDeptCommonDao.updateTaskDeptCommonInfo(comTaskInfo);
    }

    @Override
    public int deleteTaskDeptCommonInfo(String strID) {
        return taskDeptCommonDao.deleteTaskDeptCommonInfo(strID);
    }

    @Override
    public wm_task_deptcommon getTaskDeptCommonInfoByID(String id) {
        return taskDeptCommonDao.getTaskDeptCommonInfoByID(id);
    }

    @Override
    public List<wm_task_deptcommon> getTaskDeptCommonInfoByName(String taskName) {
        return taskDeptCommonDao.getTaskDeptCommonInfoByName(taskName);
    }
}
