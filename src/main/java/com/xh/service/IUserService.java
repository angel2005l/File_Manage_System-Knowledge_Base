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

	/**
	 * 
	 * @Title: insUser
	 * @Description: 添加员工信息
	 * @author 黄官易
	 * @param user
	 * @return
	 * @throws Exception
	 * @return Result<Object>
	 * @date 2018年6月27日
	 * @version 1.0
	 */
	public Result<Object> insUser(KbUser user) throws Exception;

	/**
	 * 
	 * @Title: batchInsUsers
	 * @Description: 批量添加员工信息
	 * @author 黄官易
	 * @param users
	 * @return
	 * @throws Exception
	 * @return Result<Object>
	 * @date 2018年6月27日
	 * @version 1.0
	 */
	public Result<Object> batchInsUsers(List<KbUser> users) throws Exception;

	/**
	 * 
	 * @Title: selUsersByUserDeptCode
	 * @Description: 根据部门编码查询全部的用户
	 * @author 黄官易
	 * @param userDeptCode
	 * @return
	 * @throws Exception
	 * @return Result<List<KbUser>>
	 * @date 2018年6月27日
	 * @version 1.0
	 */
	public Result<List<KbUser>> selUsersByUserDeptCode(String userDeptCode) throws Exception;

	/**
	 * 
	 * @Title: selSuperiorUserByUserDeptCode
	 * @Description: 根据部门编码查询上级部门的领导层用户
	 * @author 黄官易
	 * @param userDeptCode
	 * @return
	 * @throws Exception
	 * @return Result<List<KbUser>>
	 * @date 2018年6月27日
	 * @version 1.0
	 */
	public Result<List<KbUser>> selSuperiorUserByUserDeptCode(String userDeptCode) throws Exception;
}
