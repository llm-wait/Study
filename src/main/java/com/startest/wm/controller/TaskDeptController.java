package com.startest.wm.controller;

import com.startest.wm.pojo.sys_dept;
import com.startest.wm.pojo.wm_task_dept;
import com.startest.wm.service.TaskDeptService;
import com.startest.wm.utils.UUIDGeneratorUtil;
import com.startest.wm.utils.customresponse.ExceptionHandleUtil;
import com.startest.wm.utils.customresponse.RestResponse;
import com.startest.wm.utils.customresponse.RestResponseCode;
import com.startest.wm.utils.customresponse.RestResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-04-10-8:29
 * @描述 任务部门分配逻辑控制器
 */
@Controller
@Api(tags = "任务部门分配逻辑操作相关API")
@RequestMapping("/task/taskdept")
@Validated
public class TaskDeptController {
    @Autowired
    private TaskDeptService taskDeptService;

    /*@ApiOperation(value = "任务部门分配信息查询", notes = "任务部门分配信息查询")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/select")
    public RestResponse<List<wm_task_dept>> selectTaskDeptInfo(
            @ApiParam(value = "分配部门id") @RequestParam(name = "task_dept_id", required = false) String taskDeptID,
            @ApiParam(value = "任务索引id") @RequestParam(name = "task_index_id", required = false) String taskIndexID,
            @ApiParam(value = "部门id") @RequestParam(name = "dept_id", required = false) String deptID,
            @ApiParam(value = "部门名称") @RequestParam(name = "dept_name", required = false) String deptName) {
        RestResponse<List<wm_task_dept>> response = null;
        try {
            wm_task_dept taskdept = new wm_task_dept();
            if (taskDeptID != null && taskDeptID.length() > 0) {
                taskdept.setTask_dept_id(taskDeptID);
            }
            if (taskIndexID != null && taskIndexID.length() > 0) {
                taskdept.setTask_index_id(taskIndexID);
            }
            if (deptID != null && deptID.length() > 0) {
                taskdept.setDept_id(deptID);
            }
            if (deptName != null && deptName.length() > 0) {
                taskdept.setDept_name(deptName);
            }
            List<wm_task_dept> list = taskDeptService.selectTaskDeptInfo(taskdept);
            response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, list);
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }*/


   /* @ApiOperation(value = "添加任务部门分配信息（单条）", notes = "添加任务部门分配信息（单条）")
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/addone")
    public RestResponse<String> addOneTaskDeptInfo(
            @ApiParam("任务部门分配信息对象") @RequestBody sys_dept jsonDept,
            @RequestParam(name = "task_index_id", required = false) String taskIndexID) {
        RestResponse<String> response = null;
        try {
            wm_task_dept taskdept = new wm_task_dept();
            taskdept.setTask_dept_id(UUIDGeneratorUtil.getUUIDWithoutLineAndToUpper());
            taskdept.setTask_index_id(taskIndexID);
            taskdept.setDept_id(jsonDept.getDept_id());
            taskdept.setDept_name(jsonDept.getDept_name());
            int res = taskDeptService.insertTaskDeptInfo(taskdept);
            if (res > 0) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "任务部门分配信息添加成功！");
            } else {
                response = RestResponseUtil.createResponse(RestResponseCode.FAIL, "任务部门分配信息添加失败！");
            }
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }*/

    /**任务管理——筹划任务——分配作业室（多个部门分配多个任务）
     * @param jsonList 多个部门集合
     * @return 成功
     */
    @ApiOperation(value = "添加任务部门分配信息（批量,一个任务多个部门）", notes = "添加任务部门分配信息（批量,一个任务多个部门）")
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/addlist")
    public RestResponse<String> addTaskDeptInfoByList(
            @NotNull @ApiParam("任务部门对象列表") @RequestBody List<sys_dept> jsonList) {
        return    taskDeptService.addTaskDeptInfoByList(jsonList);

    }



   /* @ApiOperation(value = "修改任务部门分配信息（一个任务多个部门）", notes = "修改任务部门分配信息（一个任务多个部门）")
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public RestResponse<String> updateTaskDeptInfo(
            @ApiParam("任务部门对象列表") @RequestBody List<sys_dept> jsonList,
            @RequestParam(name = "task_index_id", required = false) String taskIndexID) {
        RestResponse<String> response = null;
        try {
            List<wm_task_dept> list = new ArrayList<>();
            for (sys_dept item : jsonList) {
                wm_task_dept taskdept = new wm_task_dept();
                taskdept.setTask_dept_id(UUIDGeneratorUtil.getUUIDWithoutLineAndToUpper());
                taskdept.setTask_index_id(taskIndexID);
                taskdept.setDept_id(item.getDept_id());
                taskdept.setDept_name(item.getDept_name());
                list.add(taskdept);
            }
            int res = taskDeptService.updateTaskDeptInfo(taskIndexID, list);
            if (res > 0) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "任务部门分配信息更新成功！");
            } else {
                response = RestResponseUtil.createResponse(RestResponseCode.FAIL, "任务部门分配信息更新失败！");
            }
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }*/

    /*@ApiOperation(value = "修改任务部门分配信息（一个部门多个任务）", notes = "修改任务部门分配信息（一个部门多个任务）")
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/updatelist")
    public RestResponse<String> updateTaskDeptInfoByTaskList(
            @ApiParam("部门对象列表，多个indexID，用逗号隔开，赋值如属性：task_index_id") @RequestBody sys_dept jsonDept) {
        RestResponse<String> response = null;
        try {
            List<wm_task_dept> list = new ArrayList<>();
            String[] strArr = jsonDept.getTask_index_id().split(",");
            for (String str : strArr) {
                wm_task_dept taskdept = new wm_task_dept();
                taskdept.setTask_dept_id(UUIDGeneratorUtil.getUUIDWithoutLineAndToUpper());
                taskdept.setTask_index_id(str);
                taskdept.setDept_id(jsonDept.getDept_id());
                taskdept.setDept_name(jsonDept.getDept_name());
                list.add(taskdept);
            }
            int res = taskDeptService.updateTaskDeptInfoByList(list);
            if (res > 0) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "任务部门分配信息更新成功！");
            } else {
                response = RestResponseUtil.createResponse(RestResponseCode.FAIL, "任务部门分配信息更新失败！");
            }
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }

    @ApiOperation(value = "删除任务部门分配信息（通过分配部门id）", notes = "删除任务部门分配信息（通过分配部门id）")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/deletebyid")
    public RestResponse<String> deleteTaskDeptInfoByID(
            @ApiParam(value = "分配部门id", required = true) @RequestParam(name = "task_dept_id") String taskDeptID) {
        RestResponse<String> response = null;
        try {
            int res = taskDeptService.deleteTaskDeptInfoByTaskDeptID(taskDeptID);
            if (res > 0) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "任务部门分配信息删除成功！");
            } else {
                response = RestResponseUtil.createResponse(RestResponseCode.FAIL, "任务部门分配信息删除失败！");
            }
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }*/

   /* @ApiOperation(value = "删除任务部门分配信息（通过任务索引id）", notes = "删除任务部门分配信息（通过任务索引id）")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/deletebyindexid")
    public RestResponse<String> deleteTaskDeptInfoByIndexID(
            @ApiParam(value = "分配部门id", required = true) @RequestParam(name = "task_index_id") String taskIndexID) {
        RestResponse<String> response = null;
        try {
            int res = taskDeptService.deleteTaskDeptInfoByTaskIndexID(taskIndexID);
            if (res > 0) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "任务部门分配信息删除成功！");
            } else {
                response = RestResponseUtil.createResponse(RestResponseCode.FAIL, "任务部门分配信息删除失败！");
            }
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }*/
}
