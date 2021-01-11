package com.startest.wm.dao;

import com.startest.wm.pojo.map_data;
import com.startest.wm.pojo.port_data;
import com.startest.wm.pojo.vo.CartographyTaskVo;
import com.startest.wm.pojo.vo.TaskVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-04-02 10:14
 * @描述 海图资料数据库映射
 **/
@Mapper
public interface SysDataOperationDao {

    /*****************************海图资料相关接口**********************************/

    /**
     * @Description: 新增海图
     * @Param: [mapData] 海图资料对象
     * @return: int
     **/
    int insertMapData(map_data mapData);

    /**
     * @Description: 批量新增海图
     * @Param: [mapDataList] 海图资料对象列表
     * @return: int
     **/
    int insertMapDataMany(List<map_data> mapDataList);

    /**
     * @Description: 根据海图资料ID删除海图
     * @Param: [mapID] 海图资料ID
     * @return: int
     **/
    int deleteMapDataByID(String mapID);

    /**
     * @Description: 根据海图资料图号删除海图
     * @Param: [mapCode] 海图资料ID
     * @return: int
     **/
    int deleteMapDataByMapCode(String mapCode);

    /**
     * @Description: 根据海图资料图号批量删除海图
     * @Param: [mapCodeList] 海图资料编号列表
     * @return: int
     **/
    int deleteMapDataMany(List<String> mapCodeList);

    /**
     * @Description: 更新海图资料数据
     * @Param: [mapData] 海图资料对象
     * @return: int
     **/
    int updateMapData(map_data mapData);

    /**
     * @Description: 修改海图资料状态
     * @Param: [mapIdList, mapState] 海图资料编号列表，海图资料状态
     * @return: int
     **/
    int updateMapState(@Param("mapCodes") List<String> mapIdList, @Param("mapState") String mapState);

    /**
     * @Description: 根据海图资料ID查询海图资料
     * @Param: [mapID] 海图资料ID
     * @return: java.lang.String
     **/
    String queryByMapID(String mapID);

    /**
     * @Description: 根据海图资料ID查询海图资料对象，不包含几何
     * @Param: [mapID] 海图资料ID
     * @return: com.startest.wm.pojo.map_data
     **/
    map_data queryObjectByMapID(String mapID);

    /**
     * @Description: 根据海图图号查询海图数据
     * @Param: [mapCode] 海图图号
     * @return: java.lang.String
     **/
    String queryByMapCode(String mapCode);

    /**
     * @Description: 根据点查询海图数据
     * @Param: [poi] 点
     * @return: java.lang.String
     **/
    String queryMapDataByPoint(@Param("poi")String poi);

    /**
     * @Description: 根据面查询海图数据
     * @Param: [poly] 面
     * @return: java.lang.String
     **/
    String queryMapDataByPolygon(@Param("poly")String poly);

    /**
     * @Description: 根据面查询海图数据，不包含几何
     * @Param: [poly]面
     * @return: java.util.List<com.startest.wm.pojo.map_data>
     **/
    List<map_data> queryMapListByPolygon(@Param("poly")String poly);

    /**
     * @Description: 根据海图资料编号查询海图资料对象，不包含几何
     * @Param: [mapCode] 海图资料编号
     * @return: java.util.List<com.startest.wm.pojo.map_data>
     **/
    List<map_data> queryObjectByMapCode(String mapCode);

    /**
     * @Description: 自定义条件查询海图资料
     * @Param: [map]
     * @return: java.lang.String
     **/
    String queryMapData(@Param("params")Map<String,Object>map);

    /**
     * @Description: 自定义查询海图对象列表，不包含几何
     * @Param: [map]
     * @return: java.util.List<com.startest.wm.pojo.map_data>
     **/
    List<map_data>queryListMapData(@Param("params")Map<String,Object>map);

    /**
     * @Description: 根据海图资料编号查询海图资料对象，不包含几何
     * @Param: [mapCode] 海图资料编号
     * @return: java.util.List<com.startest.wm.pojo.map_data>
     **/
    List<map_data> queryListMapData2(String[]mapCodeArray);

    /**
     * @Description: 海图资料是否存在
     * @Param: [mapCode]
     * @return: int
     **/
    int isMapExist(String mapCode);

    /**
     * @Description: 查询所有海图资料
     * @Param: []
     * @return: java.lang.String
     **/
    String queryAllMapData();

    /**
     * @Description: 查询所有海图资料列表对象，不包含几何
     * @Param: []
     * @return: java.util.List<com.startest.wm.pojo.map_data>
     **/
    List<map_data>queryAllMapObjectData();


    /*****************************港口资料相关接口**********************************/

    /**
     * @Description: 新增港口
     * @Param: [portData] 港口资料对象
     * @return: int
     **/
    int insertPortData(port_data portData);

    /**
     * @Description: 批量新增港口
     * @Param: [portDataList] 港口资料对象列表
     * @return: int
     **/
    int insertPortDataMany(List<port_data> portDataList);

    /**
     * @Description: 根据港口ID删除港口
     * @Param: [portID] 港口资料ID
     * @return: int
     **/
    int deleteByPortID(String portID);

    /**
     * @Description: 根据港口编号删除港口
     * @Param: [portCode] 港口资料编号
     * @return: int
     **/
    int deleteByPortCode(String portCode);

    /**
     * @Description: 根据港口编号批量删除港口
     * @Param: [portCodeList] 港口资料编号列表
     * @return: int
     **/
    int deletePortDataMany(List<String> portCodeList);

    /**
     * @Description: 更新港口资料数据
     * @Param: [portData] 港口资料对象
     * @return: int
     **/
    int updatePortData(port_data portData);

    /**
     * @Description: 修改港口资料状态
     * @Param: [portNumList, portState] 海图港口编号列表，港口资料状态
     * @return: int
     **/
    int updatePortState(@Param("portNums") List<String> portNumList, @Param("portState") String portState);

    /**
     * @Description: 根据ID查询港口资料
     * @Param: [portID] 港口资料ID
     * @return: java.lang.String
     **/
    String queryByPortID(String portID);

    /**
     * @Description: 根据港口资料ID查询港口资料，不包含几何
     * @Param: [portID] 港口资料ID
     * @return: com.startest.wm.pojo.port_data
     **/
    port_data queryObjectByPortID(String portID);

    /**
     * @Description: 根据港口编号查询港口数据
     * @Param: [portCode] 港口资料编号
     * @return: java.lang.String
     **/
    String queryByPortCode(String portCode);

    /**
     * @Description: 根据港口编号查询港口数据，不包含几何
     * @Param: [portCode] 港口资料编号
     * @return: java.util.List<com.startest.wm.pojo.port_data>
     **/
    List<port_data> queryObjectByPortCode(String portCode);

    /**
     * @Description: 面查询港口数据
     * @Param: [poly] 面
     * @return: java.lang.String
     **/
    String queryPortByPolygon(@Param("poly") String poly);

    /**
     * @Description: 面查询港口数据，不包含几何
     * @Param: [poly] 面
     * @return: java.util.List<com.startest.wm.pojo.port_data>
     **/
    List<port_data> queryPortListByPolygon(@Param("poly") String poly);

    /**
     * @Description: 自定义查询港口资料
     * @Param: [portData] 港口资料对象
     * @return: java.lang.String
     **/
    String queryPortData(@Param("params")Map<String,Object>map);

    /**
     * @Description: 自定义查询港口资料，不包含几何
     * @Param: [portData] 港口资料对象
     * @return: java.util.List<com.startest.wm.pojo.port_data>
     **/
    List<port_data> queryPortDataList(@Param("params")Map<String,Object>map);

    /**
     * @Description: 自定义查询港口资料，不包含几何
     * @Param: [portCodeArray]
     * @return: java.util.List<com.startest.wm.pojo.port_data>
     **/
    List<port_data> queryPortDataList2(String[] portCodeArray);

    /**
     * @Description: 港口资料是否存在
     * @Param: [portNum]
     * @return: int
     **/
    int isPortExist(String portNum);

    /**
     * @Description: 查询所有港口资料
     * @Param: []
     * @return: java.lang.String
     **/
    String queryAllPortData();

    /**
     * @Description: 查询所有的港口资料，不包含几何
     * @Param: []
     * @return: java.util.List<com.startest.wm.pojo.port_data>
     **/
    List<port_data> queryAllPortObjectData();

    /**根据分类id和任务状态查询制图任务列表　　弃用
     * @param classId　子分类id
     * @param state　任务状态
     * @return
     */
    List<TaskVo> getCartographyTaskByClassIdAndState(@Param("classId") List classId,
                                                     @Param("state") String state);


    /**根据港口号，查询港口信息
     * @param portNum 港口编号
     * @return 港口对象
     */
    port_data getPortDataByPortNum(@Param("portNum")String portNum);

    /*****************************其他资料相关接口**********************************/

}
