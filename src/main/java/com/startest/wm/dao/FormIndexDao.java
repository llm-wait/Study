package com.startest.wm.dao;

import com.startest.wm.model.TaskFormModel;
import com.startest.wm.pojo.wm_form_index;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-05-14-14:12
 * @描述 编务信息数据库映射接口
 */
@Repository
public interface FormIndexDao {

    /**
     * 查询编务信息表
     * @param form
     * @return
     */
    List<TaskFormModel> selectFormInfo(TaskFormModel form);

    /**
     * 添加编务信息
     * @param form
     * @return
     */
    int insertFormInfo(wm_form_index form);

    /**
     * 修改编务信息
     * @param form
     * @return
     */
    int updateFormInfo(wm_form_index form);

    /**
     * 修改审核状态
     * @param strID
     * @param strState
     * @param strOpinion
     * @return
     */
    int updateFromExamineState(@Param("strID") String strID,
                               @Param("strState") String strState,
                               @Param("strOpinion") String strOpinion);

    /**
     * 删除编务信息
     * @param strID
     * @return
     */
    int deleteFormInfo(@Param("strID") String strID);
}
