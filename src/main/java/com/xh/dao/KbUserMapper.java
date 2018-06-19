package com.xh.dao;

import com.xh.entity.KbUser;

public interface KbUserMapper {
    KbUser selectByPrimaryKey(Integer id);
}