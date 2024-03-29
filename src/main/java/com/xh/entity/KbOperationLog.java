package com.xh.entity;

public class KbOperationLog {
	private Integer id;

	private String logCode;

	private String logMsg;

	private String logType;

	private String logStatus;

	private String projectCode;

	private String logIsRead;

	private String logUserCode;

	private String logUserName;

	private String createUserCode;

	private String createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogCode() {
		return logCode;
	}

	public void setLogCode(String logCode) {
		this.logCode = logCode == null ? null : logCode.trim();
	}

	public String getLogMsg() {
		return logMsg;
	}

	public void setLogMsg(String logMsg) {
		this.logMsg = logMsg == null ? null : logMsg.trim();
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType == null ? null : logType.trim();
	}

	public String getLogStatus() {
		return logStatus;
	}

	public void setLogStatus(String logStatus) {
		this.logStatus = logStatus == null ? null : logStatus.trim();
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode == null ? null : projectCode.trim();
	}

	public String getLogIsRead() {
		return logIsRead;
	}

	public void setLogIsRead(String logIsRead) {
		this.logIsRead = logIsRead == null ? null : logIsRead.trim();
	}

	public String getLogUserCode() {
		return logUserCode;
	}

	public void setLogUserCode(String logUserCode) {
		this.logUserCode = logUserCode == null ? null : logUserCode.trim();
	}

	public String getLogUserName() {
		return logUserName;
	}

	public void setLogUserName(String logUserName) {
		this.logUserName = logUserName == null ? null : logUserName.trim();
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

	@Override
	public String toString() {
		return "KbOperationLog [id=" + id + ", logCode=" + logCode + ", logMsg=" + logMsg + ", logType=" + logType
				+ ", logStatus=" + logStatus + ", projectCode=" + projectCode + ", logIsRead=" + logIsRead
				+ ", logUserCode=" + logUserCode + ", logUserName=" + logUserName + ", createUserCode=" + createUserCode
				+ ", createTime=" + createTime + "]";
	}
}