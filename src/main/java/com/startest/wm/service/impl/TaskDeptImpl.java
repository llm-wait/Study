package com.startest.wm.service.impl;

import com.startest.wm.config.exception.LoginException;
import com.startest.wm.dao.TaskClassDao;
import com.startest.wm.dao.TaskDeptDao;
import com.startest.wm.dao.TaskIndexDao;
import com.startest.wm.enums.task.EnumTaskState;
import com.startest.wm.pojo.sys_dept;
import com.startest.wm.pojo.sys_login;
import com.startest.wm.pojo.wm_task_dept;
import com.startest.wm.service.TaskDeptService;
import com.startest.wm.service.TaskIndexService;
import com.startest.wm.utils.GlobalLoginInfoUtil;
import com.startest.wm.utils.UUIDGeneratorUtil;
import com.startest.wm.utils.customresponse.RestResponse;
import com.startest.wm.utils.customresponse.RestResponseCode;
import com.startest.wm.utils.customresponse.RestResponseUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-04-10-8:23
 * @描述 分配部门逻辑接口实现类
 */
@Service
@Transactional
public class TaskDeptImpl implements TaskDeptService {
    @Autowired
    private TaskDeptDao taskDeptDao;

    @Autowired
    private TaskClassDao taskClassDao;
    @Autowired
    private TaskIndexDao taskIndexDao;
    @Autowired
    TaskIndexService taskIndexService;

    @Override
    public List<wm_task_dept> selectTaskDeptInfo(wm_task_dept taskDept) {
        return taskDeptDao.selectTaskDeptInfo(taskDept);
    }

    @Override
    public int insertTaskDeptInfo(wm_task_dept taskDept) {
        return taskDeptDao.insertTaskDeptInfo(taskDept);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public int insertTaskDeptInfoByList(List<wm_task_dept> list) {
        return taskDeptDao.insertTaskDeptInfoByList(list);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public int updateTaskDeptInfo(String taskIndexID, List<wm_task_dept> list) {
        taskDeptDao.deleteTaskDeptInfoByTaskIndexID(taskIndexID);
        return taskDeptDao.insertTaskDeptInfoByList(list);
    }

    @Override
    public int deleteTaskDeptInfoByTaskDeptID(String taskDeptId) {
        return taskDeptDao.deleteTaskDeptInfoByTaskDeptID(taskDeptId);
    }

    @Override
    public int deleteTaskDeptInfoByTaskIndexID(String taskIndexId) {
        return taskDeptDao.deleteTaskDeptInfoByTaskIndexID(taskIndexId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public int updateTaskDeptInfoByList(List<wm_task_dept> reslist) {
        for (wm_task_dept item : reslist) {
            taskDeptDao.deleteTaskDeptInfoByTaskIndexID(item.getTask_index_id());
        }
        return taskDeptDao.insertTaskDeptInfoByList(reslist);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public RestResponse<String> addTaskDeptInfoByList(List<sys_dept> jsonList) {
        /*
         * 多个任务分配两个部门
         *1,任务要看下是否已下达，下达的不能修改
         *2,任务要改变一下　分配状态
         *3,任务分配表删除原有记录,再增加记录
         * */

        //所有的
        String[] taskIndexIds = jsonList.get(0).getTask_index_id().trim().split(",");
        //可改的
        String[] notReleasedIds = taskDeptDao.selectTaskStateByIdsAndState(taskIndexIds,
                EnumTaskState.JHXD.getTaskStateString());

        if (notReleasedIds==null||notReleasedIds.length==0) {
            return RestResponseUtil.success("任务已下达，无法更改！");
        }

        List<wm_task_dept> list = new ArrayList<>();
        //得到每个部门
        wm_task_dept taskdept;
        for (sys_dept item : jsonList) {
            for (String s : notReleasedIds) {
                taskdept = new wm_task_dept();
                taskdept.setTask_dept_id(UUIDGeneratorUtil.getUUIDWithoutLineAndToUpper());
                taskdept.setTask_index_id(s);
                taskdept.setDept_id(item.getDept_id());
                taskdept.setDept_name(item.getDept_name());
                list.add(taskdept);
            }
        }
        taskDeptDao.delDeptByIndexId(notReleasedIds);
        taskDeptDao.insertTaskDeptInfoByList(list);
        //批量更新部门状态
        taskClassDao.updateDept(notReleasedIds);

        return RestResponseUtil.success("成功分配作业室！");
    }


    /**
     * 作业管理——生产任务——发送生产任务　todo 发送不成功　Request请求或种类ID不能为空！
     * @param classID　分类id
     * @param taskName 任务名称
     * @param taskType　任务类型
     * @param indexId　任务id
     * @param state 要改成的任务状态
     * @return 成功
     */
    @Override
    public RestResponse<String> updateTaskIndexStateInfo(HttpServletRequest request,String classID, String taskName, String taskType, String indexId, EnumTaskState state) throws LoginException {
        //得到任务要改成的状态
        String indexState = state.getTaskStateString();
        String strDeptID = null;
        String strDeptName = null;
        wm_task_dept taskDept = null;
        //如果是　　质检　或　制印
            //要获取　登陆人的　部门id和部门名称
        if (indexState.equals(EnumTaskState.RWZJ.getTaskStateString()) ||
                indexState.equals(EnumTaskState.ZY.getTaskStateString())) {
            //分类id 不为空
            if (request != null && classID != null && classID.length() > 0) {
                sys_login sLogin = GlobalLoginInfoUtil.getLoginUserInfo(request);
                if (sLogin != null) {
                    strDeptID = sLogin.getDept_id();
                    strDeptName = sLogin.getDept_name();
                    if (strDeptID != null && strDeptID.length() > 0) {

                    } else {
                        return  RestResponseUtil.createResponse(RestResponseCode.FAIL, "获取登录用户的部门信息为空！");
                    }
                } else {
                    return  RestResponseUtil.createResponse(RestResponseCode.FAIL, "用户登录信息为空！");
                }
            } else {
                return RestResponseUtil.createResponse(RestResponseCode.FAIL, "Request请求或种类ID不能为空！");
            }

            //如果是制印
            if (indexState.equals(EnumTaskState.ZY.getTaskStateString())) {
                //获取任务部门
                wm_task_dept tdept = new wm_task_dept();
                tdept.setTask_index_id(indexId);
                List<wm_task_dept> deptList = taskDeptDao.selectTaskDeptInfo(tdept);
                if (deptList != null && deptList.size() > 0) {
                    taskDept = deptList.get(0);
                } else {
                    return    RestResponseUtil.createResponse(RestResponseCode.FAIL, "获取分配部门信息为空！");
                }
            }
        }
        List<String> lstIDs = new ArrayList<>();
        lstIDs.add(indexId);
        //下发前 查询未分配年份，和分配作业室的id
//        taskIndexService.selectIsAllocation(lstIDs);
        List<String> reIds = new ArrayList<>();
        for (String id : lstIDs) {
            //查询未分配的id
            String ids = taskIndexDao.selectIsAllocation(id);
            if (ids != null) {
                reIds.add(ids);
            }
        }


        if (reIds.size() > 0) {
            return RestResponseUtil.note("请先分配作业室和年份");
        }
        //更新任务状态
        int res = taskIndexService.updateTaskState(indexId, indexState, strDeptID, strDeptName, classID, taskName, taskType, taskDept);
        if (res > 0) {
            return RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "任务索引状态信息更新成功！");
        } else {
            return  RestResponseUtil.createResponse(RestResponseCode.FAIL, "任务索引状态信息更新失败,请检查表单是否填写完整", "任务索引状态信息更新失败,请检查表单是否填写完整");
        }
    }


}
