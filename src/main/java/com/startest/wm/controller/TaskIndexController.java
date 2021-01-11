package com.startest.wm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.startest.wm.config.exception.LoginException;
import com.startest.wm.constant.DeptId;
import com.startest.wm.constant.TaskClassId;
import com.startest.wm.enums.task.EnumTaskState;
import com.startest.wm.enums.task.EnumTaskType;
import com.startest.wm.model.*;
import com.startest.wm.pojo.*;
import com.startest.wm.service.SysDeptService;
import com.startest.wm.service.TaskClassService;
import com.startest.wm.service.TaskDeptService;
import com.startest.wm.service.TaskIndexService;
import com.startest.wm.utils.*;
import com.startest.wm.utils.customresponse.ExceptionHandleUtil;
import com.startest.wm.utils.customresponse.RestResponse;
import com.startest.wm.utils.customresponse.RestResponseCode;
import com.startest.wm.utils.customresponse.RestResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * @author 杨世凯
 * @公司 北京星天科技
 * @创建时间 2020-04-10-8:28
 * @描述 任务索引逻辑控制器
 */
@Controller
@Api(tags = "任务索引逻辑操作相关API")
@RequestMapping("/task/taskindex")
@Validated
public class TaskIndexController {

    @Autowired(required = false)
    TaskIndexService taskIndexService;
    @Autowired(required = false)
    TaskClassService taskClassService;
    @Autowired(required = false)
    SysDeptService sysDeptService;
    @Autowired(required = false)
    TaskDeptService taskDeptService;

    @ApiOperation(value = "检索任务索引信息", notes = "检索任务索引信息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/select")
    public RestResponse<List<wm_task_index>> selectTaskIndexInfo(
            @ApiParam(value = "索引id") @RequestParam(name = "index_id", required = false) String indexId,
            @ApiParam("任务年份（未分配年份值为：0）") @RequestParam(name = "task_year", required = false) String indexYear,
            @ApiParam("任务类型（年度任务，应急保障任务，日常任务）") @RequestParam(name = "task_type", required = false) String indexType,
            @ApiParam("任务状态（1筹划，2下发，3任务进行，4任务质检，5制印）,设为null时，不进行条件查询") @RequestParam(name = "task_state", required = false) String indexState,
            @ApiParam("任务名称") @RequestParam(name = "task_name", required = false) String indexName,
            @ApiParam("是否分配部门（null或0：全部；1:已分配；2：未分配") @RequestParam(name = "is_task_dept", required = false) Integer isDept) {

            wm_task_index index = new wm_task_index();
                index.setIndex_id(indexId);
                index.setTask_year(indexYear);
                index.setTask_type(indexType);
                index.setTask_state(indexState);
                index.setTask_name(indexName);
                index.setIs_task_dept(isDept);
            List<wm_task_index> list = taskIndexService.selectTaskIndexInfo(index);
            return RestResponseUtil.createResponse(RestResponseCode.SUCCESS, list);
    }

    /**任务监控——筹划任务分类——查询任务监控列表
     * @param deptId　部门id
     * @param classId 任务分类id
     * @param indexYear 任务年份
     * @param indexType　任务类型
     * @param indexState　任务状态
     * @param pageNum　当前页
     * @param pageSize　页容量　
     * @return 列表
     */
    @ApiOperation(value = "检索监控任务列表", notes = "检索监控任务列表")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/selectchecktask")
    public RestResponse<Pager<TaskIndexModel>> selectCheckTaskIndexInfo(
            @RequestParam(name = "dept_id", required = false) String deptId,
      @NotNull @RequestParam(name = "class_id") String classId,
            @RequestParam(name = "task_year", required = false) String indexYear,
            @RequestParam(name = "task_type", required = false) String indexType,
            @RequestParam(name = "task_state", required = false) String indexState,
            @RequestParam(name = "page_number", defaultValue = "1") Integer pageNum,
            @RequestParam(name = "page_size", defaultValue = "50") Integer pageSize) {
            TaskIndexModel index = new TaskIndexModel();
                index.setDept_id(deptId);
                index.setTask_year(indexYear);
                index.setTask_type(indexType);
                index.setTask_state(indexState);
                index.setTask_class_id(classId);
            List<TaskIndexModel> list = taskIndexService.getTaskIndexModelInfo(index);
            Pager<TaskIndexModel> pager = new Pager<>();
            pager.setCurentPageIndex(pageNum);
            pager.setCountPerpage(pageSize);
            pager.setBigList(list);

            return RestResponseUtil.createResponse(RestResponseCode.SUCCESS, pager);
    }

    /**作业管理——生产任务——列表查询生产任务
     * 生产任务
     * @param request　请求头
     * @param indexYear 任务年份
     * @param indexState　任务状态
     * @param taskType　任务类型
     * @param pageNum　当前页
     * @param pageSize　页容量
     * @return 　生产任务列表
     * @throws LoginException
     */
    @ApiOperation(value = "作业室任务列表", notes = "作业室任务列表")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/selectdepttask")
    public RestResponse<PageInfo> selectDeptTaskIndexInfo(
            HttpServletRequest request,
            @RequestParam(name = "task_year", required = false) String indexYear,
            @RequestParam(name = "task_state", required = false) String indexState,
            @RequestParam(name = "task_type", required = false) String taskType,
            @RequestParam(name = "page_number", defaultValue = "1") Integer pageNum,
            @RequestParam(name = "page_size", defaultValue = "50") Integer pageSize) throws LoginException {
            sys_login loginU = GlobalLoginInfoUtil.getLoginUserInfo(request);
            TaskIndexModel index = new TaskIndexModel();
                index.setDept_id(loginU.getDept_id());
                index.setTask_year(indexYear);
                index.setTask_state(indexState);
                index.setTask_type(taskType);

            PageHelper.startPage(pageNum, pageSize);
            List<TaskIndexModel> list = taskIndexService.selectDeptTaskIndexInfo(index);


            return RestResponseUtil.success(new PageInfo<>(list));
    }

    /**质控参谋——任务质量评估—任务质量评估列表查询
     * @param indexYear
     * @param status
     * @param taskType
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "质控处任务列表", notes = "质控处任务列表")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/selectcheckdepttask")
    public RestResponse<Pager<TaskIndexModel>> selectCheckDeptTaskIndexInfo(
            @RequestParam(name = "task_year", required = false) String indexYear,
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "task_type", required = false) String taskType,
            @RequestParam(name = "page_number", defaultValue = "1") Integer pageNum,
            @RequestParam(name = "page_size", defaultValue = "50") Integer pageSize) {
//            TaskIndexModel index = new TaskIndexModel();
//                index.setTask_year(indexYear);
//                index.setTask_state(status);
//                index.setTask_type(taskType);
//                //任务质检
//                index.setTask_state(EnumTaskState.RWZJ.getTaskStateString());





            List<TaskIndexModel>   list =new ArrayList<>();
        TaskIndexModel taskIndexModel = new TaskIndexModel();
        taskIndexModel.setIndex_id("7FEC7DA31F6147CBB38D9D7CB97E2921");
        taskIndexModel.setTask_name("105");
        taskIndexModel.setTask_year("2020");
        taskIndexModel.setTask_type(EnumTaskType.ZTRW.getTaskTypeString());
        taskIndexModel.setTask_class_tag("王二");
        taskIndexModel.setTask_print_date("2020-12-12");
        taskIndexModel.setTask_state("任务制检");
        taskIndexModel.setData_code("105");
        taskIndexModel.setDept_id("f18602be7ff14c918121d0fe7775fd51");
        taskIndexModel.setTask_class_tag("105");
        taskIndexModel.setTask_class_id(TaskClassId.ZTRW_ID);
        list.add(taskIndexModel);

        //进行分页支持
        Pager<TaskIndexModel> pager = new Pager<>();
        pager.setCurentPageIndex(pageNum);
        pager.setCountPerpage(pageSize);
//            pager.setBigList(taskIndexService.getTaskIndexModelInfo(index));
            pager.setBigList(list);

        return RestResponseUtil.createResponse(RestResponseCode.SUCCESS, pager);
    }


    @ApiOperation(value = "导出质控处任务列表", notes = "导出质控处任务列表")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/checkOutcheckdepttaskByIds")
    public void checkOutcheckdepttaskByIds(@RequestParam(name = "indexId") String ids, HttpServletResponse response) {
        try {
            taskIndexService.checkOutcheckdepttaskByIds(ids, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**作业管理——日常任务——作业员列表查询
     * 任务质量评估
     * @param request
     * @return
     * @throws LoginException
     */
    @ApiOperation(value = "作业室人员列表", notes = "作业室人员列表")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/selectdeptuser")
    public RestResponse<List<sys_user>> selectDeptUserListInfo(HttpServletRequest request) throws LoginException {
            sys_login loginU = GlobalLoginInfoUtil.getLoginUserInfo(request);
            if (loginU.getDept_id() != null && loginU.getDept_id().length() > 0) {
                List<sys_user> resMap = sysDeptService.getDeptUserList(loginU.getDept_id(), true);
                return RestResponseUtil.createResponse(RestResponseCode.SUCCESS, resMap);
            } else {
               return RestResponseUtil.error(300, "登录信息获取部门ID失败！");
            }
    }

    @ApiOperation(value = "导出监控任务列表到Excel", notes = "导出监控任务列表到Excel")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/selectchecktaskexport")
    public RestResponse<String> selectCheckTaskIndexInfoExport(
            HttpServletResponse httpServletResponse,
            @RequestParam(name = "dept_id", required = false) String deptid,
            @RequestParam(name = "class_id", required = false) String classid,
            @RequestParam(name = "task_year", required = false) String indexYear,
            @RequestParam(name = "task_type", required = false) String indexType,
            @RequestParam(name = "task_state", required = false) String indexState) {
        RestResponse<String> response = null;
        try {
            TaskIndexModel index = new TaskIndexModel();
            if (deptid != null && deptid.length() > 0) {
                index.setDept_id(deptid);
            }
            if (indexYear != null && indexYear.length() > 0) {
                index.setTask_year(indexYear);
            }
            if (indexType != null && indexType.length() > 0) {
                index.setTask_type(indexType);
            }
            if (indexState != null && indexState.length() > 0) {
                index.setTask_state(indexState);
            }
            if (classid != null && classid.length() > 0) {
                index.setTask_class_id(classid);
            }
            List<TaskIndexModel> list = taskIndexService.getTaskIndexModelInfo(index);
            TaskExportUtil.exportCheckListtoExcel(list, httpServletResponse);
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }

    /**任务监控——筹划任务分类——查询任务统计信息
     * 查询任务统计信息   部门进行的任务数、正在做的，进厂数等
     * @param classid 种类id
     * @param deptid  部门id
     * @param indexYear  任务年份
     * @param indexType  任务类型
     * @param indexState 任务状态
     * @return 成功
     */
    @ApiOperation(value = "获取监控任务列表统计数据", notes = "获取监控任务列表统计数据")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/getchecktaskresult")
    public RestResponse<TaskResultModel> getCheckTaskResultInfo(
            @RequestParam(name = "class_id", required = false) String classid,
            @RequestParam(name = "dept_id", required = false) String deptid,
            @RequestParam(name = "task_year", required = false) String indexYear,
            @RequestParam(name = "task_type", required = false) String indexType,
            @RequestParam(name = "task_state", required = false) String indexState) {
            TaskResultModel tresModel = new TaskResultModel();
            TaskIndexModel index = new TaskIndexModel();
                index.setTask_class_id(classid);
                index.setDept_id(deptid);
                index.setTask_year(indexYear);
                index.setTask_type(indexType);
                index.setTask_state(indexState);
            List<TaskIndexModel> list = taskIndexService.getTaskIndexModelInfo(index);
            if (list != null && list.size() > 0) {
                tresModel.setTask_total(String.valueOf(list.size()));

                List<TaskIndexModel> allList = ListUtils.select(list, x -> x.getTask_state() != null);

                List<TaskIndexModel> childList = ListUtils.select(allList, x -> x.getTask_state().equals(EnumTaskState.RWJX.getTaskStateString()));
                tresModel.setTask_doing(String.valueOf(childList.size()));

                List<TaskIndexModel> childList1 = ListUtils.select(allList, x -> x.getTask_state().equals(EnumTaskState.ZY.getTaskStateString()));
                tresModel.setTask_enter(String.valueOf(childList1.size()));
            } else {
                tresModel.setTask_total("0");
                tresModel.setTask_doing("0");
                tresModel.setTask_enter("0");
            }
            return RestResponseUtil.createResponse(RestResponseCode.SUCCESS, tresModel);
    }

    @ApiOperation(value = "通过ID获取对象信息", notes = "通过ID获取对象信息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/selectbyid")
    public RestResponse<wm_task_index> getTaskIndexInfoById(
       @NotNull     @RequestParam(name = "index_id") String indexId) {
            return    taskIndexService.getTaskIndexInfoById(indexId);
    }

    /**公共接口
     * 任务管理——筹划任务——查询任务全部年份
     * 任务监控,日常任务,任务质量评估，
     * @return 年份列表　
     */
    @ApiOperation(value = "获取任务年份", notes = "获取任务年份")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/selectyear")
    public RestResponse<List<String>> selectTaskYearInfo() {
        return taskIndexService.selectTaskYearInfo();
    }

    @ApiOperation(value = "添加任务索引信息（单个）", notes = "添加任务索引信息（单个）")
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/addone")
    public RestResponse<String> insertTaskIndexInfoByOne(
            @ApiParam("任务索引对象") @RequestBody wm_task_index jsonIndex) {
        RestResponse<String> response = null;
        try {
            wm_task_index taskIndex = new wm_task_index();
            taskIndex.setIndex_id(UUIDGeneratorUtil.getUUIDWithoutLineAndToUpper());
            taskIndex.setTask_class_id(jsonIndex.getTask_class_id());
            taskIndex.setTask_class_tag(jsonIndex.getTask_class_id());
            taskIndex.setTask_data_id(jsonIndex.getTask_data_id());
            if (jsonIndex.getTask_year() == null) {
                taskIndex.setTask_year("0");
            } else {
                taskIndex.setTask_year(jsonIndex.getTask_year());
            }
            taskIndex.setTask_type(jsonIndex.getTask_type());
            if (EnumTaskType.RCRW.getTaskTypeString().equals(jsonIndex.getTask_type())) {
                taskIndex.setTask_state(EnumTaskState.XF.getTaskStateString());
            } else {
                taskIndex.setTask_state(EnumTaskState.JHXD.getTaskStateString());
            }
            taskIndex.setTask_name(jsonIndex.getTask_name());
            taskIndex.setTask_remark(jsonIndex.getTask_remark());
            int res = taskIndexService.insertTaskIndexInfo(taskIndex);
            if (res > 0) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "任务索引信息添加成功！");
            } else {
                response = RestResponseUtil.createResponse(RestResponseCode.FAIL, "任务索引信息添加失败！");
            }
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }

    /**任务管理——新增任务
     * @param taskIndex　任务对象
     * @return 结果
     */
    @ApiOperation(value = "新增任务", notes = "新增任务")
    @ResponseBody
    @PostMapping("/addTask")
    public RestResponse<String> addTask(
            @ApiParam("任务索引对象") @RequestBody wm_task_index taskIndex) {

        taskIndex.setIndex_id(UUIDGeneratorUtil.getUUIDWithoutLineAndToUpper());
        taskIndex.setTask_state(EnumTaskState.JHXD.getTaskStateString());

        int res = taskIndexService.addTask(taskIndex);
        if (res > 0) {
            return RestResponseUtil.success( "任务添加成功！");
        } else {
            return RestResponseUtil.note( "资料不存在，请先添加资料！",null);
        }
    }





    @ApiOperation(value = "添加任务索引信息（批量）", notes = "添加任务索引信息（批量）")
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/addlist")
    public RestResponse<String> insertTaskIndexInfoByOne(
            @ApiParam("任务索引对象") @RequestBody List<wm_task_index> jsonList) {
        RestResponse<String> response = null;
        try {
            List<wm_task_index> addList = new ArrayList<>();
            for (wm_task_index item : jsonList) {
                wm_task_index taskIndex = new wm_task_index();
                taskIndex.setIndex_id(UUIDGeneratorUtil.getUUIDWithoutLineAndToUpper());
                taskIndex.setTask_class_id(item.getTask_class_id());
                taskIndex.setTask_class_tag(item.getTask_class_id());
                taskIndex.setTask_data_id(item.getTask_data_id());
                if (item.getTask_year() == null) {
                    taskIndex.setTask_year("0");
                } else {
                    taskIndex.setTask_year(item.getTask_year());
                }
                taskIndex.setTask_type(item.getTask_type());
                if (EnumTaskType.RCRW.getTaskTypeString().equals(item.getTask_type())) {
                    taskIndex.setTask_state(EnumTaskState.XF.getTaskStateString());
                } else {
                    taskIndex.setTask_state(EnumTaskState.JHXD.getTaskStateString());
                }
                taskIndex.setTask_name(item.getTask_name());
                taskIndex.setTask_remark(item.getTask_remark());
                addList.add(taskIndex);
            }
            int res = taskIndexService.insertTaskIndexInfoByList(addList);
            if (res > 0) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "任务索引信息添加成功！");
            } else {
                response = RestResponseUtil.createResponse(RestResponseCode.FAIL, "任务索引信息添加失败！");
            }
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }

    @ApiOperation(value = "修改任务索引信息", notes = "修改任务索引信息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public RestResponse<String> updateTaskIndexInfo(
            @ApiParam("任务索引对象") @RequestBody wm_task_index jsonIndex) {
        RestResponse<String> response = null;
        try {
            wm_task_index taskIndex = new wm_task_index();
            taskIndex.setIndex_id(jsonIndex.getIndex_id());
            if (jsonIndex.getTask_year() == null) {
                taskIndex.setTask_year("0");
            } else {
                taskIndex.setTask_year(jsonIndex.getTask_year());
            }
            taskIndex.setTask_type(jsonIndex.getTask_type());
            if (EnumTaskType.RCRW.getTaskTypeString().equals(jsonIndex.getTask_type())) {
                taskIndex.setTask_state(EnumTaskState.XF.getTaskStateString());
            } else {
                taskIndex.setTask_state(EnumTaskState.JHXD.getTaskStateString());
            }
            taskIndex.setTask_name(jsonIndex.getTask_name());
            taskIndex.setTask_remark(jsonIndex.getTask_remark());
            int res = taskIndexService.updateTaskIndexInfo(taskIndex);
            if (res > 0) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "任务索引信息修改成功！");
            } else {
                response = RestResponseUtil.createResponse(RestResponseCode.FAIL, "任务索引信息修改失败！");
            }
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }

    @ApiOperation(value = "更新出厂时间", notes = "更新出厂时间")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/updateprint")
    public RestResponse<String> updateTaskPrintDate(
            @RequestParam(name = "index_id") String indexId,
            @RequestParam(name = "task_print_date") String printDate) {
         return    taskIndexService.updatePrintDate(indexId, printDate);
    }

    @ApiOperation(value = "更新任务索引进度信息", notes = "更新任务索引进度信息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/updaterate")
    public RestResponse<String> updateTaskIndexRateInfo(
            @RequestParam(name = "index_id") String indexId,
            @RequestParam(name = "task_rate") String indexRate,
            @RequestParam(name = "task_rate_describe", required = false) String indexDesc) {
        RestResponse<String> response = null;
        try {
            int res = taskIndexService.updateTaskRate(indexId, indexRate, indexDesc);
            if (res > 0) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "任务索引进度信息更新成功！");
            } else {
                response = RestResponseUtil.createResponse(RestResponseCode.FAIL, "任务索引进度信息更新失败！");
            }
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }

    /**任务管理——修改任务名称
     * @param name 任务新名称
     * @param taskId　任务id
     * @return 结果
     */
    @ApiOperation(value = "修改任务名称", notes = "更新任务索引资料信息")
    @ResponseBody
    @PostMapping("/updateTaskName")
    public RestResponse<String> updateTaskName(
            @ApiParam(value = "任务名称") @RequestParam(name = "task_name") String name,
            @ApiParam(value = "任务id") @RequestParam(name = "task_index_id") String taskId
            ) {
        taskIndexService.updateTaskName(name, taskId);
        return RestResponseUtil.success("任务名称已成功修改！");
    }


    @ApiOperation(value = "更新任务索引资料信息", notes = "更新任务索引资料信息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/updatedata")
    public RestResponse<String> updateTaskIndexDataInfo(
            @ApiParam(value = "索引id") @RequestParam(name = "index_id") String indexId,
            @ApiParam(value = "资料id") @RequestParam(name = "task_data_id") String dataID) {
             taskIndexService.updateTaskData(indexId, dataID);
                return RestResponseUtil.success("任务索引资料信息更新成功！");
    }

    /**任务管理——筹划任务分类——修改分类
     * @param indexId 任务id
     * @param classId 分类id
     * @return 成功
     */
    @ApiOperation(value = "更新任务索引种类信息", notes = "更新任务索引种类信息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/updateclass")
    public RestResponse<String> updateTaskIndexClassInfo(
            @NotNull @ApiParam(value = "任务索引id(多个index_id,用英文逗号隔开)") @RequestParam(name = "index_id") String indexId,
            @NotNull @ApiParam(value = "种类id") @RequestParam(name = "task_class_id") String classId) {
        List<String> list = Arrays.asList(indexId.trim().split(","));
        taskIndexService.updateTaskClassInfo(list, classId);
        return RestResponseUtil.success("任务索引种类信息更新成功！");
    }

    /**任务管理——筹划任务分类——下达　　todo　可以被批量下达代替,已经修改了此接口调用
     * 作业管理——生产任务——发送生产任务　todo 发送不成功　Request请求或种类ID不能为空！
     * @param request
     * @param classID
     * @param taskName
     * @param taskType
     * @param indexId
     * @param state
     * @return
     */
    @ApiOperation(value = "更新任务索引状态信息", notes = "更新任务索引状态信息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/updatestate")
    public RestResponse<String> updateTaskIndexStateInfo(
            HttpServletRequest request,
            @ApiParam("种类ID") @RequestParam(name = "class_id", required = false) String classID,
            @ApiParam("任务名称") @RequestParam(name = "task_name", required = false) String taskName,
            @ApiParam("任务类型") @RequestParam(name = "task_type", required = false) String taskType,
            @ApiParam("任务索引ID") @RequestParam(name = "index_id") String indexId,
            @ApiParam("任务状态") @RequestParam(name = "task_state") EnumTaskState state) throws LoginException {

       return  taskDeptService.updateTaskIndexStateInfo(request,classID, taskName, taskType, indexId, state);

    }



    /**任务管理 ——筹划任务分类——批量下达
     * @param indexIdList
     * @param state
     * @return
     */
    @ApiOperation(value = "批量更新任务状态", notes = "批量更新任务状态")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/updatestatelist")
    public RestResponse<String> updateTaskIndexStateInfoByList(
         @NotNull   @ApiParam(value = "ID列表，用逗号隔开各个ID") @RequestParam(name = "index_id_list") String indexIdList,
            @ApiParam("任务状态：1筹划，2下发，3任务进行，4任务质检，5制印") @RequestParam(name = "task_state") EnumTaskState state) {

       return taskIndexService.updateTaskIndexStateInfoByList(indexIdList, state);
    }

    @ApiOperation(value = "更新任务索引年份信息", notes = "更新任务索引年份信息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/updateyear")
    public RestResponse<String> updateTaskIndexYearInfo(
            @NotNull @ApiParam(value = "索引id") @RequestParam(name = "index_id") String indexId,
            @NotNull @ApiParam(value = "任务年份（未分配年份值为：0）") @RequestParam(name = "task_year") String indexYear) {
        if (indexYear.isEmpty()) {
            indexYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        }
        taskIndexService.updateTaskYearInfo(indexId, indexYear);
        return RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "任务索引年份信息更新成功！");
    }

    /**任务管理——筹划任务分类——分配年份
     * @param indexIdList
     * @param indexYear
     * @return
     */
    @ApiOperation(value = "批量更新年份信息", notes = "批量更新年份信息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/updateyearlist")
    public RestResponse<String> updateTaskIndexYearInfoByList(
            @ApiParam(value = "ID列表，用逗号隔开各个ID") @RequestParam(name = "index_id_list") String indexIdList,
            @ApiParam(value = "分配年份") @RequestParam(name = "task_year") String indexYear) {
        RestResponse<String> response = null;
        try {
            List<String> lstIDs = Arrays.asList(indexIdList.split(","));
            if (indexYear == null || indexYear.length() <= 0) {
                indexYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
            }
            int res = taskIndexService.updateTaskYearInfoByList(lstIDs, indexYear);
            if (res > 0) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "任务索引年份信息更新成功！");
            } else {
                response = RestResponseUtil.createResponse(RestResponseCode.FAIL, "任务索引年份信息更新失败！");
            }
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }

    @ApiOperation(value = "删除任务索引信息", notes = "删除任务索引信息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/delete")
    public RestResponse<String> deleteTaskIndexInfo(
            @NotNull @ApiParam(value = "索引id") @RequestParam(name = "index_id") String indexId) {
        taskIndexService.deleteTaskIndexInfo(indexId);
        return RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "任务索引信息删除成功！");
    }

    /**任务管理——筹划任务——制图任务列表数据
     * @param classId  任务id
     * @param taskYear 任务年份
     * @param pageNum  当前页
     * @param pageSize 页容量
     * @return list 筹划任务分类列表 未分配部门的集合
     */
    @ApiOperation(value = "获取筹划任务分类列表（未分配部门）", notes = "获取筹划任务分类列表（未分配部门）")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/pageCheckList")
    public RestResponse<PageInfo<Object>> getPageCheckList(
            @ApiParam(value = "种类ID") @RequestParam(name = "class_id") String classId,
            @ApiParam(value = "年份") @RequestParam(name = "task_year", required = false) String taskYear,
            @ApiParam(value = "页数") @RequestParam(name = "page_number") Integer pageNum,
            @ApiParam(value = "页值") @RequestParam(name = "page_size") Integer pageSize) {
        //获取任务索引记录
        wm_task_index taskIndex = new wm_task_index();
        taskIndex.setTask_class_id(classId);
        taskIndex.setTask_year(taskYear);
        //筹划状态
        taskIndex.setTask_state(EnumTaskState.JHXD.getTaskStateString());

        PageHelper.startPage(pageNum, pageSize);
        List<Object> taskList = taskIndexService.selectTaskCheckListInfo(taskIndex);
        PageInfo<Object> pageInfo = new PageInfo<>(taskList);
        return RestResponseUtil.success(pageInfo);
    }




    /**根据任务类型和年份查询 筹划任务（未分配）列表  （新改接口，替换上面原/checklist接口）
     * @param classId 任务种类id，可能有多个子分类
     * @param taskYear 任务年份
     * @param pageNum 当前页
     * @param pageSize 页容量
     * @return  统一分页  筹划任务（未分配）列表
     */
    @ApiOperation(value = "新加 获取筹划任务分类列表（未分配部门）", notes = "新加 获取筹划任务分类列表（未分配部门）.")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/checklist")
    public RestResponse<PageInfo<wm_task_index>> getPageCheck(
            @ApiParam(value = "种类ID") @RequestParam(name = "class_id") String classId,
            @ApiParam(value = "年份") @RequestParam(name = "task_year", required = false) String taskYear,
            @ApiParam(value = "页数") @RequestParam(name = "page_number") Integer pageNum,
            @ApiParam(value = "页值") @RequestParam(name = "page_size") Integer pageSize) {


        List<wm_task_index> list = taskClassService.getPageCheck(pageNum,pageSize,classId, taskYear);
        PageInfo<wm_task_index> pageInfo = new PageInfo<>(list);
        return RestResponseUtil.success(pageInfo);
    }


    /**任务管理——筹划任务——详情
     * 根据图id,查询整图信息
     * @param mapId 图id
     * @return  图信息
     */
    @ApiOperation(value = "新加 根据图id,查询整图信息", notes = "新加 根据图id,查询整图信息.")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/checkInfo")
    public RestResponse<map_data> getMapDataById(
            @ApiParam(value = "图Id") @RequestParam(name = "mapId") String mapId) {

        map_data mapData= taskClassService.getMapDataById(mapId);
        return RestResponseUtil.success(mapData);
    }




    private wm_task_index getTaskIndex(@RequestParam(name = "class_id") @ApiParam(value = "种类ID") String classId, @RequestParam(name = "task_year", required = false) @ApiParam("年份") String taskYear) {
        wm_task_index taskIndex = new wm_task_index();
        if (classId != null && classId.length() > 0) {
            taskIndex.setTask_class_id(classId);
        }
        if (taskYear != null && taskYear.length() > 0) {
            taskIndex.setTask_year(taskYear);
        }
        taskIndex.setTask_state(EnumTaskState.JHXD.getTaskStateString());

        return taskIndex;
    }

    /**任务管理——筹划任务——导出
     * @param httpServletResponse
     * @param classID
     * @param taskYear
     * @return
     */
    @ApiOperation(value = "获取筹划任务分类列表导出（未分配部门）", notes = "获取筹划任务分类列表导出（未分配部门）")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/checklistexport")
    public RestResponse<String> getCheckListExport(
            HttpServletResponse httpServletResponse,
            @RequestParam(name = "class_id") String classID,
            @RequestParam(name = "task_year", required = false) String taskYear) {
        wm_task_index taskIndex = new wm_task_index();
        taskIndex.setTask_class_id(classID);
        taskIndex.setTask_year(taskYear);
        taskIndex.setTask_state(EnumTaskState.JHXD.getTaskStateString());
        //获取任务栏列表
        List<Object> tasklist = taskIndexService.selectTaskCheckListInfo(taskIndex);
        //获取种类列表
        List<TaskClassFieldModel> fieldList = taskClassService.getListFieldByClassId(classID);
        TaskExportUtil.exprotTaskInfoToExcel(fieldList, tasklist, httpServletResponse);

        return RestResponseUtil.success("导出完成！");
    }

    /**任务监控——部门——列表查询
     * @param classId 任务分类id
     * @param taskYear 任务年份
     * @param deptID　任务分配部门id
     * @param pageNum　当前页
     * @param pageSize　页容量
     * @return  任务监控部门查询列表
     */
    @ApiOperation(value = "获取筹划任务分部门列表（已分配部门）", notes = "获取筹划任务分部门列表（已分配部门）")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/deptchecklist")
    public RestResponse<PageInfo<Object>> getPageDeptCheckList(
            @ApiParam(value = "种类ID") @RequestParam(name = "class_id", required = false) String classId,
            @ApiParam(value = "年份") @RequestParam(name = "task_year", required = false) String taskYear,
            @ApiParam(value = "部门ID") @RequestParam(name = "dept_id") String deptID,
            @ApiParam(value = "任务状态") @RequestParam(name = "state", required = false) String state,
            @ApiParam(value = "页数") @RequestParam(name = "page_number", defaultValue = "1") Integer pageNum,
            @ApiParam(value = "页值") @RequestParam(name = "page_size", defaultValue = "50") Integer pageSize) {

        wm_task_index taskIndex = new wm_task_index();
        taskIndex.setTask_year(taskYear);
        taskIndex.setTask_state(state);
        taskIndex.setTask_class_id(classId);
        //如果是顶级部门，不用传部门id
        if (DeptId.CHART_INFORMATION_CENTER.equals(deptID)) {
            taskIndex.setDept_id(null);
        } else {
            taskIndex.setDept_id(deptID);
        }


        //获取任务列表
        List<Object> maplist = taskIndexService.selectDeptTaskCheckListInfo(taskIndex, pageNum,
                pageSize );

        PageInfo<Object> pageInfo = new PageInfo<>(maplist);
        return RestResponseUtil.createResponse(RestResponseCode.SUCCESS, pageInfo);

    }


    /**任务管理——部门——查询任务列表
     * @param classId　任务分类id
     * @param taskYear 任务年份
     * @param deptID　任务分配部门id
     * @param pageNum 当前页
     * @param pageSize　页容量
     * @return 任务管理部门查询列表
     */
    @ApiOperation(value = "获取筹划任务分部门列表（已分配部门）", notes = "获取筹划任务分部门列表（已分配部门）")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/selectTasklist")
    public RestResponse<PageInfo<Object>> selectTasklist(
            @ApiParam(value = "种类ID") @RequestParam(name = "class_id", required = false) String classId,
            @ApiParam(value = "年份") @RequestParam(name = "task_year", required = false) String taskYear,
            @ApiParam(value = "部门ID") @RequestParam(name = "dept_id") String deptID,
            @ApiParam(value = "页数") @RequestParam(name = "page_number", defaultValue = "1") Integer pageNum,
            @ApiParam(value = "页值") @RequestParam(name = "page_size", defaultValue = "50") Integer pageSize) {


        wm_task_index taskIndex = new wm_task_index();
        taskIndex.setTask_year(taskYear);
        //如果是顶级部门，不用传部门id
        if (DeptId.CHART_INFORMATION_CENTER.equals(deptID)) {
            taskIndex.setDept_id(null);
        } else {
            taskIndex.setDept_id(deptID);
        }
        taskIndex.setTask_state(EnumTaskState.JHXD.getTaskStateString());
        taskIndex.setTask_class_id(classId);

        //获取任务列表
        List<Object> maplist = taskIndexService.selectDeptTaskCheckListInfo(taskIndex,pageNum,
                pageSize);

        PageInfo<Object> pageInfo = new PageInfo<>(maplist);
        return  RestResponseUtil.createResponse(RestResponseCode.SUCCESS, pageInfo);

    }



    @ApiOperation(value = "获取筹划任务分部门列表导出（已分配部门）", notes = "获取筹划任务分部门列表导出（已分配部门）")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/deptchecklistexport")
    public RestResponse<String> getDeptCheckListExport(
            HttpServletResponse httpServletResponse,
            @RequestParam(name = "class_id") String classId,
            @RequestParam(name = "task_year", required = false) String taskYear,
            @RequestParam(name = "dept_id") String deptID) {
        RestResponse<String> response = null;
        try {
            wm_task_index taskIndex = new wm_task_index();
                taskIndex.setTask_class_id(classId);
                taskIndex.setTask_year(taskYear);
                taskIndex.setDept_id(deptID);
            taskIndex.setTask_state(EnumTaskState.JHXD.getTaskStateString());
            List<Object> maplist = taskIndexService.selectDeptTaskCheckListInfo(taskIndex,0 ,0 );
            List<TaskClassFieldModel> fieldList = taskClassService.getListFieldByClassId(classId);
            TaskExportUtil.exprotTaskInfoToExcel(fieldList, maplist, httpServletResponse);
            response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "导出完成！");
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }

    @ApiOperation(value = "获取未分配部门的海图资料任务列表", notes = "获取未分配部门的海图资料任务列表")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/maplist")
    public RestResponse<PageInfo<TaskMapModel>> getPageMapDataByTree(
            @ApiParam(value = "种类ID") @RequestParam(name = "calss_id") String classId,
            @ApiParam(value = "年份") @RequestParam(name = "task_year", required = false) String taskYear,
            @ApiParam(value = "页数") @RequestParam(name = "page_number", defaultValue = "1") Integer pageNum,
            @ApiParam(value = "页值") @RequestParam(name = "page_size", defaultValue = "50") Integer pageSize) {
        RestResponse<PageInfo<TaskMapModel>> response = null;
        try {
            PageHelper.startPage(pageNum, pageSize);
            wm_task_index taskIndex = new wm_task_index();
            if (classId != null && classId.length() > 0) {
                taskIndex.setTask_class_id(classId);
            }
            if (taskYear != null && taskYear.length() > 0) {
                taskIndex.setTask_year(taskYear);
            }
            List<TaskMapModel> maplist = taskIndexService.selectMapTaskListInfo(taskIndex);
            PageInfo<TaskMapModel> pageInfo = new PageInfo<>(maplist);
            response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, pageInfo);
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }

    @ApiOperation(value = "获取已分配部门的海图资料任务列表", notes = "获取已分配部门的海图资料任务列表")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/mapdeptlist")
    public RestResponse<PageInfo<TaskMapModel>> getPageDeptMapDataByTree(
            @ApiParam(value = "种类ID") @RequestParam(name = "class_id") String classId,
            @ApiParam(value = "年份") @RequestParam(name = "task_year", required = false) String taskYear,
            @ApiParam(value = "部门ID") @RequestParam(name = "dept_id") String deptID,
            @ApiParam(value = "页数") @RequestParam(name = "page_number", defaultValue = "1") Integer pageNum,
            @ApiParam(value = "页值") @RequestParam(name = "page_size", defaultValue = "50") Integer pageSize) {
        RestResponse<PageInfo<TaskMapModel>> response = null;
        try {
            PageHelper.startPage(pageNum, pageSize);
            wm_task_index taskIndex = new wm_task_index();
            if (classId != null && classId.length() > 0) {
                taskIndex.setTask_class_id(classId);
            }
            if (taskYear != null && taskYear.length() > 0) {
                taskIndex.setTask_year(taskYear);
            }
            if (deptID != null && deptID.length() > 0) {
                taskIndex.setDept_id(deptID);
            }
            List<TaskMapModel> maplist = taskIndexService.selectDeptMapTaskListInfo(taskIndex);
            PageInfo<TaskMapModel> pageInfo = new PageInfo<>(maplist);
            response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, pageInfo);
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }

    @ApiOperation(value = "获取未分配部门的书表资料任务列表", notes = "获取未分配部门的书表资料任务列表")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/booklist")
    public RestResponse<PageInfo<TaskBookModel>> getPageBookDataByTree(
            @ApiParam(value = "种类ID" ) @RequestParam(name = "calss_id") String classId,
            @ApiParam(value = "年份") @RequestParam(name = "task_year", required = false) String taskYear,
            @ApiParam(value = "页数" ) @RequestParam(name = "page_number", defaultValue = "1") Integer pageNum,
            @ApiParam(value = "页值") @RequestParam(name = "page_size", defaultValue = "50") Integer pageSize) {
        RestResponse<PageInfo<TaskBookModel>> response = null;
        try {
            PageHelper.startPage(pageNum, pageSize);
            wm_task_index taskIndex = new wm_task_index();
            if (classId != null && classId.length() > 0) {
                taskIndex.setTask_class_id(classId);
            }
            if (taskYear != null && taskYear.length() > 0) {
                taskIndex.setTask_year(taskYear);
            }
            List<TaskBookModel> maplist = taskIndexService.selectBookTaskListInfo(taskIndex);
            PageInfo<TaskBookModel> pageInfo = new PageInfo<>(maplist);
            response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, pageInfo);
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }

    @ApiOperation(value = "获取已分配部门的书表资料任务列表", notes = "获取已分配部门的书表资料任务列表")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/bookdeptlist")
    public RestResponse<PageInfo<TaskBookModel>> getPageDeptBookDataByTree(
            @ApiParam(value = "种类ID") @RequestParam(name = "calss_id") String classId,
            @ApiParam(value = "年份") @RequestParam(name = "task_year", required = false) String taskYear,
            @ApiParam(value = "部门ID") @RequestParam(name = "dept_id") String deptID,
            @ApiParam(value = "页数") @RequestParam(name = "page_number", defaultValue = "1") Integer pageNum,
            @ApiParam(value = "页值") @RequestParam(name = "page_size", defaultValue = "50") Integer pageSize) {
        RestResponse<PageInfo<TaskBookModel>> response = null;
        try {
            PageHelper.startPage(pageNum, pageSize);
            wm_task_index taskIndex = new wm_task_index();
            if (classId != null && classId.length() > 0) {
                taskIndex.setTask_class_id(classId);
            }
            if (taskYear != null && taskYear.length() > 0) {
                taskIndex.setTask_year(taskYear);
            }
            if (deptID != null && deptID.length() > 0) {
                taskIndex.setDept_id(deptID);
            }
            List<TaskBookModel> maplist = taskIndexService.selectDeptBookTaskListInfo(taskIndex);
            PageInfo<TaskBookModel> pageInfo = new PageInfo<>(maplist);
            response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, pageInfo);
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }

    @ApiOperation(value = "获取未分配部门的应急保障任务列表", notes = "获取未分配部门的应急保障任务列表")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/otherslist")
    public RestResponse<PageInfo<TaskOthersModel>> getPageOthersTaskByTree(
            @ApiParam(value = "种类ID") @RequestParam(name = "calss_id") String classId,
            @ApiParam(value = "年份") @RequestParam(name = "task_year", required = false) String taskYear,
            @ApiParam(value = "页数") @RequestParam(name = "page_number", defaultValue = "1") Integer pageNum,
            @ApiParam(value = "页值") @RequestParam(name = "page_size", defaultValue = "50") Integer pageSize) {
        RestResponse<PageInfo<TaskOthersModel>> response = null;
        try {
            PageHelper.startPage(pageNum, pageSize);
            wm_task_index taskIndex = new wm_task_index();
            if (classId != null && classId.length() > 0) {
                taskIndex.setTask_class_id(classId);
            }
            if (taskYear != null && taskYear.length() > 0) {
                taskIndex.setTask_year(taskYear);
            }
            List<TaskOthersModel> maplist = taskIndexService.selectOthersTaskListInfo(taskIndex);
            PageInfo<TaskOthersModel> pageInfo = new PageInfo<>(maplist);
            response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, pageInfo);
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }

    @ApiOperation(value = "获取已分配部门的应急保障任务列表", notes = "获取已分配部门的应急保障任务列表")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/othersdeptlist")
    public RestResponse<PageInfo<TaskOthersModel>> getPageDeptOthersTaskByTree(
            @ApiParam(value = "种类ID") @RequestParam(name = "calss_id") String classId,
            @ApiParam(value = "年份") @RequestParam(name = "task_year", required = false) String taskYear,
            @ApiParam(value = "部门ID") @RequestParam(name = "dept_id") String deptID,
            @ApiParam(value = "页数") @RequestParam(name = "page_number", defaultValue = "1") Integer pageNum,
            @ApiParam(value = "页值") @RequestParam(name = "page_size", defaultValue = "50") Integer pageSize) {
        RestResponse<PageInfo<TaskOthersModel>> response = null;
        try {
            PageHelper.startPage(pageNum, pageSize);
            wm_task_index taskIndex = new wm_task_index();
            if (classId != null && classId.length() > 0) {
                taskIndex.setTask_class_id(classId);
            }
            if (taskYear != null && taskYear.length() > 0) {
                taskIndex.setTask_year(taskYear);
            }
            if (deptID != null && deptID.length() > 0) {
                taskIndex.setDept_id(deptID);
            }
            List<TaskOthersModel> maplist = taskIndexService.selectDeptOthersTaskListInfo(taskIndex);
            PageInfo<TaskOthersModel> pageInfo = new PageInfo<>(maplist);
            response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, pageInfo);
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }

    @ApiOperation(value = "监控详情获取", notes = "监控详情获取")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/checklistdetail")
    public RestResponse<List<Object>> getCheckListDetailInfo(
            @RequestParam(name = "class_id") String classId,
            @RequestParam(name = "task_data_id") String dataId) {
        RestResponse<List<Object>> response = null;
        try {
            wm_task_index taskIndex = new wm_task_index();
            if (classId != null && classId.length() > 0) {
                taskIndex.setTask_class_id(classId);
            }
            if (dataId != null && dataId.length() > 0) {
                taskIndex.setTask_data_id(dataId);
            }
            List<Object> tasklist = taskIndexService.selectTaskCheckListInfo(taskIndex);
            response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, tasklist);
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }
}
