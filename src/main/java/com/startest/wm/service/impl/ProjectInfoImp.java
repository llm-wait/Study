package com.startest.wm.service.impl;

import com.startest.wm.dao.ProjectInfoDao;
import com.startest.wm.model.ProjectIndexModel;
import com.startest.wm.pojo.wm_project_distribution;
import com.startest.wm.pojo.wm_project_index;
import com.startest.wm.pojo.wm_project_info;
import com.startest.wm.service.ProjectInfoService;
import com.startest.wm.utils.UUIDGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-06-24-14:02
 * @描述 项目接口实现类
 */
@Service
public class ProjectInfoImp implements ProjectInfoService {

    @Autowired
    private ProjectInfoDao projectInfoDao;

    @Override
    public int addProjectIndex(String name) {
        wm_project_index projectIndex = new wm_project_index();
        projectIndex.setId(UUIDGeneratorUtil.getUUIDWithoutLineAndToUpper());
        projectIndex.setPid("-1");
        projectIndex.setIndex_name(name);
        return projectInfoDao.addProjectIndex(projectIndex);
    }

    @Override
    public int updateProjectIndex(wm_project_index projectIndex) {
        return projectInfoDao.updateProjectIndex(projectIndex);
    }

    @Override
    public int deleteProjectIndex(String id) {
        return projectInfoDao.deleteProjectIndex(id);
    }

    @Override
    public boolean isProjectIndexExist(wm_project_index projectIndex) {
        List<wm_project_index> list = projectInfoDao.getProjectIndexList(projectIndex);
        if (projectIndex.getId() == null) {
            return list.size() > 0 ? true : false;
        } else {
            return list.size() > 1 ? true : false;
        }
    }

//    @Override
//    public List<wm_project_index> getProjectIndexList(Integer proYear) {
//        /*//查询所有菜单
//        List<wm_project_index> allMenu = projectInfoDao.getAllProjectIndexList();
//        //获取所有根节点，父节点是0的为根节点
//        List<wm_project_index> rootMenus = ListUtils.select(allMenu, x -> x.getPid().equals("-1"));
//        //根据order排序
//        Collections.sort(rootMenus, new MyComparator());
//        //为根菜单设置子菜单，getChild是递归调用的
//        for (wm_project_index nav : rootMenus) {
//            //递归获取根节点下的所有子节点
//            List<wm_project_index> childList = getChild(nav.getId(), allMenu);
//            nav.setChildren(childList);//给根节点设置子节点
//        }
//        return rootMenus;*/
//
//        List<wm_project_index> allMenu = projectInfoDao.getAllProjectIndexList();
////        for (wm_project_index nav : allMenu) {
////            //递归获取根节点下的所有子节点
////            List<ProjectIndexModel> childList = projectInfoDao.getProjectIndexModel(nav.getId(), proYear);
////            nav.setChildren(childList);//给根节点设置子节点
////        }
////        return allMenu;
////    }

    @Override
    public List<ProjectIndexModel> getProjectIndexModelList(Integer proYear) {
        List<ProjectIndexModel> allMenu = projectInfoDao.getAllProjectIndexModelList();
        for (ProjectIndexModel nav : allMenu) {
            //递归获取根节点下的所有子节点
            List<ProjectIndexModel> childList = projectInfoDao.getProjectIndexModel(nav.getId(), proYear);
            nav.setChildren(childList);//给根节点设置子节点
        }
        return allMenu;
    }

    @Override
    public int addProjectInfo(wm_project_info projectInfo) {
        //项目索引信息表中插入记录
        /*wm_project_index projectIndex = new wm_project_index();
        projectIndex.setId(UUIDGeneratorUtil.getUUIDWithoutLineAndToUpper());
        projectIndex.setPid(projectInfo.getIndex_id());
        projectIndex.setIndex_name(projectInfo.getProject_name());
        index += projectInfoDao.addProjectIndex(projectIndex);*/
        //项目信息表中插入记录
        return projectInfoDao.addProjectInfo(projectInfo);
    }


    @Override
    public int updateProjectInfo(wm_project_info projectInfo) {
        return projectInfoDao.updateProjectInfo(projectInfo);
    }

    @Override
    public int deleteProjectById(String id) {
        return projectInfoDao.deleteProjectById(id);
    }

    @Override
    public List<wm_project_info> selectProjectInfo(Integer projectYear, String projectName, String scheduleStatus, String projectCycle) {
        return projectInfoDao.getProjectInfoByDefine(projectYear,projectName,scheduleStatus,projectCycle);
    }

    @Override
    public List<wm_project_info> selectZysProjectInfo(String deptId, wm_project_info projectInfo) {
        return null;
    }

    @Override
    public wm_project_info selectProjectInfoByID(String id) {
        return projectInfoDao.getProjectInfoById(id);
    }

    @Override
    public List<wm_project_info> selectProjectInfo(Integer proYear, String nodeID,String nodeType) {
        if (nodeType.equals("分类")) {
            return projectInfoDao.getProjectInfoByIndexAndYear(proYear, nodeID);
        } else {
            wm_project_info projectInfo = projectInfoDao.getProjectInfoById(nodeID);
            List<wm_project_info> projectInfoList = new ArrayList<>();
            projectInfoList.add(projectInfo);
            return projectInfoList;
        }
    }

    //定义一个内部比较类
    /*class MyComparator implements Comparator<wm_project_index> {
        @Override
        public int compare(wm_project_index o1, wm_project_index o2) {
            if (o1.getIndex_order() == null) {
                if (o2.getIndex_order() == null) {
                    return 0;
                } else {
                    return -1;
                }
            } else {
                if (o2.getIndex_order() == null) {
                    return 1;
                } else {
                    return o1.getIndex_order().compareTo(o2.getIndex_order());
                }
            }
        }
    }*/

    @Override
    public void addAssignPersonnelInformation(wm_project_distribution wmProjectDistribution) {
        projectInfoDao.addAssignPersonnelInformation(wmProjectDistribution);
    }

    @Override
    public Set<String> getDistributionDepartment(String projectId) {
        return projectInfoDao.getDistributionDepartment(projectId);
    }

    @Override
    public List<Map<String, String>> getListOfPeople(String projectId) {
        Set<String> distributionDepartment = projectInfoDao.getDistributionDepartment(projectId);
        return projectInfoDao.getListOfPeople(distributionDepartment);
    }


    @Override
    public void delTaskAssignment(String disId) {
        projectInfoDao.delTaskAssignment(disId);
    }
}
