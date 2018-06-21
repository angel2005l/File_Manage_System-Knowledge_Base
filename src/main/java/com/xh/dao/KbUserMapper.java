package com.xh.dao;

import java.sql.SQLException;

import com.xh.entity.KbUser;

public interface KbUserMapper {
	KbUser selectByPrimaryKey(Integer id);

	// 根据员工编号查询员工信息
	public KbUser selectUserByUserCode(String userCode) throws SQLException;

}