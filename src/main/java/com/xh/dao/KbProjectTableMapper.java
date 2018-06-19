package com.xh.dao;

import com.xh.entity.KbProjectTable;

public interface KbProjectTableMapper {
    KbProjectTable selectByPrimaryKey(Integer id);
}