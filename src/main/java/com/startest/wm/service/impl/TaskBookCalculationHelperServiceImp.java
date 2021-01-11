package com.startest.wm.service.impl;

import com.itextpdf.awt.geom.CubicCurve2D;
import com.startest.wm.dao.TaskBookCalculationHelperDao;
import com.startest.wm.dao.TaskDistributeDao;
import com.startest.wm.enums.EnumTaskChartDistributionType;
import com.startest.wm.pojo.wm_task_check;
import com.startest.wm.service.TaskBookCalculationHelperService;
import com.startest.wm.service.TaskCheckConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName:startestwm
 * @PackageName:com.startest.wm.service.impl
 * @ClassName:TaskBookCalculationHelperServiceImp
 * @Description: 书表质控评审计算实现类
 * @author: skj
 * @date 2020/9/26  16:47
 */
@Service
public class TaskBookCalculationHelperServiceImp implements TaskBookCalculationHelperService {

    @Autowired
    private TaskBookCalculationHelperDao taskBookCalculationHelperDao;

    @Autowired
    private TaskDistributeDao taskDistributeDao;
    /**
     * 计算书表作业员每工天缺陷值
     * 计算公式：错漏数量*错漏权重数/作业员工天数
     *
     * @param taskIndexID 任务索引ID
     * @param disContent 书表分配的章节内容 例如：第一章，第二章等
     * @return
     */
    @Override
    public Double BookWorkerDefactValueByWorkday(String taskIndexID,String disContent) {
        //错漏数量
        List<wm_task_check> list = taskBookCalculationHelperDao.selectTaskCheckByIndexId(taskIndexID,disContent);
        //求出错漏总权重
        Double numberOfError = BookErrorCalculate(list);
        //作业员工天数
        Double bookAllWordDays = taskDistributeDao.getBookAllWordDays(taskIndexID);

        return  numberOfError/bookAllWordDays;
    }

    /**
     * 书表 组校、校对、审查、验收消灭错漏率
     * 计算公式：（校对、审查、验收）之一错漏数量*错漏权重数/所有错漏数量*错漏权重数
     *
     * @param taskIndexID 任务索引ID
     * @param type        检查类型（校对、审查、验收）
     * @param disContent 书表分配的章节内容 例如：第一章，第二章等
     * @return
     */
    @Override
    public Double BookCheckerPassAwayDefact(String taskIndexID, EnumTaskChartDistributionType type,String disContent) {

        //根据对应职位，查询的错漏情况
       List<wm_task_check>  list=  taskBookCalculationHelperDao.selectTaskcheckByTaskIdandDisType(taskIndexID, type,disContent);
       //本职位错漏权重
       Double aDouble = BookErrorCalculate(list);


        //错漏总数量
        List<wm_task_check> list1 = taskBookCalculationHelperDao.selectTaskCheckByIndexId(taskIndexID,disContent);
        //求出错漏总权重
        Double numberOfError = BookErrorCalculate(list1);

        return aDouble/numberOfError;
    }

    /**
     * 书表产品每工天缺陷值计算
     * 计算公式：错漏数量*错漏权重/单组作业员工天数
     *
     * @param taskIndexID 任务索引ID
     * @return
     */
    @Override
    public Double BookProductDefactValue(String taskIndexID) {
        Double result = 0.0;
        //错漏数量
        List<wm_task_check> list = taskBookCalculationHelperDao.selectTaskCheckByIndexId(taskIndexID, null);
        //求出错漏总权重
        Double numberOfError = BookErrorCalculate(list);
        //作业员工天数
        Double bookAllWordDays = taskDistributeDao.getBookAllWordDays(taskIndexID);
        //计算
        result = numberOfError / bookAllWordDays;
        return result;
    }

    /**
     * 书表综合缺陷值计算
     * 计算公式：未知
     *
     * @param taskIndexID
     * @return
     */
    @Override
    public Double BookProductComprehensiveDefactValue(String taskIndexID) {
        return 0.0;
    }





    /**书表错漏总权重计算
     * @param list
     * @return
     */
    public Double BookErrorCalculate ( List<wm_task_check> list) {
        Double  numberOfError=0.0;
        if (list == null || list.size()<1) {
            return numberOfError;
        }

        for (wm_task_check wm_task_check : list) {
            numberOfError += wm_task_check.getCheck_error1() * TaskCheckConfigService.BOOK_SERIOUS +
                    wm_task_check.getCheck_error2() * TaskCheckConfigService.BOOK_LESS_SERIOUS +
                    wm_task_check.getCheck_error3() * TaskCheckConfigService.BOOK_COMMONLY +
                    wm_task_check.getCheck_error4() * TaskCheckConfigService.BOOK_SLIGHT +
                    wm_task_check.getCheck_mylevel();
        }
        return numberOfError;
    }
}
