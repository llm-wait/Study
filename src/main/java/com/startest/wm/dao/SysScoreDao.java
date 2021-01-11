package com.startest.wm.dao;

import com.startest.wm.pojo.sys_user_score;

import java.util.List;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-04-02 10:12
 * @描述 成绩信息数据库映射
 **/
public interface SysScoreDao {
    /*********************************************考评基础成绩信息录入接口*******************************************/
    int add(sys_user_score sysUserScore);

    int delete(Integer year, String userID);

    int update(sys_user_score sysUserScore);

    sys_user_score findByUserID(Integer year, String userID);

    List<sys_user_score> findByDeptID(Integer year, String deptID);

    List<sys_user_score> findMany(Integer year, List<String> listIds);

    List<sys_user_score> getDeptScoreInfo(Integer year, String deptID,String isAll);

    List<sys_user_score> getAllScoreInfo(Integer year,String isAll);
}
