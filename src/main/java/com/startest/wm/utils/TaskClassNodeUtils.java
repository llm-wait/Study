package com.startest.wm.utils;

import com.startest.wm.dao.TaskClassDao;
import com.startest.wm.pojo.wm_task_class;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * 任务找节点工具类
 * @author skj
 * @date 2020/11/27 0027 18:54
 */
@Component
public  class TaskClassNodeUtils {

    private final TaskClassDao classDao;
    private static TaskClassDao taskClassDao;

    public TaskClassNodeUtils(TaskClassDao classDao) {
        this.classDao = classDao;
    }


    @PostConstruct
    private void init(){
        taskClassDao = this.classDao;
    }

    /**
     * @Description: 根据当前任务分类id,获取它的顶级id
     * @param list, currentClassId]所有任务分类记录列表，当前选中的任务分类ID
     * @return java.lang.String 当前节点的顶级父节点
     **/
    public static String selectRootNode(List<wm_task_class> list, String currentClassId) {
        if (list==null||list.isEmpty()||currentClassId == null || currentClassId.isEmpty()){
            return "";
        }
        if (list.stream().noneMatch(task -> task.getClass_id().equals(currentClassId))) {
            return "";
        }

       return rootNode(list, currentClassId);
    }

    /**根据当前任务分类id,获取它的顶级id
     * @param currentClassId 当前任务id
     * @return 当前任务顶级id
     */
    public static String selectRootNode(String currentClassId) {
        if (currentClassId == null || currentClassId.isEmpty()){
            return "";
        }
        return rootNode(taskClassDao.selectClassInfo(new wm_task_class()), currentClassId);
    }

    /**递归找顶级节点方法
     * @param list
     * @param currentClassId
     * @return
     */
    private static String rootNode(List<wm_task_class> list, String currentClassId) {
        wm_task_class taskClass = ListUtils.select(list, x -> x.getClass_id().equals(currentClassId)).get(0);
        if ("0".equals(taskClass.getParent_id())) {
            return currentClassId;
        } else {
            return rootNode(list, taskClass.getParent_id());
        }
    }


    /**
     * @Description: 获取指定任务索引ID所有子类和本id
     * @Param: [list, currentClassId, childTaskClassStr]所有任务分类记录列表，当前选中的任务分类ID,所有子类任务的ID串
     * @return: java.lang.String
     **/
    public static List<String> selectChildNodeID(List<wm_task_class> list, String currentClassId) {

        List<String> arrayList = new ArrayList<>();
        if (list==null||list.isEmpty()||currentClassId == null || currentClassId.isEmpty()){
            return new ArrayList<String>();
        }
        return selectChildNode(list, currentClassId,arrayList);

    }


    public static List<String> selectChildNodeID(String currentClassId) {

        if (currentClassId == null || currentClassId.isEmpty()){
            return new ArrayList<String>();
        }
        return selectChildNode(taskClassDao.selectClassInfo(new wm_task_class()), currentClassId,new ArrayList<>());

    }

    private static List<String> selectChildNode(List<wm_task_class> list,
    String currentClassId, List<String> res) {
        List<wm_task_class> childList = ListUtils.select(list, x -> x.getParent_id().equals(currentClassId));
        if(childList != null && !childList.isEmpty()){
            for (wm_task_class taskClass : childList) {
                selectChildNode(list, taskClass.getClass_id(),res);
            }
        }
                res.add(currentClassId);
        return res;
    }

    /**获取所有任务分类id
     * @return
     */
    public static List<wm_task_class> getAllTaskClass(){
      return  taskClassDao.selectClassInfo(new wm_task_class());
    }

    public static void main(String[] args) {

    }
}
