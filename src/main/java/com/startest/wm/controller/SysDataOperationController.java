package com.startest.wm.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.startest.wm.enums.task.EnumTaskState;
import com.startest.wm.pojo.map_data;
import com.startest.wm.pojo.port_data;
import com.startest.wm.service.SysDataOperationService;
import com.startest.wm.service.TaskIndexService;
import com.startest.wm.utils.MapExcelOperationUtil;
import com.startest.wm.utils.PortExcelOperationUtil;
import com.startest.wm.utils.SpatialOperationUtil;
import com.startest.wm.utils.UUIDGeneratorUtil;
import com.startest.wm.utils.customresponse.RestResponse;
import com.startest.wm.utils.customresponse.RestResponseCode;
import com.startest.wm.utils.customresponse.RestResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.*;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-04-02 10:38
 * @描述 系统资料操作控制器
 **/
@Api(tags = "系统资料操作模块相关API")
@RestController
@RequestMapping(value = "/sys/data/")
public class SysDataOperationController {

    /*
     * 资料状态：已出版（黑色）、筹划（绿色）、计划下达（蓝色）、正在作业中（黄色）、已作废（红色）
     *
     * 1、已出版+筹划+已作废=所有海图
     * 2、已出版、和筹划的只有作废了才更改原始状态，其他情况不更改原始状态，只是更改资料状态
     * 3、资料发送完成后只更改资料状态为计划下达，原始状态不变
     * 4、作业室操作后也只是把计划下达改为正在作业中，原始状态不变
     * 5、初始化页面时只显示已出版的海图
     * */

    private static Logger log= LoggerFactory.getLogger(SysDataOperationController.class);

    @Autowired
    SysDataOperationService sysDataOperationService;

    @Autowired
    TaskIndexService taskIndexService;

    /*******************************************海图资料相关***********************************************/

    @ApiOperation(value = "新增海图资料(单个)", notes = "新增海图资料")
    @PostMapping("addMapData")
    public RestResponse addMapData(
            @ApiParam("海图资料对象") @RequestBody map_data mapData) {
        RestResponse response = null;
        try {
            boolean isMapExist = sysDataOperationService.isMapExist(mapData.getMap_code());
            if (isMapExist) {
                response = RestResponseUtil.createResponse(RestResponseCode.NOTE, "此编号海图已经存在", null);
            } else {
                mapData.setMap_id(UUIDGeneratorUtil.getUUIDWithoutLineAndToLower());
                mapData.setSource_state(mapData.getMap_state());//资料原始状态为初始化时选择资料状态
                if (mapData.getNorth_lat() != null && mapData.getSouth_lat() != null && mapData.getWest_lon() != null && mapData.getEast_lon() != null) {
                    String wkt = SpatialOperationUtil.getMapWKT(mapData.getNorth_lat(), mapData.getSouth_lat(), mapData.getWest_lon(), mapData.getEast_lon());
                    mapData.setShape(wkt);
                }
                int res = sysDataOperationService.insertMapData(mapData);
                if (res > 0) {
                    response = RestResponseUtil.success("添加成功！");
                } else {
                    response = RestResponseUtil.note("添加失败！");
                }
            }
        } catch (Exception e) {
            log.error("添加海图资料异常：",e);
            response = RestResponseUtil.note("添加失败！");
        }
        return response;
    }


    @ApiOperation(value = "批量新增海图资料", notes = "批量新增海图资料")
    @PostMapping(value = "addMapDataMany", headers = "content-type=multipart/form-data")
    @Transactional(propagation = Propagation.REQUIRED)
    public RestResponse<String> addMapDataMany(
            @ApiParam("海图资料excel文件") @RequestParam(value = "file") MultipartFile file) {
        RestResponse<String> response = null;
        try {
            //1.判断文件类型
            String fileName = file.getOriginalFilename();
            if (!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx")) {
                response = RestResponseUtil.note("文件格式错误！");
            } else {
                InputStream inputStream = file.getInputStream();
                List<map_data> mapDataList = MapExcelOperationUtil.readToMapData(inputStream, fileName);
                if (mapDataList.size() > 0) {
                    //过滤掉已经存在的海图图号
                    Iterator iterator = mapDataList.iterator();
                    while (iterator.hasNext()) {
                        map_data md = (map_data) iterator.next();
                        if (sysDataOperationService.isMapExist(md.getMap_code())) {
                            iterator.remove();
                        } else {
                            //设置海图的原始状态为初始状态
                            md.setSource_state(md.getMap_state());
                        }
                    }
                    if (mapDataList.size() > 0) {
                        int count = sysDataOperationService.insertMapDataMany(mapDataList);
                        if (count == mapDataList.size()) {
                            response = RestResponseUtil.success("批量导入成功！");
                            inputStream.close();
                        } else {
                            response = RestResponseUtil.note("批量导入失败！");
                        }
                    } else {
                        response = RestResponseUtil.note("海图图号都已经存在！");
                    }
                } else {
                    response = RestResponseUtil.note("资料数据为空！");
                    inputStream.close();
                }
            }
        } catch (Exception e) {
            log.error("批量导入海图资料异常：", e);
            response = RestResponseUtil.note("导入失败，资料数据格式存在问题！");
        }
        return response;
    }


    @ApiOperation(value = "自定义条件获取海图资料对象", notes = "获取条件海图资料对象")
    @PostMapping("queryMapObject")
    public RestResponse<Object> queryMapObject(
            @ApiParam(value = "海图资料状态") @RequestParam(name = "map_state", required = false) String map_state,
            @ApiParam(value = "海图资料类型") @RequestParam(name = "map_type", required = false) String map_type,
            @ApiParam(value = "海图中文名称") @RequestParam(name = "map_cn_name", required = false) String map_cn_name,
            @ApiParam(value = "海图英文名称") @RequestParam(name = "map_en_name", required = false) String map_en_name,
            @ApiParam(value = "海图比例尺") @RequestParam(name = "map_scale", required = false) List<String> scaleList,
            @ApiParam(value = "海图图号") @RequestParam(name = "map_code", required = false) String map_code,
            @ApiParam(value = "出版单位") @RequestParam(name = "publish_unit", required = false) String publish_unit,
            @ApiParam(value = "出版日期") @RequestParam(name = "publish_date", required = false) String publish_date) {
        RestResponse<Object> response = null;
        try {
            Map<String, Object> map = new HashMap<>();
            if (map_state != null && !"".equals(map_state)) {
                //多个状态组合查询
                if (map_state.indexOf(',') != -1) {
                    String mapsourcestate = "";
                    String mapstate = "";
                    for (String state : map_state.split(",")) {
                        if (EnumTaskState.YCB.getTaskStateString().equals(state) || "筹划".equals(state) || "已作废".equals(state)) {
                            mapsourcestate += "'" + state + "'" + ",";
                        } else {
                            mapstate += "'" + state + "'" + ",";
                        }
                    }
                    if (!"".equals(mapsourcestate)) {
                        map.put("source_state", mapsourcestate.substring(0, mapsourcestate.length() - 1));
                    }
                    if (!"".equals(mapstate)) {
                        map.put("map_state", mapstate.substring(0, mapstate.length() - 1));
                    }
                } else {
                    if ("已出版".equals(map_state) || "筹划".equals(map_state) ||
                            "已作废".equals(map_state)) {
                        map.put("source_state", "'" + map_state + "'");
                    } else {
                        map.put("map_state", "'" + map_state + "'");
                    }
                }
            }
            map.put("map_type", map_type);
            map.put("map_cn_name", map_cn_name);
            map.put("map_en_name", map_en_name);
            map.put("map_scale", scaleList);
            map.put("map_code", map_code);
            map.put("publish_unit", publish_unit);
            if (publish_date != null && !"".equals(publish_date)) {
                if (publish_date.indexOf(',') != -1) {
                    map.put("startdate", publish_date.split(",")[0]);
                    map.put("enddate", publish_date.split(",")[1]);
                } else {
                    map.put("startdate", publish_date);
                }
            }
            String dataResult = sysDataOperationService.queryMapData(map);
            response = RestResponseUtil.success("", JSON.parse(dataResult));
        } catch (Exception e) {
            log.error("自定义海图资料查询异常：", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "自定义条件获取海图资料列表", notes = "获取条件海图资料列表")
    @PostMapping("queryMapList")
    public RestResponse<PageInfo<map_data>> queryMapList(
            @ApiParam(value = "海图资料状态") @RequestParam(name = "map_state", required = false) String map_state,
            @ApiParam(value = "海图资料类型") @RequestParam(name = "map_type", required = false) String map_type,
            @ApiParam(value = "海图中文名称") @RequestParam(name = "map_cn_name", required = false) String map_cn_name,
            @ApiParam(value = "海图英文名称") @RequestParam(name = "map_en_name", required = false) String map_en_name,
            @ApiParam(value = "海图比例尺") @RequestParam(name = "map_scale", required = false) List<String> scaleList,
            @ApiParam(value = "海图图号") @RequestParam(name = "map_code", required = false) String map_code,
            @ApiParam(value = "出版单位") @RequestParam(name = "publish_unit", required = false) String publish_unit,
            @ApiParam(value = "出版日期") @RequestParam(name = "publish_date", required = false) String publish_date,
            @ApiParam(value = "页码", required = true) @RequestParam(name = "page_number", defaultValue = "1") Integer pageNum) {
        RestResponse<PageInfo<map_data>> response = null;
        try {
            Map<String, Object> map = new HashMap<>();
            if (map_state != null && !"".equals(map_state)) {
                //多个状态组合查询
                if (map_state.indexOf(',') != -1) {
                    String mapsourcestate = "";
                    String mapstate = "";
                    for (String state : map_state.split(",")) {
                        if ("已出版".equals(state) || "筹划".equals(state) || "已作废".equals(state)) {
                            mapsourcestate += "'" + state + "'" + ",";
                        } else {
                            mapstate += "'" + state + "'" + ",";
                        }
                    }
                    if (!"".equals(mapsourcestate)) {
                        map.put("source_state", mapsourcestate.substring(0, mapsourcestate.length() - 1));
                    }
                    if (!"".equals(mapstate)) {
                        map.put("map_state", mapstate.substring(0, mapstate.length() - 1));
                    }
                } else {
                    if ("已出版".equals(map_state) || "筹划".equals(map_state) || "已作废".equals(map_state)) {
                        map.put("source_state", "'" + map_state + "'");
                    } else {
                        map.put("map_state", "'" + map_state + "'");
                    }
                }
            }
            map.put("map_type", map_type);
            map.put("map_cn_name", map_cn_name);
            map.put("map_en_name", map_en_name);
            map.put("map_scale", scaleList);
            map.put("map_code", map_code);
            map.put("publish_unit", publish_unit);
            if (publish_date != null && !"".equals(publish_date)) {
                if (publish_date.indexOf(',') != -1) {
                    map.put("startdate", publish_date.split(",")[0]);
                    map.put("enddate", publish_date.split(",")[1]);
                } else {
                    map.put("startdate", publish_date);
                }
            }
            PageHelper.startPage(pageNum, 100);
            List<map_data> mapDataList = sysDataOperationService.queryListMapData(map);
            PageInfo<map_data> pageInfo = new PageInfo<>(mapDataList);
            response = RestResponseUtil.success("", pageInfo);
        } catch (Exception e) {
            log.error("自定义海图资料查询异常：", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "获取所有海图资料对象", notes = "获取所有海图资料对象")
    @GetMapping("getMapObject")
    public RestResponse<Object> getMapObject() {
        RestResponse<Object> response = null;
        try {
            String dataResult = sysDataOperationService.queryAllMapData();
            response = RestResponseUtil.success("", JSON.parse(dataResult));
        } catch (Exception e) {
            log.error("获取所有海图资料对象异常：", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "获取所有海图资料列表", notes = "获取所有海图资料列表")
    @GetMapping("getMapList")
    public RestResponse<PageInfo<map_data>> getMapList(
            @ApiParam(value = "页码", required = true) @RequestParam(name = "page_number", defaultValue = "1") Integer pageNum
    ) {
        RestResponse<PageInfo<map_data>> response = null;
        try {
            PageHelper.startPage(pageNum, 100);
            List<map_data> mapDataList = sysDataOperationService.queryAllMapObjectData();
            PageInfo<map_data> pageInfo = new PageInfo<>(mapDataList);
            response = RestResponseUtil.success("", pageInfo);
        } catch (Exception e) {
            log.error("获取所有海图资料列表异常：", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "根据海图ID获取海图资料信息", notes = "根据海图ID获取海图资料信息")
    @GetMapping("getMapDataInfoByMapID")
    public RestResponse<Object> getMapDataInfoByMapID(
            @ApiParam(value = "海图图号", required = true) @RequestParam(name = "map_id") String mapID) {
        RestResponse<Object> response = null;
        try {
            String dataResult = sysDataOperationService.queryByMapID(mapID);
            response = RestResponseUtil.success("", JSON.parse(dataResult));
        } catch (Exception e) {
            log.error("根据海图ID获取海图资料信息异常：", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "根据海图图号获取海图资料信息", notes = "根据海图图号获取海图资料信息")
    @GetMapping("getMapDataInfoByMapCode")
    public RestResponse<Object> getMapDataInfoByMapCode(
            @ApiParam(value = "海图图号", required = true) @RequestParam(name = "map_code") String mapCode) {
        RestResponse<Object> response = null;
        try {
            String dataResult = sysDataOperationService.queryByMapCode(mapCode);
            response = RestResponseUtil.success("", JSON.parse(dataResult));
        } catch (Exception e) {
            log.error("根据海图图号获取海图资料信息异常：", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "面查询海图资料对象", notes = "面查询海图资料对象")
    @PostMapping("getMapObjectByPolygon")
    public RestResponse<Object> getMapObjectByPolygon(
            @ApiParam(value = "指定格式的面字符串", required = true) @RequestParam(name = "poly") String poly) {
        //poly格式
        //POLYGON((119.23 30, 123 30, 123 40, 119.23 40, 119.23 30))
        RestResponse<Object> response = null;
        try {
            String dataResult = sysDataOperationService.queryMapDataByPolygon(poly);
            response = RestResponseUtil.success("", JSON.parse(dataResult));
        } catch (Exception e) {
            log.error("面查询海图资料对象异常：", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "面查询海图资料列表", notes = "面查询海图资料列表")
    @PostMapping("getMapListByPolygon")
    public RestResponse<PageInfo<map_data>> getMapListByPolygon(
            @ApiParam(value = "指定格式的面字符串", required = true) @RequestParam(name = "poly") String poly,
            @ApiParam(value = "页码", required = true) @RequestParam(name = "page_number", defaultValue = "1") Integer pageNum) {
        //poly格式
        //POLYGON((119.23 30, 123 30, 123 40, 119.23 40, 119.23 30))
        RestResponse<PageInfo<map_data>> response = null;
        try {
            PageHelper.startPage(pageNum, 100);
            List<map_data> mapDataList = sysDataOperationService.queryMapListByPolygon(poly);
            PageInfo<map_data> pageInfo = new PageInfo<>(mapDataList);
            response = RestResponseUtil.success("", pageInfo);
        } catch (Exception e) {
            log.error("面查询海图资料列表异常：", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "修改海图资料", notes = "修改海图资料信息")
    @PostMapping("updateMapDataInfo")
    public RestResponse<String> updateMapDataInfo(
            @ApiParam(value = "海图资料对象", required = true) @RequestBody map_data mapData) {
        RestResponse<String> response = null;
        try {
            String wkt = SpatialOperationUtil.getMapWKT(mapData.getNorth_lat(), mapData.getSouth_lat(), mapData.getWest_lon(), mapData.getEast_lon());
            mapData.setShape(wkt);
            int result = sysDataOperationService.updateMapData(mapData);
            if (result > 0) {
                response = RestResponseUtil.success("修改成功！");
            }
        } catch (Exception e) {
            log.error("修改海图资料异常：", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "根据海图ID删除海图资料信息", notes = "根据海图ID删除海图资料信息")
    @PostMapping("deleteMapDataByID")
    public RestResponse<String> deleteMapDataByID(
            @ApiParam(value = "海图资料ID", required = true) @RequestParam(name = "map_id") String mapID) {
        RestResponse<String> response = null;
        try {
            int count = sysDataOperationService.deleteMapDataByID(mapID);
            if (count > 0) {
                response = RestResponseUtil.success("删除成功！");
            } else {
                response = RestResponseUtil.note("删除失败！");
            }
        } catch (Exception e) {
            log.error("根据海图ID删除海图资料信息异常：", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "根据海图图号删除海图资料信息", notes = "根据海图图号删除海图资料信息")
    @PostMapping("deleteMapDataByCode")
    public RestResponse<String> deleteMapDataByCode(
            @ApiParam(value = "海图图号", required = true) @RequestParam(name = "map_code") String mapCode) {
        RestResponse<String> response = null;
        try {
            int count = sysDataOperationService.deleteMapDataByMapCode(mapCode);
            if (count > 0) {
                response = RestResponseUtil.success("删除成功！");
            } else {
                response = RestResponseUtil.note("删除失败！");
            }
        } catch (Exception e) {
            log.error("根据海图图号删除海图资料信息异常：", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "根据海图图号批量删除海图资料信息", notes = "根据海图图号删除海图资料信息")
    @PostMapping("deleteMapDataMany")
    @Transactional(propagation = Propagation.REQUIRED)
    public RestResponse<String> deleteMapDataMany(
            @ApiParam("海图图号列表") @RequestParam(name = "list") List<String> mapCodeList) {
        RestResponse<String> response = null;
        try {
            int count = sysDataOperationService.deleteMapDataMany(mapCodeList);
            if (count == mapCodeList.size()) {
                response = RestResponseUtil.success("删除成功！");
            } else {
                response = RestResponseUtil.note("删除失败！");
            }
        } catch (Exception e) {
            log.error("根据海图图号批量删除海图资料信息异常：", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "发送海图资料", notes = "可单个或批量发送")
    @PostMapping("sendMap")
    @Transactional(propagation = Propagation.REQUIRED)
    public RestResponse<String> changeMapStateToJHXD(
            @ApiParam(value = "海图图号列表") @RequestParam(name = "mapCodes") List<String> mapCodeList) {
        RestResponse<String> response = null;
        try {
            //只有已出版或筹划的资料可以发送
            Iterator<String> iterator = mapCodeList.iterator();
            while (iterator.hasNext()) {
                map_data mapData = sysDataOperationService.queryObjectByMapCode(iterator.next());
                if (!"已出版".equals(mapData.getMap_state()) && !"筹划".equals(mapData.getMap_state())) {
                    iterator.remove();
                }
            }
            if (mapCodeList.size() == 0) {
                return RestResponseUtil.note("请选择已出版或筹划的资料！");
            }
            //发送资料
            int count = sysDataOperationService.updateMapState(mapCodeList, "计划下达");
            if (count == mapCodeList.size()) {
                List<map_data> mapDataList = new ArrayList<>();
                for (int i = 0; i < mapCodeList.size(); i++) {
                    map_data mapData = sysDataOperationService.queryObjectByMapCode(mapCodeList.get(i));
                    mapDataList.add(mapData);
                }
                //关联任务
                int c1 = taskIndexService.insertTaskIndexInfoByChartData(mapDataList);
                if (c1 > 0) {
                    response = RestResponseUtil.success("发送成功！");
                } else {
                    response = RestResponseUtil.note("发送失败！");
                }
            } else {
                response = RestResponseUtil.note("发送失败！");
            }
        } catch (Exception e) {
            log.error("发送海图资料异常：", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "作废海图", notes = "可单个或批量设置")
    @PostMapping("zuofeiMap")
    @Transactional(propagation = Propagation.REQUIRED)
    public RestResponse<String> changeMapStateToZF(
            @ApiParam(value = "海图图号列表") @RequestParam(name = "mapCodes") List<String> mapCodeList) {
        RestResponse<String> response = null;
        try {
            //只有已出版或筹划的资料可以作废
            Iterator<String> iterator = mapCodeList.iterator();
            while (iterator.hasNext()) {
                map_data mapData = sysDataOperationService.queryObjectByMapCode(iterator.next());
                if (!"已出版".equals(mapData.getMap_state()) && !"筹划".equals(mapData.getMap_state())) {
                    iterator.remove();
                }
            }
            if (mapCodeList.size() == 0) {
                return RestResponseUtil.note("请选择已出版或筹划的资料！");
            }
            int count = sysDataOperationService.updateMapState(mapCodeList, "已作废");
            if (count == mapCodeList.size()) {
                response = RestResponseUtil.success("操作成功！");
            } else {
                response = RestResponseUtil.note("操作失败！");
            }
        } catch (Exception e) {
            log.error("作废海图异常：", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "更新海图资料状态", notes = "可单个或批量设置")
    @PostMapping("changeMapState")
    @Transactional(propagation = Propagation.REQUIRED)
    public RestResponse<String> changeMapState(
            @ApiParam(value = "海图图号列表") @RequestParam(name = "mapCodes") List<String> mapCodeList,
            @ApiParam(value = "海图资料状态") @RequestParam(name = "mapState") String mapState) {
        RestResponse<String> response = null;
        try {
            int count = sysDataOperationService.updateMapState(mapCodeList, mapState);
            if (count == mapCodeList.size()) {
                response = RestResponseUtil.success("操作成功！");
            } else {
                response = RestResponseUtil.note("操作失败！");
            }
        } catch (Exception e) {
            log.error("更新海图资料异常：", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }

    @ApiOperation(value = "导出所有海图资料到Excel", notes = "导出所有海图资料到Excel")
    @GetMapping("exportMapDataToExcelAll")
    public RestResponse<Object> exportMapDataToExcelAll(
            HttpServletResponse httpServletResponse) {
        RestResponse<Object> response = null;
        try {
            List<map_data> mapDataList = sysDataOperationService.queryAllMapObjectData();
            if (mapDataList.size() == 0) {
                response = RestResponseUtil.note("海图资料数据为空！");
            } else {
                MapExcelOperationUtil.exportMapListtoExcel(mapDataList, httpServletResponse);
            }
        } catch (Exception e) {
            log.error("导出所有海图资料到Excel异常：", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "导出海图资料查询结果到Excel", notes = "导出海图资料列表")
    @GetMapping("exportMapDataToExcel")
    public RestResponse<Object> exportMapDataToExcel(
            HttpServletResponse httpServletResponse,
            @ApiParam(value = "海图编号字符串") @RequestParam(name = "mapCodeList", required = false) String mapCodeStr) {
        RestResponse<Object> response = null;
        try {
            if (mapCodeStr == null || "".equals(mapCodeStr)) {
                response = RestResponseUtil.note("请先选择要导出的数据！");
            } else {
                String[] mapCodeArray = mapCodeStr.split(",");
                List<map_data> mapDataList = sysDataOperationService.queryListMapData2(mapCodeArray);
                MapExcelOperationUtil.exportMapListtoExcel(mapDataList, httpServletResponse);
            }
        } catch (Exception e) {
            log.error("导出海图资料查询结果到Excel异常：", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    /*******************************************港口资料相关***********************************************/

    @ApiOperation(value = "新增港口资料（单个）", notes = "新增港口资料（单个）")
    @PostMapping("addPortData")
    public RestResponse<String> addPortData(
            @ApiParam(value = "港口资料对象", required = true) @RequestBody port_data portData) {
        RestResponse<String> response = null;
        try {
            boolean isPortExist = sysDataOperationService.isPortExist(portData.getPort_num());
            if (isPortExist) {
                response = RestResponseUtil.createResponse(RestResponseCode.NOTE, "此编号港口资料已经存在", null);
            } else {
                String wkt = SpatialOperationUtil.getPortWKT(portData.getPort_lon(), portData.getPort_lat());
                portData.setShape(wkt);
                portData.setPort_id(UUIDGeneratorUtil.getUUIDWithoutLineAndToLower());
                portData.setSource_state(portData.getPort_state());
                int res = sysDataOperationService.insertPortData(portData);
                if (res > 0) {
                    response = RestResponseUtil.success("", "添加成功！");
                } else {
                    response = RestResponseUtil.note("添加失败！");
                }
            }
        } catch (Exception e) {
            log.error("新增港口资料异常：", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "批量新增港口资料", notes = "批量新增港口资料")
    @PostMapping(value = "addPortDataMany", headers = "content-type=multipart/form-data")
    @Transactional(propagation = Propagation.REQUIRED)
    public RestResponse<String> addPortDataMany(
            @ApiParam(value = "港口资料excel文件", required = true) @RequestParam(value = "file") MultipartFile file) {
        RestResponse<String> response = null;
        try {
            //1.判断文件类型
            String fileName = file.getOriginalFilename();
            if (!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx")) {
                response = RestResponseUtil.note("文件格式错误！");
            } else {
                InputStream inputStream = file.getInputStream();
                List<port_data> portDataList = PortExcelOperationUtil.readToPortData(inputStream, fileName);
                if (portDataList.size() > 0) {
                    //过滤掉已经存在的港口资料编号
                    Iterator iterator = portDataList.iterator();
                    while (iterator.hasNext()) {
                        port_data pData = (port_data) iterator.next();
                        if (sysDataOperationService.isPortExist(pData.getPort_num())) {
                            iterator.remove();
                        } else {
                            pData.setSource_state(pData.getPort_state());
                        }
                    }
                    if (portDataList.size() > 0) {
                        int result = sysDataOperationService.insertPortDataMany(portDataList);
                        if (result == portDataList.size()) {
                            response = RestResponseUtil.success("批量导入成功！");
                            inputStream.close();
                        } else {
                            response = RestResponseUtil.note("批量导入失败！");
                        }
                    } else {
                        response = RestResponseUtil.note("港口资料编号都已经存在！");
                    }
                } else {
                    response = RestResponseUtil.note("资料数据为空！");
                    inputStream.close();
                }
            }
        } catch (Exception e) {
            log.error("导入港口资料异常：", e);
            response = RestResponseUtil.note("导入失败，资料数据格式存在问题！");
        }
        return response;
    }


    @ApiOperation(value = "自定义条件获取港口资料对象", notes = "自定义条件获取港口资料对象")
    @PostMapping("queryPortObject")
    public RestResponse<Object> queryPortObject(
            @ApiParam(value = "港口资料状态") @RequestParam(name = "port_state", required = false) String port_state,
            @ApiParam(value = "港口资料类型") @RequestParam(name = "port_type", required = false) String port_type,
            @ApiParam(value = "港口资料编号") @RequestParam(name = "port_num", required = false) String port_num,
            @ApiParam(value = "港口中文名称") @RequestParam(name = "port_cn_name", required = false) String port_cn_name,
            @ApiParam(value = "港口英文名称") @RequestParam(name = "port_en_name", required = false) String port_en_name,
            @ApiParam(value = "所属大洲") @RequestParam(name = "port_continent", required = false) String port_continent,
            @ApiParam(value = "所属国家") @RequestParam(name = "port_country", required = false) String port_country,
            @ApiParam(value = "所属海区") @RequestParam(name = "port_sea", required = false) String port_sea,
            @ApiParam(value = "版时") @RequestParam(name = "port_data_bs", required = false) String port_data_bs) {
        RestResponse<Object> response = null;
        try {
            Map<String, Object> hashMap = new HashMap<>();

            if (port_state != null && !"".equals(port_state)) {
                if (port_state.indexOf(',') != -1) {
                    String portsourcestate = "";
                    String portstate = "";
                    for (String state : port_state.split(",")) {
                        if ("已出版".equals(state) || "筹划".equals(state) || "已作废".equals(state)) {
                            portsourcestate += "'" + state + "'" + ",";
                        } else {
                            portstate += "'" + state + "'" + ",";
                        }
                    }
                    if (!"".equals(portsourcestate)) {
                        hashMap.put("source_state", portsourcestate.substring(0, portsourcestate.length() - 1));
                    }
                    if (!"".equals(portstate)) {
                        hashMap.put("port_state", portstate.substring(0, portstate.length() - 1));
                    }
                } else {
                    if ("已出版".equals(port_state) || "筹划".equals(port_state) || "已作废".equals(port_state)) {
                        hashMap.put("source_state", "'" + port_state + "'");
                    } else {
                        hashMap.put("port_state", "'" + port_state + "'");
                    }
                }
            }
            hashMap.put("port_num", port_num);
            hashMap.put("port_type", port_type);
            hashMap.put("port_cn_name", port_cn_name);
            hashMap.put("port_en_name", port_en_name);
            hashMap.put("port_continent", port_continent);
            hashMap.put("port_country", port_country);
            hashMap.put("port_sea", port_sea);
            //版时查询暂时先不用了
            /*if (port_data_bs != null && !"".equals(port_data_bs)) {
                if (port_data_bs.indexOf(',') != -1) {
                    hashMap.put("startdate", port_data_bs.split(",")[0]);
                    hashMap.put("enddate", port_data_bs.split(",")[1]);
                } else {
                    hashMap.put("startdate", port_data_bs);
                }
            }*/
            String dataResult = sysDataOperationService.queryPortData(hashMap);
            response = RestResponseUtil.success("", JSON.parse(dataResult));
        } catch (Exception e) {
            log.error("自定义条件获取港口资料对象异常：", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "自定义条件获取港口资料列表", notes = "自定义条件获取港口资料列表")
    @PostMapping("queryPortList")
    public RestResponse<PageInfo<port_data>> queryPortList(
            @ApiParam(value = "港口资料状态") @RequestParam(name = "port_state", required = false) String port_state,
            @ApiParam(value = "港口资料类型") @RequestParam(name = "port_type", required = false) String port_type,
            @ApiParam(value = "港口资料编号") @RequestParam(name = "port_num", required = false) String port_num,
            @ApiParam(value = "港口中文名称") @RequestParam(name = "port_cn_name", required = false) String port_cn_name,
            @ApiParam(value = "港口英文名称") @RequestParam(name = "port_en_name", required = false) String port_en_name,
            @ApiParam(value = "所属大洲") @RequestParam(name = "port_continent", required = false) String port_continent,
            @ApiParam(value = "所属国家") @RequestParam(name = "port_country", required = false) String port_country,
            @ApiParam(value = "所属海区") @RequestParam(name = "port_sea", required = false) String port_sea,
            @ApiParam(value = "版时") @RequestParam(name = "port_data_bs", required = false) String port_data_bs,
            @ApiParam(value = "页码", required = true) @RequestParam(name = "page_number", defaultValue = "1") Integer pageNum) {
        RestResponse<PageInfo<port_data>> response = null;
        try {
            Map<String, Object> hashMap = new HashMap<>();

            if (port_state != null && !"".equals(port_state)) {
                if (port_state.indexOf(',') != -1) {
                    String portsourcestate = "";
                    String portstate = "";
                    for (String state : port_state.split(",")) {
                        if ("已出版".equals(state) || "筹划".equals(state) || "已作废".equals(state)) {
                            portsourcestate += "'" + state + "'" + ",";
                        } else {
                            portstate += "'" + state + "'" + ",";
                        }
                    }
                    if (!"".equals(portsourcestate)) {
                        hashMap.put("source_state", portsourcestate.substring(0, portsourcestate.length() - 1));
                    }
                    if (!"".equals(portstate)) {
                        hashMap.put("port_state", portstate.substring(0, portstate.length() - 1));
                    }
                } else {
                    if ("已出版".equals(port_state) || "筹划".equals(port_state) || "已作废".equals(port_state)) {
                        hashMap.put("source_state", "'" + port_state + "'");
                    } else {
                        hashMap.put("port_state", "'" + port_state + "'");
                    }
                }
            }
            hashMap.put("port_num", port_num);
            hashMap.put("port_type", port_type);
            hashMap.put("port_cn_name", port_cn_name);
            hashMap.put("port_en_name", port_en_name);
            hashMap.put("port_continent", port_continent);
            hashMap.put("port_country", port_country);
            hashMap.put("port_sea", port_sea);
            //版时暂时先不用了
            /*if (port_data_bs != null && !"".equals(port_data_bs)) {
                if (port_data_bs.indexOf(',') != -1) {
                    hashMap.put("startdate", port_data_bs.split(",")[0]);
                    hashMap.put("enddate", port_data_bs.split(",")[1]);
                } else {
                    hashMap.put("startdate", port_data_bs);
                }
            }*/
            PageHelper.startPage(pageNum, 100);
            List<port_data> portDataList = sysDataOperationService.queryPortDataList(hashMap);
            PageInfo<port_data> pageInfo = new PageInfo<>(portDataList);
            response = RestResponseUtil.success("", pageInfo);
        } catch (Exception e) {
            log.error("自定义条件获取港口资料列表异常：", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "获取所有港口资料对象", notes = "获取港口资料对象")
    @GetMapping("getPortObject")
    public RestResponse<Object> getPortObject() {
        RestResponse<Object> response = null;
        try {
            String dataResult = sysDataOperationService.queryAllPortData();
            response = RestResponseUtil.success("", JSON.parse(dataResult));
        } catch (Exception e) {
            log.error("获取所有港口资料对象异常：", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }

    @ApiOperation(value = "获取所有港口资料列表", notes = "获取港口资料列表")
    @GetMapping("getPortList")
    public RestResponse<PageInfo<port_data>> getPortList(
            @ApiParam(value = "页码", required = true) @RequestParam(name = "page_number", defaultValue = "1") Integer pageNum) {
        RestResponse<PageInfo<port_data>> response = null;
        try {
            PageHelper.startPage(pageNum, 100);
            List<port_data> portDataList = sysDataOperationService.queryAllPortObjectData();
            PageInfo<port_data> pageInfo = new PageInfo<>(portDataList);
            response = RestResponseUtil.success("", pageInfo);
        } catch (Exception e) {
            log.error("获取所有港口资料列表异常：", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "根据港口资料ID获取港口资料信息", notes = "根据港口资料ID获取港口资料信息")
    @GetMapping("getPortDataByID")
    public RestResponse<Object> getPortDataByID(
            @ApiParam(value = "港口资料ID", required = true) @RequestParam(name = "port_id") String portID) {
        RestResponse<Object> response = null;
        try {
            String dataResult = sysDataOperationService.queryByPortID(portID);
            response = RestResponseUtil.success("", JSON.parse(dataResult));
        } catch (Exception e) {
            log.error("根据港口资料ID获取港口资料信息异常：", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "获取港口资料编号获取资料信息", notes = "获取港口资料编号获取资料信息")
    @GetMapping("getPortDataByNum")
    public RestResponse<Object> getPortDataByNum(
            @ApiParam(value = "港口资料编号", required = true) @RequestParam(name = "port_num") String portNum) {
        RestResponse<Object> response = null;
        try {
            String dataResult = sysDataOperationService.queryByPortCode(portNum);
            response = RestResponseUtil.success("", JSON.parse(dataResult));
        } catch (Exception e) {
            log.error("获取港口资料编号获取资料信息异常：", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "面查询港口资料对象", notes = "面查询港口资料对象")
    @PostMapping("getPortObjectByPolygon")
    public RestResponse<Object> getPortDataByPolygon(
            @ApiParam(value = "指定格式的面字符串", required = true) @RequestParam(name = "poly") String poly) {
        RestResponse<Object> response = null;
        try {
            String dataResult = sysDataOperationService.queryPortByPolygon(poly);
            response = RestResponseUtil.success("", JSON.parse(dataResult));
        } catch (Exception e) {
            log.error("面查询港口资料对象异常：", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }

    @ApiOperation(value = "面查询港口资料列表", notes = "面查询港口资料列表")
    @PostMapping("getPortListByPolygon")
    public RestResponse<PageInfo<port_data>> getPortListByPolygon(
            @ApiParam(value = "指定格式的面字符串", required = true) @RequestParam(name = "poly") String poly,
            @ApiParam(value = "页码", required = true) @RequestParam(name = "page_number", defaultValue = "1") Integer pageNum) {
        RestResponse<PageInfo<port_data>> response = null;
        try {
            PageHelper.startPage(pageNum, 100);
            List<port_data> portDataList = sysDataOperationService.queryPortListByPolygon(poly);
            PageInfo<port_data> pageInfo = new PageInfo<>(portDataList);
            response = RestResponseUtil.success("", pageInfo);
        } catch (Exception e) {
            log.error("面查询港口资料列表异常：", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "修改港口资料信息", notes = "修改港口资料信息")
    @PostMapping("updatePortDataInfo")
    public RestResponse<String> updatePortDataInfo(
            @ApiParam(value = "港口资料对象", required = true) @RequestBody port_data portData) {
        RestResponse<String> response = null;
        try {
            String wkt = SpatialOperationUtil.getPortWKT(portData.getPort_lon(), portData.getPort_lat());
            portData.setShape(wkt);
            int result = sysDataOperationService.updatePortData(portData);
            if (result > 0) {
                response = RestResponseUtil.success("修改成功！");
            }
        } catch (Exception e) {
            log.error("修改港口资料信息异常：", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "根据港口资料ID删除港口资料信息", notes = "根据港口资料ID删除港口资料信息")
    @PostMapping("deletePortDataByID")
    public RestResponse<String> deletePortDataByID(
            @ApiParam(value = "港口资料ID", required = true) @RequestParam(name = "port_id") String portID) {
        RestResponse<String> response = null;
        try {
            int count = sysDataOperationService.deleteByPortID(portID);
            if (count > 0) {
                response = RestResponseUtil.success("删除成功！");
            } else {
                response = RestResponseUtil.note("删除失败！");
            }
        } catch (Exception e) {
            log.error("根据港口资料ID删除港口资料信息异常：", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "根据港口资料编号删除港口资料信息", notes = "根据港口资料编号删除港口资料信息")
    @PostMapping("deletePortDataByNum")
    public RestResponse<String> deletePortDataByNum(
            @ApiParam(value = "港口资料编号", required = true) @RequestParam(name = "port_num") String portNum) {
        RestResponse<String> response = null;
        try {
            int count = sysDataOperationService.deleteByPortCode(portNum);
            if (count > 0) {
                response = RestResponseUtil.success("删除成功！");
            } else {
                response = RestResponseUtil.note("删除失败！");
            }
        } catch (Exception e) {
            log.error("根据港口资料编号删除港口资料信息异常：", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "根据港口资料编号批量删除港口资料信息", notes = "根据港口资料编号删除港口资料信息")
    @PostMapping("deletePortDataMany")
    @Transactional(propagation = Propagation.REQUIRED)
    public RestResponse<String> deletePortDataMany(
            @ApiParam(value = "港口资料编号列表", required = true) @RequestParam(name = "list") List<String> portNumList) {
        RestResponse<String> response = null;
        try {
            int count = sysDataOperationService.deletePortDataMany(portNumList);
            if (count == portNumList.size()) {
                response = RestResponseUtil.success("删除成功！");
            } else {
                response = RestResponseUtil.note("删除失败！");
            }
        } catch (Exception e) {
            log.error("根据港口资料编号批量删除港口资料信息异常：", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "发送港口资料", notes = "可单个或批量发送")
    @PostMapping("sendPort")
    @Transactional(propagation = Propagation.REQUIRED)
    public RestResponse<String> changePortStateToJHXD(
            @ApiParam(value = "港口资料编号列表") @RequestParam(name = "portNums") List<String> portNumList) {
        RestResponse<String> response = null;
        try {
            //只有已出版或筹划的资料能发送，其他的不可发送
            Iterator<String> iterator = portNumList.iterator();
            while (iterator.hasNext()) {
                port_data portData = sysDataOperationService.queryObjectByPortCode(iterator.next());
                if (!"已出版".equals(portData.getPort_state()) && !"筹划".equals(portData.getPort_state())) {
                    iterator.remove();
                }
            }
            if (portNumList.size() == 0) {
                return RestResponseUtil.note("请选择已出版或筹划的资料！");
            }
            int count = sysDataOperationService.updatePortState(portNumList, "计划下达");
            if (count == portNumList.size()) {
                List<port_data> portDataList = new ArrayList<>();
                for (int i = 0; i < portNumList.size(); i++) {
                    port_data portData = sysDataOperationService.queryObjectByPortCode(portNumList.get(i));
                    portDataList.add(portData);
                }
                //关联任务
                int c1 = taskIndexService.insertTaskIndexInfoByBookData(portDataList);
                if (c1 > 0) {
                    response = RestResponseUtil.success("发送成功！");
                } else {
                    response = RestResponseUtil.note("发送失败！");
                }
            } else {
                response = RestResponseUtil.note("发送失败！");
            }
        } catch (Exception e) {
            log.error("发送港口资料异常：", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "作废港口", notes = "可单个或批量设置")
    @PostMapping("zuofeiPort")
    @Transactional(propagation = Propagation.REQUIRED)
    public RestResponse<String> changePortStateToZF(
            @ApiParam(value = "港口资料编号列表") @RequestParam(name = "portNums") List<String> portNumList) {
        RestResponse<String> response = null;
        try {
            //只有已出版的和筹划状态的可以作废，其他不可作废
            Iterator<String> iterator = portNumList.iterator();
            while (iterator.hasNext()) {
                port_data portData = sysDataOperationService.queryObjectByPortCode(iterator.next());
                if (!"已出版".equals(portData.getPort_state()) && !"筹划".equals(portData.getPort_state())) {
                    iterator.remove();
                }
            }
            if (portNumList.size() == 0) {
                return RestResponseUtil.note("请选择已出版或筹划的资料！");
            }
            //作废资料
            int count = sysDataOperationService.updatePortState(portNumList, "已作废");
            if (count == portNumList.size()) {
                response = RestResponseUtil.success("操作成功！");
            } else {
                response = RestResponseUtil.note("操作失败！");
            }
        } catch (Exception e) {
            log.error("作废港口资料异常：", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "更新港口资料状态", notes = "可单个或批量设置")
    @PostMapping("changePortState")
    @Transactional(propagation = Propagation.REQUIRED)
    public RestResponse<String> changePortState(
            @ApiParam(value = "港口资料编号列表") @RequestParam(name = "portNums") List<String> portNumList,
            @ApiParam(value = "港口资料状态") @RequestParam(name = "portState") String portState) {
        RestResponse<String> response = null;
        try {
            int count = sysDataOperationService.updatePortState(portNumList, portState);
            if (count == portNumList.size()) {
                response = RestResponseUtil.success("操作成功！");
            } else {
                response = RestResponseUtil.note("操作失败！");
            }
        } catch (Exception e) {
            log.error("更新港口资料状态异常：", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "导出所有港口资料到Excel", notes = "导出所有港口资料到Excel")
    @GetMapping("exportPortDataToExcelAll")
    public RestResponse<Object> exportPortDataToExcelAll(HttpServletResponse httpServletResponse) {
        RestResponse<Object> response = null;
        try {
            List<port_data> portDataList = sysDataOperationService.queryAllPortObjectData();
            if (portDataList.size() == 0) {
                response = RestResponseUtil.note("港口资料为空！");
            } else {
                PortExcelOperationUtil.exportPortListtoExcel(httpServletResponse, portDataList);
            }
        } catch (Exception e) {
            log.error("导出所有港口资料到Excel异常：", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "导出港口资料到Excel", notes = "导出港口资料到Excel")
    @GetMapping("exportPortDataToExcel")
    public RestResponse<Object> exportPortDataToExcel(
            HttpServletResponse httpServletResponse,
            @ApiParam(value = "港口编号字符串") @RequestParam(name = "portCodeStr", required = false) String portCodeStr) {
        RestResponse<Object> response = null;
        try {
            if (portCodeStr == null || "".equals(portCodeStr)) {
                response = RestResponseUtil.note("请先选择要导出的数据！");
            } else {
                String[] portCodeArray = portCodeStr.split(",");
                List<port_data> portDataList = sysDataOperationService.queryPortDataList2(portCodeArray);
                PortExcelOperationUtil.exportPortListtoExcel(httpServletResponse, portDataList);
            }
        } catch (Exception e) {
            log.error("导出港口资料到Excel异常：", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }
}

