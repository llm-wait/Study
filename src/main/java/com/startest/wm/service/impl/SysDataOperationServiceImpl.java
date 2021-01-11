package com.startest.wm.service.impl;

import com.startest.wm.dao.SysDataOperationDao;
import com.startest.wm.pojo.map_data;
import com.startest.wm.pojo.port_data;
import com.startest.wm.service.SysDataOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-04-02 10:29
 * @描述 系统资料操作相关接口实现类
 **/
@Service
public class SysDataOperationServiceImpl  implements SysDataOperationService {

    @Autowired
    SysDataOperationDao sysDataOperationDao;

    @Override
    public int insertMapData(map_data mapData) {
        return sysDataOperationDao.insertMapData(mapData);
    }

    @Override
    public int insertMapDataMany(List<map_data> mapDataList) {
        return sysDataOperationDao.insertMapDataMany(mapDataList);
    }

    @Override
    public int deleteMapDataByID(String mapID) {
        return sysDataOperationDao.deleteMapDataByID(mapID);
    }

    @Override
    public int deleteMapDataByMapCode(String mapCode) {
        return sysDataOperationDao.deleteMapDataByMapCode(mapCode);
    }

    @Override
    public int deleteMapDataMany(List<String> mapCodeList) {
        return sysDataOperationDao.deleteMapDataMany(mapCodeList);
    }

    @Override
    public int updateMapData(map_data mapData) {
        return sysDataOperationDao.updateMapData(mapData);
    }

    @Override
    public int updateMapState(List<String> mapCodeList, String mapState) {
        return sysDataOperationDao.updateMapState(mapCodeList, mapState);
    }

    @Override
    public String queryByMapID(String mapID) {
        return sysDataOperationDao.queryByMapID(mapID);
    }

    @Override
    public map_data queryObjectByMapID(String mapID) {
        return sysDataOperationDao.queryObjectByMapID(mapID);
    }

    @Override
    public String queryByMapCode(String mapCode) {
        return sysDataOperationDao.queryByMapCode(mapCode);
    }

    @Override
    public String queryMapDataByPoint(String poi) {
        return sysDataOperationDao.queryMapDataByPoint(poi);
    }

    @Override
    public String queryMapDataByPolygon(String poly) {
        return sysDataOperationDao.queryMapDataByPolygon(poly);
    }

    @Override
    public List<map_data> queryMapListByPolygon(String poly) {
        return sysDataOperationDao.queryMapListByPolygon(poly);
    }

    @Override
    public map_data queryObjectByMapCode(String mapCode) {
        List<map_data> mapDataList = sysDataOperationDao.queryObjectByMapCode(mapCode);
        if (mapDataList != null && mapDataList.size() > 0) {
            return mapDataList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public String queryMapData(Map<String, Object> map) {
        return sysDataOperationDao.queryMapData(map);
    }

    @Override
    public List<map_data> queryListMapData(Map<String, Object> map) {
        return sysDataOperationDao.queryListMapData(map);
    }

    @Override
    public List<map_data> queryListMapData2(String[] mapCodeArray) {
        return sysDataOperationDao.queryListMapData2(mapCodeArray);
    }

    @Override
    public boolean isMapExist(String mapCode) {
        return sysDataOperationDao.isMapExist(mapCode) > 0 ? true : false;
    }

    @Override
    public String queryAllMapData() {
        return sysDataOperationDao.queryAllMapData();
    }

    @Override
    public List<map_data> queryAllMapObjectData() {
        return sysDataOperationDao.queryAllMapObjectData();
    }


    @Override
    public int insertPortData(port_data portData) {
        return sysDataOperationDao.insertPortData(portData);
    }

    @Override
    public int insertPortDataMany(List<port_data> portDataList) {
        return sysDataOperationDao.insertPortDataMany(portDataList);
    }

    @Override
    public int deleteByPortID(String portID) {
        return sysDataOperationDao.deleteByPortID(portID);
    }

    @Override
    public int deleteByPortCode(String portCode) {
        return sysDataOperationDao.deleteByPortCode(portCode);
    }

    @Override
    public int deletePortDataMany(List<String> portCodeList) {
        return sysDataOperationDao.deletePortDataMany(portCodeList);
    }

    @Override
    public int updatePortData(port_data portData) {
        return sysDataOperationDao.updatePortData(portData);
    }

    @Override
    public int updatePortState(List<String> portNumList, String portState) {
        return sysDataOperationDao.updatePortState(portNumList, portState);
    }

    @Override
    public boolean isPortExist(String portCode) {
        return sysDataOperationDao.isPortExist(portCode) > 0 ? true : false;
    }

    @Override
    public String queryByPortID(String portID) {
        return sysDataOperationDao.queryByPortID(portID);
    }

    @Override
    public port_data queryObjectByPortID(String portID) {
        return sysDataOperationDao.queryObjectByPortID(portID);
    }

    @Override
    public String queryByPortCode(String portCode) {
        return sysDataOperationDao.queryByPortCode(portCode);
    }

    @Override
    public port_data queryObjectByPortCode(String portCode) {
        List<port_data> portDataList = sysDataOperationDao.queryObjectByPortCode(portCode);
        if (portDataList != null && portDataList.size() > 0) {
            return portDataList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public String queryPortByPolygon(String poly) {
        return sysDataOperationDao.queryPortByPolygon(poly);
    }

    @Override
    public List<port_data> queryPortListByPolygon(String poly) {
        return sysDataOperationDao.queryPortListByPolygon(poly);
    }

    @Override
    public String queryPortData(Map<String, Object> map) {
        return sysDataOperationDao.queryPortData(map);
    }

    @Override
    public List<port_data> queryPortDataList(Map<String, Object> map) {
        return sysDataOperationDao.queryPortDataList(map);
    }

    @Override
    public List<port_data> queryPortDataList2(String[] portCodeArray) {
        return sysDataOperationDao.queryPortDataList2(portCodeArray);
    }

    @Override
    public String queryAllPortData() {
        return sysDataOperationDao.queryAllPortData();
    }

    @Override
    public List<port_data> queryAllPortObjectData() {
        return sysDataOperationDao.queryAllPortObjectData();
    }
}
