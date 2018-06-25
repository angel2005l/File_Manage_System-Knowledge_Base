package com.xh.dao;

import java.sql.SQLException;
import java.util.List;

import com.xh.entity.KbUser;

public interface KbUserMapper {
	public KbUser selectByPrimaryKey(Integer id);

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
	//根据部门编码查询上级部门的领导层用户
	public List<KbUser> selectSuperiorUserByUserDeptCode(String userDeptCode) throws SQLException;

}
