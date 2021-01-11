package com.startest.wm.service;

import com.startest.wm.model.score.*;
import com.startest.wm.pojo.sys_user_finalscore;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-04-02 10:25
 * @描述 最终成绩操作服务接口
 **/
public interface SysFinalScoreService {

    /**
     * @Description: 插入用户最终成绩信息
     * @Param: [sysUserFinalscore] 用户最终成绩对象
     * @return: int
     **/
    int insertFinalScore(sys_user_finalscore sysUserFinalscore);

    /**
     * @Description: 更新评定结果
     * @Param: [id, pdjg]
     * @return: int
     **/
    int updateFinalScorePdjg(String id, String pdjg);

    /**
     * @Description: 删除本年份的所有用户成绩信息
     * @Param: [year deptID] 当前年份 部门ID
     * @return: int
     **/
    int deleteFinalScore(Integer year, String deptID);

    /**
     * @Description: 查询质量测评成绩评定结果
     * @Param: [calType, year, deptID, zb, rylb]计算方式 当前年份 部门ID  组别  人员类别
     * @return: java.util.List<com.startest.wm.model.score.ZlcpScoreModel>
     **/
    List<ZlcpScoreModel> queryZlcpScore(Integer calType, Integer year, String deptID, String zb, String rylb);

    /**
     * @Description: 查询工天测评成绩评定结果
     * @Param: [calType, year, deptID, zb, rylb]计算方式 当前年份 部门ID 组别  人员类别
     * @return: java.util.List<com.startest.wm.model.score.GtcpScoreModel>
     **/
    List<GtcpScoreModel> queryGtcpScore(Integer calType, Integer year, String deptID, String zb, String rylb);

    /**
     * @Description: 查询能力态度测评成绩评定结果
     * @Param: [calType, year, deptID, zb, rylb]计算方式 当前年份 部门ID 组别  人员类别
     * @return: java.util.List<com.startest.wm.model.score.NltdcpScoreModel>
     **/
    List<NltdcpScoreModel> queryNltdcpScore(Integer calType, Integer year, String deptID, String zb, String rylb);

    /**
     * @Description: 查询业务训练测评成绩评定结果
     * @Param: [calType, year, deptID, zb, rylb]计算方式 当前年份 部门ID 组别  人员类别
     * @return: java.util.List<com.startest.wm.model.score.YwxlcpScoreModel>
     **/
    List<YwxlcpScoreModel> queryYwxlcpScore(Integer calType, Integer year, String deptID, String zb, String rylb);

    /**
     * @Description: 查询综合测评成绩评定结果
     * @Param: [calType, year, deptID, zb, rylb]计算方式 当前年份 部门ID 组别  人员类别
     * @return: java.util.List<com.startest.wm.model.score.ZhcpScoreModel>
     **/
    List<ZhcpScoreModel> queryZhcpScore(Integer calType, Integer year, String deptID, String zb, String rylb);

    /**
     * @Description: 查询最终成绩评定结果
     * @Param: [year, deptID, zb, rylb]当前年份 部门ID 组别  人员类别
     * @return: java.util.List<com.startest.wm.pojo.sys_user_finalscore>
     **/
    List<sys_user_finalscore> queryFinalScore(Integer year, String deptID, String zb, String rylb);

    /**
     * @Description:计算考评成绩排名和评定结果
     * @Param: [year, deptID] 当前年份，部门ID
     * @return: java.util.List<com.startest.wm.pojo.sys_user_finalscore>
     **/
    List<sys_user_finalscore> calFinalScore(Integer year, String deptID);

    /**
     * @Description: 机关获取作业室列表
     * @Param: [year]
     * @return: java.util.List<com.startest.wm.pojo.sys_user_finalscore>
     **/
    List<sys_user_finalscore> getZysList(Integer year);

    /**
     * @Description: 获取作业室组别列表
     * @Param: [year, deptID]
     * @return: java.util.List<java.lang.String>
     **/
    List<String> getZysZbList(Integer year, String deptID);

    /**
     * @Description: 获取机关组别列表
     * @Param: [year, deptID]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     **/  
    List<String> getJgZbList(Integer year, String deptID);
}
