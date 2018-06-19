package com.xh.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	private KbUserMapper userMapper; // 员工数据库操作对象

	@Override
	public Result<KbUser> login(String userCode, String userPassword) throws Exception {
		try {
			KbUser userInfo = userMapper.selectUserByUserCode(userCode);// 根据员工编码查询员工信息
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
			log.error("登陆数据接口异常,异常原因【" + e.toString() + "】");
			return rtnErrorResult(Result.ERROR_6000, "登陆数据接口异常,请联系管理员");
		}
	}

}
