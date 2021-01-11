package com.startest.wm.service.impl;

import com.startest.wm.dao.SysDeptDao;
import com.startest.wm.model.SysOrganizationTree;
import com.startest.wm.model.SysUserModel;
import com.startest.wm.model.TaskClassFieldModel;
import com.startest.wm.pojo.*;
import com.startest.wm.service.SysDeptService;
import com.startest.wm.utils.UUIDGeneratorUtil;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-04-02 10:30
 * @描述 系统部门操作服务接口实现类
 **/
@Service
public class SysDeptServiceImpl implements SysDeptService {

    @Autowired(required = false)
    SysDeptDao sysDeptDao;

    @Override
    public int addDept(sys_dept sysDept) {
        return sysDeptDao.insertDept(sysDept);
    }

    @Override
    public int deleteDeptByID(String deptID) {
        return sysDeptDao.deleteDept(deptID);
    }

    @Override
    public int updateDept(sys_dept sysDept) {
        return sysDeptDao.updateDept(sysDept);
    }

    @Override
    public sys_dept getDeptByID(String deptID) {
        return sysDeptDao.queryDeptByID(deptID);
    }

    @Override
    public List<sys_dept> getDeptByName(String deptName) {
        return sysDeptDao.queryDeptByName(deptName);
    }

    @Override
    public List<sys_user> getDeptUserList(String deptID, boolean isAdmin) {
        List<sys_user> sysUserList = new ArrayList<>();
        getSysDeptUser(deptID, sysUserList, isAdmin);
        return sysUserList;
    }

    @Override
    public List<SysUserModel> getDeptUserModelList(String deptID, boolean isAdmin) {
        List<SysUserModel> sysUserModelList = new ArrayList<>();
        if (deptID == null || "".equals(deptID)) {
            deptID = sysDeptDao.queryRootDept().getDept_id();
        }
        List<sys_dept> sysDeptListAll = sysDeptDao.queryDeptAll(isAdmin);
        getSysDeptUserModel(sysDeptListAll, deptID, sysUserModelList, isAdmin);
        return sysUserModelList;
    }

    private void getSysDeptUser(String deptID, List<sys_user> sysUserList, boolean isAdmin) {
        List<sys_dept> sysDeptList = sysDeptDao.queryChildDeptByID(deptID, isAdmin);
        if (sysDeptList != null && sysDeptList.size() > 0) {
            //部门下级为部门
            for (sys_dept childDept : sysDeptList) {
                getSysDeptUser(childDept.getDept_id(), sysUserList, isAdmin);
            }
        } else {
            //部门下级为岗位
            List<sys_station> sysStationList = sysDeptDao.queryStationByDeptID(deptID);
            if (sysStationList != null && sysStationList.size() > 0) {
                for (sys_station sysStation : sysStationList) {
                    List<sys_user> sysUserList1 = sysDeptDao.queryDeptStationUsers(deptID, sysStation.getStation_id());
                    if (sysUserList1 != null && sysUserList1.size() > 0) {
                        sysUserList.addAll(sysUserList1);
                    }
                }
            }
        }
    }

    private void getSysDeptUserModel(List<sys_dept> sysDeptListAll, String deptID, List<SysUserModel> sysUserModelList, boolean isAdmin) {
        //部门下级为岗位
        List<sys_station> sysStationList = sysDeptDao.queryStationByDeptID(deptID);
        if (sysStationList != null && sysStationList.size() > 0) {
            for (sys_station sysStation : sysStationList) {
                List<SysUserModel> sysUserModelList1 = sysDeptDao.queryUserModelByReal(deptID, sysStation.getStation_id());
                if (sysUserModelList1 != null && sysUserModelList1.size() > 0) {
                    for (SysUserModel sysUserModel : sysUserModelList1) {
                        Map<String, String> map = new HashMap<>();
                        getDwAndZb(deptID, map, sysDeptListAll);
                        sysUserModel.setSzdwName(map.get("dw"));//设置所在单位名称
                        sysUserModel.setSzbm(map.get("zb"));//设置所在组别名称
                    }
                    sysUserModelList.addAll(sysUserModelList1);
                }
            }
        }
        List<sys_dept> sysDeptList = sysDeptDao.queryChildDeptByID(deptID, isAdmin);
        if (sysDeptList != null && sysDeptList.size() > 0) {
            //部门下级为部门
            for (sys_dept childDept : sysDeptList) {
                getSysDeptUserModel(sysDeptListAll, childDept.getDept_id(), sysUserModelList, isAdmin);
            }
        }
    }

    /**
     * @Description: 获取所属单位和所属部门:效率较低，不用了
     * @Param: [deptID, map]
     * @return: void
     **/
    private void getDwAndZb1(String deptID, Map<String, String> map) {
        sys_dept sysDept = sysDeptDao.queryDeptByID(deptID);
        if (!"-1".equals(sysDept.getDept_pid())) {
            sys_dept parentDept = sysDeptDao.queryDeptByID(sysDept.getDept_pid());
            if (!"-1".equals(parentDept.getDept_pid())) {
                sys_dept parentParentDept = sysDeptDao.queryDeptByID(parentDept.getDept_pid());
                if ("-1".equals(parentParentDept.getDept_pid())) {
                    //如果为三级节点，则单位为根节点，名称组别为二级节点名称
                    map.put("dw", parentDept.getDept_name());
                    map.put("zb", sysDept.getDept_name());
                } else {
                    getDwAndZb1(sysDept.getDept_pid(), map);
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
    public Map<String, String> getDwZb(String deptID) {
        Map<String, String> map = new HashMap<>();
        List<sys_dept> sysDeptListAll = sysDeptDao.queryDeptAll(true);
        getDwAndZb(deptID, map, sysDeptListAll);
        return map;
    }

    public void getDwAndZb(String deptID, Map<String, String> map, List<sys_dept> sysDeptListAll) {
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
    public sys_dept getDeptTree(String deptID, boolean isAdmin) {
        sys_dept sysDept = null;
        if (deptID != null && !"".equals(deptID)) {
            sysDept = getChildDeptAll(sysDeptDao.queryDeptByID(deptID), isAdmin);
        } else {
            sysDept = getChildDeptAll(sysDeptDao.queryRootDept(), isAdmin);
        }
        return sysDept;
    }

    public sys_dept getChildDeptAll(sys_dept sysDept, boolean isAdmin) {
        List<sys_dept> sysDeptList = getChildDeptByID(sysDept.getDept_id(), isAdmin);
        if (sysDeptList != null && sysDeptList.size() > 0) {
            for (sys_dept sysDept1 : sysDeptList) {
                getChildDeptAll(sysDept1, isAdmin);
            }
        }
        sysDept.setChildDepts(sysDeptList);
        return sysDept;
    }

    @Override
    public List<sys_dept> getChildDeptByID(String deptID, boolean isAdmin) {
        return sysDeptDao.queryChildDeptByID(deptID, isAdmin);
    }

    @Override
    public List<sys_dept> getZuoyeshiDepts(String deptID, boolean isAdmin) {
        List<sys_dept> sysDeptList = getChildDeptByID(deptID, isAdmin);
        if (sysDeptList != null && sysDeptList.size() > 0) {
            for (sys_dept sysDept1 : sysDeptList) {
                getChildDeptAll(sysDept1, isAdmin);
            }
        }
        return sysDeptList;
    }

    @Override
    public List<sys_station> getDeptStations(String deptID) {
        return sysDeptDao.queryStationByDeptID(deptID);
    }

    @Override
    public SysOrganizationTree getSysOrganizationTree(String deptID, boolean isAdmin) {
        SysOrganizationTree sysOrganizationTree = new SysOrganizationTree();
        if (deptID == null || "".equals(deptID)) {
            sys_dept sysDept = sysDeptDao.queryRootDept();
            sysOrganizationTree.setId(UUIDGeneratorUtil.getUUID());
            sysOrganizationTree.setPid("-1");
            sysOrganizationTree.setNodeID(sysDept.getDept_id());
            sysOrganizationTree.setNodeName(sysDept.getDept_name());
            sysOrganizationTree.setNodeOrder(sysDept.getDept_order());
            sysOrganizationTree.setNodeType("部门");
            getChildSysOrganizationTreeList(sysOrganizationTree, sysDept.getDept_id(), isAdmin);
            return sysOrganizationTree;
        } else {
            sys_dept sysDept = sysDeptDao.queryDeptByID(deptID);
            sysOrganizationTree.setId(UUIDGeneratorUtil.getUUID());
            sysOrganizationTree.setPid("-1");
            sysOrganizationTree.setNodeID(sysDept.getDept_id());
            sysOrganizationTree.setNodeName(sysDept.getDept_name());
            sysOrganizationTree.setNodeOrder(sysDept.getDept_order());
            sysOrganizationTree.setNodeType("部门");
            getChildSysOrganizationTreeList(sysOrganizationTree, deptID, isAdmin);
            return sysOrganizationTree;
        }
    }

    private void getChildSysOrganizationTreeList(SysOrganizationTree sysOrganizationTree, String deptID, boolean isAdmin) {
        //获取子级节点
        List<sys_dept> sysDeptList = sysDeptDao.queryChildDeptByID(deptID, isAdmin);
        if (sysDeptList != null && sysDeptList.size() > 0) {
            List<SysOrganizationTree> sysOrganizationTreeList = new ArrayList<SysOrganizationTree>();
            //部门下级为部门
            for (sys_dept childDept : sysDeptList) {
                SysOrganizationTree sysOrganizationTree1 = new SysOrganizationTree();
                sysOrganizationTree1.setId(UUIDGeneratorUtil.getUUID());
                sysOrganizationTree1.setPid(sysOrganizationTree.getId());//父ID为上一级部门ID
                sysOrganizationTree1.setNodeID(childDept.getDept_id());
                sysOrganizationTree1.setNodeName(childDept.getDept_name());
                sysOrganizationTree1.setNodeOrder(childDept.getDept_order());
                sysOrganizationTree1.setNodeType("部门");
                sysOrganizationTreeList.add(sysOrganizationTree1);
                getChildSysOrganizationTreeList(sysOrganizationTree1, childDept.getDept_id(), isAdmin);
            }
            sysOrganizationTree.setChildNodes(sysOrganizationTreeList);
        } else {
            //部门下级为岗位
            List<sys_station> sysStationList = sysDeptDao.queryStationByDeptID(deptID);
            if (sysStationList != null && sysStationList.size() > 0) {
                List<SysOrganizationTree> sysOrganizationTreeList = new ArrayList<SysOrganizationTree>();
                for (sys_station sysStation : sysStationList) {
                    SysOrganizationTree sysOrganizationTree1 = new SysOrganizationTree();
                    sysOrganizationTree1.setId(UUIDGeneratorUtil.getUUID());
                    sysOrganizationTree1.setPid(sysOrganizationTree.getId());//父ID为上一级部门ID
                    sysOrganizationTree1.setNodeID(sysStation.getStation_id());
                    sysOrganizationTree1.setNodeName(sysStation.getStation_name());
                    sysOrganizationTree1.setNodeType("岗位");
                    sysOrganizationTreeList.add(sysOrganizationTree1);
                    List<sys_user> sysUserList = sysDeptDao.queryDeptStationUsers(deptID, sysStation.getStation_id());
                    if (sysUserList != null && sysUserList.size() > 0) {
                        List<SysOrganizationTree> sysOrganizationTreeList1 = new ArrayList<SysOrganizationTree>();
                        for (sys_user sysUser : sysUserList) {
                            SysOrganizationTree sysOrganizationTree2 = new SysOrganizationTree();
                            sysOrganizationTree2.setId(UUIDGeneratorUtil.getUUID());
                            sysOrganizationTree2.setPid(sysOrganizationTree1.getId());//父ID为上一级岗位ID
                            sysOrganizationTree2.setNodeID(sysUser.getUser_id());
                            sysOrganizationTree2.setNodeName(sysUser.getUser_name());
                            sysOrganizationTree2.setNodeType("人员");
                            sysOrganizationTreeList1.add(sysOrganizationTree2);
                        }
                        sysOrganizationTree1.setChildNodes(sysOrganizationTreeList1);
                    }
                }
                sysOrganizationTree.setChildNodes(sysOrganizationTreeList);
            }
        }
    }

    @Override
    public SysOrganizationTree getJgDeptUsers(String deptID, boolean isAdmin) {
        SysOrganizationTree sysOrganizationTree = new SysOrganizationTree();
        sys_dept sysDept = sysDeptDao.queryDeptByID(deptID);
        sysOrganizationTree.setId(UUIDGeneratorUtil.getUUID());
        sysOrganizationTree.setPid("-1");
        sysOrganizationTree.setNodeID(sysDept.getDept_id());
        sysOrganizationTree.setNodeName(sysDept.getDept_name());
        sysOrganizationTree.setNodeType("部门");
        getJGChildDeptsList(sysOrganizationTree, deptID, isAdmin);
        return sysOrganizationTree;
    }

    private void getJGChildDeptsList(SysOrganizationTree sysOrganizationTree, String deptID, boolean isAdmin) {
        List<sys_dept> sysDeptList = sysDeptDao.queryChildDeptByID(deptID, isAdmin);
        if (sysDeptList != null && sysDeptList.size() > 0) {
            List<SysOrganizationTree> sysOrganizationTreeList = new ArrayList<SysOrganizationTree>();
            for (sys_dept childDept : sysDeptList) {
                SysOrganizationTree sysOrganizationTree1 = new SysOrganizationTree();
                sysOrganizationTree1.setId(UUIDGeneratorUtil.getUUID());
                sysOrganizationTree1.setPid(sysOrganizationTree.getId());//父ID为上一级部门ID
                sysOrganizationTree1.setNodeID(childDept.getDept_id());
                sysOrganizationTree1.setNodeName(childDept.getDept_name());
                sysOrganizationTree1.setNodeType("部门");
                sysOrganizationTreeList.add(sysOrganizationTree1);
                List<SysOrganizationTree> sysOrganizationTrees = new ArrayList<>();
                getChildUsersList(sysOrganizationTree1.getId(), childDept.getDept_id(), sysOrganizationTrees, isAdmin);
                sysOrganizationTree1.setChildNodes(sysOrganizationTrees);
            }
            sysOrganizationTree.setChildNodes(sysOrganizationTreeList);
        }
    }

    @Override
    public SysOrganizationTree getDeptUsers(String deptID, boolean isAdmin) {
        SysOrganizationTree sysOrganizationTree = new SysOrganizationTree();
        sys_dept sysDept = sysDeptDao.queryDeptByID(deptID);
        sysOrganizationTree.setId(UUIDGeneratorUtil.getUUID());
        sysOrganizationTree.setPid("-1");
        sysOrganizationTree.setNodeID(sysDept.getDept_id());
        sysOrganizationTree.setNodeName(sysDept.getDept_name());
        sysOrganizationTree.setNodeType("部门");
        List<SysOrganizationTree> sysOrganizationTreeList = new ArrayList<>();
        getChildUsersList(sysOrganizationTree.getId(), deptID, sysOrganizationTreeList, isAdmin);
        sysOrganizationTree.setChildNodes(sysOrganizationTreeList);
        return sysOrganizationTree;
    }

    private void getChildUsersList(String pid, String deptID, List<SysOrganizationTree> sysOrganizationTreeList, boolean isAdmin) {
        List<sys_dept> sysDeptList = sysDeptDao.queryChildDeptByID(deptID, isAdmin);
        if (sysDeptList != null && sysDeptList.size() > 0) {
            //部门下级为部门
            for (sys_dept childDept : sysDeptList) {
                getChildUsersList(pid, childDept.getDept_id(), sysOrganizationTreeList, isAdmin);
            }
        } else {
            //部门下级为岗位
            List<sys_station> sysStationList = sysDeptDao.queryStationByDeptID(deptID);
            if (sysStationList != null && sysStationList.size() > 0) {
                for (sys_station sysStation : sysStationList) {
                    List<sys_user> sysUserList = sysDeptDao.queryDeptStationUsers(deptID, sysStation.getStation_id());
                    if (sysUserList != null && sysUserList.size() > 0) {
                        for (sys_user sysUser : sysUserList) {
                            SysOrganizationTree sysOrganizationTree1 = new SysOrganizationTree();
                            sysOrganizationTree1.setId(UUIDGeneratorUtil.getUUID());
                            sysOrganizationTree1.setPid(pid);//父ID为上一级岗位ID
                            sysOrganizationTree1.setNodeID(sysUser.getUser_id());
                            sysOrganizationTree1.setNodeName(sysUser.getUser_name());
                            sysOrganizationTree1.setNodeType("人员");
                            sysOrganizationTreeList.add(sysOrganizationTree1);
                        }
                    }
                }
            }
        }
    }

    @Override
    public List<sys_user> getDeptStationUsers(String deptID, String stationID) {
        return sysDeptDao.queryDeptStationUsers(deptID, stationID);
    }

    @Override
    public int insertDeptStationUserReal(sys_dept_station_user sysDeptStationUser) {
        return sysDeptDao.insertDeptStationUserReal(sysDeptStationUser);
    }

    @Override
    public int updateDeptStationUserReal(sys_dept_station_user sysDeptStationUser) {
        return sysDeptDao.updateDeptStationUserReal(sysDeptStationUser);
    }

    @Override
    public boolean isDeptStationContainUser(String deptID, String stationID, String userID) {
        return sysDeptDao.getDeptStationUserReal(deptID, stationID, userID).size() > 0 ? true : false;
    }

    @Override
    public boolean isDeptContainUsers(String deptID) {
        return sysDeptDao.getDeptUserReals(deptID).size() > 0 ? true : false;
    }

    @Override
    public int deleteDeptStationUserReal(String deptID, String stationID, String userID) {
        return sysDeptDao.deleteDeptStationUserReal(deptID, stationID, userID);
    }

    @Override
    public int deleteDeptStationUserReal2(String userID) {
        return sysDeptDao.deleteDeptStationUserReal2(userID);
    }


    @Override
    public boolean isDeptExist(String deptID, String deptPID, String deptName) {
        return sysDeptDao.isDeptExist(deptID, deptPID, deptName) > 0 ? true : false;
    }

    @Override
    public SysOrganizationTree getAllZuoyeshi(boolean isAdmin) {
        SysOrganizationTree sysOrganizationTree = new SysOrganizationTree();
        //查询部门分组信息
        sys_dept sysDept = sysDeptDao.queryRootDept();
        sysOrganizationTree.setId(UUIDGeneratorUtil.getUUID());
        sysOrganizationTree.setPid("-1");
        sysOrganizationTree.setNodeID(sysDept.getDept_id());
        sysOrganizationTree.setNodeName(sysDept.getDept_name());
        sysOrganizationTree.setNodeType("部门");

        List<SysOrganizationTree> childOrganizationTreeList = new ArrayList<>();
        List<sys_dept> sysDeptList = sysDeptDao.queryChildDeptByID(sysDept.getDept_id(), isAdmin);
        for (int i = 0; i < sysDeptList.size(); i++) {
            if (!sysDeptList.get(i).getDept_name().contains("业务处")) {
                SysOrganizationTree sysOrganizationTree1 = new SysOrganizationTree();
                sysOrganizationTree1.setId(UUIDGeneratorUtil.getUUID());
                sysOrganizationTree1.setPid("-1");
                sysOrganizationTree1.setNodeID(sysDeptList.get(i).getDept_id());
                sysOrganizationTree1.setNodeName(sysDeptList.get(i).getDept_name());
                sysOrganizationTree1.setNodeType("部门");
                childOrganizationTreeList.add(sysOrganizationTree1);
            }
        }
        sysOrganizationTree.setChildNodes(childOrganizationTreeList);
        return sysOrganizationTree;
    }

    @Override
    public List<TaskClassFieldModel> getAllZuoyeshiModel(boolean isAdmin) {
        sys_dept sysDept = sysDeptDao.queryRootDept();
        List<TaskClassFieldModel> listZYS = new ArrayList<>();
        List<sys_dept> sysDeptList = sysDeptDao.queryChildDeptByID(sysDept.getDept_id(), isAdmin);
        for (int i = 0; i < sysDeptList.size(); i++) {
            TaskClassFieldModel taskClassFieldModel = new TaskClassFieldModel();
            taskClassFieldModel.setLabel(sysDeptList.get(i).getDept_id());
            taskClassFieldModel.setProp(sysDeptList.get(i).getDept_name());
            taskClassFieldModel.setVisible(true);
            listZYS.add(taskClassFieldModel);
        }
        return listZYS;
    }

    @Override
    public List<sys_dept> getAllZuoyeshiList(boolean isAdmin) {
        sys_dept sysDept = sysDeptDao.queryRootDept();
        List<sys_dept> sysDeptList = sysDeptDao.queryChildDeptByID(sysDept.getDept_id(), isAdmin);
        Iterator<sys_dept> iterator = sysDeptList.iterator();
        while (iterator.hasNext()) {
            sys_dept dept = iterator.next();
            if (dept.getDept_name().contains("业务处")) {
                sysDeptList.remove(dept);
            }
        }
        return sysDeptList;
    }

    @Override
    public sys_dept_station_user getSysDeptStationUser(String user_id) {
        return sysDeptDao.getSysDeptStationUser(user_id);
    }

    @Override
    public int deleteDeptStationUserRealAll(String deptID, String stationID) {
        return sysDeptDao.deleteDeptStationUserRealAll(deptID, stationID);
    }
}
