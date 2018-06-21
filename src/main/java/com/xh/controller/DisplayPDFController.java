/**
 * 
 */
package com.xh.controller;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xh.base.BaseController;
import com.xh.uitl.IOUtil;
import com.xh.uitl.Result;

/**
 * 操作文件的上传  下载   预览  功能
 * @date 2018年6月19日下午1:17:30
 * @version 1.0
 */
@Controller
@RequestMapping("/disFile")
public class DisplayPDFController extends BaseController{
	
	/**
	 * 
	 * @Title: uploadFile  
	 * @Description: TODO(这里用一句话描述这个方法的作用)  
	 * @author 陈专懂 
	 * @return void 
	 * @date 2018年6月19日  
	 * @version 1.0
	 */
	BaseController base=new BaseController();
	@RequestMapping("/upFile.do")
	@ResponseBody
	public Result<Object> uploadFile(HttpServletRequest req,HttpServletResponse resp,@RequestParam("fileName") MultipartFile file){
		String fileAddress=IOUtil.uploadFile(file, "upload", "");
//		System.err.println("controller:file地址:"+fileAddress);
		if(!fileAddress.equals("")){
			return base.rtnSuccessResult(Result.SUCCESS_0_MSG, fileAddress);
		}else{
			return base.rtnErrorResult(Result.ERROR_4000, Result.ERROR_4000_MSG);
		}
	}
	
	/**
	 * 
	 * @Title: downloadFile  
	 * @Description: TODO(文件下载的功能)  
	 * @author 陈专懂 
	 * @return Result<Object> 
	 * @date 2018年6月20日  
	 * @version 1.0
	 */
	@RequestMapping("/downloadFile.do")
	@ResponseBody
	public Result<Object> downloadFile(HttpServletRequest req,HttpServletResponse resp){
		String path=req.getParameter("path");
		String fileName=req.getParameter("filename");
//		System.err.println("path:"+path);
//		System.err.println("fileName:"+fileName);
		HttpServletResponse re=IOUtil.downloadFileTrue(path, fileName,resp);
		if(re!=null){
			return base.rtnSuccessResult();
		}else{
			return base.rtnErrorResult(Result.ERROR_4000, Result.ERROR_4000_MSG);
		}
	}
	
	/**
	 * 
	 * @Title: displayPDF  
	 * @Description: TODO(文件在线预览的功能)  
	 * @author 陈专懂 
	 * @return void 
	 * @date 2018年6月20日  
	 * @version 1.0
	 */
	@RequestMapping("/disPdf.do")
	@ResponseBody
	public void displayPDF(HttpServletResponse resp,HttpServletRequest req) {
        String path=req.getParameter("pathAddress"); 
        System.err.println("controller:"+path);
        req.setAttribute("Str", "/xh_bi_b_knowledge_base/disFile/disPdf.do&pathAddress=C:/Users/Administrator/Desktop/123.pdf");
		IOUtil.displayPDF(resp, req, "C:/Users/Administrator/Desktop/123.pdf");
    }
	
}
