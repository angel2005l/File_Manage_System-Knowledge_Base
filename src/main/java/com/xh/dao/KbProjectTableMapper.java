package com.xh.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xh.entity.KbProjectTable;

public interface KbProjectTableMapper {

	// 判断该层级表信息是否存在
	public boolean isExistProjectTable(int projectLevel) throws SQLException;

	// 查询当前最大开放级数
	public int selectProjectLevelMax() throws SQLException;

	// 保存项目表路径
	public int insertProject(KbProjectTable obj) throws SQLException;

	// 创建项目表
	public int createProjectTable(@Param("projectTableName") String projectTableName, @Param("projectLevel") int projectLevel)
			throws SQLException;

	// 根据层级查询表名称
	public String selectProjectTableNameByProjectLevel(@Param("projectLevel") int projectLevel) throws SQLException;

	// 查询项目分表是否存在
	public boolean isExistProjectDataTable(String ptName) throws SQLException;
	
	/**
	 * 
	 * @Title: selectAllProFormName  
	 * @Description: 查询所有的项目表的表名 
	 * @author 陈专懂 
	 * @return List<String> 
	 * @date 2018年6月28日  
	 * @version 1.0
	 */
	public List<String> selectAllProFormName();
	
}