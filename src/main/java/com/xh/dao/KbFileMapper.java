package com.xh.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.xh.entity.KbFile;

public interface KbFileMapper {

	// 新增文件信息
	public int insertFile(KbFile obj) throws SQLException;

	// 根据员工编码查询当前项目下的所有文件
	public List<Map<String, Object>> selectFileByUserCode(String fileTableName, String projectCode, String userCode)
			throws SQLException;

	// 根据员工编码查询当前项目下的所有文件行数
	public int selectFileCount(String fileName, String projectCode, String userCode) throws SQLException;

}