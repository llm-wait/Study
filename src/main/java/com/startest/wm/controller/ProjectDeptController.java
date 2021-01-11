package com.startest.wm.controller;

import com.startest.wm.model.ProjectDeptModel;
import com.startest.wm.pojo.wm_project_dept;
import com.startest.wm.service.ProjectDeptService;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-06-24-16:07
 * @描述 项目分配部门业务逻辑接口
 */
@Controller
@Api(tags = "科研项目分配部门业务逻辑接口API")
@RequestMapping("/task/projectdept")
public class ProjectDeptController {

    private static final Logger log = LoggerFactory.getLogger(ProjectDeptController.class);

    @Autowired
    private ProjectDeptService projectDeptService;


    @ApiOperation(value = "获取项目分配部门信息列表", notes = "获取项目分配部门信息列表")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/select")
    public RestResponse<List<wm_project_dept>> selectProjectDeptInfoList(
            @ApiParam("项目分配部门表ID") @RequestParam(name = "project_dept_id", required = false) String proDeptID,
            @ApiParam("项目ID") @RequestParam(name = "project_id", required = false) String proID,
            @ApiParam("部门ID") @RequestParam(name = "dept_id", required = false) String deptID) {
        RestResponse<List<wm_project_dept>> response = null;
        try {
            wm_project_dept proDept = new wm_project_dept();
            proDept.setProject_id(proDeptID);
            proDept.setProject_id(proID);
            proDept.setDept_id(deptID);
            List<wm_project_dept> proList = projectDeptService.getProjectDeptList(proDept);
            response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, proList);
        } catch (Exception e) {
            log.error("获取项目分配部门信息列表异常", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "获取项目分配部门信息的树形列表", notes = "获取项目分配部门信息的树形列表")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/selecttree")
    public RestResponse<List<wm_project_dept>> selectProjectDeptTreeInfoList(
            @ApiParam("项目分配部门表ID") @RequestParam(name = "project_dept_id", required = false) String proDeptID,
            @ApiParam("项目ID") @RequestParam(name = "project_id", required = false) String proID,
            @ApiParam("部门ID") @RequestParam(name = "dept_id", required = false) String deptID) {
        RestResponse<List<wm_project_dept>> response = null;
        try {
            wm_project_dept proDept = new wm_project_dept();
            proDept.setProject_id(proDeptID);
            proDept.setProject_id(proID);
            proDept.setDept_id(deptID);
            List<wm_project_dept> proList = projectDeptService.getProjectDeptTreeList(proDept);
            response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, proList);
        } catch (Exception e) {
            log.error("获取项目分配部门信息的树形列表异常", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "添加项目分配部门信息(批量)", notes = "添加项目分配部门信息(批量)")
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    @ResponseBody
    public RestResponse<String> addProjectDeptInfo(
            @ApiParam("添加项目分配部门对象,只赋值【project_id】和【dept_id】两个字段") @RequestBody List<wm_project_dept> jsonProList) {
        RestResponse<String> response = null;
        try {
            List<wm_project_dept> list = new ArrayList<>();
            for (wm_project_dept item : jsonProList) {
                if (!projectDeptService.isProjectDept(item.getProject_id(), item.getDept_id())) {
                    wm_project_dept proDept = new wm_project_dept();
                    proDept.setId(UUIDGeneratorUtil.getUUIDWithoutLineAndToUpper());
                    proDept.setProject_id(item.getProject_id());
                    proDept.setDept_id(item.getDept_id());
                    list.add(proDept);
                } else {
                    return RestResponseUtil.note( "已分配部门的项目无法再次分配！");
                }
            }
            int res = projectDeptService.insertProjectDeptInfo(list);
            if (res > 0) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "项目分配部门信息添加成功！");
            } else {
                response = RestResponseUtil.createResponse(RestResponseCode.NOTE, "项目分配部门信息添加失败！");
            }
        } catch (Exception e) {
            log.error("添加项目分配部门信息异常", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "修改项目分配部门信息(批量)", notes = "修改项目分配部门信息(批量)")
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    @ResponseBody
    public RestResponse<String> updateProjectDeptInfo(
            @ApiParam("项目分配部门数据模型") @RequestBody ProjectDeptModel jsonmodel) {
        RestResponse<String> response = null;
        try {
            int res = projectDeptService.updateProjectDeptInfo(jsonmodel);
            if (res > 0) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "项目分配部门信息修改成功！");
            } else {
                response = RestResponseUtil.createResponse(RestResponseCode.NOTE, "项目分配部门信息修改失败！");
            }
        } catch (Exception e) {
            log.error("修改项目分配部门信息异常", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "删除项目分配部门信息", notes = "删除项目分配部门信息")
    @RequestMapping(method = RequestMethod.POST, value = "/delete")
    @ResponseBody
    public RestResponse<String> deleteProjectDeptInfo(
            @ApiParam("项目分配部门对象") @RequestBody wm_project_dept jsonProDept) {
        RestResponse<String> response = null;
        try {
            int res = projectDeptService.deleteProjectDeptInfo(jsonProDept);
            if (res > 0) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "项目分配部门信息删除成功！");
            } else {
                response = RestResponseUtil.createResponse(RestResponseCode.NOTE, "项目分配部门信息删除失败！");
            }
        } catch (Exception e) {
            log.error("删除项目分配部门信息异常", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }
}
