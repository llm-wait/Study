package com.startest.wm.pojo;

import com.startest.wm.utils.MyDateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-06-22-16:38
 * @描述 海图产品质量评定
 */
@ApiModel("海图产品质量评定模型")
public class wm_quality_chart {
    @ApiModelProperty("唯一ID")
    private String chart_id;
    @ApiModelProperty("任务ID")
    private String task_index_id;
    @ApiModelProperty("产品类型（普通海图、图集、s57图）")
    private String chart_type;

    @ApiModelProperty("年份")
    @DateTimeFormat(pattern= MyDateUtils.DATE_PATTERN_SECOND)
    private Date task_year;
    @ApiModelProperty("图号")
    private String chart_code;
    @ApiModelProperty("图名")
    private String chart_name;
    @ApiModelProperty("纸质图每工天缺陷值")
    private String paperchart_value;
    @ApiModelProperty("数字图每工天缺陷值")
    private String elechart_value;
    @ApiModelProperty("综合缺陷值")
    private String all_value;
    @ApiModelProperty("质量评定")
    private String chart_quality_evaluation;
    @ApiModelProperty("季度")
    private String quarter;


    public Date getTask_year() {
        return task_year;
    }

    public void setTask_year(Date task_year) {
        this.task_year = task_year;
    }

    public String getChart_id() {
        return chart_id;
    }

    public void setChart_id(String chart_id) {
        this.chart_id = chart_id;
    }

    public String getTask_index_id() {
        return task_index_id;
    }

    public void setTask_index_id(String task_index_id) {
        this.task_index_id = task_index_id;
    }

    public String getChart_type() {
        return chart_type;
    }

    public void setChart_type(String chart_type) {
        this.chart_type = chart_type;
    }


    public String getChart_code() {
        return chart_code;
    }

    public void setChart_code(String chart_code) {
        this.chart_code = chart_code;
    }

    public String getChart_name() {
        return chart_name;
    }

    public void setChart_name(String chart_name) {
        this.chart_name = chart_name;
    }

    public String getPaperchart_value() {
        return paperchart_value;
    }

    public void setPaperchart_value(String paperchart_value) {
        this.paperchart_value = paperchart_value;
    }

    public String getElechart_value() {
        return elechart_value;
    }

    public void setElechart_value(String elechart_value) {
        this.elechart_value = elechart_value;
    }

    public String getAll_value() {
        return all_value;
    }

    public void setAll_value(String all_value) {
        this.all_value = all_value;
    }

    public String getChart_quality_evaluation() {
        return chart_quality_evaluation;
    }

    public void setChart_quality_evaluation(String chart_quality_evaluation) {
        this.chart_quality_evaluation = chart_quality_evaluation;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    @Override
    public String toString() {
        return "wm_quality_chart{" +
                "chart_id='" + chart_id + '\'' +
                ", task_index_id='" + task_index_id + '\'' +
                ", chart_type='" + chart_type + '\'' +
                ", task_year='" + task_year + '\'' +
                ", chart_code='" + chart_code + '\'' +
                ", chart_name='" + chart_name + '\'' +
                ", paperchart_value='" + paperchart_value + '\'' +
                ", elechart_value='" + elechart_value + '\'' +
                ", all_value='" + all_value + '\'' +
                ", chart_quality_evaluation='" + chart_quality_evaluation + '\'' +
                '}';
    }
}
