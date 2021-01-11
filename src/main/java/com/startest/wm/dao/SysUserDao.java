package com.startest.wm.dao;

import com.startest.wm.model.SysUserModel;
import com.startest.wm.model.UserSearchModel;
import com.startest.wm.pojo.sys_dept;
import com.startest.wm.pojo.sys_user;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-03-30 10:29
 * @描述 系统用户数据库映射
 **/
public interface SysUserDao {
    /**
     * @Description: 新增用户
     * @Param: [sysUser]
     * @return: int
     **/
    int insertUser(sys_user sysUser);

    int insertUserByModel(SysUserModel sysUserModel);

    /**
     * @Description: 删除用户
     * @Param: [userID]用户ID
     * @return: int
     **/
    int deleteByUserID(String userID);

    /**
     * @Description: 更新用户
     * @Param: [sysUser]
     * @return: int
     **/
    int updateUser(sys_user sysUser);

    /**
     * @Description: 更新用户状态
     * @Param: [userID, state]用户ID 用户状态
     * @return: int
     **/
    int updateUserState(@Param("user_id") String userID,@Param("sfjy") int state);

    /**
     * @Description: 根据用户ID查询用户
     * @Param: [userID]
     * @return: com.startest.wm.pojo.sys_user
     **/
    sys_user queryByUserID(@Param("user_id")String userID);

    /**
     * @Description: 根据用户ID查询用户模型
     * @Param: [userID]
     * @return: com.startest.wm.model.SysUserModel
     **/
    SysUserModel queryUserModelByUserID(@Param("user_id")String userID);

    /**
     * @Description: 根据用户真实姓名查询用户
     * @Param: [userName]
     * @return: com.startest.wm.pojo.sys_user
     **/
    sys_user queryByName(String userName);

    /**
     * @Description: 根据用户登录名称查询用户
     * @Param: [loginName]
     * @return: com.startest.wm.pojo.sys_user
     **/
    sys_user queryByLoginName(String loginName);

    /**
     * @Description: 根据用户登录名称查询用户
     * @Param: [loginName]
     * @return: com.startest.wm.model.SysUserModel
     **/  
    SysUserModel queryUserModelByLoginName(String loginName);

    /**
     * @Description: 自定义查询用户
     * @Param: [userModel]
     * @return: java.util.List<com.startest.wm.service.UserModel>
     **/
    List<sys_user> queryUserByDefinition(UserSearchModel userSearchModel);

    /**
     * @Description: 自定义查询用户模型
     * @Param: [userSearchModel]
     * @return: java.util.List<com.startest.wm.model.SysUserModel>
     **/
    List<SysUserModel> queryUserModelByDefinition(UserSearchModel userSearchModel);

    /**
     * @Description: 根据用户IDs字符串查询用户模型
     * @Param: [list]
     * @return: java.util.List<com.startest.wm.model.SysUserModel>
     **/   
    List<SysUserModel> queryUserModelByIDs(List<String> list);

    /**
     * @Description: 查询所有用户
     * @Param: []
     * @return: java.util.List<com.startest.wm.pojo.sys_user>
     **/
    List<sys_user> queryAll();

    /**
     * @Description: 查询所有用户模型
     * @Param: []
     * @return: java.util.List<com.startest.wm.model.SysUserModel>
     **/
    List<SysUserModel> queryUserModelAll();
    
    /**
     * @Description: 根据用户ID查询用户所属的部门
     * @Param: [userID] 用户ID
     * @return: com.startest.wm.pojo.sys_dept
     **/  
    sys_dept queryUserDept(String userID);

    /**
     * @Description: 根据用户ID获取用户部门，岗位，小组，人员类别，是否组长信息
     * @Param: [userID]用户ID
     * @return: java.util.List<java.util.Map < java.lang.String, java.lang.String>>
     **/
    List<Map<String, String>> getUserDetailInfo(String userID);
}
