package com.startest.wm.pojo.vo;

/**
 * 制图任务
 * @author skj
 * @date 2020/12/31 0031 9:37
 */
public class CartographyTaskVo extends TaskVo{

    /**
     * 任务进度
     */
    private String task_rate;
    /**
     * 进度说明
     */
    private String task_rate_describe;

    /**
     * 资料id
     */
    private String task_data_id;

    /**
     * 出厂时间
     */
    private String task_print_date;


    /**
     * 图号
     */
    private String data_code;

    /**
     * 图名
     */
    private String data_name;


    public String getTask_rate() {
        return task_rate;
    }

    public void setTask_rate(String task_rate) {
        this.task_rate = task_rate;
    }

    public String getTask_rate_describe() {
        return task_rate_describe;
    }

    public void setTask_rate_describe(String task_rate_describe) {
        this.task_rate_describe = task_rate_describe;
    }

    public String getTask_data_id() {
        return task_data_id;
    }

    public void setTask_data_id(String task_data_id) {
        this.task_data_id = task_data_id;
    }

    public String getTask_print_date() {
        return task_print_date;
    }

    public void setTask_print_date(String task_print_date) {
        this.task_print_date = task_print_date;
    }

    public String getData_code() {
        return data_code;
    }

    public void setData_code(String data_code) {
        this.data_code = data_code;
    }

    public String getData_name() {
        return data_name;
    }

    public void setData_name(String data_name) {
        this.data_name = data_name;
    }
}
