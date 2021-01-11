package com.startest.wm.controller;

import com.startest.wm.pojo.sys_station;
import com.startest.wm.service.SysStationService;
import com.startest.wm.utils.UUIDGeneratorUtil;
import com.startest.wm.utils.customresponse.RestResponse;
import com.startest.wm.utils.customresponse.RestResponseCode;
import com.startest.wm.utils.customresponse.RestResponseUtil;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-04-02 10:44
 * @描述 系统岗位操作控制器
 **/
@Api(tags = "系统岗位操作模块相关API")
@RestController
@RequestMapping(value = "/sys/station/")
public class SysStationController {

    @Autowired
    SysStationService sysStationService;

    private static final Logger log = LoggerFactory.getLogger(SysDeptController.class);


    @ApiOperation(value = "添加岗位", notes = "添加岗位")
    @PostMapping(value = "addStation")
    public RestResponse<String> addStation(
            @ApiParam(value = "岗位名称") @RequestParam(name = "station_name") String stationName,
            @ApiParam(value = "岗位顺序") @RequestParam(name = "station_order") Integer stationOrder,
            @ApiParam(value = "岗位描述") @RequestParam(name = "description", required = false) String description) {
        RestResponse<String> response = null;
        try {
            sys_station sysStation = new sys_station();
            sysStation.setStation_id(UUIDGeneratorUtil.getUUIDWithoutLineAndToLower());
            sysStation.setStation_name(stationName);
            sysStation.setStation_order(stationOrder);
            sysStation.setDescription(description);
            //判断此岗位名称的岗位是否已经存在
            boolean isStationExist = sysStationService.isStationExist(sysStation.getStation_id(), sysStation.getStation_name());
            if (isStationExist) {
                response = RestResponseUtil.note("此岗位已经存在！");
            } else {
                int res = sysStationService.addStation(sysStation);
                if (res > 0) {
                    response = RestResponseUtil.success("添加岗位成功！");
                } else {
                    response = RestResponseUtil.note("添加岗位失败！");
                }
            }
        } catch (Exception e) {
            log.error("添加岗位失败！原因：", e);
            response = RestResponseUtil.note("添加失败！");
        }
        return response;
    }


    @ApiOperation(value = "根据岗位ID删除岗位", notes = "根据岗位ID删除岗位")
    @PostMapping(value = "deleteStationByID")
    public RestResponse<String> deleteStationByID(
            @ApiParam(value = "岗位ID") @RequestParam(name = "station_id") String stationID) {
        RestResponse<String> response = null;
        try {
            //判断岗位下是否存在人员，存在人员则不让删除，防止导致把其他部门此岗位下人员也给删了
            boolean isStationExistUser = sysStationService.isStationExistUsers(stationID);
            if (isStationExistUser) {
                response = RestResponseUtil.note("此岗位下存在人员，无法删除！");
            } else {
                int res = sysStationService.deleteStationByID(stationID);
                if (res > 0) {
                    response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "删除成功！");
                } else {
                    response = RestResponseUtil.note("删除失败！");
                }
            }
        } catch (Exception e) {
            log.error("删除岗位失败！原因：", e);
            response = RestResponseUtil.note("删除失败！");
        }
        return response;
    }


    @ApiOperation("根据岗位ID获取岗位信息")
    @GetMapping(value = "getStationInfoByID")
    public RestResponse<sys_station> getStationInfoByID(
            @ApiParam(value = "岗位ID") @RequestParam(name = "station_id") String stationID) {
        RestResponse<sys_station> response = null;
        try {
            sys_station sysStation = sysStationService.getStationByID(stationID);
            if (sysStation != null) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, sysStation);
            } else {
                response = RestResponseUtil.note("此岗位不存在！");
            }
        } catch (Exception e) {
            log.error("系统异常！原因：", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation("根据岗位名称获取岗位信息")
    @GetMapping(value = "getStationInfoByName")
    public RestResponse<List<sys_station>> getStationInfoByName(
            @ApiParam(value = "岗位名称") @RequestParam(name = "station_name") String stationName) {
        RestResponse<List<sys_station>> response = null;
        try {
            List<sys_station> sysStationList = sysStationService.getStationByName(stationName);
            if (sysStationList != null) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, sysStationList);
            } else {
                response = RestResponseUtil.note("此岗位不存在！");
            }
        } catch (Exception e) {
            log.error("系统异常！原因：", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation("获取所有岗位列表")
    @GetMapping(value = "getStationList")
    public RestResponse<List<sys_station>> getStationList() {
        RestResponse<List<sys_station>> response = null;
        try {
            List<sys_station> sysStationList = sysStationService.getAllStation();
            response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, sysStationList);
        } catch (Exception e) {
            log.error("系统异常！原因：", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "修改岗位", notes = "修改岗位")
    @PostMapping(value = "updateStation")
    public RestResponse<String> updateStation(@ApiParam(value = "岗位对象") @RequestBody sys_station sysStation) {
        RestResponse<String> response = null;
        try {
            sys_station sysStation1 = sysStationService.getStationByID(sysStation.getStation_id());
            if (sysStation1 != null) {
                if (sysStation.getStation_name() == null || "".equals(sysStation.getStation_name())) {
                    response = RestResponseUtil.note("修改失败,岗位名称不能为空！");
                } else {
                    //判断此岗位名称的岗位是否已经存在
                    boolean isStationExist = sysStationService.isStationExist(sysStation.getStation_id(), sysStation.getStation_name());
                    if (isStationExist) {
                        response = RestResponseUtil.note("此岗位已经存在！");
                    } else {
                        int res = sysStationService.updateStation(sysStation);
                        if (res > 0) {
                            response = RestResponseUtil.success("修改成功！");
                        } else {
                            response = RestResponseUtil.note("修改岗位失败！");
                        }
                    }
                }
            } else {
                response = RestResponseUtil.note("修改失败,此岗位不存在！");
            }
        } catch (Exception e) {
            log.error("修改岗位失败！原因：", e);
            response = RestResponseUtil.note("修改失败！");
        }
        return response;
    }
}
