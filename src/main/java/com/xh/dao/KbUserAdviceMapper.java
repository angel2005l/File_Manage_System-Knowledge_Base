package com.xh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xh.entity.KbUserAdvice;

public interface KbUserAdviceMapper {
    KbUserAdvice selectByPrimaryKey(Integer id);
    
    public List<String> parentUserCodeByCode(@Param("parentProjectCode")String parentProjectCode);
    
    public int addUserAdvice(@Param("kbUserAdvice")KbUserAdvice kbUserAdvice);
}