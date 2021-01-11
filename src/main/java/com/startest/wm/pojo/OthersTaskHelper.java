package com.startest.wm.pojo;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-05-13-11:07
 * @描述 应急保障任务帮助模型
 */
public class OthersTaskHelper {
    private List<wm_task_index> indexlist;

    private List<wm_task_others> tasklist;

    public List<wm_task_index> getIndexlist() {
        return indexlist;
    }

    public void setIndexlist(List<wm_task_index> indexlist) {
        this.indexlist = indexlist;
    }

    public List<wm_task_others> getTasklist() {
        return tasklist;
    }

    public void setTasklist(List<wm_task_others> tasklist) {
        this.tasklist = tasklist;
    }
}
