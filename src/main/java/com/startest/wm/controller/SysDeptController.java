package com.startest.wm.controller;

import com.startest.wm.model.SysOrganizationTree;
import com.startest.wm.model.SysUserModel;
import com.startest.wm.model.TaskClassFieldModel;
import com.startest.wm.pojo.*;
import com.startest.wm.service.SysDeptService;
import com.startest.wm.utils.GlobalLoginInfoUtil;
import com.startest.wm.utils.UUIDGeneratorUtil;
import com.startest.wm.utils.customresponse.RestResponse;
import com.startest.wm.utils.customresponse.RestResponseCode;
import com.startest.wm.utils.customresponse.RestResponseUtil;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-04-02 10:40
 * @描述 系统部门操作模块相关API
 **/
@Api(tags = "系统部门操作模块相关API")
@RestController
@RequestMapping(value = "/sys/dept/")
public class SysDeptController {

    private static final Logger log = LoggerFactory.getLogger(SysDeptController.class);

    @Autowired
    SysDeptService sysDeptService;


    /**质控参谋——个人工作质量评估——获取作业室树列表
     * @param request
     * @param deptID
     * @return
     */
    @ApiOperation("获取初始化部门树列表")
    @GetMapping(value = "getDeptTreeList")
    public RestResponse<sys_dept> getDeptTreeList(
            HttpServletRequest request,
            @ApiParam(value = "部门ID") @RequestParam(name = "dept_id", required = false) String deptID) {
        RestResponse<sys_dept> response = null;
        try {
            /*
             * 1、根据登录账户的角色名称判断是否是管理员登录
             * 2、如果是管理员登录则查询所有的部门，否则根据部门ID是否为空区分机关和作业室主任角色显示当前部门下的参与任务和考评的部门
             * */
            boolean isAdmin = GlobalLoginInfoUtil.isAdminLogin(request);
            sys_dept sysDept = sysDeptService.getDeptTree(deptID, isAdmin);
            if (sysDept != null) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, sysDept);
            } else {
                response = RestResponseUtil.note("请先添加系统相关部门信息！");
            }
        } catch (Exception e) {
            log.error("初始化部门树失败：", e);
            response = RestResponseUtil.note("系统异常，请联系管理员！");
        }
        return response;
    }


    @ApiOperation(value = "添加部门", notes = "新增部门")
    @PostMapping(value = "addDept")
    public RestResponse<String> addDept(
            @ApiParam(value = "部门父ID", required = true) @RequestParam(name = "dept_pid") String deptPID,
            @ApiParam(value = "部门名称", required = true) @RequestParam(name = "dept_name") String deptName,
            @ApiParam(value = "部门顺序", required = true) @RequestParam(name = "dept_order") Integer deptOrder,
            @ApiParam(value = "是否参与任务和考评", required = true) @RequestParam(name = "flag") Integer flag,
            @ApiParam(value = "备注") @RequestParam(name = "remark", required = false) String remark) {
        RestResponse<String> response = null;
        try {
            sys_dept sysDept = new sys_dept();
            sysDept.setDept_id(UUIDGeneratorUtil.getUUIDWithoutLineAndToLower());
            sysDept.setDept_pid(deptPID);
            sysDept.setDept_name(deptName);
            sysDept.setDept_order(deptOrder);
            sysDept.setFlag(flag);
            sysDept.setRemark(remark);
            //判断新的部门名称是否已经存在
            boolean isExistDept = sysDeptService.isDeptExist(sysDept.getDept_id(), sysDept.getDept_pid(), sysDept.getDept_name());
            if (!isExistDept) {
                int res = sysDeptService.addDept(sysDept);
                if (res > 0) {
                    response = RestResponseUtil.success("添加成功！");
                } else {
                    response = RestResponseUtil.note("添加失败！");
                }
            } else {
                response = RestResponseUtil.note("部门已经存在！");
            }
        } catch (Exception e) {
            log.error("添加部门失败：", e);
            response = RestResponseUtil.note("系统异常，请联系管理员！");
        }
        return response;
    }


    @ApiOperation(value = "根据部门ID删除部门", notes = "根据部门ID删除部门")
    @PostMapping(value = "deleteDeptByID")
    public RestResponse<String> deleteDeptByID(
            HttpServletRequest request,
            @ApiParam(value = "部门ID", required = true) @RequestParam(name = "dept_id") String deptID) {
        RestResponse<String> response = null;
        try {
            //判断当前部门下是否存在其他部门，存在则不能删除,无论是不是管理员，都查看所有部门
            List<sys_dept> sysDeptList = sysDeptService.getChildDeptByID(deptID, true);
            if (sysDeptList != null && sysDeptList.size() > 0) {
                response = RestResponseUtil.note("请先移除下属部门后再删除！");
            } else {
                //判断当前部门下是否存在人员，存在则不能删除
                boolean result = sysDeptService.isDeptContainUsers(deptID);
                if (result) {
                    response = RestResponseUtil.note("部门下已存在人员，无法删除！");
                } else {
                    int res = sysDeptService.deleteDeptByID(deptID);
                    if (res > 0) {
                        response = RestResponseUtil.success("删除成功！");
                    } else {
                        response = RestResponseUtil.note("删除失败！");
                    }
                }
            }
        } catch (Exception e) {
            log.error("删除部门失败：", e);
            response = RestResponseUtil.note("系统异常，请联系管理员！");
        }
        return response;
    }


    @ApiOperation("根据部门ID获取部门信息")
    @GetMapping(value = "getDeptInfo")
    public RestResponse<sys_dept> getDeptInfo(
            @ApiParam(value = "部门ID", required = true) @RequestParam(name = "dept_id") String deptID) {
        RestResponse<sys_dept> response = null;
        try {
            sys_dept sysDept = sysDeptService.getDeptByID(deptID);
            if (sysDept != null) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, sysDept);
            } else {
                response = RestResponseUtil.note("此部门不存在！");
            }
        } catch (Exception e) {
            log.error("系统异常：", e);
            response = RestResponseUtil.note("系统异常，请联系管理员！");
        }
        return response;
    }


    @ApiOperation("更新部门信息")
    @PostMapping(value = "updateDeptInfo")
    public RestResponse<String> updateDeptInfo(
            @ApiParam(value = "部门对象", required = true) @RequestBody sys_dept sysDept) {
        RestResponse<String> response = null;
        try {
            if (sysDept.getDept_name() == null || "".equals(sysDept.getDept_name())) {
                response = RestResponseUtil.note("更新失败，部门名称不能为空！");
            } else {
                boolean isExistDept = sysDeptService.isDeptExist(sysDept.getDept_id(), sysDept.getDept_pid(), sysDept.getDept_name());
                if (!isExistDept) {
                    int count = sysDeptService.updateDept(sysDept);
                    if (count > 0) {
                        response = RestResponseUtil.success("修改成功！");
                    } else {
                        response = RestResponseUtil.note("更新失败！");
                    }
                } else {
                    response = RestResponseUtil.note("部门已经存在！");
                }
            }
        } catch (Exception e) {
            log.error("更新部门失败：", e);
            response = RestResponseUtil.note("系统异常，请联系管理员！");
        }
        return response;
    }


    /**任务管理——筹划任务分类--获取所有可分配部门
     * @return
     */
    @ApiOperation("获取所有科室列表")
    @GetMapping(value = "getAllZuoyeshiList")
    public RestResponse<List<TaskClassFieldModel>> getAllZuoyeshiList() {
        RestResponse<List<TaskClassFieldModel>> response = null;
        try {
            /*
             * 此处获取作业室列表是为了分配任务使用，所以传递参数为false，只获取参与任务和考评的作业室
             * */
            List<TaskClassFieldModel> listZys = sysDeptService.getAllZuoyeshiModel(false);
            response = RestResponseUtil.success("", listZys);
        } catch (Exception e) {
            log.error("系统异常：", e);
            response = RestResponseUtil.note("系统异常!");
        }
        return response;
    }


    @ApiOperation("获取科室下所有部门")
    @GetMapping(value = "getZuoyeshiDepts")
    public RestResponse<List<sys_dept>> getZuoyeshiDepts(
            @ApiParam(value = "部门ID", required = true) @RequestParam(name = "dept_id") String deptID) {
        RestResponse<List<sys_dept>> response = null;
        try {
            /*
             * 此处获取作业室列表是为了分配任务使用，所以传递参数为false，只获取参与任务和考评的作业室
             * */
            List<sys_dept> sysDeptList = sysDeptService.getZuoyeshiDepts(deptID, false);
            response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, sysDeptList);
        } catch (Exception e) {
            log.error("系统异常：", e);
            response = RestResponseUtil.note("系统异常，请联系管理员！");
        }
        return response;
    }


    /*@ApiOperation("获取部门岗位人员组织目录树")
    @GetMapping(value = "getDeptStationUserTree")
    public RestResponse<SysOrganizationTree> getDeptStationUserTree(
            HttpServletRequest request,
            @ApiParam(value = "部门ID") @RequestParam(name = "dept_id", required = false) String deptID) {
        RestResponse<SysOrganizationTree> response = null;
        try {
            boolean isAdmin = GlobalLoginInfoUtil.isAdminLogin(request);
            SysOrganizationTree sysOrganizationTree = sysDeptService.getSysOrganizationTree(deptID, isAdmin);
            response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, sysOrganizationTree);
        } catch (Exception e) {
            log.error("系统异常：", e);
            response = RestResponseUtil.note("系统异常，请联系管理员！");
        }
        return response;
    }*/


    @ApiOperation("获取部门下所有人员列表")
    @GetMapping(value = "getDeptUserModelList")
    public RestResponse<List<SysUserModel>> getDeptUsers(
            @ApiParam(value = "部门ID") @RequestParam(name = "dept_id", required = false) String deptID) {
        RestResponse<List<SysUserModel>> response = null;
        try {
            /*
             * 此处获取作业室列表是为了分配任务使用，所以传递参数为false，只获取参与任务和考评的作业室
             * */
            List<SysUserModel> sysUserModelList = sysDeptService.getDeptUserModelList(deptID, false);
            response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, sysUserModelList);
        } catch (Exception e) {
            log.error("系统异常：", e);
            response = RestResponseUtil.note("系统异常，请联系管理员！");
        }
        return response;
    }


    /**任务监控——部门——获取部门树列表
     * @return
     */
    @ApiOperation("获取所有科室树形")
    @GetMapping(value = "getAllZuoyeshi")
    public RestResponse<SysOrganizationTree> getAllZuoyeshi() {
        RestResponse<SysOrganizationTree> response = null;
        try {
            //boolean isAdmin = GlobalLoginInfoUtil.isAdminLogin(request);
            /*
             * 此处获取作业室列表是为了分配任务使用，所以传递参数为false，只获取参与任务和考评的作业室
             * */
            SysOrganizationTree sysOrganizationTree = sysDeptService.getAllZuoyeshi(false);
            response = RestResponseUtil.success("", sysOrganizationTree);
        } catch (Exception e) {
            log.error("系统异常：", e);
            response = RestResponseUtil.note("系统异常，请联系管理员！");
        }
        return response;
    }
}

