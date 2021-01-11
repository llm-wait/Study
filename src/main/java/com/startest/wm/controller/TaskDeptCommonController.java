package com.startest.wm.controller;

import com.startest.wm.model.TaskDailyModel;
import com.startest.wm.pojo.wm_task_deptcommon;
import com.startest.wm.service.TaskDeptCommonService;
import com.startest.wm.utils.TaskDeptCommonExcelOperationUtil;
import com.startest.wm.utils.UUIDGeneratorUtil;
import com.startest.wm.utils.customresponse.RestResponse;
import com.startest.wm.utils.customresponse.RestResponseCode;
import com.startest.wm.utils.customresponse.RestResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-05-11-16:36
 * @描述 部门日常任务控制层
 */
@Controller
@Api(tags = "部门日常任务API")
@RequestMapping("/task/taskcommon")
public class TaskDeptCommonController {

    private static Logger logger = LoggerFactory.getLogger(TaskDeptCommonController.class);

    @Autowired
    private TaskDeptCommonService taskDeptCommonService;

    /**作业管理——日常任务——日常任务列表查询（搜索）
     * @param comID
     * @param deptID
     * @param userID
     * @param comYear
     * @return
     */
    @ApiOperation(value = "部门日常任务信息检索", notes = "部门日常任务信息检索")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/select")
    public RestResponse<List<TaskDailyModel>> selectTaskDeptCommonInfo(
            @RequestParam(name = "ctask_id", required = false) String comID,
            @RequestParam(name = "dept_id", required = false) String deptID,
            @RequestParam(name = "user_id", required = false) String userID,
            @RequestParam(name = "ctask_year", required = false) String comYear) {
        RestResponse<List<TaskDailyModel>> response = null;
        try {
            Map map = new HashMap();
            map.put("ctask_id", comID);
            map.put("dept_id", deptID);
            map.put("user_id", userID);
            map.put("ctask_year", comYear);
            List<TaskDailyModel> comList = taskDeptCommonService.getTaskDeptCommonInfo(map);
            response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, comList);
        } catch (Exception e) {
            logger.error("部门日常任务信息检索异常：", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "通过ID获取对象", notes = "通过ID获取对象")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/selectbyid")
    public RestResponse<wm_task_deptcommon> getTaskDeptCommonByID(
            @RequestParam(name = "ctask_id") String comID) {
        RestResponse<wm_task_deptcommon> response = null;
        try {
            wm_task_deptcommon comTask = taskDeptCommonService.getTaskDeptCommonInfoByID(comID);
            response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, comTask);
        } catch (Exception e) {
            logger.error("通过ID获取对象异常：", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    /**作业管理——日常任务——新增日常任务
     * @param jsonComTask　日常任务对象
     * @return 结果
     */
    @ApiOperation(value = "新增部门日常任务信息", notes = "新增部门日常任务信息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public RestResponse<String> insertTaskDeptCommonInfo(
            @ApiParam("部门日常任务对象") @RequestBody wm_task_deptcommon jsonComTask) {
        jsonComTask.setCtask_id(UUIDGeneratorUtil.getUUIDWithoutLineAndToUpper());
        jsonComTask.setCtask_year(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        int res = taskDeptCommonService.insertTaskDeptCommonInfo(jsonComTask);
        if (res > 0) {
            return RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "部门日常任务信息添加成功！");
        } else {
            return RestResponseUtil.createResponse(RestResponseCode.FAIL, "部门日常任务信息添加失败！");
        }
    }


    @ApiOperation(value = "批量导入部门日常任务信息", notes = "批量导入部门日常任务信息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/addbylist", headers = "content-type=multipart/form-data")
    public RestResponse<String> insertTaskDeptCommonInfoByList(
            @ApiParam("日常任务excel文件") @RequestParam(value = "file") MultipartFile file,
            @ApiParam("部门ID") @RequestParam(value = "dept_id") String deptID,
            @ApiParam("部门名称") @RequestParam(value = "dept_name") String deptName,
            @ApiParam("用户ID") @RequestParam(value = "user_id") String userID,
            @ApiParam("用户名称") @RequestParam(value = "user_name") String userName) {
        RestResponse<String> response = null;
        try {
            String fileName = file.getOriginalFilename();
            if (fileName == null || fileName.length() <= 0) {
                response = RestResponseUtil.note("Excel文件路径为空！");
                return response;
            }
            //判断文件类型
            if (!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx")) {
                response = RestResponseUtil.note("文件格式错误！");
            } else {
                InputStream inputStream = file.getInputStream();
                List<wm_task_deptcommon> dataList = TaskDeptCommonExcelOperationUtil.readToDeptTaskData(inputStream, fileName, deptID, deptName, userID, userName);
//                if (dataList.size() > 0) {
//                    //通过迭代器，过滤掉任务名称重复的任务
//                    Iterator<wm_task_deptcommon> iterator = dataList.iterator();
//                    while (iterator.hasNext()) {
//                        List<wm_task_deptcommon> comTaskList = taskDeptCommonService.getTaskDeptCommonInfoByName(iterator.next().getCtask_name());
//                        if (comTaskList != null && comTaskList.size() > 0) {
//                            iterator.remove();
//                        }
//                    }
                if (dataList.size() > 0) {
                    int res = taskDeptCommonService.insertTaskDeptCommonInfoByList(dataList);
                    if (res > 0) {
                        response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "部门日常任务信息导入成功！");
                    } else {
                        response = RestResponseUtil.createResponse(RestResponseCode.FAIL, "部门日常任务信息导入失败！");
                    }
                } else {
                    response = RestResponseUtil.note("导入的数据任务名都已存在！");
                }
//                } else {
//                    response = RestResponseUtil.note("导入的数据为空！");
//                }
                inputStream.close();
            }
        } catch (Exception e) {
            logger.error("批量导入部门日常任务信息异常：", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    /**作业管理——日常任务——编辑日常任务
     * @param jsonComTask　日常任务对象
     * @return 结果
     */
    @ApiOperation(value = "修改部门日常任务信息", notes = "修改部门日常任务信息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public RestResponse<String> updateTaskDeptCommonInfo(@RequestBody  wm_task_deptcommon jsonComTask) {

        if (StringUtils.isNotBlank(jsonComTask.getCtask_id())) {
            return RestResponseUtil.note("未找到对应的日常任务");
        }

        int res = taskDeptCommonService.updateTaskDeptCommonInfo(jsonComTask);
        if (res > 0) {
            return RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "部门日常任务信息修改成功！");
        } else {
            return RestResponseUtil.createResponse(RestResponseCode.FAIL, "部门日常任务信息修改失败！");
        }
    }


    /**作业管理——日常任务——删除日常任务
     * @param comID
     * @return
     */
    @ApiOperation(value = "删除部门日常任务信息", notes = "删除部门日常任务信息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/delete")
    public RestResponse<String> deleteTaskDeptCommonInfo(
            @RequestParam(name = "ctask_id") String comID) {
        RestResponse<String> response = null;
        try {
            int res = taskDeptCommonService.deleteTaskDeptCommonInfo(comID);
            if (res > 0) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "部门日常任务信息删除成功！");
            } else {
                response = RestResponseUtil.note("部门信息删除失败！");
            }
        } catch (Exception e) {
            logger.error("删除部门日常任务信息异常：", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }
}
