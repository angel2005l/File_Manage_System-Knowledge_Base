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

	/**
	 * 
	 * @Title: insertSuperiorUserFileWithOnlyRead
	 * @Description: 默认添加上级部门所属关系
	 * @author 黄官易
	 * @param kus
	 * @return
	 * @throws SQLException
	 * @return int
	 * @date 2018年6月25日
	 * @version 1.0
	 */
	public int insertSuperiorUserFileWithOnlyRead(List<KbFileUser> kfu) throws SQLException;
}