package com.xh.entity;

public class KbUserAdvice {
    private Integer id;

    private String adviceCode;

    private String userCode;

    private String logCode;

    private String logMsg;

    private String adviceStatus;

    private String createTime;

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
	 * @return the adviceCode
	 */
	public String getAdviceCode() {
		return adviceCode;
	}

	/**
	 * @param adviceCode the adviceCode to set
	 */
	public void setAdviceCode(String adviceCode) {
		this.adviceCode = adviceCode;
	}

	/**
	 * @return the userCode
	 */
	public String getUserCode() {
		return userCode;
	}

	/**
	 * @param userCode the userCode to set
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	/**
	 * @return the logCode
	 */
	public String getLogCode() {
		return logCode;
	}

	/**
	 * @param logCode the logCode to set
	 */
	public void setLogCode(String logCode) {
		this.logCode = logCode;
	}

	/**
	 * @return the logMsg
	 */
	public String getLogMsg() {
		return logMsg;
	}

	/**
	 * @param logMsg the logMsg to set
	 */
	public void setLogMsg(String logMsg) {
		this.logMsg = logMsg;
	}

	/**
	 * @return the adviceStatus
	 */
	public String getAdviceStatus() {
		return adviceStatus;
	}

	/**
	 * @param adviceStatus the adviceStatus to set
	 */
	public void setAdviceStatus(String adviceStatus) {
		this.adviceStatus = adviceStatus;
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
	 * 
	 */
	public KbUserAdvice() {
		super();
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "KbUserAdvice [id=" + id + ", adviceCode=" + adviceCode + ", userCode=" + userCode + ", logCode="
				+ logCode + ", logMsg=" + logMsg + ", adviceStatus=" + adviceStatus + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}

	

}