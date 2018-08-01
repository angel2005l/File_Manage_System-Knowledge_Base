/**
 * 
 */
package com.xh.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.xh.base.BaseService;
import com.xh.dao.KbFileMapper;
import com.xh.dao.KbFileTableMapper;
import com.xh.dao.KbProjectMapper;
import com.xh.dao.KbProjectTableMapper;
import com.xh.dao.KbProjectUserMapper;
import com.xh.dao.KbUserMapper;
import com.xh.entity.KbProject;
import com.xh.entity.KbProjectTable;
import com.xh.entity.KbProjectUser;
import com.xh.entity.KbUser;
import com.xh.service.IProjectService;
import com.xh.uitl.DateUtil;
import com.xh.uitl.Result;
import com.xh.uitl.StrUtil;

@Service("projectServiceImpl")
public class ProjectServiceImpl extends BaseService implements IProjectService {
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class); // 日志对象

	@Autowired
	private KbProjectMapper kpm; // 项目基础表
	@Autowired
	private KbProjectTableMapper kptm; // 项目表关联表
	@Autowired
	private KbUserMapper kum; // 用户信息表
	@Autowired
	private KbProjectUserMapper kpum;// 项目用户关联表
	@Autowired
	private KbFileTableMapper kftm;// 文件用户关联表
	@Autowired
	private KbFileMapper kfm; // 文件表

	/**
	 * 
	 * @Title: createProjectList
	 * @Description: 创建数据库表（kb_project(n)）
	 * @author 陈专懂
	 * @return void
	 * @date 2018年6月21日
	 * @version 1.0
	 */
	@Transactional(rollbackFor = { Exception.class })
	public Result<Object> createProjectList(KbProjectTable kbpt) {
		if (null == kbpt) {
			return rtnFailResult(Result.ERROR_4000, "项目表数据为空");
		}
		try {
			// 判断项目是否存在
			if (kptm.isExistProjectTable(kbpt.getProjectLevel()) || kptm.isExistProjectDataTable(kbpt.getPtName())) {
				return rtnFailResult(Result.ERROR_4000, "该项目层级已存在或项目表名重复");
			}
			// 新增项目表信息
			int flag = kptm.insertProject(kbpt);
			// 创建项目表
			int createProjectTable = kptm.createProjectTable(kbpt.getPtName(), kbpt.getProjectLevel());
			if (flag > 0 && createProjectTable == 0) {
				return rtnSuccessResult("项目表创建成功");
			} else {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();// 手动回滚
				return rtnFailResult(Result.ERROR_4000, "项目表新增失败");
			}
		} catch (SQLException e) {
			log.error("新增项目表信息及项目表接口异常,异常原因:【" + e.toString() + "】");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();// 手动回滚
			return rtnErrorResult(Result.ERROR_6000, "新增项目表文件数据接口异常,请联系系统管理员");
		}
	}

	/**
	 * @Title: selectProjectTableNameByProjectLevel
	 * @Description: 根据项目等级查询项目的编号、名字
	 * @author 陈专懂
	 * @return String
	 * @date 2018年6月22日
	 * @version 1.0
	 */
	public Result<Object> selectProjectTableNameByProjectLevel(int level) {
		// if(projectLevel==0){
		// return rtnSuccessResult("该项目为一级项目",projectLevel);
		// }
		// 可以再添加一层校验，校验查询传入的等级是否超过最高等级
		try {
			int projectLevel = level + 1;
			String ptName = kptm.selectProjectTableNameByProjectLevel(projectLevel);
			if (ptName == null) {
				return rtnErrorResult(Result.ERROR_4000, "该表不存在或该层级已为最低层级");
			}
			return rtnSuccessResult("获取项目编号、名称成功", ptName);
		} catch (SQLException e) {
			log.error("获取项目表信息及项目表接口异常,异常原因:【" + e.toString() + "】");
			return rtnErrorResult(Result.ERROR_6000, "获取项目表文件数据接口异常,请联系系统管理员");
		}
	}

	/**
	 * @Title: selectUserByUserCode
	 * @Description: 根据用户code查询用户的信息
	 * @author 陈专懂
	 * @return String
	 * @date 2018年6月22日
	 * @version 1.0
	 */
	public Result<List<KbUser>> selectUserByUserCode(List<String> strList) {
		List<KbUser> list = new ArrayList<KbUser>();
		try {
			for (int i = 0; i < strList.size(); i++) {
				KbUser user = kum.selectUserByUserCode(strList.get(i));
				list.add(user);
			}
		} catch (SQLException e) {
			log.error("查询用户表信息接口异常,异常原因:【" + e.toString() + "】");
			return rtnErrorResult(Result.ERROR_6000, "查询用户表信息接口异常,请联系系统管理员");
		}
		return rtnSuccessResult("根据用户编号查询用户信息成功", list);
	}

	/**
	 * 
	 * @Title: selectAllPro
	 * @Description: 查询当前项目下的所有子项目(最大权限)
	 * @author 陈专懂
	 * @return Result<Object>
	 * @date 2018年6月23日
	 * @version 1.0
	 */
	public Result<List<KbProject>> selectAllPro(String formName, String projectCode) {
		try {
			List<KbProject> kbp = kpm.selectSonProjectByParentCode(formName, projectCode);
			if (kbp.isEmpty()) {
				return rtnErrorResult(Result.ERROR_4000, "数据表中没有数据");
			}
			return rtnSuccessResult("项目表中项目数据查询成功", kbp);
		} catch (SQLException e) {
			log.error("查询当前项目下的所有子项目数据接口异常,异常原因:【" + e.toString() + "】");
			return rtnErrorResult(Result.ERROR_6000, "查询系统异常");
		}
	}

	/**
	 * 
	 * @Title: selectAllPro
	 * @Description: 查询当前项目下的所有子项目
	 * @author 陈专懂
	 * @return Result<Object>
	 * @date 2018年6月23日
	 * @version 1.0
	 */
	public Result<List<KbProject>> selectAllProByUser(String formName, String projectCode, String userCode) {
		try {
			List<KbProject> kbp = kpm.selectSonProjectByParentCodeAndUserCode(formName, projectCode, userCode);
			if (kbp.isEmpty()) {
				return rtnErrorResult(Result.ERROR_4000, "数据表中没有数据");
			}
			return rtnSuccessResult("项目表中项目数据查询成功", kbp);
		} catch (SQLException e) {
			log.error("查询当前项目下的所有子项目数据接口异常,异常原因:【" + e.toString() + "】");
			return rtnErrorResult(Result.ERROR_6000, "查询系统异常");
		}
	}

	@Override
	public Map<String, Object> getShareProject(String projectCode, int projectLevel, String userCode)
			throws SQLException {
		// 因为想在项目表层级与文件表层级相同 fileLevel/projectLevel 相同
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 查询项目表信息
		String projectTableName = kptm.selectProjectTableNameByProjectLevel(projectLevel);
		KbProject shareProject = kpm.selectProjectByProjectCode(projectTableName, projectCode);
		// 查询文件表信息
		String fileTableName = kftm.selectFileTableNameByFileLevel(projectLevel);
		List<Map<String, Object>> shareFiles = kfm.selectFileByUserCode(fileTableName, projectCode, userCode);
		if (null != shareProject && null != shareFiles) {
			resultMap.put("shareProject", shareProject);
			resultMap.put("shareFiles", shareFiles);
		}
		return resultMap;
	}

	@Override
	public List<Map<String, Object>> selectProjectByUserCodeAndMethod(String userCode, String method,String formProjectName) throws Exception {
		try {
			String projectTable = kptm.selectProjectTableNameByProjectLevel(0);
			return kpum.selectProjectSimpleInfoByUserCodeAndMethod(userCode, method,projectTable,formProjectName);
		} catch (SQLException e) {
			log.error("根据用户编码查询项目简易信息数据接口异常,异常原因:【" + e.toString() + "】");
			return null;
		}

	}

	@Transactional(rollbackFor = { Exception.class })
	@Override
	public Result<Object> insProject(KbProject kp, List<KbProjectUser> kpus, String createUserDeptCode,
			String[] projectMainInfos) throws Exception {
		String projectTableName = "";
		Integer projectLevel = kp.getProjectLevel(); // 根据当前的项目信息获得项目等级
		try {
			projectTableName = kptm.selectProjectTableNameByProjectLevel(projectLevel);// 项目表名称
		} catch (SQLException e) {
			log.error("获得项目表信息异常,异常原因【" + e.toString() + "】");
			return rtnErrorResult(Result.ERROR_6000, "获得项目表信息异常,请联系系统管理员");
		}
		if (StrUtil.notBlank(projectTableName)) {
			try {
				List<KbUser> superiorUserList = kum.selectSuperiorUserByUserDeptCode(createUserDeptCode);
				if (null != superiorUserList && !superiorUserList.isEmpty()) {
					for (KbUser kbUser : superiorUserList) {
						KbProjectUser kpu = new KbProjectUser();
						kpu.setProjectCode(kp.getProjectCode());
						kpu.setProjectName(kp.getProjectName());
						kpu.setProjectPermission("read");
						kpu.setProjectLevel(projectLevel);
						kpu.setUserCode(kbUser.getUserCode());
						kpu.setUserName(kbUser.getUserName());
						kpu.setUserDeptCode(kbUser.getUserDeptCode());
						kpu.setProjectMainCode(projectMainInfos[0]);
						kpu.setProjectMainName(projectMainInfos[1]);
						kpu.setProjectIsCollect(projectMainInfos[2]);
						kpu.setCreateUserCode("kb_system");
						kpu.setCreateTime(DateUtil.curDateYMDHMS());
						kpus.add(kpu);
					}
				}
			} catch (SQLException e) {
				log.error("根据部门编码查询上级部门的领导层用户接口异常,异常原因:【" + e.toString() + "】");
				return rtnErrorResult(Result.ERROR_6000, "获取上级部门信息异常,请联系系统管理员");
			}
			// 执行操作 保证数据操作的原子性
			try {
				int insProNum = kpm.insertProject(kp, projectTableName);
				int insUsersNum = kpum.batchInsertProjectUsers(kpus);
				if (insProNum > 0 && insUsersNum > 0) {
					return rtnSuccessResult("新建项目成功");
				} else {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();// 手动回滚
					return rtnFailResult(Result.ERROR_4000, "新建项目失败");
				}
			} catch (SQLException e) {
				log.error("新建项目数据接口异常,异常原因:【" + e.toString() + "】");
				return rtnErrorResult(Result.ERROR_6000, "新建项目异常,请联系系统管理员");
			}
		}
		return rtnFailResult(Result.ERROR_4300, "无相关联数据表信息/该层级未开放,请联系系统管理员");
	}

	@Override
	public KbProject selectProjectInfoByProjectCode(int projectLevel, String projectCode) throws Exception {
		String projectTableName = kptm.selectProjectTableNameByProjectLevel(projectLevel);
		if (StrUtil.notBlank(projectTableName)) {
			try {
				return kpm.selectProjectInfoByProjectCode(projectTableName, projectCode);
			} catch (SQLException e) {
				log.error("根据项目编码获得项目信息数据接口异常,异常原因:【" + e.toString() + "】");
			}
		}
		return null;
	}

	@Override
	public String selectProjectNameByProjectCode(int projectLevel, String projectCode) throws Exception {
		String projectTableName = kptm.selectProjectTableNameByProjectLevel(projectLevel);
		if (StrUtil.notBlank(projectTableName)) {
			try {
				return kpm.selectProjectNameByProjectCode(projectTableName, projectCode);
			} catch (SQLException e) {
				log.error("根据项目编码获得项目名称数据接口异常,异常原因:【" + e.toString() + "】");
			}
		}
		return "";
	}

	@Transactional(rollbackFor = { Exception.class })
	@Override
	public Result<Object> changeCollect(String isCollect, String userCode, String projectMainCode) throws Exception {
		if (StrUtil.isBlank(isCollect) || StrUtil.isBlank(userCode) || StrUtil.isBlank(projectMainCode)) {
			return rtnFailResult(Result.ERROR_4000, "收藏失败");
		}
		try {
			kpum.updateCollectByUserCodeAndMainCode(isCollect, userCode, projectMainCode);
		} catch (SQLException e) {
			log.error("项目收藏数据接口异常,异常原因:【" + e.toString() + "】");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return rtnErrorResult(Result.ERROR_6000, "");
		}
		return rtnSuccessResult("收藏成功");
	}

	@Transactional(rollbackFor = { Exception.class })
	@Override
	public Result<Object> changeProjectStatus(int projectLevel, String projectCode, String userCode) throws Exception {
		try {
			int uptNum = kpm.updateProjectStatus(projectLevel, projectCode, "completed", userCode);
			if (uptNum < 1) {
				return rtnFailResult(Result.ERROR_4000, "状态更改失败（您可能不是项目参与者）");
			} else if (uptNum == 1) {
				return rtnSuccessResult("状态更改成功");
			} else {
				return rtnFailResult(Result.ERROR_4000, "状态更改失败");
			}
		} catch (SQLException e) {
			log.error("项目状态更新数据接口异常,异常原因:【" + e.toString() + "】");
			return rtnErrorResult(Result.ERROR_6000, "服务器异常,请联系系统管理员");
		}
	}

	@Override
	public Result<Object> delProject(int projectLevel, String projectCode, String userCode) throws Exception {
		// 删除项目
		/*
		 * 1.当正常的项目，超过30分钟的无法删除 2.当时锁定项目时，拥有删除无实现限制
		 */
		try {
			return kpm.delProject(projectLevel, projectCode, userCode) > 0 ? rtnSuccessResult("项目删除成功")
					: rtnFailResult(Result.ERROR_4300, "项目删除失败");
		} catch (SQLException e) {
			log.error("删除项目数据接口异常,异常原因:【" + e.toString() + "】");
			return rtnErrorResult(Result.ERROR_6000, "服务器异常,请联系系统管理员");
		}
	}

	@Override
	public Result<Object> lockProject(int projectLevel, String projectCode, String userCode) throws Exception {
		try {
			return kpm.lockProject(projectLevel, projectCode, userCode) > 0 ? rtnSuccessResult("项目已锁定")
					: rtnFailResult(Result.ERROR_4300, "项目锁定失败");
		} catch (SQLException e) {
			log.error("锁定项目数据接口异常,异常原因:【" + e.toString() + "】");
			return rtnErrorResult(Result.ERROR_6000, "服务器异常,请联系系统管理员");
		}
	}

	@Override
	public String selectDeptCodeByProjectMainCode(String projectMainCode) throws Exception {
		try {
			String projectTableName = kptm.selectProjectTableNameByProjectLevel(0);
			return kum.selectDeptCodeByProjectMainCode(projectMainCode, projectTableName);
		} catch (SQLException e) {
			log.error("根据主方法编码查询部门编码数据接口异常,异常原因:【" + e.toString() + "】");
			return "";
		}
	}

	@Override
	public String[] selectProjectMainInfo(String userCode, String projectCode) throws Exception {
		try {
			return kpum.selectProjectMainInfo(userCode, projectCode);
		} catch (SQLException e) {
			log.error("非主项目时获得主项目信息数据接口异常,异常原因:【" + e.toString() + "】");
			return new String[3];
		}
	}

	@Override
	public Result<List<Map<String, String>>> selectUsersByProjectCode(String projectCode) throws Exception {
		try {
			return rtnSuccessResult("", kpum.selectUsersByProjectCode(projectCode));
		} catch (SQLException e) {
			log.error("根据项目编码获得参员工简易信息数据接口异常,异常原因:【" + e.toString() + "】");
			return rtnErrorResult(Result.ERROR_6000, "服务器异常,请联系系统管理员");
		}
	}

}
