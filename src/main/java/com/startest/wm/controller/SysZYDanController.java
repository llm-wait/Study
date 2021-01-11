package com.startest.wm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.startest.wm.pojo.*;
import com.startest.wm.service.SysZYDanService;
import com.startest.wm.utils.UUIDGeneratorUtil;
import com.startest.wm.utils.ZyImportUtil;
import com.startest.wm.utils.ZydExportUtil;
import com.startest.wm.utils.customresponse.ExceptionHandleUtil;
import com.startest.wm.utils.customresponse.RestResponse;
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
import java.util.Iterator;
import java.util.List;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-07-03 15:33
 * @描述 制印单操作API
 **/
@Api(tags = "制印单模块相关API")
@RestController
@RequestMapping("/sys/zy/")
public class SysZYDanController {

    private static final Logger log= LoggerFactory.getLogger(SysZYDanController.class);

    @Autowired
    SysZYDanService sysZYDanService;

    /******************************map之MY*************************/

    @ApiOperation("添加map之MY相关制印单")
    @PostMapping("addMapMZyd")
    public RestResponse<String> addMapMZyd(
            @ApiParam(value = "map之MY制印单对象", required = true) @RequestBody jcd_mapm mapjcd
    ) {
        RestResponse<String> response = null;
        try {
            if (mapjcd != null) {
                boolean res = sysZYDanService.isMapMJcdExist(mapjcd.getBh());
                if (!res) {
                    mapjcd.setId(UUIDGeneratorUtil.getUUID());
                    mapjcd.setTask_index_id(UUIDGeneratorUtil.getUUID());
                    int count = sysZYDanService.insertMapMZyd(mapjcd);
                    if (count > 0) {
                        response = RestResponseUtil.success("添加成功！");
                    } else {
                        response = RestResponseUtil.note("添加失败！");
                    }
                } else {
                    response = RestResponseUtil.note(" 编号已存在！");
                }
            }
        } catch (Exception e) {
            log.error("添加map之MY相关制印单异常",e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation("更新map之MY相关制印单")
    @PostMapping("updateMapMZyd")
    public RestResponse<String> updateMapMZyd(
            @ApiParam(value = "map之MY制印单对象", required = true) @RequestBody jcd_mapm mapjcd
    ) {
        RestResponse<String> response = null;
        try {
            if (mapjcd != null) {
                //更新之前先查一下新的编号是否存在
                jcd_mapm jcdMapm = sysZYDanService.queryMapMZydByBh(mapjcd.getBh());
                if (jcdMapm != null && !jcdMapm.getId().equals(mapjcd.getId())) {
                    response = RestResponseUtil.note("编号已存在！");
                } else {
                    int res = sysZYDanService.updateMapMZyd(mapjcd);
                    if (res > 0) {
                        response = RestResponseUtil.success("更新成功！");
                    } else {
                        response = RestResponseUtil.note("更新失败！");
                    }
                }
            }
        } catch (Exception e) {
            log.error("修改map之MY相关制印单异常",e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation("根据ID删除map之MY相关制印单")
    @GetMapping("deleteMapMZydByID")
    public RestResponse<String> deleteMapMZydByID(
            @ApiParam(value = "map之MY制印单ID", required = true) @RequestParam(name = "id") String id
    ) {
        RestResponse<String> response = null;
        try {
            int res = sysZYDanService.deleteMapMZyd(id);
            if (res > 0) {
                response = RestResponseUtil.success("删除成功！");
            } else {
                response = RestResponseUtil.note("删除失败！");
            }
        } catch (Exception e) {
            log.error("根据ID删除map之MY相关制印单异常",e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation("根据ID获取map之MY相关制印单")
    @GetMapping("getMapMZydByID")
    public RestResponse<jcd_mapm> getMapMZydByID(
            @ApiParam(value = "map之MY制印单ID", required = true) @RequestParam(name = "id") String id
    ) {
        RestResponse<jcd_mapm> response = null;
        try {
            jcd_mapm mapjcd = sysZYDanService.queryMapMZydByID(id);
            response = RestResponseUtil.success("", mapjcd);
        } catch (Exception e) {
            log.error("根据ID获取map之MY相关制印单异常",e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation("自定义条件获取map之MY相关制印单")
    @GetMapping("getMapMZydByDefine")
    public RestResponse<PageInfo<jcd_mapm>> getMapMZydByDefine(
            @ApiParam(value = "map之MY制印单任务年份") @RequestParam(required = false) Integer taskYear,
            @ApiParam(value = "map之MY制印单任务名称") @RequestParam(required = false) String taskName,
            @ApiParam(value = "map之MY制印单任务状态") @RequestParam(required = false) String taskState,
            @ApiParam(value = "map之MY制印单图号") @RequestParam(required = false) String mapCode,
            @ApiParam(value = "map之MY制印单图名") @RequestParam(required = false) String mapName,
            @ApiParam(value = "map之MY制印单室别") @RequestParam(required = false) String sb,
            @ApiParam(value = "页码", required = true) @RequestParam(name = "page_number", defaultValue = "1") Integer pageNum
    ) {
        RestResponse<PageInfo<jcd_mapm>> response = null;
        try {
            PageHelper.startPage(pageNum, 100);
            List<jcd_mapm> jcdMapList = sysZYDanService.queryMapMZydByDefine(taskYear, taskName, taskState, mapCode, mapName, sb);
            PageInfo<jcd_mapm> pageInfo = new PageInfo<>(jcdMapList);
            response = RestResponseUtil.success("", pageInfo);
        } catch (Exception e) {
            log.error("自定义条件获取map之MY相关制印单异常",e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation("获取所有map之MY相关制印单")
    @GetMapping("getMapMZydAll")
    public RestResponse<PageInfo<jcd_mapm>> getMapMZydAll(
            @ApiParam(value = "页码", required = true) @RequestParam(name = "page_number", defaultValue = "1") Integer pageNum
    ) {
        RestResponse<PageInfo<jcd_mapm>> response = null;
        try {
            PageHelper.startPage(pageNum, 100);
            List<jcd_mapm> jcdMapList = sysZYDanService.queryMapMZydAll();
            PageInfo<jcd_mapm> pageInfo = new PageInfo<>(jcdMapList);
            response = RestResponseUtil.success("", pageInfo);
        } catch (Exception e) {
            log.error("获取所有map之MY相关制印单异常",e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "导入map之MY制印数据", notes = "导入map之MY制印数据")
    @PostMapping(value = "importMapMZyData", headers = "content-type=multipart/form-data")
    @Transactional(propagation = Propagation.REQUIRED)
    public RestResponse<String> importMapMZyData(
            @ApiParam("map之MY制印数据excel文件") @RequestParam(value = "file") MultipartFile file) {
        try {
            //1.判断文件类型
            String fileName = file.getOriginalFilename();
            if (!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx")) {
                return RestResponseUtil.note("文件格式错误！");
            } else {
                InputStream inputStream = file.getInputStream();
                List<jcd_mapm> mapJcdList = ZyImportUtil.readToJcdMapMData(inputStream, fileName);
                if (mapJcdList.size() > 0) {
                    //过滤掉已经存在的编号
                    Iterator iterator = mapJcdList.iterator();
                    while (iterator.hasNext()) {
                        jcd_mapm jcdMap = (jcd_mapm) iterator.next();
                        if (sysZYDanService.isMapMJcdExist(jcdMap.getBh())) {
                            iterator.remove();
                        }
                    }
                    inputStream.close();
                    if (mapJcdList.size() > 0) {
                        int count = sysZYDanService.insertMapMZydMany(mapJcdList);
                        if (count == mapJcdList.size()) {
                            return RestResponseUtil.success("批量导入成功！");
                        } else {
                            return RestResponseUtil.note("批量导入失败！");
                        }
                    } else {
                        return RestResponseUtil.note("编号都已经存在！");
                    }
                } else {
                    inputStream.close();
                    return RestResponseUtil.note("制印数据为空！");
                }
            }
        } catch (Exception e) {
            return RestResponseUtil.note("制印数据导入失败");
        }
    }


    //region 导出map之MY相关制印单Excel。不用了
    /*@ApiOperation("导出map之MY相关制印单Excel")
    @GetMapping("exportMapMZydExcelByID")
    public RestResponse<String> exportMapMZydExcelByID(
            HttpServletResponse response,
            @ApiParam(value = "map之MY制印单ID", required = true) @RequestParam(name = "id") String id
    ) {
        RestResponse<String> response1 = null;
        try {
            jcd_mapm mapjcd = sysZYDanService.queryMapMZydByID(id);
            InputStream inputStream = this.getClass().getResourceAsStream("/doc/zy/excel/jcdmap.xls");
            ZydExportUtil.exportMapMZydExcel(inputStream, response, mapjcd);
        } catch (Exception e) {
            response1 = ExceptionHandleUtil.handle(e);
        }
        return response1;
    }*/
    //endregion


    @ApiOperation("导出map之MY相关制印单PDF")
    @GetMapping("exportMapMZydPdfByID")
    public RestResponse<String> exportMapMZydPdfByID(
            HttpServletResponse response,
            @ApiParam(value = "map之MY制印单ID", required = true) @RequestParam(name = "id") String id
    ) {
        RestResponse<String> response1 = null;
        try {
            jcd_mapm mapjcd = sysZYDanService.queryMapMZydByID(id);
            String templatePath = this.getClass().getResource("/doc/zy/pdf/jcdmap.pdf").getFile();
            ZydExportUtil.exportMapMZydPdf(response, mapjcd, templatePath);
        } catch (Exception e) {
            response1 = ExceptionHandleUtil.handle(e);
        }
        return response1;
    }


    @ApiOperation("导出map之MY相关制印单列表")
    @GetMapping("exportMapMZydList")
    public RestResponse<String> exportMapMZydList(
            HttpServletResponse response,
            @ApiParam(value = "map之MY制印单任务年份") @RequestParam(required = false) Integer taskYear,
            @ApiParam(value = "map之MY制印单任务名称") @RequestParam(required = false) String taskName,
            @ApiParam(value = "map之MY制印单任务状态") @RequestParam(required = false) String taskState,
            @ApiParam(value = "map之MY制印单图号") @RequestParam(required = false) String mapCode,
            @ApiParam(value = "map之MY制印单图名") @RequestParam(required = false) String mapName,
            @ApiParam(value = "map之MY制印单室别") @RequestParam(required = false) String sb
    ) {
        RestResponse<String> response1 = null;
        try {
            List<jcd_mapm> jcdMapList = sysZYDanService.queryMapMZydByDefine(taskYear, taskName, taskState, mapCode, mapName, sb);
            ZydExportUtil.exportMapMZydListToExcel(jcdMapList, response);
        } catch (Exception e) {
            response1 = ExceptionHandleUtil.handle(e);
        }
        return response1;
    }


    @ApiOperation("发送map之MY制印单")
    @GetMapping("sendMapMZyd")
    public RestResponse<String> sendMapMZyd(
            @ApiParam(value = "map之MY制印单ID", required = true) @RequestParam(name = "id") String id
    ) {
        RestResponse<String> response1 = null;
        try {
            jcd_mapm jcdMap = sysZYDanService.queryMapMZydByID(id);
            if (!"已完成".equals(jcdMap.getTask_state())) {
                int res = sysZYDanService.updateMapMZydState(id);
                if (res > 0) {
                    response1 = RestResponseUtil.success("发送成功！");
                } else {
                    response1 = RestResponseUtil.note("发送失败！");
                }
            } else {
                response1 = RestResponseUtil.note("此任务已经完成！");
            }
        } catch (Exception e) {
            response1 = ExceptionHandleUtil.handle(e);
        }
        return response1;
    }


    /******************************map之JY*************************/

    @ApiOperation("添加map之JY相关制印单")
    @PostMapping("addMapJZyd")
    public RestResponse<String> addMapJZyd(
            @ApiParam(value = "map之JY制印单对象", required = true) @RequestBody jcd_mapj mapjcd
    ) {
        RestResponse<String> response = null;
        try {
            if (mapjcd != null) {
                boolean res = sysZYDanService.isMapJJcdExist(mapjcd.getBh());
                if (!res) {
                    mapjcd.setId(UUIDGeneratorUtil.getUUID());
                    mapjcd.setTask_index_id(UUIDGeneratorUtil.getUUID());
                    int count = sysZYDanService.insertMapJZyd(mapjcd);
                    if (count > 0) {
                        response = RestResponseUtil.success("添加成功！");
                    } else {
                        response = RestResponseUtil.note("添加失败！");
                    }
                } else {
                    response = RestResponseUtil.note("编号已存在！");
                }
            }
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }


    @ApiOperation("更新map之JY相关制印单")
    @PostMapping("updateMapJZyd")
    public RestResponse<String> updateMapJZyd(
            @ApiParam(value = "map之JY制印单对象", required = true) @RequestBody jcd_mapj mapjcd
    ) {
        RestResponse<String> response = null;
        try {
            if (mapjcd != null) {
                //更新之前先查一下新的编号是否存在
                jcd_mapj jcdMapj = sysZYDanService.queryMapJZydByBh(mapjcd.getBh());
                if (jcdMapj != null && !jcdMapj.getId().equals(mapjcd.getId())) {
                    response = RestResponseUtil.note("编号已存在！");
                } else {
                    int res = sysZYDanService.updateMapJZyd(mapjcd);
                    if (res > 0) {
                        response = RestResponseUtil.success("更新成功！");
                    } else {
                        response = RestResponseUtil.note("更新失败！");
                    }
                }
            }
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }


    @ApiOperation("根据ID删除map之JY相关制印单")
    @GetMapping("deleteMapJZydByID")
    public RestResponse<String> deleteMapJZydByID(
            @ApiParam(value = "map之JY制印单ID", required = true) @RequestParam(name = "id") String id
    ) {
        RestResponse<String> response = null;
        try {
            int res = sysZYDanService.deleteMapJZyd(id);
            if (res > 0) {
                response = RestResponseUtil.success("删除成功！");
            } else {
                response = RestResponseUtil.note("删除失败！");
            }
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }


    @ApiOperation("根据ID获取map之JY相关制印单")
    @GetMapping("getMapJZydByID")
    public RestResponse<jcd_mapj> getMapJZydByID(
            @ApiParam(value = "map之JY制印单ID", required = true) @RequestParam(name = "id") String id
    ) {
        RestResponse<jcd_mapj> response = null;
        try {
            jcd_mapj mapjcd = sysZYDanService.queryMapJZydByID(id);
            response = RestResponseUtil.success("", mapjcd);
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }


    @ApiOperation("自定义条件获取map之JY相关制印单")
    @GetMapping("getMapJZydByDefine")
    public RestResponse<PageInfo<jcd_mapj>> getMapJZydByDefine(
            @ApiParam(value = "map之JY制印单任务年份") @RequestParam(required = false) Integer taskYear,
            @ApiParam(value = "map之JY制印单任务名称") @RequestParam(required = false) String taskName,
            @ApiParam(value = "map之JY制印单任务状态") @RequestParam(required = false) String taskState,
            @ApiParam(value = "map之JY制印单图号") @RequestParam(required = false) String mapCode,
            @ApiParam(value = "map之JY制印单图名") @RequestParam(required = false) String mapName,
            @ApiParam(value = "map之JY制印单室别") @RequestParam(required = false) String sb,
            @ApiParam(value = "页码", required = true) @RequestParam(name = "page_number", defaultValue = "1") Integer pageNum
    ) {
        RestResponse<PageInfo<jcd_mapj>> response = null;
        try {
            PageHelper.startPage(pageNum, 100);
            List<jcd_mapj> jcdMapList = sysZYDanService.queryMapJZydByDefine(taskYear, taskName, taskState, mapCode, mapName, sb);
            PageInfo<jcd_mapj> pageInfo = new PageInfo<>(jcdMapList);
            response = RestResponseUtil.success("", pageInfo);
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }


    @ApiOperation("获取所有map之JY相关制印单")
    @GetMapping("getMapJZydAll")
    public RestResponse<PageInfo<jcd_mapj>> getMapJZydAll(
            @ApiParam(value = "页码", required = true) @RequestParam(name = "page_number", defaultValue = "1") Integer pageNum
    ) {
        RestResponse<PageInfo<jcd_mapj>> response = null;
        try {
            PageHelper.startPage(pageNum, 100);
            List<jcd_mapj> jcdMapList = sysZYDanService.queryMapJZydAll();
            PageInfo<jcd_mapj> pageInfo = new PageInfo<>(jcdMapList);
            response = RestResponseUtil.success("", pageInfo);
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }


    @ApiOperation(value = "导入map之JY制印数据", notes = "导入map之JY制印数据")
    @PostMapping(value = "importMapJZyData", headers = "content-type=multipart/form-data")
    @Transactional(propagation = Propagation.REQUIRED)
    public RestResponse<String> importMapJZyData(
            @ApiParam("map之JY制印数据excel文件") @RequestParam(value = "file") MultipartFile file) {
        try {
            //1.判断文件类型
            String fileName = file.getOriginalFilename();
            if (!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx")) {
                return RestResponseUtil.note("文件格式错误！");
            } else {
                InputStream inputStream = file.getInputStream();
                List<jcd_mapj> mapJcdList = ZyImportUtil.readToJcdMapJData(inputStream, fileName);
                if (mapJcdList.size() > 0) {
                    //过滤掉已经存在的编号
                    Iterator iterator = mapJcdList.iterator();
                    while (iterator.hasNext()) {
                        jcd_mapj jcdMap = (jcd_mapj) iterator.next();
                        if (sysZYDanService.isMapJJcdExist(jcdMap.getBh())) {
                            iterator.remove();
                        }
                    }
                    inputStream.close();
                    if (mapJcdList.size() > 0) {
                        int count = sysZYDanService.insertMapJZydMany(mapJcdList);
                        if (count == mapJcdList.size()) {
                            return RestResponseUtil.success("批量导入成功！");
                        } else {
                            return RestResponseUtil.note("批量导入失败！");
                        }
                    } else {
                        return RestResponseUtil.note("编号都已经存在！");
                    }
                } else {
                    inputStream.close();
                    return RestResponseUtil.note("制印数据为空！");
                }
            }
        } catch (Exception e) {
            return RestResponseUtil.note("制印数据导入失败");
        }
    }



    @ApiOperation("导出map之JY相关制印单Pdf")
    @GetMapping("exportMapJZydPdfByID")
    public RestResponse<String> exportMapJZydPdfByID(
            HttpServletResponse response,
            @ApiParam(value = "map之JY制印单ID", required = true) @RequestParam(name = "id") String id
    ) {
        RestResponse<String> response1 = null;
        try {
            jcd_mapj mapjcd = sysZYDanService.queryMapJZydByID(id);
            String templatePath = this.getClass().getResource("/doc/zy/pdf/jcdmap.pdf").getFile();
            ZydExportUtil.exportMapJZydPdf(response, mapjcd, templatePath);
        } catch (Exception e) {
            response1 = ExceptionHandleUtil.handle(e);
        }
        return response1;
    }


    @ApiOperation("导出map之JY相关制印单列表")
    @GetMapping("exportMapJZydList")
    public RestResponse<String> exportMapJZydList(
            HttpServletResponse response,
            @ApiParam(value = "map之JY制印单任务年份") @RequestParam(required = false) Integer taskYear,
            @ApiParam(value = "map之JY制印单任务名称") @RequestParam(required = false) String taskName,
            @ApiParam(value = "map之JY制印单任务状态") @RequestParam(required = false) String taskState,
            @ApiParam(value = "map之JY制印单图号") @RequestParam(required = false) String mapCode,
            @ApiParam(value = "map之JY制印单图名") @RequestParam(required = false) String mapName,
            @ApiParam(value = "map之JY制印单室别") @RequestParam(required = false) String sb
    ) {
        RestResponse<String> response1 = null;
        try {
            List<jcd_mapj> jcdMapList = sysZYDanService.queryMapJZydByDefine(taskYear, taskName, taskState, mapCode, mapName, sb);
            ZydExportUtil.exportMapJZydListToExcel(jcdMapList, response);
        } catch (Exception e) {
            response1 = ExceptionHandleUtil.handle(e);
        }
        return response1;
    }


    @ApiOperation("发送map之JY制印单")
    @GetMapping("sendMapJZyd")
    public RestResponse<String> sendMapJZyd(
            @ApiParam(value = "map之JY制印单ID", required = true) @RequestParam(name = "id") String id
    ) {
        RestResponse<String> response1 = null;
        try {
            jcd_mapj jcdMap = sysZYDanService.queryMapJZydByID(id);
            if (!"已完成".equals(jcdMap.getTask_state())) {
                int res = sysZYDanService.updateMapJZydState(id);
                if (res > 0) {
                    response1 = RestResponseUtil.success("发送成功！");
                } else {
                    response1 = RestResponseUtil.note("发送失败！");
                }
            } else {
                response1 = RestResponseUtil.note("此任务已经完成！");
            }
        } catch (Exception e) {
            response1 = ExceptionHandleUtil.handle(e);
        }
        return response1;
    }


    /******************************map之BZBD*************************/

    @ApiOperation("添加map之BZBD相关制印单")
    @PostMapping("addMapBZyd")
    public RestResponse<String> addMapBZyd(
            @ApiParam(value = "map之BZBD制印单对象", required = true) @RequestBody jcd_mapb mapjcd
    ) {
        RestResponse<String> response = null;
        try {
            if (mapjcd != null) {
                boolean res = sysZYDanService.isMapBJcdExist(mapjcd.getBh());
                if (!res) {
                    mapjcd.setId(UUIDGeneratorUtil.getUUID());
                    mapjcd.setTask_index_id(UUIDGeneratorUtil.getUUID());
                    int count = sysZYDanService.insertMapBZyd(mapjcd);
                    if (count > 0) {
                        response = RestResponseUtil.success("添加成功！");
                    } else {
                        response = RestResponseUtil.note("添加失败！");
                    }
                } else {
                    response = RestResponseUtil.note("编号已存在！");
                }
            }
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }


    @ApiOperation("更新map之BZBD相关制印单")
    @PostMapping("updateMapBZyd")
    public RestResponse<String> updateMapBZyd(
            @ApiParam(value = "map之BZBD制印单对象", required = true) @RequestBody jcd_mapb mapjcd
    ) {
        RestResponse<String> response = null;
        try {
            if (mapjcd != null) {
                //更新之前先查一下新的编号是否存在
                jcd_mapb jcdMapb = sysZYDanService.queryMapBZydByBh(mapjcd.getBh());
                if (jcdMapb != null && !jcdMapb.getId().equals(mapjcd.getId())) {
                    response = RestResponseUtil.note("编号已存在！");
                } else {
                    int res = sysZYDanService.updateMapBZyd(mapjcd);
                    if (res > 0) {
                        response = RestResponseUtil.success("更新成功！");
                    } else {
                        response = RestResponseUtil.note("更新失败！");
                    }
                }
            }
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }


    @ApiOperation("根据ID删除map之BZBD相关制印单")
    @GetMapping("deleteMapBZydByID")
    public RestResponse<String> deleteMapBZydByID(
            @ApiParam(value = "map之BZBD制印单ID", required = true) @RequestParam(name = "id") String id
    ) {
        RestResponse<String> response = null;
        try {
            int res = sysZYDanService.deleteMapBZyd(id);
            if (res > 0) {
                response = RestResponseUtil.success("删除成功！");
            } else {
                response = RestResponseUtil.note("删除失败！");
            }
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }


    @ApiOperation("根据ID获取map之BZBD相关制印单")
    @GetMapping("getMapBZydByID")
    public RestResponse<jcd_mapb> getMapBZydByID(
            @ApiParam(value = "map之BZBD制印单ID", required = true) @RequestParam(name = "id") String id
    ) {
        RestResponse<jcd_mapb> response = null;
        try {
            jcd_mapb mapjcd = sysZYDanService.queryMapBZydByID(id);
            response = RestResponseUtil.success("", mapjcd);
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }


    @ApiOperation("自定义条件获取map之BZBD相关制印单")
    @GetMapping("getMapBZydByDefine")
    public RestResponse<PageInfo<jcd_mapb>> getMapBZydByDefine(
            @ApiParam(value = "map之BZBD制印单任务年份") @RequestParam(required = false) Integer taskYear,
            @ApiParam(value = "map之BZBD制印单任务名称") @RequestParam(required = false) String taskName,
            @ApiParam(value = "map之BZBD制印单任务状态") @RequestParam(required = false) String taskState,
            @ApiParam(value = "map之BZBD制印单图号") @RequestParam(required = false) String mapCode,
            @ApiParam(value = "map之BZBD制印单图名") @RequestParam(required = false) String mapName,
            @ApiParam(value = "map之BZBD制印单室别") @RequestParam(required = false) String sb,
            @ApiParam(value = "页码", required = true) @RequestParam(name = "page_number", defaultValue = "1") Integer pageNum
    ) {
        RestResponse<PageInfo<jcd_mapb>> response = null;
        try {
            PageHelper.startPage(pageNum, 100);
            List<jcd_mapb> jcdMapList = sysZYDanService.queryMapBZydByDefine(taskYear, taskName, taskState, mapCode, mapName, sb);
            PageInfo<jcd_mapb> pageInfo = new PageInfo<>(jcdMapList);
            response = RestResponseUtil.success("", pageInfo);
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }


    @ApiOperation("获取所有map之BZBD相关制印单")
    @GetMapping("getMapBZydAll")
    public RestResponse<PageInfo<jcd_mapb>> getMapBZydAll(
            @ApiParam(value = "页码", required = true) @RequestParam(name = "page_number", defaultValue = "1") Integer pageNum
    ) {
        RestResponse<PageInfo<jcd_mapb>> response = null;
        try {
            PageHelper.startPage(pageNum, 100);
            List<jcd_mapb> jcdMapList = sysZYDanService.queryMapBZydAll();
            PageInfo<jcd_mapb> pageInfo = new PageInfo<>(jcdMapList);
            response = RestResponseUtil.success("", pageInfo);
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }


    @ApiOperation(value = "导入map之BZBD制印数据", notes = "导入map之MY制印数据")
    @PostMapping(value = "importMapBZyData", headers = "content-type=multipart/form-data")
    @Transactional(propagation = Propagation.REQUIRED)
    public RestResponse<String> importMapBZyData(
            @ApiParam("map之BZBD制印数据excel文件") @RequestParam(value = "file") MultipartFile file) {
        try {
            //1.判断文件类型
            String fileName = file.getOriginalFilename();
            if (!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx")) {
                return RestResponseUtil.note("文件格式错误！");
            } else {
                InputStream inputStream = file.getInputStream();
                List<jcd_mapb> mapJcdList = ZyImportUtil.readToJcdMapBData(inputStream, fileName);
                if (mapJcdList.size() > 0) {
                    //过滤掉已经存在的编号
                    Iterator iterator = mapJcdList.iterator();
                    while (iterator.hasNext()) {
                        jcd_mapb jcdMap = (jcd_mapb) iterator.next();
                        if (sysZYDanService.isMapBJcdExist(jcdMap.getBh())) {
                            iterator.remove();
                        }
                    }
                    inputStream.close();
                    if (mapJcdList.size() > 0) {
                        int count = sysZYDanService.insertMapBZydMany(mapJcdList);
                        if (count == mapJcdList.size()) {
                            return RestResponseUtil.success("批量导入成功！");
                        } else {
                            return RestResponseUtil.note("批量导入失败！");
                        }
                    } else {
                        return RestResponseUtil.note("编号都已经存在！");
                    }
                } else {
                    inputStream.close();
                    return RestResponseUtil.note("制印数据为空！");
                }
            }
        } catch (Exception e) {
            return RestResponseUtil.note("制印数据导入失败");
        }
    }


    @ApiOperation("导出map之BZBD相关制印单Pdf")
    @GetMapping("exportMapBZydPdfByID")
    public RestResponse<String> exportMapBZydPdfByID(
            HttpServletResponse response,
            @ApiParam(value = "map之BZBD制印单ID", required = true) @RequestParam(name = "id") String id
    ) {
        RestResponse<String> response1 = null;
        try {
            jcd_mapb mapjcd = sysZYDanService.queryMapBZydByID(id);
            String templatePath = this.getClass().getResource("/doc/zy/pdf/jcdmap.pdf").getFile();
            ZydExportUtil.exportMapBZydPdf(response, mapjcd, templatePath);
        } catch (Exception e) {
            response1 = ExceptionHandleUtil.handle(e);
        }
        return response1;
    }


    @ApiOperation("导出map之BZBD相关制印单列表")
    @GetMapping("exportMapBZydList")
    public RestResponse<String> exportMapBZydList(
            HttpServletResponse response,
            @ApiParam(value = "map之BZBD制印单任务年份") @RequestParam(required = false) Integer taskYear,
            @ApiParam(value = "map之BZBD制印单任务名称") @RequestParam(required = false) String taskName,
            @ApiParam(value = "map之BZBD制印单任务状态") @RequestParam(required = false) String taskState,
            @ApiParam(value = "map之BZBD制印单图号") @RequestParam(required = false) String mapCode,
            @ApiParam(value = "map之BZBD制印单图名") @RequestParam(required = false) String mapName,
            @ApiParam(value = "map之BZBD制印单室别") @RequestParam(required = false) String sb
    ) {
        RestResponse<String> response1 = null;
        try {
            List<jcd_mapb> jcdMapList = sysZYDanService.queryMapBZydByDefine(taskYear, taskName, taskState, mapCode, mapName, sb);
            ZydExportUtil.exportMapBZydListToExcel(jcdMapList, response);
        } catch (Exception e) {
            response1 = ExceptionHandleUtil.handle(e);
        }
        return response1;
    }


    @ApiOperation("发送map之BZBD制印单")
    @GetMapping("sendMapBZyd")
    public RestResponse<String> sendMapBZyd(
            @ApiParam(value = "map之BZBD制印单ID", required = true) @RequestParam(name = "id") String id
    ) {
        RestResponse<String> response1 = null;
        try {
            jcd_mapb jcdMap = sysZYDanService.queryMapBZydByID(id);
            if (!"已完成".equals(jcdMap.getTask_state())) {
                int res = sysZYDanService.updateMapBZydState(id);
                if (res > 0) {
                    response1 = RestResponseUtil.success("发送成功！");
                } else {
                    response1 = RestResponseUtil.note("发送失败！");
                }
            } else {
                response1 = RestResponseUtil.note("此任务已经完成！");
            }
        } catch (Exception e) {
            response1 = ExceptionHandleUtil.handle(e);
        }
        return response1;
    }

    /********************************book之MY**************************/

    @ApiOperation("添加book之MY相关制印单")
    @PostMapping("addBookMZyd")
    public RestResponse<String> addBookMZyd(
            @ApiParam(value = "book之MY制印单对象", required = true) @RequestBody jcd_bookm bookjcd
    ) {
        RestResponse<String> response = null;
        try {
            if (bookjcd != null) {
                boolean res = sysZYDanService.isBookMJcdExist(bookjcd.getBh());
                if (!res) {
                    bookjcd.setId(UUIDGeneratorUtil.getUUID());
                    bookjcd.setTask_index_id(UUIDGeneratorUtil.getUUID());
                    int count = sysZYDanService.insertBookMZyd(bookjcd);
                    if (count > 0) {
                        response = RestResponseUtil.success("添加成功！");
                    } else {
                        response = RestResponseUtil.note("添加失败！");
                    }
                } else {
                    response = RestResponseUtil.note("编号已存在！");
                }
            }
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }


    @ApiOperation("更新book之MY相关制印单")
    @PostMapping("updateBookMZyd")
    public RestResponse<String> updateBookMZyd(
            @ApiParam(value = "book之MY制印单对象", required = true) @RequestBody jcd_bookm bookjcd
    ) {
        RestResponse<String> response = null;
        try {
            if (bookjcd != null) {
                //更新之前先查一下新的编号是否存在
                jcd_bookm jcdBookm = sysZYDanService.queryBookMZydByBh(bookjcd.getBh());
                if (jcdBookm != null && !jcdBookm.getId().equals(bookjcd.getId())) {
                    response = RestResponseUtil.note("编号已存在！");
                } else {
                    int res = sysZYDanService.updateBookMZyd(bookjcd);
                    if (res > 0) {
                        response = RestResponseUtil.success("更新成功！");
                    } else {
                        response = RestResponseUtil.note("更新失败！");
                    }
                }
            }
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }


    @ApiOperation("根据ID删除book之MY相关制印单")
    @GetMapping("deleteBookMZydByID")
    public RestResponse<String> deleteBookMZydByID(
            @ApiParam(value = "book之MY制印单ID", required = true) @RequestParam(name = "id") String id
    ) {
        RestResponse<String> response = null;
        try {
            int res = sysZYDanService.deleteBookMZyd(id);
            if (res > 0) {
                response = RestResponseUtil.success("删除成功！");
            } else {
                response = RestResponseUtil.note("删除失败！");
            }
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }


    @ApiOperation("根据ID获取book之MY相关制印单")
    @GetMapping("getBookMZydByID")
    public RestResponse<jcd_bookm> getBookMZydByID(
            @ApiParam(value = "book之MY制印单ID", required = true) @RequestParam(name = "id") String id
    ) {
        RestResponse<jcd_bookm> response = null;
        try {
            jcd_bookm bookjcd = sysZYDanService.queryBookMZydByID(id);
            response = RestResponseUtil.success("", bookjcd);
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }


    @ApiOperation("自定义条件获取book之MY相关制印单")
    @GetMapping("getBookMZydByDefine")
    public RestResponse<PageInfo<jcd_bookm>> getBookMZydByDefine(
            @ApiParam(value = "book之MY制印单任务年份") @RequestParam(required = false) Integer taskYear,
            @ApiParam(value = "book之MY制印单任务名称") @RequestParam(required = false) String taskName,
            @ApiParam(value = "book之MY制印单任务状态") @RequestParam(required = false) String taskState,
            @ApiParam(value = "book之MY制印单书号") @RequestParam(required = false) String sh,
            @ApiParam(value = "book之MY制印单书名") @RequestParam(required = false) String sm,
            @ApiParam(value = "book之MY制印单室别") @RequestParam(required = false) String sb,
            @ApiParam(value = "页码", required = true) @RequestParam(name = "page_number", defaultValue = "1") Integer pageNum
    ) {
        RestResponse<PageInfo<jcd_bookm>> response = null;
        try {
            PageHelper.startPage(pageNum, 100);
            List<jcd_bookm> jcdBookList = sysZYDanService.queryBookMZydByDefine(taskYear, taskName, taskState, sh, sm, sb);
            PageInfo<jcd_bookm> pageInfo = new PageInfo<>(jcdBookList);
            response = RestResponseUtil.success("", pageInfo);
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }


    @ApiOperation("获取所有book之MY相关制印单")
    @GetMapping("getBookMZydAll")
    public RestResponse<PageInfo<jcd_bookm>> getBookMZydAll(
            @ApiParam(value = "页码", required = true) @RequestParam(name = "page_number", defaultValue = "1") Integer pageNum
    ) {
        RestResponse<PageInfo<jcd_bookm>> response = null;
        try {
            PageHelper.startPage(pageNum, 100);
            List<jcd_bookm> jcdBookList = sysZYDanService.queryBookMZydAll();
            PageInfo<jcd_bookm> pageInfo = new PageInfo<>(jcdBookList);
            response = RestResponseUtil.success("", pageInfo);
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }


    @ApiOperation(value = "导入book之MY制印数据", notes = "导入book之MY制印数据")
    @PostMapping(value = "importBookMZyData", headers = "content-type=multipart/form-data")
    @Transactional(propagation = Propagation.REQUIRED)
    public RestResponse<String> importBookMZyData(
            @ApiParam("book之MY制印数据excel文件") @RequestParam(value = "file") MultipartFile file) {
        try {
            //1.判断文件类型
            String fileName = file.getOriginalFilename();
            if (!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx")) {
                return RestResponseUtil.note("文件格式错误！");
            } else {
                InputStream inputStream = file.getInputStream();
                List<jcd_bookm> bookJcdList = ZyImportUtil.readToJcdBookMData(inputStream, fileName);
                if (bookJcdList.size() > 0) {
                    //过滤掉已经存在的编号
                    Iterator iterator = bookJcdList.iterator();
                    while (iterator.hasNext()) {
                        jcd_bookm bookMap = (jcd_bookm) iterator.next();
                        if (sysZYDanService.isBookMJcdExist(bookMap.getBh())) {
                            iterator.remove();
                        }
                    }
                    inputStream.close();
                    if (bookJcdList.size() > 0) {
                        int count = sysZYDanService.insertBookMZydMany(bookJcdList);
                        if (count == bookJcdList.size()) {
                            return RestResponseUtil.success("批量导入成功！");
                        } else {
                            return RestResponseUtil.note("批量导入失败！");
                        }
                    } else {
                        return RestResponseUtil.note("编号都已经存在！");
                    }
                } else {
                    inputStream.close();
                    return RestResponseUtil.note("制印数据为空！");
                }
            }
        } catch (Exception e) {
            return RestResponseUtil.note("制印数据导入失败");
        }
    }



    @ApiOperation("导出book之MY制印单Pdf")
    @GetMapping("exportBookMZydPdfByID")
    public RestResponse<String> exportBookMZydPdfByID(
            HttpServletResponse response,
            @ApiParam(value = "book之MY制印单ID", required = true) @RequestParam(name = "id") String id
    ) {
        RestResponse<String> response1 = null;
        try {
            jcd_bookm jcdBook = sysZYDanService.queryBookMZydByID(id);
            String templatePath = this.getClass().getResource("/doc/zy/pdf/jcdbook.pdf").getFile();
            ZydExportUtil.exportBookMZydPdf(response, jcdBook, templatePath);
        } catch (Exception e) {
            response1 = ExceptionHandleUtil.handle(e);
        }
        return response1;
    }


    @ApiOperation("导出book之MY相关制印单列表")
    @GetMapping("exportBookMZydList")
    public RestResponse<String> exportBookMZydList(
            HttpServletResponse response,
            @ApiParam(value = "book之MY制印单任务年份") @RequestParam(required = false) Integer taskYear,
            @ApiParam(value = "book之MY制印单任务名称") @RequestParam(required = false) String taskName,
            @ApiParam(value = "book之MY制印单任务状态") @RequestParam(required = false) String taskState,
            @ApiParam(value = "book之MY制印单书号") @RequestParam(required = false) String sh,
            @ApiParam(value = "book之MY制印单书名") @RequestParam(required = false) String sm,
            @ApiParam(value = "book之MY制印单室别") @RequestParam(required = false) String sb
    ) {
        RestResponse<String> response1 = null;
        try {
            List<jcd_bookm> jcdBookList = sysZYDanService.queryBookMZydByDefine(taskYear, taskName, taskState, sh, sm, sb);
            ZydExportUtil.exportBookMZydListToExcel(jcdBookList, response);
        } catch (Exception e) {
            response1 = ExceptionHandleUtil.handle(e);
        }
        return response1;
    }


    @ApiOperation("发送book之MY制印单")
    @GetMapping("sendBookMZyd")
    public RestResponse<String> sendBookMZyd(
            @ApiParam(value = "book之MY制印单ID", required = true) @RequestParam(name = "id") String id
    ) {
        RestResponse<String> response1 = null;
        try {
            jcd_bookm jcdBook = sysZYDanService.queryBookMZydByID(id);
            if (!"已完成".equals(jcdBook.getTask_state())) {
                int res = sysZYDanService.updateBookMZydState(id);
                if (res > 0) {
                    response1 = RestResponseUtil.success("发送成功！");
                } else {
                    response1 = RestResponseUtil.note("发送失败！");
                }
            } else {
                response1 = RestResponseUtil.note("此任务已经完成！");
            }
        } catch (Exception e) {
            response1 = ExceptionHandleUtil.handle(e);
        }
        return response1;
    }


    /********************************book之JY**************************/

    @ApiOperation("添加book之JY相关制印单")
    @PostMapping("addBookJZyd")
    public RestResponse<String> addBookJZyd(
            @ApiParam(value = "书表制印单对象", required = true) @RequestBody jcd_bookj bookjcd
    ) {
        RestResponse<String> response = null;
        try {
            if (bookjcd != null) {
                boolean res = sysZYDanService.isBookJJcdExist(bookjcd.getBh());
                if (!res) {
                    bookjcd.setId(UUIDGeneratorUtil.getUUID());
                    bookjcd.setTask_index_id(UUIDGeneratorUtil.getUUID());
                    int count = sysZYDanService.insertBookJZyd(bookjcd);
                    if (count > 0) {
                        response = RestResponseUtil.success("添加成功！");
                    } else {
                        response = RestResponseUtil.note("添加失败！");
                    }
                } else {
                    response = RestResponseUtil.note("编号已存在！");
                }
            }
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }


    @ApiOperation("更新book之JY相关制印单")
    @PostMapping("updateBookJZyd")
    public RestResponse<String> updateBookJZyd(
            @ApiParam(value = "book之JY制印单对象", required = true) @RequestBody jcd_bookj bookjcd
    ) {
        RestResponse<String> response = null;
        try {
            if (bookjcd != null) {
                //更新之前先查一下新的编号是否存在
                jcd_bookj jcdBookj = sysZYDanService.queryBookJZydByBh(bookjcd.getBh());
                if (jcdBookj != null && !jcdBookj.getId().equals(bookjcd.getId())) {
                    response = RestResponseUtil.note("编号已存在！");
                } else {
                    int res = sysZYDanService.updateBookJZyd(bookjcd);
                    if (res > 0) {
                        response = RestResponseUtil.success("更新成功！");
                    } else {
                        response = RestResponseUtil.note("更新失败！");
                    }
                }
            }
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }


    @ApiOperation("根据ID删除book之JY相关制印单")
    @GetMapping("deleteBookJZydByID")
    public RestResponse<String> deleteBookJZydByID(
            @ApiParam(value = "book之JY制印单ID", required = true) @RequestParam(name = "id") String id
    ) {
        RestResponse<String> response = null;
        try {
            int res = sysZYDanService.deleteBookJZyd(id);
            if (res > 0) {
                response = RestResponseUtil.success("删除成功！");
            } else {
                response = RestResponseUtil.note("删除失败！");
            }
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }


    @ApiOperation("根据ID获取book之JY相关制印单")
    @GetMapping("getBookJZydByID")
    public RestResponse<jcd_bookj> getBookJZydByID(
            @ApiParam(value = "book之JY制印单ID", required = true) @RequestParam(name = "id") String id
    ) {
        RestResponse<jcd_bookj> response = null;
        try {
            jcd_bookj bookjcd = sysZYDanService.queryBookJZydByID(id);
            response = RestResponseUtil.success("", bookjcd);
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }


    @ApiOperation("自定义条件获取book之JY相关制印单")
    @GetMapping("getBookJZydByDefine")
    public RestResponse<PageInfo<jcd_bookj>> getBookJZydByDefine(
            @ApiParam(value = "book之JY制印单任务年份") @RequestParam(required = false) Integer taskYear,
            @ApiParam(value = "book之JY制印单任务名称") @RequestParam(required = false) String taskName,
            @ApiParam(value = "book之JY制印单任务状态") @RequestParam(required = false) String taskState,
            @ApiParam(value = "book之JY制印单书号") @RequestParam(required = false) String sh,
            @ApiParam(value = "book之JY制印单书名") @RequestParam(required = false) String sm,
            @ApiParam(value = "book之JY制印单室别") @RequestParam(required = false) String sb,
            @ApiParam(value = "页码", required = true) @RequestParam(name = "page_number", defaultValue = "1") Integer pageNum
    ) {
        RestResponse<PageInfo<jcd_bookj>> response = null;
        try {
            PageHelper.startPage(pageNum, 100);
            List<jcd_bookj> jcdBookList = sysZYDanService.queryBookJZydByDefine(taskYear, taskName, taskState, sh, sm, sb);
            PageInfo<jcd_bookj> pageInfo = new PageInfo<>(jcdBookList);
            response = RestResponseUtil.success("", pageInfo);
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }


    @ApiOperation("获取所有book之JY相关制印单")
    @GetMapping("getBookJZydAll")
    public RestResponse<PageInfo<jcd_bookj>> getBookJZydAll(
            @ApiParam(value = "页码", required = true) @RequestParam(name = "page_number", defaultValue = "1") Integer pageNum
    ) {
        RestResponse<PageInfo<jcd_bookj>> response = null;
        try {
            PageHelper.startPage(pageNum, 100);
            List<jcd_bookj> jcdBookList = sysZYDanService.queryBookJZydAll();
            PageInfo<jcd_bookj> pageInfo = new PageInfo<>(jcdBookList);
            response = RestResponseUtil.success("", pageInfo);
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }


    @ApiOperation(value = "导入book之JY制印数据", notes = "导入book之JY制印数据")
    @PostMapping(value = "importBookJZyData", headers = "content-type=multipart/form-data")
    @Transactional(propagation = Propagation.REQUIRED)
    public RestResponse<String> importBookJZyData(
            @ApiParam("book之JY制印数据excel文件") @RequestParam(value = "file") MultipartFile file) {
        try {
            //1.判断文件类型
            String fileName = file.getOriginalFilename();
            if (!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx")) {
                return RestResponseUtil.note("文件格式错误！");
            } else {
                InputStream inputStream = file.getInputStream();
                List<jcd_bookj> bookJcdList = ZyImportUtil.readToJcdBookJData(inputStream, fileName);
                if (bookJcdList.size() > 0) {
                    //过滤掉已经存在的编号
                    Iterator iterator = bookJcdList.iterator();
                    while (iterator.hasNext()) {
                        jcd_bookj bookMap = (jcd_bookj) iterator.next();
                        if (sysZYDanService.isBookJJcdExist(bookMap.getBh())) {
                            iterator.remove();
                        }
                    }
                    inputStream.close();
                    if (bookJcdList.size() > 0) {
                        int count = sysZYDanService.insertBookJZydMany(bookJcdList);
                        if (count == bookJcdList.size()) {
                            return RestResponseUtil.success("批量导入成功！");
                        } else {
                            return RestResponseUtil.note("批量导入失败！");
                        }
                    } else {
                        return RestResponseUtil.note("编号都已经存在！");
                    }
                } else {
                    inputStream.close();
                    return RestResponseUtil.note("制印数据为空！");
                }
            }
        } catch (Exception e) {
            return RestResponseUtil.note("制印数据导入失败");
        }
    }



    @ApiOperation("导出book之JY制印单Pdf")
    @GetMapping("exportBookJZydPdfByID")
    public RestResponse<String> exportBookJZydPdfByID(
            HttpServletResponse response,
            @ApiParam(value = "制印单ID", required = true) @RequestParam(name = "id") String id
    ) {
        RestResponse<String> response1 = null;
        try {
            jcd_bookj jcdBook = sysZYDanService.queryBookJZydByID(id);
            String templatePath = this.getClass().getResource("/doc/zy/pdf/jcdbook.pdf").getFile();
            ZydExportUtil.exportBookJZydPdf(response, jcdBook, templatePath);
        } catch (Exception e) {
            response1 = ExceptionHandleUtil.handle(e);
        }
        return response1;
    }


    @ApiOperation("导出book之JY相关制印单列表")
    @GetMapping("exportBookJZydList")
    public RestResponse<String> exportBookJZydList(
            HttpServletResponse response,
            @ApiParam(value = "book之JY制印单任务年份") @RequestParam(required = false) Integer taskYear,
            @ApiParam(value = "book之JY制印单任务名称") @RequestParam(required = false) String taskName,
            @ApiParam(value = "book之JY制印单任务状态") @RequestParam(required = false) String taskState,
            @ApiParam(value = "book之JY制印单书号") @RequestParam(required = false) String sh,
            @ApiParam(value = "book之JY制印单书名") @RequestParam(required = false) String sm,
            @ApiParam(value = "book之JY制印单室别") @RequestParam(required = false) String sb
    ) {
        RestResponse<String> response1 = null;
        try {
            List<jcd_bookj> jcdBookList = sysZYDanService.queryBookJZydByDefine(taskYear, taskName, taskState, sh, sm, sb);
            ZydExportUtil.exportBookJZydListToExcel(jcdBookList, response);
        } catch (Exception e) {
            response1 = ExceptionHandleUtil.handle(e);
        }
        return response1;
    }


    @ApiOperation("发送book之JY制印单")
    @GetMapping("sendBookJZyd")
    public RestResponse<String> sendBookJZyd(
            @ApiParam(value = "book之JY制印单ID", required = true) @RequestParam(name = "id") String id
    ) {
        RestResponse<String> response1 = null;
        try {
            jcd_bookj jcdBook = sysZYDanService.queryBookJZydByID(id);
            if (!"已完成".equals(jcdBook.getTask_state())) {
                int res = sysZYDanService.updateBookJZydState(id);
                if (res > 0) {
                    response1 = RestResponseUtil.success("发送成功！");
                } else {
                    response1 = RestResponseUtil.note("发送失败！");
                }
            } else {
                response1 = RestResponseUtil.note("此任务已经完成！");
            }
        } catch (Exception e) {
            response1 = ExceptionHandleUtil.handle(e);
        }
        return response1;
    }


    /********************************book之BZBD**************************/

    @ApiOperation("添加book之BZBD相关制印单")
    @PostMapping("addBookBZyd")
    public RestResponse<String> addBookBZyd(
            @ApiParam(value = "book之BZBD制印单对象", required = true) @RequestBody jcd_bookb bookjcd
    ) {
        RestResponse<String> response = null;
        try {
            if (bookjcd != null) {
                //判断编号是否存在，编号不能重复
                boolean res = sysZYDanService.isBookBJcdExist(bookjcd.getBh());
                if (!res) {
                    bookjcd.setId(UUIDGeneratorUtil.getUUID());
                    bookjcd.setTask_index_id(UUIDGeneratorUtil.getUUID());
                    int count = sysZYDanService.insertBookBZyd(bookjcd);
                    if (count > 0) {
                        response = RestResponseUtil.success("添加成功！");
                    } else {
                        response = RestResponseUtil.note("添加失败！");
                    }
                } else {
                    response = RestResponseUtil.note("编号已存在！");
                }
            }
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }


    @ApiOperation("更新book之BZBD相关制印单")
    @PostMapping("updateBookBZyd")
    public RestResponse<String> updateBookBZyd(
            @ApiParam(value = "book之BZBD制印单对象", required = true) @RequestBody jcd_bookb bookjcd
    ) {
        RestResponse<String> response = null;
        try {
            if (bookjcd != null) {
                //更新之前先查一下新的编号是否存在
                jcd_bookb jcdBookb = sysZYDanService.queryBookBZydByBh(bookjcd.getBh());
                if (jcdBookb != null && !jcdBookb.getId().equals(bookjcd.getId())) {
                    response = RestResponseUtil.note("编号已存在！");
                } else {
                    int res = sysZYDanService.updateBookBZyd(bookjcd);
                    if (res > 0) {
                        response = RestResponseUtil.success("更新成功！");
                    } else {
                        response = RestResponseUtil.note("更新失败！");
                    }
                }
            }
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }


    @ApiOperation("根据ID删除book之BZBD相关制印单")
    @GetMapping("deleteBookBZydByID")
    public RestResponse<String> deleteBookBZydByID(
            @ApiParam(value = "book之BZBD制印单ID", required = true) @RequestParam(name = "id") String id
    ) {
        RestResponse<String> response = null;
        try {
            int res = sysZYDanService.deleteBookBZyd(id);
            if (res > 0) {
                response = RestResponseUtil.success("删除成功！");
            } else {
                response = RestResponseUtil.note("删除失败！");
            }
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }


    @ApiOperation("根据ID获取book之BZBD相关制印单")
    @GetMapping("getBookBZydByID")
    public RestResponse<jcd_bookb> getBookBZydByID(
            @ApiParam(value = "book之BZBD制印单ID", required = true) @RequestParam(name = "id") String id
    ) {
        RestResponse<jcd_bookb> response = null;
        try {
            jcd_bookb bookjcd = sysZYDanService.queryBookBZydByID(id);
            response = RestResponseUtil.success("", bookjcd);
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }


    @ApiOperation("自定义条件获取book之BZBD相关制印单")
    @GetMapping("getBookBZydByDefine")
    public RestResponse<PageInfo<jcd_bookb>> getBookBZydByDefine(
            @ApiParam(value = "book之BZBD制印单任务年份") @RequestParam(required = false) Integer taskYear,
            @ApiParam(value = "book之BZBD制印单任务名称") @RequestParam(required = false) String taskName,
            @ApiParam(value = "book之BZBD制印单任务状态") @RequestParam(required = false) String taskState,
            @ApiParam(value = "book之BZBD制印单书号") @RequestParam(required = false) String sh,
            @ApiParam(value = "book之BZBD制印单书名") @RequestParam(required = false) String sm,
            @ApiParam(value = "book之BZBD制印单室别") @RequestParam(required = false) String sb,
            @ApiParam(value = "页码", required = true) @RequestParam(name = "page_number", defaultValue = "1") Integer pageNum
    ) {
        RestResponse<PageInfo<jcd_bookb>> response = null;
        try {
            PageHelper.startPage(pageNum, 100);
            List<jcd_bookb> jcdBookList = sysZYDanService.queryBookBZydByDefine(taskYear, taskName, taskState, sh, sm, sb);
            PageInfo<jcd_bookb> pageInfo = new PageInfo<>(jcdBookList);
            response = RestResponseUtil.success("", pageInfo);
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }


    @ApiOperation("获取所有book之BZBD相关制印单")
    @GetMapping("getBookBZydAll")
    public RestResponse<PageInfo<jcd_bookb>> getBookBZydAll(
            @ApiParam(value = "页码", required = true) @RequestParam(name = "page_number", defaultValue = "1") Integer pageNum
    ) {
        RestResponse<PageInfo<jcd_bookb>> response = null;
        try {
            PageHelper.startPage(pageNum, 100);
            List<jcd_bookb> jcdBookList = sysZYDanService.queryBookBZydAll();
            PageInfo<jcd_bookb> pageInfo = new PageInfo<>(jcdBookList);
            response = RestResponseUtil.success("", pageInfo);
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }


    @ApiOperation(value = "导入book之BZBD制印数据", notes = "导入book之BZBD制印数据")
    @PostMapping(value = "importBookBZyData", headers = "content-type=multipart/form-data")
    @Transactional(propagation = Propagation.REQUIRED)
    public RestResponse<String> importBookBZyData(
            @ApiParam("book之BZBD制印数据excel文件") @RequestParam(value = "file") MultipartFile file) {
        try {
            //1.判断文件类型
            String fileName = file.getOriginalFilename();
            if (!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx")) {
                return RestResponseUtil.note("文件格式错误！");
            } else {
                InputStream inputStream = file.getInputStream();
                List<jcd_bookb> bookJcdList = ZyImportUtil.readToJcdBookBData(inputStream, fileName);
                if (bookJcdList.size() > 0) {
                    //过滤掉已经存在的编号
                    Iterator iterator = bookJcdList.iterator();
                    while (iterator.hasNext()) {
                        jcd_bookb bookMap = (jcd_bookb) iterator.next();
                        if (sysZYDanService.isBookBJcdExist(bookMap.getBh())) {
                            iterator.remove();
                        }
                    }
                    inputStream.close();
                    if (bookJcdList.size() > 0) {
                        int count = sysZYDanService.insertBookBZydMany(bookJcdList);
                        if (count == bookJcdList.size()) {
                            return RestResponseUtil.success("批量导入成功！");
                        } else {
                            return RestResponseUtil.note("批量导入失败！");
                        }
                    } else {
                        return RestResponseUtil.note("编号都已经存在！");
                    }
                } else {
                    inputStream.close();
                    return RestResponseUtil.note("制印数据为空！");
                }
            }
        } catch (Exception e) {
            return RestResponseUtil.note("制印数据导入失败");
        }
    }



    @ApiOperation("导出book之BZBD制印单Pdf")
    @GetMapping("exportBookBZydPdfByID")
    public RestResponse<String> exportBookBZydPdfByID(
            HttpServletResponse response,
            @ApiParam(value = "book之BZBD制印单ID", required = true) @RequestParam(name = "id") String id
    ) {
        RestResponse<String> response1 = null;
        try {
            jcd_bookb jcdBook = sysZYDanService.queryBookBZydByID(id);
            String templatePath = this.getClass().getResource("/doc/zy/pdf/jcdbook.pdf").getFile();
            ZydExportUtil.exportBookBZydPdf(response, jcdBook, templatePath);
        } catch (Exception e) {
            response1 = ExceptionHandleUtil.handle(e);
        }
        return response1;
    }


    @ApiOperation("导出book之BZBD相关制印单列表")
    @GetMapping("exportBookBZydList")
    public RestResponse<String> exportBookBZydList(
            HttpServletResponse response,
            @ApiParam(value = "book之BZBD制印单任务年份") @RequestParam(required = false) Integer taskYear,
            @ApiParam(value = "book之BZBD制印单任务名称") @RequestParam(required = false) String taskName,
            @ApiParam(value = "book之BZBD制印单任务状态") @RequestParam(required = false) String taskState,
            @ApiParam(value = "book之BZBD制印单书号") @RequestParam(required = false) String sh,
            @ApiParam(value = "book之BZBD制印单书名") @RequestParam(required = false) String sm,
            @ApiParam(value = "book之BZBD制印单室别") @RequestParam(required = false) String sb
    ) {
        RestResponse<String> response1 = null;
        try {
            List<jcd_bookb> jcdBookList = sysZYDanService.queryBookBZydByDefine(taskYear, taskName, taskState, sh, sm, sb);
            ZydExportUtil.exportBookBZydListToExcel(jcdBookList, response);
        } catch (Exception e) {
            response1 = ExceptionHandleUtil.handle(e);
        }
        return response1;
    }


    @ApiOperation("发送book之BZBD制印单")
    @GetMapping("sendBookBZyd")
    public RestResponse<String> sendBookBZyd(
            @ApiParam(value = "book之BZBD制印单ID", required = true) @RequestParam(name = "id") String id
    ) {
        RestResponse<String> response1 = null;
        try {
            jcd_bookb jcdBook = sysZYDanService.queryBookBZydByID(id);
            if (!"已完成".equals(jcdBook.getTask_state())) {
                int res = sysZYDanService.updateBookBZydState(id);
                if (res > 0) {
                    response1 = RestResponseUtil.success("发送成功！");
                } else {
                    response1 = RestResponseUtil.note("发送失败！");
                }
            } else {
                response1 = RestResponseUtil.note("此任务已经完成！");
            }
        } catch (Exception e) {
            response1 = ExceptionHandleUtil.handle(e);
        }
        return response1;
    }


    /*******************************UVPH相关制印单***************************/

    @ApiOperation("添加UVPH相关制印单")
    @PostMapping("addUvphZyd")
    public RestResponse<String> addUvphZyd(
            @ApiParam(value = "Uvph制印单对象", required = true) @RequestBody jcd_uvph uvphjcd
    ) {
        RestResponse<String> response = null;
        try {
            if (uvphjcd != null) {
                uvphjcd.setId(UUIDGeneratorUtil.getUUID());
                uvphjcd.setTask_index_id(UUIDGeneratorUtil.getUUID());
                //判断编号是否存在，编号不能重复
                boolean res = sysZYDanService.isUvphJcdExist(uvphjcd.getBh());
                if (!res) {
                    int count = sysZYDanService.insertUvphZyd(uvphjcd);
                    if (count > 0) {
                        response = RestResponseUtil.success("添加成功！");
                    } else {
                        response = RestResponseUtil.note("添加失败！");
                    }
                } else {
                    response = RestResponseUtil.note("编号已存在！");
                }
            }
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }


    @ApiOperation("更新UVPH相关制印单")
    @PostMapping("updateUvphZyd")
    public RestResponse<String> updateUvphZyd(
            @ApiParam(value = "UVPH制印单对象", required = true) @RequestBody jcd_uvph uvphjcd
    ) {
        RestResponse<String> response = null;
        try {
            if (uvphjcd != null) {
                //更新之前先查一下新的编号是否存在
                jcd_uvph jcdUvph = sysZYDanService.queryUvphZydByBh(uvphjcd.getBh());
                if (jcdUvph != null && !jcdUvph.getId().equals(uvphjcd.getId())) {
                    response = RestResponseUtil.note("编号已存在！");
                } else {
                    int res = sysZYDanService.updateUvphZyd(uvphjcd);
                    if (res > 0) {
                        response = RestResponseUtil.success("更新成功！");
                    } else {
                        response = RestResponseUtil.note("更新失败！");
                    }
                }
            }
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }


    @ApiOperation("根据ID删除UVPH相关制印单")
    @GetMapping("deleteUvphZydByID")
    public RestResponse<String> deleteUvphZydByID(
            @ApiParam(value = "UVPH制印单ID", required = true) @RequestParam(name = "id") String id
    ) {
        RestResponse<String> response = null;
        try {
            int res = sysZYDanService.deleteUvphZyd(id);
            if (res > 0) {
                response = RestResponseUtil.success("删除成功！");
            } else {
                response = RestResponseUtil.note("删除失败！");
            }
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }


    @ApiOperation("根据ID获取UVPH相关制印单")
    @GetMapping("getUvphZydByID")
    public RestResponse<jcd_uvph> getUvphZydByID(
            @ApiParam(value = "UVPH制印单ID", required = true) @RequestParam(name = "id") String id
    ) {
        RestResponse<jcd_uvph> response = null;
        try {
            jcd_uvph uvphjcd = sysZYDanService.queryUvphZydByID(id);
            response = RestResponseUtil.success("", uvphjcd);
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }


    @ApiOperation("自定义条件获取UVPH相关制印单")
    @GetMapping("getUvphZydByDefine")
    public RestResponse<PageInfo<jcd_uvph>> getUvphZydByDefine(
            @ApiParam(value = "UVPH制印单任务年份") @RequestParam(required = false) Integer taskYear,
            @ApiParam(value = "UVPH制印单任务名称") @RequestParam(required = false) String taskName,
            @ApiParam(value = "UVPH制印单任务状态") @RequestParam(required = false) String taskState,
            @ApiParam(value = "UVPH制印单图号") @RequestParam(required = false) String mapCode,
            @ApiParam(value = "UVPH制印单图名") @RequestParam(required = false) String mapName,
            @ApiParam(value = "UVPH制印单室别") @RequestParam(required = false) String sb,
            @ApiParam(value = "页码", required = true) @RequestParam(name = "page_number", defaultValue = "1") Integer pageNum
    ) {
        RestResponse<PageInfo<jcd_uvph>> response = null;
        try {
            PageHelper.startPage(pageNum, 100);
            List<jcd_uvph> uvphList = sysZYDanService.queryUvphZydByDefine(taskYear, taskName, taskState, mapCode, mapName, sb);
            PageInfo<jcd_uvph> pageInfo = new PageInfo<>(uvphList);
            response = RestResponseUtil.success("", pageInfo);
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }


    @ApiOperation("获取所有UVPH相关制印单")
    @GetMapping("getUvphZydAll")
    public RestResponse<PageInfo<jcd_uvph>> getUvphZydAll(
            @ApiParam(value = "页码", required = true) @RequestParam(name = "page_number", defaultValue = "1") Integer pageNum
    ) {
        RestResponse<PageInfo<jcd_uvph>> response = null;
        try {
            PageHelper.startPage(pageNum, 100);
            List<jcd_uvph> uvphList = sysZYDanService.queryUvphZydAll();
            PageInfo<jcd_uvph> pageInfo = new PageInfo<>(uvphList);
            response = RestResponseUtil.success("", pageInfo);
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }


    @ApiOperation(value = "导入UVPH制印数据", notes = "导入UVPH制印数据")
    @PostMapping(value = "importUvphZyData", headers = "content-type=multipart/form-data")
    @Transactional(propagation = Propagation.REQUIRED)
    public RestResponse<String> importUvphZyData(
            @ApiParam("UVPH制印数据excel文件") @RequestParam(value = "file") MultipartFile file) {
        try {
            //1.判断文件类型
            String fileName = file.getOriginalFilename();
            if (!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx")) {
                return RestResponseUtil.note("文件格式错误！");
            } else {
                InputStream inputStream = file.getInputStream();
                List<jcd_uvph> uvphJcdList = ZyImportUtil.readToJcdUvphData(inputStream, fileName);
                if (uvphJcdList.size() > 0) {
                    //过滤掉已经存在的编号
                    Iterator iterator = uvphJcdList.iterator();
                    while (iterator.hasNext()) {
                        jcd_uvph jcdUvph = (jcd_uvph) iterator.next();
                        if (sysZYDanService.isUvphJcdExist(jcdUvph.getBh())) {
                            iterator.remove();
                        }
                    }
                    inputStream.close();
                    if (uvphJcdList.size() > 0) {
                        int count = sysZYDanService.insertUvphZydMany(uvphJcdList);
                        if (count == uvphJcdList.size()) {
                            return RestResponseUtil.success("批量导入成功！");
                        } else {
                            return RestResponseUtil.note("批量导入失败！");
                        }
                    } else {
                        return RestResponseUtil.note("编号都已经存在！");
                    }
                } else {
                    return RestResponseUtil.note("制印数据为空！");
                }
            }
        } catch (Exception e) {
            return RestResponseUtil.note("制印数据导入失败");
        }
    }



    @ApiOperation("导出UVPH制印单Pdf")
    @GetMapping("exportUvphZydPdfByID")
    public RestResponse<String> exportUvphZydPdfByID(
            HttpServletResponse response,
            @ApiParam(value = "UVPH制印单ID", required = true) @RequestParam(name = "id") String id
    ) {
        RestResponse<String> response1 = null;
        try {
            jcd_uvph uvphjcd = sysZYDanService.queryUvphZydByID(id);
            String templatePath = this.getClass().getResource("/doc/zy/pdf/jcduvph.pdf").getFile();
            ZydExportUtil.exportUvphZydPdf(response, uvphjcd, templatePath);
        } catch (Exception e) {
            response1 = ExceptionHandleUtil.handle(e);
        }
        return response1;
    }


    @ApiOperation("导出UVPH相关制印单列表")
    @GetMapping("exportUvphZydList")
    public RestResponse<String> exportUvphZydList(
            HttpServletResponse response,
            @ApiParam(value = "UVPH制印单任务年份") @RequestParam(required = false) Integer taskYear,
            @ApiParam(value = "UVPH制印单任务名称") @RequestParam(required = false) String taskName,
            @ApiParam(value = "UVPH制印单任务状态") @RequestParam(required = false) String taskState,
            @ApiParam(value = "UVPH制印单图号") @RequestParam(required = false) String mapCode,
            @ApiParam(value = "UVPH制印单图名") @RequestParam(required = false) String mapName,
            @ApiParam(value = "UVPH制印单室别") @RequestParam(required = false) String sb
    ) {
        RestResponse<String> response1 = null;
        try {
            List<jcd_uvph> uvphList = sysZYDanService.queryUvphZydByDefine(taskYear, taskName, taskState, mapCode, mapName, sb);
            ZydExportUtil.exportUvphZydListToExcel(uvphList, response);
        } catch (Exception e) {
            response1 = ExceptionHandleUtil.handle(e);
        }
        return response1;
    }


    @ApiOperation("发送UVPH制印单")
    @GetMapping("sendUvphZyd")
    public RestResponse<String> sendUvphZyd(
            @ApiParam(value = "UVPH制印单ID", required = true) @RequestParam(name = "id") String id
    ) {
        RestResponse<String> response1 = null;
        try {
            jcd_uvph jcdUvph = sysZYDanService.queryUvphZydByID(id);
            if (!"已完成".equals(jcdUvph.getTask_state())) {
                int res = sysZYDanService.updateUvphZydState(id);
                if (res > 0) {
                    response1 = RestResponseUtil.success("发送成功！");
                } else {
                    response1 = RestResponseUtil.note("发送失败！");
                }
            } else {
                response1 = RestResponseUtil.note("此任务已经完成！");
            }
        } catch (Exception e) {
            response1 = ExceptionHandleUtil.handle(e);
        }
        return response1;
    }
}
