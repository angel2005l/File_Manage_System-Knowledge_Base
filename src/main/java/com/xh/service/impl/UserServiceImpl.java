package com.xh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xh.dao.KbUserMapper;
import com.xh.entity.KbUser;
import com.xh.service.IUserService;
import com.xh.uitl.Result;

@Service("userServiceImpl")
public class UserServiceImpl implements IUserService {

	@Autowired
	KbUserMapper userMapper;

	@Override
	public Result<KbUser> login(String userCode) throws Exception {

		return null;
	}

}
