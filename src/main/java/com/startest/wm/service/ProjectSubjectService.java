package com.startest.wm.service;

import com.startest.wm.pojo.wm_project_subject;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-06-28-9:40
 * @描述 科研项目业务逻辑服务接口
 */
public interface ProjectSubjectService {
    /**
     * 检索
     * @param sub
     * @return
     */
    List<wm_project_subject> getProjectSubjectInfoList(wm_project_subject sub);

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
    int deleteProjectSubjectInfo(String strID);
}
