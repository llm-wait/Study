package com.startest.wm.service;

import com.startest.wm.enums.EnumFormType;
import com.startest.wm.model.TaskFormModel;
import com.startest.wm.pojo.wm_form_index;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-05-15-9:09
 * @描述 编务服务层接口
 */
public interface FormIndexService {
    /**
     * 查询编务信息表
     *
     * @param form
     * @return
     */
    List<TaskFormModel> selectFormInfo(TaskFormModel form);

    /**
     * 获取表单详情
     *
     * @param type
     * @param formid
     * @return
     */
    Object selectFormDetaildInfo(EnumFormType type, String formid);

    /*
     * 添加编务信息
     * @param form
     * @return
     */
    int insertFormInfo(wm_form_index form);

    /**
     * 修改编务信息
     *
     * @param form
     * @return
     */
    int updateFormInfo(wm_form_index form);

    /**
     * 修改审核状态
     *
     * @param strID
     * @param strState
     * @param strOpinion
     * @return
     */
    int updateFromExamineState(String strID, String strState, String strOpinion);

    /**
     * 删除编务信息
     *
     * @param strID
     * @return
     */
    int deleteFormInfo(String strID);
}
