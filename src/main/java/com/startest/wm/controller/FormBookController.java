package com.startest.wm.controller;

import com.startest.wm.pojo.sys_login;
import com.startest.wm.pojo.wm_form_book;
import com.startest.wm.service.FormBookService;
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
 * @创建时间 2020-07-15-11:03
 * @描述 标准书号申领单逻辑接口层
 */
@Controller
@Api(tags = "书号申领单API")
@RequestMapping("/task/formbook")
public class FormBookController {
    @Autowired
    private FormBookService formBookService;

    /**生产任务——表单详情——书号申请——回显
     * @param strId　任务id
     * @return 　书号工作表
     */
    @ApiOperation(value = "获取默认书号申领单信息", notes = "获取默认书号申领单信息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/bookbyid")
    public RestResponse<wm_form_book> selectFormDetailInfo(
            @ApiParam("任务索引ID") @RequestParam(name = "task_index_id") String strId) {
            wm_form_book wmFormBook = formBookService.selectFormBookInfoByTaskIndexID(strId);
            return RestResponseUtil.createResponse(RestResponseCode.SUCCESS, wmFormBook);
    }

    @ApiOperation(value = "新增书号申领单信息", notes = "新增书号申领单信息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public RestResponse<String> addFormBookInfo(
            HttpServletRequest request,
            @ApiParam("书号申领单对象") @RequestBody wm_form_book jsonBook) {
        RestResponse<String> response = null;
        try {
            sys_login loginU = GlobalLoginInfoUtil.getLoginUserInfo(request);
            if (loginU != null && loginU.getDept_id() != null && loginU.getDept_id().length() > 0) {
                wm_form_book book = new wm_form_book();
                String strID = jsonBook.getForm_id();
                if (strID != null && strID.length() > 0) {
                    book.setForm_id(jsonBook.getForm_id());
                    book.setBook_jcode(jsonBook.getBook_jcode());
                    book.setBook_code(jsonBook.getBook_code());
                    book.setBook_name(jsonBook.getBook_name());
                    book.setBook_standard_code(jsonBook.getBook_standard_code());
                    book.setBook_unit_post(jsonBook.getBook_unit_post());
                    book.setBook_edit(jsonBook.getBook_edit());
                    book.setBook_post_title(jsonBook.getBook_post_title());
                    book.setRemark(jsonBook.getRemark());
                    int res = formBookService.updateFormBookInfo(book);
                    if (res > 0) {
                        response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "书号申领单信息修改成功！");
                    } else {
                        response = RestResponseUtil.createResponse(RestResponseCode.FAIL, "书号申领单信息修改失败！");
                    }
                } else {
                    book.setForm_id(UUIDGeneratorUtil.getUUIDWithoutLineAndToUpper());
                    book.setBook_jcode(jsonBook.getBook_jcode());
                    book.setBook_code(jsonBook.getBook_code());
                    book.setBook_name(jsonBook.getBook_name());
                    book.setBook_standard_code(jsonBook.getBook_standard_code());
                    book.setBook_unit_post(jsonBook.getBook_unit_post());
                    book.setBook_edit(jsonBook.getBook_edit());
                    book.setBook_post_title(jsonBook.getBook_post_title());
                    book.setRemark(jsonBook.getRemark());
                    book.setTask_index_id(jsonBook.getTask_index_id());
                    int res = formBookService.insertFormBookInfo(book, loginU);
                    if (res > 0) {
                        response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "书号申领单信息添加成功！");
                    } else {
                        response = RestResponseUtil.createResponse(RestResponseCode.FAIL, "书号申领单信息添加失败！");
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

    @ApiOperation(value = "修改书号申领单信息",notes = "修改书号申领单信息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST,value = "/update")
    public RestResponse<String> updateFormDataInfo(
            @ApiParam("书号申领单信息对象") @RequestBody wm_form_book jsonBook) {
        RestResponse<String> response = null;
        try {
            wm_form_book book = new wm_form_book();
            book.setForm_id(jsonBook.getForm_id());
            book.setBook_jcode(jsonBook.getBook_jcode());
            book.setBook_code(jsonBook.getBook_code());
            book.setBook_name(jsonBook.getBook_name());
            book.setBook_standard_code(jsonBook.getBook_standard_code());
            book.setBook_unit_post(jsonBook.getBook_unit_post());
            book.setBook_edit(jsonBook.getBook_edit());
            book.setBook_post_title(jsonBook.getBook_post_title());
            book.setRemark(jsonBook.getRemark());
            int res = formBookService.updateFormBookInfo(book);
            if (res > 0) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "书号申领单信息修改成功！");
            } else {
                response = RestResponseUtil.createResponse(RestResponseCode.FAIL, "书号申领单信息修改失败！");
            }
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }

    @ApiOperation(value = "删除书号申领单信息", notes = "删除书号申领单信息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/delete")
    public RestResponse<String> deleteFormBookInfo(
            @RequestParam(name = "form_id") String strID) {
        RestResponse<String> response = null;
        try {
            int res = formBookService.deleteFormBookInfo(strID);
            if (res > 0) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "书号申领单信息删除成功！");
            } else {
                response = RestResponseUtil.createResponse(RestResponseCode.FAIL, "书号申领单信息删除失败！");
            }
        } catch (Exception e) {
            response = ExceptionHandleUtil.handle(e);
        }
        return response;
    }
}
