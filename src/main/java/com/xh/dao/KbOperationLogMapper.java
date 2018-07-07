package com.xh.dao;

import org.apache.ibatis.annotations.Param;

import com.xh.entity.KbOperationLog;

public interface KbOperationLogMapper {
    KbOperationLog selectByPrimaryKey(Integer id);
    /**
     * 
     * @Title: addLog  
     * @Description: 添加用户的操作日志
     * @author 陈专懂 
     * @return int 
     * @date 2018年7月6日  
     * @version 1.0
     */
    public int addLog(@Param("log")KbOperationLog log);
    
}