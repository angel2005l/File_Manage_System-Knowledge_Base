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

	// 添加项目信息
	public Result<Object> insertProject(KbProject kbObj, List<KbProjectUser> listUser, String formName);

	// 根据员工的编号，查找员工信息
	public Result<List<KbUser>> selectUserByUserCode(List<String> strList);

	// 查询所有的根层的项目信息(最大权限)
	public Result<List<KbProject>> selectAllPro(String formName, String projectCode);

	// 查询所有的根层的项目信息
	public Result<List<KbProject>> selectAllProByUser(String formName, String projectCode,String userCode);
	
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
	public Map<String, Object> getShareProject(String projectCode, int projectLevel, String userCode)
			throws SQLException;
	
	/**
	 * 
	 * @Title: selectAllPro  
	 * @Description: 查询主界面所有的项目信息
	 * @author 陈专懂 
	 * @return List<KbProject> 
	 * @date 2018年6月28日  
	 * @version 1.0
	 */
	public List<List<KbProject>> selectAllProInMain();
}
