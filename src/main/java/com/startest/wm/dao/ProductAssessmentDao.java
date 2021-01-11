package com.startest.wm.dao;

import com.startest.wm.pojo.wm_quality_book;
import com.startest.wm.pojo.wm_quality_chart;
import com.startest.wm.pojo.wm_quality_user;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName:startestwm
 * @PackageName:com.startest.wm.dao
 * @ClassName:productAssessmentDao
 * @Description:
 * @author: skj
 * @date 2020/7/28  16:11
 */
@Repository
public interface ProductAssessmentDao {


    List<wm_quality_chart> chartProductSelect(@Param("chartType") String chartType,@Param("start") String start,@Param("end") String end);

    List<Map> sailingBookTableSelect(@Param("start")String start, @Param("end") String end);
}
