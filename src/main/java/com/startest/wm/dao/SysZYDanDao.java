package com.startest.wm.dao;

import com.startest.wm.pojo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-05-11 10:06
 * @描述 制印相关出单表数据库映射
 **/
@Repository
public interface SysZYDanDao {
    /*******************************map之MY******************************/
    int insertMapMZyd(jcd_mapm mapjcd);

    int insertMapMZydMany(List<jcd_mapm> jcdMapList);

    int updateMapMZyd(jcd_mapm mapjcd);

    int updateMapMZydState(String id);

    int deleteMapMZyd(String id);

    jcd_mapm queryMapMZydByID(String id);

    jcd_mapm queryMapMZydByBh(Integer bh);

    List<jcd_mapm> queryMapMZydByDefine(
            @Param("taskYear") Integer taskYear,
            @Param("taskName")String taskName,
            @Param("taskState")String taskState,
            @Param("mapCode") String mapCode,
            @Param("mapName") String mapName,
            @Param("sb") String sb);

    List<jcd_mapm> queryMapMZydAll();


    /*******************************map之JY******************************/
    int insertMapJZyd(jcd_mapj mapjcd);

    int insertMapJZydMany(List<jcd_mapj> jcdMapList);

    int updateMapJZyd(jcd_mapj mapjcd);

    int updateMapJZydState(String id);

    int deleteMapJZyd(String id);

    jcd_mapj queryMapJZydByID(String id);

    jcd_mapj queryMapJZydByBh(Integer bh);

    List<jcd_mapj> queryMapJZydByDefine(
            @Param("taskYear") Integer taskYear,
            @Param("taskName")String taskName,
            @Param("taskState")String taskState,
            @Param("mapCode") String mapCode,
            @Param("mapName") String mapName,
            @Param("sb") String sb);

    List<jcd_mapj> queryMapJZydAll();


    /*******************************map之BZBD******************************/
    int insertMapBZyd(jcd_mapb mapbjcd);

    int insertMapBZydMany(List<jcd_mapb> jcdMapList);

    int updateMapBZyd(jcd_mapb mapjcd);

    int updateMapBZydState(String id);

    int deleteMapBZyd(String id);

    jcd_mapb queryMapBZydByID(String id);

    jcd_mapb queryMapBZydByBh(Integer bh);

    List<jcd_mapb> queryMapBZydByDefine(
            @Param("taskYear") Integer taskYear,
            @Param("taskName")String taskName,
            @Param("taskState")String taskState,
            @Param("mapCode") String mapCode,
            @Param("mapName") String mapName,
            @Param("sb") String sb);

    List<jcd_mapb> queryMapBZydAll();


    /*******************************book之MY******************************/
    int insertBookMZyd(jcd_bookm bookjcd);

    int insertBookMZydMany(List<jcd_bookm> jcdBookList);

    int updateBookMZyd(jcd_bookm bookjcd);

    int updateBookMZydState(String id);

    int deleteBookMZyd(String id);

    jcd_bookm queryBookMZydByID(String id);

    jcd_bookm queryBookMZydByBh(Integer bh);

    List<jcd_bookm>queryBookMZydByDefine(
            @Param("taskYear")Integer taskYear,
            @Param("taskName")String taskName,
            @Param("taskState")String taskState,
            @Param("sh")String sh,
            @Param("sm")String sm,
            @Param("sb")String sb);

    List<jcd_bookm>queryBookMZydAll();


    /*******************************book之JY******************************/
    int insertBookJZyd(jcd_bookj bookjcd);

    int insertBookJZydMany(List<jcd_bookj> jcdBookList);

    int updateBookJZyd(jcd_bookj bookjcd);

    int updateBookJZydState(String id);

    int deleteBookJZyd(String id);

    jcd_bookj queryBookJZydByID(String id);

    jcd_bookj queryBookJZydByBh(Integer bh);

    List<jcd_bookj>queryBookJZydByDefine(
            @Param("taskYear")Integer taskYear,
            @Param("taskName")String taskName,
            @Param("taskState")String taskState,
            @Param("sh")String sh,
            @Param("sm")String sm,
            @Param("sb")String sb);

    List<jcd_bookj>queryBookJZydAll();


    /*******************************book之BZBD******************************/
    int insertBookBZyd(jcd_bookb bookjcd);

    int insertBookBZydMany(List<jcd_bookb> jcdBookList);

    int updateBookBZyd(jcd_bookb bookjcd);

    int updateBookBZydState(String id);

    int deleteBookBZyd(String id);

    jcd_bookb queryBookBZydByID(String id);

    jcd_bookb queryBookBZydByBh(Integer bh);

    List<jcd_bookb>queryBookBZydByDefine(
            @Param("taskYear")Integer taskYear,
            @Param("taskName")String taskName,
            @Param("taskState")String taskState,
            @Param("sh")String sh,
            @Param("sm")String sm,
            @Param("sb")String sb);

    List<jcd_bookb>queryBookBZydAll();
    /*******************************uvph******************************/

    int insertUvphZyd(jcd_uvph uvph);

    int insertUvphZydMany(List<jcd_uvph> jcdUvphList);

    int updateUvphZyd(jcd_uvph uvph);

    int updateUvphZydState(String id);

    int deleteUvphZyd(String id);

    jcd_uvph queryUvphZydByID(String id);

    jcd_uvph queryUvphZydByBh(Integer jcdUvphBh);

    List<jcd_uvph>queryUvphZydByDefine(
            @Param("taskYear")Integer taskYear,
            @Param("taskName")String taskName,
            @Param("taskState")String taskState,
            @Param("mapCode")String mapCode,
            @Param("mapName")String mapName,
            @Param("sb")String sb);

    List<jcd_uvph>queryUvphZydAll();
}
