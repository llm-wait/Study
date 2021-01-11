package com.startest.wm.controller;

import com.startest.wm.pojo.wm_subject_pay;
import com.startest.wm.service.SubjectPayService;
import com.startest.wm.utils.UUIDGeneratorUtil;
import com.startest.wm.utils.customresponse.RestResponse;
import com.startest.wm.utils.customresponse.RestResponseCode;
import com.startest.wm.utils.customresponse.RestResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-06-29-13:47
 * @描述 科目支出逻辑API
 */
@Controller
@Api(tags = "科研科目支出信息业务逻辑API")
@RequestMapping("/task/subjectpay")
public class SubjectPayController {

    private static final Logger log = LoggerFactory.getLogger(SubjectPayController.class);

    @Autowired
    private SubjectPayService subjectPayService;

    @ApiOperation(value = "获取科目支出信息列表", notes = "获取科目支出信息列表")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/select")
    public RestResponse<List<wm_subject_pay>> selectSubjectPayInfoList(
            @ApiParam("支出ID") @RequestParam(name = "pay_id", required = false) String payID,
            @ApiParam("项目ID") @RequestParam(name = "project_id", required = false) String proID,
            @ApiParam("科目ID") @RequestParam(name = "subject_id", required = false) String subID) {
        RestResponse<List<wm_subject_pay>> response = null;
        try {
            wm_subject_pay pay = new wm_subject_pay();
            pay.setPay_id(payID);
            pay.setProject_id(proID);
            pay.setSubject_id(subID);
            List<wm_subject_pay> payList = subjectPayService.getSubjectPayInfoList(pay);
            response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, payList);
        } catch (Exception e) {
            log.error("获取科目支出信息列表异常", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }



    @ApiOperation(value = "通过ID获取科目支出信息", notes = "通过ID获取科目支出信息")
    @RequestMapping(method = RequestMethod.GET, value = "/selectbyid")
    @ResponseBody
    public RestResponse<wm_subject_pay> getSubjectPayInfoByID(
            @ApiParam("支出ID") @RequestParam(name = "pay_id") String payID) {
        RestResponse<wm_subject_pay> response = null;
        try {
            wm_subject_pay subjectPay = subjectPayService.getSubjectPayInfoByID(payID);
            response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, subjectPay);
        } catch (Exception e) {
            log.error("通过ID获取科目支出信息异常", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }



    @ApiOperation(value = "添加科目支出信息", notes = "添加科目支出信息")
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    @ResponseBody
    public RestResponse<String> addSubjectPayInfo(
            @ApiParam("科目支出信息对象") @RequestBody wm_subject_pay subjectPay) {
        RestResponse<String> response = null;
        try {
            subjectPay.setPay_id(UUIDGeneratorUtil.getUUIDWithoutLineAndToUpper());
            int res = subjectPayService.insertSubjectPayInfo(subjectPay);
            if (res > 0) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "科目支出信息添加成功！");
            } else {
                response = RestResponseUtil.createResponse(RestResponseCode.NOTE, "科目支出信息添加失败！");
            }
        } catch (Exception e) {
            log.error("添加科目支出信息异常", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }



    @ApiOperation(value = "修改科目支出信息", notes = "修改科目支出信息")
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    @ResponseBody
    public RestResponse<String> updateSubjectPayInfo(
            @ApiParam("科目支出信息对象") @RequestBody wm_subject_pay subjectPay) {
        RestResponse<String> response = null;
        try {
            int res = subjectPayService.updateSubjectPayInfo(subjectPay);
            if (res > 0) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "科目支出信息修改成功！");
            } else {
                response = RestResponseUtil.note("科目支出信息修改失败！");
            }
        } catch (Exception e) {
            log.error("修改科目支出信息异常", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }



    @ApiOperation(value = "删除科目支出信息", notes = "删除科目支出信息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/deletebyid")
    public RestResponse<String> deleteSubjectPayInfoByID(
            @ApiParam("支出ID") @RequestParam(name = "pay_id") String payID) {
        RestResponse<String> response = null;
        try {
            int res = subjectPayService.deleteSubjectPayInfo(payID);
            if (res > 0) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "科目支出信息删除成功！");
            } else {
                response = RestResponseUtil.createResponse(RestResponseCode.FAIL, "科目支出信息删除失败！");
            }
        } catch (Exception e) {
            log.error("删除科目支出信息异常", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }
}
