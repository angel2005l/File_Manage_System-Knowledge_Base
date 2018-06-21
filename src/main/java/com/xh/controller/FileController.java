package com.xh.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xh.base.BaseController;
import com.xh.base.Constant;
import com.xh.entity.KbFileTable;
import com.xh.service.IFileService;
import com.xh.uitl.DateUtil;
import com.xh.uitl.IOUtil;
import com.xh.uitl.Result;
import com.xh.uitl.StrUtil;

@Controller
@RequestMapping("/file")
public class FileController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(FileController.class);// 日志对象
	private static final String FILETABLETAG = Constant.FILETABLETAG;// 文件表标识表头
	private static final String TABELTAG = Constant.TABELTAG;// 数据库表头

	@Autowired
	@Qualifier("fileServiceImpl")
	private IFileService fs;

	/**
	 * 
	 * @Title: uploadFile
	 * @Description: 文件的上传功能
	 * @author 陈专懂
	 * @return void
	 * @date 2018年6月19日
	 * @version 1.0
	 */
	BaseController base = new BaseController();

	@RequestMapping("/upFile.do")
	@ResponseBody
	public Result<Object> uploadFile(HttpServletRequest req, HttpServletResponse resp,
			@RequestParam("fileName") MultipartFile file) {
		String fileAddress = IOUtil.uploadFile(file, "../upload", "");
		// System.err.println("controller:file地址:"+fileAddress);
		if (!fileAddress.equals("")) {
			return base.rtnSuccessResult(Result.SUCCESS_0_MSG, fileAddress);
		} else {
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
	public Result<Object> downloadFile(HttpServletRequest req, HttpServletResponse resp) {
		String fileName = req.getParameter("filename");
		// System.err.println("path:"+path);
		// System.err.println("fileName:"+fileName);
		HttpServletResponse re = IOUtil.downloadFileServlet("../upload", fileName, resp);
		if (re != null) {
			return base.rtnSuccessResult();
		} else {
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
	public void displayPDF(HttpServletResponse resp, HttpServletRequest req) {
		String path = req.getParameter("pathAddress");
		// System.err.println("controller:"+path);
		IOUtil.displayPDF(resp, req, path);
	}

	/**
	 * 
	 * @Title: insFileTable
	 * @Description: 新增文件表信息及文件表业务方法
	 * @author 黄官易
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @return Result<Object>
	 * @date 2018年6月21日
	 * @version 1.0
	 */
	@RequestMapping("/insft.do")
	@ResponseBody
	public Result<Object> insFileTable(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String ftName = request.getParameter("ft_name");
		String ftLevel = request.getParameter("ft_level");
		KbFileTable kft = new KbFileTable();
		kft.setFtCode(
				FILETABLETAG + DateUtil.curDateYMDHMSForService() + StrUtil.getRandom((int) (Math.random() * 1000), 3));
		kft.setFtName(TABELTAG + ftName);
		kft.setFileLevel(Integer.parseInt(ftLevel));
		// kft.setCreateUserCode(session.getAttribute("userCode").toString());
		kft.setCreateUserCode("kb_system");
		kft.setCreateTime(DateUtil.curDateYMDHMS());
		try {
			return fs.inseFileTable(kft);
		} catch (Exception e) {
			log.error("新增文件表信息及文件表接口异常,异常原因:【" + e.toString() + "】");
			return rtnErrorResult(Result.ERROR_6000, "新增文件表信息及文件表接口异常,请联系系统管理员");
		}
	}

}
