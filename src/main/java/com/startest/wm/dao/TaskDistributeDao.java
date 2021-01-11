package com.startest.wm.dao;

import com.startest.wm.pojo.wm_task_distribution;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-04-08-16:24
 * @描述 任务执行单映射
 */
@Repository
public interface TaskDistributeDao {
    /**
     * 检索任务执行单信息
     *
     * @param taskDistribution
     * @return
     */
    List<wm_task_distribution> selectTaskDistribute(wm_task_distribution taskDistribution);

    /**
     * 获取分配的工作内容
     *
     * @param taskID
     * @param deptID
     * @return
     */
    List<Map> selectTaskDistributionContent(@Param("taskID") String taskID,
                                            @Param("deptID") String deptID);

    /**
     * 通过条件获取分配人姓名
     *
     * @param taskID
     * @param deptID
     * @param taskCon
     * @param taskDuty
     * @return
     */
    String selectTaskDistributionNameByID(@Param("taskID") String taskID,
                                          @Param("deptID") String deptID,
                                          @Param("taskCon") String taskCon,
                                          @Param("taskDuty") String taskDuty);

    /**
     * 插入任务执行单信息
     *
     * @param taskDistribution
     * @return
     */
    int insertTaskDistribution(wm_task_distribution taskDistribution);

    /**
     * 更新任务执行单信息
     *
     * @param taskDistribution
     * @return
     */
    int updateTaskDistribution(wm_task_distribution taskDistribution);

    /**
     * 更新接受时间
     *
     * @param distributionID
     * @param startDate
     * @return
     */
    int updateTaskDistributionStartDate(@Param("distributionID") String distributionID, @Param("startDate") String startDate);

    /**
     * 更新完成时间
     *
     * @param distributionID
     * @param endDate
     * @return
     */
    int updateTaskDistributionEndDate(@Param("distributionID") String distributionID, @Param("endDate") String endDate);


    /**
     * 删除任务执行单信息
     *
     * @param distributionID
     * @return
     */
    int delteTaskDistributiion(@Param("distributionID") String distributionID);

    /**
     * 跟据任务id计算海图任务总工天
     *
     * @param indexID     任务索引ID
     * @param productType 海图产品类型（制图，电子图，S57图等）
     * @return
     */
    Double getChartAllWorkDays(@Param("indexID") String indexID, @Param("productType") String productType);

    /**
     * 跟据任务id计算书表任务总工天
     *
     * @param indexID 任务索引ID
     *                书表章节类型（默认输入第一章名称或者输入任意一张名称），书表章节作业员的工天要一致
     * @return
     */
    Double getBookAllWordDays(@Param("indexID") String indexID);

    /**
     * 通过ID获取其对应的人员姓名及其职务
     *
     * @param indexID
     * @param deptID
     * @return
     */
    List<Map> getDuty(@Param("indexID") String indexID, @Param("deptID") String deptID);

    /**
     * 检索产品类型
     *
     * @param indexID 任务id
     * @return 产品类型和分配类型（海图／书表＋电子／s57）
     */
    List<wm_task_distribution> selectTaskProductType(@Param("indexID") String indexID);

    /**
     * 获取部门一个季度海图任务列表
     *
     * @param year 年份
     * @param startDate 查询的开始时间
     * @param endDate 查询的结束时间
     * @param deptID 部门ID
     * @return
     */
    List<wm_task_distribution> getDeptAllChartWorkListInAQuarter(@Param("year") String year,
                                                                 @Param("startDate") String startDate,
                                                                 @Param("endDate") String endDate,
                                                                 @Param("deptID") String deptID);

    /**
     * 获取部门一个季度的书表任务列表
     *
     * @param year 年份
     * @param startDate 查询的开始时间
     * @param endDate 查询的结束时间
     * @param deptID 部门ID
     * @return
     */
    List<wm_task_distribution> getDeptAllBookWorkListInAQuarter(@Param("year") String year,
                                                                @Param("startDate") String startDate,
                                                                @Param("endDate") String endDate,
                                                                @Param("deptID") String deptID);

    /**
     * 获取部门人员一年内的业务总工天数
     * @param year 年份
     * @param userID 用户ID
     * @return
     */
    Double getUserOneYearAllWorkDays(@Param("year") String year,
                                  @Param("userID") String userID);
}
