package com.startest.wm.service;

import com.startest.wm.pojo.sys_user_score;

import java.util.List;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-04-02 10:26
 * @描述 系统成绩操作服务接口
 **/
public interface SysScoreService {

    /*********************************************考评成绩基础信息录入接口*******************************************/
    
    /**
     * @Description: 添加用户基础成绩信息
     * @Param: [sysUserScore]
     * @return: int
     **/  
    int add(sys_user_score sysUserScore);

    /**
     * @Description: 删除用户基础成绩信息
     * @Param: [year, userID]
     * @return: int
     **/  
    int delete(Integer year, String userID);

    /**
     * @Description: 更新用户基础成绩信息
     * @Param: [sysUserScore]
     * @return: int
     **/  
    int update(sys_user_score sysUserScore);

    /**
     * @Description: 根据用户ID获取用户基础成绩信息
     * @Param: [year, userID]
     * @return: com.startest.wm.pojo.sys_user_score
     **/  
    sys_user_score findByUserID(Integer year, String userID);

    /**
     * @Description: 根据部门ID获取用户基础成绩信息
     * @Param: [year, deptID]
     * @return: java.util.List<com.startest.wm.pojo.sys_user_score>
     **/  
    List<sys_user_score> findByDeptID(Integer year, String deptID);

    /**
     * @Description: 查询指定用户ID的所有用户基础成绩信息
     * @Param: [year, listIds]
     * @return: java.util.List<com.startest.wm.pojo.sys_user_score>
     **/  
    List<sys_user_score> findMany(Integer year, List<String> listIds);

    /**
     * @Description: 存储用户基础成绩信息，不包含排名和评定结果
     * @Param: [sysUserScoreList]
     * @return: boolean
     **/  
    boolean saveToFinalScore(Integer year,String deptID,List<sys_user_score> sysUserScoreList);

    /**
     * @Description: 获取单位所有人员考评信息,通过此可以获取单位考评人数和总人数
     * @Param: [year, deptID, isAll] 年份 部门id 是/否
     * @return: java.util.List<com.startest.wm.pojo.sys_user_score>
     **/  
    List<sys_user_score> getDeptScoreInfo(Integer year, String deptID,String isAll);

    /**
     * @Description: 获取全社所有人员考评信息,通过此可以获取全社考评人数和总人数
     * @Param: [year, isAll]
     * @return: java.util.List<com.startest.wm.pojo.sys_user_score>
     **/  
    List<sys_user_score> getAllScoreInfo(Integer year,String isAll);

}
