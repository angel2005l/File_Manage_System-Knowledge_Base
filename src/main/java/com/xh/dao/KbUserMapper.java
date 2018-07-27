package com.xh.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xh.entity.KbUser;

public interface KbUserMapper {
	/**
	 * 
	 * @Title: selectUserByUserName
	 * @Description: 根据员工编号查询员工信息
	 * @author 黄官易
	 * @param userName
	 * @return
	 * @throws SQLException
	 * @return KbUser
	 * @date 2018年6月19日
	 * @version 1.0
	 */
	public KbUser selectUserByUserCode(String userCode) throws SQLException;

	// 添加员工信息
	public int insertUser(KbUser user) throws SQLException;

	// 批量添加员工信息
	public int batchInsertUsers(List<KbUser> users) throws SQLException;

	// 根据部门编码查询全部的用户
	public List<KbUser> selectUsersByUserDeptCode(String userDeptCode) throws SQLException;

	/**
	 * 
	 * @Title: selectSuperiorUserByUserDeptCode
	 * @Description: 根据部门编码查询上级部门的领导层用户
	 * @author 黄官易
	 * @param userDeptCode
	 * @return
	 * @throws SQLException
	 * @return List<KbUser>
	 * @date 2018年6月29日
	 * @version 1.0
	 */
	public List<KbUser> selectSuperiorUserByUserDeptCode(String userDeptCode) throws SQLException;

	/**
	 * 
	 * @Title: selectDeptCodeByProjectMainCode
	 * @Description: 根据主方法编码查询部门编码
	 * @author 黄官易
	 * @param projectMainCode
	 * @param projectTabelName
	 * @return
	 * @return String
	 * @date 2018年7月24日
	 * @version 1.0
	 */
	public String selectDeptCodeByProjectMainCode(@Param("projectMainCode") String projectMainCode,
			@Param("projectTabelName") String projectTabelName);

	/**
	 * 
	 * @Title: selectUsersForAll
	 * @Description: 查询所有员工简易信息
	 * @author 黄官易
	 * @return
	 * @throws SQLException
	 * @return List<KbUser>
	 * @date 2018年7月27日
	 * @version 1.0
	 */
	public List<KbUser> selectUsersForAll() throws SQLException;
}
