package com.startest.wm.utils;

import java.util.UUID;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-04-09 10:31
 * @描述 唯一ID生成器
 **/
public class UUIDGeneratorUtil {
    public UUIDGeneratorUtil() {
    }

    /**
     * @Description: 获取唯一ID
     * @Param: []
     * @return: java.lang.String
     **/
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * @Description: 获取没有下划线的唯一ID
     * @Param: []
     * @return: java.lang.String
     **/
    public static String getUUIDWithoutLine() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * @Description: 获取没有下划线且字母小写的唯一ID
     * @Param: []
     * @return: java.lang.String
     **/
    public static String getUUIDWithoutLineAndToLower() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }

    /**
     * @Description: 获取没有下划线且字母大写的唯一ID
     * @Param: []
     * @return: java.lang.String
     **/
    public static String getUUIDWithoutLineAndToUpper() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    public static void main(String[] args) {
        System.out.println(getUUIDWithoutLineAndToUpper());
        System.out.println(getUUID());
        System.out.println(getUUIDWithoutLine());
    }

}
