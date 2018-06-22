/**
 * 
 */
package com.xh.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.xh.base.BaseService;
import com.xh.dao.KbProjectMapper;
import com.xh.dao.KbProjectTableMapper;
import com.xh.dao.KbUserMapper;
import com.xh.entity.KbProject;
import com.xh.entity.KbProjectTable;
import com.xh.entity.KbUser;
import com.xh.service.IProjectService;
import com.xh.uitl.Result;

/**
 * @author 陈专懂
 * @date 2018年6月21日下午3:45:15
 * @version 1.0
 */
@Service("ProjectServiceImpl")
public class ProjectServiceImpl extends BaseService implements IProjectService {
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class); // 日志对象

	@Autowired 
	private KbProjectMapper projectMapper; //项目基础表
	@Autowired 
	private KbProjectTableMapper projectTableMapper; //项目表关联表
	@Autowired 
	private KbUserMapper userMapper; //用户信息表
	
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
	public Result<Object> createProjectList(KbProjectTable kbpt){
		if (null == kbpt) {
			return rtnFailResult(Result.ERROR_4000, "项目表数据为空");
		}
		try {
			// 判断项目是否存在
			if (projectTableMapper.isExistProjectTable(kbpt.getProjectLevel()) || projectTableMapper.isExistProjectDataTable(kbpt.getPtName())) {
				return rtnFailResult(Result.ERROR_4000, "该项目层级已存在或项目表名重复");
			}
			//新增项目表信息
			int flag=projectTableMapper.insertProject(kbpt);
			//创建项目表
			int createProjectTable = projectTableMapper.createProjectTable(kbpt.getPtName(), kbpt.getProjectLevel());
			if(flag>0&&createProjectTable==0){
				return rtnSuccessResult("项目表创建成功");
			}else{
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
	 * @Description: 根据项目等级查询上级项目的编号、名字
	 * @author 陈专懂 
	 * @return String 
	 * @date 2018年6月22日  
	 * @version 1.0
	 */
	@Transactional(rollbackFor = { Exception.class })
	public Result<Object> selectProjectTableNameByProjectLevel(int projectLevel) {
//		if(projectLevel==0){
//			return rtnSuccessResult("该项目为一级项目",projectLevel);
//		}
		//可以再添加一层校验，校验查询传入的等级是否超过最高等级
		try {
			String ptName=projectTableMapper.selectProjectTableNameByProjectLevel(projectLevel);
			return rtnSuccessResult("获取上级项目编号、名称成功", ptName);
		} catch (SQLException e) {
			log.error("获取上级项目表信息及项目表接口异常,异常原因:【" + e.toString() + "】");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();// 手动回滚
			return rtnErrorResult(Result.ERROR_6000, "获取上级项目表文件数据接口异常,请联系系统管理员");
		}
	}


/**
 * 
 */
	@Transactional(rollbackFor = { Exception.class })
	public Result<Object> insertProject(Map<String,Object> map) {
		try {
			int i=projectMapper.insertProject(map);
			if(i==1){
				return rtnSuccessResult();
			}else{
				return rtnErrorResult(Result.ERROR_4000, "添加项目表信息失败");
			}
		} catch (SQLException e) {
			log.error("添加项目表信息接口异常,异常原因:【" + e.toString() + "】");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();// 手动回滚
			return rtnErrorResult(Result.ERROR_6000, "添加项目表信息接口异常,请联系系统管理员");
		}
	}

	/**
	 * 
	 */
	@Transactional(rollbackFor = { Exception.class })
	public Result<Object> selectUserByUserCode(List<String> strList,Map<String,Object> map,String projectPermission) {
		try {
			for(int i=0;i<strList.size();i++){
				KbUser user=userMapper.selectUserByUserCode(strList.get(i));
				map.put("userCode", user.getUserCode());
				map.put("userName", user.getUserName());
				map.put("userDeptCode", user.getUserDeptCode());
			}
			} catch (SQLException e) {
				log.error("查询用户表信息接口异常,异常原因:【" + e.toString() + "】");
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();// 手动回滚
				return rtnErrorResult(Result.ERROR_6000, "查询用户表信息接口异常,请联系系统管理员");
			}
		
		return null;
	}

}
