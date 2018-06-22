package com.xh.entity;

import java.io.Serializable;

public class KbFileUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5383314717397681908L;
	private Integer id;
	private String fileCode;

	private String fileName;

	private String fileType;

	private String filePermission;

	private String userCode;

	private String userName;

	private String userDeptCode;

	private String createUserCode;

	private String createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFileCode() {
		return fileCode;
	}

	public void setFileCode(String fileCode) {
		this.fileCode = fileCode == null ? null : fileCode.trim();
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName == null ? null : fileName.trim();
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType == null ? null : fileType.trim();
	}

	public String getFilePermission() {
		return filePermission;
	}

	public void setFilePermission(String filePermission) {
		this.filePermission = filePermission == null ? null : filePermission.trim();
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