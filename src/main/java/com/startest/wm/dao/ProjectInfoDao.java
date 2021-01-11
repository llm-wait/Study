package com.startest.wm.dao;

import com.startest.wm.model.ProjectIndexModel;
import com.startest.wm.pojo.wm_project_distribution;
import com.startest.wm.pojo.wm_project_index;
import com.startest.wm.pojo.wm_project_info;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-06-24-13:17
 * @描述 项目信息表
 */
@Repository
public interface ProjectInfoDao {

    /**
     * @Description: 添加项目索引
     * @Param: [projectIndex] 索引对象
     * @return: int
     **/
    int addProjectIndex(wm_project_index projectIndex);

    /**
     * @Description: 修改项目索引
     * @Param: [scientificIndex] 索引对象
     * @return: int
     **/
    int updateProjectIndex(wm_project_index projectIndex);

    /**
     * @Description: 删除科研项目索引信息
     * @Param: [id] 科研项目索引ID
     * @return: int
     **/
    int deleteProjectIndex(String id);

    /**
     * @Description: 获取项目类型索引列表
     * @Param: [projectIndex]
     * @return: java.util.List<com.startest.wm.pojo.wm_project_index>
     **/
    List<wm_project_index> getProjectIndexList(wm_project_index projectIndex);

    /**
     * @Description: 获取项目索引类型模型
     * @Param: [indexID, proYear]
     * @return: java.util.List<com.startest.wm.model.ProjectIndexModel>
     **/
    List<ProjectIndexModel> getProjectIndexModel(String indexID, Integer proYear);

    /**
     * 获取全部科研项目索引
     *
     * @return
     */
    List<wm_project_index> getAllProjectIndexList();

    /**
     * @Description: 获取全部科研项目索引模型
     * @Param: []
     * @return: java.util.List<com.startest.wm.model.ProjectIndexModel>
     **/  
    List<ProjectIndexModel> getAllProjectIndexModelList();

    /**
     * @Description: 增加科研项目信息
     * @Param: [projectInfo]
     * @return: int
     **/
    int addProjectInfo(wm_project_info projectInfo);

    /**
     * @Description: 修改科研项目信息
     * @Param: [projectInfo]
     * @return: void
     **/
    int updateProjectInfo(wm_project_info projectInfo);

    /**
     * @Description: 根据id删除项目信息
     * @Param: [id]
     * @return: int
     **/
    int deleteProjectById(String id);

    /**
     * @Description: 根据id获取科研项目信息
     * @Param: [id] 项目id
     * @return: com.startest.wm.pojo.wm_project_info
     **/
    wm_project_info getProjectInfoById(String id);

    /**
     * @Description: 根据年份和索引分类ID查询项目列表
     * @Param: [proYear, indexID]
     * @return: java.util.List<com.startest.wm.pojo.wm_project_info>
     **/
    List<wm_project_info> getProjectInfoByIndexAndYear(Integer proYear, String indexID);

    /**
     * @Description: 自定义条件查询项目列表
     * @Param: [year, projectName, scheduleStatus, projectCycle]
     * @return: java.util.List<com.startest.wm.pojo.wm_project_info>
     **/
    List<wm_project_info> getProjectInfoByDefine(
            @Param("projectYear") Integer projectYear,
            @Param("projectName") String projectName,
            @Param("projectState") String projectState,
            @Param("projectCycle") String projectCycle);


    /**
     * 作业室主任查询科研列表
     *
     * @param projectInfo 科研详情对象
     * @param deptId      作业室部门id
     * @return 科研列表
     */
    List<wm_project_info> zysSelect(@Param("deptId") String deptId, @Param("projectInfo") wm_project_info projectInfo);

    /**
     * 添加科研任务，分配人员信息
     *
     * @param wmProjectDistribution 分配信息对象
     */
    void addAssignPersonnelInformation(wm_project_distribution wmProjectDistribution);

    /**
     * 科研任务，根据科研科目id,查询分配的部门id
     *
     * @param projectId 科研科目id
     * @return 分配部门id集合
     */
    Set<String> getDistributionDepartment(String projectId);

    /**
     * 科研任务，根据科研科目id，查询部门人员的名称
     *
     * @param projectId 科研科目id
     * @return 部门人员名称
     */
    List<Map<String, String>> getListOfPeople(@Param("projectId") Set<String> projectId);

    /**
     * 删除指定分配信息
     *
     * @param disId 分配id
     * @author Administrator
     * @date 1040 2020/12/2 000
     **/
    void delTaskAssignment(String disId);



}
