package com.startest.wm.model;


import java.util.List;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-04-15 15:00
 * @描述 系统部门岗位人员结构树模型
 **/
public class SysOrganizationTree {
    private String id;//树节点ID
    private String pid;//树节点上一级ID
    private String nodeID;//树节点数据ID
    private String nodeName;//树节点名称
    private String nodeType;//树节点类型
    private String nodeTag;//树节点标识
    private Integer nodeOrder;//树节点顺序

    private List<SysOrganizationTree> childNodes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getNodeID() {
        return nodeID;
    }

    public void setNodeID(String nodeID) {
        this.nodeID = nodeID;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getNodeTag() {
        return nodeTag;
    }

    public void setNodeTag(String nodeTag) {
        this.nodeTag = nodeTag;
    }

    public Integer getNodeOrder() {
        return nodeOrder;
    }

    public void setNodeOrder(Integer nodeOrder) {
        this.nodeOrder = nodeOrder;
    }

    public List<SysOrganizationTree> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(List<SysOrganizationTree> childNodes) {
        this.childNodes = childNodes;
    }
}
