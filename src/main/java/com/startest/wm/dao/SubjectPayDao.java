package com.startest.wm.dao;

import com.startest.wm.pojo.wm_subject_pay;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-06-28-18:01
 * @描述 项目支出数据接口层
 */
@Repository
public interface SubjectPayDao {
    /**
     * 检索
     * @param pay
     * @return
     */
    List<wm_subject_pay> selectSubjectPayInfoList(wm_subject_pay pay);

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
    int deleteSubjectPayInfo(@Param("strID") String strID);
}
