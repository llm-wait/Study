package com.startest.wm.controller;

import com.startest.wm.enums.EnumTaskChartDistributionType;
import com.startest.wm.pojo.wm_check_index;
import com.startest.wm.service.CheckIndexService;
import com.startest.wm.service.TaskDistributionService;
import com.startest.wm.utils.customresponse.ExceptionHandleUtil;
import com.startest.wm.utils.customresponse.RestResponse;
import com.startest.wm.utils.customresponse.RestResponseCode;
import com.startest.wm.utils.customresponse.RestResponseUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-07-09-15:51
 * @描述 质检索引逻辑控制层
 */
@Controller
@Api(tags = "质检索引业务逻辑API")
@RequestMapping("/task/checkindex")
public class CheckIndexController {
    @Autowired
    public CheckIndexService checkIndexService;

    @Autowired
    private  TaskDistributionService taskDistributionService;

    @ApiOperation(value = "获取质检索引对象", notes = "获取质检索引对象")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/selectmodel")
    public RestResponse<wm_check_index> getTaskCheckIndeModel(
            @ApiParam("任务ID") @RequestParam(name = "task_index_id") String indexID,
            @ApiParam("部门ID") @RequestParam(name = "dept_id", required = false) String deptID,
            @ApiParam("海图类型或书表章节") @RequestParam(name = "chart_type", required = false) String strType) {

            wm_check_index check = new wm_check_index();
            check.setTask_index_id(indexID);
            check.setDept_id(deptID);
            List<wm_check_index> list = checkIndexService.selectCheckIndexInfo(check);

            //根据任务id查询总工天
//            Double workDays = taskDistributionService.getAllWorkDays(indexID);

//            List<Map> map = taskDistributionService.getDuty(indexID, deptID);
//            查询作业员
            //编辑
            //组长

            if (list != null && list.size() > 0) {
                wm_check_index resIndex = list.get(0);
                //查询编辑设计
                String strBJSJ = taskDistributionService.getTaskDistributeName(indexID, deptID, strType, EnumTaskChartDistributionType.BJSJ.getTaskChartDistributionType());
                resIndex.setChart_editor(strBJSJ);

                //作业员
                String strZYY = taskDistributionService.getTaskDistributeName(indexID, deptID, strType, EnumTaskChartDistributionType.ZY.getTaskChartDistributionType());
                resIndex.setChart_maker(strBJSJ);

                //组长
                String strZZ = taskDistributionService.getTaskDistributeName(indexID, deptID, strType, EnumTaskChartDistributionType.ZZ.getTaskChartDistributionType());
                resIndex.setChart_leader(strBJSJ);

                return RestResponseUtil.createResponse(RestResponseCode.SUCCESS, list.get(0));
            } else {
               return RestResponseUtil.error(300, "检索信息为空！");
            }
    }

    @ApiOperation(value = "修改质检索引对象", notes = "修改质检索引对象")
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public RestResponse<String> updateTaskIndexInfo(
            @ApiParam("质检索引对象") @RequestBody wm_check_index jsonIndex) {
        RestResponse<String> response = null;
        try {
            wm_check_index index = new wm_check_index();
            index.setCheck_index_id(jsonIndex.getCheck_index_id());
            String strDate=jsonIndex.getStart_check_date();
            if(strDate!=null&&strDate.length()>0){
                index.setStart_check_date(jsonIndex.getStart_check_date());
            }else {
                index.setStart_check_date(null);
            }
            index.setChart_book_back(jsonIndex.getChart_book_back());
            index.setChart_pdf_back(jsonIndex.getChart_pdf_back());
            index.setChart_edit(jsonIndex.getChart_edit());
            index.setChart_map_edit(jsonIndex.getChart_map_edit());
            index.setChart_manager(jsonIndex.getChart_manager());
            index.setChart_manager1(jsonIndex.getChart_manager1());
            int res = checkIndexService.updateCheckIndexInfo(index);
            if (res > 0) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "质检索引信息修改成功！");
            } else {
                response = RestResponseUtil.createResponse(RestResponseCode.FAIL, "质检索引信息修改失败！");
            }
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }
}
