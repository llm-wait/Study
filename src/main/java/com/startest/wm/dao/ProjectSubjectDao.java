package com.startest.wm.dao;

import com.startest.wm.pojo.wm_project_subject;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-06-28-9:23
 * @描述 科研项目数据库访问接口
 */
@Repository
public interface ProjectSubjectDao {
    /**
     * 检索
     * @param sub
     * @return
     */
    List<wm_project_subject> selectProjectSubjectInfoList(wm_project_subject sub);

    wm_project_subject getProjectSubjectInfoByID(String subID);

    /**
     * 新增
     * @param sub
     * @return
     */
    int insertProjectSubjectInfo(wm_project_subject sub);

    /**
     * 修改
     * @param sub
     * @return
     */
    int updateProjectSubjectInfo(wm_project_subject sub);

    /**
     * 删除
     * @param strID
     * @return
     */
    int deleteProjectSubjectInfo(@Param("strID") String strID);
}
