package com.xh.dao;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Param;

import com.xh.entity.KbProject;
import com.xh.entity.KbProjectTable;

public interface KbProjectTableMapper {
	// 自动生成,根据主键查询ProjectTable信息
	public KbProjectTable selectByPrimaryKey(Integer id);

	// 判断该层级表信息是否存在
	public boolean isExistProjectTable(int projectLevel) throws SQLException;

	// 查询当前最大开放级数
	public int selectProjectLevelMax() throws SQLException;

	// 保存文件表路径
	public int insertProject(KbProjectTable obj) throws SQLException;

	// 创建文件表
	public int createProjectTable(@Param("projectTableName") String projectTableName, @Param("projectLevel") int projectLevel)
			throws SQLException;

	// 根据层级查询表名称
	public String selectProjectTableNameByProjectLevel(int projectLevel) throws SQLException;

	// 查询文件分表是否存在
	public boolean isExistProjectDataTable(String ptName) throws SQLException;
}