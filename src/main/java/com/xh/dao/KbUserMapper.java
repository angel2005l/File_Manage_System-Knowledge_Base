package com.xh.dao;

import java.sql.SQLException;

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
	public KbUser selectUserByUserName(String userName) throws SQLException;

}
