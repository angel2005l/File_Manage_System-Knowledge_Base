package com.xh.dao;

import com.xh.entity.KbFile;

public interface KbFileMapper {
    KbFile selectByPrimaryKey(Integer id);
}