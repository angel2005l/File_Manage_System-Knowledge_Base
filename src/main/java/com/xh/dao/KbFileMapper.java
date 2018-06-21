package com.xh.dao;

import java.sql.SQLException;

import com.xh.entity.KbFile;

public interface KbFileMapper {
    
	//文件保存
	public int insertFile(KbFile obj) throws SQLException;
	//文件
	
}