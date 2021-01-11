package com.startest.wm.service;

import com.startest.wm.pojo.ysk_test;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-07-11-8:37
 * @描述 杨世凯测试
 */
public interface YskTestService {
    List<ysk_test> getAllList(ysk_test yt);

    int insertInfo(ysk_test yt);

    int updateInfo(ysk_test yt);

    int deleteInfo(String strID);
}
