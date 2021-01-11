package com.startest.wm.service;

import com.startest.wm.pojo.wm_project_template;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-06-29-13:38
 * @描述 项目模板文件服务接口
 */
public interface ProjectTemplateService {
    /**
     * 检索
     * @param template
     * @return
     */
    List<wm_project_template> getProjectTemplateInfoList(wm_project_template template);

    /**
     * 根据ID获取科研项目模板
     * @param id
     * @return
     */
    wm_project_template getProjectTemplateInfoByID(String id);

    /**
     * 新增
     * @param template
     * @return
     */
    int insertProjectTemplateInfo(wm_project_template template);

    /**
     * 批量新增模板信息
     * @param list
     * @return
     */
    int insertProjectTemplateByList(List<wm_project_template> list);

    /**
     * 修改
     * @param template
     * @return
     */
    int updateProjectTemplateInfo(wm_project_template template);

    /**
     * @Description: 更新模板文件
     * @Param: [template_id, template_name, template_update, template_upoper, filePath]
     * @return: int
     **/  
    int updateProjectTemplateFile(String template_id,String filename,String template_update,String template_upoper,String filePath);

    /**
     * 删除
     * @param strID
     * @return
     */
    int deleteProjectTemplateInfo(String strID);
}
