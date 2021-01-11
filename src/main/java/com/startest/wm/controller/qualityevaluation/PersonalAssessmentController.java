package com.startest.wm.controller.qualityevaluation;

import com.startest.wm.enums.EnumStationName;
import com.startest.wm.enums.EnumTaskChartDistributionType;
import com.startest.wm.enums.task.EnumTaskProductType;
import com.startest.wm.pojo.wm_quality_user;
import com.startest.wm.service.TaskBookCalculationHelperService;
import com.startest.wm.service.TaskChartCalculationHelperService;
import com.startest.wm.service.qualityevaluation.PersonalAssessmentService;
import com.startest.wm.utils.PersonalCheckoutUtil;
import com.startest.wm.utils.customresponse.RestResponse;
import com.startest.wm.utils.customresponse.RestResponseCode;
import com.startest.wm.utils.customresponse.RestResponseUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName:startestwm
 * @PackageName:com.startest.wm.controller.qualityevaluation
 * @ClassName:PersonalAssessment
 * @Description: 个人工作质量评估
 * @author: skj
 * @date 2020/7/28  13:16
 */
@Controller
@Api(tags = "质控处参谋相关API")
@RequestMapping("/personal/assessment")
public class PersonalAssessmentController {

    @Autowired
    private PersonalAssessmentService personalAssessmentService;

    @Autowired
    TaskChartCalculationHelperService taskChartCalculationHelperService;

// 书表质控评审计算帮助接口
    @Autowired
    private   TaskBookCalculationHelperService taskBookCalculationHelperService;


    /**质控参谋——个人工作质量评估——查询个人工作质量列表
     * @param wmQualityUser
     * @return
     */
    @ApiOperation(value = "个人工作质量评估",notes = "个人工作质量评估")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "task_year",value = "年度",dataType = "String"),
//            @ApiImplicitParam(name = "quarter",value = "季度",dataType = "String"),
//            @ApiImplicitParam(name = "dept_id",value = "作业室",dataType = "String"),
//            @ApiImplicitParam(name = "user_name",value = "姓名",dataType = "String"),
//            @ApiImplicitParam(name = "performance_evaluation",value = "工作质量(优秀1，良好2，合格3)",required = true,dataType =
//                    "String"),
//            @ApiImplicitParam(name = "product_type",value = "产品类型(海图，书表，图集，整体测绘)",required = true,dataType = "String"),
//            @ApiImplicitParam(name = "product_class",value = "产品分类(原创航海资料，改版航海资料，法规，汇编我)",required = true,dataType =
//                    "String")
//    })
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET,value = "/personalSelect")
    public RestResponse<List<wm_quality_user>> personalSelect(wm_quality_user wmQualityUser){
        List<wm_quality_user> list=personalAssessmentService.personalSelect(wmQualityUser);
     return     RestResponseUtil.createResponse(RestResponseCode.SUCCESS,list);
    }



    @ApiOperation(value = "个人工作质量评估,列表导出",notes = "个人工作质量评估,列表导出")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "task_year",value = "年度",required = false,dataType = "String"),
//            @ApiImplicitParam(name = "quarter",value = "季度",required = false,dataType = "String"),
//            @ApiImplicitParam(name = "dept_id",value = "作业室",required = false,dataType = "String"),
//    })
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET,value = "/checkOutPersonalAssessment")
    public void checkOut(
            HttpServletResponse httpServletResponse,
            wm_quality_user wmQualityUser){
        List<wm_quality_user> list=personalAssessmentService.personalSelect(wmQualityUser);
            PersonalCheckoutUtil.exportCheckListtoExcel(list,  httpServletResponse);
    }


    /**质控参谋——个人工作质量评估——获取人员姓名
     * @param dept_name
     * @param dept_id
     * @return
     */
    @ApiOperation(value = "跟据作业室名称或id，查询相关人员",notes = "跟据作业室，查询相关人员")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET,value = "/selectNameByStation")
    public RestResponse<List<String>> selectNameByStation(
            @ApiParam(value = "部门名称") String dept_name,
            @ApiParam(value = "部门id")String dept_id){
        List<String> list=personalAssessmentService.selectNameByStation(dept_id,dept_name);
        return     RestResponseUtil.createResponse(RestResponseCode.SUCCESS,list);
    }

    @ApiOperation(value = "任务质量评估：查询组长名称",notes = "查询组长名称")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET,value = "/selectGroupLeader")
    public RestResponse<List<String>> selectGroupLeader(HttpServletResponse httpServletResponse, @ApiParam("岗位角色：" +
            "BJ:制图编辑，ZYY:作业员，ZZ:组长") @RequestParam(name = "task_state") EnumStationName StationName){
//        List<String> list=personalAssessmentService.selectNameByStation(dept_id,dept_name);
        List<String> list = personalAssessmentService.selectGroupLeader(StationName.getStationName());
        return     RestResponseUtil.createResponse(RestResponseCode.SUCCESS,list);
    }


    @ApiOperation(value = "校对员错漏率计算",notes = "错漏率计算")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET,value = "/selectOperatorError")
    public RestResponse<String>  selectOperatorError (HttpServletResponse httpServletResponse,String dept_id ){
//        List<String> list = personalAssessmentService.selectGroupLeader(StationName.getStationName());

        //校对员消灭错漏率=  校对员消灭的缺陷值  *  本级及以后各级消灭缺陷值之和 *100%


        return     RestResponseUtil.createResponse(RestResponseCode.SUCCESS,"11%");
    }


    @ApiOperation(value = "质量评估纸图计算",notes = "质量评估纸图计算————")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "map_code",value = "作业员id",required = false,dataType = "String"),
//    })
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET,value = "/calculate")
    public RestResponse<Map>  calculate (HttpServletResponse httpServletResponse,String task_id,String map_code ,String TaskChartType){
        EnumTaskProductType  productType=EnumTaskProductType.ZT;
        if (EnumTaskProductType.SZT.getTaskProductType().equals(TaskChartType)){
            productType=EnumTaskProductType.SZT;
        }else if (EnumTaskProductType.EPS.getTaskProductType().equals(TaskChartType)){
            productType=EnumTaskProductType.EPS;
        }else if (EnumTaskProductType.S57.getTaskProductType().equals(TaskChartType)){
            productType=EnumTaskProductType.S57;
        }else if (EnumTaskProductType.TJCT.getTaskProductType().equals(TaskChartType)){
            productType=EnumTaskProductType.TJCT;
        }



        //在质控操作处，只计算前三个，后边再计算剩下的

        //1.作业员缺陷值
        Double zyyqxz = taskChartCalculationHelperService.ChartWorkerDefactValueByWorkday(task_id, productType);
        //2.校对消灭错漏率=校对员消灭的缺陷值  *  本级及以后各级消灭缺陷值之和 *100%     (校对员的缺陷值)
        Double jdcll = taskChartCalculationHelperService.ChartCheckerPassAwayDefact(task_id, EnumTaskChartDistributionType.JD, productType);
        //3.审查消灭错漏率=  审查编辑消灭缺陷值/本级及以后各级消灭的缺陷值 *100%
        Double sccll = taskChartCalculationHelperService.ChartCheckerPassAwayDefact(task_id, EnumTaskChartDistributionType.BJSC, productType);

        //4.本幅海图综合质量评定= 产品验收时发现产品缺陷值大小评定  >0.5 不合格   0.1-0.5 合格证 0.1 优秀
        //5.校对工作质量评定= 消灭错漏率和图幅难度系数评定   ok
        //6.编辑审查工作质量评定=审查编辑消灭错漏率 和 图幅难度系数
        //7作业员工作质量评定=   每工天产生缺陷值（校对，审查，验收）发现的产品总缺陷值
        // 难度系数=本图工天  / 本季度本单位完成图幅的平均工天


        Map<String, Double>  map= new HashMap<String,Double>();

        map.put("zyclv", zyyqxz);
        map.put("jdxmcll", jdcll);
        map.put("scxmcll", sccll);
//        map.put("htzhzlpd", "");
//        map.put("jdgzzzpd", "4");
//        map.put("bjscpd", "5");
//        map.put("zyyzlpd", "0");
        return     RestResponseUtil.createResponse(RestResponseCode.SUCCESS,map);
    }


}
