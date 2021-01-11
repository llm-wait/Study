package com.startest.wm.constant;

import org.apache.commons.collections4.Put;

import java.util.LinkedHashMap;

/**
 * 描述：
 *
 * @author skj
 * @PackageName:com.startest.wm.constant
 * @date 2021/1/8 0008 10:42
 */
public enum TaskExcelHeader {

    /**
     * 任务核心数据模型
     */
    TASK_INDEX_MODEL(new LinkedHashMap<String,String>(){{
        put("index_id", "索引id");
        put("task_class_id", "种类id");
        put("task_data_id", "资料id");
        put("dept_id", "部门ID");
        put("task_info_id", "任务信息ID");
        put("task_year", "任务年份");
        put("task_type", "任务类型");
        put("task_name", "任务名称");
        put("data_code", "图号");
        put("task_remark", "任务备注");
        put("task_class_tag", "图书名称");
        put("task_print_date", "出厂时间");
        put("task_type_content", "任务内容");
        put("task_start_date", "任务下达时间");
        put("task_state", "任务状态");
        put("otask_maker", "下达人");
        put("task_state_list", "任务状态列表");
    }}),
    /**
     * 任务索引
     */
    WM_TASK_INDEX(new LinkedHashMap<String, String>(){{
        put("","");
    }}),
    /**
     * 应急保障任务
     */
    WM_TASK_OTHERS(new LinkedHashMap<String, String>(){{
        put("对应编号/记录编号", "otask_code");
        put("任务名称", "otask_name");
        put("任务来源", "otask_source");
        put("下达人员", "otask_maker");
        put("承担单位", "otask_unit");
        put("参与兵力", "otask_people");
        put("下达时间", "task_start_date");
        put("具体内容", "otask_content");
        put("完成数量", "task_count");
        put("任务折算", "otask_works");
    }}),
    ;

    private LinkedHashMap<String,String> linkedHashMap;

    TaskExcelHeader(LinkedHashMap<String, String> stringStringLinkedHashMap) {
    }


    public LinkedHashMap<String, String> getLinkedHashMap() {
        return linkedHashMap;
    }

    public void setLinkedHashMap(LinkedHashMap<String, String> linkedHashMap) {
        this.linkedHashMap = linkedHashMap;
    }
}
