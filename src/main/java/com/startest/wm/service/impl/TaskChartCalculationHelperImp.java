package com.startest.wm.service.impl;

import com.startest.wm.dao.*;
import com.startest.wm.enums.EnumTaskChartDistributionType;
import com.startest.wm.enums.task.EnumTaskProductType;
import com.startest.wm.enums.EnumTaskQualityState;
import com.startest.wm.pojo.wm_task_check;
import com.startest.wm.pojo.wm_task_distribution;
import com.startest.wm.service.TaskChartCalculationHelperService;
import com.startest.wm.service.TaskCheckConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-09-18-14:34
 * @描述 海图质控评审计算实现类
 */
@Service
public class TaskChartCalculationHelperImp implements TaskChartCalculationHelperService {

    @Autowired
    private TaskChartCalculationHelperDao taskChartCalculationHelperDao;

    @Autowired
    private TaskCheckDao taskCheckDao;

    @Autowired
    private TaskDistributeDao taskDistributeDao;

    @Autowired
    private TaskDeptCommonDao taskDeptCommonDao;

    @Autowired
    private TaskChartProductQualityDao taskChartProductQualityDao;

    @Autowired
    private TaskBookProductQualityDao taskBookProductQualityDao;

    @Autowired
    private CheckIndexDao checkIndexDao;

    /**
     * 获取错漏值
     *
     * @param taskIndexID 任务索引ID
     * @param productType 产品类型（制图，电子图等）
     * @param disType 检查类型（校对、审查、验收） 不包含组校
     * @return
     */
    @Override
    public Double GetAllTheErrorValue(String taskIndexID, String productType,String disType) {
        Double result = 0.0;
        //获取错漏数量
        wm_task_check tcheck = new wm_task_check();
        if (taskIndexID != null && taskIndexID.length() > 0) {
            tcheck.setTask_id(taskIndexID);
        }
        if (productType != null && productType.length() > 0) {
            tcheck.setDistribution_type(productType);
        }
        if (disType != null && disType.length() > 0) {
            tcheck.setUser_duty(disType);
        }
        List<wm_task_check> checkList = taskCheckDao.selectTaskCheckListInfo(tcheck);
        if (checkList != null && checkList.size() > 0) {
            //遍历计算错漏量
            for (wm_task_check item : checkList) {
                result = result + item.getCheck_error1() * TaskCheckConfigService.CHART_SERIOUS + item.getCheck_error3() * TaskCheckConfigService.CHART_COMMONLY + item.getCheck_error4() * TaskCheckConfigService.CHART_SLIGHT + item.getCheck_mylevel();
            }
        }
        return result;
    }

    /**
     * 计算海图作业员每工天缺陷值
     * 计算公式：错漏数量*错漏权重数/作业员工天数
     *（）
     * @param taskIndexID 任务索引ID
     * @param productType 产品类型（制图，电子图等）
     * @return
     */
    @Override
    public Double ChartWorkerDefactValueByWorkday(String taskIndexID, EnumTaskProductType productType) {
        Double result = 0.0;

        //获取错漏数量
        Double errorValue = GetAllTheErrorValue(taskIndexID, productType.getTaskProductType(), null);
        if (errorValue != 0.0) {

            //获取作业员的工天数
            Double allDays = taskDistributeDao.getChartAllWorkDays(taskIndexID, productType.getTaskProductType());

            //计算员工每工天缺陷值
            result = errorValue / allDays;
        }

        return result;
    }

    /**
     * 海图 组校、校对、审查、验收消灭错漏率
     * 计算公式：（校对、审查、验收）之一错漏数量*错漏权重数/所有错漏数量*错漏权重数  不包含组校
     *
     * @param taskIndexID 任务索引ID
     * @param type        检查类型（组校、校对、审查、验收）
     * @param productType 产品类型（制图，电子图等）
     * @return
     */
    @Override
    public Double ChartCheckerPassAwayDefact(String taskIndexID, EnumTaskChartDistributionType type, EnumTaskProductType productType) {
        Double result = 0.0;

        //获取总共的错漏值
        Double errorValue = GetAllTheErrorValue(taskIndexID, productType.getTaskProductType(), null);
        if (errorValue != 0.0) {

            //获取组校或者其他单一的错漏值
            Double erValue = GetAllTheErrorValue(taskIndexID, productType.getTaskProductType(), productType.getTaskProductType());
            if (erValue != 0.0) {
                result = erValue / errorValue;
            }
        }

        return 0.0;
    }

    /**
     * 海图产品每工天缺陷值计算
     * 计算公式：验收错漏数量*错漏权重/作业员工天数
     *
     * @param taskIndexID 任务索引ID
     * @param productType 产品类型（纸质图、电子图）
     * @return
     */
    @Override
    public Double ChartProductDefactValue(String taskIndexID, EnumTaskProductType productType) {
        Double result = 0.0;

        //获取验收错漏量
        Double errorValue = GetAllTheErrorValue(taskIndexID, productType.getTaskProductType(), EnumTaskChartDistributionType.YS.getTaskChartDistributionType());
        if (errorValue != 0.0) {

            //获取作业员的工天数
            Double allDays = taskDistributeDao.getChartAllWorkDays(taskIndexID, productType.getTaskProductType());
            result = errorValue / allDays;
        }

        return result;
    }

    /**
     * 海图综合缺陷值计算
     * 计算公式：纸图错漏数量*纸图错漏权重+电子图错漏数量*电子图错漏权重/纸图作业员工天数+电子图作业员工天数
     *
     * @param taskIndexID 任务索引ID
     * @return
     */
    @Override
    public Double ChartProductComprehensiveDefactValue(String taskIndexID) {
        Double result = 0.0;

        //获取纸图的错漏量
        Double pageChartError = GetAllTheErrorValue(taskIndexID, EnumTaskProductType.ZT.getTaskProductType(), null);

        //获取数字图的错漏量
        Double eleChartError = GetAllTheErrorValue(taskIndexID, EnumTaskProductType.SZT.getTaskProductType(), null);

        //获取纸图的作业工天
        Double pageChartDays = taskDistributeDao.getChartAllWorkDays(taskIndexID, EnumTaskProductType.ZT.getTaskProductType());

        //获取数字图作业工天
        Double eleChartDays = taskDistributeDao.getChartAllWorkDays(taskIndexID, EnumTaskProductType.SZT.getTaskProductType());

        //计算结果
        result = (pageChartError + eleChartError) / (pageChartDays + eleChartDays);
        return result;
    }

    /**
     * 计算员工一幅海图的工天因数（也叫图幅难度系数）
     * 计算公式：本图工天/一个季度所做海图的平均工天  (条件：一个作业室，一个季度)
     * 注意：所以算的海图是已验收的海图，正在做的没有验收的海图不计算在内
     *
     * @param year        年份
     * @param quarter     季度
     * @param userID      员工ID
     * @param productType 产品类型 数字图，纸质图，s57图等
     * @param deptID 部门ID
     * @param taskIndexID 任务索引ID
     * @return
     */
    @Override
    public Double ChartWorkDayFactor(String year, int quarter, String userID, EnumTaskProductType productType,String deptID,String taskIndexID) {
        Double result = 0.0;

        //计算部门海图产品的平均工天
        Double averageChartDay = ChartWorkDayAverage(year, quarter, deptID);

        //获取作业员的工天数
        Double allDays = taskDistributeDao.getChartAllWorkDays(taskIndexID, productType.getTaskProductType());

        //计算结果
        result = allDays / averageChartDay;

        return result;
    }

    /**
     * 计算部门海图产品的平均工天
     * 计算公式：一个季度所做的工天之和/该季度所做图数
     * 注意：所以算的海图是已验收的海图，正在做的没有验收的海图不计算在内
     *
     * @param year 年份
     * @param quarter 季度
     * @param deptID      部门ID
     * @return
     */
    @Override
    public Double ChartWorkDayAverage(String year, int quarter, String deptID) {
        Double result = 0.0;
        String startDate = null;
        String endDate = null;
        String year1 = String.valueOf(Integer.valueOf(year) + 1);
        switch (quarter) {
            case 1:
                startDate = year + "-1-1";
                endDate = year + "-4-1";
                break;
            case 2:
                startDate = year + "-4-1";
                endDate = year + "-7-1";
                break;
            case 3:
                startDate = year + "-7-1";
                endDate = year + "-10-1";
                break;
            case 4:
                startDate = year + "-10-1";
                endDate = year1 + "-1-1";
                break;
            default:
                startDate = year + "-1-1";
                endDate = year1 + "-1-1";
                break;
        }
        List<wm_task_distribution> resList = taskDistributeDao.getDeptAllChartWorkListInAQuarter(year, startDate, endDate, deptID);
        if (resList != null && resList.size() > 0) {
            int chartCount = resList.size();
            Double allDays = 0.0;
            for (wm_task_distribution item : resList) {
                allDays = allDays + Double.valueOf(item.getWork_days());
            }
            result = allDays / chartCount;
        }
        return result;
    }

    /**
     * 错漏量计算
     *
     * @param taskIndexID 任务索引Id
     * @param productType 产品类型
     * @param productType 职务类型（组校，校对，审查）
     * @return
     */
    @Override
    public Double GetChartDefactValue(String taskIndexID, EnumTaskProductType productType, EnumTaskChartDistributionType disType) {
        return 0.0;
    }


    /***************************用户考评接口**************************************/
    /**
     * 获取用户一年的合格率
     * 计算公式：用户一年的合格率=用户一年中合格产品的工天数/用户一年中所有产品的工天数
     *
     * @param userID
     * @param year
     * @return
     */
    @Override
    public Double GetUserOneYearQualified(String userID, String year) {
        Double result = 0.0;

        //获取一年度海图合格产品工天数
        Double yearChartDays = taskChartProductQualityDao.getUserChartProductTypeWordDays(userID, null, year, EnumTaskQualityState.HG.getTaskQualityState());

        //获取一年度书表合格产品工天数
        Double yearBookDays = taskBookProductQualityDao.getUserBookProductTypeWordDays(userID, null, year, EnumTaskQualityState.HG.getTaskQualityState());

        //依据任务ID、人员ID从任务执行单里获取工天数
        Double yearChartAllDays = taskChartProductQualityDao.getUserChartProductTypeWordDays(userID, null, year, null);

        //获取书表的总工天数
        Double yearBookAllDays = taskBookProductQualityDao.getUserBookProductTypeWordDays(userID, null, year, null);

        //计算用户一年的合格率
        result = (yearChartDays + yearBookDays) / (yearChartAllDays + yearBookAllDays);


        return result;
    }

    /**
     * 获取用户一年的优秀率
     * 计算公式：用户一年的优秀率=用户一年中优秀产品的工天数/用户一年中所有产品的工天数
     *
     * @param userID
     * @param year
     * @return
     */
    @Override
    public Double GetUserOneYearExcellent(String userID, String year) {
        Double result = 0.0;

        //获取一年度海图优秀产品工天数
        Double yearChartDays = taskChartProductQualityDao.getUserChartProductTypeWordDays(userID, null, year, EnumTaskQualityState.YX.getTaskQualityState());

        //获取一年度书表优秀产品工天数
        Double yearBookDays = taskBookProductQualityDao.getUserBookProductTypeWordDays(userID, null, year, EnumTaskQualityState.YX.getTaskQualityState());

        //依据任务ID、人员ID从任务执行单里获取工天数
        Double yearChartAllDays = taskChartProductQualityDao.getUserChartProductTypeWordDays(userID, null, year, null);

        //获取书表的总工天数
        Double yearBookAllDays = taskBookProductQualityDao.getUserBookProductTypeWordDays(userID, null, year, null);

        //计算用户一年的合格率
        result = (yearChartDays + yearBookDays) / (yearChartAllDays + yearBookAllDays);

        return result;
    }

    /**
     * 获取用户一年的优秀工天
     * 计算公式：用户一年的优秀工天=用户一年中优秀产品的工天数之和
     *
     * @param userID
     * @param year
     * @return
     */
    @Override
    public Double GetUserOneYearExcellentDays(String userID, String year) {

        //获取一年度海图优秀产品工天数
        Double yearChartDays = taskChartProductQualityDao.getUserChartProductTypeWordDays(userID, null, year, EnumTaskQualityState.YX.getTaskQualityState());

        //获取一年度书表优秀产品工天数
        Double yearBookDays = taskBookProductQualityDao.getUserBookProductTypeWordDays(userID, null, year, EnumTaskQualityState.YX.getTaskQualityState());

        return (yearChartDays + yearBookDays);
    }

    /**
     * 获取用户一年内总工天
     * @param userID 用户ID
     * @param year 年份
     * @return
     */
    @Override
    public Double GetUserOneYearAllWorkDays(String userID, String year) {

        //获取用户日常任务总工天
        Double commonDays = taskDeptCommonDao.getUserOneYearAllCommonWorkDays(year, userID);
        //获取用户业务总工天
        Double workDays = taskDistributeDao.getUserOneYearAllWorkDays(year, userID);
        return commonDays + workDays;
    }

    /**
     * 获取用户一年内的业务工天之和
     *
     * @param userID 用户ID
     * @param year   年份
     * @return
     */
    @Override
    public Double GetUserOneYearBusinessWorkDays(String userID, String year) {
        return taskDistributeDao.getUserOneYearAllWorkDays(year, userID);
    }

    /**
     * 获取单位人均工天
     * 获取全社人均工天
     *
     * 计算公式：单位人均工天=单位所有人总工天之和/单位人数
     *
     * @param userIDList 用户ID列表
     * @param year 年份
     * @return
     */
    @Override
    public Double GetDeptOneYearAverageDays(List<String> userIDList, String year) {
        Double result = 0.0;

        //计算单位的所有总工天
        Double deptAllWorkDays = 0.0;
        for (String item : userIDList) {
            deptAllWorkDays = deptAllWorkDays + GetUserOneYearAllWorkDays(item, year);
        }

        //单位人数
        int deptCount = userIDList.size();

        //计算
        result = deptAllWorkDays / deptCount;

        return result;
    }

    /**
     * 获取单位考评总工天 单位 一年 用户工天之和
     * 获取单位实际总工天 单位 一年 用户业务工天之和
     * 获取单位优秀总工天 单位 一年 优秀工天之和
     * 获取单位优秀考评总工天 单位 一年 优秀工天之和
     *
     * @param userIDList 用户ID列表（考核人员列表，总人员列表）
     * @param year 年份
     * @param type 类型：0：获取单位考评总工天；1：获取单位实际总工天；2：获取单位优秀总工天；3：获取单位优秀考评总工天；
     * @return
     */
    @Override
    public Double GetDeptOneYearAllDays(List<String> userIDList, String year, int type) {
        Double result = 0.0;
        for (String item : userIDList) {
            switch (type) {
                case 0:
                    result = result + GetUserOneYearAllWorkDays(item, year);
                    break;
                case 1:
                    result = result + GetUserOneYearBusinessWorkDays(item, year);
                    break;
                case 2:
                    result = result + GetUserOneYearExcellentDays(item, year);
                    break;
                case 3:
                    result = result + GetUserOneYearExcellentDays(item, year);
                    break;
            }
        }
        return result;
    }

    /**
     * 获取单位平均优秀工天
     * 获取全社平均优秀工天
     *
     * 计算公式：单位平均优秀工天=单位优秀总工天/单位总人数
     *
     * @param userIDList  用户ID列表
     * @param year 年份
     * @return
     */
    @Override
    public Double GetDeptOneYearAverageExcellentDays(List<String> userIDList, String year) {

        Double reuslt = 0.0;

        //获取单位总的优秀工天
        Double alldays = 0.0;
        for (String item : userIDList) {
            alldays = alldays + GetUserOneYearExcellentDays(item, year);
        }

        //获取单位总人数
        int peopleCount = userIDList.size();

        //计算单位平均优秀工天
        reuslt = alldays / peopleCount;

        return reuslt;
    }

    /**
     * 获取单位平均优秀率
     * 获取全社平均优秀率
     *
     * 计算公式：单位平均优秀率=单位所有人优秀率之和/单位总人数
     *
     * @param userIDList  部门参与考评用户ID列表
     * @param year 年份
     * @return
     */
    @Override
    public Double GetDeptOneYearAverageExcellent(List<String> userIDList, String year) {
        Double result = 0.0;

        //获取单位总的优秀率之和
        Double allCount = 0.0;
        for (String item : userIDList) {
            allCount = allCount + GetUserOneYearExcellent(item, year);
        }

        //获取单位总人数
        int peopleCount = userIDList.size();

        //计算
        result = allCount / peopleCount;

        return result;
    }

    /**
     * 获取用户一年中技术修改和改成图数量
     *
     * @param userID 用户ID
     * @param year 年份
     * @param type 类型：0：技术修改；1：改成图
     * @param isMain 是否是主要：0：主要负责；1：次要负责
     * @return
     */
    @Override
    public Integer getUserChartEditProductCount(String userID, String year, Integer type, Integer isMain) {

        return checkIndexDao.getUserChartEditProductCount(userID, year, type, isMain);
    }
}
