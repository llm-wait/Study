package com.startest.wm.controller;

import com.startest.wm.config.exception.FileOperationException;
import com.startest.wm.model.SysUserModel;
import com.startest.wm.model.UserSearchModel;
import com.startest.wm.model.yml.UserPhoto;
import com.startest.wm.pojo.sys_dept_station_user;
import com.startest.wm.pojo.sys_user;
import com.startest.wm.pojo.wm_task_distribution;
import com.startest.wm.service.SysDeptService;
import com.startest.wm.service.SysUserService;
import com.startest.wm.service.TaskDistributionService;
import com.startest.wm.utils.GlobalLoginInfoUtil;
import com.startest.wm.utils.UUIDGeneratorUtil;
import com.startest.wm.utils.UserExcelOperationUtil;
import com.startest.wm.utils.customresponse.RestResponse;
import com.startest.wm.utils.customresponse.RestResponseCode;
import com.startest.wm.utils.customresponse.RestResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-03-31 14:30
 * @描述 系统用户管理控制器
 **/
@Api(tags = "系统用户操作模块相关API")
@RestController
@RequestMapping("/sys/user/")
public class SysUserController {

    @Autowired
    SysUserService sysUserService;
    @Autowired
    SysDeptService sysDeptService;
    @Autowired
    TaskDistributionService taskDistributionService;
    @Autowired
    UserPhoto userPhoto;

    private static final Logger log = LoggerFactory.getLogger(SysDeptController.class);

    @ApiOperation("添加用户")
    @PostMapping(value = "addUser")
    @Transactional(propagation = Propagation.REQUIRED)
    public RestResponse<String> addUser(
            HttpServletRequest request,
            @ApiParam(value = "所在部门，参数传输部门ID", required = true) @RequestParam(name = "szbm") String szbm,
            @ApiParam(value = "真实姓名", required = true) @RequestParam(name = "user_name") String truename,
            @ApiParam(value = "性别", required = true) @RequestParam(name = "sex") String sex,
            @ApiParam(value = "民族", required = true) @RequestParam(name = "mz") String mz,
            @ApiParam(value = "出生日期", required = true) @RequestParam(name = "cssj") String cssj,
            @ApiParam(value = "籍贯", required = true) @RequestParam(name = "jg") String jg,
            @ApiParam(value = "来社时间", required = true) @RequestParam(name = "lssj") String lssj,
            @ApiParam(value = "技术职务（员工档级）", required = true) @RequestParam(name = "jszw") String jszw,
            @ApiParam(value = "定档时间") @RequestParam(name = "ddsj", required = false) String ddsj,
            @ApiParam(value = "军衔", required = true) @RequestParam(name = "jx") String jx,
            @ApiParam(value = "军衔时间") @RequestParam(name = "jxsj", required = false) String jxsj,
            @ApiParam(value = "所在岗位，参数传输岗位ID", required = true) @RequestParam(name = "szgw") String szgw,
            @ApiParam(value = "职称", required = true) @RequestParam(name = "zc") String zc,
            @ApiParam(value = "职称时间") @RequestParam(name = "zcsj", required = false) String zcsj,
            @ApiParam(value = "入伍时间") @RequestParam(name = "rwsj", required = false) String rwsj,
            @ApiParam(value = "婚姻状况", required = true) @RequestParam(name = "hyzk") String hyzk,
            @ApiParam(value = "政治面貌", required = true) @RequestParam(name = "zzmm") String zzmm,
            @ApiParam(value = "入党时间") @RequestParam(name = "rdsj", required = false) String rdsj,
            @ApiParam(value = "军官证号") @RequestParam(name = "jgzh", required = false) String jgzh,
            @ApiParam(value = "身份证号", required = true) @RequestParam(name = "sfzh") String sfzh,
            @ApiParam(value = "人员类别", required = true) @RequestParam(name = "rylb") String rylb,
            @ApiParam(value = "第一学历") @RequestParam(name = "dyxl", required = false) String dyxl,
            @ApiParam(value = "第一学历院校") @RequestParam(name = "dyxlyx", required = false) String dyxlyx,
            @ApiParam(value = "第一学历专业") @RequestParam(name = "dyxlzy", required = false) String dyxlzy,
            @ApiParam(value = "第一学历毕业时间") @RequestParam(name = "dyxlbysj", required = false) String dyxlbysj,
            @ApiParam(value = "第二学历") @RequestParam(name = "dexl", required = false) String dexl,
            @ApiParam(value = "第二学历院校") @RequestParam(name = "dexlyx", required = false) String dexlyx,
            @ApiParam(value = "第二学历专业") @RequestParam(name = "dexlzy", required = false) String dexlzy,
            @ApiParam(value = "第二学历毕业时间") @RequestParam(name = "dexlbysj", required = false) String dexlbysj,
            @ApiParam(value = "头像") @RequestParam(name = "file", required = false) MultipartFile photoFile) {
        RestResponse response = null;
        try {
            sys_user sysUser = new sys_user();
            sysUser.setUser_id(UUIDGeneratorUtil.getUUIDWithoutLineAndToLower());
            sysUser.setUser_name(truename);
            sysUser.setSex(sex);
            sysUser.setMz(mz);
            sysUser.setCssj(cssj);
            sysUser.setJg(jg);
            sysUser.setLssj(lssj);
            sysUser.setJszw(jszw);
            sysUser.setDdsj(ddsj);
            sysUser.setJx(jx);
            sysUser.setJxsj(jxsj);
            sysUser.setZc(zc);
            sysUser.setZcsj(zcsj);
            sysUser.setRwsj(rwsj);
            sysUser.setHyzk(hyzk);
            sysUser.setZzmm(zzmm);
            sysUser.setRdsj(rdsj);
            sysUser.setJgzh(jgzh);
            sysUser.setSfzh(sfzh);
            sysUser.setRylb(rylb);
            sysUser.setDyxl(dyxl);
            sysUser.setDyxlyx(dyxlyx);
            sysUser.setDyxlzy(dyxlzy);
            sysUser.setDyxlbysj(dyxlbysj);
            sysUser.setDexl(dexl);
            sysUser.setDexlyx(dexlyx);
            sysUser.setDexlzy(dexlzy);
            sysUser.setDexlbysj(dexlbysj);
            sysUser.setSfjy(0);//默认为启用状态

            //用户头像处理
            if (photoFile != null) {
                File serverPhotoFile = null;
                try {
                    //头像在服务器上的实际存储文件夹路径
                    String serverPhotoPath = request.getServletContext().getRealPath("/") + "upload/user/image";
                    File src = new File(serverPhotoPath);
                    if (!src.exists()) {
                        src.mkdirs();
                    }
                    //获取头像文件类型
                    String fileType = photoFile.getOriginalFilename().substring(photoFile.getOriginalFilename().lastIndexOf("."));
                    //头像文件以用户ID进行重命名命名，防止文件同名
                    String fileName = sysUser.getUser_id() + fileType;
                    String url = "/upload/user/image/" + fileName;
                    sysUser.setPhoto(url);//头像在服务器上的http路径，用于页面回显，数据库存储此路径

                    //在指定路径下创建一个新的文件
                    serverPhotoFile = new File(serverPhotoPath, fileName);
                    //使用spring mvc的transferTo方法上传文件
                    photoFile.transferTo(serverPhotoFile);
                } catch (Exception e) {
                    if (serverPhotoFile != null && serverPhotoFile.exists()) {
                        serverPhotoFile.delete();
                    }
                }
            }

            //部门岗位人员关系表中添加记录
            sys_dept_station_user sysDeptStationUser = new sys_dept_station_user();
            sysDeptStationUser.setId(UUIDGeneratorUtil.getUUIDWithoutLineAndToLower());
            sysDeptStationUser.setDept_id(szbm);
            sysDeptStationUser.setStation_id(szgw);
            sysDeptStationUser.setUser_id(sysUser.getUser_id());
            int count = sysDeptService.insertDeptStationUserReal(sysDeptStationUser);
            if (count > 0) {
                int res = sysUserService.insertUser(sysUser);
                if (res > 0) {
                    response = RestResponseUtil.success("添加成功！");
                } else {
                    response = RestResponseUtil.note("添加失败！");
                }
            } else {
                response = RestResponseUtil.note("添加失败！");
            }
        } catch (Exception e) {
            log.error("添加用户失败：", e);
            response = RestResponseUtil.note("添加失败！");
        }
        return response;
    }


    @ApiOperation("更新用户")
    @PostMapping(value = "updateUser")
    @Transactional(propagation = Propagation.REQUIRED)
    public RestResponse<String> updateUser(
            HttpServletRequest request,
            @ApiParam(value = "用户ID", required = true) @RequestParam(name = "user_id") String id,
            @ApiParam(value = "真实姓名", required = true) @RequestParam(name = "user_name") String truename,
            @ApiParam(value = "性别", required = true) @RequestParam(name = "sex") String sex,
            @ApiParam(value = "民族", required = true) @RequestParam(name = "mz") String mz,
            @ApiParam(value = "出生日期", required = true) @RequestParam(name = "cssj") String cssj,
            @ApiParam(value = "籍贯", required = true) @RequestParam(name = "jg") String jg,
            @ApiParam(value = "来社时间", required = true) @RequestParam(name = "lssj") String lssj,
            @ApiParam(value = "技术职务（员工档级）", required = true) @RequestParam(name = "jszw") String jszw,
            @ApiParam(value = "定档时间") @RequestParam(name = "ddsj", required = false) String ddsj,
            @ApiParam(value = "军衔", required = true) @RequestParam(name = "jx") String jx,
            @ApiParam(value = "军衔时间") @RequestParam(name = "jxsj", required = false) String jxsj,
            @ApiParam(value = "所在岗位，参数传输岗位ID", required = true) @RequestParam(name = "szgw") String szgw,
            @ApiParam(value = "职称", required = true) @RequestParam(name = "zc") String zc,
            @ApiParam(value = "职称时间") @RequestParam(name = "zcsj", required = false) String zcsj,
            @ApiParam(value = "入伍时间") @RequestParam(name = "rwsj", required = false) String rwsj,
            @ApiParam(value = "婚姻状况", required = true) @RequestParam(name = "hyzk") String hyzk,
            @ApiParam(value = "政治面貌", required = true) @RequestParam(name = "zzmm") String zzmm,
            @ApiParam(value = "入党时间") @RequestParam(name = "rdsj", required = false) String rdsj,
            @ApiParam(value = "军官证号") @RequestParam(name = "jgzh", required = false) String jgzh,
            @ApiParam(value = "身份证号", required = true) @RequestParam(name = "sfzh") String sfzh,
            @ApiParam(value = "人员类别", required = true) @RequestParam(name = "rylb") String rylb,
            @ApiParam(value = "第一学历") @RequestParam(name = "dyxl", required = false) String dyxl,
            @ApiParam(value = "第一学历院校") @RequestParam(name = "dyxlyx", required = false) String dyxlyx,
            @ApiParam(value = "第一学历专业") @RequestParam(name = "dyxlzy", required = false) String dyxlzy,
            @ApiParam(value = "第一学历毕业时间") @RequestParam(name = "dyxlbysj", required = false) String dyxlbysj,
            @ApiParam(value = "第二学历") @RequestParam(name = "dexl", required = false) String dexl,
            @ApiParam(value = "第二学历院校") @RequestParam(name = "dexlyx", required = false) String dexlyx,
            @ApiParam(value = "第二学历专业") @RequestParam(name = "dexlzy", required = false) String dexlzy,
            @ApiParam(value = "第二学历毕业时间") @RequestParam(name = "dexlbysj", required = false) String dexlbysj,
            @ApiParam(value = "头像") @RequestParam(name = "file", required = false) MultipartFile photoFile) {
        RestResponse response = null;
        try {
            sys_user sysUser = sysUserService.queryByUserID(id);
            sysUser.setUser_name(truename);
            sysUser.setSex(sex);
            sysUser.setMz(mz);
            sysUser.setCssj(cssj);
            sysUser.setJg(jg);
            sysUser.setLssj(lssj);
            sysUser.setJszw(jszw);
            sysUser.setDdsj(ddsj);
            sysUser.setJx(jx);
            sysUser.setJxsj(jxsj);
            sysUser.setZc(zc);
            sysUser.setZcsj(zcsj);
            sysUser.setRwsj(rwsj);
            sysUser.setHyzk(hyzk);
            sysUser.setZzmm(zzmm);
            sysUser.setRdsj(rdsj);
            sysUser.setJgzh(jgzh);
            sysUser.setSfzh(sfzh);
            sysUser.setRylb(rylb);
            sysUser.setDyxl(dyxl);
            sysUser.setDyxlyx(dyxlyx);
            sysUser.setDyxlzy(dyxlzy);
            sysUser.setDyxlbysj(dyxlbysj);
            sysUser.setDexl(dexl);
            sysUser.setDexlyx(dexlyx);
            sysUser.setDexlzy(dexlzy);
            sysUser.setDexlbysj(dexlbysj);

            //用户头像处理
            if (photoFile != null) {
                File serverPhotoFile = null;
                try {
                    //头像在服务器上的实际存储文件夹路径
                    String serverPhotoPath = request.getServletContext().getRealPath("/") + "upload/user/image";
                    File src = new File(serverPhotoPath);
                    if (!src.exists()) {
                        src.mkdirs();
                    }
                    //获取头像文件类型
                    String fileType = photoFile.getOriginalFilename().substring(photoFile.getOriginalFilename().lastIndexOf("."));
                    //头像文件以用户ID进行重命名命名，防止文件同名
                    String fileName = sysUser.getUser_id() + fileType;
                    String url = "/upload/user/image/" + fileName;
                    sysUser.setPhoto(url);//头像在服务器上的http路径，用于页面回显，数据库存储此路径

                    //在指定路径下创建一个新的文件
                    serverPhotoFile = new File(serverPhotoPath, fileName);
                    //使用spring mvc的transferTo方法上传文件
                    photoFile.transferTo(serverPhotoFile);
                } catch (Exception e) {
                    log.error("更新用户头像错误：" + e);
                    e.printStackTrace();
                }
            }

            sys_dept_station_user sysDeptStationUser = sysDeptService.getSysDeptStationUser(sysUser.getUser_id());
            //判断部门岗位是否做了修改，若修改了，则先更新部门岗位人员关系表记录
            if (!sysDeptStationUser.getStation_id().equals(szgw)) {
                sysDeptStationUser.setStation_id(szgw);
                int res = sysDeptService.updateDeptStationUserReal(sysDeptStationUser);
                if (res > 0) {
                    if (sysUserService.updateUser(sysUser) > 0) {
                        response = RestResponseUtil.success("更新成功！");
                    } else {
                        response = RestResponseUtil.note("更新失败！");
                    }
                } else {
                    response = RestResponseUtil.note("更新失败！");
                }
            } else {
                if (sysUserService.updateUser(sysUser) > 0) {
                    response = RestResponseUtil.success("更新成功！");
                } else {
                    response = RestResponseUtil.note("更新失败！");
                }
            }
        } catch (Exception e) {
            log.error("更新用户失败！原因：", e);
            response = RestResponseUtil.note("更新失败！");
        }
        return response;
    }


    @ApiOperation(value = "批量导入人员", notes = "部门ID")
    @PostMapping(value = "importUsers", headers = "content-type=multipart/form-data")
    @Transactional(propagation = Propagation.REQUIRED)
    public RestResponse<String> addUserMany(
            @ApiParam("用户文件") @RequestParam(value = "file") MultipartFile file) {
        RestResponse<String> response = null;
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            List<SysUserModel> sysUserModelList = UserExcelOperationUtil.readExcelToUserList(inputStream, file.getOriginalFilename());
            if (sysUserModelList != null && sysUserModelList.size() > 0) {
                if (sysUserModelList.size() > 0) {
                    int count = sysUserService.insertUserMany(sysUserModelList, true);
                    if (count == sysUserModelList.size()) {
                        response = RestResponseUtil.success("批量导入成功！");
                    } else {
                        response = RestResponseUtil.note("批量导入失败！");
                    }
                } else {
                    response = RestResponseUtil.note("登录名都已经存在！");
                }
            } else {
                response = RestResponseUtil.note("资料数据为空！");
            }
        } catch (IOException e) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ex) {
                    log.error("系统异常！原因：", ex);
                    response = RestResponseUtil.note("输出流无法关闭");
                }
            }
        } catch (FileOperationException e) {
            log.error("系统异常！原因：", e);
            response = RestResponseUtil.note(e.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ex) {
                    log.error("系统异常！原因：", ex);
                    response = RestResponseUtil.note("输出流无法关闭");
                }
            }
        }
        return response;
    }


    @ApiOperation("导出人员列表")
    @PostMapping(value = "exportUsers")
    public RestResponse<Object> exportUsers(
            @ApiParam(value = "用户ID字符串") @RequestParam(name = "list") List<String> list,
            HttpServletResponse httpServletResponse
    ) {
        RestResponse response = null;
        try {
            if (null != list && list.size() > 0) {
                List<SysUserModel> sysUserModelList = sysUserService.queryUserModelByIDs(list);
                UserExcelOperationUtil.exportUserListToExcel(sysUserModelList, httpServletResponse);
            } else {
                response = RestResponseUtil.note("请先选择要导出的人员！");
            }
        } catch (IOException e) {
            log.error("导出人员异常：", e);
            response = RestResponseUtil.note("导出人员列表失败");
        }
        return response;
    }


    @ApiOperation("禁用/启用用户")
    @PostMapping("updateUserState")
    public RestResponse<String> updateUserState(
            @ApiParam(value = "用户ID", required = true) @RequestParam(value = "user_id") String userID,
            @ApiParam(value = "用户状态0启用，1禁用", required = true) @RequestParam(value = "sfjy") int userState
    ) {
        RestResponse response = null;
        try {
            int res = sysUserService.updateUserState(userID, userState);
            if (res > 0) {
                response = RestResponseUtil.success();
            } else {
                response = RestResponseUtil.note("设置失败！");
            }
        } catch (Exception e) {
            log.error("系统异常！原因：", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation("自定义条件获取所有用户")
    @PostMapping(value = "getUserListByDefine")
    public RestResponse<List<SysUserModel>> getUserListByDefine(
            HttpServletRequest request,
            @ApiParam(value = "用户对象") @RequestBody UserSearchModel userSearchModel
    ) {
        RestResponse response = null;
        try {
            boolean isAdmin = GlobalLoginInfoUtil.isAdminLogin(request);
            String deptID = GlobalLoginInfoUtil.getLoginUserInfo(request).getDept_id();
            List<SysUserModel> sysUserModelList = sysDeptService.getDeptUserModelList(deptID, isAdmin);
            Iterator<SysUserModel> iterator = sysUserModelList.iterator();
            while (iterator.hasNext()) {
                SysUserModel model = iterator.next();
                if (userSearchModel.getUser_name() != null && !"".equals(userSearchModel.getUser_name())) {
                    if (!model.getUser_name().contains(userSearchModel.getUser_name())) {
                        iterator.remove();
                        continue;
                    }
                }
                if (userSearchModel.getRylb() != null && !"".equals(userSearchModel.getRylb())) {
                    if (!model.getRylb().equals(userSearchModel.getRylb())) {
                        iterator.remove();
                        continue;
                    }
                }
                if (userSearchModel.getSfjy() != null && !"".equals(userSearchModel.getSfjy())) {
                    if (!model.getSfjy().equals(userSearchModel.getSfjy())) {
                        iterator.remove();
                        continue;
                    }
                }
            }
            response = RestResponseUtil.createResponse(RestResponseCode.SUCCESS, sysUserModelList);
        } catch (Exception e) {
            log.error("系统异常！原因：", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }


    @ApiOperation("根据用户ID删除用户")
    @PostMapping(value = "deleteUserByID")
    @Transactional(propagation = Propagation.REQUIRED)
    public RestResponse<sys_user> deleteUserByID(
            @ApiParam(value = "用户ID", required = true) @RequestParam(value = "user_id") String userID
    ) {
        RestResponse response = null;
        try {
            wm_task_distribution taskDistribution = new wm_task_distribution();
            taskDistribution.setUser_id(userID);
            List<wm_task_distribution> wmTaskDistributionList = taskDistributionService.selectTaskDistribute(taskDistribution);
            if (wmTaskDistributionList != null && wmTaskDistributionList.size() > 0) {
                response = RestResponseUtil.note("此人员已经被分配任务，无法删除！");
                return response;
            }
            sys_user sysUser = sysUserService.queryByUserID(userID);
            int res = sysUserService.deleteByUserID(userID);
            if (res > 0) {
                //删除已有部门岗位人员关系记录
                int res1 = sysDeptService.deleteDeptStationUserReal2(sysUser.getUser_id());
                if (res1 > 0) {
                    //删除用户头像
                    if (sysUser.getPhoto() != null && !"".equals(sysUser.getPhoto())) {
                        File userPhotoFile = new File(sysUser.getPhoto());
                        if (userPhotoFile != null && userPhotoFile.exists()) {
                            userPhotoFile.delete();
                        }
                    }
                    response = RestResponseUtil.success("删除成功");
                } else {
                    response = RestResponseUtil.note("删除失败！");
                }
            } else {
                response = RestResponseUtil.note("删除失败！");
            }
        } catch (Exception e) {
            log.error("系统异常！原因：", e);
            response = RestResponseUtil.note("系统异常！");
        }
        return response;
    }
}
