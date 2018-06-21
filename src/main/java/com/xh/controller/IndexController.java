package com.xh.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import com.xh.uitl.AsposeUtil;
import com.xh.uitl.IOUtil;

@Controller
@RequestMapping("/index")
public class IndexController {

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
}
