package com.startest.wm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.startest.wm.enums.EnumFormType;
import com.startest.wm.model.TaskFormModel;
import com.startest.wm.pojo.sys_login;
import com.startest.wm.pojo.wm_form_index;
import com.startest.wm.service.FormIndexService;
import com.startest.wm.utils.GlobalLoginInfoUtil;
import com.startest.wm.utils.UUIDGeneratorUtil;
import com.startest.wm.utils.customresponse.ExceptionHandleUtil;
import com.startest.wm.utils.customresponse.RestResponse;
import com.startest.wm.utils.customresponse.RestResponseCode;
import com.startest.wm.utils.customresponse.RestResponseUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-05-15-9:16
 * @描述 编务表单接口层
 */
@Controller
@Api(tags = "编务业务逻辑API")
@RequestMapping("/task/formindex")
public class FormIndexController {
    @Autowired
    private FormIndexService formIndexService;

    @ApiOperation(value = "获取编务信息列表", notes = "获取编务信息列表")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/select")
    public RestResponse<PageInfo<TaskFormModel>> selectFormIndexInfo(
            @ApiParam("任务名称") @RequestParam(name = "task_name", required = false) String taskName,
            @ApiParam("分配年份") @RequestParam(name = "task_year", required = false) String taskYear,
            @ApiParam("提交单位") @RequestParam(name = "submit_unit", required = false) String submitUnit,
            @ApiParam("审核状态") @RequestParam(name = "examine_state", required = false) String submitState,
            @ApiParam("页码") @RequestParam(name = "page_number", defaultValue = "1") Integer pageNum,
            @ApiParam("页面数据量") @RequestParam(name = "page_size", defaultValue = "50") Integer pageSize) {
        RestResponse<PageInfo<TaskFormModel>> response = null;
        try {
            PageHelper.startPage(pageNum, pageSize);
            TaskFormModel taskForm = new TaskFormModel();
            if (taskName != null && taskName.length() > 0) {
                taskForm.setTask_name(taskName);
            }
            if (taskYear != null && taskYear.length() > 0) {
                taskForm.setTask_year(taskYear);
            }
            if (submitUnit != null && submitUnit.length() > 0) {
                taskForm.setSubmit_unit(submitUnit);
            }
            if (submitState != null && submitState.length() > 0) {
                taskForm.setExamine_state(submitState);
            }
            List<TaskFormModel> otaskList = formIndexService.selectFormInfo(taskForm);
            PageInfo<TaskFormModel> pageInfo = new PageInfo<>(otaskList);
            response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, pageInfo);
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }

    @ApiOperation(value = "获取表单详情", notes = "获取表单详情")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/selectdetail")
    public RestResponse<Object> selectFormDetailInfo(
            @ApiParam("表单类型") @RequestParam(name = "form_type") EnumFormType strType,
            @ApiParam("关联表单ID") @RequestParam(name = "form_id") String strID) {
        RestResponse<Object> response = null;
        try {
            Object obj = formIndexService.selectFormDetaildInfo(strType, strID);
            response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, obj);
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }

    @ApiOperation(value = "添加编务信息列表", notes = "添加编务信息列表")
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public RestResponse<String> addFormIndexInfo(
            HttpServletRequest request,
            @ApiParam("书号申领单对象") @RequestBody wm_form_index jsonIndex) {
        RestResponse<String> response = null;
        try {
            sys_login loginU = GlobalLoginInfoUtil.getLoginUserInfo(request);
            if (loginU != null && loginU.getDept_id() != null && loginU.getDept_id().length() > 0) {
                wm_form_index findex = new wm_form_index();
                findex.setForm_index_id(UUIDGeneratorUtil.getUUIDWithoutLineAndToUpper());
                findex.setIndex_id(jsonIndex.getIndex_id());
                findex.setForm_id(jsonIndex.getForm_id());
                findex.setForm_type(jsonIndex.getForm_type());
                findex.setSubmit_oper(loginU.getLogin_name());
                findex.setSubmit_unit(loginU.getDept_name());
                findex.setSubmit_date(jsonIndex.getSubmit_date());
                findex.setExamine_state("未审核");
                findex.setExamine_opinion("");
                int res = formIndexService.insertFormInfo(findex);
                if (res > 0) {
                    response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "书号申领单信息添加成功！");
                } else {
                    response = RestResponseUtil.createResponse(RestResponseCode.FAIL, "书号申领单信息添加失败！");
                }
            } else {
                response = RestResponseUtil.createResponse(RestResponseCode.FAIL, "获取登录部门信息失败！");
            }
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }

    @ApiOperation(value = "修改审核状态", notes = "修改审核状态")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/updatestate")
    public RestResponse<String> updateFormIndexStateInfo(
            @ApiParam("ID") @RequestParam(name = "form_index_id", required = false) String indexID,
            @ApiParam("审核结果（审核通过、审核未通过）") @RequestParam(name = "examine_state", required = false) String state,
            @ApiParam("审核意见") @RequestParam(name = "examine_opinion", required = false) String opinion) {
        RestResponse<String> response = null;
        try {
            int res = formIndexService.updateFromExamineState(indexID, state, opinion);
            if (res > 0) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "审核状态修改成功！");
            } else {
                response = RestResponseUtil.createResponse(RestResponseCode.FAIL, "审核状态修改失败！");
            }
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }

    @ApiOperation(value = "编务删除", notes = "编务删除")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/delete")
    public RestResponse<String> deleteFormIndexStateInfo(
            @ApiParam("ID") @RequestParam(name = "form_index_id", required = false) String indexID) {
        RestResponse<String> response = null;
        try {
            int res = formIndexService.deleteFormInfo(indexID);
            if (res > 0) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "审核状态删除成功！");
            } else {
                response = RestResponseUtil.createResponse(RestResponseCode.FAIL, "审核状态删除失败！");
            }
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }
}
