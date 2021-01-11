package com.startest.wm.controller.qualityevaluation;

import com.startest.wm.pojo.wm_quality_chart;
import com.startest.wm.service.qualityevaluation.ProductAssessmentService;
import com.startest.wm.utils.ProductCheckoutUtil;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName:startestwm
 * @PackageName:com.startest.wm.controller.qualityevaluation
 * @ClassName:ProductAssessmentController
 * @Description: 产品质量评估
 * @author: skj
 * @date 2020/7/28  17:20
 */
@Controller
@Api(tags = "质控处参谋相关API")
@RequestMapping("/Product/assessment")
public class ProductAssessmentController {

    @Autowired
    private ProductAssessmentService productAssessmentService;


    /**质控参谋——产品质量评估——获取海图产品质评列表
     * @param year
     * @param quarter
     * @param chartType
     * @return
     */
    @ApiOperation(value = "海图产品质量评估", notes = "海图产品质量评估")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/chartProductSelect")
    public RestResponse<List<wm_quality_chart>> chartProductSelect(
            @ApiParam(value = "年度") String year,
            @ApiParam(value = "季度") String quarter,
            @ApiParam(value = "产品类型") String chartType) {

        List<wm_quality_chart> list = productAssessmentService.chartProductSelect(year, quarter, chartType);

        //todo --
//        ArrayList<wm_quality_chart> list = new ArrayList<>();
//        wm_quality_chart wmQualityChart = new wm_quality_chart();
//        wmQualityChart.setChart_name("105");
////        wmQualityChart.setTask_year(2020);
//        wmQualityChart.setChart_code("105");
//        wmQualityChart.setTask_index_id("7FEC7DA31F6147CBB38D9D7CB97E2921");


        return RestResponseUtil.createResponse(RestResponseCode.SUCCESS, list);
    }


    @ApiOperation(value = "海图产品质量评估,列表导出", notes = "海图产品质量评估,列表导出")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/checkOutChart")
    public void checkOutChart(
            HttpServletResponse httpServletResponse,
            @ApiParam(value = "年度") String year,
            @ApiParam(value = "季度") String quarter,
            @ApiParam(value = "产品类型") String chartType) {
        List<wm_quality_chart> list = productAssessmentService.chartProductSelect(year, quarter, chartType);
        ProductCheckoutUtil.exportCheckListtoExcel(list, httpServletResponse);
    }


    /**质控参谋——产品质量评估——航海书表质评列表
     * @param year
     * @param quarter
     * @return
     */
    @ApiOperation(value = "航海书表质量评估", notes = "航海书表质量评估")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "year", value = "年度", required = false, dataType = "String"),
            @ApiImplicitParam(name = "quarter", value = "季度", required = false, dataType = "String"),
    })
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/sailingBookTableSelect")
    public RestResponse<List<Map>> sailingBookTableSelect(
            @ApiParam(value = "年度") String year,
            @ApiParam(value = "季度") String quarter) {

        List<Map> list = productAssessmentService.sailingBookTableSelect(year, quarter);
        return RestResponseUtil.createResponse(RestResponseCode.SUCCESS, list);
//        return null;
    }


    @ApiOperation(value = "航海书表质量评估,列表导出", notes = "航海书表质量评估,列表导出")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/checkoutSailingBookTable")
    public void checkoutSailingBookTable(
            HttpServletResponse httpServletResponse,
            @ApiParam(value = "年度") String year,
            @ApiParam(value = "季度(o:全年，1，2，3，4季度)") String quarter,
            String chartType) {
        List<Map> list = productAssessmentService.sailingBookTableSelect(year, quarter);
        ProductCheckoutUtil.CheckExcelSailingBook(list, httpServletResponse);
    }
}
