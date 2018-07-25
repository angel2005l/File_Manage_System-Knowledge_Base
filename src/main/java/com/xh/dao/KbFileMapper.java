package com.xh.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xh.entity.KbFile;

public interface KbFileMapper {

	/**
	 * 
	 * @Title: insertFile
	 * @Description: 新增文件信息
	 * @param obj
	 * @return
	 * @throws SQLException
	 * @author: MR.H
	 * @return: int
	 *
	 */
	public int insertFile(@Param("kf") KbFile kf, @Param("fileTabelName") String fileTabelName) throws SQLException;

	/**
	 * 
	 * @Title: selectFileByUserCode
	 * @Description: 根据员工编码查询当前项目下的所有文件
	 * @param fileTableName
	 * @param projectCode
	 * @param userCode
	 * @return
	 * @throws SQLException
	 * @author: MR.H
	 * @return: List<Map<String,Object>>
	 *
	 */
	public List<Map<String, Object>> selectFileByUserCode(@Param("fileTableName") String fileTableName,
			@Param("projectCode") String projectCode, @Param("userCode") String userCode) throws SQLException;

	/**
	 * 
	 * @Title: selectFileByParams
	 * @Description: 根据员工编码查询当前项目下的特定文件
	 * @author 黄官易
	 * @param fileTableName
	 * @param projectCode
	 * @param userCode
	 * @param fileSelMap
	 * @return
	 * @throws SQLException
	 * @return List<Map<String,Object>>
	 * @date 2018年7月11日
	 * @version 1.0
	 */
	public List<Map<String, Object>> selectFileByParams(@Param("fileTableName") String fileTableName,
			@Param("projectCode") String projectCode, @Param("userCode") String userCode,
			@Param("fileSelMap") Map<String, String> fileSelMap) throws SQLException;

	/**
	 * 
	 * @Title: selectFileCount
	 * @Description: 根据员工编码查询当前项目下的所有文件行数
	 * @param fileName
	 * @param projectCode
	 * @param userCode
	 * @return
	 * @throws SQLException
	 * @author: MR.H
	 * @return: int
	 *
	 */
	public int selectFileCount(@Param("fileTableName") String fileTableName, @Param("projectCode") String projectCode,
			@Param("userCode") String userCode) throws SQLException;

	/**
	 * 
	 * @Title: selectFileByFileCode
	 * @Description: 根据文件标号查询特定文件信息
	 * @author 黄官易
	 * @param fileTableName
	 * @param fileCode
	 * @return
	 * @throws SQLException
	 * @return KbFile
	 * @date 2018年6月22日
	 * @version 1.0
	 */
	public KbFile selectFileByFileCode(@Param("fileTableName") String fileTableName, @Param("fileCode") String fileCode)
			throws SQLException;

	/**
	 * 
	 * @Title: updateLockFile
	 * @Description: 文件锁定
	 * @author 黄官易
	 * @param fileLevel
	 * @param fileCode
	 * @param userCode
	 * @return
	 * @throws SQLException
	 * @return int
	 * @date 2018年7月25日
	 * @version 1.0
	 */
	public int updateLockFile(@Param("fileLevel") int fileLevel, @Param("fileCode") String fileCode,
			@Param("userCode") String userCode) throws SQLException;

	/**
	 * 
	 * @Title: deleteFile
	 * @Description: 文件删除
	 * @author 黄官易
	 * @param fileLevel
	 * @param fileCode
	 * @param userCode
	 * @return
	 * @throws SQLException
	 * @return int
	 * @date 2018年7月25日
	 * @version 1.0
	 */
	public int deleteFile(@Param("fileLevel") int fileLevel, @Param("fileCode") String fileCode,
			@Param("userCode") String userCode) throws SQLException;
}