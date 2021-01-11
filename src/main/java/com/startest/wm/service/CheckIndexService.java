package com.startest.wm.service;

import com.startest.wm.pojo.wm_check_index;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-07-09-15:48
 * @描述 质检索引逻辑接口
 */
public interface CheckIndexService {
    /**
     * 获取质检索引信息
     * @param cindex
     * @return
     */
    List<wm_check_index> selectCheckIndexInfo(wm_check_index cindex);

    /**
     * 新增质检索引信息
     * @param cindex
     * @return
     */
    int insertCheckIndexInfo(wm_check_index cindex);

    /**
     * 修改质检索引信息
     * @param cindex
     * @return
     */
    int updateCheckIndexInfo(wm_check_index cindex);

    /**
     * 删除质检索引信息
     * @param strID
     * @return
     */
    int deleteCheckIndexInfo(String strID);
}
