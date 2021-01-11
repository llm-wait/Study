package com.startest.wm.dao;

import com.startest.wm.pojo.sys_login;
import com.startest.wm.pojo.wm_form_book;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-05-25-14:41
 * @描述 图书申领单SQL映射
 */
@Repository
public interface FormBookDao {

    /**
     * 查询图书申领单信息
     * @param book
     * @return
     */
    List<wm_form_book> selectFormBookListInfo(wm_form_book book);

    /**
     * 通过任务索引ID获取图书申领单信息
     * @param strID　任务id
     * @return  书号申领单 信息
     */
    List<wm_form_book> selectFormBookInfoByTaskIndexID(@Param("strID") String strID);

    /**
     * 新增图书申领单信息
     * @param book
     * @return
     */
    int addFormBookInfo(wm_form_book book);

    /**
     * 修改图书申领单信息
     * @param book
     * @return
     */
    int updateFormBookInfo(wm_form_book book);

    /**
     * 删除图书申领单信息
     * @param strID
     * @return
     */
    int deleteFormBookInfo(@Param("strID") String strID);
}
