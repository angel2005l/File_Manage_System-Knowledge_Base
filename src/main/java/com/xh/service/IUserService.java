package com.xh.service;

import java.util.List;

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

	// 添加员工信息
	public Result<Object> insUser(KbUser user) throws Exception;

	// 批量添加员工信息
	public Result<Object> batchInsUsers(List<KbUser> users) throws Exception;

	// 根据部门编码查询全部的用户
	public Result<List<KbUser>> selUsersByUserDeptCode(String userDeptCode) throws Exception;

	// 根据部门编码查询上级部门的领导层用户
	public Result<List<KbUser>> selSuperiorUserByUserDeptCode(String userDeptCode) throws Exception;
}
