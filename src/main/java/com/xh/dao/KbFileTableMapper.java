package com.xh.dao;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Param;

import com.xh.entity.KbFileTable;

public interface KbFileTableMapper {

	/**
	 * 
	 * @Title: isExistFileTable
	 * @Description: 判断该层级表信息是否存在
	 * @author 黄官易
	 * @param fileLevel
	 * @return
	 * @throws SQLException
	 * @return boolean
	 * @date 2018年6月21日
	 * @version 1.0
	 */
	public boolean isExistFileTable(int fileLevel) throws SQLException;

	/**
	 * 
	 * @Title: selectFileLevelMax
	 * @Description: 查询当前最大开放级数
	 * @author 黄官易
	 * @return
	 * @throws SQLException
	 * @return int
	 * @date 2018年6月21日
	 * @version 1.0
	 */
	public int selectFileLevelMax() throws SQLException;

	/**
	 * 
	 * @Title: insertFile
	 * @Description: 保存文件表映射
	 * @author 黄官易
	 * @param obj
	 * @return
	 * @throws SQLException
	 * @return int
	 * @date 2018年6月21日
	 * @version 1.0
	 */
	public int insertFileTable(KbFileTable obj) throws SQLException;

	/**
	 * 
	 * @Title: createFileTable
	 * @Description: 创建文件表
	 * @author 黄官易
	 * @param fileTableName
	 * @param fileLevel
	 * @return
	 * @throws SQLException
	 * @return int
	 * @date 2018年6月21日
	 * @version 1.0
	 */
	public int createFileDataTable(@Param("fileTableName") String fileTableName, @Param("fileLevel") int fileLevel)
			throws SQLException;

	/**
	 * 
	 * @Title: selectFileTableNameByFileLevel
	 * @Description: 根据层级查询表名称
	 * @author 黄官易
	 * @param fileLevel
	 * @return
	 * @throws SQLException
	 * @return String
	 * @date 2018年6月21日
	 * @version 1.0
	 */
	public String selectFileTableNameByFileLevel(int fileLevel) throws SQLException;

	/**
	 * 
	 * @Title: isExistFileDataTable
	 * @Description: 查询文件分表是否存在
	 * @author 黄官易
	 * @param ftName
	 * @return
	 * @throws SQLException
	 * @return boolean
	 * @date 2018年6月21日
	 * @version 1.0
	 */
	public boolean isExistFileDataTable(String ftName) throws SQLException;

	/**
	 * 
	 * @Title: dropFileDataTable
	 * @Description: 删除文件表
	 * @author 黄官易
	 * @return
	 * @throws SQLException
	 * @return int
	 * @date 2018年6月22日
	 * @version 1.0
	 */
	public int dropFileDataTable(String fileTableName) throws SQLException;
	/**
	 * 
	 * @Title: deleteFileTabel  
	 * @Description: 删除文件表映射
	 * @author 黄官易
	 * @return
	 * @throws SQLException    
	 * @return int 
	 * @date 2018年6月22日  
	 * @version 1.0
	 */
	public int deleteFileTabel(String ftCode) throws SQLException;

}