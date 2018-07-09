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
			@Param("method") String method) throws SQLException;

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

	public int batchInsertProjectUsers(@Param("kpuList") List<KbProjectUser> kpuList,
			@Param("projectCode") String projectCode, @Param("userCode") String userCode) throws SQLException;

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
	 * @Title: selectProjectUserByProjectCodeAndUserCode
	 * @Description: 根据userCode&projectCode查询用户的主项目信息
	 * @author 黄官易
	 * @return
	 * @throws SQLException
	 * @return String[]
	 * @date 2018年7月7日
	 * @version 1.0
	 */
	public String[] selectMainProjectInfoByProjectCodeAndUserCode() throws SQLException;

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
	public int updateCollectByUserCodeAndMainCode(@Param("isCollect") String isCollect,@Param("userCode") String userCode,@Param("projectMainCode") String projectMainCode) throws SQLException;

}