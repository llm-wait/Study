package com.startest.wm.dao;

import com.startest.wm.pojo.wm_form_book;
import com.startest.wm.pojo.wm_form_data;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-05-25-14:41
 * @描述 数据单SQL映射
 */
@Repository
public interface FormDataDao {

    /**
     * 查询数据工作单信息
     * @param book
     * @return
     */
    List<wm_form_data> selectFormDataListInfo(wm_form_data book);

    /**
     * 通过任务索引ID获取数据工作单信息
     * @param strID
     * @return
     */
    List<wm_form_data> selectFormDataInfoByTaskIndexID(@Param("strID") String strID);

    /**
     * 新增数据工作单信息
     * @param book
     * @return
     */
    int addFormDataInfo(wm_form_data book);

    /**
     * 修改数据工作单信息
     * @param book
     * @return
     */
    int updateFormDataInfo(wm_form_data book);

    /**
     * 删除数据工作单信息
     * @param strID
     * @return
     */
    int deleteFormDataInfo(@Param("strID") String strID);
}
