package com.xh.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xh.entity.KbProject;

public interface KbProjectMapper {
	KbProject selectByPrimaryKey(Integer id);

	// 添加项目信息
	public int insertProject(@Param("kb") KbProject kbObj, @Param("formName") String formName) throws SQLException;

	/**
	 * 
	 * @Title: selectSonProjectByParentCode
	 * @Description: 查询当前项目下的所有子项目
	 * @author 陈专懂
	 * @param formName
	 * @param projectParentCode
	 * @return
	 * @return List<KbProject>
	 * @date 2018年6月27日
	 * @version 1.0
	 */
	public List<KbProject> selectSonProjectByParentCode(@Param("formName") String formName,
			@Param("projectParentCode") String projectParentCode) throws SQLException;;

	/**
	 * 
	 * @Title: selectProjectByProjectCode
	 * @Description: 查询特定的项目信息
	 * @author 黄官易
	 * @param projectTableName
	 * @param projectCode
	 * @return
	 * @throws SQLException
	 * @return KbProject
	 * @date 2018年6月27日
	 * @version 1.0
	 */
	public KbProject selectProjectByProjectCode(@Param("projectTableName") String projectTableName,
			@Param("projectCode") String projectCode) throws SQLException;

}