package com.startest.wm.service.impl;

import com.startest.wm.dao.ProductAssessmentDao;
import com.startest.wm.pojo.wm_quality_chart;
import com.startest.wm.service.qualityevaluation.ProductAssessmentService;
import com.startest.wm.utils.MyDateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName:startestwm
 * @PackageName:com.startest.wm.service.impl
 * @ClassName:ProductAssessmentServiceimpl
 * @Description: 产品质量评估实现类
 * @author: skj
 * @date 2020/7/28  17:27
 */
@Service
public class ProductAssessmentServiceimpl implements ProductAssessmentService {

    @Autowired
    private ProductAssessmentDao productAssessmentDao;


    @Override
    public List<wm_quality_chart> chartProductSelect(String year, String quarter, String chartType) {
        String start = MyDateUtils.quarterStart(year, quarter);
        String end = MyDateUtils.quarterEnd(year, quarter);


        return productAssessmentDao.chartProductSelect(chartType,start,end);
    }


    @Override
    public List<Map> sailingBookTableSelect(String year, String quarter) {
        String start = MyDateUtils.quarterStart(year, quarter);
        String end = MyDateUtils.quarterEnd(year, quarter);
        List<Map> map = productAssessmentDao.sailingBookTableSelect(start, end);


        return map;
    }
}
