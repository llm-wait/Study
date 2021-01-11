package com.startest.wm.controller;

import com.startest.wm.pojo.wm_project_subject;
import com.startest.wm.service.ProjectSubjectService;
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

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-06-28-9:43
 * @描述 科研项目接口类
 */
@Controller
@Api(tags = "科研科目信息业务逻辑API")
@RequestMapping("/task/projectsubject")
public class ProjectSubjectController {

    private static final Logger log = LoggerFactory.getLogger(ProjectSubjectController.class);

    @Autowired
    private ProjectSubjectService projectSubjectService;

    @ApiOperation(value = "获取科研项目信息列表", notes = "获取科研项目信息列表")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/select")
    public RestResponse<List<wm_project_subject>> selectProjectSubjectInfoList(
            @ApiParam("科研项目ID") @RequestParam(name = "subject_id", required = false) String subID,
            @ApiParam("科研ID") @RequestParam(name = "project_id", required = false) String proID,
            @ApiParam("项目名称") @RequestParam(name = "subject_name", required = false) String subName) {
        RestResponse<List<wm_project_subject>> response = null;
        try {
            wm_project_subject subject = new wm_project_subject();
            subject.setSubject_id(subID);
            subject.setProject_id(proID);
            subject.setSubject_name(subName);
            List<wm_project_subject> subList = projectSubjectService.getProjectSubjectInfoList(subject);
            response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, subList);
        } catch (Exception e) {
            log.error("获取科研项目信息列表异常", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "获取科研项目信息对象", notes = "获取科研项目信息对象")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/selectbyid")
    public RestResponse<wm_project_subject> selectProjectSubjectInfoList(
            @ApiParam("科研项目ID") @RequestParam(name = "subject_id") String subID) {
        RestResponse<wm_project_subject> response = null;
        try {
            wm_project_subject subject = projectSubjectService.getProjectSubjectInfoByID(subID);
            response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, subject);
        } catch (Exception e) {
            log.error("获取科研项目信息对象异常", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "添加科研项目信息", notes = "添加科研项目信息")
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    @ResponseBody
    public RestResponse<String> addProjectInfo(
            @ApiParam("科研项目信息对象") @RequestBody wm_project_subject subject) {
        RestResponse<String> response = null;
        try {
            subject.setSubject_id(UUIDGeneratorUtil.getUUIDWithoutLineAndToUpper());
            int res = projectSubjectService.insertProjectSubjectInfo(subject);
            if (res > 0) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "科研项目信息添加成功！");
            } else {
                response = RestResponseUtil.note("科研项目信息添加失败！");
            }
        } catch (Exception e) {
            log.error("添加科研项目信息异常", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "修改科研项目信息", notes = "修改科研项目信息")
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    @ResponseBody
    public RestResponse<String> updateProjectInfo(
            @ApiParam("科研项目信息对象") @RequestBody wm_project_subject subject) {
        RestResponse<String> response = null;
        try {
            int res = projectSubjectService.updateProjectSubjectInfo(subject);
            if (res > 0) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "科研项目信息修改成功！");
            } else {
                response = RestResponseUtil.createResponse(RestResponseCode.NOTE, "科研项目信息修改失败！");
            }
        } catch (Exception e) {
            log.error("修改科研项目信息异常", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "删除科研项目信息", notes = "删除科研项目信息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/deletebyid")
    public RestResponse<String> deleteProjectSubjectInfoByID(
            @RequestParam(name = "subject_id") String subID) {
        RestResponse<String> response = null;
        try {
            int res = projectSubjectService.deleteProjectSubjectInfo(subID);
            if (res > 0) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "科研项目信息删除成功！");
            } else {
                response = RestResponseUtil.createResponse(RestResponseCode.FAIL, "科研项目信息删除失败！");
            }
        } catch (Exception e) {
            log.error("删除科研项目信息异常", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }
}
