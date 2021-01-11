package com.startest.wm.controller;

import com.startest.wm.config.exception.LoginException;
import com.startest.wm.enums.EnumTaskBookDistributionType;
import com.startest.wm.enums.EnumTaskChartDistributionType;
import com.startest.wm.model.TaskCheckInfoModel;
import com.startest.wm.pojo.sys_login;
import com.startest.wm.pojo.wm_task_check;
import com.startest.wm.pojo.wm_task_distribution;
import com.startest.wm.service.TaskCheckService;
import com.startest.wm.service.TaskDistributionService;
import com.startest.wm.utils.GlobalLoginInfoUtil;
import com.startest.wm.utils.UUIDGeneratorUtil;
import com.startest.wm.utils.customresponse.ExceptionHandleUtil;
import com.startest.wm.utils.customresponse.RestResponse;
import com.startest.wm.utils.customresponse.RestResponseCode;
import com.startest.wm.utils.customresponse.RestResponseUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-04-10-8:30
 * @描述 任务执行单逻辑控制器
 */
@Controller
@Validated
@Api(tags = "任务进度信息API")
@RequestMapping("/task/taskdistribution")
public class TaskDistributionController {
    @Autowired
    TaskDistributionService taskDistributionService;
    @Autowired
    private TaskCheckService taskCheckService;

    /**任务监控——筹划任务分类——列表——单个任务进度查询/详情查询
     * 生产任务——表单详情查询
     *作业管理——生产任务——人员与进度信息浏览    下面两个地方，已经重写了方法，在下面
     * @param request　request
     * @param disID 部门id
     * @param indexID 任务id
     * @param userID　用户id
     * @param userName　用户名
     * @param disType　分配图类型（电子图，s57图。。）
     * @return 生产任务表单详情列表
     */
    @ApiOperation(value = "检索任务执行单信息", notes = "检索任务执行单信息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/select")
    public RestResponse<List<wm_task_distribution>> selectTaskDistributionInfo(
            HttpServletRequest request,
            @RequestParam(name = "distribution_id", required = false) String disID,
            @RequestParam(name = "task_index_id") String indexID,
            @RequestParam(name = "user_id", required = false) String userID,
            @RequestParam(name = "user_name", required = false) String userName,
            @RequestParam(name = "distribution_type", required = false) String disType) throws LoginException {
            wm_task_distribution task = new wm_task_distribution();
            sys_login sysLogin = GlobalLoginInfoUtil.getLoginUserInfo(request);
            task.setDept_id(sysLogin.getDept_id());
                task.setDistribution_id(disID);
                task.setTask_index_id(indexID);
                task.setUser_id(userID);
                task.setUser_name(userName);
            task.setDistribution_type(disType);

            return RestResponseUtil.success(taskDistributionService.selectTaskDistribute(task));
    }

//    作业管理——生产任务——人员与进度查询
//     * 重新写  上面的共用方法  todo   前台还没改用此接口
    /**
     * 任务监控——进度查询  已使用些接口
     * @param indexId 任务id
     * @param disType 任务分配图类型
     * @return 进度列表
     */
    @ApiOperation(value = "人员与进度查询", notes = "人员与进度查询")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/selectTaskSchedulePersonnel")
    public RestResponse<List<wm_task_distribution>> selectTaskSchedulePersonnel(
        @NotNull @RequestParam(name = "task_index_id") String indexId,
        @NotNull    @RequestParam(name = "distribution_type") String disType)  {
        wm_task_distribution task = new wm_task_distribution();
        task.setTask_index_id(indexId);
        task.setDistribution_type(disType);
        return RestResponseUtil.success(taskDistributionService.selectTaskDistribute(task));
    }



    /**作业管理——生产任务——查询人员与进度信息列表　todo
     * @param request
     * @param indexID
     * @return
     */
    @ApiOperation(value = "获取分配任务内容列表", notes = "获取分配任务内容列表")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/taskcontent")
    public RestResponse<List<Map>> selectTaskDistributionContentInfo(
            HttpServletRequest request,
            @ApiParam("任务ID") @RequestParam(name = "task_index_id") String indexID) throws LoginException {
            sys_login sysLogin = GlobalLoginInfoUtil.getLoginUserInfo(request);
            return RestResponseUtil.success( taskDistributionService.getTaskDistributionContentList(indexID, sysLogin.getDept_id()));
    }

    @ApiOperation(value = "获取分配任务数据模型", notes = "获取分配任务数据模型")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/taskdismodel")
    public RestResponse<TaskCheckInfoModel> selectTaskDistributionModelInfo(
            @ApiParam("任务ID") @RequestParam(name = "task_index_id", required = false) String indexID,
            @ApiParam("部门ID") @RequestParam(name = "dept_id", required = false) String deptID,
            @ApiParam("分配内容") @RequestParam(name = "distribution_type", required = false) String disType,
            @ApiParam("0:海图，1：书表") @RequestParam(name = "data_type", required = false) String dataType) {
        RestResponse<TaskCheckInfoModel> response = null;
        try {
            String alldays = taskDistributionService.getTaskDistributeAllWorkDays(indexID, deptID, disType);
            String strEditor = null;
            String strMaker = null;
            String strLeader = null;
            if ("0".equals(dataType)) {
                strEditor = taskDistributionService.getTaskDistributeName(indexID, deptID, disType, EnumTaskChartDistributionType.BJSJ.getTaskChartDistributionType());
                strMaker = taskDistributionService.getTaskDistributeName(indexID, deptID, disType, EnumTaskChartDistributionType.ZY.getTaskChartDistributionType());
                strLeader = taskDistributionService.getTaskDistributeName(indexID, deptID, disType, EnumTaskChartDistributionType.ZZ.getTaskChartDistributionType());
            } else {
                strEditor = taskDistributionService.getTaskDistributeName(indexID, deptID, disType, EnumTaskBookDistributionType.BJSJ.getTaskBookDistributionType());
                strMaker = taskDistributionService.getTaskDistributeName(indexID, deptID, disType, EnumTaskBookDistributionType.ZY.getTaskBookDistributionType());
                strLeader = taskDistributionService.getTaskDistributeName(indexID, deptID, disType, EnumTaskBookDistributionType.ZZ.getTaskBookDistributionType());
            }
            TaskCheckInfoModel model = new TaskCheckInfoModel();
            model.setAll_work_days(alldays);
            model.setChartbookeditor(strEditor);
            model.setChartbookmaker(strMaker);
            model.setChartbookleader(strLeader);
            response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, model);
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }

    @ApiOperation(value = "检索任务执行单信息（通过ID）", notes = "检索任务执行单信息（通过ID）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "distribution_id", value = "表单id", required = true, dataType = "String")
    })
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/selectbyid")
    public RestResponse<wm_task_distribution> getTaskDistributionByID(
            @RequestParam(name = "distribution_id") String disID) {
        RestResponse<wm_task_distribution> response = null;
        try {
            wm_task_distribution task = new wm_task_distribution();
            task.setDistribution_id(disID);
            List<wm_task_distribution> list = taskDistributionService.selectTaskDistribute(task);
            if (list != null && list.size() > 0) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, list.get(0));
            } else {
                response = RestResponseUtil.error(300, "检索信息为空！");
            }
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }

    /**生产任务——表单详情——新增表单详情
     * @param request　请求
     * @param taskDistribution 任务执行单对象
     * @return  结果
     */
    @ApiOperation(value = "新增任务执行单信息", notes = "新增任务执行单信息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public RestResponse<String> addTaskDistributionInfo(
            HttpServletRequest request,
            @ApiParam("任务执行单对象") @RequestBody wm_task_distribution taskDistribution) throws LoginException {

        //判断是否登陆
        sys_login sysLogin = GlobalLoginInfoUtil.getLoginUserInfo(request);
        if (sysLogin == null || sysLogin.getDept_id().length() < 1) {
            return RestResponseUtil.createResponse(RestResponseCode.FAIL, "获取部门ID失败！");
        }

        taskDistribution.setDistribution_id(UUIDGeneratorUtil.getUUIDWithoutLineAndToUpper());
        taskDistribution.setDept_id(sysLogin.getDept_id());

        return taskDistributionService.addTaskDistributionInfo(taskDistribution);
    }

    @ApiOperation(value = "修改任务执行单信息", notes = "修改任务执行单信息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public RestResponse<String> updateTaskDistributionInfo(
            @ApiParam("任务执行单对象") @RequestBody wm_task_distribution jsonDis) {
        RestResponse<String> response = null;
        try {
            wm_task_distribution task = new wm_task_distribution();
            task.setDistribution_id(jsonDis.getDistribution_id());
            task.setUser_id(jsonDis.getUser_id());
            task.setUser_name(jsonDis.getUser_name());
            task.setDistribution_type(jsonDis.getDistribution_type());
            task.setUser_duty(jsonDis.getUser_duty());
            task.setWork_days(jsonDis.getWork_days());
            task.setStart_date(jsonDis.getStart_date());
            task.setEnd_date(jsonDis.getEnd_date());
            task.setRemark(jsonDis.getRemark());
            int res = taskDistributionService.updateTaskDistribution(task);
            if (res > 0) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "任务执行单信息修改成功！");
            } else {
                response = RestResponseUtil.createResponse(RestResponseCode.FAIL, "任务执行单信息修改失败！");
            }
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }

    @ApiOperation(value = "更新任务执行单接受时间信息", notes = "更新任务执行单接受时间信息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/updatestart")
    public RestResponse<String> updateTaskDistributionStartDate(
            @ApiParam(value = "表单id", required = true) @RequestParam(name = "distribution_id") String disID,
            @ApiParam(value = "接受时间", required = true) @RequestParam(name = "start_date") String startDate) {
        RestResponse<String> response = null;
        try {
            int res = taskDistributionService.updateTaskDistributionStartDate(disID, startDate);
            if (res > 0) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "任务执行单信息更新成功！");
            } else {
                response = RestResponseUtil.createResponse(RestResponseCode.FAIL, "任务执行单信息更新失败！");
            }
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }

    @ApiOperation(value = "更新任务执行单完成时间信息", notes = "更新任务执行单完成时间信息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/updateend")
    public RestResponse<String> updateTaskDistributionEndDate(
            @ApiParam(value = "表单id", required = true) @RequestParam(name = "distribution_id") String disID,
            @ApiParam(value = "完成时间", required = true) @RequestParam(name = "end_date") String endDate) {
        RestResponse<String> response = null;
        try {
            int res = taskDistributionService.updateTaskDistributionEndDate(disID, endDate);
            if (res > 0) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "任务执行单信息更新成功！");
            } else {
                response = RestResponseUtil.createResponse(RestResponseCode.FAIL, "任务执行单信息更新失败！");
            }
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }

    /**生产任务——表单详情——删除人员执行记录
     * @param disId　执行单关联id
     * @return 成功
     */
    @ApiOperation(value = "删除任务执行单信息", notes = "删除任务执行单信息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/delete")
    public RestResponse<String> deleteTaskDistributionInfo(
            @ApiParam(value = "表单id", required = true) @RequestParam(name = "distribution_id") String disId) {
        return taskDistributionService.deleteTaskDistributionInfo(disId);
    }


    @ApiOperation(value = "跟据任务id计算任务总工天", notes = "跟据任务id计算任务总工天")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/getAllWorkDaysById")
    public RestResponse<Double> getAllWorkDaysById(
            @ApiParam(value = "任务id", required = true) @RequestParam(name = "indexID") String indexID,
            @ApiParam(value = "产品类型", required = false) @RequestParam(name = "chart_type") String productType
    ) {
        Double res = taskDistributionService.getChartAllWorkDays(indexID, productType);

        //todo --
        return RestResponseUtil.createResponse(RestResponseCode.SUCCESS, 2.1);
    }


    @ApiOperation(value = "根据任务，部门id，查询岗位名称", notes = "根据任务，部门id，查询岗位名称")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/getDuty")
    public RestResponse<List<Map>> getDuty(
            @ApiParam(value = "任务索引id", required = true) @RequestParam(name = "task_index_id") String indexID,
            @ApiParam(value = "部门id", required = true) @RequestParam(name = "dept_id") String deptID) {
        List<Map> map = taskDistributionService.getDuty(indexID, deptID);
        return RestResponseUtil.createResponse(RestResponseCode.SUCCESS, map);
    }
}
