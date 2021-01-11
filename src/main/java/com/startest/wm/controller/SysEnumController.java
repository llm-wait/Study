package com.startest.wm.controller;

import com.startest.wm.enums.*;
import com.startest.wm.enums.task.EnumTaskProductType;
import com.startest.wm.enums.task.EnumTaskState;
import com.startest.wm.enums.task.EnumTaskType;
import com.startest.wm.pojo.sys_dept;
import com.startest.wm.service.SysDeptService;
import com.startest.wm.service.SysEnumService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-05-13 9:49
 * @描述 系统相关枚举控制器
 **/
@Api(tags = "系统枚举相关控制器")
@RestController
@RequestMapping(value = "/sys/enumconfig/")
public class SysEnumController {

    @Autowired
    SysEnumService sysEnumService;

    @Autowired
    SysDeptService sysDeptService;

    /******************************************海图港口资料相关枚举***********************************************/

    @ApiOperation(value = "获取海图资料类型枚举列表")
    @GetMapping("getMapDataType")
    public List<String> getMapDataType() {
        List<String> list = new ArrayList<>();
        for (EnumMapDataType enumMapDataType : EnumMapDataType.values()) {
            list.add(enumMapDataType.getMapType());
        }
        return list;
    }

    @ApiOperation(value = "获取海图出版单位枚举列表")
    @GetMapping("getMapPublishUnit")
    public List<String> getMapPublishUnit() {
        List<String> list = sysEnumService.queryMapPublishUnit();
        return list;
    }

    @ApiOperation(value = "获取海图港口资料状态枚举列表")
    @GetMapping("getMapDataState")
    public List<String> getMapDataState() {
        List<String> list = new ArrayList<>();
        for (EnumMapDataState enumMapDataState : EnumMapDataState.values()) {
            list.add(enumMapDataState.getMapDataState());
        }
        return list;
    }

    @ApiOperation(value = "获取军用海图比例尺枚举列表")
    @GetMapping("getJyMapScale")
    public List<String> getJyMapScale() {
        List<String> list = new ArrayList<>();
        for (EnumJyMapScale enumJyMapScale : EnumJyMapScale.values()) {
            list.add(enumJyMapScale.getJyMapScale());
        }
        return list;
    }

    @ApiOperation(value = "获取民用海图比例尺枚举列表")
    @GetMapping("getMyMapScale")
    public List<String> getMyMapScale() {
        List<String> list = new ArrayList<>();
        for (EnumMyMapScale enumMyMapScale : EnumMyMapScale.values()) {
            list.add(enumMyMapScale.getMyMapScale());
        }
        return list;
    }

    @ApiOperation(value = "获取港口资料类型枚举列表")
    @GetMapping("getPortDataType")
    public List<String> getPortDataType() {
        List<String> list = new ArrayList<>();
        for (EnumPortDataType enumPortDataType : EnumPortDataType.values()) {
            list.add(enumPortDataType.getPortDataType());
        }
        return list;
    }

    @ApiOperation(value = "获取港口资料状态枚举列表")
    @GetMapping("getPortDataState")
    public List<String> getPortDataState() {
        List<String> list = new ArrayList<>();
        for (EnumPortDataState enumPortDataState : EnumPortDataState.values()) {
            list.add(enumPortDataState.getPortDataState());
        }
        return list;
    }

    @ApiOperation(value = "获取资料所属大洲枚举列表")
    @GetMapping("getDataContient")
    public List<String> getDataContient() {
        List<String> list = sysEnumService.queryPortContient();
        return list;
    }

    @ApiOperation(value = "获取资料所属国家枚举列表")
    @GetMapping("getDataCountry")
    public List<String> getDataCountry() {
        List<String> list = sysEnumService.queryPortCountry();
        return list;
    }

    @ApiOperation(value = "获取资料所属海区枚举列表")
    @GetMapping("getDataSea")
    public List<String> getDataSea() {
        List<String> list = sysEnumService.queryPortSea();
        return list;
    }

    @ApiOperation(value = "获取港口资料来源列表")
    @GetMapping("getDataSource")
    public List<String> getDataSource() {
        List<String> list = sysEnumService.queryPortDataSource();
        return list;
    }

    /******************************************登录信息相关枚举***********************************************/
    @ApiOperation(value = "获取登录角色枚举列表")
    @GetMapping("getLoginRole")
    public List<String> getLoginRole() {
        List<String> list = new ArrayList<>();
        for (EnumLoginRole enumLoginRole : EnumLoginRole.values()) {
            list.add(enumLoginRole.getName());
        }
        return list;
    }


    /******************************************用户信息相关枚举***********************************************/

    @ApiOperation(value = "获取用户技术职务枚举列表")
    @GetMapping("getUserJszw")
    public List<String> getUserJszw() {
        List<String> list = new ArrayList<>();
        for (EnumUserJszw enumUserJszw : EnumUserJszw.values()) {
            list.add(enumUserJszw.getUserJszw());
        }
        return list;
    }

    @ApiOperation(value = "获取用户J衔枚举列表")
    @GetMapping("getUserJx")
    public List<String> getUserJx() {
        List<String> list = new ArrayList<>();
        for (EnumUserJX enumUserJX : EnumUserJX.values()) {
            list.add(enumUserJX.getUserJX());
        }
        return list;
    }

    @ApiOperation(value = "获取用户人员类别枚举列表")
    @GetMapping("getUserRylb")
    public List<String> getUserRylb() {
        List<String> list = new ArrayList<>();
        for (EnumUserRylb enumUserRylb : EnumUserRylb.values()) {
            list.add(enumUserRylb.getUserRylb());
        }
        return list;
    }

    /**生产任务——表单详情——职务职称
     * @return 职务列表
     */
    @ApiOperation(value = "获取用户职称枚举列表")
    @GetMapping("getUserZc")
    public List<String> getUserZc() {
        List<String> list = new ArrayList<>();
        for (EnumUserZc enumUserZc : EnumUserZc.values()) {
            list.add(enumUserZc.getUserZc());
        }
        return list;
    }

    @ApiOperation(value = "获取用户婚姻状况枚举列表")
    @GetMapping("getUserSfhp")
    public List<String> getUserSfhp() {
        List<String> list = new ArrayList<>();
        for (EnumUserSfhp enumUserSfhp : EnumUserSfhp.values()) {
            list.add(enumUserSfhp.getUserSfhp());
        }
        return list;
    }

    @ApiOperation(value = "获取用户所在单位枚举列表")
    @GetMapping("getUserSzdw")
    public List<sys_dept> getUserSzdw() {
        List<sys_dept> listZys = sysDeptService.getAllZuoyeshiList(true);
        return listZys;
    }

    @ApiOperation(value = "获取用户学历枚举列表")
    @GetMapping("getUserXl")
    public List<String> getUserXl() {
        List<String> list = new ArrayList<>();
        for (EnumUserXl enumUserXl : EnumUserXl.values()) {
            list.add(enumUserXl.getUserXl());
        }
        return list;
    }

    @ApiOperation(value = "获取用户政治面貌枚举列表")
    @GetMapping("getUserZzmm")
    public List<String> getUserZzmm() {
        List<String> list = new ArrayList<>();
        for (EnumUserZzmm enumUserZzmm : EnumUserZzmm.values()) {
            list.add(enumUserZzmm.getUserZzmm());
        }
        return list;
    }

    /******************************************制印通知单相关枚举***********************************************/

    /*@ApiOperation(value = "获取制印横竖幅枚举列表")
    @GetMapping("getZyHSF")
    public List<String> getZyHSF() {
        List<String> list = new ArrayList<>();
        for (EnumZyHSF enumZyHSF : EnumZyHSF.values()) {
            list.add(enumZyHSF.getHsf());
        }
        return list;
    }*/

    /*@ApiOperation(value = "获取制印成图尺寸枚举列表")
    @GetMapping("getZyCTCC")
    public List<String> getZyCTCC() {
        List<String> list = new ArrayList<>();
        for (EnumZyCTCC enumZyCTCC : EnumZyCTCC.values()) {
            list.add(enumZyCTCC.getCtcc());
        }
        return list;
    }*/

    /*@ApiOperation(value = "获取制印彩色样枚举列表")
    @GetMapping("getZyCSY")
    public List<String> getZyCSY() {
        List<String> list = new ArrayList<>();
        for (EnumZyCSY enumZyCSY : EnumZyCSY.values()) {
            list.add(String.valueOf(enumZyCSY.getCsy()));
        }
        return list;
    }*/

    /*@ApiOperation(value = "获取制印成开本枚举列表")
    @GetMapping("getZyKB")
    public List<String> getZyKB() {
        List<String> list = new ArrayList<>();
        for (EnumZyKB enumZyKB : EnumZyKB.values()) {
            list.add(enumZyKB.getKb());
        }
        return list;
    }*/

    @ApiOperation(value = "获取制印密级枚举列表")
    @GetMapping("getZyMJ")
    public List<String> getZyMJ() {
        List<String> list = new ArrayList<>();
        for (EnumZyMJ enumZyMJ : EnumZyMJ.values()) {
            list.add(enumZyMJ.getMj());
        }
        return list;
    }

    /*@ApiOperation(value = "获取制印是否覆膜枚举列表")
    @GetMapping("getZySFFM")
    public List<String> getZySFFM() {
        List<String> list = new ArrayList<>();
        for (EnumZySFFM enumZySFFM : EnumZySFFM.values()) {
            list.add(enumZySFFM.getSffm());
        }
        return list;
    }*/

    /*@ApiOperation(value = "获取制印印色枚举列表")
    @GetMapping("getZyYS")
    public List<String> getZyYS() {
        List<String> list = new ArrayList<>();
        for (EnumZyYS enumZyYS : EnumZyYS.values()) {
            list.add(enumZyYS.getYs());
        }
        return list;
    }*/

    /*@ApiOperation(value = "获取制印通知单表头枚举列表")
    @GetMapping("getZyFormHeaderType")
    public List<String> getZyFormHeaderType() {
        List<String> list = new ArrayList<>();
        for (EnumZyFormHeaderType enumZyFormHeaderType : EnumZyFormHeaderType.values()) {
            list.add(enumZyFormHeaderType.getFormHeaderType());
        }
        return list;
    }*/

//    /******************************************任务相关枚举***********************************************/

    /**任务监控——筹划任务分类——获取任务状态列表
     * 作业管理——生产任务——作业状态查询
     * 任务质量评估　　todo　公共接口
     * @param userType
     * @return
     */
    @ApiOperation(value = "任务状态枚举列表")
    @GetMapping("getTaskState")
    public List<String> getTaskState(@ApiParam("使用环境：0：监控；1：作业室") @RequestParam(name = "use_type", required = true) Integer userType) {
        List<String> list = new ArrayList<>();
        for (EnumTaskState state : EnumTaskState.values()) {
            String strValue = state.getTaskStateString();
            if (userType == 1 && EnumTaskState.JHXD.getTaskStateString().equals(strValue)) {
            } else {
                list.add(strValue);
            }
        }
        return list;
    }

    /**生产任务－获取任务类型
     * @return 任务类型列表
     */
    @ApiOperation(value = "任务类型枚举列表")
    @GetMapping("getTaskType")
    public List<String> getTaskType() {
        List<String> list = new ArrayList<>();
        for (EnumTaskType state : EnumTaskType.values()) {
            list.add(state.getTaskTypeString());
        }
        return list;
    }

    @ApiOperation(value = "获取海图产品分类")
    @GetMapping("getChartProductType")
    public List<String> getChartProductType() {
        List<String> list = new ArrayList<>();
        for (EnumTaskProductType type : EnumTaskProductType.values()) {
            list.add(type.getTaskProductType());
        }
        return list;
    }

    @ApiOperation(value = "获取书表产品分类")
    @GetMapping("getBookProductType")
    public List<String> getBookProductType() {
        List<String> list = new ArrayList<>();
        for (EnumTaskBookProductType type : EnumTaskBookProductType.values()) {
            list.add(type.getTaskProductType());
        }
        return list;
    }

    @ApiOperation(value = "获取海图任务职务分类")
    @GetMapping("getChartTaskType")
    public List<String> getChartTaskType() {
        List<String> list = new ArrayList<>();
        for (EnumTaskChartDistributionType type : EnumTaskChartDistributionType.values()) {
            list.add(type.getTaskChartDistributionType());
        }
        return list;
    }

    /**生产任务——表单详情——编辑书表
     * @return
     */
    @ApiOperation(value = "获取书表任务职务分类")
    @GetMapping("getBookTaskType")
    public List<String> getBookTaskType() {
        List<String> list = new ArrayList<>();
        for (EnumTaskBookDistributionType type : EnumTaskBookDistributionType.values()) {
            list.add(type.getTaskBookDistributionType());
        }
        return list;
    }

    @ApiOperation(value = "获取项目状态")
    @GetMapping("getProjectState")
    public List<String> getProjectState() {
        List<String> list = new ArrayList<>();
        for (EnumProjectState state : EnumProjectState.values()) {
            list.add(state.getProjectStateString());
        }
        return list;
    }

    /******************************************科研相关枚举***********************************************/
    /*@ApiOperation(value = "科研项目类型枚举")
    @GetMapping("getKyProjectType")
    public List<String> getKyProjectType() {
        List<String> list = new ArrayList<>();
        list.add("专项科研");
        list.add("军内科研");
        return list;
    }*/

    @ApiOperation(value = "科研项目年份枚举")
    @GetMapping("getKyProjectYear")
    public List<Integer> getKyProjectYear() {
        List<Integer> list = new ArrayList<>();
        Integer currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (; currentYear >= 2020; currentYear--) {
            list.add(currentYear);
        }
        return list;
    }

    @ApiOperation(value = "科研项目状态枚举")
    @GetMapping("getKyProjectState")
    public List<String> getKyProjectState() {
        List<String> list = new ArrayList<>();
        list.add("待处理");
        list.add("进行中");
        list.add("已完成");
        return list;
    }

    @ApiOperation(value = "科研项目周期枚举")
    @GetMapping("getKyProjectCycle")
    public List<String> getKyProjectCycle() {
        List<String> list = new ArrayList<>();
        list.add("小于一年");
        list.add("大于一年小于三年");
        list.add("大于三年");
        return list;
    }
}
