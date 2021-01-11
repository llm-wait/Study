package com.startest.wm.service.impl;

import com.github.pagehelper.PageHelper;
import com.startest.wm.constant.TaskClassId;
import com.startest.wm.constant.TaskExcelHeader;
import com.startest.wm.dao.*;
import com.startest.wm.enums.EnumTaskChartDistributionType;
import com.startest.wm.enums.task.EnumTaskState;
import com.startest.wm.enums.task.EnumTaskType;
import com.startest.wm.model.TaskBookModel;
import com.startest.wm.model.TaskIndexModel;
import com.startest.wm.model.TaskMapModel;
import com.startest.wm.model.TaskOthersModel;
import com.startest.wm.pojo.*;
import com.startest.wm.service.SysZYDanService;
import com.startest.wm.service.TaskDistributionService;
import com.startest.wm.service.TaskIndexService;
import com.startest.wm.utils.ExeclUtil;
import com.startest.wm.utils.MyDateUtils;
import com.startest.wm.utils.TaskClassNodeUtils;
import com.startest.wm.utils.UUIDGeneratorUtil;
import com.startest.wm.utils.customresponse.RestResponse;
import com.startest.wm.utils.customresponse.RestResponseCode;
import com.startest.wm.utils.customresponse.RestResponseUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-04-10-8:21
 * @描述 任务索引逻辑接口实现类
 */
@Service
@Transactional
public class TaskIndexImpl implements TaskIndexService {
    @Autowired
    private TaskIndexDao taskIndexDao;

    @Autowired
    private TaskClassDao taskClassDao;

    @Autowired
    private CheckIndexDao checkIndexDao;

    @Autowired
    private SysZYDanService sysZYDanService;


    @Autowired
    private TaskDistributionService taskDistributionService;
    @Autowired
    private TaskInfoDao taskInfoDao;

    @Autowired
    SysDataOperationDao sysDataOperationDao;

    @Override
    public List<wm_task_index> selectTaskIndexInfo(wm_task_index taskIndex) {
        return taskIndexDao.selectTaskIndexInfo(taskIndex);
    }

    @Override
    public int insertTaskIndexInfo(wm_task_index taskIndex) {
        return taskIndexDao.insertTaskIndexInfo(taskIndex);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public int insertTaskIndexInfoByList(List<wm_task_index> list) {
        return taskIndexDao.insertTaskIndexInfoByList(list);
    }

    @Override
    public int updateTaskIndexInfo(wm_task_index taskIndex) {
        return taskIndexDao.updateTaskIndexInfo(taskIndex);
    }

    @Override
    public int updateTaskRate(String indexID, String rate, String description) {
        return taskIndexDao.updateTaskRate(indexID, rate, description);
    }

    @Override
    public void updateTaskData(String indexID, String dataID) {
        taskIndexDao.updateTaskData(indexID, dataID);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public void updateTaskClassInfo(List<String> list, String classId) {

        String name = taskClassDao.selectTaskNameById(classId);
        taskIndexDao.updateTaskClassInfo(list, classId, name);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public int updateTaskState(String indexID, String state, String deptID, String deptName, String classID, String taskName, String taskType, wm_task_dept tdept) {
        //String strClassID = null;
        //如果是筹划
        if (state.equals(EnumTaskState.JHXD.getTaskStateString())) {

        } else if (state.equals(EnumTaskState.XF.getTaskStateString())) {
            //已　抽出
            taskReleaseModification(indexID);
        } else if (state.equals(EnumTaskState.RWJX.getTaskStateString())) {
            //任务进行
            //strClassID = "A6C13B7C510247FDA4E854A1E64D0202";
        } else if (state.equals(EnumTaskState.RWZJ.getTaskStateString())) {
            return modifyPrintingStatus(indexID, state, deptID, deptName, classID);

        } else if (EnumTaskState.ZY.getTaskStateString().equals(state)) {
            //制印
            taskPrintingModification(indexID, state, classID, taskName, taskType, tdept);
        } else if (state.equals(EnumTaskState.YWC.getTaskStateString())) {
            //已完成
            //strClassID = "F3AF45228537400080F9203444EE4C60";
        } else {
            //strClassID = "A1D0C89F9AF8458B9F55522E221740AE";
        }
        if (state.equals(EnumTaskState.ZY.getTaskStateString())) {
            //制印
            Date d1 = new Date();
            return taskIndexDao.updateTaskState(indexID, state,
                    MyDateUtils.getCurrentDate(MyDateUtils.DATE_PATTERN_SECOND));
        } else {
            return taskIndexDao.updateTaskState(indexID, state, null);
        }
    }

    /**修改任务制印状态
     * @param indexID
     * @param state
     * @param classID
     * @param taskName
     * @param taskType
     * @param tdept
     */
    private void taskPrintingModification(String indexID, String state, String classID, String taskName, String taskType, wm_task_dept tdept) {
        //strClassID = "1F3C457F60554A0A9B82C539422BDADC";
        //获取分配人员姓名
        String strZYY = taskDistributionService.getTaskDistributeName(indexID, tdept.getDept_id(), "", EnumTaskChartDistributionType.ZY.getTaskChartDistributionType());
        String strBj = taskDistributionService.getTaskDistributeName(indexID, tdept.getDept_id(), "", EnumTaskChartDistributionType.BJSJ.getTaskChartDistributionType());
        String strYS = taskDistributionService.getTaskDistributeName(indexID, tdept.getDept_id(), "", EnumTaskChartDistributionType.YS.getTaskChartDistributionType());

        wm_task_class tClass = new wm_task_class();

        List<wm_task_class> allList = taskClassDao.selectClassInfo(tClass);
        String rootNodeID = TaskClassNodeUtils.selectRootNode(allList, classID);
        wm_task_index taskIndex = new wm_task_index();
        taskIndex.setIndex_id(indexID);
        if (rootNodeID != null) {
            switch (rootNodeID) {
                case "54A89E793FA24141B4C5FB13FE80AC1D"://制图任务
                    List<TaskMapModel> mapList = taskIndexDao.selectMapTaskListInfo(taskIndex);
                    if (mapList != null && mapList.size() > 0) {
                        TaskMapModel taskMap = mapList.get(0);
                        taskMap.setTask_state(state);
                        if (taskMap.getMap_type().contains("民用")) {
                            //民用海图同时写入民用海图制印单和保障部队民用海图制印单
                            sysZYDanService.insertMapMZyd(taskMap, taskName, taskType, tdept.getDept_name(), strZYY, strBj, strYS);
                            sysZYDanService.insertMapBZyd(taskMap, taskName, taskType, tdept.getDept_name(), strZYY, strBj, strYS);
                        } else {
                            //军用海图写入军用海图制印单
                            sysZYDanService.insertMapJZyd(taskMap, taskName, taskType, tdept.getDept_name(), strZYY, strBj, strYS);
                        }
                    }
                    break;
                case TaskClassId.SBRW_ID://书表任务
                    List<TaskBookModel> bookList = taskIndexDao.selectBookTaskListInfo(taskIndex);
                    if (bookList != null && bookList.size() > 0) {
                        TaskBookModel taskBook = bookList.get(0);
                        int yearY = Integer.valueOf(taskBook.getTask_year());
                        if (taskBook.getPort_type().contains("民用")) {
                            //民用书表同时写入民用书表制印单和保障部队民用书表制印单
                            sysZYDanService.insertBookMZyd(indexID, yearY, taskName, taskType, state, tdept.getDept_name(), taskBook.getPort_cn_name(), taskBook.getPort_num(), strZYY, strBj, strYS);
                            sysZYDanService.insertBookBZyd(indexID, yearY, taskName, taskType, state, tdept.getDept_name(), taskBook.getPort_cn_name(), taskBook.getPort_num(), strZYY, strBj, strYS);
                        } else {
                            //民用书表写入民用书表制印单
                            sysZYDanService.insertBookJZyd(indexID, yearY, taskName, taskType, state, tdept.getDept_name(), taskBook.getPort_cn_name(), taskBook.getPort_num(), strZYY, strBj, strYS);
                        }
                    }
                    break;
                case TaskClassId.YJBZ_ID://应急保障任务
                    List<TaskOthersModel> othersList = taskIndexDao.selectOthersTaskListInfo(taskIndex);
                    if (othersList != null && othersList.size() > 0) {
                        TaskOthersModel taskOthers = othersList.get(0);

                    }
                    break;
            }
        }
    }

    /**任务下发修改  ,下发修改已经另写接口了，应该走不到
     * @param indexID　任务id
     */
    private void taskReleaseModification(String indexID) {
        //如果是下发  要往　　taskinfo插入数据，后台要生成Task_info_id，还有当前时间　　　　　改的东西有：
        //strClassID = "FAC071FF79624889AF29DC54E3EE69B2";

        wm_task_info taskInfo = new wm_task_info();
        taskInfo.setTask_info_id(UUIDGeneratorUtil.getUUIDWithoutLineAndToUpper());
        taskInfo.setTask_index_id(indexID);
        taskInfo.setTask_start_date(MyDateUtils.getCurrentDate(MyDateUtils.DATE_PATTERN_SECOND));

        taskInfoDao.insertTaskInfo(taskInfo);
    }

    /**修改任务质检状态
     * @param indexID
     * @param state
     * @param deptID
     * @param deptName
     * @param classID
     * @return
     */
    private int modifyPrintingStatus(String indexID, String state, String deptID, String deptName, String classID) {
        //质检修改状态
        //              //１,插入质检信息表数据，２，修改任务表状态
        //
        //            //任务质检
        //            //strClassID = "3652A05C55E94F82A7C307562F67DDA7";
        wm_check_index cIndex = new wm_check_index();
        //生成Check_index_id　质检id
        cIndex.setCheck_index_id(UUIDGeneratorUtil.getUUIDWithoutLineAndToUpper());
        cIndex.setTask_index_id(indexID);
        cIndex.setDept_id(deptID);
        cIndex.setDept_name(deptName);

        //获取类型信息
        List<wm_task_distribution> listType = taskDistributionService.selectTaskProductType(indexID);
        if (listType == null || listType.size() <= 0) {
            return 0;
        }

        wm_task_class tClass = new wm_task_class();
        List<wm_task_class> allList = taskClassDao.selectClassInfo(tClass);
        String rootNodeID = TaskClassNodeUtils.selectRootNode(allList, classID);
        wm_task_index taskIndex = new wm_task_index();
        taskIndex.setIndex_id(indexID);
        if (rootNodeID != null) {
            switch (rootNodeID) {
                case "54A89E793FA24141B4C5FB13FE80AC1D"://制图任务
                    List<TaskMapModel> mapList = taskIndexDao.selectMapTaskListInfo(taskIndex);
                    if (mapList != null && mapList.size() > 0) {
                        TaskMapModel taskMap = mapList.get(0);
                        cIndex.setChart_book_code(taskMap.getMap_code());
                        cIndex.setChart_book_name(taskMap.getMap_cn_name());
                        cIndex.setProduct_type("海图");
                        //cIndex.setDistribution_type(taskMap.get);
                    }
                    break;
                case TaskClassId.SBRW_ID://书表任务
                    List<TaskBookModel> bookList = taskIndexDao.selectBookTaskListInfo(taskIndex);
                    if (bookList != null && bookList.size() > 0) {
                        TaskBookModel taskBook = bookList.get(0);
                        cIndex.setChart_book_code(taskBook.getPort_num());
                        cIndex.setChart_book_name(taskBook.getPort_cn_name());
                        cIndex.setProduct_type("书表");
                    }
                    break;
                case TaskClassId.YJBZ_ID://应急保障任务
                    List<TaskOthersModel> othersList = taskIndexDao.selectOthersTaskListInfo(taskIndex);
                    if (othersList != null && othersList.size() > 0) {
                        TaskOthersModel taskOthers = othersList.get(0);
                        cIndex.setChart_book_code(taskOthers.getOtask_code());
                        cIndex.setChart_book_name(taskOthers.getOtask_name());
                        cIndex.setProduct_type("海图");
                    }
                    break;
            }
        }
        int count = 0;
        if ("海图".equals(cIndex.getProduct_type())) {
            for (wm_task_distribution item : listType) {
                wm_check_index cIndexItem = new wm_check_index();
                cIndexItem = cIndex;
                cIndex.setDistribution_type(item.getDistribution_type());
                count = checkIndexDao.insertCheckIndexInfo(cIndexItem);
                if (count == 0) {
                    break;
                }
            }
        } else {
            count = checkIndexDao.insertCheckIndexInfo(cIndex);
        }
        if (count > 0) {

            //修改索引状态
            return taskIndexDao.updateTaskState(indexID, state, null);
        } else {
            return 0;
        }
    }


    @Override
    public void updateTaskYearInfo(String indexID, String year) {
         taskIndexDao.updateTaskYearInfo(indexID, year);
    }

    @Override
    public void deleteTaskIndexInfo(String indexID) {
        taskIndexDao.deleteTaskIndexInfo(indexID);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public int insertTaskIndexInfoByChartData(List<map_data> dataList) {
        List<wm_task_index> tList = new ArrayList<>();
        if (dataList != null && dataList.size() > 0) {
            for (map_data data : dataList) {
                wm_task_index taskIndex = new wm_task_index();
                taskIndex.setIndex_id(UUIDGeneratorUtil.getUUIDWithoutLineAndToUpper());
                String strClassID = "54A89E793FA24141B4C5FB13FE80AC1D";//制图任务
                taskIndex.setTask_class_id(strClassID);
                taskIndex.setTask_class_tag(data.getMap_cn_name());
                taskIndex.setTask_state(EnumTaskState.JHXD.getTaskStateString());
                taskIndex.setTask_data_id(data.getMap_id());
                taskIndex.setTask_type(EnumTaskType.ZTRW.getTaskTypeString());
                taskIndex.setTask_name(data.getMap_code());
                taskIndex.setData_code(data.getMap_code());
                String strName = data.getMap_cn_name();
                if (strName != null && strName.length() > 0) {
                    taskIndex.setData_name(strName);
                } else {
                    taskIndex.setData_name(data.getMap_en_name());
                }
                tList.add(taskIndex);
            }
        }
        return taskIndexDao.insertTaskIndexInfoByList(tList);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public int insertTaskIndexInfoByBookData(List<port_data> dataList) {
        List<wm_task_index> tList = new ArrayList<>();
        if (dataList != null && dataList.size() > 0) {
            for (port_data data : dataList) {
                wm_task_index taskIndex = new wm_task_index();
                taskIndex.setIndex_id(UUIDGeneratorUtil.getUUIDWithoutLineAndToUpper());
                String strClassID = TaskClassId.SBRW_ID;//书表任务
                taskIndex.setTask_class_id(strClassID);
                taskIndex.setTask_class_tag(data.getPort_cn_name());
                taskIndex.setTask_data_id(data.getPort_id());
                taskIndex.setTask_type(EnumTaskType.SBRW.getTaskTypeString());
                taskIndex.setTask_state(EnumTaskState.JHXD.getTaskStateString());
                taskIndex.setTask_name(data.getPort_num());
                taskIndex.setData_code(data.getPort_num());
                String strName = data.getPort_cn_name();
                if (strName != null && strName.length() > 0) {
                    taskIndex.setData_name(strName);
                } else {
                    taskIndex.setData_name(data.getPort_en_name());
                }
                tList.add(taskIndex);
            }
        }
        return taskIndexDao.insertTaskIndexInfoByList(tList);
    }

    @Override
    public List<TaskMapModel> selectMapTaskListInfo(wm_task_index taskIndex) {
        return taskIndexDao.selectMapTaskListInfo(taskIndex);
    }

    @Override
    public List<TaskMapModel> selectDeptMapTaskListInfo(wm_task_index taskIndex) {
        return taskIndexDao.selectDeptMapTaskListInfo(taskIndex);
    }

    @Override
    public List<TaskBookModel> selectBookTaskListInfo(wm_task_index taskIndex) {
        return taskIndexDao.selectBookTaskListInfo(taskIndex);
    }

    @Override
    public List<TaskBookModel> selectDeptBookTaskListInfo(wm_task_index taskIndex) {
        return taskIndexDao.selectDeptBookTaskListInfo(taskIndex);
    }

    @Override
    public List<TaskOthersModel> selectOthersTaskListInfo(wm_task_index taskIndex) {
        return taskIndexDao.selectOthersTaskListInfo(taskIndex);
    }

    @Override
    public List<TaskOthersModel> selectDeptOthersTaskListInfo(wm_task_index taskIndex) {
        return taskIndexDao.selectDeptOthersTaskListInfo(taskIndex);
    }

    @Override
    public int updateTaskYearInfoByList(List<String> list, String year) {
        return taskIndexDao.updateTaskYearInfoByList(list, year);
    }

    /**批量更新任务状态
     * @param list
     * @param state
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public int updateTaskStateByList(List<String> list, String state) {
        if (state.equals(EnumTaskState.XF.getTaskStateString())) {
            for (String item : list) {
                wm_task_info taskInfo = new wm_task_info();
                taskInfo.setTask_info_id(UUIDGeneratorUtil.getUUIDWithoutLineAndToUpper());
                taskInfo.setTask_index_id(item);
                taskInfo.setTask_start_date(MyDateUtils.getCurrentDate(MyDateUtils.DATE_PATTERN_SECOND));
                taskInfoDao.insertTaskInfo(taskInfo);
            }
        }
        return taskIndexDao.updateTaskStateByList(list, state);
    }

    @Override
    public RestResponse<List<String>> selectTaskYearInfo() {
        return RestResponseUtil.success(taskIndexDao.selectTaskYearInfo());
    }


    @Override
    public RestResponse<String> updatePrintDate(String strID, String printDate) {
        int i = taskIndexDao.updatePrintDate(strID, printDate);
        if (1>0) {
          return  RestResponseUtil.success("任务出厂时间更新成功！");
        }
        return RestResponseUtil.error(300,"任务出厂时间更新失败");
    }

  /*  @Override
    public void updateDept(String[] list) {
            taskClassDao.updateDept(list);
    }*/


    /**查询是否分配年份和作业室
     * @param lstIDs
     * @return
     */
    public List<String> selectIsAllocation(List<String> lstIDs) {
        List<String> reIds = new ArrayList<>();
        for (String id : lstIDs) {
            //查询未分配的id
            String ids = taskIndexDao.selectIsAllocation(id);
            if (ids != null) {
                reIds.add(ids);
            }
        }
        return reIds;
    }


    @Override
    public List<TaskIndexModel> getTaskIndexModelInfo(TaskIndexModel task) {
//        List<TaskIndexModel> resList = new ArrayList<>();
        //年度任务
//            resList.addAll(taskIndexDao.selectCommontTaskInfo(task));
        //应急保障任务
//            resList.addAll(taskIndexDao.selectOthersTaskInfo(task));
        //查询所有列表
        List<wm_task_class> taskClasses = taskClassDao.selectClassInfo(new wm_task_class());
        //拿到所有子节点的id
        List list = TaskClassNodeUtils.selectChildNodeID(taskClasses, task.getTask_class_id());
//        s += task.getTask_class_id();

        return taskIndexDao.getTaskIndexModelInfo(list);
    }

    @Override
    public List selectTaskCheckListInfo(wm_task_index taskIndex) {
        //获取当前任务索引节点的所有子节点任务索引ID
        List list = TaskClassNodeUtils.selectChildNodeID(taskIndex.getTask_class_id());
        //追加当前任务索引节点ID
        taskIndex.setChild_id_list(list);

        //获取当前任务索引节点的根节点ID
        String rootNodeID = TaskClassNodeUtils.selectRootNode(taskIndex.getTask_class_id());

        //设置父ID为空，此处查询是根据task_class_id的in查询，所以把task_class_id设置为空，用child_id_list去查
        taskIndex.setTask_class_id(null);


        switch (rootNodeID) {
            case TaskClassId.ZTRW_ID:
                //制图任务
                return taskClassDao.selectTaskByClassAndYear(list, taskIndex.getTask_year(),
                        EnumTaskState.JHXD.getTaskStateString());
            case TaskClassId.SBRW_ID:
                //书表任务
                return taskIndexDao.selectBookTaskListInfo(taskIndex);
            case TaskClassId.YJBZ_ID:
                //应急保障任务
                return taskIndexDao.selectOthersTaskListInfo(taskIndex);
            default:
                return new ArrayList<>();
        }

    }

    @Override
    public List selectDeptTaskCheckListInfo(wm_task_index taskIndex, Integer pageNum, Integer pageSize) {
        String rootNodeId = TaskClassNodeUtils.selectRootNode(taskIndex.getTask_class_id());
        List<String> list = TaskClassNodeUtils.selectChildNodeID(taskIndex.getTask_class_id());
                taskIndex.setChild_id_list(list);

        //注意：分页开启的顺序很重要，不要随意移动
        PageHelper.startPage(pageNum, pageSize);
        switch (rootNodeId) {
            //制图任务
            case TaskClassId.ZTRW_ID:
                return taskIndexDao.selectDeptMapTaskListInfo(taskIndex);
            //书表任务
            case TaskClassId.SBRW_ID:
                return taskIndexDao.selectDeptBookTaskListInfo(taskIndex);
            //应急保障任务
            case TaskClassId.YJBZ_ID:
                return taskIndexDao.selectDeptOthersTaskListInfo(taskIndex);
            default:
                //全部任务类型数据
                return taskIndexDao.selectallTaskListInfo(taskIndex);
        }
    }


    @Override
    public void checkOutcheckdepttaskByIds(String ids, HttpServletResponse response) throws Exception {
        List<TaskIndexModel> taskIndexModels = new ArrayList<>();
        List<String> lstIds = Arrays.asList(ids.split(","));

        //查询年度任务
        List<TaskIndexModel> list = taskIndexDao.selectCommontByIds(lstIds);

        //查询应急任务
        List<TaskIndexModel> list2 = taskIndexDao.selectOthersByIds(lstIds);

        taskIndexModels.addAll(list);
        taskIndexModels.addAll(list2);
        List<TaskIndexModel> collect = taskIndexModels.stream().distinct().collect(Collectors.toList());
        ExeclUtil.listtoExecl(collect, response,
                TaskExcelHeader.TASK_INDEX_MODEL.getLinkedHashMap(), "质控列表导出");

    }


    @Override
    public RestResponse<String> updateTaskIndexStateInfoByList(String indexIdList, EnumTaskState state) {
        List<String> lstIDs = Arrays.asList(indexIdList.split(","));

        //单个的逻辑//查看　　是否分配　　作业室和年份
        //        //更新任务状态
        //        //如果是下发  要往　　taskinfo插入数据，后台要生成Task_info_id，还有当前时间　　　　　改的东西有：
        //        //生成id
        ////        taskInfo.setTask_index_id(indexID);
        //        //生成时间


        //下发前 查询未分配年份，和分配作业室的id
        List<String> unAssignIds = selectIsAllocation(lstIDs);
        String unAssignId = "";
        for (String id : unAssignIds) {
            unAssignId += id + ",";
        }
        if (unAssignIds.size() > 0) {
            return RestResponseUtil.note("有任务没有分配年份或作业室，任务名称为" + unAssignId);
        }

        //修改任务状态
        int res = updateTaskStateByList(lstIDs, state.getTaskStateString());
        if (res > 0) {
            return RestResponseUtil.createResponse(RestResponseCode.SUCCESS, "任务索引状态信息更新成功！");
        } else {
            return RestResponseUtil.createResponse(RestResponseCode.FAIL, "任务索引状态信息更新失败！");
        }
    }

    @Override
    public List<TaskIndexModel> selectDeptTaskIndexInfo(TaskIndexModel index) {
        return taskIndexDao.selectDeptTaskIndexInfo(index);
    }

    @Override
    public RestResponse<wm_task_index> getTaskIndexInfoById(String indexId) {
        wm_task_index taskIndexInfoById = taskIndexDao.getTaskIndexInfoById(indexId);
        if (taskIndexInfoById!=null) {
            return RestResponseUtil.success(taskIndexInfoById);
        }
        return RestResponseUtil.error(300,"任务索引信息检索为空");
    }

    @Override
    public int updateTaskName(String name,String taskId) {
        return taskIndexDao.updateTaskName(name,taskId);
    }


    @Override
    public int addTask(wm_task_index taskIndex) {

        /* 1.先查询有没有资料，没有资料不能新建任务，先添加资料
         * 2.新建任务
         */

        boolean isExist = dataIsExist(taskIndex.getData_code(), taskIndex.getTask_class_id());

        if (isExist) {
            return taskIndexDao.insertTaskIndexInfo(taskIndex);
        }

        return -1;
    }

    /**资料是否存在　
     * @param dataCode 资料编号
     * @param taskClassId 任务分类id
     * @return 查询资料结果
     */
    private boolean dataIsExist(String dataCode, String taskClassId) {

        if (StringUtils.isBlank(dataCode)||StringUtils.isBlank(taskClassId)) {
            return false;
        }

        String parentId = TaskClassNodeUtils.selectRootNode(taskClassId);
        if (TaskClassId.ZTRW_ID.equals(parentId)){
            List<map_data> mapData = sysDataOperationDao.queryObjectByMapCode(taskClassId);
            return CollectionUtils.isNotEmpty(mapData);
        }
        if (TaskClassId.SBRW_ID.equals(parentId)) {
            port_data portDataByPortNum = sysDataOperationDao.getPortDataByPortNum(dataCode);
            return portDataByPortNum != null;
        }
        return false;
    }
}
