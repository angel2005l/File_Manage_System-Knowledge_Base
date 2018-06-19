package com.xh.dao;

import com.xh.entity.KbFileTable;

public interface KbFileTableMapper {
    KbFileTable selectByPrimaryKey(Integer id);
}