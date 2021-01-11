package com.startest.wm.dao;

import com.startest.wm.pojo.wm_task_others;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-05-11-11:35
 * @描述 应急保障任务管理映射
 */
@Repository
public interface TaskOthersDao {

    /**
     * 检索应急保障任务信息
     * @param taskOthers 应急保障任务对象
     * @return 应急保障任务列表
     */
    List<wm_task_others> selectTaskOthersInfo(wm_task_others taskOthers);

    /**
     * 新增应急保障任务信息
     * @param taskOthers 应急保障任务对象
     * @return 添加条数
     */
    int addTaskOthersInfo(wm_task_others taskOthers);

    /**
     * 批量导入应急保障任务
     * @param list
     * @return
     */
    int insertTaskOthersByList(@Param("list") List<wm_task_others> list);

    /**
     * 修改应急保障任务信息
     * @param taskOthers　　应急对象
     * @return
     */
    int editTaskOthersInfo(wm_task_others taskOthers);

    /**
     * 删除应急保障任务信息
     * @param strID　应急任务id
     * @return 删除条数
     */
    int deleteTaskOthersInfo(@Param("strID") String strID);


    /**查询应急任务名是否存在
     * @param otaskName
     * @return yes
     */
    boolean isOtherByName(@Param("otaskName") String otaskName);

    /**根据当天记录编号，查询应急任务数
     * @param dateNowStr　时间编号
     * @return 条数
     */
    Integer countOtherByCode(@Param("dateNowStr") String dateNowStr);

}
