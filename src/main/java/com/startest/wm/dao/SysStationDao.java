package com.startest.wm.dao;

import com.startest.wm.pojo.sys_dept;
import com.startest.wm.pojo.sys_station;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-04-02 9:42
 * @描述 岗位管理数据库映射
 **/
public interface SysStationDao {

    /**
     * @Description: 新增岗位信息
     * @Param: [station] 岗位对象
     * @return: int
     **/
    int insertStation(sys_station station);

    /**
     * @Description: 根据ID删除岗位信息
     * @Param: [stationID] 岗位ID
     * @return: int
     **/
    int deleteStationByID(String stationID);

    /**
     * @Description: 根据名称删除岗位信息
     * @Param: [stationName] 岗位名称
     * @return: int
     **/
    int deleteStationByName(String stationName);

    /**
     * @Description: 更新岗位信息
     * @Param: [station] 岗位对象
     * @return: int
     **/
    int updateStation(sys_station station);

    /**
     * @Description: 根据岗位ID查询岗位信息
     * @Param: [stationID] 岗位ID
     * @return: com.startest.wm.pojo.sys_station
     **/
    sys_station queryStationByID(String stationID);

    /**
     * @Description: 根据岗位名称查询岗位信息
     * @Param: [stationName] 岗位名称
     * @return: java.util.List<com.startest.wm.pojo.sys_station>
     **/
    List<sys_station> queryStationByName(@Param("stationName") String stationName);

    /**
     * @Description: 查询所有岗位信息
     * @Param: []
     * @return: java.util.List<com.startest.wm.pojo.sys_station>
     **/
    List<sys_station> queryStationAll();

    /**
     * @Description: 查询拥有此岗位的所有部门
     * @Param: [stationID] 岗位ID
     * @return: java.util.List<com.startest.wm.pojo.sys_dept>
     **/
    List<sys_dept> queryDept(String stationID);

    /**
     * @Description: 判断岗位是否已经存在
     * @Param: [stationID, stationName]岗位ID，岗位名称
     * @return: int
     **/  
    int isStationExist(@Param("station_id") String stationID, @Param("station_name") String stationName);

    /**
     * @Description: 判断岗位下是否已经存在人员
     * @Param: [stationID]
     * @return: int
     **/  
    int isStationExistUsers(@Param("station_id") String stationID);

}
