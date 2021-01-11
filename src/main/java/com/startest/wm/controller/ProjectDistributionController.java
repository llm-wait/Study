package com.startest.wm.controller;

import com.startest.wm.pojo.sys_login;
import com.startest.wm.pojo.wm_project_distribution;
import com.startest.wm.service.ProjectDistributionService;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-06-24-14:58
 * @描述 项目分配接口
 */
@Controller
@Api(tags = "科研项目分配人员业务逻辑API")
@RequestMapping("/task/projectdistribute")
public class ProjectDistributionController {

    private static final Logger log = LoggerFactory.getLogger(ProjectDistributionController.class);

    @Autowired
    private ProjectDistributionService projectDistributionService;

    @ApiOperation(value = "获取项目分配信息列表", notes = "获取项目分配信息列表")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/select")
    public RestResponse<List<wm_project_distribution>> selectProjectDistributionInfoList(
            @ApiParam("项目分配ID") @RequestParam(name = "dis_id", required = false) String disID,
            @ApiParam("部门ID") @RequestParam(name = "dept_id", required = false) String dept_id,
            @ApiParam("项目ID") @RequestParam(name = "project_id", required = false) String proID,
            @ApiParam("用户ID") @RequestParam(name = "user_id", required = false) String userID) {
        RestResponse<List<wm_project_distribution>> response = null;
        try {
            wm_project_distribution proDis = new wm_project_distribution();
            proDis.setDis_id(disID);
            proDis.setDept_id(dept_id);
            proDis.setProject_id(proID);
            proDis.setUser_id(userID);
            List<wm_project_distribution> proList = projectDistributionService.getProjectDistributionList(proDis);
            response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, proList);
        } catch (Exception e) {
            log.error("获取项目分配信息列表异常", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "获取项目的部门分配信息列表", notes = "获取项目的部门分配信息列表")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/selectdept")
    public RestResponse<List<wm_project_distribution>> selectProjecDistributiontInfoListByDept1(
            HttpServletRequest request,
            @ApiParam("项目分配ID") @RequestParam(name = "dis_id", required = false) String disID,
            @ApiParam("项目ID") @RequestParam(name = "project_id", required = false) String proID,
            @ApiParam("用户ID") @RequestParam(name = "user_id", required = false) String userID) {
        RestResponse<List<wm_project_distribution>> response = null;
        try {
            sys_login login = GlobalLoginInfoUtil.getLoginUserInfo(request);
            if (login != null && login.getDept_id().length() > 0) {
                wm_project_distribution proDis = new wm_project_distribution();
                proDis.setDept_id(login.getDept_id());
                proDis.setDis_id(disID);
                proDis.setProject_id(proID);
                proDis.setUser_id(userID);
                List<wm_project_distribution> proList = projectDistributionService.getProjectDistributionList(proDis);
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, proList);
            } else {
                response = RestResponseUtil.error(300, "获取部门ID失败");
            }
        } catch (Exception e) {
            log.error("获取项目的部门分配信息列表异常", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "通过ID获取项目分配信息", notes = "通过ID获取项目分配信息")
    @RequestMapping(method = RequestMethod.GET, value = "/selectbyid")
    @ResponseBody
    public RestResponse<wm_project_distribution> getProjectDistributionInfoByID(
            @RequestParam(name = "dis_id", required = false) String disID,
            @RequestParam(name = "project_id", required = false) String projectId) {
        RestResponse<wm_project_distribution> response = null;
        try {
            wm_project_distribution proDis = new wm_project_distribution();
            proDis.setDis_id(disID);
            proDis.setProject_id(projectId);
            List<wm_project_distribution> disList = projectDistributionService.getProjectDistributionList(proDis);
            if (disList != null && disList.size() > 0) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, disList.get(0));
            } else {
                response = RestResponseUtil.note("查询失败，查询结果为空！");
            }
        } catch (Exception e) {
            log.error("通过ID获取项目分配信息异常", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "添加项目分配信息", notes = "添加项目分配信息")
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    @ResponseBody
    public RestResponse<String> addProjectDistributionInfo(
            HttpServletRequest request,
            @ApiParam("添加项目分配信息对象") @RequestBody wm_project_distribution jsonPro) {
        RestResponse<String> response = null;
        try {
            sys_login login = GlobalLoginInfoUtil.getLoginUserInfo(request);
            if (login != null && login.getDept_id().length() > 0) {
                jsonPro.setDis_id(UUIDGeneratorUtil.getUUIDWithoutLineAndToUpper());
                jsonPro.setDept_id(login.getDept_id());
                jsonPro.setUser_id(login.getId());
                int res = projectDistributionService.insertProjectDistribution(jsonPro);
                if (res > 0) {
                    response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "项目分配信息添加成功！");
                } else {
                    response = RestResponseUtil.note("项目分配信息添加失败！");
                }
            } else {
                response = RestResponseUtil.error(300, "获取部门ID失败");
            }
        } catch (Exception e) {
            log.error("添加项目分配信息异常", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "修改项目分配信息", notes = "修改项目分配信息")
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    @ResponseBody
    public RestResponse<String> updateProjectDistributionInfo(
            HttpServletRequest request,
            @ApiParam("修改项目分配信息对象") @RequestBody wm_project_distribution jsonPro) {
        RestResponse<String> response = null;
        try {
            sys_login login = GlobalLoginInfoUtil.getLoginUserInfo(request);
            if (login != null && login.getDept_id().length() > 0) {
                jsonPro.setDept_id(login.getDept_id());
                int res = projectDistributionService.updateProjectDistribution(jsonPro);
                if (res > 0) {
                    response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "项目分配信息修改成功！");
                } else {
                    response = RestResponseUtil.note("项目分配信息修改失败！");
                }
            } else {
                response = RestResponseUtil.error(300, "获取部门ID失败");
            }
        } catch (Exception e) {
            log.error("修改项目分配信息异常", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "删除项目分配信息", notes = "删除项目分配信息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/deletebyid")
    public RestResponse<String> deleteProjectDistributionInfoByID(
            @RequestParam(name = "dis_id") String disID) {
        RestResponse<String> response = null;
        try {
            int res = projectDistributionService.deleteProjectDistribution(disID);
            if (res > 0) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "项目分配信息删除成功！");
            } else {
                response = RestResponseUtil.note("项目分配信息删除失败！");
            }
        } catch (Exception e) {
            log.error("删除项目分配信息异常", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }
}
