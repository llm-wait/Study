package com.startest.wm.controller;

import com.startest.wm.pojo.wm_task_check;
import com.startest.wm.service.TaskCheckService;
import com.startest.wm.utils.UUIDGeneratorUtil;
import com.startest.wm.utils.customresponse.ExceptionHandleUtil;
import com.startest.wm.utils.customresponse.RestResponse;
import com.startest.wm.utils.customresponse.RestResponseCode;
import com.startest.wm.utils.customresponse.RestResponseUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-05-11-15:30
 * @描述 任务质检控制器
 */
@Controller
@Api(tags = "任务质检控制器")
@RequestMapping("/task/taskcheck")
public class TaskCheckController {
    @Autowired
    private TaskCheckService taskCheckService;

    @ApiOperation(value = "任务质检信息检索", notes = "任务质检信息检索")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/select")
    public RestResponse<List<wm_task_check>> selectTaskCheckInfo(
            @ApiParam(value = "质检ID") @RequestParam(name = "check_id", required = false) String checkID,
            @ApiParam(value = "任务索引ID") @RequestParam(name = "task_id", required = false) String indexID,
            @ApiParam(value = "操作者ID") @RequestParam(name = "user_id", required = false) String userID,
            @ApiParam(value = "分配内容(海图：数字图，纸图，S57图)(书表：第一章)") @RequestParam(name = "distribution_type", required = false) String diType) {
        RestResponse<List<wm_task_check>> response = null;
        try {
            wm_task_check taskCheck = new wm_task_check();
            if (checkID != null && checkID.length() > 0) {
                taskCheck.setCheck_id(checkID);
            }
            if (indexID != null && indexID.length() > 0) {
                taskCheck.setTask_id(indexID);
            }
            if (userID != null && userID.length() > 0) {
                taskCheck.setUser_id(userID);
            }
            if (diType != null && diType.length() > 0) {
                taskCheck.setDistribution_type(diType);
            }
            List<wm_task_check> checkList = taskCheckService.getTaskCheckListInfo(taskCheck);
            response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, checkList);
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }

    @ApiOperation(value = "通过任务索引ID获取对象", notes = "通过任务索引ID获取对象")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/selectbyid")
    public RestResponse<wm_task_check> getTaskCheckByID(
            @ApiParam(value = "任务索引ID") @RequestParam(name = "task_index_id") String taskID,
            @ApiParam(value = "人员ID") @RequestParam(name = "user_id") String userID,
            @ApiParam(value = "分配内容(海图：数字图，纸图，S57图)(书表：第一章)") @RequestParam(name = "distribution_type", required = false) String diType) {
        RestResponse<wm_task_check> response = null;
        try {
            wm_task_check taskCheck = new wm_task_check();
            taskCheck.setTask_id(taskID);
            //taskCheck.setCheck_oper(strOper);
            taskCheck.setDistribution_type(diType);
            taskCheck.setUser_id(userID);
            List<wm_task_check> list = taskCheckService.getTaskCheckListInfo(taskCheck);
            if (list != null && list.size() > 0) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, list.get(0));
            } else {
                response = RestResponseUtil.error(300, "任务质检信息检索失败！");
            }
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }

//TODO新增的质控查询接口
    @ApiOperation(value = "根据执行单id查询质检信息", notes = "根据执行单id查询质检信息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/getTaskCheckByDisID")
    public RestResponse<wm_task_check> getTaskCheckByDisID(
           @RequestParam(name = "distribution_id", required = true) String distributionId) {
        RestResponse<wm_task_check> response = null;
        try{
            wm_task_check wmTaskCheck = taskCheckService.getTaskCheckByDisID(distributionId);
            if (wmTaskCheck != null ) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, wmTaskCheck);
            } else {
                response = RestResponseUtil.error(300, "任务质检信息检索失败！");
            }
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }

    //新增加的质控修改接口
    @ApiOperation(value = "根据执行单id修改质检信息", notes = "根据执行单id修改质检信息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/updataTaskCheckByDisID")
    public RestResponse<wm_task_check> updataTaskCheckByDisID(
            @ApiParam("任务质检对象")@RequestBody wm_task_check wmTaskCheck) {
        RestResponse<wm_task_check> response = null;
        try{
             taskCheckService.updataTaskCheckByDisID(wmTaskCheck);
             return RestResponseUtil.createResponse(RestResponseCode.SUCCESS, wmTaskCheck);
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }



    @ApiOperation(value = "新增任务质检信息", notes = "新增任务质检信息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public RestResponse<String> insertTaskCheckInfo(
            @ApiParam("任务质检对象") @RequestBody wm_task_check jsonTaskCheck) {
        RestResponse<String> response = null;
        try {
            wm_task_check taskCheck = new wm_task_check();

            //依据ID， 判断该质检信息是否已存在
            String strID = jsonTaskCheck.getCheck_id();
            if (strID != null && strID.length() > 0) {
                taskCheck.setCheck_id(strID);
                taskCheck.setCheck_oper(jsonTaskCheck.getCheck_oper());
                taskCheck.setCheck_maptable(jsonTaskCheck.getCheck_maptable());
                taskCheck.setCheck_error1(jsonTaskCheck.getCheck_error1());
                taskCheck.setCheck_error2(jsonTaskCheck.getCheck_error2());
                taskCheck.setCheck_error3(jsonTaskCheck.getCheck_error3());
                taskCheck.setCheck_error4(jsonTaskCheck.getCheck_error4());
                taskCheck.setCheck_lost(jsonTaskCheck.getCheck_lost());
                taskCheck.setCheck_mylevel(jsonTaskCheck.getCheck_mylevel());
                taskCheck.setCheck_mapquality(jsonTaskCheck.getCheck_mapquality());
                taskCheck.setRemark(jsonTaskCheck.getRemark());
                taskCheck.setUser_id(jsonTaskCheck.getUser_id());
                int res = taskCheckService.updateTaskCheckInfo(taskCheck);
                if (res > 0) {
                    response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "任务质检信息修改成功！");
                } else {
                    response = RestResponseUtil.createResponse(RestResponseCode.FAIL, "任务质检信息修改失败！");
                }
            } else {
                taskCheck.setCheck_id(UUIDGeneratorUtil.getUUIDWithoutLineAndToUpper());
                taskCheck.setTask_id(jsonTaskCheck.getTask_id());
                taskCheck.setDistribution_type(jsonTaskCheck.getDistribution_type());
                taskCheck.setCheck_oper(jsonTaskCheck.getCheck_oper());
                taskCheck.setCheck_maptable(jsonTaskCheck.getCheck_maptable());
                taskCheck.setCheck_error1(jsonTaskCheck.getCheck_error1());
                taskCheck.setCheck_error2(jsonTaskCheck.getCheck_error2());
                taskCheck.setCheck_error3(jsonTaskCheck.getCheck_error3());
                taskCheck.setCheck_error4(jsonTaskCheck.getCheck_error4());
                taskCheck.setCheck_lost(jsonTaskCheck.getCheck_lost());
                taskCheck.setCheck_mylevel(jsonTaskCheck.getCheck_mylevel());
                taskCheck.setRemark(jsonTaskCheck.getRemark());
                taskCheck.setCheck_mapquality(jsonTaskCheck.getCheck_mapquality());
                taskCheck.setUser_id(jsonTaskCheck.getUser_id());
                int res = taskCheckService.insertTaskCheckInfo(taskCheck);
                if (res > 0) {
                    response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "任务质检信息添加成功！");
                } else {
                    response = RestResponseUtil.createResponse(RestResponseCode.FAIL, "任务质检信息添加成功！");
                }
            }
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }

    @ApiOperation(value = "修改任务质检信息", notes = "修改任务质检信息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public RestResponse<String> updateTaskCheckInfo(
            @ApiParam("任务质检对象") @RequestBody wm_task_check jsonTaskCheck) {
        RestResponse<String> response = null;
        try {
            wm_task_check taskCheck = new wm_task_check();
            taskCheck.setCheck_id(jsonTaskCheck.getCheck_id());
            taskCheck.setCheck_oper(jsonTaskCheck.getCheck_oper());
            taskCheck.setCheck_maptable(jsonTaskCheck.getCheck_maptable());
            taskCheck.setCheck_error1(jsonTaskCheck.getCheck_error1());
            taskCheck.setCheck_error2(jsonTaskCheck.getCheck_error2());
            taskCheck.setCheck_error3(jsonTaskCheck.getCheck_error3());
            taskCheck.setCheck_error4(jsonTaskCheck.getCheck_error4());
            taskCheck.setCheck_lost(jsonTaskCheck.getCheck_lost());
            taskCheck.setCheck_mylevel(jsonTaskCheck.getCheck_mylevel());
            taskCheck.setRemark(jsonTaskCheck.getRemark());
            taskCheck.setUser_id(jsonTaskCheck.getUser_id());
            int res = taskCheckService.updateTaskCheckInfo(taskCheck);
            if (res > 0) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "任务质检信息修改成功！");
            } else {
                response = RestResponseUtil.createResponse(RestResponseCode.FAIL, "任务质检信息修改失败！");
            }
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }

    @ApiOperation(value = "删除任务质检信息", notes = "删除任务质检信息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/delete")
    public RestResponse<String> deleteTaskCheckInfo(
            @ApiParam(value = "质检ID") @RequestParam(name = "check_id") String checkID) {
        RestResponse<String> response = null;
        try {
            int res = taskCheckService.deleteTaskCheckInfo(checkID);
            if (res > 0) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "任务质检信息删除成功！");
            } else {
                response = RestResponseUtil.createResponse(RestResponseCode.FAIL, "任务质检信息删除成功！");
            }
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }
}
