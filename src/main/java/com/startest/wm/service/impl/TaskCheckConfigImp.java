package com.startest.wm.service.impl;

import com.startest.wm.enums.EnumCheckConfigType;
import com.startest.wm.service.TaskCheckConfigService;
import org.springframework.stereotype.Service;

/**
 * @author skj
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-09-18-13:29
 * @描述 质控参数配置接口实现
 */
@Service
public class TaskCheckConfigImp implements TaskCheckConfigService {

    @Override
    public Double GetCheckConfigParam(EnumCheckConfigType strType) {
        Double result = 0.0;
        switch (strType) {
            case 评优每工天错漏率上限值:
                break;
            case 评合格每工天错漏率下限值:
                break;
            case 评优秀每工天错漏率上限值_s57:
                break;
            case 评合格每工天错漏率下限值_s57:
                break;
            case 评优秀每工天错漏率上限值_图集插图:
                break;
            case 评合格每工天错漏率下限值_图集插图:
                break;
            case 组长优秀率参数:
                break;
            case 编辑_校对_作业员评优秀比例参数:
                break;
            case 航海产品质量参数_错漏工天k1:
                break;
            case 航海产品质量参数_错漏工天k2:
                break;
            case 水文产品质量参数_错漏工天k1:
                break;
            case 水文产品质量参数_错漏工天k2:
                break;
            case 翻译产品质量参数_错漏工天k1:
                break;
            case 翻译产品质量参数_错漏工天k2:
                break;
            case 编辑差错率_错漏工天m1:
                break;
            case 编辑差错率_错漏工天m2:
                break;
            case 校对审查消灭错漏率n1:
                break;
            case 校对审查消灭错漏率n2:
                break;
            case 产品质量参数k1:
                break;
            case 产品质量参数k2:
                break;
            case 编辑每项差错M1:
                break;
            case 编辑每项差错M2:
                break;
            case 校对审查消灭错漏率m1:
                break;
            case 校对审查消灭错漏率m2:
                break;
            case 书表编辑优秀百分比:
                break;
            case 书表校对优秀百分比:
                break;
            case 书表审查优秀百分比:
                break;
            case 通告编辑优秀百分比:
                break;
            case 通告校对优秀百分比:
                break;
            case 通告审查优秀百分比:
                break;
        }
        return result;
    }

    @Override
    public boolean SetCheckConfigParam(EnumCheckConfigType strType, Double value) {
        boolean result = false;
        switch (strType) {
            case 评优每工天错漏率上限值:
                break;
            case 评合格每工天错漏率下限值:
                break;
            case 评优秀每工天错漏率上限值_s57:
                break;
            case 评合格每工天错漏率下限值_s57:
                break;
            case 评优秀每工天错漏率上限值_图集插图:
                break;
            case 评合格每工天错漏率下限值_图集插图:
                break;
            case 组长优秀率参数:
                break;
            case 编辑_校对_作业员评优秀比例参数:
                break;
            case 航海产品质量参数_错漏工天k1:
                break;
            case 航海产品质量参数_错漏工天k2:
                break;
            case 水文产品质量参数_错漏工天k1:
                break;
            case 水文产品质量参数_错漏工天k2:
                break;
            case 翻译产品质量参数_错漏工天k1:
                break;
            case 翻译产品质量参数_错漏工天k2:
                break;
            case 编辑差错率_错漏工天m1:
                break;
            case 编辑差错率_错漏工天m2:
                break;
            case 校对审查消灭错漏率n1:
                break;
            case 校对审查消灭错漏率n2:
                break;
            case 产品质量参数k1:
                break;
            case 产品质量参数k2:
                break;
            case 编辑每项差错M1:
                break;
            case 编辑每项差错M2:
                break;
            case 校对审查消灭错漏率m1:
                break;
            case 校对审查消灭错漏率m2:
                break;
            case 书表编辑优秀百分比:
                break;
            case 书表校对优秀百分比:
                break;
            case 书表审查优秀百分比:
                break;
            case 通告编辑优秀百分比:
                break;
            case 通告校对优秀百分比:
                break;
            case 通告审查优秀百分比:
                break;
        }
        return result;
    }
}
