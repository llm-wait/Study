package com.startest.wm.dao;

import com.startest.wm.pojo.wm_project_template;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-06-29-13:21
 * @描述 项目模板信息数据库访问层接口
 */
@Repository
public interface ProjectTemplateDao {
    /**
     * 检索
     *
     * @param template
     * @return
     */
    List<wm_project_template> selectProjectTemplateInfoList(wm_project_template template);

    /**
     * @Description: 根据ID获取科研项目模板
     * @Param: [id]
     * @return: com.startest.wm.pojo.wm_project_template
     **/
    wm_project_template getProjectTemplateInfoByID(String id);

    /**
     * 新增
     *
     * @param template
     * @return
     */
    int insertProjectTemplateInfo(wm_project_template template);

    /**
     * 批量新增模板信息
     *
     * @param list
     * @return
     */
    int insertProjectTemplateByList(@Param("list") List<wm_project_template> list);

    /**
     * 修改
     *
     * @param template
     * @return
     */
    int updateProjectTemplateInfo(wm_project_template template);

    /**
     * 更新模板文件
     *
     * @param template_id
     * @param filePath
     * @return
     */
    int updateProjectTemplateFile(
            @Param("template_id") String template_id,
            @Param("filename") String filename,
            @Param("template_update") String template_update,
            @Param("template_upoper") String template_upoper,
            @Param("filePath") String filePath);

    /**
     * 删除
     *
     * @param strID
     * @return
     */
    int deleteProjectTemplateInfo(@Param("strID") String strID);
}
