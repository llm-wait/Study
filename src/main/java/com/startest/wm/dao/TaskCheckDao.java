package com.startest.wm.dao;

import com.startest.wm.pojo.wm_check_index;
import com.startest.wm.pojo.wm_task_check;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-05-11-14:57
 * @描述 任务质检SQL映射层
 */
@Repository
public interface TaskCheckDao {
    /**
     * 检索任务质检信息
     * @param taskCheck
     * @return
     */
    List<wm_task_check> selectTaskCheckListInfo(wm_task_check taskCheck);

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

    /**
     * 通过ID获取质检错漏信息对象
     * @param distributionId 信息ID
     * @return
     */
    wm_task_check getTaskCheckByDisID(String distributionId);

    /**
     * 通过ID修改质检错漏信息
     * @param wmTaskCheck 错漏信息ID
     */
    void updataTaskCheckByDisID(wm_task_check wmTaskCheck);

    /**
     * 通过ID删除质检错漏信息
     * @param disID 错漏信息ID
     * @return
     */
    int deleteTaskCheckByDisId(String disID);


}
