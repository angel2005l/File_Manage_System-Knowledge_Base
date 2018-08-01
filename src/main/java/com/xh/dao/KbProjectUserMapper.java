package com.xh.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xh.entity.KbProjectUser;

public interface KbProjectUserMapper {
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
	public List<Map<String, Object>> selectProjectSimpleInfoByUserCodeAndMethod(@Param("userCode") String userCode,
			@Param("method") String method,@Param("projectTable") String projectTable,@Param("formProjectName") String formProjectName) throws SQLException;

	/**
	 * 
	 * @Title: insertProjectUsers
	 * @Description: 新增项目信息关联表信息 新版本
	 * @author 黄官易
	 * @param kpuList
	 * @return
	 * @throws SQLException
	 * @return int
	 * @date 2018年6月29日
	 * @version 1.0
	 */

	public int batchInsertProjectUsers(List<KbProjectUser> kpuList) throws SQLException;

	/**
	 * 
	 * @Title: selectProjectPermissionByUserCode
	 * @Description: 查询特定的项目权限
	 * @author 黄官易
	 * @param projectCode
	 * @param userCode
	 * @return
	 * @throws SQLException
	 * @return String
	 * @date 2018年7月4日
	 * @version 1.0
	 */
	public String selectProjectPermissionByUserCode(@Param("projectCode") String projectCode,
			@Param("userCode") String userCode) throws SQLException;

	/**
	 * 
	 * @Title: updateCollectByUserCodeAndMainCode
	 * @Description: 根据用户编码和主方法更新收藏
	 * @author 黄官易
	 * @param userCode
	 * @param projectMainCode
	 * @return
	 * @throws SQLException
	 * @return int
	 * @date 2018年7月9日
	 * @version 1.0
	 */
	public int updateCollectByUserCodeAndMainCode(@Param("isCollect") String isCollect,
			@Param("userCode") String userCode, @Param("projectMainCode") String projectMainCode) throws SQLException;

	/**
	 * 
	 * @Title: selectProjectMainInfo
	 * @Description: 获得主项目信息
	 * @author 黄官易
	 * @param userCode
	 * @param project
	 * @return
	 * @throws SQLException
	 * @return String[]
	 * @date 2018年7月24日
	 * @version 1.0
	 */
	public String selectProjectMainInfo(@Param("userCode") String userCode, @Param("projectCode") String projectCode)
			throws SQLException;

	/**
	 * 
	 * @Title: selectProjectMainCodeByUserCodeAndProjectCode
	 * @Description: 获得主项目编码
	 * @author 黄官易
	 * @param userCode
	 * @param projectCode
	 * @return
	 * @throws SQLException
	 * @return String
	 * @date 2018年7月30日
	 * @version 1.0
	 */
	public String selectProjectMainCodeByUserCodeAndProjectCode(@Param("userCode") String userCode,
			@Param("projectCode") String projectCode) throws SQLException;

	/**
	 * 
	 * @Title: selectUsersByProjectCode
	 * @Description: 根据项目编码 获得参员工简易信息
	 * @author 黄官易
	 * @param projetcCode
	 * @return
	 * @throws SQLException
	 * @return List<Map<String,String>>
	 * @date 2018年7月27日
	 * @version 1.0
	 */
	public List<Map<String, String>> selectUsersByProjectCode(String projectCode) throws SQLException;

}