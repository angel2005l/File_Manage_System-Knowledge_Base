package com.xh.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xh.entity.KbProject;

public interface KbProjectMapper {
	/**
	 * 
	 * @Title: insertProject
	 * @Description: 添加项目信息
	 * @author 黄官易
	 * @param kbObj
	 * @param formName
	 * @return
	 * @throws SQLException
	 * @return int
	 * @date 2018年7月4日
	 * @version 1.0
	 */
	public int insertProject(@Param("kb") KbProject kbObj, @Param("formName") String formName) throws SQLException;

	/**
	 * 
	 * @Title: selectSonProjectByParentCode
	 * @Description: 查询当前项目下的所有子项目(最大权限)
	 * @author 陈专懂
	 * @param formName
	 * @param projectParentCode
	 * @return
	 * @return List<KbProject>
	 * @date 2018年6月27日
	 * @version 1.0
	 */
	public List<KbProject> selectSonProjectByParentCode(@Param("formName") String formName,
			@Param("projectCode") String projectCode) throws SQLException;

	/**
	 * 
	 * @Title: selectSonProjectByParentCode
	 * @Description: 查询当前项目下的所有子项目
	 * @author 陈专懂
	 * @param formName
	 * @param projectParentCode
	 * @return
	 * @return List<KbProject>
	 * @date 2018年6月27日
	 * @version 1.0
	 */
	public List<KbProject> selectSonProjectByParentCodeAndUserCode(@Param("formName") String formName,
			@Param("projectCode") String projectCode, @Param("userCode") String userCode) throws SQLException;

	/**
	 * 
	 * @Title: selectProjectByProjectCode
	 * @Description: 查询特定的项目信息及其权限
	 * @author 黄官易
	 * @param projectTableName
	 * @param projectCode
	 * @return
	 * @throws SQLException
	 * @return KbProject
	 * @date 2018年6月27日
	 * @version 1.0
	 */
	public Map<String, Object> selectProjectWithProjectPerssionByProjectCode(@Param("projectTableName") String projectTableName,
			@Param("projectCode") String projectCode, @Param("userCode") String userCode) throws SQLException;

	/**
	 * 
	 * @Title: selectProjectByProjectCode
	 * @Description: 查询特定的项目信息
	 * @author 黄官易
	 * @param projectTableName
	 * @param projectCode
	 * @return
	 * @throws SQLException
	 * @return KbProject
	 * @date 2018年6月27日
	 * @version 1.0
	 */
	public KbProject selectProjectByProjectCode(@Param("projectTableName") String projectTableName,
			@Param("projectCode") String projectCode) throws SQLException;
	
	/**
	 * 
	 * @Title: selectAllPro
	 * @Description: 查询主界面的所有的项目信息
	 * @author 陈专懂
	 * @return List<KbProject>
	 * @date 2018年6月28日
	 * @version 1.0
	 */
	public List<KbProject> selectAllPro(@Param("formName") String formName);

	/**
	 * 
	 * @Title: selectSuperiorAllPro
	 * @Description: 返回功能 查询上级项目下的所有的项目
	 * @author 陈专懂
	 * @return List<KbProject>
	 * @date 2018年6月28日
	 * @version 1.0
	 */
	public List<KbProject> selectSuperiorAllPro(@Param("formName") String formName,
			@Param("projectCode") String projectCode, @Param("userCode") String userCode);

	/**
	 * 
	 * @Title: getProjectParentCode
	 * @Description: 根据projectCode获取父类的Code
	 * @author 陈专懂
	 * @return String
	 * @date 2018年6月29日
	 * @version 1.0
	 */
	public String getProjectParentCode(@Param("formName") String formName, @Param("projectCode") String projectCode);

	/**
	 * 
	 * @Title: selectProjectInfoByProjectCode
	 * @Description: 根据项目编码获得项目信息
	 * @author 黄官易
	 * @param projectTableName
	 * @param projectCode
	 * @return
	 * @throws SQLException
	 * @return KbProject
	 * @date 2018年6月30日
	 * @version 1.0
	 */
	public KbProject selectProjectInfoByProjectCode(@Param("projectTableName") String projectTableName,
			@Param("projectCode") String projectCode) throws SQLException;

	/**
	 * 
	 * @Title: selectProjectNameInfoByProjectCode
	 * @Description: 根据项目编码获得项目名称
	 * @author 黄官易
	 * @param projectTableName
	 * @param projectCode
	 * @return
	 * @throws SQLException
	 * @return String
	 * @date 2018年6月30日
	 * @version 1.0
	 */
	public String selectProjectNameByProjectCode(@Param("projectTableName") String projectTableName,
			@Param("projectCode") String projectCode) throws SQLException;

	/**
	 * 
	 * @Title: selectProjectsByUserCodeAndProjectCode
	 * @Description: 根据用户编码/项目编码 查询项目信息集合
	 * @author 黄官易
	 * @param projectTableName
	 * @param userCode
	 * @param projectCode
	 * @return
	 * @throws SQLException
	 * @return List<KbProject>
	 * @date 2018年6月30日
	 * @version 1.0
	 */
	public List<KbProject> selectProjectsByProjectCode(@Param("projectTableName") String projectTableName,
			@Param("projectCode") String projectCode) throws SQLException;

	/**
	 * 
	 * @Title: selectSuperiorProjectCodeByProjectCode
	 * @Description: 根据项目编码查询父类项目编码
	 * @author 黄官易
	 * @param projectTableName
	 * @param projectCode
	 * @return
	 * @throws SQLException
	 * @return String
	 * @date 2018年6月30日
	 * @version 1.0
	 */
	public String selectSuperiorProjectCodeByProjectCode(@Param("projectTableName") String projectTableName,
			@Param("projectCode") String projectCode) throws SQLException;
	/**
	 * 
	 * @Title: selectProjectAndSonProjectInfos  
	 * @Description: 根据userCode，projectCode查询项目详细
	 * @author 黄官易
	 * @param projectLevel
	 * @param projectCode
	 * @param userCode
	 * @return
	 * @throws SQLException    
	 * @return Map<String,Object> 
	 * @date 2018年7月5日  
	 * @version 1.0
	 */
	public Map<String,Object> selectProjectAndSonProjectInfos(@Param("projectLevel") int projectLevel,@Param("projectCode") String projectCode ,@Param("userCode") String userCode) throws SQLException;
}