package com.xh.dao;

import org.springframework.stereotype.Repository;

import com.xh.entity.User;

@Repository
public interface IUserDao {
	User selectUserForLogin(String userCode);
}