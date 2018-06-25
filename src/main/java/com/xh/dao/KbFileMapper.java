package com.xh.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xh.entity.KbFile;
import com.xh.entity.KbFileUser;

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