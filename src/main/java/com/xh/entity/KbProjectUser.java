package com.xh.entity;

public class KbProjectUser {
	private Integer id;

	private String projectCode;

	private String projectName;

	private String projectPermission;

	private Integer projectLevel;

	private String userCode;

	private String userName;

	private String userDeptCode;

	private String projectMainCode;

	private String projectMainName;

	private String projectIsCollect;

	private String createUserCode;

	private String createTime;

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

	public String getProjectPermission() {
		return projectPermission;
	}

	public void setProjectPermission(String projectPermission) {
		this.projectPermission = projectPermission == null ? null : projectPermission.trim();
	}

	public Integer getProjectLevel() {
		return projectLevel;
	}

	public void setProjectLevel(Integer projectLevel) {
		this.projectLevel = projectLevel;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode == null ? null : userCode.trim();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public String getUserDeptCode() {
		return userDeptCode;
	}

	public void setUserDeptCode(String userDeptCode) {
		this.userDeptCode = userDeptCode == null ? null : userDeptCode.trim();
	}

	public String getProjectMainCode() {
		return projectMainCode;
	}

	public void setProjectMainCode(String projectMainCode) {
		this.projectMainCode = projectMainCode == null ? null : projectMainCode.trim();
	}

	public String getProjectMainName() {
		return projectMainName;
	}

	public void setProjectMainName(String projectMainName) {
		this.projectMainName = projectMainName == null ? null : projectMainName.trim();
	}

	public String getProjectIsCollect() {
		return projectIsCollect;
	}

	public void setProjectIsCollect(String projectIsCollect) {
		this.projectIsCollect = projectIsCollect == null ? null : projectIsCollect.trim();
	}

	public String getCreateUserCode() {
		return createUserCode;
	}

	public void setCreateUserCode(String createUserCode) {
		this.createUserCode = createUserCode == null ? null : createUserCode.trim();
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}