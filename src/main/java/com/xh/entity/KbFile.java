package com.xh.entity;

public class KbFile {
	private Integer id;

	private String fileCode;

	private String fileName;

	private String fileInfo;

	private String fileType;

	private String fileStatus;

	private Integer fileLevel;

	private String projectCode;

	private String fileEventType;

	private String fileEventLevel;

	private String fileResearchUserCode;

	private String fileResearchStartTime;

	private String createUserCode;

	private String createTime;

	private String updateUserCode;

	private String updateTime;

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

	public String getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(String fileInfo) {
		this.fileInfo = fileInfo == null ? null : fileInfo.trim();
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType == null ? null : fileType.trim();
	}

	public String getFileStatus() {
		return fileStatus;
	}

	public void setFileStatus(String fileStatus) {
		this.fileStatus = fileStatus == null ? null : fileStatus.trim();
	}

	public Integer getFileLevel() {
		return fileLevel;
	}

	public void setFileLevel(Integer fileLevel) {
		this.fileLevel = fileLevel;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode == null ? null : projectCode.trim();
	}

	public String getFileEventType() {
		return fileEventType;
	}

	public void setFileEventType(String fileEventType) {
		this.fileEventType = fileEventType == null ? null : fileEventType.trim();
	}

	public String getFileEventLevel() {
		return fileEventLevel;
	}

	public void setFileEventLevel(String fileEventLevel) {
		this.fileEventLevel = fileEventLevel == null ? null : fileEventLevel.trim();
	}

	public String getFileResearchUserCode() {
		return fileResearchUserCode;
	}

	public void setFileResearchUserCode(String fileResearchUserCode) {
		this.fileResearchUserCode = fileResearchUserCode == null ? null : fileResearchUserCode.trim();
	}

	public String getFileResearchStartTime() {
		return fileResearchStartTime;
	}

	public void setFileResearchStartTime(String fileResearchStartTime) {
		this.fileResearchStartTime = fileResearchStartTime;
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

	public String getUpdateUserCode() {
		return updateUserCode;
	}

	public void setUpdateUserCode(String updateUserCode) {
		this.updateUserCode = updateUserCode == null ? null : updateUserCode.trim();
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}