package com.startest.wm.dao;

import com.startest.wm.pojo.wm_quality_user;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName:startestwm
 * @PackageName:com.startest.wm.dao
 * @ClassName:PersonalAssessmentDao
 * @Description:
 * @author: skj
 * @date 2020/7/28  15:55
 */
@Repository
public interface PersonalAssessmentDao {


    /**条件查询个人质量评估列表
     * @param wmQualityUser
     * @return
     */
    List<wm_quality_user> personalSelect(wm_quality_user wmQualityUser);

    List<String> selectNameByStation(@Param("dept_id") String dept_id,@Param("dept_name") String dept_name);

    /**查询所有的组长名称
     * @return
     * @param stationName
     */
    List<String> selectGroupLeader(String stationName);
}
