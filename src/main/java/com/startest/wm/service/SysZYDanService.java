package com.startest.wm.service;

import com.startest.wm.model.TaskMapModel;
import com.startest.wm.pojo.*;

import java.util.List;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-05-11 10:20
 * @描述 制印通知单操作服务接口
 **/
public interface SysZYDanService {

    /*******************************map之MY******************************/

    int insertMapMZyd(jcd_mapm mapjcd);

    int insertMapMZyd(TaskMapModel taskMapModel, String taskName, String taskType, String sb, String zyy, String bjy, String ysy);

    int insertMapMZydMany(List<jcd_mapm> jcdMapList);

    int updateMapMZyd(jcd_mapm mapjcd);

    int updateMapMZydState(String id);

    int deleteMapMZyd(String id);

    jcd_mapm queryMapMZydByID(String id);

    jcd_mapm queryMapMZydByBh(Integer bh);

    List<jcd_mapm> queryMapMZydByDefine(Integer taskYear, String taskName, String taskState, String mapCode, String mapName, String sb);

    List<jcd_mapm> queryMapMZydAll();

    boolean isMapMJcdExist(Integer jcdMapBh);


    /*******************************map之JY******************************/
    int insertMapJZyd(TaskMapModel taskMapModel, String taskName, String taskType, String sb, String zyy, String bjy, String ysy);

    int insertMapJZyd(jcd_mapj mapjcd);

    int insertMapJZydMany(List<jcd_mapj> jcdMapList);

    int updateMapJZyd(jcd_mapj mapjjcd);

    int updateMapJZydState(String id);

    int deleteMapJZyd(String id);

    jcd_mapj queryMapJZydByID(String id);

    jcd_mapj queryMapJZydByBh(Integer bh);

    List<jcd_mapj> queryMapJZydByDefine(Integer taskYear, String taskName, String taskState, String mapCode, String mapName, String sb);

    List<jcd_mapj> queryMapJZydAll();

    boolean isMapJJcdExist(Integer jcdMapBh);


    /*******************************map之BZBD******************************/
    int insertMapBZyd(TaskMapModel taskMapModel, String taskName, String taskType, String sb, String zyy, String bjy, String ysy);

    int insertMapBZyd(jcd_mapb mapjcd);

    int insertMapBZydMany(List<jcd_mapb> jcdMapList);

    int updateMapBZyd(jcd_mapb mapjcd);

    int updateMapBZydState(String id);

    int deleteMapBZyd(String id);

    jcd_mapb queryMapBZydByID(String id);

    jcd_mapb queryMapBZydByBh(Integer bh);

    List<jcd_mapb> queryMapBZydByDefine(Integer taskYear, String taskName, String taskState, String mapCode, String mapName, String sb);

    List<jcd_mapb> queryMapBZydAll();

    boolean isMapBJcdExist(Integer jcdMapBh);


    /*******************************book之MY******************************/

    /**
     * @Description: 新增书表相关制印通知单
     * @Param: [taskIndexID, taskYear, taskName, taskType, taskState, sb, sh, sm]
     * 任务ID，任务年份，任务名称，任务类型，任务状态，室别，书号，书名
     * @return: int
     **/
    int insertBookMZyd(String taskIndexID, Integer taskYear, String taskName, String taskType, String taskState, String sb, String sh, String sm, String zyy, String bjy, String ysy);

    int insertBookMZyd(jcd_bookm bookjcd);

    int insertBookMZydMany(List<jcd_bookm> jcdBookList);

    int updateBookMZyd(jcd_bookm bookjcd);

    int updateBookMZydState(String id);

    int deleteBookMZyd(String id);

    jcd_bookm queryBookMZydByID(String id);

    jcd_bookm queryBookMZydByBh(Integer bh);

    List<jcd_bookm> queryBookMZydByDefine(Integer taskYear, String taskName, String taskState, String sh, String sm, String sb);

    List<jcd_bookm> queryBookMZydAll();

    boolean isBookMJcdExist(Integer jcdBookBh);

    /*******************************book之JY******************************/
    int insertBookJZyd(String taskIndexID, Integer taskYear, String taskName, String taskType, String taskState, String sb, String sh, String sm, String zyy, String bjy, String ysy);

    int insertBookJZyd(jcd_bookj bookjcd);

    int insertBookJZydMany(List<jcd_bookj> jcdBookList);

    int updateBookJZyd(jcd_bookj bookjcd);

    int updateBookJZydState(String id);

    int deleteBookJZyd(String id);

    jcd_bookj queryBookJZydByID(String id);

    jcd_bookj queryBookJZydByBh(Integer bh);

    List<jcd_bookj> queryBookJZydByDefine(Integer taskYear, String taskName, String taskState, String sh, String sm, String sb);

    List<jcd_bookj> queryBookJZydAll();

    boolean isBookJJcdExist(Integer jcdBookBh);

    /*******************************book之BZBD******************************/
    int insertBookBZyd(String taskIndexID, Integer taskYear, String taskName, String taskType, String taskState, String sb, String sh, String sm, String zyy, String bjy, String ysy);

    int insertBookBZyd(jcd_bookb bookjcd);

    int insertBookBZydMany(List<jcd_bookb> jcdBookList);

    int updateBookBZyd(jcd_bookb bookjcd);

    int updateBookBZydState(String id);

    int deleteBookBZyd(String id);

    jcd_bookb queryBookBZydByID(String id);

    jcd_bookb queryBookBZydByBh(Integer bh);

    List<jcd_bookb> queryBookBZydByDefine(Integer taskYear, String taskName, String taskState, String sh, String sm, String sb);

    List<jcd_bookb> queryBookBZydAll();

    boolean isBookBJcdExist(Integer jcdBookBh);

    /*******************************uvph******************************/

    int insertUvphZyd(jcd_uvph uvph);

    int insertUvphZydMany(List<jcd_uvph> jcdUvphList);

    int updateUvphZyd(jcd_uvph uvph);

    int updateUvphZydState(String id);

    int deleteUvphZyd(String id);

    jcd_uvph queryUvphZydByID(String id);

    jcd_uvph queryUvphZydByBh(Integer bh);

    List<jcd_uvph> queryUvphZydByDefine(Integer taskYear, String taskName, String taskState, String mapCode, String mapName, String sb);

    List<jcd_uvph> queryUvphZydAll();

    boolean isUvphJcdExist(Integer jcdUvphBh);
}
