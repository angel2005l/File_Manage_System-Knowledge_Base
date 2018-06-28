package com.xh.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.xh.entity.KbProjectUser;

public interface KbProjectUserMapper {
	/**
	 * 
	 * @Title: insertProjectUser
	 * @Description: 新增项目信息关联表信息
	 * @author 黄官易
	 * @param listUser
	 * @return
	 * @throws SQLException
	 * @return int
	 * @date 2018年6月28日
	 * @version 1.0
	 */
	public int insertProjectUser(List<KbProjectUser> listUser) throws SQLException;

	/**
	 * 
	 * @Title: selectProjectSimpleInfoByUserCode
	 * @Description: 根据用户编码查询用户所关联的信息
	 * @author 黄官易
	 * @return
	 * @throws SQLException
	 * @return List<Map<String,Object>>
	 * @date 2018年6月28日
	 * @version 1.0
	 */
	public List<Map<String, Object>> selectProjectSimpleInfoByUserCode(String userCode) throws SQLException;

}