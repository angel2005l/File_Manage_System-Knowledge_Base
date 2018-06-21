package com.xh.dao;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Param;

import com.xh.entity.KbFileTable;

public interface KbFileTableMapper {
	// 自动生成,根据主键查询FileTable信息
	public KbFileTable selectByPrimaryKey(Integer id);

	// 判断该层级表信息是否存在
	public boolean isExistFileTable(int fileLevel) throws SQLException;

	// 查询当前最大开放级数
	public int selectFileLevelMax() throws SQLException;

	// 保存文件表路径
	public int insertFile(KbFileTable obj) throws SQLException;

	// 创建文件表
	public int createFileTable(@Param("fileTableName") String fileTableName, @Param("fileLevel") int fileLevel)
			throws SQLException;

	// 根据层级查询表名称
	public String selectFileTableNameByFileLevel(int fileLevel) throws SQLException;

	// 查询文件分表是否存在
	public boolean isExistFileDataTable(String ftName) throws SQLException;

}