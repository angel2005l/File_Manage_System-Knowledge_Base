package com.xh.service;

import com.xh.entity.KbUser;
import com.xh.uitl.Result;

public interface IUserService {
	/**
	 * 
	 * @Title: login
	 * @Description: 员工登录业务方法
	 * @author 黄官易
	 * @param userCode
	 * @param userPassword
	 * @return
	 * @throws Exception
	 * @return Result<KbUser>
	 * @date 2018年6月19日
	 * @version 1.0
	 */
	public Result<KbUser> login(String userCode, String userPassword) throws Exception;
}
