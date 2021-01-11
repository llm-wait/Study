package com.startest.wm.service.qualityevaluation;

import com.startest.wm.pojo.wm_quality_book;
import com.startest.wm.pojo.wm_quality_chart;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName:startestwm
 * @PackageName:com.startest.wm.service
 * @InterfaceName:ProductAssessmentService
 * @Description:
 * @author: skj
 * @date 2020/7/28  17:23
 */
public interface ProductAssessmentService {
    /**条件查询产品质量评估
     * @param year
     * @param quarter
     * @param chartType
     * @return
     */
    List<wm_quality_chart> chartProductSelect(String year, String quarter, String chartType);


    /**航海书表质量评估
     * @param year
     * @param quarter
     * @return
     */
    List<Map> sailingBookTableSelect(String year, String quarter);
}
