package com.xh.dao;

import java.util.Map;

import com.xh.entity.KbProjectUser;

public interface KbProjectUserMapper {
    KbProjectUser selectByPrimaryKey(Integer id);
    //新增项目信息关联表信息
    public int insertProjectUser(Map<String,Object> map);
    //根据用户code来查询
    
    
}