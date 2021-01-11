package com.startest.wm.service;

import com.startest.wm.pojo.sys_login;
import com.startest.wm.pojo.wm_form_book;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.PrimitiveIterator;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-07-15-10:11
 * @描述 标准书号申领单逻辑接口
 */
public interface FormBookService {

    /**
     * 获取标准书号申请单信息
     * @param book
     * @return
     */
    List<wm_form_book> getFormBookInfoList(wm_form_book book);

    /**
     * 通过任务索引ID获取图书申领单信息
     * @param strId　任务id
     * @return 图书申领单信息
     */
    wm_form_book selectFormBookInfoByTaskIndexID(String strId);

    /**
     * 插入标准书号申请单信息
     * @param book
     * @return
     */
    int insertFormBookInfo(wm_form_book book, sys_login login);

    /**
     * 修改标准书号申请单信息
     * @param book
     * @return
     */
    int updateFormBookInfo(wm_form_book book);

    /**
     * 删除标准书号申请单信息
     * @param strID
     * @return
     */
    int deleteFormBookInfo(String strID);
}
