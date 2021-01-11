package com.startest.wm.controller;

import com.startest.wm.pojo.sys_login;
import com.startest.wm.pojo.wm_form_data;
import com.startest.wm.service.FormDataService;
import com.startest.wm.utils.GlobalLoginInfoUtil;
import com.startest.wm.utils.UUIDGeneratorUtil;
import com.startest.wm.utils.customresponse.ExceptionHandleUtil;
import com.startest.wm.utils.customresponse.RestResponse;
import com.startest.wm.utils.customresponse.RestResponseCode;
import com.startest.wm.utils.customresponse.RestResponseUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-07-15-11:04
 * @描述 数据工作单逻辑接口层
 */
@Controller
@Api(tags = "数据工作单API")
@RequestMapping("/task/formdata")
public class FormDataController {
    @Autowired
    private FormDataService formDataService;

    /**生产任务——表单详情——书号cip回显
     * @param strID　任务id
     * @return 书号cip号
     */
    @ApiOperation(value = "获取默认数据工作单信息", notes = "获取默认数据工作单信息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/databyid")
    public RestResponse<Object> selectFormDetailInfo(
            @ApiParam("任务索引ID") @RequestParam(name = "task_index_id") String strID) {
        RestResponse<Object> response = null;
        try {
            Object obj = formDataService.selectFormDataInfoByTaskIndexID(strID);
            response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, obj);
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }

    @ApiOperation(value = "新增数据工作单信息", notes = "新增数据工作单信息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public RestResponse<String> addFormDataInfo(
            HttpServletRequest request,
            @ApiParam("数据工作单信息对象") @RequestBody wm_form_data jsonData) {
        RestResponse<String> response = null;
        try {
            sys_login loginU = GlobalLoginInfoUtil.getLoginUserInfo(request);
            if (loginU != null && loginU.getDept_id() != null && loginU.getDept_id().length() > 0) {
                wm_form_data data = new wm_form_data();
                String strID=jsonData.getBook_data_id();
                if(strID!=null&&strID.length()>0) {
                    data.setBook_data_id(jsonData.getBook_data_id());
                    data.setBook_jcode(jsonData.getBook_jcode());
                    data.setBook_name(jsonData.getBook_name());
                    data.setBook_describe(jsonData.getBook_describe());
                    data.setBook_edition(jsonData.getBook_edition());
                    data.setBook_impression(jsonData.getBook_impression());
                    data.setBook_edit(jsonData.getBook_edit());
                    data.setBook_publish(jsonData.getBook_publish());
                    data.setBook_impression_count(jsonData.getBook_impression_count());
                    int res = formDataService.updateFormDataInfo(data);
                    if (res > 0) {
                        response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "数据工作单信息修改成功！");
                    } else {
                        response = RestResponseUtil.createResponse(RestResponseCode.FAIL, "数据工作单信息修改失败！");
                    }
                }else {
                    data.setBook_data_id(UUIDGeneratorUtil.getUUIDWithoutLineAndToUpper());
                    data.setBook_jcode(jsonData.getBook_jcode());
                    data.setBook_name(jsonData.getBook_name());
                    data.setBook_describe(jsonData.getBook_describe());
                    data.setBook_edition(jsonData.getBook_edition());
                    data.setBook_impression(jsonData.getBook_impression());
                    data.setBook_edit(jsonData.getBook_edit());
                    data.setBook_publish(jsonData.getBook_publish());
                    data.setBook_impression_count(jsonData.getBook_impression_count());
                    data.setTask_index_id(jsonData.getTask_index_id());
                    int res = formDataService.insertFormDataInfo(data, loginU);
                    if (res > 0) {
                        response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "数据工作单信息添加成功！");
                    } else {
                        response = RestResponseUtil.createResponse(RestResponseCode.FAIL, "数据工作单信息添加失败！");
                    }
                }
            } else {
                response = RestResponseUtil.createResponse(RestResponseCode.FAIL, "获取登录部门信息失败！");
            }
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }

    @ApiOperation(value = "修改数据工作单信息",notes = "修改数据工作单信息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST,value = "/update")
    public RestResponse<String> updateFormDataInfo(
            @ApiParam("数据工作单信息对象") @RequestBody wm_form_data jsonData) {
        RestResponse<String> response = null;
        try {
            wm_form_data data = new wm_form_data();
            data.setBook_data_id(jsonData.getBook_data_id());
            data.setBook_jcode(jsonData.getBook_jcode());
            data.setBook_name(jsonData.getBook_name());
            data.setBook_describe(jsonData.getBook_describe());
            data.setBook_edition(jsonData.getBook_edition());
            data.setBook_impression(jsonData.getBook_impression());
            data.setBook_edit(jsonData.getBook_edit());
            data.setBook_publish(jsonData.getBook_publish());
            data.setBook_impression_count(jsonData.getBook_impression_count());
            int res = formDataService.updateFormDataInfo(data);
            if (res > 0) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "数据工作单信息修改成功！");
            } else {
                response = RestResponseUtil.createResponse(RestResponseCode.FAIL, "数据工作单信息修改失败！");
            }
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }

    @ApiOperation(value = "删除数据工作单信息", notes = "删除数据工作单信息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/delete")
    public RestResponse<String> deleteFormDataInfo(
            @RequestParam(name = "book_data_id") String strID) {
        RestResponse<String> response = null;
        try {
            int res = formDataService.deleteFormDataInfo(strID);
            if (res > 0) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "数据工作单信息删除成功！");
            } else {
                response = RestResponseUtil.createResponse(RestResponseCode.FAIL, "数据工作单信息删除失败！");
            }
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }
}
