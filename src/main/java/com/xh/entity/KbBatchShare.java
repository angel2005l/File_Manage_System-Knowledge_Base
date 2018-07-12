package com.xh.entity;

public class KbBatchShare {
    private Integer id;

    private String shareCode;

    private String projectCode;

    private Integer projectLevel;

    private String shareTime;

    private String createTime;

    private String createUserCode;

    private String fileCodes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShareCode() {
        return shareCode;
    }

    public void setShareCode(String shareCode) {
        this.shareCode = shareCode == null ? null : shareCode.trim();
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode == null ? null : projectCode.trim();
    }

    public Integer getProjectLevel() {
        return projectLevel;
    }

    public void setProjectLevel(Integer projectLevel) {
        this.projectLevel = projectLevel;
    }

   
	public String getShareTime() {
		return shareTime;
	}

	public void setShareTime(String shareTime) {
		this.shareTime = shareTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateUserCode() {
        return createUserCode;
    }

    public void setCreateUserCode(String createUserCode) {
        this.createUserCode = createUserCode == null ? null : createUserCode.trim();
    }

    public String getFileCodes() {
        return fileCodes;
    }

    public void setFileCodes(String fileCodes) {
        this.fileCodes = fileCodes == null ? null : fileCodes.trim();
    }
}