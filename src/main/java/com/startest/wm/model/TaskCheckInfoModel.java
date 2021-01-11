package com.startest.wm.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-07-09-16:40
 * @描述 任务质检信息模型
 */
public class TaskCheckInfoModel {
    @ApiModelProperty("总工天")
    private String all_work_days;
    @ApiModelProperty("制图编辑/编辑设计")
    private String chartbookeditor;
    @ApiModelProperty("作业员")
    private String chartbookmaker;
    @ApiModelProperty("组长")
    private String chartbookleader;

    public String getAll_work_days() {
        return all_work_days;
    }

    public void setAll_work_days(String all_work_days) {
        this.all_work_days = all_work_days;
    }

    public String getChartbookeditor() {
        return chartbookeditor;
    }

    public void setChartbookeditor(String chartbookeditor) {
        this.chartbookeditor = chartbookeditor;
    }

    public String getChartbookmaker() {
        return chartbookmaker;
    }

    public void setChartbookmaker(String chartbookmaker) {
        this.chartbookmaker = chartbookmaker;
    }

    public String getChartbookleader() {
        return chartbookleader;
    }

    public void setChartbookleader(String chartbookleader) {
        this.chartbookleader = chartbookleader;
    }
}
