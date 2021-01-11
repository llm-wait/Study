package com.startest.wm.service;

import com.startest.wm.enums.EnumTaskChartDistributionType;
import com.startest.wm.enums.task.EnumTaskProductType;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-09-17-11:21
 * @描述 海图质控评审计算帮助接口
 */
public interface TaskChartCalculationHelperService {
    /**
     * 获取错漏值
     *
     * @param taskIndexID 任务索引ID
     * @param productType 产品类型（制图，电子图等）
     * @param disType     检查类型（组校、校对、审查、验收）
     * @return
     */
    Double GetAllTheErrorValue(String taskIndexID, String productType, String disType);

    /**
     * 计算海图作业员每工天缺陷值
     * 计算公式：错漏数量*错漏权重数/作业员工天数
     *
     * @param taskIndexID 任务索引ID
     * @param productType 产品类型（制图，电子图等）
     * @return
     */
    Double ChartWorkerDefactValueByWorkday(String taskIndexID, EnumTaskProductType productType);


    /**
     * 海图 组校、校对、审查、验收消灭错漏率
     * 计算公式：（组校、校对、审查、验收）之一错漏数量*错漏权重数/所有错漏数量*错漏权重数
     *
     * @param taskIndexID 任务索引ID
     * @param type        检查类型（组校、校对、审查、验收）
     * @param productType 产品类型（制图，电子图等）
     * @return
     */
    Double ChartCheckerPassAwayDefact(String taskIndexID, EnumTaskChartDistributionType type, EnumTaskProductType productType);


    /**
     * 海图产品每工天缺陷值计算
     * 计算公式：错漏数量*错漏权重/作业员工天数
     *
     * @param taskIndexID 任务索引ID
     * @param productType 产品类型（纸质图、电子图）
     * @return
     */
    Double ChartProductDefactValue(String taskIndexID, EnumTaskProductType productType);

    /**
     * 海图综合缺陷值计算
     * 计算公式(猜测)：纸图（错漏数量*错漏权重）+电子图（错漏数量*错漏权重）/纸质图工天数+电子图工天数
     *
     * @param taskIndexID 任务索引ID
     * @return
     */
    Double ChartProductComprehensiveDefactValue(String taskIndexID);

    /**
     * 计算海图员工的工天因数（也叫图幅难度系数）
     * 计算公式：本图工天/一个季度所做海图的平均工天  (条件：一个作业室，一个季度)
     * 注意：所以算的海图是已验收的海图，正在做的没有验收的海图不计算在内
     *
     * @param year        年份
     * @param quarter     季度
     * @param userID      员工ID
     * @param productType 产品类型 数字图，纸质图，s57图等
     * @param taskIndexID 任务索引ID
     * @return
     */
    Double ChartWorkDayFactor(String year, int quarter, String userID, EnumTaskProductType productType, String deptID, String taskIndexID);

    /**
     * 计算海图员工的平均工天
     * 计算公式：一个季度所做的工天之和/该季度所做图数
     * 注意：所以算的海图是已验收的海图，正在做的没有验收的海图不计算在内
     *
     * @param year    年份
     * @param quarter 季度
     * @param deptID  部门ID
     * @return
     */
    Double ChartWorkDayAverage(String year, int quarter, String deptID);

    /**
     * 错漏量计算
     *
     * @param taskIndexID 任务索引Id
     * @param productType 产品类型
     * @param productType 职务类型（组校，校对，审查）
     * @return
     */
    Double GetChartDefactValue(String taskIndexID, EnumTaskProductType productType, EnumTaskChartDistributionType disType);

    /***************************用户考评接口**************************************/

    /**
     * 获取用户一年的合格率
     * 计算公式：用户一年的合格率=用户一年中合格产品的工天数之和/用户一年中所有产品的工天数之和
     *
     * @param userID 用户ID
     * @param year   年份
     * @return
     */
    Double GetUserOneYearQualified(String userID, String year);

    /**
     * 获取用户一年的优秀率
     * 计算公式：用户一年的优秀率=用户一年中优秀产品的工天数之和/用户一年中所有产品的工天数之和
     *
     * @param userID 用户ID
     * @param year   年份
     * @return
     */
    Double GetUserOneYearExcellent(String userID, String year);

    /**
     * 获取用户一年的优秀工天
     * 计算公式：用户一年的优秀工天=用户一年中优秀产品的工天数之和
     *
     * @param userID 用户ID
     * @param year   年份
     * @return
     */
    Double GetUserOneYearExcellentDays(String userID, String year);

    /**
     * 获取用户一年内总工天
     * 计算公式：用户一年内总工天=用户一年日常任务总工天+用户一年中业务总工天
     *
     * @param userID 用户ID
     * @param year   年份
     * @return
     */
    Double GetUserOneYearAllWorkDays(String userID, String year);

    /**
     * 获取用户一年内的业务工天之和
     *
     * @param userID 用户ID
     * @param year   年份
     * @return
     */
    Double GetUserOneYearBusinessWorkDays(String userID, String year);

    /**
     * 获取单位人均工天
     * 获取全社人均工天
     * <p>
     * 计算公式：单位人均工天=单位所有人总工天之和/单位人数
     *
     * @param userIDList 用户ID列表
     * @param year       年份
     * @return
     */
    Double GetDeptOneYearAverageDays(List<String> userIDList, String year);

    /**
     * 获取单位考评总工天
     * 获取单位实际总工天
     * 获取单位优秀总工天
     * 获取单位优秀考评总工天
     *
     * @param userIDList 用户ID列表（考核人员列表，总人员列表）
     * @param year       年份
     * @param type       类型：0：获取单位考评总工天；1：获取单位实际总工天；2：获取单位优秀总工天；3：获取单位优秀考评总工天；
     * @return
     */
    Double GetDeptOneYearAllDays(List<String> userIDList, String year, int type);

    /**
     * 获取单位平均优秀工天
     * 获取全社平均优秀工天
     * <p>
     * 计算公式：单位平均优秀工天=单位优秀总工天/单位总人数
     *
     * @param userIDList 用户ID列表
     * @param year       年份
     * @return
     */
    Double GetDeptOneYearAverageExcellentDays(List<String> userIDList, String year);

    /**
     * 获取单位平均优秀率
     * 获取全社平均优秀率
     * <p>
     * 计算公式：单位平均优秀率=单位所有人优秀率之和/单位总人数
     *
     * @param userIDList 部门参与考评用户ID列表
     * @param year       年份
     * @return
     */
    Double GetDeptOneYearAverageExcellent(List<String> userIDList, String year);

    /**
     * 获取用户一年中技术修改和改成图数量
     *
     * @param userID 用户ID
     * @param year 年份
     * @param type 类型：0：技术修改；1：改成图
     * @param isMain 是否是主要：0：主要负责；1：次要负责
     * @return
     */
    Integer getUserChartEditProductCount(String userID, String year, Integer type, Integer isMain);
}
