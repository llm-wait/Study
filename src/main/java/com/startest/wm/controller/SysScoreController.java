package com.startest.wm.controller;

import com.startest.wm.enums.EnumUserRylb;
import com.startest.wm.model.SysOrganizationTree;
import com.startest.wm.model.score.*;
import com.startest.wm.pojo.sys_jghzgt;
import com.startest.wm.pojo.sys_user_finalscore;
import com.startest.wm.pojo.sys_user_score;
import com.startest.wm.service.*;
import com.startest.wm.utils.ScoreExcelOperationUtil;
import com.startest.wm.utils.UUIDGeneratorUtil;
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

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.*;

/*
 * 考评模块设计思路：
 * 1、作业室主任只对当前年份的成绩进行录入，不提供历年成绩查询，历年成绩查询统一在业务处那里查看
 * 2、作业室主任考评分析只对当前年份的成绩进行分析，不提供对历年成绩进行分析
 * 3、作业室主任那只有考评成绩存档后才能进行分析
 * 4、业务处提供对各科室历年成绩的查询和分析
 * */

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-04-02 10:43
 * @描述 用户成绩控制器
 **/
@Api(tags = "考评成绩相关API")
@RestController
@RequestMapping(value = "/sys/score/")
public class SysScoreController {

    @Autowired
    SysScoreService sysScoreService;
    @Autowired
    SysFinalScoreService sysFinalScoreService;
    @Autowired
    SysJghzgtService sysJghzgtService;
    @Autowired
    SysDeptService sysDeptService;
    @Autowired
    SysUserService sysUserService;
    @Autowired
    TaskChartCalculationHelperService calculationHelperService;

    private static final Logger log = LoggerFactory.getLogger(SysScoreController.class);


    /*********************************************作业室主任公共接口*******************************************/

    @ApiOperation("作业室获取组别列表")
    @GetMapping("getZysZb")
    public RestResponse<List<String>> getZysZbList(
            @ApiParam(value = "部门ID", required = true) @RequestParam(name = "deptID") String deptID) {
        /*
         * 说明：
         * 1、作业室主任从成绩最终表中获取组别列表
         * */
        try {
            Integer year = Calendar.getInstance().get(Calendar.YEAR);
            List<String> zbList = sysFinalScoreService.getZysZbList(year, deptID);
            zbList.add(0, "全体人员");
            return RestResponseUtil.success("", zbList);
        } catch (Exception e) {
            log.error("作业室获取组别列表异常：", e);
            return RestResponseUtil.note("系统异常！");
        }
    }


    @ApiOperation("作业室获取人员类别列表")
    @GetMapping("getZysRylb")
    public RestResponse<List<String>> getZysRylbList() {
        /*
         * 说明：
         * 1、作业室主任从人员类别枚举中获取人员类别列表
         * */
        try {
            List<String> zbList = new ArrayList<>();
            zbList.add("全体人员");
            for (EnumUserRylb rylb : EnumUserRylb.values()) {
                zbList.add(rylb.getUserRylb());
            }
            return RestResponseUtil.success(zbList);
        } catch (Exception e) {
            log.error("作业室获取人员类别列表异常：", e);
            return RestResponseUtil.note("系统异常！");
        }
    }

    /*********************************************作业室考评信息录入接口*******************************************/

    @ApiOperation("作业室人员目录树")
    @GetMapping("getZysUserTree")
    public RestResponse<SysOrganizationTree> getZysUserTree(
            @ApiParam(value = "部门ID", required = true) @RequestParam(name = "dept_id") String deptID) {
        try {
            //1、只获取参与任务和考评的人员
            SysOrganizationTree sysOrganizationTree = sysDeptService.getDeptUsers(deptID, false);
            return RestResponseUtil.success(sysOrganizationTree);
        } catch (Exception e) {
            log.error("作业室人员目录树异常：", e);
            return RestResponseUtil.note("系统异常！");
        }
    }


    @ApiOperation("获取用户成绩")
    @GetMapping("getUserBaseScore")
    public RestResponse<sys_user_score> getUserBaseScore(
            @ApiParam(value = "用户ID", required = true) @RequestParam(name = "user_id") String userID
    ) {
        try {
            Integer year = Calendar.getInstance().get(Calendar.YEAR);
            sys_user_score sysUserScore = sysScoreService.findByUserID(year, userID);
            return RestResponseUtil.success(sysUserScore);
        } catch (Exception e) {
            log.error("获取用户成绩异常：", e);
            return RestResponseUtil.note("系统异常！");
        }
    }


    @ApiOperation("录入用户成绩信息")
    @PostMapping("saveUserScore")
    public RestResponse<String> saveUserScore(
            @ApiParam(value = "用户ID", required = true) @RequestParam(name = "user_id") String userID,
            @ApiParam(value = "部门ID", required = true) @RequestParam(name = "dept_id") String deptID,
            @ApiParam(value = "额定工天", required = true) @RequestParam(name = "edgt", defaultValue = "200") Double edgt,
            @ApiParam(value = "业务能力", required = true) @RequestParam(name = "ywnl") Double ywnl,
            @ApiParam(value = "工作态度", required = true) @RequestParam(name = "gztd") Double gztd,
            @ApiParam(value = "出勤率", required = true) @RequestParam(name = "cql") Double cql,
            @ApiParam(value = "训练成绩", required = true) @RequestParam(name = "xlcj") Double xlcj,
            @ApiParam(value = "比武成绩", required = true) @RequestParam(name = "bwcj") Double bwcj,
            @ApiParam(value = "通报表扬", required = true) @RequestParam(name = "tbby", defaultValue = "0") Integer tbby,
            @ApiParam(value = "是否参与考评", required = true) @RequestParam(name = "sfcykp", defaultValue = "是") String sfcykp,
            @ApiParam(value = "是否有重大错误", required = true) @RequestParam(name = "sfjg", defaultValue = "否") String sfjg,
            @ApiParam(value = "犯错信息") @RequestParam(name = "errorinfo", required = false) String errorinfo) {
        /* 说明：
         * 1、考评信息涉及字段：
         *    合格率、优秀率、技术修改数量（主）、技术修改数量（次）、改成图数量（主）、改成图数量（次）
         *    业务工天、优秀工天、业务能力、工作态度、额定工天、出勤率、训练成绩、比武成绩、通报表扬（0.5分每次，数据库存储次数）
         *    是否参与考评、是否有重大错误、错误备注
         * 2、如果用户成绩已经存在则执行更新，否则添加用户成绩信息
         * 3、前端录入字段：
         *    额定工天、业务能力、工作态度、出勤率、训练成绩、比武成绩、通报表扬（0.5分每次，数据库存储次数）、
         *    是否参与考评、是否记过、犯错信息
         * */
        try {
            Integer year = Calendar.getInstance().get(Calendar.YEAR);
            Map<String, String> map = sysUserService.getUserDetailInfo(userID);
            Map<String, String> dwzbMap = new HashMap<String, String>();
            dwzbMap = sysDeptService.getDwZb(deptID);
            sys_user_score sysUserScore = sysScoreService.findByUserID(year, userID);
            int res = 0;
            if (sysUserScore == null) {
                sysUserScore = new sys_user_score();
                sysUserScore.setId(UUIDGeneratorUtil.getUUID());
                sysUserScore.setKpnf(year);//考评年份
                sysUserScore.setUser_id(userID);//用户ID
                sysUserScore.setUser_name(map.get("user_name"));//用户名称
                sysUserScore.setDept_id(deptID);//部门ID
                sysUserScore.setDept_name(dwzbMap.get("dw"));//部门名称
                sysUserScore.setSzxz(dwzbMap.get("zb"));//小组名称
                sysUserScore.setStation_name(map.get("station_name"));//岗位名称
                sysUserScore.setSfzz(map.get("station_name") == "组长" ? "是" : "否");//是否组长
                sysUserScore.setZb(map.get("dept_name"));//组别
                sysUserScore.setRylb(map.get("rylb"));//人员类别
                sysUserScore.setEdgt(edgt);//额定工天
                sysUserScore.setYwnl(ywnl);//业务能力
                sysUserScore.setGztd(gztd);//工作态度
                sysUserScore.setCql(cql);//出勤率
                sysUserScore.setXlcj(xlcj);//训练成绩
                sysUserScore.setBwcj(bwcj);//比武成绩
                sysUserScore.setTbby(tbby);//通报表扬，记录次数
                sysUserScore.setSfcykp(sfcykp);//是否参与考评
                sysUserScore.setSfjg(sfjg);//是否记过
                sysUserScore.setErrorinfo(errorinfo);//犯错信息
                res = sysScoreService.add(sysUserScore);
            } else {
                sysUserScore.setKpnf(year);//考评年份
                sysUserScore.setUser_id(userID);//用户ID
                sysUserScore.setUser_name(map.get("user_name"));//用户名称
                sysUserScore.setDept_id(deptID);//部门ID
                sysUserScore.setDept_name(dwzbMap.get("dw"));//部门名称
                sysUserScore.setSzxz(dwzbMap.get("zb"));//小组名称
                sysUserScore.setStation_name(map.get("station_name"));//岗位名称
                sysUserScore.setSfzz(map.get("station_name") == "组长" ? "是" : "否");//是否组长
                sysUserScore.setZb(map.get("dept_name"));//组别
                sysUserScore.setRylb("rylb");//人员类别
                sysUserScore.setEdgt(edgt);//额定工天
                sysUserScore.setYwnl(ywnl);//业务能力
                sysUserScore.setGztd(gztd);//工作态度
                sysUserScore.setCql(cql);//出勤率
                sysUserScore.setXlcj(xlcj);//训练成绩
                sysUserScore.setBwcj(bwcj);//比武成绩
                sysUserScore.setTbby(tbby);//通报表扬，记录次数
                sysUserScore.setSfcykp(sfcykp);//是否参与考评
                sysUserScore.setSfjg(sfjg);//是否记过
                sysUserScore.setErrorinfo(errorinfo);//犯错信息
                res = sysScoreService.update(sysUserScore);
            }
            if (res > 0) {
                return RestResponseUtil.success("保存成功！");
            } else {
                return RestResponseUtil.note("保存失败！");
            }
        } catch (Exception e) {
            log.error("录入用户成绩信息异常：", e);
            return RestResponseUtil.note("系统异常！");
        }
    }


    @ApiOperation("删除成绩信息")
    @PostMapping("deleteUserScore")
    public RestResponse<String> deleteUserScore(
            @ApiParam(value = "用户ID", required = true) @RequestParam(name = "user_id") String userID) {
        /*
         * 说明：
         * 此处删除的仅为上面手动录入的成绩信息,而不是成绩最终表中的信息
         * */
        try {
            Integer year = Calendar.getInstance().get(Calendar.YEAR);
            int res = sysScoreService.delete(year, userID);
            if (res > 0) {
                return RestResponseUtil.success("删除成功！");
            } else {
                return RestResponseUtil.note("删除失败！");
            }
        } catch (Exception e) {
            log.error("删除成绩信息异常：", e);
            return RestResponseUtil.note("系统异常！");
        }
    }


    @ApiOperation("作业室考评成绩存档")
    @GetMapping("saveToFinalScore")
    @Transactional(propagation = Propagation.REQUIRED)
    public RestResponse<Map<String, List>> saveToFinalScore(
            @ApiParam(value = "部门ID", required = true) @RequestParam(name = "dept_id") String deptID) {
        RestResponse<Map<String, List>> response = null;
        try {
            /*
             * 说明：考评成绩存档，是将当前作业室下用户的所有考评成绩进行存档
             * 1、存档之前先把以存档的数据给删了
             * 2、计算其他成绩
             * 3、对组长成绩进行特殊处理并更新
             * 4、计算质量成绩排名、工天成绩排名、能力态度成绩排名、业务训练成绩排名、成绩总排名、考评结果（组别和人员类别都按照全体人员计算）将所有成绩进行存档
             * 5、从存档中查询所有的结果，按照总排名进行排序
             * 备注：只有组别和人员类别为全体人员时才让更新评定结果，其他只是查询显示，或者导出excel后手动更改
             * */
            Integer year = Calendar.getInstance().get(Calendar.YEAR);
            List<sys_user_score> sysUserScoreList = sysScoreService.findByDeptID(year, deptID);
            if (sysUserScoreList != null && sysUserScoreList.size() > 0) {
                boolean res = sysScoreService.saveToFinalScore(year, deptID, sysUserScoreList);
                if (res) {
                    response = RestResponseUtil.success("考评成绩存档成功！");
                } else {
                    return RestResponseUtil.note("考评成绩存档失败！");
                }
            } else {
                return RestResponseUtil.note("请先录入考评成绩！");
            }
        } catch (Exception e) {
            log.error("作业室考评成绩存档异常：", e);
            return RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation("获取作业室考评最终成绩")
    @GetMapping("getZysFinalScore")
    public RestResponse<Map<String, List>> getZysFinalScore(
            @ApiParam(value = "部门ID", required = true) @RequestParam(name = "dept_id") String deptID) {
        RestResponse<Map<String, List>> response = null;
        try {
            Integer year = Calendar.getInstance().get(Calendar.YEAR);
            //质量测评成绩列表
            List<ZlcpScoreModel> zlcpResultList = sysFinalScoreService.queryZlcpScore(0, year, deptID, "全体人员", "全体人员");
            //工天测评成绩列表
            List<GtcpScoreModel> gtcpResultList = sysFinalScoreService.queryGtcpScore(0, year, deptID, "全体人员", "全体人员");
            //能力态度测评成绩列表
            List<NltdcpScoreModel> nltdcpResultList = sysFinalScoreService.queryNltdcpScore(0, year, deptID, "全体人员", "全体人员");
            //业务训练测评成绩列表
            List<YwxlcpScoreModel> ywxlcpResultList = sysFinalScoreService.queryYwxlcpScore(0, year, deptID, "全体人员", "全体人员");
            //综合测评成绩列表模型
            List<ZhcpScoreModel> zhcpResultList = sysFinalScoreService.queryZhcpScore(0, year, deptID, "全体人员", "全体人员");
            Map<String, List> map = new HashMap<>();
            map.put("zlcj", zlcpResultList);
            map.put("gtcj", gtcpResultList);
            map.put("nltdcj", nltdcpResultList);
            map.put("ywxlcj", ywxlcpResultList);
            map.put("zhcj", zhcpResultList);
            response = RestResponseUtil.success("", map);
        } catch (Exception e) {
            log.error("获取作业室考评最终成绩异常：", e);
            return RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    /*********************************************作业室考评分析相关接口*******************************************/

    @ApiOperation("获取作业室考评相关成绩")
    @GetMapping("getZysKpInfo")
    public RestResponse<Map> getZysKpInfo(
            @ApiParam(value = "部门ID", required = true) @RequestParam(name = "dept_id") String deptID
    ) {
        try {
            Integer year = Calendar.getInstance().get(Calendar.YEAR);
            List<sys_user_finalscore> list = sysFinalScoreService.queryFinalScore(year, deptID, "全体人员", "全体人员");
            if (list != null && list.size() > 0) {
                String deptName = list.get(0).getDept_name();

                List<String> deptKpUserIDList = new ArrayList<>();//单位参与考评的人员ID列表
                List<sys_user_score> deptKpScoreList = sysScoreService.getDeptScoreInfo(year, deptID, "否");//只算参与考评的人
                for (sys_user_score sysUserScore : deptKpScoreList) {
                    deptKpUserIDList.add(sysUserScore.getUser_id());
                }
                List<String> deptAllUserIDList = new ArrayList<>();//单位所有人员ID列表
                List<sys_user_score> deptAllScoreList = sysScoreService.getDeptScoreInfo(year, deptID, "是");//算所有人
                for (sys_user_score sysUserScore : deptAllScoreList) {
                    deptAllUserIDList.add(sysUserScore.getUser_id());
                }
                List<String> allUserIDList = new ArrayList<>();//全社所有人员ID列表
                List<sys_user_score> scoreList2 = sysScoreService.getAllScoreInfo(year, "是");//算所有人
                for (sys_user_score sysUserScore : scoreList2) {
                    allUserIDList.add(sysUserScore.getUser_id());
                }
                double jghzgt = sysJghzgtService.query(year, deptID).getJghzgt();
                //TODO 相关数据需要改成生产环境
                Map<String, Object> map = new HashMap<>();
                map.put("deptName", deptName);//单位
                map.put("zrs", deptAllUserIDList.size());//单位总人数
                map.put("kprs", deptKpUserIDList.size());//单位考评人数
                map.put("jghzgt", jghzgt);//机关核准总工天

                map.put("dwsjzgt", 200.00);//单位实际总工天
                map.put("dwkpzgt", 210.00);//单位考评总工天
                map.put("dwrjgt", 212.00);//单位人均工天
                map.put("dwkprjgt", 212.00);//单位考评人均工天
                map.put("dwyxzgt", 224.00);//单位优秀总工天
                map.put("dwyxkpzgt", 232.00);//单位优秀考评总工天
                map.put("dwpjyxgt", 220.00);//单位平均优秀工天
                map.put("dwpjyxl", 208.00);//单位平均优秀率
                map.put("qspjgt", 208.00);//全社平均工天
                map.put("qspjyxl", 208.00);//全社平均优秀率

                //生产环境中使用
            /*double dwsjzgt=calculationHelperService.GetDeptOneYearAllDays(deptAllUserIDList,year.toString(),1);
            map.put("dwsjzgt", new BigDecimal(dwsjzgt).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue());//单位实际总工天

            double dwkpzgt=calculationHelperService.GetDeptOneYearAllDays(deptKpUserIDList,year.toString(),0);
            map.put("dwkpzgt", new BigDecimal(dwkpzgt).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue());//单位考评总工天

            double dwrjgt=calculationHelperService.GetDeptOneYearAverageDays(deptAllUserIDList,year.toString());
            map.put("dwrjgt", new BigDecimal(dwrjgt).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue());//单位人均工天

            double dwkprjgt=calculationHelperService.GetDeptOneYearAllDays(deptKpUserIDList,year.toString(),0);
            map.put("dwkprjgt", new BigDecimal(dwkprjgt).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue());//单位考评人均工天

            double dwyxzgt=calculationHelperService.GetDeptOneYearAllDays(deptAllUserIDList,year.toString(),2);
            map.put("dwyxzgt", new BigDecimal(dwyxzgt).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue());//单位优秀总工天

            double dwyxkpzgt=calculationHelperService.GetDeptOneYearAllDays(deptKpUserIDList,year.toString(),3);
            map.put("dwyxkpzgt", new BigDecimal(dwyxkpzgt).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue());//单位优秀考评总工天

            double dwpjyxgt=calculationHelperService.GetDeptOneYearAverageExcellentDays(deptAllUserIDList,year.toString());
            map.put("dwpjyxgt", new BigDecimal(dwpjyxgt).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue());//单位平均优秀工天

            double dwpjyxl=calculationHelperService.GetDeptOneYearAverageExcellent(deptAllUserIDList,year.toString());
            map.put("dwpjyxl", new BigDecimal(dwpjyxl).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue());//单位平均优秀率

            double qspjgt=calculationHelperService.GetDeptOneYearAverageDays(allUserIDList,year.toString());
            map.put("qspjgt", new BigDecimal(qspjgt).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue());//全社平均工天

            double qspjyxl=calculationHelperService.GetDeptOneYearAverageExcellent(allUserIDList,year.toString());
            map.put("qspjyxl", new BigDecimal(qspjyxl).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue());//全社平均优秀率*/
                return RestResponseUtil.success(map);
            } else {
                return RestResponseUtil.note("成绩信息还未存档！");
            }
        } catch (Exception e) {
            log.error("获取作业室考评相关成绩异常：", e);
            return RestResponseUtil.note("系统异常！");
        }
    }

    @ApiOperation("计算:0按全体人员计算，1按小组（专业类别）计算")
    @PostMapping("calScore")
    public RestResponse<Map> calScore(
            @ApiParam(value = "部门ID", required = true) @RequestParam(name = "dept_id") String deptID,
            @ApiParam(value = "组别", required = true) @RequestParam(name = "zb") String zb,
            @ApiParam(value = "人员类别", required = true) @RequestParam(name = "rylb") String rylb,
            @ApiParam(value = "计算方式", required = true) @RequestParam("calType") Integer calType) {
        Map<String, List> map = new HashMap<>();
        try {
            Integer year = Calendar.getInstance().get(Calendar.YEAR);
            //质量测评成绩列表
            List<ZlcpScoreModel> zlcpResultList = sysFinalScoreService.queryZlcpScore(calType, year, deptID, zb, rylb);
            //工天测评成绩列表
            List<GtcpScoreModel> gtcpResultList = sysFinalScoreService.queryGtcpScore(calType, year, deptID, zb, rylb);
            //能力态度测评成绩列表
            List<NltdcpScoreModel> nltdcpResultList = sysFinalScoreService.queryNltdcpScore(calType, year, deptID, zb, rylb);
            //业务训练测评成绩列表
            List<YwxlcpScoreModel> ywxlcpResultList = sysFinalScoreService.queryYwxlcpScore(calType, year, deptID, zb, rylb);
            //综合测评成绩列表模型
            List<ZhcpScoreModel> zhcpResultList = sysFinalScoreService.queryZhcpScore(calType, year, deptID, zb, rylb);
            map.put("zlcj", zlcpResultList);
            map.put("gtcj", gtcpResultList);
            map.put("nltdcj", nltdcpResultList);
            map.put("ywxlcj", ywxlcpResultList);
            map.put("zhcj", zhcpResultList);
            return RestResponseUtil.success(map);
        } catch (Exception e) {
            log.error("成绩计算异常：", e);
            return RestResponseUtil.note("系统异常！");
        }
    }

    @ApiOperation("作业室导出考评信息")
    @GetMapping("exportZysScore")
    public RestResponse<Object> exportZysScore(
            @ApiParam(value = "部门ID", required = true) @RequestParam(name = "dept_id") String deptID,
            @ApiParam(value = "组别", required = true) @RequestParam(name = "zb") String zb,
            @ApiParam(value = "人员类别", required = true) @RequestParam(name = "rylb") String rylb,
            HttpServletResponse response) {
        try {
            InputStream inputStream = this.getClass().getResourceAsStream("/doc/kp/kaoping.xls");
            Integer year = Calendar.getInstance().get(Calendar.YEAR);
            List<sys_user_finalscore> finalscoreList = sysFinalScoreService.queryFinalScore(year, deptID, zb, rylb);
            String deptName = "";
            if (finalscoreList != null && finalscoreList.size() > 0) {
                deptName = finalscoreList.get(0).getDept_name();
            }
            ScoreExcelOperationUtil.exportScoreResultToExcel(year.toString(), deptName, finalscoreList, inputStream, response);
            return RestResponseUtil.success("导出成功！");
        } catch (Exception e) {
            log.error("作业室导出考评信息异常：", e);
            return RestResponseUtil.note("系统异常！");
        }
    }


    /*********************************************机关公共接口*******************************************/

    @ApiOperation("机关获取组别列表")
    @GetMapping("getJgZb")
    public RestResponse<List<String>> getJgZb(
            @ApiParam(value = "考评年份", required = true) @RequestParam(name = "kpnf") Integer year,
            @ApiParam(value = "部门ID", required = true) @RequestParam(name = "deptID") String deptID) {
        /*
         * 说明：
         * 1、机关从考评成绩最终表中获取组别列表
         * */
        try {
            List<String> zbList = sysFinalScoreService.getJgZbList(year, deptID);
            return RestResponseUtil.success(zbList);
        } catch (Exception e) {
            log.error("机关获取组别列表异常：", e);
            return RestResponseUtil.note("系统异常！");
        }
    }


    @ApiOperation("机关获取人员类别列表")
    @GetMapping("getJgRylb")
    public RestResponse<List<String>> getJgRylb() {
        try {
            List<String> zbList = new ArrayList<>();
            zbList.add("全体人员");
            for (EnumUserRylb rylb : EnumUserRylb.values()) {
                zbList.add(rylb.getUserRylb());
            }
            return RestResponseUtil.success(zbList);
        } catch (Exception e) {
            log.error("机关获取人员类别列表异常：", e);
            return RestResponseUtil.note("系统异常！");
        }
    }


    /*********************************************机关考评管理相关接口*******************************************/

    @ApiOperation("获取机关组织目录树")
    @GetMapping("getJgTree")
    public RestResponse<SysOrganizationTree> getJgTree(
            @ApiParam(value = "考评年份", required = true) @RequestParam(name = "kpnf") Integer year
    ) {
        /*
         * 说明：
         * 1、机关组织目录树从考评成绩最终表中获取，这样可以保证对历年考评成绩信息进行一个完整的存储
         * */
        RestResponse<SysOrganizationTree> response = null;
        try {
            SysOrganizationTree sysOrganizationTree = new SysOrganizationTree();
            sysOrganizationTree.setId(UUIDGeneratorUtil.getUUID());
            sysOrganizationTree.setPid("-1");
            sysOrganizationTree.setNodeID("-1");
            sysOrganizationTree.setNodeOrder(0);
            sysOrganizationTree.setNodeType("部门");
            sysOrganizationTree.setNodeName("海图信息中心");

            List<SysOrganizationTree> sysOrganizationTreeList = new ArrayList<>();
            List<sys_user_finalscore> sysUserFinalscoreList = sysFinalScoreService.getZysList(year);
            for (sys_user_finalscore sysUserFinalscore : sysUserFinalscoreList) {
                SysOrganizationTree sysOrganizationTree1 = new SysOrganizationTree();
                sysOrganizationTree1.setId(UUIDGeneratorUtil.getUUID());
                sysOrganizationTree1.setPid(sysOrganizationTree.getId());
                sysOrganizationTree1.setNodeID(sysUserFinalscore.getDept_id());
                sysOrganizationTree1.setNodeName(sysUserFinalscore.getDept_name());
                sysOrganizationTree1.setNodeType("部门");
                sysOrganizationTreeList.add(sysOrganizationTree1);
            }
            sysOrganizationTree.setChildNodes(sysOrganizationTreeList);
            response = RestResponseUtil.success(sysOrganizationTree);
        } catch (Exception e) {
            log.error("获取机关组织目录树异常：", e);
            return RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation("机关获取作业室考评信息")
    @GetMapping("getJgScore")
    public RestResponse<Map<String, List>> getJgScore(
            @ApiParam(value = "考评年份", required = true) @RequestParam(name = "kpnf") Integer year,
            @ApiParam(value = "部门ID", required = true) @RequestParam(name = "dept_id") String deptID,
            @ApiParam(value = "组别", required = true) @RequestParam(name = "zb") String zb,
            @ApiParam(value = "人员类别", required = true) @RequestParam(name = "rylb") String rylb) {
        /*
         * 说明：
         * 1、机关获取各作业室成绩信息从考评成绩最终表中获取
         * 2、各作业室计算成绩存储在考评成绩最终表中
         *
         * 3、机关考评成绩最终表存储信息：
         *    唯一ID、考评年份、用户ID、姓名、部门ID、部门名称、岗位名称、是否组长、组别、人员类别、合格率、合格率得分、优秀率、优秀率得分、优秀工天、优秀工天比、优秀工天比得分、改成图数量主、改成图数量次、改成图数量、改成图扣分、技术修改数量主、技术修改数量次、技术修改数量、技术修改扣分、质量总得分、质量排名、额定工天、工天、工天得分、工天比、工天比得分、工天总得分、工天得分排名、业务能力、工作态度、能力态度总得分、能力态度得分排名、出勤率、出勤率得分、训练成绩、训练得分、比武成绩、比武得分、业务训练总得分、训练排名、通报表扬（0.5分每次）、考评总分、考评总排名、评定结果、是否参与考评、是否记过、犯错信息
         *
         * 4、考评成绩
         * 质量测评：姓名、岗位、组长、合格率、合格率得分、优秀率、优秀率得分、优秀工天、优秀工天比、优秀工天比得分、改成图扣分、技术修改扣分、质量总得分、质量排名
         * 工天测评：姓名、岗位、组长、工天、工天得分、工天比、工天比得分、工天总得分、工天得分排名
         * 能力与态度：姓名、岗位、组长、业务能力、工作态度、能力态度总得分、能力态度得分排名
         * 业务训练：姓名、岗位、组长、出勤率、出勤率得分、训练成绩、训练得分、比武成绩、比武得分、业务训练总得分、训练排名
         * 综合测评：姓名、岗位、组长、合格率、合格率得分、优秀率、优秀率得分、优秀工天、优秀工天比、优秀工天比得分、改成图扣分、技术修改扣分、质量总得分、质量排名、工天、工天得分、工天比、工天比得分、工天总得分、工天得分排名、业务能力、工作态度、能力态度总得分、能力态度得分排名、出勤率、出勤率得分、训练成绩、训练得分、比武成绩、比武得分、业务训练总得分、训练排名、通报表扬得分、考评总分、考评总排名、评定结果、姓名
         *
         * */

        RestResponse<Map<String, List>> response = null;
        try {
            //质量测评成绩列表
            List<ZlcpScoreModel> zlcpScoreModelList = sysFinalScoreService.queryZlcpScore(0, year, deptID, zb, rylb);
            //工天测评成绩列表
            List<GtcpScoreModel> gtcpScoreModelList = sysFinalScoreService.queryGtcpScore(0, year, deptID, zb, rylb);
            //能力态度测评成绩列表
            List<NltdcpScoreModel> nltdcpScoreModelList = sysFinalScoreService.queryNltdcpScore(0, year, deptID, zb, rylb);
            //业务训练测评成绩列表
            List<YwxlcpScoreModel> ywxlcpScoreModelList = sysFinalScoreService.queryYwxlcpScore(0, year, deptID, zb, rylb);
            //综合测评成绩列表模型
            List<ZhcpScoreModel> zhcpScoreModelList = sysFinalScoreService.queryZhcpScore(0, year, deptID, zb, rylb);
            Map<String, List> map = new HashMap<>();
            map.put("zlcj", zlcpScoreModelList);
            map.put("gtcj", gtcpScoreModelList);
            map.put("nltdcj", nltdcpScoreModelList);
            map.put("ywxlcj", ywxlcpScoreModelList);
            map.put("zhcj", zhcpScoreModelList);
            response = RestResponseUtil.success("", map);
        } catch (Exception e) {
            log.error("机关获取作业室考评信息异常：", e);
            return RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation("机关导出考评信息")
    @GetMapping("exportJgScore")
    public RestResponse<Object> exportJgScore(
            @ApiParam(value = "部门ID", required = true) @RequestParam(name = "dept_id") String deptID,
            @ApiParam(value = "组别", required = true) @RequestParam(name = "zb") String zb,
            @ApiParam(value = "人员类别", required = true) @RequestParam(name = "rylb") String rylb,
            HttpServletResponse response) {
        /*
         * 说明：
         * 1、机关导出各作业室成绩信息从考评成绩最终表中获取
         * */
        try {
            Integer year = Calendar.getInstance().get(Calendar.YEAR);
            InputStream inputStream = this.getClass().getResourceAsStream("/doc/kp/kaoping.xls");
            List<sys_user_finalscore> finalscoreList = sysFinalScoreService.queryFinalScore(year, deptID, zb, rylb);
            String deptName = "";
            if (finalscoreList != null && finalscoreList.size() > 0) {
                deptName = finalscoreList.get(0).getDept_name();
            }
            ScoreExcelOperationUtil.exportScoreResultToExcel(year.toString(), deptName, finalscoreList, inputStream, response);
            return RestResponseUtil.success("导出成功！");
        } catch (Exception e) {
            log.error("机关导出考评信息异常：", e);
            return RestResponseUtil.note("系统异常！");
        }
    }


    /*********************************************机关考评分析相关接口*******************************************/

    @ApiOperation("机关获取作业室考评基本信息")
    @GetMapping("getJgKpInfo")
    public RestResponse<sys_jghzgt> getSysDeptKpBaseInfo(
            @ApiParam(value = "考评年份", required = true) @RequestParam(name = "kpnf") Integer year,
            @ApiParam(value = "部门ID", required = true) @RequestParam(name = "dept_id") String deptID,
            @ApiParam(value = "部门名称", required = true) @RequestParam(name = "dept_name") String deptName) {
        /*
         * 说明：
         * 1、年份如果是当前年份，则所有数据通过计算所得，否则，从机关核准工天表中获取历年各机关考评基本信息
         * */
        try {
            sys_jghzgt sysJghzgt = null;
            if (Calendar.getInstance().get(Calendar.YEAR) == year) {
                List<String> deptKpUserIDList = new ArrayList<>();//单位参与考评的人员ID列表
                List<sys_user_score> deptKpScoreList = sysScoreService.getDeptScoreInfo(year, deptID, "否");//只算参与考评的人
                for (sys_user_score sysUserScore : deptKpScoreList) {
                    deptKpUserIDList.add(sysUserScore.getUser_id());
                }
                List<String> deptAllUserIDList = new ArrayList<>();//单位所有人员ID列表
                List<sys_user_score> deptAllScoreList = sysScoreService.getDeptScoreInfo(year, deptID, "是");//算所有人
                for (sys_user_score sysUserScore : deptAllScoreList) {
                    deptAllUserIDList.add(sysUserScore.getUser_id());
                }
                List<String> allUserIDList = new ArrayList<>();//全社所有人员ID列表
                List<sys_user_score> scoreList2 = sysScoreService.getAllScoreInfo(year, "是");//算所有人
                for (sys_user_score sysUserScore : scoreList2) {
                    allUserIDList.add(sysUserScore.getUser_id());
                }

                sysJghzgt = new sys_jghzgt();
                sysJghzgt.setDept_id(deptID);//单位名称
                sysJghzgt.setDept_name(deptName);//单位名称
                sysJghzgt.setDept_zrs(deptAllUserIDList.size());//单位总人数
                sysJghzgt.setDept_kprs(deptKpUserIDList.size());//单位考评人数
                //TODO 相关数据需要改成生产环境
                sysJghzgt.setDept_sjzgt(200.00);//单位实际总工天
                sysJghzgt.setDept_kpzgt(180.00);//单位考评总工天
                sysJghzgt.setDept_rjgt(170.00);//单位人均工天
                sysJghzgt.setDept_rjkpgt(160.00);//单位考评人均工天
                sysJghzgt.setDept_yxzgt(150.00);//单位优秀总工天
                sysJghzgt.setDept_yxkpzgt(140.00);//单位优秀考评总工天
                sysJghzgt.setDept_pjyxgt(130.00);//单位平均优秀工天
                sysJghzgt.setDept_pjyxl(120.00);//单位平均优秀率
                sysJghzgt.setQspjgt(110.00);//全社平均工天
                sysJghzgt.setQspjyxl(100.00);//全社平均优秀率


                //生产环境中使用
                /*double dwsjzgt = calculationHelperService.GetDeptOneYearAllDays(deptAllUserIDList, year.toString(), 1);
                sysJghzgt.setDept_sjzgt(new BigDecimal(dwsjzgt).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue());//单位实际总工天

                double dwkpzgt = calculationHelperService.GetDeptOneYearAllDays(deptKpUserIDList, year.toString(), 0);
                sysJghzgt.setDept_kpzgt(new BigDecimal(dwkpzgt).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue());//单位考评总工天

                double dwrjgt = calculationHelperService.GetDeptOneYearAverageDays(deptAllUserIDList, year.toString());
                sysJghzgt.setDept_rjgt(new BigDecimal(dwrjgt).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue());//单位人均工天

                double dwkprjgt = calculationHelperService.GetDeptOneYearAllDays(deptKpUserIDList, year.toString(), 0);
                sysJghzgt.setDept_rjkpgt(new BigDecimal(dwkprjgt).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue());//单位考评人均工天

                double dwyxzgt = calculationHelperService.GetDeptOneYearAllDays(deptAllUserIDList, year.toString(), 2);
                sysJghzgt.setDept_yxzgt(new BigDecimal(dwyxzgt).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue());//单位优秀总工天

                double dwyxkpzgt = calculationHelperService.GetDeptOneYearAllDays(deptKpUserIDList, year.toString(), 3);
                sysJghzgt.setDept_yxkpzgt(new BigDecimal(dwyxkpzgt).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue());//单位优秀考评总工天

                double dwpjyxgt = calculationHelperService.GetDeptOneYearAverageExcellentDays(deptAllUserIDList, year.toString());
                sysJghzgt.setDept_pjyxgt(new BigDecimal(dwpjyxgt).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue());//单位平均优秀工天

                double dwpjyxl = calculationHelperService.GetDeptOneYearAverageExcellent(deptAllUserIDList, year.toString());
                sysJghzgt.setDept_pjyxl(new BigDecimal(dwpjyxl).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue());//单位平均优秀率

                double qspjgt = calculationHelperService.GetDeptOneYearAverageDays(allUserIDList, year.toString());
                sysJghzgt.setQspjgt(new BigDecimal(qspjgt).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue());//全社平均工天

                double qspjyxl = calculationHelperService.GetDeptOneYearAverageExcellent(allUserIDList, year.toString());
                sysJghzgt.setQspjyxl(new BigDecimal(qspjyxl).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue());//全社平均优秀率*/
            } else {
                sysJghzgt = sysJghzgtService.query(Calendar.getInstance().get(Calendar.YEAR), deptID);
            }
            return RestResponseUtil.success(sysJghzgt);
        } catch (Exception e) {
            log.error("机关获取作业室考评基本信息异常：", e);
            return RestResponseUtil.note("系统异常！");
        }
    }

    @ApiOperation("修改机关核准总工天")
    @PostMapping("saveJghzgt")
    public RestResponse<String> saveJghzgt(
            @ApiParam(value = "部门ID", required = true) @RequestParam(name = "dept_id") String deptID,
            @ApiParam(value = "部门名称", required = true) @RequestParam(name = "dept_name") String deptName,
            @ApiParam(value = "机关核准工天", required = true) @RequestParam(name = "jghzgt") Double jghzgt) {
        try {
            /* 说明：
             * 1、如果机关核准工天不存在就添加，否则更新，只操作当前年份的机关核准工天
             **/
            Integer year = Calendar.getInstance().get(Calendar.YEAR);
            sys_jghzgt sysJghzgt = sysJghzgtService.query(year, deptID);

            List<String> deptKpUserIDList = new ArrayList<>();//单位参与考评的人员ID列表
            List<sys_user_score> deptKpScoreList = sysScoreService.getDeptScoreInfo(year, deptID, "否");//只算参与考评的人
            for (sys_user_score sysUserScore : deptKpScoreList) {
                deptKpUserIDList.add(sysUserScore.getUser_id());
            }
            List<String> deptAllUserIDList = new ArrayList<>();//单位所有人员ID列表
            List<sys_user_score> deptAllScoreList = sysScoreService.getDeptScoreInfo(year, deptID, "是");//算所有人
            for (sys_user_score sysUserScore : deptAllScoreList) {
                deptAllUserIDList.add(sysUserScore.getUser_id());
            }
            List<String> allUserIDList = new ArrayList<>();//全社所有人员ID列表
            List<sys_user_score> scoreList2 = sysScoreService.getAllScoreInfo(year, "是");//算所有人
            for (sys_user_score sysUserScore : scoreList2) {
                allUserIDList.add(sysUserScore.getUser_id());
            }


            boolean add = false;
            if (sysJghzgt == null) {
                add = true;//标记为新增
                sysJghzgt = new sys_jghzgt();
                sysJghzgt.setId(UUIDGeneratorUtil.getUUID());
                sysJghzgt.setDept_id(deptID);
            }
            sysJghzgt.setYear(year);
            sysJghzgt.setJghzgt(jghzgt);//机关核准工天
            sysJghzgt.setDept_name(deptName);//单位名称
            sysJghzgt.setDept_zrs(deptAllUserIDList.size());//单位总人数
            sysJghzgt.setDept_kprs(deptKpScoreList.size());//单位考评人数
            //TODO 相关数据需要改成生产环境
            sysJghzgt.setDept_sjzgt(200.00);//单位实际总公共天
            sysJghzgt.setDept_kpzgt(180.00);//单位考评总工天
            sysJghzgt.setDept_rjgt(170.00);//单位人均工天
            sysJghzgt.setDept_rjkpgt(160.00);//单位考评人均工天
            sysJghzgt.setDept_yxzgt(150.00);//单位优秀总工天
            sysJghzgt.setDept_yxkpzgt(140.00);//单位优秀考评总工天
            sysJghzgt.setDept_pjyxgt(130.00);//单位平均优秀工天
            sysJghzgt.setDept_pjyxl(120.00);//单位平均优秀率
            sysJghzgt.setQspjgt(110.00);//全社平均工天
            sysJghzgt.setQspjyxl(100.00);//全社平均优秀率


            //生产环境中使用
            /*double dwsjzgt = calculationHelperService.GetDeptOneYearAllDays(deptAllUserIDList, year.toString(), 1);
            sysJghzgt.setDept_sjzgt(new BigDecimal(dwsjzgt).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue());//单位实际总工天

            double dwkpzgt = calculationHelperService.GetDeptOneYearAllDays(deptKpUserIDList, year.toString(), 0);
            sysJghzgt.setDept_kpzgt(new BigDecimal(dwkpzgt).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue());//单位考评总工天

            double dwrjgt = calculationHelperService.GetDeptOneYearAverageDays(deptAllUserIDList, year.toString());
            sysJghzgt.setDept_rjgt(new BigDecimal(dwrjgt).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue());//单位人均工天

            double dwkprjgt = calculationHelperService.GetDeptOneYearAllDays(deptKpUserIDList, year.toString(), 0);
            sysJghzgt.setDept_rjkpgt(new BigDecimal(dwkprjgt).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue());//单位考评人均工天

            double dwyxzgt = calculationHelperService.GetDeptOneYearAllDays(deptAllUserIDList, year.toString(), 2);
            sysJghzgt.setDept_yxzgt(new BigDecimal(dwyxzgt).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue());//单位优秀总工天

            double dwyxkpzgt = calculationHelperService.GetDeptOneYearAllDays(deptKpUserIDList, year.toString(), 3);
            sysJghzgt.setDept_yxkpzgt(new BigDecimal(dwyxkpzgt).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue());//单位优秀考评总工天

            double dwpjyxgt = calculationHelperService.GetDeptOneYearAverageExcellentDays(deptAllUserIDList, year.toString());
            sysJghzgt.setDept_pjyxgt(new BigDecimal(dwpjyxgt).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue());//单位平均优秀工天

            double dwpjyxl = calculationHelperService.GetDeptOneYearAverageExcellent(deptAllUserIDList, year.toString());
            sysJghzgt.setDept_pjyxl(new BigDecimal(dwpjyxl).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue());//单位平均优秀率

            double qspjgt = calculationHelperService.GetDeptOneYearAverageDays(allUserIDList, year.toString());
            sysJghzgt.setQspjgt(new BigDecimal(qspjgt).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue());//全社平均工天

            double qspjyxl = calculationHelperService.GetDeptOneYearAverageExcellent(allUserIDList, year.toString());
            sysJghzgt.setQspjyxl(new BigDecimal(qspjyxl).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue());//全社平均优秀率*/


            int res = 0;
            if (add) {
                res = sysJghzgtService.insert(sysJghzgt);
            } else {
                res = sysJghzgtService.update(sysJghzgt);
            }
            if (res > 0) {
                return RestResponseUtil.success("保存成功！");
            } else {
                return RestResponseUtil.note("保存失败！");
            }
        } catch (Exception e) {
            log.error("修改机关核准总工天异常：", e);
            return RestResponseUtil.note("系统异常！");
        }
    }


    /*********************************************历年成绩查看相关接口*******************************************/

}

