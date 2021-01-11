package com.startest.wm.service;

import com.startest.wm.pojo.sys_login;
import com.startest.wm.pojo.wm_form_data;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-07-15-10:12
 * @描述 数据工作单逻辑接口
 */
public interface FormDataService {

    /**
     * 获取数据工作单信息
     * @param data
     * @return
     */
    List<wm_form_data> getFormDataInfoList(wm_form_data data);

    /**
     * 通过任务索引ID获取数据工作单信息
     * @param strID　任务id
     * @return 数据工作单
     */
    wm_form_data selectFormDataInfoByTaskIndexID(String strID);

    /**
     * 插入数据工作单信息
     * @param data
     * @return
     */
    int insertFormDataInfo(wm_form_data data, sys_login login);

    /**
     * 修改数据工作单信息
     * @param data
     * @return
     */
    int updateFormDataInfo(wm_form_data data);

    /**
     * 删除数据工作单信息
     * @param data
     * @return
     */
    int deleteFormDataInfo(String data);
}
