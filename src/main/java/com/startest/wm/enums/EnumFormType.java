package com.startest.wm.enums;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-05-25-15:09
 * @描述 编务表单类型
 */
public enum EnumFormType {
    Book("标准书号申领单"),
    Data("数据工作单");

    private String formType;

    EnumFormType(String formType) {
        this.formType = formType;
    }

    public String getTaskStateString() {
        return formType;
    }
}
