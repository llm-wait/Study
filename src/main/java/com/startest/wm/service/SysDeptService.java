package com.startest.wm.service;

import com.startest.wm.model.SysOrganizationTree;
import com.startest.wm.model.SysUserModel;
import com.startest.wm.model.TaskClassFieldModel;
import com.startest.wm.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-04-02 10:18
 * @描述 系统部门操作服务接口
 **/
public interface SysDeptService {

    /**
     * @Description: 新增部门
     * @Param: [sysDept] 部门对象
     * @return: int
     **/
    int addDept(sys_dept sysDept);

    /**
     * @Description: 删除部门
     * @Param: [deptID] 部门ID
     * @return: int
     **/
    int deleteDeptByID(String deptID);

    /**
     * @Description: 更新部门
     * @Param: [sysDept] 部门对象
     * @return: int
     **/
    int updateDept(sys_dept sysDept);

    /**
     * @Description: 根据部门ID获取部门对象
     * @Param: [deptID] 部门ID
     * @return: com.startest.wm.pojo.sys_dept
     **/
    sys_dept getDeptByID(String deptID);

    /**
     * @Description: 根据部门名称查询部门
     * @Param: [deptName]部门名称
     * @return: java.util.List<com.startest.wm.pojo.sys_dept>
     **/
    List<sys_dept> getDeptByName(String deptName);

    /**
     * @Description: 获取部门列表
     * @Param: [deptID]部门ID
     * @return: com.startest.wm.pojo.sys_dept
     **/
    sys_dept getDeptTree(String deptID, boolean isAdmin);

    /**
     * @Description: 获取部门列表
     * @Param: [deptID] 部门ID
     * @return: java.util.List<com.startest.wm.pojo.sys_dept>
     **/
    List<sys_dept> getChildDeptByID(String deptID, boolean isAdmin);

    /**
     * @Description: 获取部门拥有的所有岗位
     * @Param: [deptID]
     * @return: java.util.List<com.startest.wm.pojo.sys_station>
     **/
    List<sys_station> getDeptStations(String deptID);

    /**
     * @Description: 获取部门拥有的所有人员
     * @Param: deptID　部门id
     * @Param: isAdmin　是否查询机关
     * @return: java.util.List<com.startest.wm.pojo.sys_user>
     **/
    List<sys_user> getDeptUserList(String deptID, boolean isAdmin);

    /**
     * @Description: 获取部门拥有的所有人员模型
     * @Param: [deptID]树根节点ID
     * @return: java.util.List<com.startest.wm.pojo.SysOrganizationTree>
     **/
    List<SysUserModel> getDeptUserModelList(String deptID, boolean isAdmin);

    /**
     * @Description: 获取部门岗位人员组织目录树
     * @Param: [deptID]树根节点ID
     * @return: java.util.List<com.startest.wm.pojo.SysOrganizationTree>
     **/
    SysOrganizationTree getSysOrganizationTree(String deptID, boolean isAdmin);

    /**
     * @Description: 获取部门人员组织目录树
     * @Param: [deptID]树根节点ID
     * @return: java.util.List<com.startest.wm.pojo.SysOrganizationTree>
     **/
    SysOrganizationTree getDeptUsers(String deptID, boolean isAdmin);

    /**
     * @Description: 获取科室部门人员组织目录树
     * @Param: [deptID]树根节点ID
     * @return: java.util.List<com.startest.wm.pojo.SysOrganizationTree>
     **/
    SysOrganizationTree getJgDeptUsers(String deptID, boolean isAdmin);

    /**
     * @Description: 获取部门岗位下人员列表
     * @Param: [deptID, stationID]部门ID，人员ID
     * @return: java.util.List<com.startest.wm.pojo.sys_user>
     **/
    List<sys_user> getDeptStationUsers(String deptID, String stationID);


    /**
     * @Description: 新增部门岗位人员关系记录
     * @Param: [sysDeptStationUser]部门岗位关系对象
     * @return: int
     **/
    int insertDeptStationUserReal(sys_dept_station_user sysDeptStationUser);

    /**
     * @Description: 更新部门岗位人员关系记录
     * @Param: [sysDeptStationUser]部门岗位关系对象
     * @return: int
     **/
    int updateDeptStationUserReal(sys_dept_station_user sysDeptStationUser);

    /**
     * @Description: 获取部门岗位人员关系对象
     * @Param: [deptID, stationID, userID] 部门ID,岗位ID,人员ID
     * @return: boolean
     **/
    boolean isDeptStationContainUser(String deptID, String stationID, String userID);

    /**
     * @Description: 判断部门下是否包含人员
     * @Param: [deptID]
     * @return: boolean
     **/
    boolean isDeptContainUsers(String deptID);

    /**
     * @Description: 删除部门岗位人员关系对象
     * @Param: [deptID, stationID, userID]部门ID,岗位ID,用户ID
     * @return: int
     **/
    int deleteDeptStationUserReal(String deptID, String stationID, String userID);

    /**
     * @Description: 删除部门岗位人员关系对象
     * @Param: [userID]用户ID
     * @return: int
     **/
    int deleteDeptStationUserReal2(String userID);


    /**
     * @Description: 判断部门是否已经存在
     * @Param: [deptID, deptName]部门ID，部门名称
     * @return: boolean
     **/
    boolean isDeptExist(@Param("dept_id") String deptID, @Param("dept_pid") String deptPID, @Param("dept_name") String deptName);

    /**
     * @Description: 获取机关下所有的科室树形
     * @Param: []
     * @return: com.startest.wm.model.SysOrganizationTree
     **/
    SysOrganizationTree getAllZuoyeshi(boolean isAdmin);

    /**
     * @Description: 获取机关下所有的科室列表
     * @Param: []
     * @return: java.util.List<com.startest.wm.model.TaskClassFieldModel>
     **/
    List<TaskClassFieldModel> getAllZuoyeshiModel(boolean isAdmin);

    /**
     * @Description: 获取科室下所有部门
     * @Param: [deptID]
     * @return: java.util.List<com.startest.wm.pojo.sys_dept>
     **/
    List<sys_dept> getZuoyeshiDepts(String deptID, boolean isAdmin);

    /**
     * @Description: 获取机关下所有的科室列表
     * @Param: []
     * @return: java.util.List<com.startest.wm.pojo.sys_dept>
     **/
    List<sys_dept> getAllZuoyeshiList(boolean isAdmin);


    sys_dept_station_user getSysDeptStationUser(@Param("user_id") String user_id);

    /**
     * @Description: 删除部门岗位下所有人员记录
     * @Param: [deptID, stationID]部门ID，岗位ID
     * @return: int
     **/
    int deleteDeptStationUserRealAll(String deptID, String stationID);

    /**
     * @Description: 获取单位和组别
     * @Param: [deptID, map, sysDeptListAll]部门ID，空的map，所有部门列表
     * @return: java.util.Map<java.lang.String, java.lang.String>
     **/
    Map<String, String> getDwZb(String deptID);

}
