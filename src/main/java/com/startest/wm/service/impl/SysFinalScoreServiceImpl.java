package com.startest.wm.service.impl;

import com.startest.wm.dao.SysFinalScoreDao;
import com.startest.wm.model.score.*;
import com.startest.wm.pojo.sys_user_finalscore;
import com.startest.wm.service.SysFinalScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-04-02 10:31
 * @描述 系统最终成绩操作接口实现类
 **/
@Service
public class SysFinalScoreServiceImpl implements SysFinalScoreService {
    @Autowired
    SysFinalScoreDao sysFinalScoreDao;

    @Override
    public int insertFinalScore(sys_user_finalscore sysUserFinalscore) {
        return sysFinalScoreDao.insertFinalScore(sysUserFinalscore);
    }

    @Override
    public int updateFinalScorePdjg(String id, String pdjg) {
        return sysFinalScoreDao.updateFinalScorePdjg(id, pdjg);
    }

    @Override
    public int deleteFinalScore(Integer year, String deptID) {
        return sysFinalScoreDao.deleteFinalScore(year, deptID);
    }

    @Override
    public List<ZlcpScoreModel> queryZlcpScore(Integer calType, Integer year, String deptID, String zb, String rylb) {
        if (calType == 0) {
            return sysFinalScoreDao.queryZlcpScore(year, deptID, zb, rylb);
        } else {
            return sysFinalScoreDao.queryZlcpScore2(year, deptID, zb, rylb);
        }
    }

    @Override
    public List<GtcpScoreModel> queryGtcpScore(Integer calType, Integer year, String deptID, String zb, String rylb) {
        if (calType == 0) {
            return sysFinalScoreDao.queryGtcpScore(year, deptID, zb, rylb);
        } else {
            return sysFinalScoreDao.queryGtcpScore2(year, deptID, zb, rylb);
        }
    }

    @Override
    public List<NltdcpScoreModel> queryNltdcpScore(Integer calType, Integer year, String deptID, String zb, String rylb) {
        if (calType == 0) {
            return sysFinalScoreDao.queryNltdcpScore(year, deptID, zb, rylb);
        } else {
            return sysFinalScoreDao.queryNltdcpScore2(year, deptID, zb, rylb);
        }
    }

    @Override
    public List<YwxlcpScoreModel> queryYwxlcpScore(Integer calType, Integer year, String deptID, String zb, String rylb) {
        if (calType == 0) {
            return sysFinalScoreDao.queryYwxlcpScore(year, deptID, zb, rylb);
        } else {
            return sysFinalScoreDao.queryYwxlcpScore2(year, deptID, zb, rylb);
        }
    }

    @Override
    public List<ZhcpScoreModel> queryZhcpScore(Integer calType, Integer year, String deptID, String zb, String rylb) {
        if (calType == 0) {
            return sysFinalScoreDao.queryZhcpScore(year, deptID, zb, rylb);
        } else {
            return sysFinalScoreDao.queryZhcpScore2(year, deptID, zb, rylb);
        }
    }

    @Override
    public List<sys_user_finalscore> calFinalScore(Integer year, String deptID) {
        return sysFinalScoreDao.calFinalScore(year, deptID);
    }

    @Override
    public List<sys_user_finalscore> queryFinalScore(Integer year, String deptID, String zb, String rylb) {
        return sysFinalScoreDao.queryFinalScore(year, deptID, zb, rylb);
    }

    @Override
    public List<sys_user_finalscore> getZysList(Integer year) {
        return sysFinalScoreDao.getZysList(year);
    }

    @Override
    public List<String> getZysZbList(Integer year, String deptID) {
        return sysFinalScoreDao.getZysZbList(year, deptID);
    }

    @Override
    public List<String> getJgZbList(Integer year, String deptID) {
        List<String> list = new ArrayList<>();
        /*Map<String, String> map = new HashMap<>();*/
        list.add("全体人员");
        if (!deptID.equals("-1")) {
            /*List<sys_user_finalscore> sysUserFinalscoreList = sysFinalScoreDao.getZysList(year);
            for (sys_user_finalscore sysUserFinalscore : sysUserFinalscoreList) {
                Map<String, String> map1 = new HashMap<>();
                map1.put("dept_id", sysUserFinalscore.getDept_id());
                map1.put("dept_name", sysUserFinalscore.getDept_name());
                list.add(map1);
            }*/
            List<String> zbList = sysFinalScoreDao.getZysZbList(year, deptID);
            list.addAll(zbList);
        }
        return list;
    }
}
