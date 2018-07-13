package com.xh.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import com.alibaba.fastjson.JSONObject;
import com.xh.base.BaseController;
import com.xh.entity.KbUserAdvice;
import com.xh.service.IUserAdviceService;
import com.xh.uitl.AsposeUtil;
import com.xh.uitl.IOUtil;

@Controller
@RequestMapping("/index")
public class IndexController extends BaseController{
	private static final Logger log = LoggerFactory.getLogger(FileController.class);// 日志对象
	
	@Autowired
	@Qualifier("userAdviceServiceImpl")
	private IUserAdviceService ads;
	
	@RequestMapping("/getAllMsg.do")
	@ResponseBody
	public JSONObject getAllAdvice(HttpServletRequest request,HttpSession session,HttpServletResponse response){
			String userCode = session.getAttribute("user_code").toString();// 用户编码
			List<KbUserAdvice> adList=ads.getAdviceMsgByUser(userCode);
			JSONObject obj=new JSONObject();
			obj.put("adNum",adList.size());
			obj.put("adviceMsg",adList);
			return obj;
	}

}
