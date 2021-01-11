package com.startest.wm.dao;

import com.startest.wm.enums.EnumTaskChartDistributionType;
import com.startest.wm.pojo.wm_task_check;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName:startestwm
 * @PackageName:com.startest.wm.dao
 * @ClassName:TaskBookCalculationHelperDao
 * @Description:
 * @author: skj
 * @date 2020/9/26  17:01
 */
@Repository
public interface TaskBookCalculationHelperDao {
    /**
     * 根据任务id查询错漏情况
     * @param taskIndexID 任务索引ID
     * @param disContent 分配的书表工作内容 如：第一章，第二章
     * @return
     */
    public List<wm_task_check> selectTaskCheckByIndexId(@Param("taskIndexID") String taskIndexID,
                                                        @Param("disContent") String disContent);

    /**
     * 根据任务id和对应职位，查询的错漏情况
     * @param taskIndexID 任务索引ID
     * @param type 职务类型：校对，审查，验收
     * @param disContent 分配的工作内容：第一章，第二章等
     * @return
     */
    List<wm_task_check> selectTaskcheckByTaskIdandDisType(@Param("taskIndexID") String taskIndexID,
                                                          @Param("type") EnumTaskChartDistributionType type,
                                                          @Param("disContent") String disContent);
}
