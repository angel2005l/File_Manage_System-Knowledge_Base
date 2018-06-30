package com.xh.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.xh.base.BaseService;
import com.xh.dao.KbUserMapper;
import com.xh.entity.KbUser;
import com.xh.service.IUserService;
import com.xh.uitl.MD5Util;
import com.xh.uitl.Result;

@Service("userServiceImpl")
public class UserServiceImpl extends BaseService implements IUserService {
	private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class); // 日志对象
	@Autowired
	private KbUserMapper kum; // 员工数据库操作对象

	@Override

	public Result<KbUser> login(String userCode, String userPassword) throws Exception {
		try {
			KbUser userInfo = kum.selectUserByUserCode(userCode);// 根据员工编码查询员工信息
			if (null == userInfo || "3".equals(userInfo.getUserStatus())) {
				// 对象不存在或者状态为3（删除）
				return rtnFailResult(Result.ERROR_4100, "员工不存在");
			} else if ("2".equals(userInfo.getUserStatus())) {
				// 对象状态2（锁定）
				return rtnFailResult(Result.ERROR_4100, "员工账户被锁定");
			} else {
				// 检验密码是否正确
				return MD5Util.check(userPassword, userInfo.getUserSalt(), userInfo.getUserPassword())
						? rtnSuccessResult("登录成功", userInfo)
						: rtnFailResult(Result.ERROR_4100, "用户密码不正确");
			}
		} catch (Exception e) {
			log.error("员工登陆数据接口异常,异常原因【" + e.toString() + "】");
			return rtnErrorResult(Result.ERROR_6000, "员工登陆数据接口异常,请联系管理员");
		}
	}

	@Override
	public Result<Object> insUser(KbUser user) throws Exception {
		if (null == user) {
			rtnFailResult(Result.ERROR_4000, "员工数据为空");
		}
		try {
			if (kum.insertUser(user) > 0) {
				return rtnSuccessResult("添加员工成功");
			} else {
				return rtnFailResult(Result.ERROR_4000, "添加员工失败");
			}
		} catch (SQLException e) {
			log.error("添加员工数据接口异常,异常原因：【" + e.toString() + "】");
			return rtnErrorResult(Result.ERROR_6000, "添加员工数据接口异常,请联系系统管理员");
		}
	}

	@Transactional(rollbackFor = { Exception.class })
	@Override
	public Result<Object> batchInsUsers(List<KbUser> users) throws Exception {
		if (null == users || users.isEmpty()) {
			return rtnFailResult(Result.ERROR_4000, "用户数据");
		}
		try {
			if (kum.batchInsertUsers(users) == users.size()) {
				return rtnSuccessResult("批量添加员工成功");
			} else {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();// 手动回滚
				return rtnSuccessResult("批量添加员工失败");
			}
		} catch (SQLException e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();// 手动回滚
			log.error("批量添加员工数据接口异常,异常原因：【" + e.toString() + "】");
			return rtnErrorResult(Result.ERROR_6000, "批量添加员工数据接口异常,请联系系统管理员");
		}
	}

	@Override
	public Result<List<KbUser>> selUsersByUserDeptCode(String userDeptCode) throws Exception {
		try {
			List<KbUser> kus = kum.selectUsersByUserDeptCode(userDeptCode);
			return rtnSuccessResult("", kus);
		} catch (SQLException e) {
			log.error("根据部门编码查询全部的用户接口异常,异常信息:【" + e.toString() + "】");
			return rtnErrorResult(Result.ERROR_6000, "查询系统异常,请联系系统管理员");
		}
	}

	@Override
	public Result<List<KbUser>> selSuperiorUserByUserDeptCode(String userDeptCode) throws Exception {
		try {
			List<KbUser> kus = kum.selectSuperiorUserByUserDeptCode(userDeptCode);
			return rtnSuccessResult("", kus);
		} catch (SQLException e) {
			log.error("根据部门编码查询上级部门的领导层用户,异常信息:【" + e.toString() + "】");
			return rtnErrorResult(Result.ERROR_6000, "查询系统异常,请联系系统管理员");
		}
	}
}
