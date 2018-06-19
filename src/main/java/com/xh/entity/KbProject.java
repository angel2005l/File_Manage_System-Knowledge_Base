package com.xh.entity;

import java.util.Date;

public class KbProject {
    private Integer id;

    private String projectCode;

    private String projectName;

    private String projectType;

    private String projectInfo;

    private String projectRemark;

    private String projectParentCode;

    private Integer projectLevel;

    private String projectStatus;

    private String createUserCode;

    private Date createTime;

    private String updateUserCode;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode == null ? null : projectCode.trim();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType == null ? null : projectType.trim();
    }

    public String getProjectInfo() {
        return projectInfo;
    }

    public void setProjectInfo(String projectInfo) {
        this.projectInfo = projectInfo == null ? null : projectInfo.trim();
    }

    public String getProjectRemark() {
        return projectRemark;
    }

    public void setProjectRemark(String projectRemark) {
        this.projectRemark = projectRemark == null ? null : projectRemark.trim();
    }

    public String getProjectParentCode() {
        return projectParentCode;
    }

    public void setProjectParentCode(String projectParentCode) {
        this.projectParentCode = projectParentCode == null ? null : projectParentCode.trim();
    }

    public Integer getProjectLevel() {
        return projectLevel;
    }

    public void setProjectLevel(Integer projectLevel) {
        this.projectLevel = projectLevel;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus == null ? null : projectStatus.trim();
    }

    public String getCreateUserCode() {
        return createUserCode;
    }

    public void setCreateUserCode(String createUserCode) {
        this.createUserCode = createUserCode == null ? null : createUserCode.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUserCode() {
        return updateUserCode;
    }

    public void setUpdateUserCode(String updateUserCode) {
        this.updateUserCode = updateUserCode == null ? null : updateUserCode.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}