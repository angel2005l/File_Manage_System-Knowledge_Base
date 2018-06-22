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

    private String createTime;

    private String updateUserCode;

    private String updateTime;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the projectCode
	 */
	public String getProjectCode() {
		return projectCode;
	}

	/**
	 * @param projectCode the projectCode to set
	 */
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * @return the projectType
	 */
	public String getProjectType() {
		return projectType;
	}

	/**
	 * @param projectType the projectType to set
	 */
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	/**
	 * @return the projectInfo
	 */
	public String getProjectInfo() {
		return projectInfo;
	}

	/**
	 * @param projectInfo the projectInfo to set
	 */
	public void setProjectInfo(String projectInfo) {
		this.projectInfo = projectInfo;
	}

	/**
	 * @return the projectRemark
	 */
	public String getProjectRemark() {
		return projectRemark;
	}

	/**
	 * @param projectRemark the projectRemark to set
	 */
	public void setProjectRemark(String projectRemark) {
		this.projectRemark = projectRemark;
	}

	/**
	 * @return the projectParentCode
	 */
	public String getProjectParentCode() {
		return projectParentCode;
	}

	/**
	 * @param projectParentCode the projectParentCode to set
	 */
	public void setProjectParentCode(String projectParentCode) {
		this.projectParentCode = projectParentCode;
	}

	/**
	 * @return the projectLevel
	 */
	public Integer getProjectLevel() {
		return projectLevel;
	}

	/**
	 * @param projectLevel the projectLevel to set
	 */
	public void setProjectLevel(Integer projectLevel) {
		this.projectLevel = projectLevel;
	}

	/**
	 * @return the projectStatus
	 */
	public String getProjectStatus() {
		return projectStatus;
	}

	/**
	 * @param projectStatus the projectStatus to set
	 */
	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}

	/**
	 * @return the createUserCode
	 */
	public String getCreateUserCode() {
		return createUserCode;
	}

	/**
	 * @param createUserCode the createUserCode to set
	 */
	public void setCreateUserCode(String createUserCode) {
		this.createUserCode = createUserCode;
	}

	/**
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the updateUserCode
	 */
	public String getUpdateUserCode() {
		return updateUserCode;
	}

	/**
	 * @param updateUserCode the updateUserCode to set
	 */
	public void setUpdateUserCode(String updateUserCode) {
		this.updateUserCode = updateUserCode;
	}

	/**
	 * @return the updateTime
	 */
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @param id
	 * @param projectCode
	 * @param projectName
	 * @param projectType
	 * @param projectInfo
	 * @param projectRemark
	 * @param projectParentCode
	 * @param projectLevel
	 * @param projectStatus
	 * @param createUserCode
	 * @param createTime
	 * @param updateUserCode
	 * @param updateTime
	 */
	public KbProject(Integer id, String projectCode, String projectName, String projectType, String projectInfo,
			String projectRemark, String projectParentCode, Integer projectLevel, String projectStatus,
			String createUserCode, String createTime, String updateUserCode, String updateTime) {
		super();
		this.id = id;
		this.projectCode = projectCode;
		this.projectName = projectName;
		this.projectType = projectType;
		this.projectInfo = projectInfo;
		this.projectRemark = projectRemark;
		this.projectParentCode = projectParentCode;
		this.projectLevel = projectLevel;
		this.projectStatus = projectStatus;
		this.createUserCode = createUserCode;
		this.createTime = createTime;
		this.updateUserCode = updateUserCode;
		this.updateTime = updateTime;
	}

	/**
	 * 
	 */
	public KbProject() {
		super();
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "KbProject [id=" + id + ", projectCode=" + projectCode + ", projectName=" + projectName
				+ ", projectType=" + projectType + ", projectInfo=" + projectInfo + ", projectRemark=" + projectRemark
				+ ", projectParentCode=" + projectParentCode + ", projectLevel=" + projectLevel + ", projectStatus="
				+ projectStatus + ", createUserCode=" + createUserCode + ", createTime=" + createTime
				+ ", updateUserCode=" + updateUserCode + ", updateTime=" + updateTime + "]";
	}

 
}