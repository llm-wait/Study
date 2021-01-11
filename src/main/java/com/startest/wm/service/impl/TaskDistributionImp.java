package com.startest.wm.service.impl;

import com.startest.wm.dao.TaskCheckDao;
import com.startest.wm.dao.TaskDistributeDao;
import com.startest.wm.dao.TaskIndexDao;
import com.startest.wm.enums.task.EnumTaskState;
import com.startest.wm.pojo.wm_task_check;
import com.startest.wm.pojo.wm_task_distribution;
import com.startest.wm.service.TaskDistributionService;
import com.startest.wm.utils.UUIDGeneratorUtil;
import com.startest.wm.utils.customresponse.RestResponse;
import com.startest.wm.utils.customresponse.RestResponseCode;
import com.startest.wm.utils.customresponse.RestResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-04-10-8:28
 * @描述 任务执行单接口实现类
 */
@Service
@Transactional
public class TaskDistributionImp implements TaskDistributionService {
    @Autowired
    private TaskDistributeDao taskDistributeDao;
    @Autowired
    private TaskIndexDao taskIndexDao;

    @Autowired
    private TaskCheckDao taskCheckDao;

    /**
     * 条件检索任务分配信息
     *
     * @param taskDistribution
     * @return
     */
    @Override
    public List<wm_task_distribution> selectTaskDistribute(wm_task_distribution taskDistribution) {
        return taskDistributeDao.selectTaskDistribute(taskDistribution);
    }

    /**
     * 获取分配工天
     *
     * @param taskId
     * @param deptID
     * @param taskCon
     * @return
     */
    @Override
    public String getTaskDistributeAllWorkDays(String taskId, String deptID, String taskCon) {
        double allDays = 0f;
        wm_task_distribution taskDis = new wm_task_distribution();
        taskDis.setTask_index_id(taskId);
        taskDis.setDept_id(deptID);
        taskDis.setDistribution_type(taskCon);
        List<wm_task_distribution> disList = taskDistributeDao.selectTaskDistribute(taskDis);
        if (disList != null && disList.size() > 0) {
            for (wm_task_distribution item : disList) {
                Double days = item.getWork_days();
                if (days != null) {
                    allDays = allDays + days;
                }
            }
        }
        return String.valueOf(allDays);
    }

    /**
     * 获取分配过程名称的所有人员名
     * @param taskId 任务ID
     * @param deptID 部门ID
     * @param taskCon 内容
     * @param strduty 内容
     * @return
     */
    @Override
    public String getTaskDistributeName(String taskId, String deptID, String taskCon, String strduty) {
        String allNames = null;
        wm_task_distribution taskDis = new wm_task_distribution();
        taskDis.setTask_index_id(taskId);
        taskDis.setDept_id(deptID);
        taskDis.setDistribution_type(taskCon);
        taskDis.setUser_duty(strduty);
        List<wm_task_distribution> disList = taskDistributeDao.selectTaskDistribute(taskDis);
        if (disList != null && disList.size() > 0) {
            for (wm_task_distribution item : disList) {
                String name = item.getUser_name();
                if (name != null && name.length() > 0) {
                    if (allNames != null && allNames.length() > 0) {
                        allNames = allNames + "," + name;
                    } else {
                        allNames = name;
                    }
                }
            }
        }
        return allNames;
    }

    @Override
    public List<Map> getTaskDistributionContentList(String taskId, String deptID) {
        return taskDistributeDao.selectTaskDistributionContent(taskId,deptID);
    }



    @Override
    public int updateTaskDistribution(wm_task_distribution taskDistribution) {
        return taskDistributeDao.updateTaskDistribution(taskDistribution);
    }

    @Override
    public int updateTaskDistributionStartDate(String distributionID, String startDate) {
        return taskDistributeDao.updateTaskDistributionStartDate(distributionID,startDate);
    }

    @Override
    public int updateTaskDistributionEndDate(String distributionID, String endDate) {
        return taskDistributeDao.updateTaskDistributionEndDate(distributionID,endDate);
    }


    /**
     * 根据ID获取人员姓名及其对应的职责
     * @param indexID
     * @param deptID
     * @return
     */
    @Override
    public List<Map> getDuty(String indexID, String deptID) {
        return taskDistributeDao.getDuty(indexID, deptID);
    }

    /**跟据任务id计算任务总工天（一个任务的总工天数及是其作业员工天数）
     * @param indexID
     * @return
     */
    @Override
    public Double getChartAllWorkDays(String indexID,String productType) {
        //如果有分类就查询海图，没有分类就是书表
        if (productType==null) {
        return taskDistributeDao.getBookAllWordDays(indexID);
        }
        return taskDistributeDao.getChartAllWorkDays(indexID, productType);
    }

    @Override
    public List<wm_task_distribution> selectTaskProductType(String indexID) {
        return taskDistributeDao.selectTaskProductType(indexID);
    }


    /**
     * 获取部门一个季度海图任务列表
     *
     * @param year 年份
     * @param quarter 季度
     * @param deptID 部门ID
     * @return
     */
    @Override
    public List<wm_task_distribution> getDeptAllChartWorkListInAQuarter(String year, int quarter, String deptID) {
        String startDate = null;
        String endDate = null;
        String year1 = String.valueOf(Integer.valueOf(year) + 1);
        switch (quarter) {
            case 1:
                startDate = year + "-1-1";
                endDate = year + "-4-1";
                break;
            case 2:
                startDate = year + "-4-1";
                endDate = year + "-7-1";
                break;
            case 3:
                startDate = year + "-7-1";
                endDate = year + "-10-1";
                break;
            case 4:
                startDate = year + "-10-1";
                endDate = year1 + "-1-1";
                break;
            default:
                startDate = year + "-1-1";
                endDate = year1 + "-1-1";
                break;
        }

        return taskDistributeDao.getDeptAllChartWorkListInAQuarter(year, startDate, endDate, deptID);
    }


    /**
     * 获取部门一个季度的书表任务列表
     *
     * @param year 年份
     * @param quarter 季度
     * @param deptID 部门ID
     * @return
     */
    @Override
    public List<wm_task_distribution> getDeptAllBookWorkListInAQuarter(String year, int quarter, String deptID) {
        String startDate = null;
        String endDate = null;
        String year1 = String.valueOf(Integer.valueOf(year) + 1);
        switch (quarter) {
            case 1:
                startDate = year + "-1-1";
                endDate = year + "-4-1";
                break;
            case 2:
                startDate = year + "-4-1";
                endDate = year + "-7-1";
                break;
            case 3:
                startDate = year + "-7-1";
                endDate = year + "-10-1";
                break;
            case 4:
                startDate = year + "-10-1";
                endDate = year1 + "-1-1";
                break;
            default:
                startDate = year + "-1-1";
                endDate = year1 + "-1-1";
                break;
        }
        return taskDistributeDao.getDeptAllBookWorkListInAQuarter(year, startDate, endDate, deptID);
    }

    @Override
    public RestResponse<String> deleteTaskDistributionInfo(String disId) {
        taskDistributeDao.delteTaskDistributiion(disId);
        taskCheckDao.deleteTaskCheckByDisId(disId);
        return RestResponseUtil.success("任务执行单信息删除成功");
    }

    @Override
    public RestResponse<String> addTaskDistributionInfo(wm_task_distribution taskDistribution) {
        //查询添加信息是否重复
        Integer isExist = taskIndexDao.selectTask(taskDistribution);
        if (isExist != null && isExist > 0) {
            return RestResponseUtil.note("添加数据已存在，请删除后再添加！", null);
        }

        taskIndexDao.updateTaskState(taskDistribution.getTask_index_id(), EnumTaskState.RWJX.getTaskStateString(), null);
        taskDistributeDao.insertTaskDistribution(taskDistribution);


        //添加执行单的同时，再质检中加入关联字段
        wm_task_check wmTaskCheck = new wm_task_check();
        wmTaskCheck.setCheck_id(UUIDGeneratorUtil.getUUIDWithoutLineAndToUpper());
        wmTaskCheck.setDistribution_id(taskDistribution.getDistribution_id());
        taskCheckDao.insertTaskCheckInfo(wmTaskCheck);

        return RestResponseUtil.success("添加详情成功！", null);

    }
}
