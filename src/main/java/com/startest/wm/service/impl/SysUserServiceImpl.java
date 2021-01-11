package com.startest.wm.service.impl;

import com.startest.wm.config.exception.FileOperationException;
import com.startest.wm.dao.SysDeptDao;
import com.startest.wm.dao.SysStationDao;
import com.startest.wm.dao.SysUserDao;
import com.startest.wm.model.SysUserModel;
import com.startest.wm.model.UserDept;
import com.startest.wm.model.UserSearchModel;
import com.startest.wm.pojo.sys_dept;
import com.startest.wm.pojo.sys_dept_station_user;
import com.startest.wm.pojo.sys_user;
import com.startest.wm.service.SysUserService;
import com.startest.wm.utils.UUIDGeneratorUtil;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-03-27 14:42
 * @描述 用户信息操作服务实现类
 **/
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    SysUserDao sysUserDao;

    @Autowired
    SysDeptDao sysDeptDao;

    @Autowired
    SysStationDao sysStationDao;

    @Override
    public int insertUser(sys_user sysUser) {
        return sysUserDao.insertUser(sysUser);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer insertUserMany(List<SysUserModel> sysUserModelList,boolean isAdmin) throws FileOperationException {
        int resCount = 0;
        for (SysUserModel sysUserModel : sysUserModelList) {
            try {
                //1、用户信息表中插入数据
                int count = sysUserDao.insertUserByModel(sysUserModel);
                //2、部门岗位用户关系表中插入记录
                if (count > 0) {
                    //获取所在单位ID
                    String dwId = sysDeptDao.queryDeptByName(sysUserModel.getSzdwName()).get(0).getDept_id();
                    List<sys_dept> deptList = sysDeptDao.queryChildDeptByID(dwId,true);
                    //获取所在部门ID
                    String bmID = ListUtils.select(deptList, x -> x.getDept_name().equals(sysUserModel.getSzbm())).get(0).getDept_id();
                    //获取所在岗位ID
                    String stationID = sysStationDao.queryStationByName(sysUserModel.getSzgw()).get(0).getStation_id();
                    sys_dept_station_user sysDeptStationUser = new sys_dept_station_user();
                    sysDeptStationUser.setId(UUIDGeneratorUtil.getUUIDWithoutLineAndToLower());
                    sysDeptStationUser.setDept_id(bmID);
                    sysDeptStationUser.setStation_id(stationID);
                    sysDeptStationUser.setUser_id(sysUserModel.getUser_id());
                    int res = sysDeptDao.insertDeptStationUserReal(sysDeptStationUser);
                    if (res > 0) {
                        resCount++;
                    }
                }
            } catch (Exception e) {
                throw new FileOperationException("用户批量插入失败");
            }
        }
        return resCount;
    }

    @Override
    public int deleteByUserID(String userID) {
        return sysUserDao.deleteByUserID(userID);
    }

    @Override
    public int updateUser(sys_user sysUser) {
        return sysUserDao.updateUser(sysUser);
    }

    @Override
    public int updateUserState(String userID, int state) {
        return sysUserDao.updateUserState(userID, state);
    }

    @Override
    public sys_user queryByUserID(String userID) {
        return sysUserDao.queryByUserID(userID);
    }

    @Override
    public SysUserModel queryUserModelByUserID(String userID) {
        return sysUserDao.queryUserModelByUserID(userID);
    }

    @Override
    public sys_user queryByName(String userName) {
        return sysUserDao.queryByName(userName);
    }

    @Override
    public sys_user queryByLoginName(String loginName) {
        return sysUserDao.queryByLoginName(loginName);
    }

    @Override
    public SysUserModel queryUserModelByLoginName(String loginName) {
        return sysUserDao.queryUserModelByLoginName(loginName);
    }

    @Override
    public List<sys_user> queryUserByDefinition(UserSearchModel uerSearchModel) {
        return sysUserDao.queryUserByDefinition(uerSearchModel);
    }

    @Override
    public List<SysUserModel> queryUserModelByDefinition(UserSearchModel userSearchModel) {
        List<sys_dept> sysDeptListAll = sysDeptDao.queryDeptAll(true);
        Map<String, String> map = new HashMap<>();
        List<SysUserModel> sysUserModelList = sysUserDao.queryUserModelByDefinition(userSearchModel);
        for (SysUserModel sysUserModel : sysUserModelList) {
            getDwAndZb(sysUserModel.getSzbmID(), map, sysDeptListAll);
            sysUserModel.setSzdwName(map.get("dw"));//设置所在单位名称
            sysUserModel.setSzbm(map.get("zb"));//设置所在单位名称
        }
        return sysUserModelList;
    }

    @Override
    public List<SysUserModel> queryUserModelByIDs(List<String> lstID) {
        List<sys_dept> sysDeptListAll = sysDeptDao.queryDeptAll(true);
        Map<String, String> map = new HashMap<>();
        List<SysUserModel> sysUserModelList = sysUserDao.queryUserModelByIDs(lstID);
        for (SysUserModel sysUserModel : sysUserModelList) {
            getDwAndZb(sysUserModel.getSzbmID(), map, sysDeptListAll);
            sysUserModel.setSzdwName(map.get("dw"));//设置所在单位名称
            sysUserModel.setSzbm(map.get("zb"));//设置所在单位名称
        }
        return sysUserModelList;
    }

    public void getDwAndZb(String deptID, Map<String,String>map,List<sys_dept>sysDeptListAll) {
        sys_dept sysDept = ListUtils.select(sysDeptListAll, x -> x.getDept_id().equals(deptID)).get(0);
        if (!"-1".equals(sysDept.getDept_pid())) {
            sys_dept parentDept = ListUtils.select(sysDeptListAll, x -> x.getDept_id().equals(sysDept.getDept_pid())).get(0);
            if (!"-1".equals(parentDept.getDept_pid())) {
                sys_dept parentParentDept = ListUtils.select(sysDeptListAll, x -> x.getDept_id().equals(parentDept.getDept_pid())).get(0);
                if ("-1".equals(parentParentDept.getDept_pid())) {
                    //如果为三级节点，则单位为根节点，名称组别为二级节点名称
                    map.put("dw", parentDept.getDept_name());
                    map.put("zb", sysDept.getDept_name());
                } else {
                    getDwAndZb(sysDept.getDept_pid(), map, sysDeptListAll);
                }
            } else {
                //如果为二级节点，则单位为根节点名称，名称组别为二级节点名称
                map.put("dw", parentDept.getDept_name());
                map.put("zb", "");
            }
        } else {
            //如果为根节点,则单位和组别都为二级节点名称
            map.put("dw", "");
            map.put("zb", "");
        }
    }

    @Override
    public List<sys_user> queryAll() {
        return sysUserDao.queryAll();
    }

    @Override
    public List<SysUserModel> queryUserModelAll() {
        return sysUserDao.queryUserModelAll();
    }

    @Override
    public UserDept queryUserDept(String userID) {
        sys_user sysUser = sysUserDao.queryByUserID(userID);
        //查询用户所属于科室
        sys_dept sysDept = sysUserDao.queryUserDept(userID);
        sys_dept zuoyeshiDept = getLastDept(sysDept);
        UserDept userDept = new UserDept(sysUser.getUser_id(), sysUser.getUser_name(), zuoyeshiDept.getDept_id(), zuoyeshiDept.getDept_name());
        return userDept;
    }

    /**
     * @Description: 获取上一级部门
     * @Param: [sys_dept] 部门
     * @return: com.startest.wm.pojo.sys_dept
     **/
    private sys_dept getLastDept(sys_dept dept) {
        sys_dept sysDept = sysDeptDao.queryDeptByID(dept.getDept_pid());
        if (sysDept != null && "-1".equals(sysDept.getDept_pid())) {
            return sysDept;
        } else {
            getLastDept(sysDept);
        }
        return sysDept;
    }

    /**
     * @Description: 根据用户ID获取用户部门，岗位，小组，人员类别，是否组长信息
     * @Param: [userID]用户ID
     * @return: java.util.Map<java.lang.String, java.lang.String>
     **/
    @Override
    public Map<String, String> getUserDetailInfo(String userID) {
        List<Map<String, String>> list = sysUserDao.getUserDetailInfo(userID);
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }
}
