package com.startest.wm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.startest.wm.config.exception.LoginException;
import com.startest.wm.model.ProjectIndexModel;
import com.startest.wm.pojo.sys_login;
import com.startest.wm.pojo.wm_project_distribution;
import com.startest.wm.pojo.wm_project_index;
import com.startest.wm.pojo.wm_project_info;
import com.startest.wm.service.ProjectInfoService;
import com.startest.wm.utils.GlobalLoginInfoUtil;
import com.startest.wm.utils.UUIDGeneratorUtil;
import com.startest.wm.utils.customresponse.RestResponse;
import com.startest.wm.utils.customresponse.RestResponseCode;
import com.startest.wm.utils.customresponse.RestResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-06-24-14:04
 * @描述 科研项目信息接口类
 */

@Api(tags = "科研项目信息业务逻辑API")
@RestController
@RequestMapping("/task/ky")
public class ProjectInfoController {

    private static final Logger log = LoggerFactory.getLogger(ProjectInfoController.class);

    @Autowired
    ProjectInfoService projectInfoService;

    @ApiOperation(value = "获取项目索引目录树", notes = "获取项目索引目录树")
    @GetMapping(value = "/getProjectIndexList")
    public RestResponse<List<ProjectIndexModel>> getProjectIndexList(
            @ApiParam(value = "项目年份") @RequestParam(name = "project_year", required = false) Integer proYear
    ) {
        RestResponse<List<ProjectIndexModel>> response = null;
        try {
            //如果年份为空，默认查询当前年份
            if (proYear == null) {
                proYear = Calendar.getInstance().get(Calendar.YEAR);
            }
            List<ProjectIndexModel> list = projectInfoService.getProjectIndexModelList(proYear);
            response = RestResponseUtil.success(list);
        } catch (Exception e) {
            log.error("获取项目列表异常", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "作业室科研项目列表", notes = "作业室科研项目列表")
    @GetMapping(value = "/select")
    public RestResponse<PageInfo<wm_project_info>> select(
            HttpServletRequest request,
            @ApiParam(value = "项目年份", required = true) @RequestParam(name = "project_year") String proYear,
            @ApiParam(value = "项目名称") @RequestParam(name = "project_name", required = false) String proName,
            @ApiParam(value = "项目状态(待处理，处理中，已完成)") @RequestParam(name = "project_state", required = false) String proState,
            @ApiParam(value = "页码") @RequestParam(name = "page_number", defaultValue = "1") Integer pageNum,
            @ApiParam(value = "页面数据量") @RequestParam(name = "page_size", defaultValue = "50") Integer pageSize
    ) {
        RestResponse<PageInfo<wm_project_info>> response = null;
        try {
            //获取登录部门ID
            sys_login loginUserInfo = null;
            loginUserInfo = GlobalLoginInfoUtil.getLoginUserInfo(request);

            wm_project_info projectInfo = new wm_project_info();
            projectInfo.setProject_year(Integer.parseInt(proYear));
            projectInfo.setProject_name(proName);
            projectInfo.setProject_state(proState);

            PageHelper.startPage(pageNum, pageSize);
            List<wm_project_info> list = projectInfoService.selectZysProjectInfo(loginUserInfo.getDept_id(), projectInfo);
            PageInfo<wm_project_info> pageInfo = new PageInfo<>(list);
            response = RestResponseUtil.success(pageInfo);
        } catch (LoginException e) {
            log.error("获取登录信息异常", e);
            response = RestResponseUtil.note("系统异常！");
        } catch (Exception e) {
            log.error("查询项目详细信息异常", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "添加科研项目类型", notes = "添加科研项目类型 ")
    @PostMapping(value = "/addProjectIndex")
    public RestResponse<String> addProjectIndex(
            @ApiParam(value = "项目类型名称", required = true) @RequestParam(value = "name") String name
    ) {
        RestResponse<String> response = null;
        try {
            wm_project_index projectIndex = new wm_project_index();
            projectIndex.setIndex_name(name);
            boolean res = projectInfoService.isProjectIndexExist(projectIndex);
            if (res) {
                return RestResponseUtil.note("项目类型已存在！");
            }
            int count = projectInfoService.addProjectIndex(name);
            if (count > 0) {
                response = RestResponseUtil.success("添加成功");
            } else {
                response = RestResponseUtil.note("添加失败！");
            }
        } catch (Exception e) {
            log.error("添加项目或科目列表异常", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "修改科研项目类型", notes = "修改科研项目类型 ")
    @PostMapping(value = "/updateProjectIndex")
    public RestResponse<String> updateProjectIndex(
            @ApiParam(value = "项目类型对象", required = true) @RequestBody wm_project_index projectIndex
    ) {
        RestResponse<String> response = null;
        try {
            boolean res = projectInfoService.isProjectIndexExist(projectIndex);
            if (res) {
                return RestResponseUtil.note("项目类型已存在！");
            }
            int count = projectInfoService.updateProjectIndex(projectIndex);
            if (count > 0) {
                response = RestResponseUtil.success("修改成功");
            } else {
                response = RestResponseUtil.note("修改失败！");
            }
        } catch (Exception e) {
            log.error("修改科研项目类型异常", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "删除科研项目类型或项目信息", notes = "删除科研项目类型或项目信息 ")
    @PostMapping(value = "/delete")
    public RestResponse<String> delete(
            @ApiParam(value = "索引ID或项目ID", required = true) @RequestParam(name = "id") String id,
            @ApiParam(value = "节点类型", required = true) @RequestParam(name = "nodeType") String type
    ) {
        RestResponse<String> response = null;
        try {
            if (type.equals("分类")) {
                //删除项目分类前判断项目类型下是否有项目存在，无论哪一年的项目，有则无法删除
                List<wm_project_info> list = projectInfoService.selectProjectInfo(null, id, type);
                if (list.size() > 0) {
                    return RestResponseUtil.note("此类型下已有项目，无法删除！");
                }
                int count = projectInfoService.deleteProjectIndex(id);
                if (count > 0) {
                    response = RestResponseUtil.success("删除成功");
                } else {
                    response = RestResponseUtil.note("删除失败！");
                }
            } else {
                //删除项目前先判断此项目是否已经下发，如果下发了则不能删除,否则要删除所有与此项目相关的记录
                wm_project_info projectInfo = projectInfoService.selectProjectInfoByID(id);
                if (!"待处理".equals(projectInfo.getProject_state())) {
                    return RestResponseUtil.note("项目进行中或已完成，无法删除！");
                }
                int count = projectInfoService.deleteProjectById(id);
                if (count > 0) {
                    response = RestResponseUtil.success("删除成功！");
                } else {
                    response = RestResponseUtil.note("删除失败！");
                }
            }
        } catch (Exception e) {
            log.error("删除科研项目类型或项目信息异常", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "添加科研项目信息", notes = "添加科研项目信息")
    @PostMapping(value = "/addProject")
    public RestResponse<String> addProject(
            @ApiParam("项目对象") @RequestBody wm_project_info projectInfo
    ) {
        RestResponse<String> response = null;
        try {
            if (projectInfo.getIndex_id() == null || "".equals(projectInfo.getIndex_id())) {
                response = RestResponseUtil.note("请先选择项目分类！");
            } else {
                projectInfo.setId(UUIDGeneratorUtil.getUUIDWithoutLineAndToUpper());
                int count = projectInfoService.addProjectInfo(projectInfo);
                if (count > 0) {
                    response = RestResponseUtil.success("添加成功");
                } else {
                    response = RestResponseUtil.note("添加失败！");
                }
            }
        } catch (Exception e) {
            log.error("添加科研项目异常", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "修改科研项目信息", notes = "修改科研项目信息")
    @PostMapping(value = "/updateProject")
    public RestResponse<String> updateProject(
            @ApiParam("项目对象") @RequestBody wm_project_info projectInfo
    ) {
        RestResponse<String> response = null;
        try {
            if (projectInfo.getIndex_id() == null || "".equals(projectInfo.getIndex_id())) {
                response = RestResponseUtil.note("请先选择项目分类！");
            } else {
                int count = projectInfoService.updateProjectInfo(projectInfo);
                if (count > 0) {
                    response = RestResponseUtil.success("添加成功");
                } else {
                    response = RestResponseUtil.note("添加失败！");
                }
            }
        } catch (Exception e) {
            log.error("修改科研项目异常", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "根据ID查询项目信息", notes = "根据ID查询项目信息")
    @GetMapping(value = "/getProjectByID")
    public RestResponse<wm_project_info> getScientificById(
            @ApiParam(value = "项目ID", required = true) @RequestParam(name = "id") String id
    ) {
        try {
            wm_project_info projectInfo = projectInfoService.selectProjectInfoByID(id);
            return RestResponseUtil.success(projectInfo);
        } catch (Exception e) {
            log.error("根据ID查询项目信息异常", e);
            return RestResponseUtil.note("系统异常！");
        }
    }


    @ApiOperation(value = "根据年份和分类索引查询项目列表", notes = "根据年份和分类索引查询项目列表")
    @GetMapping(value = "/getProjectList")
    public RestResponse<PageInfo<wm_project_info>> getProjectList(
            @ApiParam(value = "项目年份") @RequestParam(name = "project_year", required = false) Integer proYear,
            @ApiParam(value = "节点ID", required = true) @RequestParam(name = "node_id") String nodeID,
            @ApiParam(value = "节点类型", required = true) @RequestParam(name = "node_type") String nodeType,
            @ApiParam(value = "页码") @RequestParam(name = "page_number", defaultValue = "1") Integer pageNum,
            @ApiParam(value = "页面数据量") @RequestParam(name = "page_size", defaultValue = "50") Integer pageSize
    ) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<wm_project_info> list = projectInfoService.selectProjectInfo(proYear, nodeID, nodeType);
            PageInfo<wm_project_info> pageInfo = new PageInfo<>(list);
            return RestResponseUtil.createResponse(RestResponseCode.SUCCESS, pageInfo);
        } catch (Exception e) {
            log.error("查询项目详细信息异常", e);
            return RestResponseUtil.note("系统异常！");
        }
    }


    @ApiOperation(value = "自定义查询项目详细信息", notes = "自定义查询项目详细信息")
    @GetMapping(value = "/getProjectByDefine")
    public RestResponse<PageInfo<wm_project_info>> getProjectByDefine(
            @ApiParam(value = "项目年份") @RequestParam(name = "project_year", required = false) Integer projectYear,
            @ApiParam(value = "项目名称") @RequestParam(name = "project_name", required = false) String projectName,
            @ApiParam(value = "进度状态") @RequestParam(name = "schedule_status", required = false) String scheduleStatus,
            @ApiParam(value = "项目周期") @RequestParam(name = "project_cycle", required = false) String projectCycle,
            @ApiParam(value = "页码") @RequestParam(name = "page_number", defaultValue = "1") Integer pageNum,
            @ApiParam(value = "页面数据量") @RequestParam(name = "page_size", defaultValue = "50") Integer pageSize
    ) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<wm_project_info> list = projectInfoService.selectProjectInfo(projectYear, projectName, scheduleStatus, projectCycle);
            PageInfo<wm_project_info> pageInfo = new PageInfo<>(list);
            return RestResponseUtil.createResponse(RestResponseCode.SUCCESS, pageInfo);
        } catch (Exception e) {
            log.error("查询项目详细信息异常", e);
            return RestResponseUtil.note("系统异常！");
        }
    }


    @ApiOperation(value = "科研任务,查询是否分配部门", notes = "科研任务,查询是否分配部门")
    @GetMapping(value = "/isDistributionDepartment")
    public RestResponse<String> isDistributionDepartment(
            @NotNull @ApiParam(value = "科目ID", required = true) @RequestParam(name = "project_id") String projectId
    ) {
        Set<String> deptIds = projectInfoService.getDistributionDepartment(projectId);
        if (deptIds == null || deptIds.isEmpty()) {
            return RestResponseUtil.note("请先分配作业室");
        }
        StringBuilder reDeptId = new StringBuilder();
        for (String deptId : deptIds) {
            reDeptId.append(deptId).append(",");
        }
        return RestResponseUtil.success("已分配作业室", reDeptId.toString());
    }


    @ApiOperation(value = "科研任务，获取进度中的人员", notes = "科研任务，获取进度中的人员")
    @GetMapping(value = "/getListOfPeople")
    public RestResponse<List<Map<String, String>>> getListOfPeople(
            @NotNull @ApiParam(value = "科目ID") @RequestParam(name = "project_id") String projectId
    ) {
        List<Map<String, String>> names = projectInfoService.getListOfPeople(projectId);
        return RestResponseUtil.success(names);
    }


    @ApiOperation(value = "增加科研任务分配信息表", notes = "增加科研任务分配信息表")
    @PostMapping(value = "/addAssignPersonnelInformation")
    public RestResponse<String> addAssignPersonnelInformation(
            @ApiParam("项目列表对象") wm_project_distribution wmProjectDistribution
    ) {
        wmProjectDistribution.setDis_id(UUIDGeneratorUtil.getUUIDWithoutLineAndToLower());
        projectInfoService.addAssignPersonnelInformation(wmProjectDistribution);
        return RestResponseUtil.success("添加成功");
    }


    @ApiOperation(value = "删除科研任务分配信息", notes = "删除科研任务分配信息")
    @PostMapping(value = "/delTaskAssignment")
    public RestResponse<String> delTaskAssignment(
            @NotNull @ApiParam(value = "分配信息id") @RequestParam(name = "dis_id") String disId
    ) {
        projectInfoService.delTaskAssignment(disId);
        return RestResponseUtil.success("删除成功");
    }
}
