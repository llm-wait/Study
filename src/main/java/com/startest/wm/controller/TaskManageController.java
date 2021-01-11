package com.startest.wm.controller;

import com.startest.wm.pojo.wm_task_others;
import com.startest.wm.utils.customresponse.RestResponse;
import com.startest.wm.utils.customresponse.RestResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author skj
 * @date 2020/12/25 0025 16:27
 * @PackageName:StartestWM
 * @ClassName:任务管理 描述：
 */
@Controller
@Api(tags = "任务管理相关api")
@RequestMapping("/task/manager")
public class TaskManageController {

//    @Autowired
//    private TaskManageService taskManageService;


    @ApiOperation(value = "添加任务", notes = "添加任务")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/select")
    public RestResponse<List<wm_task_others>> addTask(
            @ApiParam( "其他任务Id") @RequestParam(name = "otask_id", required = false) String othersId,
            @ApiParam("任务索引Id") @RequestParam(name = "index_id", required = false) String indexId,
            @ApiParam("任务名称") @RequestParam(name = "otask_name", required = false) String othersName) {
        return RestResponseUtil.success("");
    }

}
