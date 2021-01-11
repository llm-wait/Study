package com.startest.wm.service;

import com.startest.wm.model.ProjectIndexModel;
import com.startest.wm.pojo.wm_project_distribution;
import com.startest.wm.pojo.wm_project_index;
import com.startest.wm.pojo.wm_project_info;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-06-24-14:00
 * @描述 项目信息逻辑接口
 */
public interface ProjectInfoService {

    /**
     * @Description: 添加项目类型
     * @Param: [name] 类型
     * @return: int
     **/
    int addProjectIndex(String name);

    /**
     * @Description: 修改项目类型
     * @Param: [projectIndex] 项目索引对象
     * @return: int
     **/
    int updateProjectIndex(wm_project_index projectIndex);

    /**
     * @Description: 删除项目索引
     * @Param: [id] 项目索引id
     * @return: int
     **/
    int deleteProjectIndex(String id);

    /**
     * @Description: 判断项目类型是否存在
     * @Param: [projectIndex] 项目索引对象
     * @return: boolean
     **/
    boolean isProjectIndexExist(wm_project_index projectIndex);

    /**
     * @Description: 获取科研项目索引信息列表
     * @Param: [proYear] 项目年份
     * @return: java.util.List<com.startest.wm.pojo.wm_project_index>
     **/  
    //List<wm_project_index> getProjectIndexList(Integer proYear);

    /**
     * @Description: 获取科研项目索引信息模型列表
     * @Param: [proYear] 项目年份
     * @return: java.util.List<ProjectIndexModel>
     **/  
    List<ProjectIndexModel> getProjectIndexModelList(Integer proYear);

    /**
     * @Description: 增加项目信息
     * @Param: [projectInfo] 项目信息对象
     * @return: int
     **/  
    int addProjectInfo(wm_project_info projectInfo);

    /**
     * @Description: 修改项目信息
     * @Param: [projectInfo] 项目信息对象
     * @return: int
     **/  
    int updateProjectInfo(wm_project_info projectInfo);

    /**
     * @Description: 根据项目id删除项目
     * @Param: [id]
     * @return: int
     **/
    int deleteProjectById(String id);

    /**
     * @Description: 根据项目ID查询项目信息
     * @Param: [id]
     * @return: com.startest.wm.pojo.wm_project_info
     **/  
    wm_project_info selectProjectInfoByID(String id);

    /**
     * @Description: 根据索引和年份查询项目信息
     * @Param: [proYear, nodeID,nodeType]年份,节点id，节点类型
     * @return: java.util.List<com.startest.wm.pojo.wm_project_info>
     **/
    List<wm_project_info> selectProjectInfo(Integer proYear, String nodeID,String nodeType);

    /**
     * @Description: 自定义查询项目信息列表
     * @Param: [projectYear, projectName, scheduleStatus, projectCycle]年份，项目名称，进度状态，项目周期
     * @return: java.util.List<com.startest.wm.pojo.wm_project_info>
     **/
    List<wm_project_info> selectProjectInfo(Integer projectYear, String projectName, String scheduleStatus, String projectCycle);





    /**
     * @Description: 作业室查询科研项目信息列表
     * @Param: [deptId, projectInfo] 作业室id,科研项目对象
     * @return: java.util.List<com.startest.wm.pojo.wm_project_info>
     **/
    List<wm_project_info> selectZysProjectInfo(String deptId, wm_project_info projectInfo);


    /**
     * 添加科研任务，人员分配信息
     *
     * @param wmProjectDistribution 分配信息对象
     */
    void addAssignPersonnelInformation(wm_project_distribution wmProjectDistribution);

    /**
     * 科研任务，根据科研科目id ,查询分配的部门id
     *
     * @param projectId 科研科目id
     * @return 部门id列表
     */
    Set<String> getDistributionDepartment(String projectId);

    /**
     * 科研任务，根据科研科目id，查询部门人员的名称
     *
     * @param projectId 科研科目id
     * @return 部门人员名称
     */
    List<Map<String, String>> getListOfPeople(String projectId);

    /**
     * 删除指定分配信息
     *
     * @param disId 分配信息id
     * @date 10:30 2020/12/2 0002
     **/
    void delTaskAssignment(String disId);

    
}
