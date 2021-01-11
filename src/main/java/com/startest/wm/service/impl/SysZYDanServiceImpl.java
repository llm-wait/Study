package com.startest.wm.service.impl;

import com.startest.wm.dao.SysZYDanDao;
import com.startest.wm.model.TaskMapModel;
import com.startest.wm.pojo.*;
import com.startest.wm.service.SysZYDanService;
import com.startest.wm.utils.UUIDGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-05-11 13:10
 * @描述 制印通知单服务实现类
 **/
@Service
public class SysZYDanServiceImpl implements SysZYDanService {

    @Autowired
    SysZYDanDao sysZYDanDao;

    /*******************************map之MY******************************/

    @Override
    public int insertMapMZyd(jcd_mapm mapjcd) {
        return sysZYDanDao.insertMapMZyd(mapjcd);
    }

    @Override
    public int insertMapMZyd(TaskMapModel taskMapModel, String taskName, String taskType, String sb, String zyy, String bjy, String ysy) {
        jcd_mapm jcdMapm = new jcd_mapm();
        jcdMapm.setId(UUIDGeneratorUtil.getUUID());
        jcdMapm.setTask_index_id(taskMapModel.getIndex_id());
        jcdMapm.setTask_year(Integer.parseInt(taskMapModel.getTask_year()));
        jcdMapm.setTask_name(taskName);
        jcdMapm.setTask_type(taskType);
        jcdMapm.setTask_state(taskMapModel.getTask_state());
        jcdMapm.setSb(sb);
        jcdMapm.setMap_code(taskMapModel.getMap_code());
        jcdMapm.setMap_name(taskMapModel.getMap_cn_name());
        jcdMapm.setZyy(zyy);
        jcdMapm.setBjy(bjy);
        jcdMapm.setYsy(ysy);
        return sysZYDanDao.insertMapMZyd(jcdMapm);
    }

    @Override
    public int insertMapMZydMany(List<jcd_mapm> jcdMapList) {
        return sysZYDanDao.insertMapMZydMany(jcdMapList);
    }

    @Override
    public int updateMapMZyd(jcd_mapm mapjcd) {
        return sysZYDanDao.updateMapMZyd(mapjcd);
    }

    @Override
    public int updateMapMZydState(String id) {
        return sysZYDanDao.updateMapMZydState(id);
    }

    @Override
    public int deleteMapMZyd(String id) {
        return sysZYDanDao.deleteMapMZyd(id);
    }

    @Override
    public jcd_mapm queryMapMZydByID(String id) {
        return sysZYDanDao.queryMapMZydByID(id);
    }

    @Override
    public jcd_mapm queryMapMZydByBh(Integer bh) {
        return sysZYDanDao.queryMapMZydByBh(bh);
    }

    @Override
    public List<jcd_mapm> queryMapMZydByDefine(Integer taskYear, String taskName, String taskState, String mapCode, String mapName, String sb) {
        return sysZYDanDao.queryMapMZydByDefine(taskYear, taskName, taskState, mapCode, mapName, sb);
    }

    @Override
    public List<jcd_mapm> queryMapMZydAll() {
        return sysZYDanDao.queryMapMZydAll();
    }

    @Override
    public boolean isMapMJcdExist(Integer jcdMapBh) {
        return sysZYDanDao.queryMapMZydByBh(jcdMapBh) != null;
    }


    /*******************************map之JY******************************/

    @Override
    public int insertMapJZyd(jcd_mapj mapjcd) {
        return sysZYDanDao.insertMapJZyd(mapjcd);
    }

    @Override
    public int insertMapJZyd(TaskMapModel taskMapModel, String taskName, String taskType, String sb, String zyy, String bjy, String ysy) {
        jcd_mapj jcdMapj = new jcd_mapj();
        jcdMapj.setId(UUIDGeneratorUtil.getUUID());
        jcdMapj.setTask_index_id(taskMapModel.getIndex_id());
        jcdMapj.setTask_year(Integer.parseInt(taskMapModel.getTask_year()));
        jcdMapj.setTask_name(taskName);
        jcdMapj.setTask_type(taskType);
        jcdMapj.setTask_state(taskMapModel.getTask_state());
        jcdMapj.setSb(sb);
        jcdMapj.setMap_code(taskMapModel.getMap_code());
        jcdMapj.setMap_name(taskMapModel.getMap_cn_name());
        jcdMapj.setZyy(zyy);
        jcdMapj.setBjy(bjy);
        jcdMapj.setYsy(ysy);
        return sysZYDanDao.insertMapJZyd(jcdMapj);
    }

    @Override
    public int insertMapJZydMany(List<jcd_mapj> jcdMapList) {
        return sysZYDanDao.insertMapJZydMany(jcdMapList);
    }

    @Override
    public int updateMapJZyd(jcd_mapj mapjcd) {
        return sysZYDanDao.updateMapJZyd(mapjcd);
    }

    @Override
    public int updateMapJZydState(String id) {
        return sysZYDanDao.updateMapJZydState(id);
    }

    @Override
    public int deleteMapJZyd(String id) {
        return sysZYDanDao.deleteMapJZyd(id);
    }

    @Override
    public jcd_mapj queryMapJZydByID(String id) {
        return sysZYDanDao.queryMapJZydByID(id);
    }

    @Override
    public jcd_mapj queryMapJZydByBh(Integer bh) {
        return sysZYDanDao.queryMapJZydByBh(bh);
    }

    @Override
    public List<jcd_mapj> queryMapJZydByDefine(Integer taskYear, String taskName, String taskState, String mapCode, String mapName, String sb) {
        return sysZYDanDao.queryMapJZydByDefine(taskYear, taskName, taskState, mapCode, mapName, sb);
    }

    @Override
    public List<jcd_mapj> queryMapJZydAll() {
        return sysZYDanDao.queryMapJZydAll();
    }

    @Override
    public boolean isMapJJcdExist(Integer jcdMapBh) {
        return sysZYDanDao.queryMapJZydByBh(jcdMapBh) != null;
    }

    /*******************************map之BZBD******************************/

    @Override
    public int insertMapBZyd(jcd_mapb mapjcd) {
        return sysZYDanDao.insertMapBZyd(mapjcd);
    }

    @Override
    public int insertMapBZyd(TaskMapModel taskMapModel, String taskName, String taskType, String sb, String zyy, String bjy, String ysy) {
        jcd_mapb jcdMapb = new jcd_mapb();
        jcdMapb.setId(UUIDGeneratorUtil.getUUID());
        jcdMapb.setTask_index_id(taskMapModel.getIndex_id());
        jcdMapb.setTask_year(Integer.parseInt(taskMapModel.getTask_year()));
        jcdMapb.setTask_name(taskName);
        jcdMapb.setTask_type(taskType);
        jcdMapb.setTask_state(taskMapModel.getTask_state());
        jcdMapb.setSb(sb);
        jcdMapb.setMap_code(taskMapModel.getMap_code());
        jcdMapb.setMap_name(taskMapModel.getMap_cn_name());
        jcdMapb.setZyy(zyy);
        jcdMapb.setBjy(bjy);
        jcdMapb.setYsy(ysy);
        return sysZYDanDao.insertMapBZyd(jcdMapb);
    }

    @Override
    public int insertMapBZydMany(List<jcd_mapb> jcdMapList) {
        return sysZYDanDao.insertMapBZydMany(jcdMapList);
    }

    @Override
    public int updateMapBZyd(jcd_mapb mapjcd) {
        return sysZYDanDao.updateMapBZyd(mapjcd);
    }

    @Override
    public int updateMapBZydState(String id) {
        return sysZYDanDao.updateMapBZydState(id);
    }

    @Override
    public int deleteMapBZyd(String id) {
        return sysZYDanDao.deleteMapBZyd(id);
    }

    @Override
    public jcd_mapb queryMapBZydByID(String id) {
        return sysZYDanDao.queryMapBZydByID(id);
    }

    @Override
    public jcd_mapb queryMapBZydByBh(Integer bh) {
        return sysZYDanDao.queryMapBZydByBh(bh);
    }

    @Override
    public List<jcd_mapb> queryMapBZydByDefine(Integer taskYear, String taskName, String taskState, String mapCode, String mapName, String sb) {
        return sysZYDanDao.queryMapBZydByDefine(taskYear, taskName, taskState, mapCode, mapName, sb);
    }

    @Override
    public List<jcd_mapb> queryMapBZydAll() {
        return sysZYDanDao.queryMapBZydAll();
    }

    @Override
    public boolean isMapBJcdExist(Integer jcdMapBh) {
        return sysZYDanDao.queryMapBZydByBh(jcdMapBh) != null;
    }

    /*******************************book之MY******************************/

    @Override
    public int insertBookMZyd(jcd_bookm bookjcd) {
        return sysZYDanDao.insertBookMZyd(bookjcd);
    }

    @Override
    public int insertBookMZyd(String taskIndexID, Integer taskYear, String taskName, String taskType, String taskState, String sb, String sh, String sm, String zyy, String bjy, String ysy) {
        jcd_bookm jcdBookm = new jcd_bookm();
        jcdBookm.setId(UUIDGeneratorUtil.getUUID());
        jcdBookm.setTask_index_id(taskIndexID);
        jcdBookm.setTask_year(taskYear);
        jcdBookm.setTask_name(taskName);
        jcdBookm.setTask_type(taskType);
        jcdBookm.setTask_state(taskState);
        jcdBookm.setSb(sb);
        jcdBookm.setSh(sh);
        jcdBookm.setSm(sm);
        jcdBookm.setZyy(zyy);
        jcdBookm.setBjy(bjy);
        jcdBookm.setYsy(ysy);
        return sysZYDanDao.insertBookMZyd(jcdBookm);
    }

    @Override
    public int insertBookMZydMany(List<jcd_bookm> jcdBookList) {
        return sysZYDanDao.insertBookMZydMany(jcdBookList);
    }

    @Override
    public int updateBookMZyd(jcd_bookm bookjcd) {
        return sysZYDanDao.updateBookMZyd(bookjcd);
    }

    @Override
    public int updateBookMZydState(String id) {
        return sysZYDanDao.updateBookMZydState(id);
    }

    @Override
    public int deleteBookMZyd(String id) {
        return sysZYDanDao.deleteBookMZyd(id);
    }

    @Override
    public jcd_bookm queryBookMZydByID(String id) {
        return sysZYDanDao.queryBookMZydByID(id);
    }

    @Override
    public jcd_bookm queryBookMZydByBh(Integer bh) {
        return sysZYDanDao.queryBookMZydByBh(bh);
    }

    @Override
    public List<jcd_bookm> queryBookMZydByDefine(Integer taskYear, String taskName, String taskState, String sh, String sm, String sb) {
        return sysZYDanDao.queryBookMZydByDefine(taskYear, taskName, taskState, sh, sm, sb);
    }

    @Override
    public List<jcd_bookm> queryBookMZydAll() {
        return sysZYDanDao.queryBookMZydAll();
    }

    @Override
    public boolean isBookMJcdExist(Integer jcdBookBh) {
        return sysZYDanDao.queryBookMZydByBh(jcdBookBh) != null;
    }

    /*******************************book之JY******************************/

    @Override
    public int insertBookJZyd(jcd_bookj bookjcd) {
        return sysZYDanDao.insertBookJZyd(bookjcd);
    }

    @Override
    public int insertBookJZyd(String taskIndexID, Integer taskYear, String taskName, String taskType, String taskState, String sb, String sh, String sm, String zyy, String bjy, String ysy) {
        jcd_bookj jcdBookj = new jcd_bookj();
        jcdBookj.setId(UUIDGeneratorUtil.getUUID());
        jcdBookj.setTask_index_id(taskIndexID);
        jcdBookj.setTask_year(taskYear);
        jcdBookj.setTask_name(taskName);
        jcdBookj.setTask_type(taskType);
        jcdBookj.setTask_state(taskState);
        jcdBookj.setSb(sb);
        jcdBookj.setSh(sh);
        jcdBookj.setSm(sm);
        jcdBookj.setZyy(zyy);
        jcdBookj.setBjy(bjy);
        jcdBookj.setYsy(ysy);
        return sysZYDanDao.insertBookJZyd(jcdBookj);
    }

    @Override
    public int insertBookJZydMany(List<jcd_bookj> jcdBookList) {
        return sysZYDanDao.insertBookJZydMany(jcdBookList);
    }

    @Override
    public int updateBookJZyd(jcd_bookj bookjcd) {
        return sysZYDanDao.updateBookJZyd(bookjcd);
    }

    @Override
    public int updateBookJZydState(String id) {
        return sysZYDanDao.updateBookJZydState(id);
    }

    @Override
    public int deleteBookJZyd(String id) {
        return sysZYDanDao.deleteBookJZyd(id);
    }

    @Override
    public jcd_bookj queryBookJZydByID(String id) {
        return sysZYDanDao.queryBookJZydByID(id);
    }

    @Override
    public jcd_bookj queryBookJZydByBh(Integer bh) {
        return sysZYDanDao.queryBookJZydByBh(bh);
    }

    @Override
    public List<jcd_bookj> queryBookJZydByDefine(Integer taskYear, String taskName, String taskState, String sh, String sm, String sb) {
        return sysZYDanDao.queryBookJZydByDefine(taskYear, taskName, taskState, sh, sm, sb);
    }

    @Override
    public List<jcd_bookj> queryBookJZydAll() {
        return sysZYDanDao.queryBookJZydAll();
    }

    @Override
    public boolean isBookJJcdExist(Integer jcdBookBh) {
        return sysZYDanDao.queryBookJZydByBh(jcdBookBh) != null;
    }

    /*******************************book之BZBD******************************/

    @Override
    public int insertBookBZyd(jcd_bookb bookjcd) {
        return sysZYDanDao.insertBookBZyd(bookjcd);
    }

    @Override
    public int insertBookBZyd(String taskIndexID, Integer taskYear, String taskName, String taskType, String taskState, String sb, String sh, String sm, String zyy, String bjy, String ysy) {
        jcd_bookb jcdBookb = new jcd_bookb();
        jcdBookb.setId(UUIDGeneratorUtil.getUUID());
        jcdBookb.setTask_index_id(taskIndexID);
        jcdBookb.setTask_year(taskYear);
        jcdBookb.setTask_name(taskName);
        jcdBookb.setTask_type(taskType);
        jcdBookb.setTask_state(taskState);
        jcdBookb.setSb(sb);
        jcdBookb.setSh(sh);
        jcdBookb.setSm(sm);
        jcdBookb.setZyy(zyy);
        jcdBookb.setBjy(bjy);
        jcdBookb.setYsy(ysy);
        return sysZYDanDao.insertBookBZyd(jcdBookb);
    }

    @Override
    public int insertBookBZydMany(List<jcd_bookb> jcdBookList) {
        return sysZYDanDao.insertBookBZydMany(jcdBookList);
    }

    @Override
    public int updateBookBZyd(jcd_bookb bookjcd) {
        return sysZYDanDao.updateBookBZyd(bookjcd);
    }

    @Override
    public int updateBookBZydState(String id) {
        return sysZYDanDao.updateBookBZydState(id);
    }

    @Override
    public int deleteBookBZyd(String id) {
        return sysZYDanDao.deleteBookBZyd(id);
    }

    @Override
    public jcd_bookb queryBookBZydByID(String id) {
        return sysZYDanDao.queryBookBZydByID(id);
    }

    @Override
    public jcd_bookb queryBookBZydByBh(Integer bh) {
        return sysZYDanDao.queryBookBZydByBh(bh);
    }

    @Override
    public List<jcd_bookb> queryBookBZydByDefine(Integer taskYear, String taskName, String taskState, String sh, String sm, String sb) {
        return sysZYDanDao.queryBookBZydByDefine(taskYear, taskName, taskState, sh, sm, sb);
    }

    @Override
    public List<jcd_bookb> queryBookBZydAll() {
        return sysZYDanDao.queryBookBZydAll();
    }

    @Override
    public boolean isBookBJcdExist(Integer jcdBookBh) {
        return sysZYDanDao.queryBookBZydByBh(jcdBookBh) != null;
    }

    /*******************************uvph******************************/

    @Override
    public int insertUvphZyd(jcd_uvph uvph) {
        return sysZYDanDao.insertUvphZyd(uvph);
    }

    @Override
    public int insertUvphZydMany(List<jcd_uvph> jcdUvphList) {
        return sysZYDanDao.insertUvphZydMany(jcdUvphList);
    }

    @Override
    public boolean isUvphJcdExist(Integer jcdUvphBh) {
        return sysZYDanDao.queryUvphZydByBh(jcdUvphBh) != null;
    }

    @Override
    public int updateUvphZyd(jcd_uvph uvph) {
        return sysZYDanDao.updateUvphZyd(uvph);
    }

    @Override
    public int updateUvphZydState(String id) {
        return sysZYDanDao.updateUvphZydState(id);
    }

    @Override
    public int deleteUvphZyd(String id) {
        return sysZYDanDao.deleteUvphZyd(id);
    }

    @Override
    public jcd_uvph queryUvphZydByID(String id) {
        return sysZYDanDao.queryUvphZydByID(id);
    }

    @Override
    public jcd_uvph queryUvphZydByBh(Integer bh) {
        return sysZYDanDao.queryUvphZydByBh(bh);
    }

    @Override
    public List<jcd_uvph> queryUvphZydByDefine(Integer taskYear, String taskName, String taskState, String mapCode, String mapName, String sb) {
        return sysZYDanDao.queryUvphZydByDefine(taskYear, taskName, taskState, mapCode, mapName, sb);
    }

    @Override
    public List<jcd_uvph> queryUvphZydAll() {
        return sysZYDanDao.queryUvphZydAll();
    }
}
