package com.xh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

	/**
	 * 
	 * @Title: login
	 * @Description: 员工登录
	 * @author 黄官易
	 * @param
	 * @return void
	 * @date 2018年6月19日
	 * @version 1.0
	 */
	@RequestMapping("/login.do")
	@ResponseBody
	public void login() {
		
		
		
	}

}
