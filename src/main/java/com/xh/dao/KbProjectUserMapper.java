package com.xh.dao;

import java.util.Map;

import com.xh.entity.KbProjectUser;

public interface KbProjectUserMapper {
    KbProjectUser selectByPrimaryKey(Integer id);
    
    public int insertProjectUser(Map<String,Object> map);
}