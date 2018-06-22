package com.xh.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.xh.entity.KbFile;
import com.xh.entity.KbFileTable;
import com.xh.entity.KbFileUser;
import com.xh.service.IFileService;
import com.xh.uitl.DateUtil;
import com.xh.uitl.IOUtil;
import com.xh.uitl.Result;
import com.xh.uitl.StrUtil;

@Controller
@RequestMapping("/file")
public class FileController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(FileController.class);// 日志对象
	private static final String FILETABLETAG = Constant.FILETABLETAG;// 文件表标识头
	private static final String TABELTAG = Constant.TABELTAG;// 数据库表标识头

	@Autowired
	@Qualifier("fileServiceImpl")
	private IFileService fs;

	/**
	 * 
	 * @Title: uploadFile
	 * @Description: 文件上传入口方法
	 * @author 黄官易
	 * @param req
	 * @param resp
	 * @param file
	 * @return
	 * @return Result<Object>
	 * @date 2018年6月22日
	 * @version 1.0
	 */

	@RequestMapping("/upFile.do")
	@ResponseBody
	public Result<Map<String, String>> uploadFile(HttpServletRequest request, HttpSession session,
			@RequestParam("file_data") MultipartFile mf) {
		try {
			// 上传文件并写入磁盘保存
			Result<Map<String, String>> ufResult = fs.uploadFile(mf);
			if (Result.SUCCESS_0 != ufResult.getCode()) {
				return ufResult;
			}
			// 上传并写入文件成功,将文件信息写入数据库
			String fileInfo = request.getParameter("file_info");
			String projectLevel = request.getParameter("project_level");
			String projectCode = request.getParameter("project_code");
			String userCode = session.getAttribute("userCode").toString();
			// String userCode = "820032";
			Map<String, String> fileMap = ufResult.getData();
			String fileCode = fileMap.get("fileCode");
			String fileName = fileMap.get("fileName");
			String fileType = fileMap.get("fileType");
			KbFile kf = new KbFile();
			// 文件对象
			kf.setFileCode(fileCode);
			kf.setFileName(fileName);
			kf.setFileInfo(fileInfo);
			kf.setFileType(fileType);
			kf.setFileStatus("record");
			kf.setFileLevel(Integer.parseInt(projectLevel));
			kf.setProjectCode(projectCode);
			kf.setCreateUserCode(userCode);
			kf.setCreateTime(DateUtil.curDateYMDHMS());

			// 文件关联关系对象
			List<KbFileUser> kfus = new ArrayList<KbFileUser>();
			// 下载具有预览的权限加深
			String[] fileShow = request.getParameterValues("file_show");
			// String[] fileShow = {};
			// String[] fileDownload = {"820032","820046","820033"};
			// 还差对上级所属部门的查询

			for (String showUserCode : fileShow) {
				KbFileUser kfu = new KbFileUser();
				kfu.setFileCode(fileCode);
				kfu.setFileName(fileName);
				kfu.setFileType(fileType);
				kfu.setUserCode(showUserCode);
				kfu.setFilePermission("onlyread");
				kfu.setCreateUserCode(userCode);
				kfu.setCreateTime(DateUtil.curDateYMDHMS());
				kfus.add(kfu);
			}
			String[] fileDownload = request.getParameterValues("file_download");
			for (String downloadUserCode : fileDownload) {
				KbFileUser kfu = new KbFileUser();
				kfu.setFileCode(fileCode);
				kfu.setFileName(fileName);
				kfu.setFileType(fileType);
				kfu.setUserCode(downloadUserCode);
				kfu.setFilePermission("download");
				kfu.setCreateUserCode(userCode);
				kfu.setCreateTime(DateUtil.curDateYMDHMS());
				KbFileUser cloneKfu = IOUtil.deepClone(kfu);
				cloneKfu.setFilePermission("onlyread");
				kfus.add(kfu);
				kfus.add(cloneKfu);
			}
			// 数据文件结构
			return fs.insFile(kf, projectLevel, kfus);
		} catch (Exception e) {
			log.error("文件上传服务异常,异常原因【" + e.toString() + "】");
			return rtnErrorResult(Result.ERROR_6000, "文件上传服务异常,请联系系统管理员");
		}
	}

	/**
	 * 
	 * @Title: downloadCheck
	 * @Description: 校验下载文件
	 * @author 黄官易
	 * @param request
	 * @return
	 * @return Result<String>
	 * @date 2018年6月22日
	 * @version 1.0
	 */
	public Result<String> downloadCheck(HttpServletRequest request) {
		String fileCode = request.getParameter("file_code");
		String fileLevel = request.getParameter("file_level");
		try {
			Result<KbFile> fileResult = fs.selFileByFileCode(Integer.parseInt(fileLevel), fileCode);
			if (Result.SUCCESS_0 != fileResult.getCode()) {
				return rtnFailResult(fileResult.getCode(), fileResult.getMsg());
			}
			KbFile data = fileResult.getData();
			return rtnSuccessResult("", data.getFileCode() + data.getFileType());
		} catch (Exception e) {
			log.error("文件下载异常,异常原因【" + e.toString() + "】");
			return rtnErrorResult(Result.ERROR_6000, "文件下载异常,请联系系统管理员");
		}
	}

	/**
	 * 
	 * @Title: downloadFile
	 * @Description: 下载文件
	 * @author 黄官易
	 * @param request
	 * @param resp
	 * @return
	 * @return Result<Object>
	 * @date 2018年6月22日
	 * @version 1.0
	 */
	@RequestMapping("/downloadFile.do")
	@ResponseBody
	public Object downloadFile(HttpServletRequest request) {
		String fileName = request.getParameter("file_Name");
		return IOUtil.downloadFile("../upload", fileName);
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
		IOUtil.displayPDF(resp, req, path);
	}

	/**
	 * 
	 * @Title: insFileTable
	 * @Description: 新增文件表信息及文件表入口方法
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
	public Result<Object> insFileTable(HttpServletRequest request, HttpSession session) {
		String ftName = request.getParameter("ft_name");
		String ftLevel = request.getParameter("ft_level");
		KbFileTable kft = new KbFileTable();
		try {
			// 编码规则 FT+yyyyMMddHHmmss+3位随机数
			kft.setFtCode(FILETABLETAG + DateUtil.curDateYMDHMSForService()
					+ StrUtil.getRandom((int) (Math.random() * 1000), 3));
			kft.setFtName(TABELTAG + ftName);
			kft.setFileLevel(Integer.parseInt(ftLevel));
			kft.setCreateUserCode(session.getAttribute("userCode") == null ? "kb_system"
					: session.getAttribute("userCode").toString());
			kft.setCreateTime(DateUtil.curDateYMDHMS());
			return fs.insFileTable(kft);
		} catch (NumberFormatException e) {
			return rtnFailResult(Result.ERROR_4000, "文件层级参数不合法,文件层级参数必须为一个自然数（0或正整数）");
		} catch (Exception e) {
			log.error("新增文件表信息及文件表接口异常,异常原因:【" + e.toString() + "】");
			return rtnErrorResult(Result.ERROR_6000, "新增文件表信息及文件表接口异常,请联系系统管理员");
		}
	}

}
