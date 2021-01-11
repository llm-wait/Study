package com.startest.wm.controller;

import com.startest.wm.pojo.sys_score_setting;
import com.startest.wm.service.SysScoreSettingService;
import com.startest.wm.utils.UUIDGeneratorUtil;
import com.startest.wm.utils.customresponse.ExceptionHandleUtil;
import com.startest.wm.utils.customresponse.RestResponse;
import com.startest.wm.utils.customresponse.RestResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-04-02 10:43
 * @描述 系统成绩设置控制器
 **/
@Api(tags = "考评成绩配置相关API")
@RestController
@RequestMapping(value = "/sys/setscore/")
public class SysScoreSettingController {

    @Autowired
    SysScoreSettingService sysScoreSettingService;

    @ApiOperation("保存考评成绩设置参数")
    @PostMapping("saveScoreSetting")
    public RestResponse<String> saveScoreSetting(
            @ApiParam(value = "年份", required = true) @RequestParam(name = "year") Integer year,
            @ApiParam(value = "合格率", required = true) @RequestParam(name = "hgl") Double hgl,
            @ApiParam(value = "优秀率", required = true) @RequestParam(name = "yxl") Double yxl,
            @ApiParam(value = "优秀工天比", required = true) @RequestParam(name = "yxgtb") Double yxgtb,
            @ApiParam(value = "改成图主", required = true) @RequestParam(name = "gctz") Double gctz,
            @ApiParam(value = "改成图次", required = true) @RequestParam(name = "gctc") Double gctc,
            @ApiParam(value = "技术修改主", required = true) @RequestParam(name = "jsxgz") Double jsxgz,
            @ApiParam(value = "技术修改次", required = true) @RequestParam(name = "jsxgc") Double jsxgc,
            @ApiParam(value = "业务工天", required = true) @RequestParam(name = "ywgt") Double ywgt,
            @ApiParam(value = "工天比", required = true) @RequestParam(name = "gtb") Double gtb,
            @ApiParam(value = "业务能力", required = true) @RequestParam(name = "ywnl") Double ywnl,
            @ApiParam(value = "工作态度", required = true) @RequestParam(name = "gztd") Double gztd,
            @ApiParam(value = "参训出勤率", required = true) @RequestParam(name = "cxcql") Double cxcql,
            @ApiParam(value = "训练成绩", required = true) @RequestParam(name = "xlcj") Double xlcj,
            @ApiParam(value = "比武成绩", required = true) @RequestParam(name = "bwcj") Double bwcj
    ) {
        try {
            if (year == Calendar.getInstance().get(Calendar.YEAR)) {
                //判断配置项总分值是否为100
                double totalValue = hgl + yxl + yxgtb + ywgt + gtb + ywnl + gztd + cxcql + xlcj + bwcj;
                if (totalValue != 100.00) {
                    return RestResponseUtil.note("所有分值总和不是100！");
                }
                //如果当前年份成绩配置已存在则更新，否则新增
                sys_score_setting sysScoreSetting = sysScoreSettingService.queryScoreSettingByYear(year);
                if (sysScoreSetting != null) {
                    sysScoreSetting.setHgl(hgl);
                    sysScoreSetting.setYxl(yxl);
                    sysScoreSetting.setYxgtb(yxgtb);
                    sysScoreSetting.setGctz(gctz);
                    sysScoreSetting.setGctc(gctc);
                    sysScoreSetting.setJsxgz(jsxgz);
                    sysScoreSetting.setJsxgc(jsxgc);
                    sysScoreSetting.setYwgt(ywgt);
                    sysScoreSetting.setGtb(gtb);
                    sysScoreSetting.setYwnl(ywnl);
                    sysScoreSetting.setGztd(gztd);
                    sysScoreSetting.setCxcql(cxcql);
                    sysScoreSetting.setXlcj(xlcj);
                    sysScoreSetting.setBwcj(bwcj);
                    int res = sysScoreSettingService.updateScoreSetting(sysScoreSetting);
                    if (res > 0) {
                        return RestResponseUtil.success("保存成功！");
                    } else {
                        return RestResponseUtil.note("保存失败！");
                    }
                } else {
                    sysScoreSetting = new sys_score_setting();
                    sysScoreSetting.setId(UUIDGeneratorUtil.getUUID());
                    sysScoreSetting.setYear(year);
                    sysScoreSetting.setHgl(hgl);
                    sysScoreSetting.setYxl(yxl);
                    sysScoreSetting.setYxgtb(yxgtb);
                    sysScoreSetting.setGctz(gctz);
                    sysScoreSetting.setGctc(gctc);
                    sysScoreSetting.setJsxgz(jsxgz);
                    sysScoreSetting.setJsxgc(jsxgc);
                    sysScoreSetting.setYwgt(ywgt);
                    sysScoreSetting.setGtb(gtb);
                    sysScoreSetting.setYwnl(ywnl);
                    sysScoreSetting.setGztd(gztd);
                    sysScoreSetting.setCxcql(cxcql);
                    sysScoreSetting.setXlcj(xlcj);
                    sysScoreSetting.setBwcj(bwcj);
                    int res = sysScoreSettingService.insertScoreSetting(sysScoreSetting);
                    if (res > 0) {
                        return RestResponseUtil.success("保存成功！");
                    } else {
                        return RestResponseUtil.note("保存失败！");
                    }
                }
            } else {
                return RestResponseUtil.note("非当前年份不可修改！");
            }
        } catch (Exception e) {
            return ExceptionHandleUtil.handle(e);
        }
    }


    @ApiOperation("根据年份查询考评成绩配置参数")
    @GetMapping("getByYear")
    public RestResponse<Object> getSettingByYear(
            @ApiParam(value = "年份", required = true) @RequestParam(name = "year") Integer year
    ) {
        try {
            sys_score_setting sysScoreSetting = sysScoreSettingService.queryScoreSettingByYear(year);
            return RestResponseUtil.success("", sysScoreSetting);
        } catch (Exception e) {
            return ExceptionHandleUtil.handle(e);
        }
    }

    /*@ApiOperation("根据ID查询考评成绩设置参数")
    @GetMapping("get/{id}")
    public RestResponse<Object> getSettingByID(
            @ApiParam(value = "唯一ID", required = true) @RequestParam(name = "id") String id
    ) {
        try {
            sys_score_setting sysScoreSetting = sysScoreSettingService.queryScoreSettingByID(id);
            return RestResponseUtil.success("", sysScoreSetting);
        } catch (Exception e) {
            return ExceptionHandleUtil.handle(e);
        }
    }*/

    /*@ApiOperation("获取所有考评成绩设置参数")
    @GetMapping("listAll")
    public RestResponse<List<sys_score_setting>> listSettingAll() {
        try {
            List<sys_score_setting> list = sysScoreSettingService.queryAllScoreSetting();
            return RestResponseUtil.success("", list);
        } catch (Exception e) {
            return ExceptionHandleUtil.handle(e);
        }
    }*/
}
