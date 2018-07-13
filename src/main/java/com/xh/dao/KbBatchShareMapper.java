package com.xh.dao;

import java.sql.SQLException;
import java.util.Map;

import com.xh.entity.KbBatchShare;

public interface KbBatchShareMapper {
	/**
	 * 
	 * @Title: insertBatchShare  
	 * @Description: 新增分享信息 
	 * @author 黄官易
	 * @param kbs
	 * @return
	 * @throws SQLException    
	 * @return int 
	 * @date 2018年7月12日  
	 * @version 1.0
	 */
	public int insertBatchShare(KbBatchShare kbs) throws SQLException;

	/**
	 * 
	 * @Title: selectBatchShareByShareCode  
	 * @Description: 根据分享码查询分享信息 
	 * @author 黄官易
	 * @param shareCode
	 * @return
	 * @throws SQLException    
	 * @return Map<String,Object> 
	 * @date 2018年7月12日  
	 * @version 1.0
	 */
	public Map<String,Object> selectBatchShareByShareCode(String shareCode) throws SQLException;

}