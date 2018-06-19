package com.xh.entity;

import java.util.Date;

public class KbServer {
    private Integer id;

    private String serverCode;

    private String serverName;

    private Integer serverCore;

    private Integer serverMemory;

    private Integer serverBandWidth;

    private String inIp;

    private String outIp;

    private String inConnect;

    private String outConnect;

    private String serverStatus;

    private String createUserCode;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServerCode() {
        return serverCode;
    }

    public void setServerCode(String serverCode) {
        this.serverCode = serverCode == null ? null : serverCode.trim();
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName == null ? null : serverName.trim();
    }

    public Integer getServerCore() {
        return serverCore;
    }

    public void setServerCore(Integer serverCore) {
        this.serverCore = serverCore;
    }

    public Integer getServerMemory() {
        return serverMemory;
    }

    public void setServerMemory(Integer serverMemory) {
        this.serverMemory = serverMemory;
    }

    public Integer getServerBandWidth() {
        return serverBandWidth;
    }

    public void setServerBandWidth(Integer serverBandWidth) {
        this.serverBandWidth = serverBandWidth;
    }

    public String getInIp() {
        return inIp;
    }

    public void setInIp(String inIp) {
        this.inIp = inIp == null ? null : inIp.trim();
    }

    public String getOutIp() {
        return outIp;
    }

    public void setOutIp(String outIp) {
        this.outIp = outIp == null ? null : outIp.trim();
    }

    public String getInConnect() {
        return inConnect;
    }

    public void setInConnect(String inConnect) {
        this.inConnect = inConnect == null ? null : inConnect.trim();
    }

    public String getOutConnect() {
        return outConnect;
    }

    public void setOutConnect(String outConnect) {
        this.outConnect = outConnect == null ? null : outConnect.trim();
    }

    public String getServerStatus() {
        return serverStatus;
    }

    public void setServerStatus(String serverStatus) {
        this.serverStatus = serverStatus == null ? null : serverStatus.trim();
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