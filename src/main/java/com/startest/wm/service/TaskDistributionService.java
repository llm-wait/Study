package com.startest.wm.service;

import com.startest.wm.pojo.wm_task_distribution;
import com.startest.wm.utils.customresponse.RestResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-04-10-8:27
 * @描述 任务执行单逻辑接口
 */
public interface TaskDistributionService {
    /**
     * 检索任务执行单信息
     * @param taskDistribution
     * @return
     */
    List<wm_task_distribution> selectTaskDistribute(wm_task_distribution taskDistribution);

    /**
     * 获取总工天数
     * @param taskId
     * @param deptID
     * @param taskCon
     * @return
     */
    String getTaskDistributeAllWorkDays(String taskId,String deptID,String taskCon);

    /**
     *获取分配人员姓名
     * @param taskId 任务ID
     * @param deptID 部门ID
     * @param taskCon 内容
     * @param strduty 内容
     * @return
     */
    String getTaskDistributeName(String taskId,String deptID,String taskCon,String strduty);

    /**
     * 获取任务内容
     * @param taskId
     * @param deptID
     * @return
     */
    List<Map> getTaskDistributionContentList(String taskId, String deptID);


    /**
     * 更新任务执行单信息
     * @param taskDistribution
     * @return
     */
    int updateTaskDistribution(wm_task_distribution taskDistribution);

    /**
     * 更新接受时间
     * @param distributionID
     * @param startDate
     * @return
     */
    int updateTaskDistributionStartDate(String distributionID, String startDate);

    /**
     * 更新完成时间
     * @param distributionID
     * @param endDate
     * @return
     */
    int updateTaskDistributionEndDate(String distributionID,String endDate);




    /**跟据任务id计算任务总工天
     * @param indexID
     * @return
     */
    Double getChartAllWorkDays(String indexID, String productType);

    List<Map> getDuty(String indexID, String deptID);

    /**
     * 检索产品类型
     *
     * @param indexID
     * @return
     */
    List<wm_task_distribution> selectTaskProductType(String indexID);

    /**
     * 获取部门一个季度海图任务列表
     *
     * @param year 年份
     * @param quarter 季度
     * @param deptID 部门ID
     * @return
     */
    List<wm_task_distribution> getDeptAllChartWorkListInAQuarter(@Param("year") String year,
                                                                 @Param("quarter") int quarter,
                                                                 @Param("deptID") String deptID);

    /**
     * 获取部门一个季度的书表任务列表
     *
     * @param year 年份
     * @param quarter 季度
     * @param deptID 部门ID
     * @return
     */
    List<wm_task_distribution> getDeptAllBookWorkListInAQuarter(@Param("year") String year,
                                                                 @Param("quarter") int quarter,
                                                                 @Param("deptID") String deptID);

    /**生产任务——表单详情——删除人员执行记录
     * @param disId 执行单id
     * @return 成功
     */
    RestResponse<String> deleteTaskDistributionInfo(String disId);

    /**新增任务执行单
     * @param taskDistribution 任务分配执行单对象
     * @return 结果
     */
    RestResponse<String> addTaskDistributionInfo(wm_task_distribution taskDistribution);
}
