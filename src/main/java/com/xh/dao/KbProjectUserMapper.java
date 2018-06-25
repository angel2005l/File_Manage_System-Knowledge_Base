package com.xh.dao;

import java.util.List;
import java.util.Map;

import com.xh.entity.KbProjectUser;
import com.xh.entity.KbUser;

public interface KbProjectUserMapper {
    KbProjectUser selectByPrimaryKey(Integer id);
    //新增项目信息关联表信息
    public int insertProjectUser(List<KbProjectUser> listUser);
    //根据用户code来查询
    
    
}