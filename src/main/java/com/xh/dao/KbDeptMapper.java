package com.xh.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface KbDeptMapper {
	/**
	 * 
	 * @Title: selectDeptForAll  
	 * @Description: 查询所有的部门简易信息
	 * @author 黄官易
	 * @return
	 * @throws SQLException    
	 * @return List<Map<String,String>> 
	 * @date 2018年7月27日  
	 * @version 1.0
	 */
	public List<Map<String, String>> selectDeptForAll() throws SQLException;
		
}