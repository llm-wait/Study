package com.startest.wm.controller;

import com.startest.wm.pojo.wm_task_info;
import com.startest.wm.service.TaskInfoService;
import com.startest.wm.utils.UUIDGeneratorUtil;
import com.startest.wm.utils.customresponse.RestResponse;
import com.startest.wm.utils.customresponse.RestResponseCode;
import com.startest.wm.utils.customresponse.RestResponseUtil;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Administrator
 * @公司 北京星天科技
 * @创建时间 2020-04-10-8:30
 * @描述 任务单逻辑控制器
 */
@Controller
@Api(tags = "任务单信息逻辑操作相关API")
@RequestMapping("/task/taskinfo")
@Validated
public class TaskInfoController {
    private final TaskInfoService taskInfoService;

    public TaskInfoController(TaskInfoService taskInfoService) {
        this.taskInfoService = taskInfoService;
    }

    @ApiOperation(value = "检索任务单信息", notes = "检索任务单信息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/select")
    public RestResponse<List<wm_task_info>> selectTaskInfoList(
            @ApiParam(value = "任务单id") @RequestParam(name = "task_id", required = false) String taskId,
            @ApiParam(value = "任务索引id") @RequestParam(name = "task_index_id", required = false) String taskIndexId,
            @ApiParam(value = "任务种类id") @RequestParam(name = "task_class_id", required = false) String taskClassId,
            @ApiParam(value = "任务编号") @RequestParam(name = "task_code", required = false) String taskCode,
            @ApiParam(value = "任务下达时间") @RequestParam(name = "task_start_date", required = false) String taskStartDate,
            @ApiParam(value = "任务完成期限") @RequestParam(name = "task_end_date", required = false) String taskEndDate,
            @ApiParam(value = "任务级别") @RequestParam(name = "task_level", required = false) String taskLevel,
            @ApiParam(value = "任务类型") @RequestParam(name = "task_type", required = false) String taskType,
            @ApiParam(value = "任务名称") @RequestParam(name = "task_name", required = false) String taskName) {
            wm_task_info taskinfo = new wm_task_info();
                taskinfo.setTask_info_id(taskId);
                taskinfo.setTask_index_id(taskIndexId);
                taskinfo.setTask_class_id(taskClassId);
                taskinfo.setTask_code(taskCode);
                taskinfo.setTask_start_date(taskStartDate);
                taskinfo.setTask_end_date(taskEndDate);
                taskinfo.setTask_level(taskLevel);
                taskinfo.setTask_type_content(taskType);
                taskinfo.setTask_name(taskName);

           return RestResponseUtil.success(taskInfoService.selectTaskInfo(taskinfo));
    }

    /**生产任务——任务信息
     * @param taskId
     * @return
     */
    @ApiOperation(value = "通过Id获取任务单信息", notes = "通过Id获取任务单信息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/getbyid")
    public RestResponse<wm_task_info> getTaskInfoById(
        @NotNull @ApiParam(value = "任务索引Id", required = true) @RequestParam(name = "task_index_id") String taskId) {
            wm_task_info task = new wm_task_info();
            task.setTask_index_id(taskId);
            List<wm_task_info> list = taskInfoService.selectTaskInfo(task);
            if (list != null && list.size() > 0) {
                return RestResponseUtil.createResponse(RestResponseCode.SUCCESS, list.get(0));
            } else {
                return RestResponseUtil.error(300, "查询结果为空，获取值失败！");
            }
    }

    /**生产任务——任务信息——添加／修改任务信息
     * @param jsonTask
     * @return
     */
    @ApiOperation(value = "添加任务单信息", notes = "添加任务单信息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public RestResponse<String> addTaskInfo(@ApiParam("任务单对象") @NotNull @RequestBody wm_task_info jsonTask) {

        String strId = jsonTask.getTask_info_id();

            if (strId == null || strId.isEmpty()) {
                jsonTask.setTask_info_id(UUIDGeneratorUtil.getUUIDWithoutLineAndToUpper());
                int res = taskInfoService.insertTaskInfo(jsonTask);
                if (res > 0) {
                    return RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "任务单信息添加成功！");
                } else {
                    return RestResponseUtil.createResponse(RestResponseCode.FAIL, "任务单信息添加失败！");
                }
            }

            int res = taskInfoService.updateTaskInfo(jsonTask);
            if (res > 0) {
                return RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "任务单信息修改成功！");
            } else {
                return RestResponseUtil.createResponse(RestResponseCode.FAIL, "任务单信息修改失败！");
            }
    }

    /**todo 和上面接口一样，可以去掉
     * @param jsonTask 任务对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改任务单信息", notes = "修改任务单信息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public RestResponse<String> updateTaskInfo(@ApiParam("任务单对象") @NotNull @RequestBody wm_task_info jsonTask) {
            int res = taskInfoService.updateTaskInfo(jsonTask);
            if (res > 0) {
                return RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "任务单信息修改成功！");
            } else {
                return RestResponseUtil.createResponse(RestResponseCode.FAIL, "任务单信息修改失败！");
            }
    }

    @ApiOperation(value = "删除任务单信息（通过任务单Id）", notes = "删除任务单信息（通过任务单Id）")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/deletebyid")
    public RestResponse<String> deleteTaskInfoByTaskId(
        @NotNull    @ApiParam(value = "任务单Id", required = true) @RequestParam(name = "task_id") String taskId) {
            int res = taskInfoService.deleteTaskInfoByTaskInfoId(taskId);
            if (res > 0) {
                return  RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "任务单信息删除成功！");
            } else {
                return  RestResponseUtil.createResponse(RestResponseCode.FAIL, "任务单信息删除失败！");
            }
    }

    @ApiOperation(value = "删除任务单信息（通过任务索引Id）", notes = "删除任务单信息（通过任务索引Id）")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/deletebyindex")
    public RestResponse<String> deleteTaskInfoByTaskIndexId(
      @NotNull      @ApiParam(value = "任务索引id", required = true) @RequestParam(name = "task_index_id") String taskIndexId) {
            int res = taskInfoService.deleteTaskInfoByTaskIndexId(taskIndexId);
            if (res > 0) {
                return RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "任务单信息删除成功！");
            } else {
                return RestResponseUtil.createResponse(RestResponseCode.FAIL, "任务单信息删除失败！");
            }
    }
}
