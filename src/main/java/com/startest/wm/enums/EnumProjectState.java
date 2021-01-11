package com.startest.wm.enums;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-06-24-13:06
 * @描述 项目过程
 */
public enum EnumProjectState {
    XMLZ("项目论证"),
    SSFA("实施方案"),
    JFYSPS("经费预算评审"),
    ZQPS("中期评审"),
    ZSDGPS("验收大纲评审"),
    SYX("试运行"),
    XMYS("项目验收"),
    XMJD("项目鉴定");

    private String projectState;

    EnumProjectState(String projectState) {
        this.projectState = projectState;
    }

    public String getProjectStateString() {
        return projectState;
    }
}
