package com.xh.dao;

import com.xh.entity.KbServer;

public interface KbServerMapper {
    KbServer selectByPrimaryKey(Integer id);
}