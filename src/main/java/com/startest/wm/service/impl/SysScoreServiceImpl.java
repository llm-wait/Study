package com.startest.wm.service.impl;

import com.startest.wm.dao.SysDeptDao;
import com.startest.wm.dao.SysFinalScoreDao;
import com.startest.wm.dao.SysScoreDao;
import com.startest.wm.pojo.sys_score_setting;
import com.startest.wm.pojo.sys_user_finalscore;
import com.startest.wm.pojo.sys_user_score;
import com.startest.wm.service.SysScoreService;
import com.startest.wm.service.SysScoreSettingService;
import com.startest.wm.service.TaskChartCalculationHelperService;
import com.startest.wm.utils.UUIDGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-04-02 10:33
 * @描述 系统成绩操作服务接口实现类
 **/
@Service
public class SysScoreServiceImpl implements SysScoreService {

    @Autowired
    SysScoreDao sysScoreDao;
    @Autowired
    SysDeptDao sysDeptDao;
    @Autowired
    SysFinalScoreDao sysFinalScoreDao;
    @Autowired
    SysScoreSettingService sysScoreSettingService;
    @Autowired
    TaskChartCalculationHelperService calculationHelperService;

    /*********************************************考评信息录入接口*******************************************/

    @Override
    public int add(sys_user_score sysUserScore) {
        return sysScoreDao.add(sysUserScore);
    }

    @Override
    public int delete(Integer year, String userID) {
        return sysScoreDao.delete(year, userID);
    }

    @Override
    public int update(sys_user_score sysUserScore) {
        return sysScoreDao.update(sysUserScore);
    }

    @Override
    public sys_user_score findByUserID(Integer year, String userID) {
        return sysScoreDao.findByUserID(year, userID);
    }

    @Override
    public List<sys_user_score> findByDeptID(Integer year, String deptID) {
        return sysScoreDao.findByDeptID(year, deptID);
    }

    @Override
    public List<sys_user_score> findMany(Integer year, List<String> listIds) {
        return sysScoreDao.findMany(year, listIds);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean saveToFinalScore(Integer year, String deptID, List<sys_user_score> sysUserScoreList) {

        /*
         * 1、成绩存档之前先删除已有的成绩
         * */
        int res = sysFinalScoreDao.deleteFinalScore(year, deptID);

        /*
         * 2、考评成绩信息存入考评成绩最终表，此时并未计算排名和考评结果
         * */
        boolean saveResult = false;
        sys_score_setting sysScoreSetting = sysScoreSettingService.queryScoreSettingByYear(year);
        List<sys_user_finalscore> finalscoreList = new ArrayList<>();
        for (sys_user_score sysUserScore : sysUserScoreList) {
            sys_user_finalscore finalscore = new sys_user_finalscore();
            finalscore.setId(UUIDGeneratorUtil.getUUID());//唯一ID
            finalscore.setKpnf(sysUserScore.getKpnf());//考评年份
            finalscore.setUser_id(sysUserScore.getUser_id());//用户ID
            finalscore.setUser_name(sysUserScore.getUser_name());//用户名称
            finalscore.setDept_id(sysUserScore.getDept_id());//部门ID
            finalscore.setDept_name(sysUserScore.getDept_name());//部门名称
            finalscore.setZb(sysUserScore.getSzxz());//所在小组
            finalscore.setStation_name(sysUserScore.getStation_name());//岗位名称
            finalscore.setSfzz(sysUserScore.getSfzz());//是否组长
            finalscore.setZb(sysUserScore.getZb());//组别
            finalscore.setRylb(sysUserScore.getRylb());//人员类别
            finalscore.setSfcykp(sysUserScore.getSfcykp());//是否参与考评
            if (finalscore.getSfcykp().equals("是")) {
                //todo 合格率需要改成生产环境
                double hgl = 80;
                //生产环境中使用
                //double hgl=calculationHelperService.GetUserOneYearQualified(sysUserScore.getUser_id(),sysUserScore.getKpnf().toString());
                finalscore.setHgl(hgl);//合格率
                double hgldf = hgl / 100 * sysScoreSetting.getHgl();
                finalscore.setHgldf(new BigDecimal(hgldf).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue());//合格率得分

                //todo 优秀率需要改成生产环境
                double yxl = 80;
                //生产环境中使用
                //double yxl=calculationHelperService.GetUserOneYearExcellent(sysUserScore.getUser_id(),sysUserScore.getKpnf().toString());
                finalscore.setYxl(yxl);//优秀率
                double yxldf = yxl / 100 * sysScoreSetting.getYxl();
                finalscore.setYxldf(new BigDecimal(yxldf).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue());//优秀率得分

                //todo 优秀工天需要改成生产环境
                double yxgt = 100;
                //生产环境中使用
                //double yxgt=calculationHelperService.GetUserOneYearExcellentDays(sysUserScore.getUser_id(),sysUserScore.getKpnf().toString());
                finalscore.setYxgt(yxgt);//优秀工天
                //todo 部门平均工天需要改成生产环境
                double deptAvgGt = 100;
                //生产环境中使用
                //用户id从用户成绩表中获取
                List<String> deptUserIDList = new ArrayList<>();
                for (sys_user_score userScore : sysUserScoreList) {
                    deptUserIDList.add(userScore.getUser_id());
                }
                //double deptAvgGt=calculationHelperService.GetDeptOneYearAverageDays(deptUserIDList,sysUserScore.getKpnf().toString());
                double yxgtb = yxgt / (deptAvgGt * 2);
                finalscore.setYxgtb(new BigDecimal(yxgtb).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue());//优秀工天比:优秀工天除以（单位平均工天的2倍）
                double yxgtbdf = yxgtb * sysScoreSetting.getYxgtb();
                finalscore.setYxgtbdf(new BigDecimal(yxgtbdf).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue());//优秀工天比得分：最高为5分，高于5分按5分计算

                //todo 改成图数量主需要改成生产环境
                double gctslz = 0;
                //生产环境中使用
                //double gctslz=calculationHelperService.getUserChartEditProductCount(sysUserScore.getUser_id(),sysUserScore.getKpnf().toString(),1,0);
                finalscore.setGctzsl(gctslz);//改成图数量主
                //todo 改成图数量次需要改成生产环境
                double gctslc = 1;
                //生产环境中使用
                //double gctslc=calculationHelperService.getUserChartEditProductCount(sysUserScore.getUser_id(),sysUserScore.getKpnf().toString(),1,1);
                finalscore.setGctcsl(gctslc);//改成图数量次
                finalscore.setGctsl(finalscore.getGctzsl() + finalscore.getGctcsl());//改成图数量
                double gctkf = finalscore.getGctzsl() * sysScoreSetting.getGctz() + finalscore.getGctcsl() * sysScoreSetting.getGctc();
                finalscore.setGctkf(new BigDecimal(gctkf).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue());//改成图扣分

                //todo 技术修改数量主需要改成生产环境
                double jsxgslz = 1;
                //生产环境中使用
                //double jsxgslz=calculationHelperService.getUserChartEditProductCount(sysUserScore.getUser_id(),sysUserScore.getKpnf().toString(),0,0);
                finalscore.setJsxgzsl(jsxgslz);//技术修改数量主
                //todo 技术修改数量次需要改成生产环境
                double jsxgslc = 0;
                //生产环境中使用
                //double jsxgslc=calculationHelperService.getUserChartEditProductCount(sysUserScore.getUser_id(),sysUserScore.getKpnf().toString(),0,1);
                finalscore.setJsxgcsl(jsxgslc);//技术修改数量次
                finalscore.setJsxgsl(finalscore.getJsxgzsl() + finalscore.getJsxgcsl());//技术修改数量
                double jsxgkf = finalscore.getJsxgzsl() * sysScoreSetting.getJsxgz() + finalscore.getJsxgcsl() * sysScoreSetting.getJsxgc();
                finalscore.setJsxgkf(new BigDecimal(jsxgkf).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue());//技术修改扣分

                double zlzdf = finalscore.getHgldf() + finalscore.getYxldf() + finalscore.getYxgtbdf() - finalscore.getGctkf() - finalscore.getJsxgkf();
                if (zlzdf >= 35) {
                    zlzdf = 35;
                }
                if (zlzdf < 0) {
                    zlzdf = 0;
                }
                finalscore.setZlzdf(new BigDecimal(zlzdf).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue());//质量总得分，最高35分

                finalscore.setEdgt(sysUserScore.getEdgt());//额定工天
                //todo 工天需要改成生产环境
                double gt = 220;
                //生产环境中使用
                //double gt=calculationHelperService.GetUserOneYearAllWorkDays(sysUserScore.getUser_id(),sysUserScore.getKpnf().toString());
                finalscore.setGt(gt);//工天
                double gtdf = 0;
                if (gt >= sysUserScore.getEdgt()) {
                    gtdf = 18.9 + (gt - sysUserScore.getEdgt()) * 0.035;
                    gtdf = gtdf >= 31.5 ? 31.5 : gtdf;
                }
                finalscore.setGtdf(new BigDecimal(gtdf).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue());//工天得分，最高为31.5
                double gtb = finalscore.getGt() / (deptAvgGt * 2);
                finalscore.setGtb(new BigDecimal(gtb).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue());//工天比:个人工天除以(单位平均工天的2倍)
                double gtbdf = Math.round(finalscore.getGtb() * sysScoreSetting.getGtb());
                finalscore.setGtbdf(new BigDecimal(gtbdf).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue());//工天比得分，最高为3.5分
                double gtzdf = finalscore.getGtdf() + finalscore.getGtbdf();
                if (gtzdf >= 35) {
                    gtzdf = 35;
                }
                finalscore.setGtzdf(gtzdf);//工天总得分

                finalscore.setYwnl(sysUserScore.getYwnl());//业务能力
                finalscore.setGztd(sysUserScore.getGztd());//工作态度
                finalscore.setNltdzf(finalscore.getYwnl() + finalscore.getGztd());//能力态度得分

                finalscore.setCql(sysUserScore.getCql());//出勤率
                double cqldf = finalscore.getCql() * sysScoreSetting.getCxcql();
                if (cqldf < 0.6) {
                    cqldf = 0;
                }
                finalscore.setCqldf(cqldf);//出勤率得分
                finalscore.setXlcj(sysUserScore.getXlcj());//训练成绩
                double xlcjdf = sysUserScore.getXlcj() * sysScoreSetting.getXlcj();
                finalscore.setXlcjdf(new BigDecimal(xlcjdf).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue());//训练得分
                finalscore.setBwcj(sysUserScore.getBwcj());//比武成绩
                double bwcjdf = sysUserScore.getBwcj() * sysScoreSetting.getBwcj();
                finalscore.setBwcjdf(new BigDecimal(bwcjdf).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue());//比武得分
                finalscore.setYwxlzdf(finalscore.getXlcjdf() + finalscore.getBwcjdf());//业务训练总得分

                finalscore.setTbby(sysUserScore.getTbby() * 0.5);//通报表扬（0.5分每次，数据库只记录次数）
                finalscore.setSfjg(sysUserScore.getSfjg());//是否记过
                finalscore.setErrorinfo(sysUserScore.getErrorinfo());//犯错信息
                if (finalscore.getSfjg() == "是") {
                    finalscore.setKpzf(0.0);//如果记过，则总分记为0分
                } else {
                    finalscore.setKpzf(finalscore.getZlzdf() + finalscore.getGtzdf() + finalscore.getNltdzf() + finalscore.getYwxlzdf() + finalscore.getTbby());//考评总分
                }
            } else {
                finalscore.setHgl(0.0);//合格率
                finalscore.setHgldf(0.0);//合格率得分
                finalscore.setYxl(0.0);//优秀率
                finalscore.setYxldf(0.0);//优秀率得分
                finalscore.setYxgt(0.0);//优秀工天
                finalscore.setYxgtb(0.0);//优秀工天比
                finalscore.setYxgtbdf(0.0);//优秀工天比得分
                finalscore.setGctzsl(0.0);//改成图数量主
                finalscore.setGctcsl(0.0);//改成图数量次
                finalscore.setGctsl(0.0);//改成图数量
                finalscore.setGctkf(0.0);//改成图扣分
                finalscore.setJsxgzsl(0.0);//技术修改数量主
                finalscore.setJsxgcsl(0.0);//技术修改数量次
                finalscore.setJsxgsl(0.0);//技术修改数量
                finalscore.setJsxgkf(0.0);//技术修改扣分
                finalscore.setZlzdf(0.0);//质量总得分
                finalscore.setEdgt(sysUserScore.getEdgt());//额定工天
                finalscore.setGt(0.0);//工天
                finalscore.setGtdf(0.0);//工天得分
                finalscore.setGtb(0.0);//工天比
                finalscore.setGtbdf(0.0);//工天比得分
                finalscore.setGtzdf(0.0);//工天总得分
                finalscore.setYwnl(sysUserScore.getYwnl());//业务能力
                finalscore.setGztd(sysUserScore.getGztd());//工作态度
                finalscore.setNltdzf(finalscore.getYwnl() + finalscore.getGztd());//能力态度得分
                finalscore.setCql(sysUserScore.getCql());//出勤率
                finalscore.setCqldf(0.0);//出勤率得分
                finalscore.setXlcj(sysUserScore.getXlcj());//训练成绩
                finalscore.setXlcjdf(0.0);//训练得分
                finalscore.setBwcj(sysUserScore.getBwcj());//比武成绩
                finalscore.setBwcjdf(0.0);//比武得分
                finalscore.setYwxlzdf(0.0);//业务训练总得分
                finalscore.setTbby(sysUserScore.getTbby() * 0.5);//通报表扬
                finalscore.setSfjg(sysUserScore.getSfjg());//是否记过
                finalscore.setErrorinfo(sysUserScore.getErrorinfo());//犯错信息
                finalscore.setKpzf(0.0);//考评总分
            }
            finalscoreList.add(finalscore);
        }

        /*
         * 3、将成绩信息入库
         * */
        int count = sysFinalScoreDao.insertFinalScoreMany(finalscoreList);

        /*
         * 4、更新组长成绩信息：组长成绩=自身成绩的85%+本组平均成绩的15%
         * */
        for (sys_user_finalscore sysUserFinalscore : finalscoreList) {
            if (sysUserFinalscore.getStation_name().equals("组长")) {
                String zb = sysUserFinalscore.getZb();
                Double avgXiaoZu = sysFinalScoreDao.getAvgScoreXiaoZu(year, deptID, zb);//获取小组考评平均分
                double kpzf = sysUserFinalscore.getKpzf() * 0.85 + avgXiaoZu * 0.15;
                sysUserFinalscore.setKpzf(new BigDecimal(kpzf).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue());
                sysFinalScoreDao.updateFinalScore(sysUserFinalscore);
                break;
            }
        }
        /*
         * 5、计算更新各类排名信息
         * */
        List<sys_user_finalscore> finalscores = sysFinalScoreDao.calFinalScore(year, deptID);
        //更新各类排名和成绩评定结果
        for (sys_user_finalscore finalscore : finalscores) {
            if (finalscore.getSfjg().equals("是")) {
                finalscore.setPdjg("差");
            } else {
                double bl = finalscore.getKpzpm().doubleValue() / finalscores.size();
                if (bl <= 0.35) {
                    finalscore.setPdjg("优秀");
                } else if (bl > 0.35 && bl <= 0.85) {
                    finalscore.setPdjg("良好");
                } else if (bl > 0.85 && bl <= 0.95) {
                    finalscore.setPdjg("一般");
                } else {
                    finalscore.setPdjg("差");
                }
            }
        }
        int count1 = sysFinalScoreDao.updateFinalScoreMany(finalscores);
        if (count1 == finalscores.size()) {
            saveResult = true;
        }
        return saveResult;
    }

    @Override
    public List<sys_user_score> getDeptScoreInfo(Integer year, String deptID, String isAll) {
        return sysScoreDao.getDeptScoreInfo(year, deptID, isAll);
    }

    @Override
    public List<sys_user_score> getAllScoreInfo(Integer year, String isAll) {
        return sysScoreDao.getAllScoreInfo(year, isAll);
    }
}
