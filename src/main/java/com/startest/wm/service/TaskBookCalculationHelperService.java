package com.startest.wm.service;

import com.startest.wm.enums.EnumTaskChartDistributionType;
import org.springframework.stereotype.Service;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-09-18-13:46
 * @描述 书表质控评审计算帮助接口
 */
public interface TaskBookCalculationHelperService {

    /**
     * 计算书表作业员每工天缺陷值
     * 计算公式：错漏数量*错漏权重数/作业员工天数
     *
     * @param taskIndexID 任务索引ID
     * @param disContent  分配的书表工作内容 例如：第一章，第二章等
     * @return
     */
    Double BookWorkerDefactValueByWorkday(String taskIndexID, String disContent);

    /**
     * 书表 组校、校对、审查、验收消灭错漏率
     * 计算公式：（组校、校对、审查、验收）之一错漏数量*错漏权重数/所有错漏数量*错漏权重数
     *
     * @param taskIndexID 任务索引ID
     * @param type        检查类型（组校、校对、审查、验收）
     * @return
     */
    Double BookCheckerPassAwayDefact(String taskIndexID, EnumTaskChartDistributionType type, String disContent);

    /**
     * 书表产品每工天缺陷值计算
     * 计算公式：错漏数量*错漏权重/单组作业员工天数
     *
     * @param taskIndexID 任务索引ID
     * @return
     */
    Double BookProductDefactValue(String taskIndexID);

    /**
     * 书表综合缺陷值计算
     * 计算公式：未知
     *
     * @param taskIndexID
     * @return
     */
    Double BookProductComprehensiveDefactValue(String taskIndexID);
}
