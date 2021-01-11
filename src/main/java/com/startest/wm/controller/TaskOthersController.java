package com.startest.wm.controller;

import com.startest.wm.constant.TaskClassId;
import com.startest.wm.pojo.OthersTaskHelper;
import com.startest.wm.pojo.wm_task_others;
import com.startest.wm.service.TaskOthersService;
import com.startest.wm.utils.TaskOthersExcelOperationUtil;
import com.startest.wm.utils.UUIDGeneratorUtil;
import com.startest.wm.utils.customresponse.RestResponse;
import com.startest.wm.utils.customresponse.RestResponseCode;
import com.startest.wm.utils.customresponse.RestResponseUtil;
import io.swagger.annotations.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author 杨世凯
 * @公司 北京星天科技
 * @创建时间 2020-05-11-13:30
 * @描述 应急保障任务控制器
 */
@Controller
@Api(tags = "应急保障逻辑操作相关API接口")
@RequestMapping("/task/taskother")
public class TaskOthersController {

    protected static final Logger log = LoggerFactory.getLogger(TaskOthersController.class);

    private final TaskOthersService taskOthersService;

    public TaskOthersController(TaskOthersService taskOthersService) {
        this.taskOthersService = taskOthersService;
    }

    @ApiOperation(value = "应急保障任务信息检索", notes = "应急保障任务信息检索")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/select")
    public RestResponse<List<wm_task_others>> selectTaskOthersInfo(
            @ApiParam( "其他任务Id") @RequestParam(name = "otask_id", required = false) String othersId,
            @ApiParam("任务索引Id") @RequestParam(name = "index_id", required = false) String indexId,
            @ApiParam("任务名称") @RequestParam(name = "otask_name", required = false) String othersName) {
        wm_task_others taskOthers = new wm_task_others();
        taskOthers.setOtask_id(othersId);
        taskOthers.setIndex_id(indexId);
        taskOthers.setOtask_name(othersName);
        return RestResponseUtil.success(taskOthersService.getTaskOthersInfo(taskOthers));
    }


    @ApiOperation(value = "通过Id获取对象", notes = "通过Id获取对象")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/selectbyid")
    public RestResponse<wm_task_others> getTaskOthersById(
        @NotNull @ApiParam("其他任务Id") @RequestParam(name = "otask_id") String othersId) {

            wm_task_others taskOthers = new wm_task_others();
            taskOthers.setOtask_id(othersId);
            List<wm_task_others> list = taskOthersService.getTaskOthersInfo(taskOthers);
            if (list != null && list.size() > 0) {
                return RestResponseUtil.createResponse(RestResponseCode.SUCCESS, list.get(0));
            } else {
                return  RestResponseUtil.error(300, "应急保障任务信息检索失败！");
            }
    }

    /**任务管理——筹划任务——获取应急保障任务编号
     * @return 按已有数据和时间生成一个唯一的序号
     */
    @ApiOperation(value = "获取应急保障任务默认编号", notes = "获取应急保障任务默认编号")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/selectcode")
    public RestResponse<String> getTaskOthersCode() {
            String strCode = taskOthersService.getTaskOtherCode();
            if (strCode != null && strCode.length() > 0) {
                return RestResponseUtil.createResponse(RestResponseCode.SUCCESS, strCode);
            } else {
                return RestResponseUtil.error(300, "获取应急保障任务默认编号失败！");
            }
    }

    /**任务管理——筹划任务——新增应急保障任务
     * @param wmTaskOthers　应急保障任务对象
     * @return 成功
     */
    @ApiOperation(value = "新增应急保障任务信息", notes = "新增应急保障任务信息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public RestResponse<String> insertTaskOthersInfo(
            @ApiParam("应急保障任务对象") @RequestBody wm_task_others wmTaskOthers) {
        wmTaskOthers.setOtask_id(UUIDGeneratorUtil.getUUIDWithoutLineAndToUpper());
        wmTaskOthers.setClass_id(TaskClassId.YJBZ_ID);

        taskOthersService.addTaskOthersInfo(wmTaskOthers);
        return RestResponseUtil.success("应急保障任务信息添加成功！");
    }

    @ApiOperation(value = "修改应急保障任务信息", notes = "修改应急保障任务信息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public RestResponse<String> updateTaskOthersInfo(
            @ApiParam("应急保障任务对象") @RequestBody wm_task_others jsonTaskOthers) {
            int res = taskOthersService.editTaskOthersInfo(jsonTaskOthers);
            if (res > 0) {
                return RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "应急保障任务信息修改成功！");
            } else {
                return RestResponseUtil.createResponse(RestResponseCode.FAIL, "应急保障任务信息修改失败！");
            }
    }

    @ApiOperation(value = "删除应急保障任务信息", notes = "删除应急保障任务信息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/delete")
    public RestResponse<String> deleteTaskOthersInfo(
        @NotBlank @ApiParam("其他任务Id") @RequestParam(name = "otask_id") String othersId) {
            int res = taskOthersService.deleteTaskOthersInfo(othersId);
            if (res > 0) {
                return RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "应急保障任务信息删除成功！");
            } else {
                return RestResponseUtil.createResponse(RestResponseCode.FAIL, "应急保障任务信息删除成功！");
            }
    }

    /**任务管理——筹划任务分类——批量导入
     * @param classId
     * @param multipartFile
     * @return
     * todo  导入后，要修改index表的任务状态
     */
    @ApiOperation(value = "根据分类id，批量导入任务", notes = "根据分类id，批量导入任务")
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/addbylist", headers = "content-type=multipart/form-data")
    public RestResponse<String> listTaskByClassId(
            @ApiParam("分类Id") @RequestParam(value = "class_id", defaultValue = TaskClassId.YJBZ_ID) String classId,
            @ApiParam("应急保障任务excel文件") @RequestParam(value = "file") MultipartFile multipartFile)  {

        if (StringUtils.isBlank(classId)||multipartFile.isEmpty()) {
            return RestResponseUtil.note("参数错误");
        }

        return taskOthersService.listTaskByClassId(classId,multipartFile);

    }
}
