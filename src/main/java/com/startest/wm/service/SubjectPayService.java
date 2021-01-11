package com.startest.wm.service;

import com.startest.wm.pojo.wm_subject_pay;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-06-29-13:36
 * @描述 科目信息支持服务接口
 */
public interface SubjectPayService {
    /**
     * 检索
     * @param pay
     * @return
     */
    List<wm_subject_pay> getSubjectPayInfoList(wm_subject_pay pay);

    wm_subject_pay getSubjectPayInfoByID(String payID);
    /**
     * 新增
     * @param pay
     * @return
     */
    int insertSubjectPayInfo(wm_subject_pay pay);

    /**
     * 修改
     * @param pay
     * @return
     */
    int updateSubjectPayInfo(wm_subject_pay pay);

    /**
     * 删除
     * @param strID
     * @return
     */
    int deleteSubjectPayInfo(String strID);
}
