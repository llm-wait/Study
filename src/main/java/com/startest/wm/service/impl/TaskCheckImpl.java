package com.startest.wm.service.impl;

import com.startest.wm.dao.TaskCheckDao;
import com.startest.wm.pojo.wm_check_index;
import com.startest.wm.pojo.wm_task_check;
import com.startest.wm.service.TaskCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-05-11-15:27
 * @描述 任务质检服务实现接口
 */
@Service
public class TaskCheckImpl implements TaskCheckService {
    @Autowired
    private TaskCheckDao taskCheckDao;

    @Override
    public List<wm_task_check> getTaskCheckListInfo(wm_task_check taskCheck) {
        return taskCheckDao.selectTaskCheckListInfo(taskCheck);
    }

    @Override
    public int insertTaskCheckInfo(wm_task_check taskCheck) {
        return taskCheckDao.insertTaskCheckInfo(taskCheck);
    }

    @Override
    public int updateTaskCheckInfo(wm_task_check taskCheck) {
        return taskCheckDao.updateTaskCheckInfo(taskCheck);
    }

    @Override
    public int updateTaskEndCheckInfo(wm_check_index wmCheckIndex) {
        return taskCheckDao.updateTaskEndCheckInfo(wmCheckIndex);
    }

    @Override
    public wm_task_check getTaskCheckByDisID(String distributionId) {
        return taskCheckDao.getTaskCheckByDisID(distributionId);
    }

    @Override
    public void updataTaskCheckByDisID(wm_task_check wmTaskCheck) {
        taskCheckDao.updataTaskCheckByDisID(wmTaskCheck);
    }


    @Override
    public int deleteTaskCheckInfo(String strID) {
        return taskCheckDao.deleteTaskCheckInfo(strID);
    }
}
