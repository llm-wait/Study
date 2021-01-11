package com.startest.wm.dao;

import com.startest.wm.pojo.wm_task_class;
import com.startest.wm.pojo.wm_task_index;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 杨世凯
 * @公司 北京星天科技
 * @创建时间 2020-04-08-9:56
 * @描述 任务分类信息表映射
 */
@Repository
public interface TaskClassDao {
    /**
     * 获取任务分类详细信息
     * @param taskClass 分类对象
     * @return 任务分类信息
     */
    List<wm_task_class> selectClassInfo(wm_task_class taskClass);

    /**
     * 查找分类条件存在几条
     * @param classID 种类id
     * @param parentID 种类父id
     * @param className 种类名称
     * @return 查询到几条
     */
    Integer isTaskClassExist(
            @Param("class_id") String classID,
            @Param("parentID") String parentID,
            @Param("className") String className);

    /**
     * 获取子节点最大排序值
     * @param parentID 父id
     * @return 子节点最大值
     */
    Integer getNodeMaxOrderNumber(@Param("parentID") String parentID);

    /**
     * 新增任务分类信息
     * @param taskCalss
     * @return
     */
    int insertClassInfo(wm_task_class taskCalss);

    /**
     * 修改任务分类信息
     * @param taskClass
     * @return
     */
    int updateClassInfo(wm_task_class taskClass);

    /**
     * 修改部分分类信息
     * @param taskClass 任务分类对象
     * @return 修改条数
     */
    int editPartClassInfo(wm_task_class taskClass);

    /**
     * 删除任务分类信息
     * @param classId 分类id
     * @return 删除条数
     */
    int deleteClassInfo(@Param("classId") String classId);


    /**批量修改部门分配状态
     * @param ids 任务id数组
     */
    void updateDept(@Param("ids") String[] ids);

    /**查询指定任务类型和年份的任务信息
     * @param calssIds 任务分类id集合
     * @param taskYear 任务年份
     * @return 任务信息（任务分类名称，图号，任务状态，是否分配）
     */
    List<wm_task_index> selectTaskByClassAndYear(@Param("calssIds") List<String> calssIds,@Param(
            "taskYear") String taskYear,@Param("taskState") String taskState);


    /**查询全部任务分类信息
     * @return
     */
    List<wm_task_class> selectTaskClass();

    /**根据分类id,查询分类名称
     * @param classID 分类id
     */
    String selectTaskNameById(String classID);
}
