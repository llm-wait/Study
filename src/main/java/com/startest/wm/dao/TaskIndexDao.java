package com.startest.wm.dao;

import com.startest.wm.model.TaskBookModel;
import com.startest.wm.model.TaskIndexModel;
import com.startest.wm.model.TaskMapModel;
import com.startest.wm.model.TaskOthersModel;
import com.startest.wm.pojo.wm_task_distribution;
import com.startest.wm.pojo.wm_task_index;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-04-08-14:03
 * @描述 任务索引表SQL映射
 */
@Repository
public interface TaskIndexDao {
    /**
     * 获取任务索引信息
     * @param taskIndex 任务索引对象
     * @return 任务索引信息
     */
    List<wm_task_index> selectTaskIndexInfo(wm_task_index taskIndex);

    /**
     * 获取年度任务
     * @param model 核心任务模型
     * @return 核心任务模型对象列表
     */
    List<TaskIndexModel> selectCommontTaskInfo(TaskIndexModel model);

    /**
     * 获取应急保障任务
     * @param model 核心任务模型
     * @return 核心任务模型对象列表
     */
    List<TaskIndexModel> selectOthersTaskInfo(TaskIndexModel model);

    /**
     * 新增任务索引信息(单个)
     * @param taskIndex　任务对象
     * @return 结果
     */
    int insertTaskIndexInfo(wm_task_index taskIndex);

    /**
     * 批量新增任务索引信息（多个）
     * @param list
     * @return
     */
    int insertTaskIndexInfoByList(@Param("list") List<wm_task_index> list);

    /**
     * 更新任务索引信息
     * @param taskIndex
     * @return
     */
    int updateTaskIndexInfo(wm_task_index taskIndex);

    /**
     * 更新任务进度
     * @param indexID
     * @param rate
     * @param description
     * @return
     */
    int updateTaskRate(@Param("indexID") String indexID,
                       @Param("rate") String rate,
                       @Param("description") String description);

    /**
     * 更新任务资料
     * @param indexID
     * @param dataID
     * @return
     */
    int updateTaskData(@Param("indexID") String indexID,
                       @Param("dataID") String dataID);

    /**
     * 更新任务种类
     * @param list
     * @param classID
     * @return
     */
    int updateTaskClassInfo(@Param("list") List<String> list,
                            @Param("classID") String classID,
                            @Param("name") String name
    );

    /**
     * 更新任务状态
     * @param indexID
     * @param state
     * @return
     */
    int updateTaskState(@Param("indexID") String indexID,
                        @Param("state") String state,
                        @Param("endData")String endData);

    /**
     * 批量更新任务状态
     * @param list
     * @param state
     * @return
     */
    int updateTaskStateByList(@Param("list") List<String> list,
                                @Param("state") String state);

    /**
     * 更新任务分配年份
     * @param indexID
     * @param year
     * @return
     */
    int updateTaskYearInfo(@Param("indexID") String indexID,
                           @Param("year") String year);

    /**
     * 批量更新年份
     * @param list
     * @param year
     * @return
     */
    int updateTaskYearInfoByList(@Param("list") List<String> list,
                                 @Param("year") String year);

    /**
     * 删除任务索引信息
     * @param indexID
     * @return
     */
    int deleteTaskIndexInfo(@Param("indexID") String indexID);

    /**
     * 查询海图资料任务列表（未分配部门列表）
     * @param taskIndex
     * @return
     */
    List<TaskMapModel> selectMapTaskListInfo(wm_task_index taskIndex);

    /**
     * 查询海图资料任务列表（已分配部门列表）
     * @param taskIndex
     * @return
     */
    List<TaskMapModel> selectDeptMapTaskListInfo(wm_task_index taskIndex);

    /**
     * 检索书表资料任务列表（未分配部门）
     * @param taskIndex
     * @return
     */
    List<TaskBookModel> selectBookTaskListInfo(wm_task_index taskIndex);

    /**
     * 检索书表资料任务列表（已分配部门）
     * @param taskIndex
     * @return
     */
    List<TaskBookModel> selectDeptBookTaskListInfo(wm_task_index taskIndex);

    /**
     * 检索应急保障任务（未分配部门）
     * @param taskIndex
     * @return
     */
    List<TaskOthersModel> selectOthersTaskListInfo(wm_task_index taskIndex);

    /**
     * 检索应急保障任务（已分配部门）
     * @param taskIndex
     * @return
     */
    List<TaskOthersModel> selectDeptOthersTaskListInfo(wm_task_index taskIndex);

    /**
     * 获取任务已分配年份列表
     * @return 任务年份列表
     */
    List<String> selectTaskYearInfo();

    /**
     * 更新出厂时间
     * @param strID
     * @param printDate
     * @return
     */
    int updatePrintDate(@Param("strID") String strID,
                        @Param("printDate") String printDate);

    String selectIsAllocation(String id);


    /**根据id查询年度任务
     * @param list
     * @return
     */
    List<TaskIndexModel> selectCommontByIds(List<String> list);

    /**根据id查询应急任务
     * @param list
     * @return
     */
    List<TaskIndexModel> selectOthersByIds(List<String> list);

    /**根据类型和人员查询是否重复
     *  @param taskDistribution
     *              任务执行单
     * @return
     *          是否重复
     */
    Integer selectTask(wm_task_distribution taskDistribution);

    /**查询所有流转任务详细数据
     * @param taskIndex 任务索引对象
     * @return 任务详情列表
     */
    ArrayList<wm_task_index> selectallTaskListInfo(wm_task_index taskIndex);

    /**查询年度任务和应急任务列表
     * @param classIds 任务核心数据对象
     * @return 任务核心数据对象集合
     */
    List<TaskIndexModel> getTaskIndexModelInfo(@Param("classIds") List<String> classIds);

    /**查询生产任务列表
     * @param index 核心任务对象
     * @return 生产任务列表
     */
    List<TaskIndexModel> selectDeptTaskIndexInfo(TaskIndexModel index);

    /**通过ID获取对象信息
     * @param indexId
     * @return
     */
    wm_task_index getTaskIndexInfoById(@Param("indexId") String indexId);

    /**修改任务名称
     * @param name　任务名称
     * @param taskId　任务id
     * @return 修改条数
     */
    int updateTaskName(@Param("name") String name,@Param("taskId") String taskId);
}
