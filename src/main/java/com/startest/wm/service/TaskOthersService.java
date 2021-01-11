package com.startest.wm.service;

import com.startest.wm.pojo.wm_task_index;
import com.startest.wm.pojo.wm_task_others;
import com.startest.wm.utils.customresponse.RestResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author 杨世凯
 * @公司 北京星天科技
 * @创建时间 2020-05-11-13:25
 * @描述 应急保障任务接口
 */
public interface TaskOthersService {

    /**
     * 检索应急保障任务信息
     * @param taskOthers 应急保障任务对象
     * @return 应急保障任务对象列表
     */
    List<wm_task_others> getTaskOthersInfo(wm_task_others taskOthers);

    /**
     * 获取应急保障任务默认编号
     * @return 应急任务默认编号
     */
    String getTaskOtherCode();

    /**
     * 新增应急保障任务信息
     * @param taskOthers
     * @return
     */
    void addTaskOthersInfo(wm_task_others taskOthers);


    /**
     * 修改应急保障任务信息
     * @param taskOthers
     * @return
     */
    int editTaskOthersInfo(wm_task_others taskOthers);

    /**
     * 删除应急保障任务信息
     * @param strID 应急任务id
     * @return 删除条数
     */
    int deleteTaskOthersInfo(@Param("strID") String strID);



    /**根据分类，批量导入任务
     * @param classId 　任务分类id
     * @param multipartFile  导入excle文件
     * @return 结果
     */
    RestResponse<String> listTaskByClassId(String classId, MultipartFile multipartFile);
}
