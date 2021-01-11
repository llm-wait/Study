package com.startest.wm.service.impl;

import com.startest.wm.constant.TaskClassId;
import com.startest.wm.constant.TaskExcelHeader;
import com.startest.wm.dao.TaskIndexDao;
import com.startest.wm.dao.TaskOthersDao;
import com.startest.wm.enums.task.EnumTaskState;
import com.startest.wm.enums.task.EnumTaskType;
import com.startest.wm.pojo.OthersTaskHelper;
import com.startest.wm.pojo.wm_task_index;
import com.startest.wm.pojo.wm_task_others;
import com.startest.wm.service.TaskOthersService;
import com.startest.wm.utils.*;
import com.startest.wm.utils.customresponse.RestResponse;
import com.startest.wm.utils.customresponse.RestResponseUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-05-11-13:26
 * @描述 应急保障任务实现逻辑
 */
@Service
public class TaskOthersImpl implements TaskOthersService {

    protected static final Logger log = LoggerFactory.getLogger(TaskOthersImpl.class);
    private final TaskOthersDao taskOthersDao;
    private final TaskIndexDao taskIndexDao;

    public TaskOthersImpl(TaskOthersDao taskOthersDao, TaskIndexDao taskIndexDao) {
        this.taskOthersDao = taskOthersDao;
        this.taskIndexDao = taskIndexDao;
    }

    @Override
    public List<wm_task_others> getTaskOthersInfo(wm_task_others taskOthers) {
        return taskOthersDao.selectTaskOthersInfo(taskOthers);
    }

    @Override
    public String getTaskOtherCode() {

        String dateNowStr = MyDateUtils.getCurrentDate();
        //当天，一共有几个应急任务
        Integer count = taskOthersDao.countOtherByCode(dateNowStr);
        //查到后，当前时间 + 查询条数基础上+1，
        StringBuilder stringBuilder = new StringBuilder(dateNowStr);
        if (count!=null&&count>0) {
            String strNum = String.valueOf(1000 + count+1);
            stringBuilder.append(strNum.substring(1));
        } else {
            //如果没查到，当前时间+001
            stringBuilder.append("001");
        }
        return stringBuilder.toString();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public void addTaskOthersInfo(wm_task_others taskOthers) {
        wm_task_index taskIndex = new wm_task_index();
        String indexId = UUIDGeneratorUtil.getUUIDWithoutLineAndToUpper();
        taskIndex.setIndex_id(indexId);
        taskIndex.setTask_class_id(taskOthers.getClass_id());
        taskIndex.setTask_class_tag(taskOthers.getOtask_name());
        taskIndex.setTask_data_id(taskOthers.getOtask_id());
        taskIndex.setTask_type(EnumTaskType.YJBZRW.getTaskTypeString());
        taskIndex.setTask_name(taskOthers.getOtask_name());
        taskIndex.setTask_state(EnumTaskState.JHXD.getTaskStateString());
        taskIndexDao.insertTaskIndexInfo(taskIndex);
        taskOthers.setIndex_id(indexId);
        taskOthersDao.addTaskOthersInfo(taskOthers);
    }

    @Override
    public int editTaskOthersInfo(wm_task_others taskOthers) {
        return taskOthersDao.editTaskOthersInfo(taskOthers);
    }

    @Override
    public int deleteTaskOthersInfo(String strID) {
        return taskOthersDao.deleteTaskOthersInfo(strID);
    }





    @Override
    public RestResponse<String> listTaskByClassId(String classId, MultipartFile multipartFile) {


//todo 只写了应急保障
        if (classId.equals(TaskClassId.YJBZ_ID)) {
         return    listEmergencySupportMission(multipartFile);
        }


        return RestResponseUtil.note("暂不支持的任务类型");
    }

    /**批量添加应急保障任务
     * @param multipartFile　导入文件
     * @return 结果
     */
    private RestResponse<String> listEmergencySupportMission(MultipartFile multipartFile) {

        try {


            //导入的总应急任务
            List<wm_task_others> wmTaskIndexList =
                    ExeclUtil.execltoList(multipartFile, wm_task_others.class,
                            TaskExcelHeader.WM_TASK_OTHERS.getLinkedHashMap());


            //通过迭代器，过滤掉任务名称重复的任务
            wmTaskIndexList.removeIf(wm_task_others -> taskOthersDao.isOtherByName(wm_task_others.getOtask_name()));

            if (CollectionUtils.isEmpty(wmTaskIndexList)) {
                return RestResponseUtil.note("导入的任务名称都已存在！");
            }

            ArrayList<wm_task_index> taskIndexArrayList = new ArrayList<>();
            wm_task_index wmTaskIndex;
            for (wm_task_others wmTaskOthers : wmTaskIndexList) {
                wmTaskOthers.setOtask_id(UUIDGeneratorUtil.getUUIDWithoutLineAndToLower());
                if (StringUtils.isBlank(wmTaskOthers.getOtask_code())) {
                    wmTaskOthers.setOtask_code(getTaskOtherCode());
                }

                String indexTaskId = UUIDGeneratorUtil.getUUIDWithoutLineAndToLower();


                wmTaskIndex = new wm_task_index();
                wmTaskIndex.setIndex_id(indexTaskId);
                wmTaskIndex.setTask_name(wmTaskOthers.getOtask_name());
                wmTaskIndex.setTask_class_id(TaskClassId.YJBZ_ID);
                wmTaskIndex.setTask_type(EnumTaskType.YJBZRW.getTaskTypeString());
                wmTaskIndex.setTask_year(MyDateUtils.getCurrentDate(MyDateUtils.DATE_YEAR));
                wmTaskIndex.setTask_state(EnumTaskState.JHXD.getTaskStateString());

                taskIndexArrayList.add(wmTaskIndex);
            }

            taskIndexDao.insertTaskIndexInfoByList(taskIndexArrayList);
            taskOthersDao.insertTaskOthersByList(wmTaskIndexList);

            return RestResponseUtil.success("应急保障任务信息导入成功");


        } catch (Exception e) {
            e.printStackTrace();
            log.error("应急保障任务信息导入失败", e);
            return RestResponseUtil.note("应急保障任务信息导入失败");
        }
    }
}
