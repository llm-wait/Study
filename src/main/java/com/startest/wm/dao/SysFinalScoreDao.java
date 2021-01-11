package com.startest.wm.dao;

import com.startest.wm.model.score.*;
import com.startest.wm.pojo.sys_user_finalscore;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-04-02 10:13
 * @描述 最终成绩数据库映射
 **/
public interface SysFinalScoreDao {
    /**
     * @Description: 插入用户最终成绩信息
     * @Param: [sysUserFinalscore] 用户最终成绩对象
     * @return: int
     **/
    int insertFinalScore(sys_user_finalscore sysUserFinalscore);

    /**
     * @Description: 批量插入用户最终成绩信息
     * @Param: [sysUserFinalscoreList]
     * @return: int
     **/
    int insertFinalScoreMany(List<sys_user_finalscore> sysUserFinalscoreList);

    /**
     * @Description: 更新用户最终成绩信息
     * @Param: [sysUserFinalscoreList]
     * @return: int
     **/
    int updateFinalScore(sys_user_finalscore sysUserFinalscore);

    /**
     * @Description: 批量更新用户最终成绩信息
     * @Param: [sysUserFinalscoreList]
     * @return: int
     **/
    int updateFinalScoreMany(List<sys_user_finalscore> sysUserFinalscoreList);

    /**
     * @Description: 更新评定结果
     * @Param: [id, pdjg]
     * @return: int
     **/
    int updateFinalScorePdjg(@Param("id") String id,@Param("pdjg") String pdjg);

    /**
     * @Description: 删除指定年份的所有用户成绩信息
     * @Param: [year deptID] 指定年份 部门ID
     * @return: int
     **/
    int deleteFinalScore(@Param("year") Integer year,@Param("deptID") String deptID);

    /**
     * @Description: 获取指定年份，指定部门的小组平均总成绩
     * @Param: [year, deptID, zb]指定年份 部门ID
     * @return: double
     **/
    Double getAvgScoreXiaoZu(@Param("kpnf") Integer kpnf,@Param("dept_id") String dept_id,@Param("zb") String zb);

    /**
     * @Description: 查询质量测评成绩评定结果(全体人员)
     * @Param: [year, deptID, zb, rylb]当前年份 部门ID  组别  人员类别
     * @return: java.util.List<com.startest.wm.model.score.ZlcpScoreModel>
     **/
    List<ZlcpScoreModel> queryZlcpScore(@Param("kpnf") Integer year,@Param("dept_id") String deptID,@Param("zb") String zb,@Param("rylb") String rylb);

    /**
     * @Description: 查询工天测评成绩评定结果(全体人员)
     * @Param: [year, deptID, zb, rylb]当前年份 部门ID 组别  人员类别
     * @return: java.util.List<com.startest.wm.model.score.GtcpScoreModel>
     **/
    List<GtcpScoreModel> queryGtcpScore(@Param("kpnf")Integer year, @Param("dept_id")String deptID, @Param("zb")String zb, @Param("rylb")String rylb);

    /**
     * @Description: 查询能力态度测评成绩评定结果(全体人员)
     * @Param: [year, deptID, zb, rylb]当前年份 部门ID 组别  人员类别
     * @return: java.util.List<com.startest.wm.model.score.NltdcpScoreModel>
     **/
    List<NltdcpScoreModel> queryNltdcpScore(@Param("kpnf")Integer year, @Param("dept_id")String deptID, @Param("zb")String zb, @Param("rylb")String rylb);

    /**
     * @Description: 查询业务训练测评成绩评定结果(全体人员)
     * @Param: [year, deptID, zb, rylb]当前年份 部门ID 组别  人员类别
     * @return: java.util.List<com.startest.wm.model.score.YwxlcpScoreModel>
     **/
    List<YwxlcpScoreModel> queryYwxlcpScore(@Param("kpnf")Integer year, @Param("dept_id")String deptID, @Param("zb")String zb, @Param("rylb")String rylb);

    /**
     * @Description: 查询综合测评成绩评定结果(全体人员)
     * @Param: [year, deptID, zb, rylb]当前年份 部门ID 组别  人员类别
     * @return: java.util.List<com.startest.wm.model.score.ZhcpScoreModel>
     **/
    List<ZhcpScoreModel> queryZhcpScore(@Param("kpnf")Integer year, @Param("dept_id")String deptID, @Param("zb")String zb, @Param("rylb")String rylb);

    /**
     * @Description: 查询质量测评成绩评定结果(专业类别)
     * @Param: [year, deptID, zb, rylb]当前年份 部门ID  组别  人员类别
     * @return: java.util.List<com.startest.wm.model.score.ZlcpScoreModel>
     **/
    List<ZlcpScoreModel> queryZlcpScore2(@Param("kpnf")Integer year, @Param("dept_id")String deptID, @Param("zb")String zb, @Param("rylb")String rylb);

    /**
     * @Description: 查询工天测评成绩评定结果(专业类别)
     * @Param: [year, deptID, zb, rylb]当前年份 部门ID 组别  人员类别
     * @return: java.util.List<com.startest.wm.model.score.GtcpScoreModel>
     **/
    List<GtcpScoreModel> queryGtcpScore2(@Param("kpnf")Integer year, @Param("dept_id")String deptID, @Param("zb")String zb, @Param("rylb")String rylb);

    /**
     * @Description: 查询能力态度测评成绩评定结果(专业类别)
     * @Param: [year, deptID, zb, rylb]当前年份 部门ID 组别  人员类别
     * @return: java.util.List<com.startest.wm.model.score.NltdcpScoreModel>
     **/
    List<NltdcpScoreModel> queryNltdcpScore2(@Param("kpnf")Integer year, @Param("dept_id")String deptID, @Param("zb")String zb, @Param("rylb")String rylb);

    /**
     * @Description: 查询业务训练测评成绩评定结果(专业类别)
     * @Param: [year, deptID, zb, rylb]当前年份 部门ID 组别  人员类别
     * @return: java.util.List<com.startest.wm.model.score.YwxlcpScoreModel>
     **/
    List<YwxlcpScoreModel> queryYwxlcpScore2(@Param("kpnf")Integer year, @Param("dept_id")String deptID, @Param("zb")String zb, @Param("rylb")String rylb);

    /**
     * @Description: 查询综合测评成绩评定结果(专业类别)
     * @Param: [year, deptID, zb, rylb]当前年份 部门ID 组别  人员类别
     * @return: java.util.List<com.startest.wm.model.score.ZhcpScoreModel>
     **/
    List<ZhcpScoreModel> queryZhcpScore2(@Param("kpnf")Integer year, @Param("dept_id")String deptID, @Param("zb")String zb, @Param("rylb")String rylb);

    /**
     * @Description:计算考评成绩排名和评定结果
     * @Param: [year, deptID] 当前年份，部门ID
     * @return: java.util.List<com.startest.wm.pojo.sys_user_finalscore>
     **/
    List<sys_user_finalscore> calFinalScore(@Param("kpnf")Integer year, @Param("dept_id")String deptID);

    /**
     * @Description: 查询最终成绩评定结果
     * @Param: [year, deptID, zb, rylb]当前年份 部门ID 组别  人员类别
     * @return: java.util.List<com.startest.wm.pojo.sys_user_finalscore>
     **/
    List<sys_user_finalscore> queryFinalScore(@Param("kpnf")Integer year, @Param("dept_id")String deptID, @Param("zb")String zb, @Param("rylb")String rylb);

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
    List<String> getZysZbList(@Param("year") Integer year,@Param("deptID") String deptID);

    /**
     * @Description: 获取作业室人员类别
     * @Param: [year, deptID]
     * @return: java.util.List<java.lang.String>
     **/
    List<String> getZysRylbList(@Param("year")Integer year, @Param("deptID")String deptID);
}
