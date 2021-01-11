package com.startest.wm.dao;

import com.startest.wm.pojo.sys_jghzgt;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-04-02 9:39
 * @描述 机关核准工天数据库映射
 **/
public interface SysJghzgtDao {
    /**
     * @Description: 新增机关核准工天
     * @Param: [jghzgt]
     * @return: int
     **/
    int insert(sys_jghzgt jghzgt);

    /**
     * @Description: 更新机关核准工天
     * @Param: [jghzgt]
     * @return: int
     **/
    int update(sys_jghzgt jghzgt);

    /**
     * @Description: 根据年份,部门查询机关核准工天
     * @Param: [year, deptID]
     * @return: com.startest.wm.pojo.sys_jghzgt
     **/
    sys_jghzgt query(Integer year, String deptID);

    /**
     * @Description: 根据ID查询机关核准工天
     * @Param: [id]
     * @return: com.startest.wm.pojo.sys_jghzgt
     **/
    sys_jghzgt queryByID(String id);
}
