package com.xh.entity;

import java.util.Date;

public class KbOperationLog {
    private Integer id;

    private String logCode;

    private String logMsg;

    private String logType;

    private String logStatus;

    private String logUserCode;

    private String logUserName;

    private String createUserCode;

    private Date createTime;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}