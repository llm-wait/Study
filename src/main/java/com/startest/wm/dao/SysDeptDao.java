package com.startest.wm.dao;

import com.startest.wm.model.SysUserModel;
import com.startest.wm.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-04-02 9:40
 * @描述 部门管理数据库映射
 **/
@Mapper
public interface SysDeptDao {

    /**
     * @Description: 新增部门信息
     * @Param: [dept] 部门对象
     * @return: int
     **/
    int insertDept(sys_dept dept);

    /**
     * @Description: 根据部门ID删除部门对象
     * @Param: [deptID] 部门ID
     * @return: int
     **/
    int deleteDept(@Param("dept_id") String deptID);

    /**
     * @Description: 更新部门信息
     * @Param: [dept] 部门对象
     * @return: int
     **/
    int updateDept(sys_dept dept);

    /**
     * @Description: 根据部门ID查询部门信息
     * @Param: [deptID] 部门ID
     * @return: com.startest.wm.pojo.sys_dept
     **/
    sys_dept queryDeptByID(@Param("dept_id") String deptID);


    /**查询部门根节点
     * @return 根部门信息对象
     */
    sys_dept queryRootDept();

    /**
     * @Description: 根据部门名称查询部门
     * @Param: [deptName]部门名称
     * @return: java.util.List<com.startest.wm.pojo.sys_dept>
     **/
    List<sys_dept> queryDeptByName(@Param("dept_name") String deptName);


    /**根据部门ID查询子部门信息
     * @param deptID 部门ID
     * @param isAdmin 是否查询机关部门，机关部门不参与任务和考评
     * @return
     */
    List<sys_dept> queryChildDeptByID(@Param("dept_id") String deptID,@Param("isAdmin") boolean isAdmin);

    /**
     * @Description: 查询所有部门信息
     * @Param: []
     * @return: java.util.List<com.startest.wm.pojo.sys_dept>
     **/
    List<sys_dept> queryDeptAll(@Param("isAdmin")boolean isAdmin);

    /**
     * @Description: 根据部门ID查询部门岗位信息
     * @Param: [deptID]
     * @return: java.util.List<com.startest.wm.pojo.sys_station>
     **/
    List<sys_station> queryStationByDeptID(@Param("dept_id") String deptID);


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
     * @Description: 获取部门包含的人员关系列表
     * @Param: [deptID, stationID, userID]部门ID,岗位ID，人员ID
     * @return: java.util.List<com.startest.wm.pojo.sys_dept_station_user>
     **/
    List<sys_dept_station_user> getDeptStationUserReal(@Param("dept_id") String dept_id, @Param("station_id") String station_id, @Param("user_id") String userID);

    /**
     * @Description: 获取部门包含的人员关系列表
     * @Param: [dept_id]
     * @return: java.util.List<com.startest.wm.pojo.sys_dept_station_user>
     **/
    List<sys_dept_station_user> getDeptUserReals(@Param("dept_id") String dept_id);

    /**
     * @Description: 删除部门岗位人员关系对象
     * @Param: [deptID, stationID, userID]部门ID,岗位ID,用户ID
     * @return: int
     **/
    int deleteDeptStationUserReal(@Param("dept_id") String deptID, @Param("station_id") String stationID, @Param("user_id") String userID);

    int deleteDeptStationUserReal2(@Param("user_id") String userID);

    /**
     * @Description: 删除部门岗位下所有人员记录
     * @Param: [deptID, stationID]部门ID，岗位ID
     * @return: int
     **/
    int deleteDeptStationUserRealAll(@Param("dept_id") String deptID, @Param("station_id") String stationID);

    /**
     * @Description: 获取部门岗位下人员列表
     * @Param: [deptID, stationID] 部门ID，岗位ID
     * @return: java.util.List<com.startest.wm.pojo.sys_user>
     **/
    List<sys_user> queryDeptStationUsers(@Param("deptID") String deptID,@Param("stationID") String stationID);

    /**
     * @Description: 获取部门下所有人员列表
     * @Param: [deptID] 部门ID
     * @return: java.util.List<com.startest.wm.pojo.sys_user>
     **/
    List<sys_user> queryDeptAllUsers(String deptID);

    /**
     * @Description: 判断部门是否已经存在
     * @Param: [deptID, deptPID,deptName]部门ID，部门PID,部门名称
     * @return: int
     **/
    int isDeptExist(@Param("dept_id") String deptID, @Param("dept_Pid") String deptPID, @Param("dept_name") String deptName);

    /**
     * @Description: 根据用户ID获取部门岗位人员关系记录
     * @Param: [user_id] 用户ID
     * @return: com.startest.wm.pojo.sys_dept_station_user
     **/
    sys_dept_station_user getSysDeptStationUser(@Param("user_id") String user_id);

    /**
     * @Description: 根据部门岗位ID查询用户模型列表
     * @Param: [deptID, stationID]
     * @return: java.util.List<com.startest.wm.model.SysUserModel>
     **/
    List<SysUserModel> queryUserModelByReal(@Param("deptID")String deptID, @Param("stationID")String stationID);
}
