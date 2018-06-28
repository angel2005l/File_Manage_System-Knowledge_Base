package com.xh.entity;

public class KbProjectTable {
    private Integer id;

    private String ptCode;

    private String ptName;

    private Integer projectLevel;

    private String createUserCode;

    private String createTime;

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
	 * @return the ptCode
	 */
	public String getPtCode() {
		return ptCode;
	}

	/**
	 * @param ptCode the ptCode to set
	 */
	public void setPtCode(String ptCode) {
		this.ptCode = ptCode;
	}

	/**
	 * @return the ptName
	 */
	public String getPtName() {
		return ptName;
	}

	/**
	 * @param ptName the ptName to set
	 */
	public void setPtName(String ptName) {
		this.ptName = ptName;
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
	 * @param id
	 * @param ptCode
	 * @param ptName
	 * @param projectLevel
	 * @param createUserCode
	 * @param createTime
	 */
	public KbProjectTable(Integer id, String ptCode, String ptName, Integer projectLevel, String createUserCode,
			String createTime) {
		super();
		this.id = id;
		this.ptCode = ptCode;
		this.ptName = ptName;
		this.projectLevel = projectLevel;
		this.createUserCode = createUserCode;
		this.createTime = createTime;
	}

	/**
	 * 
	 */
	public KbProjectTable() {
		super();
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "KbProjectTable [id=" + id + ", ptCode=" + ptCode + ", ptName=" + ptName + ", projectLevel="
				+ projectLevel + ", createUserCode=" + createUserCode + ", createTime=" + createTime + "]";
	}

 
}