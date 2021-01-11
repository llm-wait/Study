package com.startest.wm.service.qualityevaluation;

import com.startest.wm.pojo.wm_quality_user;

import java.util.List;

/**
 * @ProjectName:startestwm
 * @PackageName:com.startest.wm.service.PersonalAssessmentService
 * @ClassName:PersonalAssessmentService
 * @Description:
 * @author: skj
 * @date 2020/7/28  13:43
 */
public interface PersonalAssessmentService {

    /**条件查询个人质量评估列表
     * @param wmQualityUser
     * @return
     */
    public List<wm_quality_user> personalSelect(wm_quality_user wmQualityUser);


    List<String> selectNameByStation(String dept_id, String dept_name);

    /**查询组长列表
     * @return
     * @param stationName
     */
    List<String> selectGroupLeader(String stationName);
}
