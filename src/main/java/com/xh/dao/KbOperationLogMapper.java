package com.xh.dao;

import com.xh.entity.KbOperationLog;

public interface KbOperationLogMapper {
    KbOperationLog selectByPrimaryKey(Integer id);
}