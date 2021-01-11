package com.startest.wm.service;

import com.startest.wm.pojo.sys_station;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-04-02 10:28
 * @描述 系统岗位操作服务接口
 **/
public interface SysStationService {
    /**
     * @Description: 新增岗位
     * @Param: [sysStation] 岗位对象
     * @return: int
     **/
    int addStation(sys_station sysStation);

    /**
     * @Description: 删除岗位
     * @Param: [stationID] 岗位ID
     * @return: int
     **/
    int deleteStationByID(String stationID);

    /**
     * @Description: 删除岗位
     * @Param: [stationName] 岗位名称
     * @return: int
     **/
    int deleteStationByName(String stationName);

    /**
     * @Description: 更新岗位信息
     * @Param: [sysStation] 岗位对象
     * @return: int
     **/
    int updateStation(sys_station sysStation);

    /**
     * @Description: 根据岗位ID获取岗位信息
     * @Param: [stationID] 岗位ID
     * @return: com.startest.wm.pojo.sys_station
     **/
    sys_station getStationByID(String stationID);

    /**
     * @Description: 根据岗位名称获取岗位信息
     * @Param: [stationName] 岗位名称
     * @return: com.startest.wm.pojo.sys_station
     **/
    List<sys_station> getStationByName(String stationName);

    /**
     * @Description: 获取所有岗位列表
     * @Param: []
     * @return: java.util.List<com.startest.wm.pojo.sys_station>
     **/
    List<sys_station> getAllStation();

    /**
     * @Description: 判断岗位是否存在
     * @Param: [stationID, stationName]
     * @return: boolean
     **/
    boolean isStationExist(String stationID, String stationName);

    /**
     * @Description: 判断岗位下是否已经存在人员
     * @Param: [stationID]
     * @return: boolean
     **/
    boolean isStationExistUsers(@Param("station_id") String stationID);
}
