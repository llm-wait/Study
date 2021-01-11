package com.startest.wm.dao;

import com.startest.wm.pojo.ysk_test;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-07-11-8:21
 * @描述 杨世凯测试
 */
@Repository
public interface YskTestDao {

    List<ysk_test> selectStudentList(ysk_test yt);

    int insertStudentInfo(ysk_test yt);

    int updateStudentInfo(ysk_test yt);

    int deleteStudentInfo(@Param("strID")String strID);

}
