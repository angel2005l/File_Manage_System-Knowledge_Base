/**
 * 
 */
package com.xh.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.xh.entity.KbProject;
import com.xh.entity.KbProjectTable;
import com.xh.entity.KbProjectUser;
import com.xh.entity.KbUser;
import com.xh.uitl.Result;

/**
 * @author 陈专懂
 * @date 2018年6月21日下午3:24:06
 * @version 1.0
 */
public interface IProjectService {
	// 动态建表
	public Result<Object> createProjectList(KbProjectTable kbpt);

	// 根据项目等级查询项目的编号、名字
	public Result<Object> selectProjectTableNameByProjectLevel(int projectLevel);

	// 根据员工的编号，查找员工信息
	public Result<List<KbUser>> selectUserByUserCode(List<String> strList);

	// 查询所有的根层的项目信息(最大权限)
	public Result<List<KbProject>> selectAllPro(String formName, String projectCode);

	// 查询所有的根层的项目信息
	public Result<List<KbProject>> selectAllProByUser(String formName, String projectCode, String userCode);

	/**
	 * 
	 * @Title: getShareProject
	 * @Description: 项目分享业务
	 * @author 黄官易
	 * @param projectCode
	 * @param projectLevel
	 * @param userCode
	 * @return
	 * @throws SQLException
	 * @return Map<String,Object>
	 * @date 2018年6月27日
	 * @version 1.0
	 */
	public Map<String, Object> getShareProject(String projectCode, int projectLevel, String userCode) throws Exception;

	/**
	 * 
	 * @Title: selectProjectByUserCode
	 * @Description: 根据用户编码查询项目的简易信息
	 * @author 黄官易
	 * @param userCode
	 * @return
	 * @throws Exception
	 * @return List<KbProject>
	 * @date 2018年6月28日
	 * @version 1.0
	 */
	public List<Map<String, Object>> selectProjectByUserCode(String userCode) throws Exception;

	/**
	 * 
	 * @Title: insProject
	 * @Description: 保存项目信息
	 * @author 黄官易
	 * @param kp
	 * @param kpus
	 * @return
	 * @throws Exception
	 * @return Result<Object>
	 * @date 2018年6月29日
	 * @version 1.0
	 */
	public Result<Object> insProject(KbProject kp, List<KbProjectUser> kpus) throws Exception;

	/**
	 * 
	 * @Title: selectProjectInfoByProjectCode
	 * @Description: 根据项目编码获得项目信息
	 * @author 黄官易
	 * @param projectLevel
	 * @param projectCode
	 * @return
	 * @throws Exception
	 * @return KbProject
	 * @date 2018年6月30日
	 * @version 1.0
	 */
	public KbProject selectProjectInfoByProjectCode(int projectLevel, String projectCode) throws Exception;

	/**
	 * 
	 * @Title: selectProjectNameByProjectCode
	 * @Description: 根据项目编码获得项目名称
	 * @author 黄官易
	 * @param projectLevel
	 * @param projectCode
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2018年6月30日
	 * @version 1.0
	 */
	public String selectProjectNameByProjectCode(int projectLevel, String projectCode) throws Exception;

	/**
	 * 
	 * @Title: selectProjects
	 * @Description: 获得当前层级的所有根据员工相关的项目信息
	 * @author 黄官易
	 * @param projectLevel
	 * @param superiorProjectCode
	 * @param userCode
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2018年6月30日
	 * @version 1.0
	 */
	public List<KbProject> selectProjects(int projectLevel, String projectCode, String userCode) throws Exception;

}
