package com.startest.wm.utils;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-06-16 15:00
 * @描述 MD5加密工具类
 **/
public class MD5Utils {
    public static final String encryptToMD5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] digest = messageDigest.digest(str.getBytes("utf-8"));
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String hexStr = base64Encoder.encode(digest);
        return hexStr;
    }
}
