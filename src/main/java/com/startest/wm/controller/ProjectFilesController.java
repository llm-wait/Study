package com.startest.wm.controller;

import com.startest.wm.pojo.wm_project_files;
import com.startest.wm.service.ProjectFilesService;
import com.startest.wm.utils.FileUpOrDownloadUtil;
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
import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-06-29-13:47
 * @描述 项目文件逻辑接口
 */
@Controller
@Api(tags = "科研项目文件信息业务逻辑API")
@RequestMapping("/task/projectfiles")
public class ProjectFilesController {

    private static final Logger log = LoggerFactory.getLogger(ProjectFilesController.class);

    @Autowired
    ProjectFilesService projectFilesService;

    @ApiOperation(value = "获取项目文件信息列表", notes = "获取项目文件信息列表")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/select")
    public RestResponse<List<wm_project_files>> selectProjectFilesInfoList(
            @ApiParam("文档ID") @RequestParam(name = "file_id", required = false) String fileID,
            @ApiParam("项目ID") @RequestParam(name = "project_id", required = false) String proID,
            @ApiParam("文档名称") @RequestParam(name = "file_name", required = false) String fileName,
            @ApiParam("文档类型") @RequestParam(name = "file_type", required = false) String fileType) {
        RestResponse<List<wm_project_files>> response = null;
        try {
            wm_project_files files = new wm_project_files();
            files.setFile_id(fileID);
            files.setProject_id(proID);
            files.setFile_name(fileName);
            files.setFile_type(fileType);
            List<wm_project_files> filesList = projectFilesService.getProjectFilesInfoList(files);
            response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, filesList);
        } catch (Exception e) {
            log.error("获取项目文件信息列表异常", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "添加项目文件信息", notes = "添加项目文件信息")
    @RequestMapping(method = RequestMethod.POST, value = "/add", headers = "content-type=multipart/form-data")
    @ResponseBody
    public RestResponse<String> addProjectFilesInfo(
            @ApiParam("项目ID") @RequestParam(value = "project_id") String proID,
            @ApiParam("文件描述") @RequestParam(value = "file_description", required = false) String fileDes,
            @ApiParam("备注") @RequestParam(value = "remark", required = false) String remark,
            @ApiParam("文档类型") @RequestParam(value = "file_type", required = false) String fileType,
            @ApiParam("项目文件") @RequestParam(value = "file") MultipartFile file,
            HttpServletRequest request) {
        RestResponse<String> response = null;
        try {
            wm_project_files proFile = new wm_project_files();
            proFile.setFile_id(UUIDGeneratorUtil.getUUIDWithoutLineAndToUpper());
            proFile.setProject_id(proID);
            proFile.setFile_type(fileType);
            proFile.setFile_description(fileDes);
            proFile.setRemark(remark);

            //模板文件处理
            if (file != null) {
                File serverDocFile = null;
                try {
                    //模板在服务器上的实际存储文件夹路径
                    String serverFilePath = request.getServletContext().getRealPath("/")
                            + "upload/ky/project/"
                            + fileType
                            + "/"
                            + proFile.getFile_id();
                    File src = new File(serverFilePath);
                    if (!src.exists()) {
                        src.mkdirs();
                    }
                    String url = "/upload/ky/project/"
                            + fileType
                            + "/"
                            + proFile.getFile_id()
                            + "/"
                            + file.getOriginalFilename();
                    proFile.setFile_name(file.getOriginalFilename());//模板名称，也即是文件名称
                    proFile.setFile_path(url);//模板在服务器上的http路径，用于页面回显，数据库存储此路径

                    //在指定路径下创建一个新的文件
                    serverDocFile = new File(serverFilePath, file.getOriginalFilename());
                    //使用spring mvc的transferTo方法上传文件
                    file.transferTo(serverDocFile);
                } catch (Exception e) {
                    if (serverDocFile != null && serverDocFile.exists()) {
                        serverDocFile.delete();
                    }
                }
            }
            //添加记录
            int res = projectFilesService.insertProjectFilesInfo(proFile);
            if (res > 0) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "添加项目文件添加成功！");
            } else {
                response = RestResponseUtil.createResponse(RestResponseCode.NOTE, "添加项目文件添加失败！");
            }
        } catch (Exception e) {
            log.error("添加项目文件信息异常", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "修改项目文件", notes = "修改项目文件")
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/update", headers = "content-type=multipart/form-data")
    public RestResponse<String> insertTaskDeptCommonInfoByList(
            @ApiParam("项目文件") @RequestParam(value = "file") MultipartFile file,
            @ApiParam("文件ID") @RequestParam(value = "file_id") String fileID,
            @ApiParam("文件类型") @RequestParam(value = "file_type") String fileType,
            HttpServletRequest request) {
        RestResponse<String> response = null;
        try {
            wm_project_files proFile = new wm_project_files();
            proFile.setFile_id(fileID);
            proFile.setFile_type(fileType);

            //模板文件处理
            if (file != null) {
                File serverDocFile = null;
                try {
                    //模板在服务器上的实际存储文件夹路径
                    String serverFilePath = request.getServletContext().getRealPath("/")
                            + "upload/ky/project/"
                            + fileType
                            + "/"
                            + proFile.getFile_id();
                    File src = new File(serverFilePath);
                    if (!src.exists()) {
                        src.mkdirs();
                    }
                    String url = "/upload/ky/project/"
                            + fileType
                            + "/"
                            + proFile.getFile_id()
                            + "/"
                            + file.getOriginalFilename();
                    proFile.setFile_name(file.getOriginalFilename());//模板文件名称为上传文件名称
                    proFile.setFile_path(url);//模板在服务器上的http路径，用于页面回显，数据库存储此路径

                    //在指定路径下创建一个新的文件
                    serverDocFile = new File(serverFilePath, file.getOriginalFilename());
                    //使用spring mvc的transferTo方法上传文件
                    file.transferTo(serverDocFile);

                    //如果上传的模板文件名变化了，则删除老文件
                    for (File f : src.listFiles()) {
                        if (!f.getName().equals(file.getOriginalFilename())) {
                            f.delete();
                        }
                    }
                } catch (Exception e) {
                    if (serverDocFile != null && serverDocFile.exists()) {
                        serverDocFile.delete();
                    }
                }
            }
            //更新
            int res = projectFilesService.updateProjectFilesPath(proFile);
            if (res > 0) {
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "项目文件上传成功！");
            } else {
                response = RestResponseUtil.createResponse(RestResponseCode.NOTE, "项目文件上传失败！");
            }
        } catch (Exception e) {
            log.error("修改项目文件异常", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "删除项目文件信息", notes = "删除项目文件信息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/delete")
    @Transactional(propagation = Propagation.REQUIRED)
    public RestResponse<String> deleteProjectFilesInfoByID(
            HttpServletRequest request,
            @ApiParam("文档ID") @RequestParam(name = "file_id", required = false) String fileID) {
        RestResponse<String> response = null;
        try {
            wm_project_files proFile = projectFilesService.getProjectFilesInfoByID(fileID);
            int res = projectFilesService.deleteProjectFilesInfo(fileID);
            if (res > 0) {
                //删除模板文件
                if (proFile.getFile_path() != null) {
                    String filePath = request.getServletContext().getRealPath("/") + proFile.getFile_path();
                    File file = new File(filePath);
                    file.delete();
                    file.getParentFile().delete();//删除文件夹
                }
                response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "项目文件删除成功！");
            } else {
                response = RestResponseUtil.createResponse(RestResponseCode.FAIL, "项目文件删除失败！");
            }
        } catch (Exception e) {
            log.error("删除项目文件信息异常", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "预览项目文件信息", notes = "预览项目文件信息")
    @RequestMapping(method = RequestMethod.GET, value = "/preview")
    @ResponseBody
    public RestResponse<wm_project_files> getProjectFilesInfoByID(
            @ApiParam("文档ID") @RequestParam(name = "file_id") String fileID) {
        RestResponse<wm_project_files> response = null;
        try {
            wm_project_files projectFile = projectFilesService.getProjectFilesInfoByID(fileID);
            response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, projectFile);
        } catch (Exception e) {
            log.error("通过ID获取项目文件信息异常", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation(value = "项目文件下载", notes = "项目文件下载")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/download")
    public RestResponse<String> getDeptCheckListExport(
            HttpServletRequest request,
            HttpServletResponse httpServletResponse,
            @ApiParam("文档ID") @RequestParam(name = "file_id") String fileID) {
        RestResponse<String> response = null;
        try {
            wm_project_files proFile = projectFilesService.getProjectFilesInfoByID(fileID);
            String filePath = request.getServletContext().getRealPath("/") + proFile.getFile_path();
            FileUpOrDownloadUtil.downloadFile(proFile.getFile_name(), filePath, httpServletResponse);
            response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "下载完成！");
        } catch (Exception e) {
            log.error("项目文件下载异常", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }
}
