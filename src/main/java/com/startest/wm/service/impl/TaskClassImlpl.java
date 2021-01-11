package com.startest.wm.service.impl;

import com.github.pagehelper.PageHelper;
import com.startest.wm.constant.TaskClassId;
import com.startest.wm.dao.SysDataOperationDao;
import com.startest.wm.dao.SysDeptDao;
import com.startest.wm.dao.TaskClassDao;
import com.startest.wm.enums.task.EnumTaskState;
import com.startest.wm.enums.task.EnumTaskType;
import com.startest.wm.model.SysOrganizationTree;
import com.startest.wm.model.TaskClassFieldModel;
import com.startest.wm.pojo.map_data;
import com.startest.wm.pojo.sys_dept;
import com.startest.wm.pojo.vo.TaskVo;
import com.startest.wm.pojo.wm_task_class;
import com.startest.wm.pojo.wm_task_index;
import com.startest.wm.service.TaskClassService;
import com.startest.wm.utils.TaskClassNodeUtils;
import com.startest.wm.utils.UUIDGeneratorUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @公司 北京星天科技
 * @author 杨世凯
 * @创建时间 2020-04-08-17:08
 * @描述 任务种类业务逻辑实现
 */
@Service
public class TaskClassImlpl implements TaskClassService {

    protected static final Logger log = LoggerFactory.getLogger(TaskClassImlpl.class);

    public static ArrayList<wm_task_class> child = new ArrayList<>();

    private  final  TaskClassDao taskClassDao;
    private  final  SysDeptDao sysDeptDao;
    private  final  SysDataOperationDao sysDataOperationDao;

    public TaskClassImlpl(TaskClassDao taskClassDao, SysDeptDao sysDeptDao,
                          SysDataOperationDao sysDataOperationDao) {
        this.taskClassDao = taskClassDao;
        this.sysDeptDao = sysDeptDao;
        this.sysDataOperationDao = sysDataOperationDao;
    }


    @Override
    public List<wm_task_class> getAllTaskClassInfo() {
        return taskClassDao.selectClassInfo(new wm_task_class());
    }


    @Override
    public List<wm_task_class> getTreeTaskClassInfo(Integer type) {

        wm_task_class taskClass = new wm_task_class();
        taskClass.setClass_type(type);

        //根据种类类型，查询全部分类详情
        List<wm_task_class> list = taskClassDao.selectClassInfo(taskClass);
        if (list == null || list.isEmpty()) {
            return new ArrayList<>();
        }

        List<wm_task_index> indexList = new ArrayList<>();
        //查询根节点
        List<wm_task_class> rootList = list.stream()
                .filter(s -> "0".equals(s.getParent_id()))
                .sorted(Comparator.comparing(wm_task_class::getClass_order))
                .collect(Collectors.toList());

           return searchTreeChildNode(rootList, list, indexList);
    }

    @Override
    public List<wm_task_class> getTreeTaskClassEditList(String strClassId) {
        //获取全部种类分类详情
        List<wm_task_class> allList = taskClassDao.selectClassInfo(new wm_task_class());

        //查找，指定id的顶级  id
        String strId = TaskClassNodeUtils.selectRootNode(allList, strClassId);

        if (strId == null || strId.isEmpty()) {
            return new ArrayList<>();
        }

        //查询根节点 的详情列表
        List<wm_task_class> rootList = allList.stream()
                .filter(s -> s.getClass_id().equals(strId))
                .collect(Collectors.toList());

        //查询所有的子id  详情集合
       return searchTreeChildNode(rootList, allList, new ArrayList<>());
    }

    /**
     * 递归过去所有子节点
     *
     * @param rootList 顶节点集合
     * @param list  全部分类集合
     * @param indexList  空集合
     * @return 传入集合的所有子节点
     */
    private List<wm_task_class> searchTreeChildNode(List<wm_task_class> rootList, List<wm_task_class> list, List<wm_task_index> indexList) {
        for (wm_task_class item : rootList) {
            //筛选该节点下的任务种类
            List<wm_task_class> childList = list.stream()
                    .filter(s -> s.getParent_id().equals(item.getClass_id()))
                    .sorted(Comparator.comparing(wm_task_class::getClass_order))
                    .collect(Collectors.toList());
            //筛选该节点下的任务索引
            List<wm_task_index> childIndexList = indexList.stream()
                    .filter(s -> s.getTask_class_id().equals(item.getClass_id()))
                    .collect(Collectors.toList());
            if (childIndexList.size() > 0) {
                int count = childList.size();
                for (wm_task_index childIndex : childIndexList) {
                    wm_task_class taskClass = new wm_task_class();
                    taskClass.setClass_id(childIndex.getIndex_id());
                    taskClass.setData_id(childIndex.getTask_data_id());
                    taskClass.setParent_id(childIndex.getTask_class_id());
                    taskClass.setClass_name(childIndex.getTask_name());
                    taskClass.setClass_year(childIndex.getTask_year());
                    taskClass.setClass_tag("任务");
                    taskClass.setClass_order(++count);
                    childList.add(taskClass);
                }
            }
            if (childList.size() > 0) {
                childList.sort(Comparator.comparingInt(wm_task_class::getClass_order));
                item.setChildList(childList);
                searchTreeChildNode(childList, list, indexList);
            }
        }
        return rootList;
    }

    @Override
    public boolean isTaskClassExist(String classId, String parentId, String className) {
        return taskClassDao.isTaskClassExist(classId, parentId, className) > 0;
    }

    @Override
    public Integer getMaxOrderNumberOnParentNode(String parentId) {
        return taskClassDao.getNodeMaxOrderNumber(parentId);
    }

    @Override
    public List<wm_task_class> selectClassInfo(wm_task_class taskClass) {
        return taskClassDao.selectClassInfo(taskClass);
    }

    @Override
    public int insertClassInfo(wm_task_class taskCalss) {
        return taskClassDao.insertClassInfo(taskCalss);
    }

    @Override
    public int updateClassInfo(wm_task_class taskClass) {
        return taskClassDao.updateClassInfo(taskClass);
    }

    @Override
    public int editPartClassInfo(wm_task_class taskClass) {
        return taskClassDao.editPartClassInfo(taskClass);
    }

    @Override
    public int deleteClassInfo(String classId) {
        return taskClassDao.deleteClassInfo(classId);
    }

    @Override
    public List<TaskClassFieldModel> getListFieldByClassId(String strClassId) {
        String strId = TaskClassNodeUtils.selectRootNode( strClassId);
        if (strId == null) {
            return getallDataField();
        }
        switch (strId) {
            //书表任务
            case TaskClassId.SBRW_ID:
                return getBookDataField();
            //应急保障任务
            case TaskClassId.YJBZ_ID:
                return getOthersTaskField();
            //制图任务
            case TaskClassId.ZTRW_ID:
                return getMapDataField();
            default:
                return getallDataField();
        }
    }

    /**获取通用任务表头
     * @return 表头集合
     */
    private List<TaskClassFieldModel> getallDataField() {

        List<TaskClassFieldModel> list = new ArrayList<>();
        list.add(new TaskClassFieldModel("任务年份", "task_year"));
        list.add(new TaskClassFieldModel("任务类型", "task_type"));
        list.add(new TaskClassFieldModel("图名", "data_name"));
        list.add(new TaskClassFieldModel("任务状态", "task_state"));
        list.add(new TaskClassFieldModel("图号", "data_code"));
        return list;
    }


    /**获取制图任务表头
     * @return 表头列表
     */
        private List<TaskClassFieldModel> getMapDataField() {
        List<TaskClassFieldModel> list = new ArrayList<>();
        list.add(new TaskClassFieldModel("任务名称", "task_name"));
        list.add(new TaskClassFieldModel("分配年份", "task_year"));
        list.add(new TaskClassFieldModel("任务分类", "task_type"));
        list.add(new TaskClassFieldModel("作业室", "dept_name"));
        list.add(new TaskClassFieldModel("任务状态", "task_state"));
        list.add(new TaskClassFieldModel("图号", "data_code"));
        return list;
    }

    /**获取书表任务表头
     * @return 书表任务表头
     */
    private List<TaskClassFieldModel> getBookDataField() {
        List<TaskClassFieldModel> list = new ArrayList<>();
        list.add(new TaskClassFieldModel("任务名称", "task_name"));
        list.add(new TaskClassFieldModel("分配年份","task_year"));
        list.add(new TaskClassFieldModel("任务状态","task_state"));
        list.add(new TaskClassFieldModel("港口编号","port_num"));
        list.add(new TaskClassFieldModel("港口中文名称","port_cn_name"));
        list.add(new TaskClassFieldModel("港口英文名称","port_en_name"));
        list.add(new TaskClassFieldModel("所属国家","port_country"));
        list.add(new TaskClassFieldModel("所属海区","port_sea"));
        list.add(new TaskClassFieldModel("版时","port_data_bs"));
        list.add(new TaskClassFieldModel("资料来源","port_data_source"));
        list.add(new TaskClassFieldModel("版次","bc"));
        list.add(new TaskClassFieldModel("港口类型","port_type"));
        return list;
    }

    /**获取应急保障任务表头
     * @return 应急保障任务表头列表
     */
    private List<TaskClassFieldModel> getOthersTaskField() {
        List<TaskClassFieldModel> list = new ArrayList<>();
        list.add(new TaskClassFieldModel("任务名称", "otask_name"));
        list.add(new TaskClassFieldModel("分配年份","task_year"));
        list.add(new TaskClassFieldModel("任务状态","task_state"));
        list.add(new TaskClassFieldModel("对应编号/记录编号","otask_code"));
        list.add(new TaskClassFieldModel("任务名称","otask_name"));
        list.add(new TaskClassFieldModel("任务来源","otask_source"));
        list.add(new TaskClassFieldModel("下达人员","otask_maker"));
        list.add(new TaskClassFieldModel("承担单位","otask_unit"));
        list.add(new TaskClassFieldModel("参与人数","otask_people"));
        list.add(new TaskClassFieldModel("下达时间","task_start_date"));
        list.add(new TaskClassFieldModel("具体内容","otask_content"));
        return list;
    }

    @Override
    public SysOrganizationTree getTreeDeptListInfo() {

        //最终返回的父级树结构对象
        SysOrganizationTree sysOrganizationTree = new SysOrganizationTree();
        sysOrganizationTree.setId(UUIDGeneratorUtil.getUUID());
        sysOrganizationTree.setPid("-1");

        //得到部门根节点信息　　一级，海图信息中心
        sys_dept sysDept = sysDeptDao.queryRootDept();
        sysOrganizationTree.setNodeID(sysDept.getDept_id());
        sysOrganizationTree.setNodeName(sysDept.getDept_name());
        sysOrganizationTree.setNodeType("部门");

        List<SysOrganizationTree> childOrganizationTreeList = new ArrayList<>();
        //查询子部门信息　　二级　　中国海区制图室，数据资料室
        List<sys_dept> sysDeptList = sysDeptDao.queryChildDeptByID(sysDept.getDept_id(), false);

        //把子部门信息变成树结构
        for (sys_dept sys_dept : sysDeptList) {
            SysOrganizationTree sysOrganizationTree1 = new SysOrganizationTree();
            sysOrganizationTree1.setId(UUIDGeneratorUtil.getUUID());

            sysOrganizationTree1.setPid(sys_dept.getDept_id());
            //前台任务管理用作classid
            sysOrganizationTree1.setNodeID(null);
            sysOrganizationTree1.setNodeOrder(sys_dept.getDept_order());
            sysOrganizationTree1.setNodeName(sys_dept.getDept_name());
            sysOrganizationTree1.setNodeType("部门");
            childOrganizationTreeList.add(sysOrganizationTree1);

            // 生成3种分类的结构树  三级　　制图任务　，书表任务
            List<SysOrganizationTree> childClassList = getDeptClassInfoList(sys_dept.getDept_id());
            //把3种分类的结构树，添加到原来 的子树结构中
            sysOrganizationTree1.setChildNodes(childClassList);

        }

        sysOrganizationTree.setChildNodes(childOrganizationTreeList);
        return sysOrganizationTree;
    }

    /**获取三种分类结构树
     * @param deptID
     * @return
     */
    private List<SysOrganizationTree> getDeptClassInfoList(String deptID) {
        List<SysOrganizationTree> resList = new ArrayList<>();

        //制图任务
        SysOrganizationTree sysOrganizationTree1 = new SysOrganizationTree();
        sysOrganizationTree1.setId(UUIDGeneratorUtil.getUUIDWithoutLineAndToUpper());
        sysOrganizationTree1.setPid(deptID);
        sysOrganizationTree1.setNodeID(TaskClassId.ZTRW_ID);
        sysOrganizationTree1.setNodeName(EnumTaskType.ZTRW.getTaskTypeString());
        sysOrganizationTree1.setNodeType("任务");
        resList.add(sysOrganizationTree1);

        //书表任务
        SysOrganizationTree sysOrganizationTree2 = new SysOrganizationTree();
        sysOrganizationTree2.setId(UUIDGeneratorUtil.getUUIDWithoutLineAndToUpper());
        sysOrganizationTree2.setPid(deptID);
        sysOrganizationTree2.setNodeID(TaskClassId.SBRW_ID);
        sysOrganizationTree2.setNodeName(EnumTaskType.SBRW.getTaskTypeString());
        sysOrganizationTree2.setNodeType("任务");
        resList.add(sysOrganizationTree2);

        //应急保障任务
        SysOrganizationTree sysOrganizationTree3 = new SysOrganizationTree();
        sysOrganizationTree3.setId(UUIDGeneratorUtil.getUUIDWithoutLineAndToUpper());
        sysOrganizationTree3.setPid(deptID);
        sysOrganizationTree3.setNodeID(TaskClassId.YJBZ_ID);
        sysOrganizationTree3.setNodeName(EnumTaskType.YJBZRW.getTaskTypeString());
        sysOrganizationTree3.setNodeType("任务");
        resList.add(sysOrganizationTree3);

        return resList;
    }


    @Override
    public List<wm_task_index> getPageCheck(Integer pageNum, Integer pageSize, String calssId, String taskYear) {



        List<wm_task_class> listAll = taskClassDao.selectTaskClass();
        child.clear();
        List<wm_task_class> childCalssId = getChildCalssId(listAll, calssId);

        ArrayList<String> strings = new ArrayList<>();
        for (wm_task_class task : childCalssId) {
            if (!strings.contains(task.getClass_id())) {
                strings.add(task.getClass_id());
            }
        }
        strings.add(calssId);
        PageHelper.startPage(pageNum, pageSize);
        return taskClassDao.selectTaskByClassAndYear(strings, taskYear,EnumTaskState.JHXD.getTaskStateString());
    }

    /**
     * 根据任务种类id，对比获取所有种类的子id
     *
     * @param list    所有的任务种类信息
     * @param calssId 种类父id
     * @return 种类的子id
     */
    public List<wm_task_class> getChildCalssId(List<wm_task_class> list, String calssId) {
        for (wm_task_class taskClass : list) {
            //遍历出父id等于参数的id，add进子节点集合
            if (calssId.equals(taskClass.getParent_id())) {
                //递归遍历下一级
                getChildCalssId(list, taskClass.getClass_id());
                child.add(taskClass);
            }
        }
        return child;
    }

    @Override
    public map_data getMapDataById(String mapId) {
        return sysDataOperationDao.queryObjectByMapID(mapId);
    }

    @Override
    public List getTaskList(String classId) {
        if (TaskClassId.ZTRW_ID.equals(classId)) {
            return getCartographyTask();
        }

        return new ArrayList();
    }


    /**获取制图任务列表　　弃用
     * @return 任务列表
     */
    private List<TaskVo> getCartographyTask() {
        List<wm_task_class> taskClasses = taskClassDao.selectClassInfo(new wm_task_class());
        List classId = TaskClassNodeUtils.selectChildNodeID(taskClasses, TaskClassId.ZTRW_ID);

        return sysDataOperationDao.getCartographyTaskByClassIdAndState(classId,
                EnumTaskState.JHXD.getTaskStateString());

    }
}
