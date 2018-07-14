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
import com.xh.uitl.Result;

@Controller
@RequestMapping("/index")
public class IndexController extends BaseController{
	private static final Logger log = LoggerFactory.getLogger(FileController.class);// 日志对象
	
	@Autowired
	@Qualifier("userAdviceServiceImpl")
	private IUserAdviceService ads;
	/**
	 * 
	 * @Title: office2Pdf  
	 * @Description: 转换pdfdemo 
	 * @author 黄官易
	 * @param request
	 * @param response
	 * @return    
	 * @return ResponseEntity<byte[]> 
	 * @date 2018年6月21日  
	 * @version 1.0
	 */
	@RequestMapping("/pdf2.do")
	@ResponseBody
	public ResponseEntity<byte[]> office2Pdf(HttpServletRequest request, HttpServletResponse response) {
		String realPath = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
		System.err.println(realPath);
		String word2pdfStr = AsposeUtil.word2PDFStr(realPath + "..//upload//123xin是.docx");
		System.err.println(word2pdfStr);
		String substring = word2pdfStr.substring(word2pdfStr.lastIndexOf("/")+1, word2pdfStr.length());
		System.err.println(substring);
		ResponseEntity<byte[]> downloadFile = IOUtil.downloadFile("pdf", substring);
		return downloadFile;
	}
	
	@RequestMapping("/getAllMsg.do")
	@ResponseBody
	public Result<JSONObject> getAllAdvice(HttpServletRequest request,HttpSession session,HttpServletResponse response){
			String userCode = session.getAttribute("user_code").toString();// 用户编码
			try {
				List<KbUserAdvice> adList = ads.getAdviceMsgByUser(userCode).getData();
				JSONObject obj=new JSONObject();
				obj.put("adNum",adList.size());
				obj.put("adviceMsg",adList);
				return rtnSuccessResult("通知数据查询成功", obj);
			} catch (Exception e) {
				log.error("通知查询方法业务方法异常，异常原因【"+e.toString()+"】");
				return rtnErrorResult(Result.ERROR_6000, "查询通知异常,请联系管理员");
			}

	}

}
