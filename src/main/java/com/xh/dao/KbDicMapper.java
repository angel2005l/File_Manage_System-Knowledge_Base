package com.xh.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xh.entity.KbDic;

public interface KbDicMapper {

	/**
	 * 
	 * @Title: selectFileEventTypeForAll
	 * @Description: 获得文件事件类型
	 * @author 黄官易
	 * @return
	 * @throws SQLException
	 * @return List<KbDic>
	 * @date 2018年7月25日
	 * @version 1.0
	 */
	public List<KbDic> selectFileEventTypeForAll() throws SQLException;

	/**
	 * 
	 * @Title: selectFileEventTypeValueByCode
	 * @Description: 根据文件事件类型编码获得值
	 * @author 黄官易
	 * @param code
	 * @return
	 * @throws SQLException
	 * @return KbDic
	 * @date 2018年7月25日
	 * @version 1.0
	 */
	public String selectFileEventTypeValueByCode(String code) throws SQLException;

	/**
	 * 
	 * @Title: selectFileEventLevelForAll
	 * @Description: 获得文件事件级别
	 * @author 黄官易
	 * @return
	 * @throws SQLException
	 * @return List<KbDic>
	 * @date 2018年7月25日
	 * @version 1.0
	 */
	public List<KbDic> selectFileEventLevelForAll() throws SQLException;

	/**
	 * 
	 * @Title: selectFileEventLevelvalueByCode
	 * @Description: 根据文件事件级别编码获得值
	 * @author 黄官易
	 * @param code
	 * @return
	 * @throws SQLException
	 * @return KbDic
	 * @date 2018年7月25日
	 * @version 1.0
	 */
	public String selectFileEventLevelvalueByCode(String code) throws SQLException;

	/**
	 * 
	 * @Title: selectDicKvForAll  
	 * @Description: 获得特定的数据字典集合
	 * @author 黄官易
	 * @param dicParentCode
	 * @return
	 * @throws SQLException    
	 * @return List<Map<String,String>> 
	 * @date 2018年7月26日  
	 * @version 1.0
	 */
	public List<Map<String, String>> selectDicKvForAll(String dicParentCode) throws SQLException;
	/**
	 * 
	 * @Title: selectDicVauleByCode  
	 * @Description: 根据编码查询数据字典值 
	 * @author 黄官易
	 * @param dicParentCode
	 * @param code
	 * @return
	 * @throws SQLException    
	 * @return String 
	 * @date 2018年7月26日  
	 * @version 1.0
	 */
	public String selectDicValueByCode(@Param("dicParentCode") String dicParentCode,@Param("code") String code) throws SQLException;

}