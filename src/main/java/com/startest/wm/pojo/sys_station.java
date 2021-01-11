package com.startest.wm.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-03-20 15:35
 * @描述 系统岗位信息表实体
 **/
@ApiModel("岗位管理操作模块API")
public class sys_station {
    @ApiModelProperty("岗位唯一ID")
    private String station_id;//唯一ID
    @ApiModelProperty("岗位名称")
    private String station_name;//岗位名称
    @ApiModelProperty("岗位顺序")
    private Integer station_order;//岗位顺序
    @ApiModelProperty("岗位描述")
    private String description;//岗位描述

    public String getStation_id() {
        return station_id;
    }

    public void setStation_id(String station_id) {
        this.station_id = station_id;
    }

    public String getStation_name() {
        return station_name;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    public Integer getStation_order() {
        return station_order;
    }

    public void setStation_order(Integer station_order) {
        this.station_order = station_order;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
