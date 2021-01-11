package com.startest.wm.service;

import com.startest.wm.pojo.wm_check_index;
import com.startest.wm.pojo.wm_task_check;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-05-11-15:26
 * @描述 任务质检服务接口
 */
public interface TaskCheckService {
    /**
     * 检索任务质检信息
     * @param taskCheck
     * @return
     */
    List<wm_task_check> getTaskCheckListInfo(wm_task_check taskCheck);

    /**
     * 新增任务质检信息
     * @param taskCheck
     * @return
     */
    int insertTaskCheckInfo(wm_task_check taskCheck);

    /**
     * 修改任务质检信息
     * @param taskCheck
     * @return
     */
    int updateTaskCheckInfo(wm_task_check taskCheck);

    /**
     * 修改任务结束后的质检信息
     * @param wmCheckIndex
     * @return
     */
    int updateTaskEndCheckInfo(wm_check_index wmCheckIndex);

    /**
     * 删除任务质检信息
     * @param strID
     * @return
     */
    int deleteTaskCheckInfo(@Param("strID") String strID);

    /**根据id查询质检信息
     * @param distributionId
     * @return
     */
    wm_task_check getTaskCheckByDisID(String distributionId);


    /**根据id修改质检信息
     * @param wmTaskCheck
     */
    void updataTaskCheckByDisID(wm_task_check wmTaskCheck);

}
