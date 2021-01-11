package com.startest.wm.controller;

import com.startest.wm.pojo.sys_login;
import com.startest.wm.pojo.wm_project_template;
import com.startest.wm.service.ProjectTemplateService;
import com.startest.wm.utils.FileUpOrDownloadUtil;
import com.startest.wm.utils.MyDateUtils;
import com.startest.wm.utils.ProjectExcelImportUtil;
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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-06-29-13:48
 * @描述 项目模板逻辑接口
 */
@Controller
@Api(tags = "科研项目模板信息业务逻辑API")
@RequestMapping("/task/projecttemplate")
public class ProjectTemplateController {

    private static final Logger log = LoggerFactory.getLogger(ProjectTemplateController.class);

    @Autowired
    ProjectTemplateService projectTemplateService;

    @ApiOperation(value = "获取项目模板信息列表", notes = "获取项目模板信息列表")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/select")
    public RestResponse<List<wm_project_template>> selectProjectTemplateInfoList(
            @ApiParam("模板ID") @RequestParam(name = "template_id", required = false) String temID,
            @ApiParam("模板名称") @RequestParam(name = "template_name", required = false) String temName) {
        RestResponse<List<wm_project_template>> response = null;
        try {
            wm_project_template template = new wm_project_template();
            template.setTemplate_id(temID);
            template.setTemplate_name(temName);
            List<wm_project_template> temList = projectTemplateService.getProjectTemplateInfoList(template);
            response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, temList);
        } catch (Exception e) {
            log.error("获取项目模板信息列表异常", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "通过ID获取项目模板信息", notes = "通过ID获取项目模板信息")
    @RequestMapping(method = RequestMethod.GET, value = "/selectbyid")
    @ResponseBody
    public RestResponse<wm_project_template> getProjectTemplateInfoByID(
            @ApiParam("模板ID") @RequestParam(name = "template_id") String id) {
        RestResponse<wm_project_template> response = null;
        try {
            wm_project_template projectTemplate = projectTemplateService.getProjectTemplateInfoByID(id);
            response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, projectTemplate);
        } catch (Exception e) {
            log.error("通过ID获取项目模板信息异常", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "添加科研项目模板信息", notes = "添加项目模板信息")
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    @ResponseBody
    public RestResponse<String> addProjectTemplateInfo(
            HttpServletRequest request,
            @ApiParam("模板名称") @RequestParam(name = "template_name") String temName,
            @ApiParam("应用范围") @RequestParam(value = "template_range") String temRange,
            @ApiParam("作者") @RequestParam(value = "template_auther") String temAuther,
            @ApiParam("备注") @RequestParam(value = "remark", required = false) String remark,
            @ApiParam(value = "模板文件") @RequestParam(name = "file", required = false) MultipartFile templateFile
    ) {
        sys_login syslogin = (sys_login) request.getSession().getAttribute("syslogin");
        if (syslogin == null) {
            return RestResponseUtil.createResponse(RestResponseCode.EXPIRE, RestResponseCode.EXPIRE.getMsg());
        }
        RestResponse<String> response = null;
        try {
            wm_project_template template = new wm_project_template();
            template.setTemplate_id(UUIDGeneratorUtil.getUUIDWithoutLineAndToUpper());
            template.setTemplate_name(temName);
            template.setTemplate_range(temRange);
            template.setTemplate_auther(temAuther);
            template.setTemplate_update(MyDateUtils.getCurrentDate(MyDateUtils.DATE_PATTERN_SECOND));
            template.setTemplate_upoper(syslogin.getLogin_name());
            template.setRemark(remark);

            //模板文件处理
            if (templateFile != null) {
                File serverTemplateFile = null;
                try {
                    //模板在服务器上的实际存储文件夹路径
                    String serverFilePath = request.getServletContext().getRealPath("/") + "upload/ky/template/" + template.getTemplate_id();
                    File src = new File(serverFilePath);
                    if (!src.exists()) {
                        src.mkdirs();
                    }
                    String url = "/upload/ky/template/" + template.getTemplate_id() + "/" + templateFile.getOriginalFilename();
                    template.setFilename(templateFile.getOriginalFilename());//模板名称，也即是文件名称
                    template.setTemplate_path(url);//模板在服务器上的http路径，用于页面回显，数据库存储此路径

                    //在指定路径下创建一个新的文件
                    serverTemplateFile = new File(serverFilePath, templateFile.getOriginalFilename());
                    //使用spring mvc的transferTo方法上传文件
                    templateFile.transferTo(serverTemplateFile);
                } catch (Exception e) {
                    if (serverTemplateFile != null && serverTemplateFile.exists()) {
                        serverTemplateFile.delete();
                    }
                }
            }
            //添加记录
            int res = projectTemplateService.insertProjectTemplateInfo(template);
            if (res > 0) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "项目模板信息添加成功！");
            } else {
                response = RestResponseUtil.createResponse(RestResponseCode.NOTE, "项目模板信息添加失败！");
            }
        } catch (Exception e) {
            log.error("添加科研项目模板信息异常", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "批量导入项目模板信息", notes = "批量导入项目模板信息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/dataimport", headers = "content-type=multipart/form-data")
    @Transactional(propagation = Propagation.REQUIRED)
    public RestResponse<String> importProjectTemplateByList(
            @ApiParam(value = "项目模板excel文件", required = true) @RequestParam(value = "file") MultipartFile file) {
        RestResponse<String> response = null;
        try {
            if (!file.getOriginalFilename().endsWith(".xls") && !file.getOriginalFilename().endsWith(".xlsx")) {
                response = RestResponseUtil.note("文件格式错误！");
            } else {
                InputStream inputStream = file.getInputStream();
                List<wm_project_template> templateList = ProjectExcelImportUtil.readToProjectTemplateData(inputStream, file.getOriginalFilename());
                if (templateList.size() > 0) {
                    int res = projectTemplateService.insertProjectTemplateByList(templateList);
                    if (res > 0) {
                        response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "项目模板文件信息导入成功！");
                    } else {
                        response = RestResponseUtil.createResponse(RestResponseCode.FAIL, "项目模板文件信息导入失败！");
                    }
                } else {
                    response = RestResponseUtil.note("导入的数据为空！");
                }
                inputStream.close();
            }
        } catch (Exception e) {
            log.error("批量导入科研项目模板信息异常", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "修改项目模板信息", notes = "修改项目模板信息")
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    @ResponseBody
    public RestResponse<String> updateProjectTemplateInfo(
            HttpServletRequest request,
            @ApiParam("模板id") @RequestParam(value = "template_id") String templateId,
            @ApiParam("模板名称") @RequestParam(name = "template_name") String temName,
            @ApiParam("应用范围") @RequestParam(value = "template_range") String temRange,
            @ApiParam("作者") @RequestParam(value = "template_auther") String temAuther,
            @ApiParam("备注") @RequestParam(value = "remark", required = false) String remark,
            @ApiParam(value = "模板文件") @RequestParam(name = "file", required = false) MultipartFile templateFile) {
        sys_login syslogin = (sys_login) request.getSession().getAttribute("syslogin");
        if (syslogin == null) {
            return RestResponseUtil.createResponse(RestResponseCode.EXPIRE, RestResponseCode.EXPIRE.getMsg());
        }
        RestResponse<String> response = null;
        try {
            wm_project_template template = new wm_project_template();
            template.setTemplate_id(templateId);
            template.setTemplate_name(temName);
            template.setTemplate_range(temRange);
            template.setTemplate_update(MyDateUtils.getCurrentDate(MyDateUtils.DATE_PATTERN_FLAT));
            template.setTemplate_auther(temAuther);
            template.setTemplate_upoper(syslogin.getLogin_name());
            template.setRemark(remark);

            //模板文件处理
            if (templateFile != null) {
                File serverTemplateFile = null;
                try {
                    //模板在服务器上的实际存储文件夹路径
                    String serverFilePath = request.getServletContext().getRealPath("/") + "upload/ky/template/" + template.getTemplate_id();
                    File src = new File(serverFilePath);
                    if (!src.exists()) {
                        src.mkdirs();
                    }
                    String url = "/upload/ky/template/" + template.getTemplate_id() + "/" + templateFile.getOriginalFilename();
                    template.setFilename(templateFile.getOriginalFilename());//模板文件名称为上传文件名称
                    template.setTemplate_path(url);//模板在服务器上的http路径，用于页面回显，数据库存储此路径

                    //在指定路径下创建一个新的文件
                    serverTemplateFile = new File(serverFilePath, templateFile.getOriginalFilename());
                    //使用spring mvc的transferTo方法上传文件
                    templateFile.transferTo(serverTemplateFile);

                    //如果上传的模板文件名变化了，则删除老文件
                    for (File file : src.listFiles()) {
                        if (!file.getName().equals(templateFile.getOriginalFilename())) {
                            file.delete();
                        }
                    }
                } catch (Exception e) {
                    if (serverTemplateFile != null && serverTemplateFile.exists()) {
                        serverTemplateFile.delete();
                    }
                }
            }
            //更新
            int res = projectTemplateService.updateProjectTemplateInfo(template);
            if (res > 0) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "项目模板信息修改成功！");
            } else {
                response = RestResponseUtil.createResponse(RestResponseCode.NOTE, "项目模板信息修改失败！");
            }
        } catch (Exception e) {
            log.error("修改项目模板信息异常", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "删除项目模板信息", notes = "删除项目模板信息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/deletebyid")
    public RestResponse<String> deleteProjectTemplateInfoByID(
            HttpServletRequest request,
            @ApiParam("模板ID") @RequestParam(name = "template_id") String temID) {
        RestResponse<String> response = null;
        try {
            wm_project_template projectTemplate = projectTemplateService.getProjectTemplateInfoByID(temID);
            int res = projectTemplateService.deleteProjectTemplateInfo(temID);
            if (res > 0) {
                //删除模板文件
                if (projectTemplate.getTemplate_path() != null) {
                    String filePath = request.getServletContext().getRealPath("/") + projectTemplate.getTemplate_path();
                    File templateFile = new File(filePath);
                    templateFile.delete();
                    templateFile.getParentFile().delete();//删除文件夹
                }
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "项目模板信息删除成功！");
            } else {
                response = RestResponseUtil.createResponse(RestResponseCode.FAIL, "项目模板信息删除失败！");
            }
        } catch (Exception e) {
            log.error("删除项目模板信息异常", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "模板文件上传", notes = "模板文件上传")
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/upfile", headers = "content-type=multipart/form-data")
    public RestResponse<String> insertTaskDeptCommonInfoByList(
            HttpServletRequest request,
            @ApiParam(value = "项目文件", required = true) @RequestParam(value = "file") MultipartFile templateFile,
            @ApiParam("模板ID") @RequestParam(value = "template_id") String temID
    ) {
        sys_login syslogin = (sys_login) request.getSession().getAttribute("syslogin");
        if (syslogin == null) {
            return RestResponseUtil.createResponse(RestResponseCode.EXPIRE, RestResponseCode.EXPIRE.getMsg());
        }
        RestResponse<String> response = null;
        try {
            String serverFilePath = request.getServletContext().getRealPath("/") + "upload/ky/template/" + temID;
            File src = new File(serverFilePath);
            if (!src.exists()) {
                src.mkdirs();
            }
            String url = "/upload/ky/template/" + temID + "/" + templateFile.getOriginalFilename();
            //在指定路径下创建一个新的文件
            File serverTemplateFile = new File(serverFilePath, templateFile.getOriginalFilename());
            //使用spring mvc的transferTo方法上传文件
            templateFile.transferTo(serverTemplateFile);

            //如果上传的模板文件名变化了，则删除老文件
            for (File file : src.listFiles()) {
                if (!file.getName().equals(templateFile.getOriginalFilename())) {
                    file.delete();
                }
            }

            int res = projectTemplateService.updateProjectTemplateFile(
                    temID,
                    templateFile.getOriginalFilename(),
                    MyDateUtils.getCurrentDate(MyDateUtils.DATE_PATTERN_SECOND)
                    ,
                    syslogin.getLogin_name(),
                    url);
            if (res > 0) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "模板文件上传成功！");
            } else {
                response = RestResponseUtil.createResponse(RestResponseCode.FAIL, "模板文件上传失败！");
            }
        } catch (Exception e) {
            log.error("模板文件上传异常", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "模板文件下载", notes = "模板文件下载")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/filedownload")
    public RestResponse<String> getDeptCheckListExport(
            HttpServletRequest request,
            HttpServletResponse httpServletResponse,
            @ApiParam("模板ID") @RequestParam(name = "template_id") String temID) {
        RestResponse<String> response = null;
        try {
            wm_project_template projectTemplate = projectTemplateService.getProjectTemplateInfoByID(temID);
            if (projectTemplate.getTemplate_path() != null) {
                String filePath = request.getServletContext().getRealPath("/") + projectTemplate.getTemplate_path();
                FileUpOrDownloadUtil.downloadFile(projectTemplate.getFilename(), filePath, httpServletResponse);
            } else {
                return RestResponseUtil.note("模板文件不存在！");
            }
        } catch (Exception e) {
            log.error("模板文件下载异常", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }
}
