package com.startest.wm.service;

import com.startest.wm.model.SysOrganizationTree;
import com.startest.wm.model.TaskClassFieldModel;
import com.startest.wm.pojo.map_data;
import com.startest.wm.pojo.wm_task_class;
import com.startest.wm.pojo.wm_task_index;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-04-08-16:57
 * @描述 任务种类业务逻辑接口
 */
public interface TaskClassService {
    /**
     * 获取所有分类信息
     * @return 所有分类信息集合
     */
    List<wm_task_class> getAllTaskClassInfo();


    /**获取任务分类树结构
     * @param type 任务分类类型 0筹划任务，1任务过程分类
     * @return 种类树信息
     */
    List<wm_task_class> getTreeTaskClassInfo(Integer type);

    /**
     * 获取修改分类数据接口
     * @param strClassId   当前任务分类id
     * @return 修改的任务分类树结构
     */
    List<wm_task_class> getTreeTaskClassEditList(String strClassId);

    /**
     * 获取树形部门信息
     * @return 部门树列表
     */
    SysOrganizationTree getTreeDeptListInfo();

    /**
     * 判断是否有符合条件的分类，全部参数符合
     * @param classId 种类id
     * @param parentId 父id
     * @param className  分类名称
     * @return 是否查找到符合条件的分类 ,有返回true
     */
    boolean isTaskClassExist(String classId, String parentId, String className);//todo:  还未进行测试，一共有三个方法调用

    /**
     * 获取当前节点下子节点的最大次序值
     * @param parentId 父id
     * @return 子节点最大值
     */
    Integer getMaxOrderNumberOnParentNode(String parentId);

    /**
     * 根据分类对象，获取任务分类空缺信息
     * @param taskClass 任务分类对象
     * @return 任务分类信息
     */
    List<wm_task_class> selectClassInfo(wm_task_class taskClass);


    /**
     * 新增任务分类信息
     * @param taskCalss 分类对象
     * @return 成功几条
     */
    int insertClassInfo(wm_task_class taskCalss);

    /**
     * 修改任务分类信息
     * @param taskClass 任务分类对象
     * @return 修改条数
     * todo 没有用到
     */
    int updateClassInfo(wm_task_class taskClass);

    /**
     * 修改部分分类信息
     * @param taskClass 任务种类对象
     * @return 修改条数
     */
    int editPartClassInfo(wm_task_class taskClass);

    /**
     * 删除任务分类信息
     * @param classId 分类id
     * @return 删除条数
     */
    int deleteClassInfo(String classId);

    /**
     * 通过ClassID获取种类的列表字段
     * @param strClassId 种类id
     * @return 任务种类列表的表头
     */
    List<TaskClassFieldModel> getListFieldByClassId(String strClassId);

    /**查询未分配部门的  筹划任务列表 (新增)
     *
     * @param pageNum 当前页
     * @param pageSize 页容量
     * @param classId 任务分类id
     * @param taskYear 任务年份
     * @return  任务信息
     */
    List<wm_task_index> getPageCheck(Integer pageNum, Integer pageSize, String classId, String taskYear);

    /**根据图id，查询地图信息
     * @param mapId 图id
     * @return 图信息
     */
    map_data getMapDataById(String mapId);

    /**获取任务列表　弃用
     * @param classId
     * @return
     */
    List getTaskList(String classId);

}
