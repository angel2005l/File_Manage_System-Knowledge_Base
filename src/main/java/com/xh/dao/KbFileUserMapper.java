package com.xh.dao;

import java.sql.SQLException;
import java.util.List;

import com.xh.entity.KbFileUser;

public interface KbFileUserMapper {

	/**
	 * 
	 * @Title: batchInsertFileUser   
	 * @Description: 批量插入文件与用户关联关系   
	 * @param kfus
	 * @return
	 * @throws SQLException
	 * @author: MR.H
	 * @return: int
	 *
	 */
	public int batchInsertFileUser(List<KbFileUser> kfus) throws SQLException;

	/**
	 * 
	 * @Title: insertFileUser   
	 * @Description: 插入文件与用户关联关系 
	 * @param kfu
	 * @return
	 * @throws SQLException
	 * @author: MR.H
	 * @return: int
	 *
	 */
	public int insertFileUser(KbFileUser kfu) throws SQLException;
}