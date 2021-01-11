package com.startest.wm.controller;

import com.startest.wm.pojo.ysk_test;
import com.startest.wm.service.YskTestService;
import com.startest.wm.utils.UUIDGeneratorUtil;
import com.startest.wm.utils.customresponse.ExceptionHandleUtil;
import com.startest.wm.utils.customresponse.RestResponse;
import com.startest.wm.utils.customresponse.RestResponseCode;
import com.startest.wm.utils.customresponse.RestResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-07-11-8:40
 * @描述 杨世凯测试
 */
@Controller
@RequestMapping("/ysktest")
public class YskTestController {
    @Autowired
    private YskTestService yskTestService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/select")
    public RestResponse<List<ysk_test>> selectList(@RequestParam(name = "id", required = false) String strID,
                                                   @RequestParam(name = "name", required = false) String strName,
                                                   @RequestParam(name = "age", required = false) Integer strAge,
                                                   @RequestParam(name = "address", required = false) String strAddress) {
            ysk_test y = new ysk_test();
                y.setStudent_id(strID);
                y.setStudent_name(strName);
                y.setStudent_age(strAge);
                y.setStudent_address(strAddress);
            List<ysk_test> list = yskTestService.getAllList(y);
            return  RestResponseUtil.createResponse(RestResponseCode.SUCCESS, list);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public RestResponse<String> insertInfo(@RequestBody ysk_test yJson) {
        RestResponse<String> response = null;
        try {
            ysk_test y = new ysk_test();
            y.setStudent_id(UUIDGeneratorUtil.getUUIDWithoutLineAndToUpper());
            y.setStudent_name(yJson.getStudent_name());
            y.setStudent_age(yJson.getStudent_age());
            y.setStudent_address(yJson.getStudent_address());
            int res = yskTestService.insertInfo(y);
            if (res > 0) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "添加成功");
            } else {
                response = RestResponseUtil.createResponse(RestResponseCode.FAIL, "添加失败");
            }

        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST,value = "/update")
    public RestResponse<String> updateInfo(@RequestBody ysk_test yJson) {
        RestResponse<String> response = null;
        try {
            ysk_test y = new ysk_test();
            y.setStudent_id(yJson.getStudent_id());
            y.setStudent_name(yJson.getStudent_name());
            y.setStudent_age(yJson.getStudent_age());
            y.setStudent_address(yJson.getStudent_address());
            int res = yskTestService.updateInfo(y);
            if (res > 0) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "修改成功");
            } else {
                response = RestResponseUtil.createResponse(RestResponseCode.FAIL, "修改失败");
            }
        } catch (Exception e) {
            response=ExceptionHandleUtil.handle(e);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET,value = "/delete")
    public RestResponse<String> deleteInfo(@RequestParam(name = "id",required = true)String strID) {
        RestResponse<String> response = null;
        try {
            int res = yskTestService.deleteInfo(strID);
            if (res > 0) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "删除成功");
            } else {
                response = RestResponseUtil.createResponse(RestResponseCode.FAIL, "删除失败");
            }
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }
}
