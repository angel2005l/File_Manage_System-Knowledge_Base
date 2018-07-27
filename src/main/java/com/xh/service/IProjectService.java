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
	public List<Map<String, Object>> selectProjectByUserCodeAndMethod(String userCode, String method) throws Exception;

	/**
	 * 
	 * @Title: insProject
	 * @Description: 保存项目信息
	 * @author 黄官易
	 * @param kp
	 * @param kpus
	 * @return
	 * @return Result<Object>
	 * @date 2018年7月24日
	 * @version 1.0
	 */
	public Result<Object> insProject(KbProject kp, List<KbProjectUser> kpu, String createUserDeptCode,
			String[] projectMainInfos) throws Exception;

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
	 * @Title: changeCollect
	 * @Description: 收藏服务接口
	 * @author 黄官易
	 * @param isCollect
	 * @param userCode
	 * @param projectMainCode
	 * @return
	 * @throws Exception
	 * @return Result<Object>
	 * @date 2018年7月9日
	 * @version 1.0
	 */
	public Result<Object> changeCollect(String isCollect, String userCode, String projectMainCode) throws Exception;

	/**
	 * 
	 * @Title: changeProjectStatus
	 * @Description: 项目完成状态更新
	 * @author 黄官易
	 * @param projectLevel
	 * @param projectCode
	 * @param userCode
	 * @return
	 * @throws Exception
	 * @return Result<Object>
	 * @date 2018年7月10日
	 * @version 1.0
	 */
	public Result<Object> changeProjectStatus(int projectLevel, String projectCode, String userCode) throws Exception;

	/**
	 * 
	 * @Title: delProject
	 * @Description: 删除项目
	 * @author 黄官易
	 * @param projectLevel
	 * @param projectCode
	 * @param userCode
	 * @return
	 * @throws Exception
	 * @return Result<Object>
	 * @date 2018年7月21日
	 * @version 1.0
	 */
	public Result<Object> delProject(int projectLevel, String projectCode, String userCode) throws Exception;

	/**
	 * 
	 * @Title: lockProject
	 * @Description: 锁定项目
	 * @author 黄官易
	 * @param projectLevel
	 * @param projectCode
	 * @param userCode
	 * @return
	 * @throws Exception
	 * @return Result<Object>
	 * @date 2018年7月21日
	 * @version 1.0
	 */
	public Result<Object> lockProject(int projectLevel, String projectCode, String userCode) throws Exception;

	/**
	 * 
	 * @Title: selectDeptCodeByProjectMainCode
	 * @Description: 根据主方法编码查询部门编码
	 * @author 黄官易
	 * @param projectMainCode
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2018年7月24日
	 * @version 1.0
	 */
	public String selectDeptCodeByProjectMainCode(String projectMainCode) throws Exception;

	/**
	 * 
	 * @Title: selectProjectMainInfo
	 * @Description: 非主项目时 获得主项目信息 0编码 1 名称 2是否收藏
	 * @author 黄官易
	 * @param userCode
	 * @param projectCode
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2018年7月24日
	 * @version 1.0
	 */
	public String[] selectProjectMainInfo(String userCode, String projectCode) throws Exception;

	/**
	 * 
	 * @Title: selectUsersByProjectCode
	 * @Description: 根据项目编码 获得参员工简易信息业务接口
	 * @author 黄官易
	 * @param projectCode
	 * @return
	 * @throws Exception
	 * @return Result<Object>
	 * @date 2018年7月27日
	 * @version 1.0
	 */
	public Result<List<Map<String, String>>> selectUsersByProjectCode(String projectCode) throws Exception;

}
