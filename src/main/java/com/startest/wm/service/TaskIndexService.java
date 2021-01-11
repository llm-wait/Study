package com.startest.wm.service;

import com.startest.wm.enums.task.EnumTaskState;
import com.startest.wm.model.TaskBookModel;
import com.startest.wm.model.TaskIndexModel;
import com.startest.wm.model.TaskMapModel;
import com.startest.wm.model.TaskOthersModel;
import com.startest.wm.pojo.*;
import com.startest.wm.utils.customresponse.RestResponse;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-04-10-8:21
 * @描述 任务索引逻辑接口
 */
public interface TaskIndexService {
    /**
     * 获取任务索引信息
     * @param taskIndex 任务索引对象
     * @return 任务索引信息
     */
    List<wm_task_index> selectTaskIndexInfo(wm_task_index taskIndex);

    /**
     * 获取任务核心模型数据信息
     * @param task 任务对象
     * @return 任务质量评估列表
     */
    List<TaskIndexModel> getTaskIndexModelInfo(TaskIndexModel task);

    /**
     * 新增任务索引信息(单个)
     * @param taskIndex 任务对象
     * @return  添加结果
     */
    int insertTaskIndexInfo(wm_task_index taskIndex);

    /**
     * 批量新增任务索引信息（多个）
     * @param list
     * @return
     */
    int insertTaskIndexInfoByList(List<wm_task_index> list);

    /**
     * 关联下发的海图任务资料
     * @param dataList
     * @return
     */
    int insertTaskIndexInfoByChartData(List<map_data> dataList);

    /**
     * 关联下发的书表任务资料
     * @param dataList
     * @return
     */
    int insertTaskIndexInfoByBookData(List<port_data> dataList);

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
    int updateTaskRate(String indexID, String rate, String description);

    /**
     * 更新任务资料
     * @param indexID
     * @param dataID
     * @return
     */
    void updateTaskData(String indexID, String dataID);

    /**
     * 更新任务种类
     * @param list 任务id集合
     * @param classId 分类id
     */
    void updateTaskClassInfo(List<String> list, String classId);

    /**更新任务状态
     * @param indexID  任务id
     * @param state   要改成的任务状态
     * @param deptID　　部门id
     * @param deptName　部门名称
     * @param classID　任务分类id
     * @param taskName 任务名称
     * @param taskType　任务类型
     * @param tdept　　分配部门对象
     * @return 　修改条数
     */
    int updateTaskState(String indexID, String state,String deptID,String deptName,String classID,String taskName,String taskType,wm_task_dept tdept);


    /**
     * 更新任务分配年份
     * @param indexID 任务id
     * @param year 当前年份
     */
    void updateTaskYearInfo(String indexID, String year);

    /**
     * 批量更新年份
     * @param list
     * @param year
     * @return
     */
    int updateTaskYearInfoByList(List<String> list, String year);

    /**
     * 删除任务索引信息
     * @param indexID 任务id
     */
    void deleteTaskIndexInfo(String indexID);

    /**
     * 获取任务栏列表（未分配部门的）
     * @param taskIndex
     * @return
     */
    List<Object> selectTaskCheckListInfo(wm_task_index taskIndex);

    /**
     * 获取任务列表（已分配部门的）
     * @param taskIndex
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<Object> selectDeptTaskCheckListInfo(wm_task_index taskIndex,Integer pageNum,Integer pageSize);


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
     * @return 已分配任务年份列表
     */
    RestResponse<List<String>> selectTaskYearInfo();

    /**
     * 更新出厂时间
     * @param strID
     * @param printDate
     * @return
     */
    RestResponse<String> updatePrintDate(String strID, String printDate);



//    /**批量更新部门分配状态
//     * @param list 任务id列表
//     */
//    void updateDept(String[] list);

    /**根据id,查询质量评估列表并导出数据
     * @param ids 任务id
     */
    void checkOutcheckdepttaskByIds(String ids, HttpServletResponse response) throws Exception;

    /**批量下达任务到作业室
     * @param indexIdList　任务id集合
     * @param state　任务要修改成的状态
     * @return　修改结果
     */
    RestResponse<String> updateTaskIndexStateInfoByList(String indexIdList, EnumTaskState state);

    /**查询生产任务列表
     * @param index 　核心任务对象
     * @return List 生产任务列表
     */
    List<TaskIndexModel> selectDeptTaskIndexInfo(TaskIndexModel index);

    /**通过ID获取对象信息
     * @param indexId  任务id
     * @return 任务详情对象
     */
    RestResponse<wm_task_index> getTaskIndexInfoById(String indexId);

    /**修改任务名称
     * @param name　任务名称
     * @param taskId　任务id
     * @return  修改条数
     */
    int updateTaskName(String name,String taskId);

    /**新增任务
     * @param taskIndex 任务对象
     * @return 添加结果
     */
    int addTask(wm_task_index taskIndex);
}
