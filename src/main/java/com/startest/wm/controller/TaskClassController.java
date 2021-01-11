package com.startest.wm.controller;

import com.startest.wm.model.SysOrganizationTree;
import com.startest.wm.model.TaskClassFieldModel;
import com.startest.wm.pojo.vo.CartographyTaskVo;
import com.startest.wm.pojo.wm_task_class;
import com.startest.wm.pojo.wm_task_info;
import com.startest.wm.service.TaskClassService;
import com.startest.wm.service.TaskInfoService;
import com.startest.wm.utils.UUIDGeneratorUtil;
import com.startest.wm.utils.customresponse.RestResponse;
import com.startest.wm.utils.customresponse.RestResponseCode;
import com.startest.wm.utils.customresponse.RestResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 杨世凯
 * @公司 北京星天科技
 * @创建时间 2020-04-09-14:37
 * @描述 任务管理分类控制器
 */
@Controller
@Api(tags = "任务管理页面操作相关API")
@RequestMapping("/task/taskclass")
@Validated
public class TaskClassController {
    private final TaskClassService taskClassService;
    private final TaskInfoService taskInfoService;

    public TaskClassController(TaskClassService taskClassService, TaskInfoService taskInfoService) {
        this.taskClassService = taskClassService;
        this.taskInfoService = taskInfoService;
    }

    /**根据任务分类对象，查询分类对象空缺信息
     * @param classId 种类id
     * @param parentId 种类父id
     * @param className 种类名称
     * @param classType 种类类型（0：筹划分类；1：任务过程分类）
     * @return 任务分类对象集合
     */
    @ApiOperation("检索任务分类信息")
    @RequestMapping(method = RequestMethod.GET, value = "/select")
    @ResponseBody
    public RestResponse<List<wm_task_class>> selectTaskClassList(
            @ApiParam(value = "种类id") @RequestParam(name = "class_id", required = false) String classId,
            @ApiParam(value = "父项id") @RequestParam(name = "parent_id", required = false) String parentId,
            @ApiParam(value = "分类名称") @RequestParam(name = "class_name", required = false) String className,
            @ApiParam(value = "种类类型：0筹划分类；1：任务过程分类") @RequestParam(name = "class_type", required = false) Integer classType) {

            wm_task_class taskClass = new wm_task_class();
                taskClass.setClass_id(classId);
                taskClass.setParent_id(parentId);
                taskClass.setClass_name(className);
                taskClass.setClass_type(classType);

            List<wm_task_class> taskClassList = taskClassService.selectClassInfo(taskClass);
            return RestResponseUtil.createResponse(RestResponseCode.SUCCESS, taskClassList);
    }

    /**任务管理——筹划任务——获取筹划任务树
     * 任务监控
     * 通用任务分类，获取完整的树结构
     * @param type 任务分类类型，0筹划任务，1任务过程分类
     * @return  任务分类树结构
     */
    @ApiOperation("任务类型树")
    @RequestMapping(method = RequestMethod.GET, value = "/gettree")
    @ResponseBody
    public RestResponse<List<wm_task_class>> getTaskClassTreeList(
         @Min(0)@Max(1) @ApiParam(value = "种类id", required = true) @RequestParam(name = "class_type", defaultValue = "0") Integer type) {

            List<wm_task_class> taskClassList = taskClassService.getTreeTaskClassInfo(type);
            return RestResponseUtil.createResponse(RestResponseCode.SUCCESS, taskClassList);
    }

    /**任务管理——筹划任务分类——获取修改分类树列表
     * 下，获取  修改分类树型结构
     * @param strClassId 当前任务分类id
     * @return  修改任务分类树结构
     */
    @ApiOperation("类型修改种类树")
    @RequestMapping(method = RequestMethod.GET, value = "/gettreeEdit")
    @ResponseBody
    public RestResponse<List<wm_task_class>> getTaskClassTreeEditList(
         @NotNull @ApiParam(value = "种类id", required = true) @RequestParam(name = "calss_id") String strClassId) {
            List<wm_task_class> taskClassList = taskClassService.getTreeTaskClassEditList(strClassId);
            return RestResponseUtil.createResponse(RestResponseCode.SUCCESS, taskClassList);
    }

    /**任务管理——部门--获取部门树列表
     * @return 部门树列表
     */
    @ApiOperation(value = "获取任务部门树", notes = "获取任务部门树")
    @RequestMapping(method = RequestMethod.GET, value = "/getdepttree")
    @ResponseBody
    public RestResponse<SysOrganizationTree> getTaskDeptTreeList() {
            SysOrganizationTree deptTree = taskClassService.getTreeDeptListInfo();
            return RestResponseUtil.success(deptTree);
    }

    /**根据任务种类id,查询任务分类信息
     * @param classId 任务种类id
     * @return 任务分类信息
     */
    @ApiOperation(value = "通过分类ID获取分类信息", notes = "通过分类ID获取分类信息")
    @RequestMapping(method = RequestMethod.GET, value = "/selectbyid")
    @ResponseBody
    public RestResponse<wm_task_class> getTaskClassById(
            @ApiParam(value = "任务分类ID", required = true) @RequestParam(name = "class_id") String classId) {

        wm_task_class tClass = new wm_task_class();
        tClass.setClass_id(classId);
        List<wm_task_class> taskClassList = taskClassService.selectClassInfo(tClass);

        if (taskClassList == null || taskClassList.size() == 0) {
            return RestResponseUtil.note("查询结果为空！");
        }

        return RestResponseUtil.createResponse(RestResponseCode.SUCCESS, taskClassList.get(0));
    }

    /**任务分类——筹划任务分类——筹划任务树添加子分类
     * 添加筹划任务分类
     * @param parentId 父种类id
     * @param className 名称
     * @param order 排序
     * @param remark 备注
     * @return 成功/失败
     */
    @ApiOperation(value = "添加分类", notes = "添加分类")
    @RequestMapping(method = RequestMethod.GET, value = "/add")
    @ResponseBody
    public RestResponse<String> addTaskClassInfo(
         @NotNull   @ApiParam(value = "父节点ID", required = true) @RequestParam(name = "parent_id") String parentId,
         @NotNull   @ApiParam(value = "分类名称", required = true) @RequestParam(name = "class_name") String className,
         @NotNull   @ApiParam(value = "次序", required = true) @RequestParam(name = "class_order") Integer order,
            @ApiParam(value = "备注") @RequestParam(name = "class_remark", required = false) String remark) {

            if (taskClassService.isTaskClassExist(null, parentId, className)) {
              return RestResponseUtil.note("分类名称已存在，请重新输入！");
            }

            wm_task_class tClass = new wm_task_class();
            tClass.setClass_id(UUIDGeneratorUtil.getUUIDWithoutLineAndToUpper());
            tClass.setParent_id(parentId);
            tClass.setClass_name(className);
            tClass.setClass_order(order);
            tClass.setClass_remark(remark);
            tClass.setClass_type(0);
            int res = taskClassService.insertClassInfo(tClass);
            if (res > 0) {
                return RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "任务分类信息添加成功！");
            } else {
                return RestResponseUtil.note("任务分类信息添加失败！");
            }
    }

    /**任务管理--筹划任务分类——筹划任务分类树修改
     * @param classId 种类id
     * @param className 种类名称
     * @param classOrder 排序
     * @param classRemark 备注
     * @return 成功
     */
    @ApiOperation(value = "编辑分类", notes = "编辑分类")
    @RequestMapping(method = RequestMethod.GET, value = "/update")
    @ResponseBody
    public RestResponse<String> editTaskClassInfo(
            @NotNull @ApiParam(value = "任务分类Id", required = true) @RequestParam(name = "class_id") String classId,
            @NotNull @ApiParam(value = "任务分类名称", required = true) @RequestParam(name = "class_name") String className,
            @NotNull @ApiParam(value = "次序", required = true) @RequestParam(name = "class_order") Integer classOrder,
            @ApiParam(value = "备注", required = true) @RequestParam(name = "class_remark") String classRemark) {

        if (taskClassService.isTaskClassExist(classId, null, className)) {
            return RestResponseUtil.createResponse(RestResponseCode.NOTE, "分类名称已存在，请重新输入！");
        }
        wm_task_class tClass = new wm_task_class();
        tClass.setClass_id(classId);
        tClass.setClass_name(className);
        tClass.setClass_order(classOrder);
        tClass.setClass_remark(classRemark);
        int res = taskClassService.editPartClassInfo(tClass);
        if (res > 0) {
            return RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "任务分类信息修改成功！");
        } else {
            return RestResponseUtil.createResponse(RestResponseCode.NOTE, "任务分类信息修改失败！");
        }
    }

    /**任务管理——筹划任务分类——筹划任务树删除子分类
     * @param classId 分类id
     * @return 成功
     */
    @ApiOperation(value = "删除分类（其下有子节点将无法进行删除）", notes = "删除分类（其下有子节点将无法进行删除）")
    @RequestMapping(method = RequestMethod.GET, value = "/delete")
    @ResponseBody
    public RestResponse<String> deleteTaskClassInfo(
            @ApiParam(value = "任务分类Id", required = true) @RequestParam(name = "class_id") String classId) {

        if (taskClassService.isTaskClassExist(null, classId, null)) {
            return RestResponseUtil.note("请先删除子分类");
        }

        int res = taskClassService.deleteClassInfo(classId);
        if (res > 0) {
            return RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "任务分类信息删除成功！");
        } else {
            return RestResponseUtil.createResponse(RestResponseCode.NOTE, "任务分类信息删除失败！");
        }
    }

    @ApiOperation(value = "获取孩子节点中最大的次序值", notes = "获取孩子节点中最大的次序值")
    @RequestMapping(method = RequestMethod.GET, value = "/getchildmax")
    @ResponseBody
    public RestResponse<Integer> getChildNodeMaxOrderNumber(
            @NotNull @ApiParam(value = "父节点ID", required = true) @RequestParam(name = "parent_id") String parentId) {

        return RestResponseUtil.success(taskClassService.getMaxOrderNumberOnParentNode(parentId));
    }

    @ApiOperation(value = "判断分类名称是否已存在", notes = "判断分类名称是否已存在")
    @RequestMapping(method = RequestMethod.GET, value = "/nameisexist")
    @ResponseBody
    public RestResponse<Boolean> getClassNameIsExist(
            @ApiParam(value = "分类名称", required = true) @RequestParam(name = "class_name") String className) {

        return RestResponseUtil.success(taskClassService.isTaskClassExist(null, null, className));
    }

    /**添加任务单详情
     * @param taskInfo 任务单对象
     * @return 添加成功
     * todo,没有controller映射
     */
    @ApiOperation(value = "新建任务", notes = "新建任务")
    @ResponseBody
    public RestResponse<String> addTaskInfo(@NotNull @ApiParam("任务单对象") @RequestBody wm_task_info taskInfo) {

        taskInfo.setTask_info_id(UUIDGeneratorUtil.getUUIDWithoutLineAndToUpper());

        int res = taskInfoService.insertTaskInfo(taskInfo);
        if (res > 0) {
            return RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "任务单信息添加成功！");
        } else {
            return RestResponseUtil.createResponse(RestResponseCode.FAIL, "任务单信息添加失败！");
        }
    }

    /**任务管理——筹划任务——制图任务列表表头
     * @param classId　任务类型id
     * @return  任务查询表头列表
     */
    @ApiOperation(value = "通过种类ID获取列表字段", notes = "通过种类ID获取列表字段")
    @RequestMapping(method = RequestMethod.GET, value = "/fieldbyid")
    @ResponseBody
    public RestResponse<List<TaskClassFieldModel>> getListFieldByClassId(
        @ApiParam(value = "分类ID", required = true) @RequestParam(name = "class_id",required = false) String classId) {

        return RestResponseUtil.success(taskClassService.getListFieldByClassId(classId));
    }


    /**弃用
     * @param classId
     * @return
     */
    @ApiOperation(value = "查询计划下达任务列表", notes = "查询计划下达任务列表")
    @RequestMapping(method = RequestMethod.GET, value = "/getTaskList")
    @ResponseBody
    public RestResponse<List> getTaskList(
            @NotNull    @ApiParam(value = "分类ID", required = true) @RequestParam(name = "class_id") String classId) {

        return RestResponseUtil.success(taskClassService.getTaskList(classId));
    }
}
