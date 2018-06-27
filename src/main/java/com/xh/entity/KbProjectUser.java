package com.xh.entity;

public class KbProjectUser {
	private Integer id;

	private String projectCode;

	private String projectName;

	private String projectPermission;

	private String userCode;

	private String userName;

	private String userDeptCode;

	private String createUserCode;

	private String createTime;

	private Integer projectLevel;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
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
	 * @param projectCode
	 *            the projectCode to set
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
	 * @param projectName
	 *            the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * @return the projectPermission
	 */
	public String getProjectPermission() {
		return projectPermission;
	}

	/**
	 * @param projectPermission
	 *            the projectPermission to set
	 */
	public void setProjectPermission(String projectPermission) {
		this.projectPermission = projectPermission;
	}

	/**
	 * @return the userCode
	 */
	public String getUserCode() {
		return userCode;
	}

	/**
	 * @param userCode
	 *            the userCode to set
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the userDeptCode
	 */
	public String getUserDeptCode() {
		return userDeptCode;
	}

	/**
	 * @param userDeptCode
	 *            the userDeptCode to set
	 */
	public void setUserDeptCode(String userDeptCode) {
		this.userDeptCode = userDeptCode;
	}

	/**
	 * @return the createUserCode
	 */
	public String getCreateUserCode() {
		return createUserCode;
	}

	/**
	 * @param createUserCode
	 *            the createUserCode to set
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
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the projectLevel
	 */
	public Integer getProjectLevel() {
		return projectLevel;
	}

	/**
	 * @param projectLevel
	 *            the projectLevel to set
	 */
	public void setProjectLevel(Integer projectLevel) {
		this.projectLevel = projectLevel;
	}

	/**
	 * @param id
	 * @param projectCode
	 * @param projectName
	 * @param projectPermission
	 * @param userCode
	 * @param userName
	 * @param userDeptCode
	 * @param createUserCode
	 * @param createTime
	 * @param projectLevel
	 */
	public KbProjectUser(Integer id, String projectCode, String projectName, String projectPermission, String userCode,
			String userName, String userDeptCode, String createUserCode, String createTime, Integer projectLevel) {
		super();
		this.id = id;
		this.projectCode = projectCode;
		this.projectName = projectName;
		this.projectPermission = projectPermission;
		this.userCode = userCode;
		this.userName = userName;
		this.userDeptCode = userDeptCode;
		this.createUserCode = createUserCode;
		this.createTime = createTime;
		this.projectLevel = projectLevel;
	}

	/**
	 * 
	 */
	public KbProjectUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "KbProjectUser [id=" + id + ", projectCode=" + projectCode + ", projectName=" + projectName
				+ ", projectPermission=" + projectPermission + ", userCode=" + userCode + ", userName=" + userName
				+ ", userDeptCode=" + userDeptCode + ", createUserCode=" + createUserCode + ", createTime=" + createTime
				+ ", projectLevel=" + projectLevel + "]";
	}

}