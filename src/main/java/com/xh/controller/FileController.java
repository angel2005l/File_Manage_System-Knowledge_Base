package com.xh.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xh.aop.SystemControllerLog;
import com.xh.base.BaseController;
import com.xh.base.Constant;
import com.xh.entity.KbBatchShare;
import com.xh.entity.KbFile;
import com.xh.entity.KbFileTable;
import com.xh.entity.KbFileUser;
import com.xh.entity.KbUser;
import com.xh.service.IFileService;
import com.xh.service.IUserService;
import com.xh.uitl.DateUtil;
import com.xh.uitl.IOUtil;
import com.xh.uitl.IpUtil;
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

	@Autowired
	@Qualifier("userServiceImpl")
	private IUserService us;

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
	public Result<Object> uploadFile(HttpServletRequest request, HttpSession session,
			@RequestParam("file_data") MultipartFile mf) {
		try {
			// 上传文件并写入磁盘保存
			Result<Map<String, String>> ufResult = fs.uploadFile(mf);
			if (Result.SUCCESS_0 != ufResult.getCode()) {
				return rtnFailResult(ufResult.getCode(), ufResult.getMsg());
			}
			// 上传并写入文件成功,将文件信息写入数据库
			String fileInfo = request.getParameter("file_info");
			String projectLevel = request.getParameter("project_level");
			String projectCode = request.getParameter("project_code");
			String userCode = session.getAttribute("user_code").toString();
			String userDeptCode = session.getAttribute("user_dept_code").toString();
			Map<String, String> fileMap = ufResult.getData();// 文件写入后的信息
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
			// 关联预览权限
			String[] fileShow = request.getParameterValues("file_show");
			if (null != fileShow && fileShow.length > 0) {
				for (String showUser : fileShow) {
					String[] showUserInfo = showUser.split(",");
					KbFileUser kfu = new KbFileUser();
					kfu.setFileCode(fileCode);
					kfu.setFileName(fileName);
					kfu.setFileType(fileType);
					kfu.setUserCode(showUserInfo[0]);
					kfu.setUserName(showUserInfo[1]);
					kfu.setFilePermission("onlyread");
					kfu.setCreateUserCode(userCode);
					kfu.setUserDeptCode(userDeptCode);
					kfu.setCreateTime(DateUtil.curDateYMDHMS());
					kfus.add(kfu);
				}
			}
			// 关联下载权限
			String[] fileDownload = request.getParameterValues("file_download");
			if (null != fileDownload && fileDownload.length > 0) {
				for (String downloadUser : fileDownload) {
					String[] downloadUserInfo = downloadUser.split(",");
					KbFileUser kfu = new KbFileUser();
					kfu.setFileCode(fileCode);
					kfu.setFileName(fileName);
					kfu.setFileType(fileType);
					kfu.setUserCode(downloadUserInfo[0]);
					kfu.setUserName(downloadUserInfo[1]);
					kfu.setFilePermission("download");
					kfu.setCreateUserCode(userCode);
					kfu.setUserDeptCode(userDeptCode);
					kfu.setCreateTime(DateUtil.curDateYMDHMS());
					kfus.add(kfu);
				}
			}
			return fs.insFile(kf, projectLevel, kfus);
		} catch (NumberFormatException e) {
			log.error("非法登录,非法ip：" + IpUtil.getIp(request));
			return rtnErrorResult(Result.ERROR_6000, "非法登录!");
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
	@RequestMapping("/downloadCheck.do")
	@ResponseBody
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
			log.error("校验下载异常,异常原因【" + e.toString() + "】");
			return rtnErrorResult(Result.ERROR_6000, "校验下载异常,请联系系统管理员");
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
	public ResponseEntity<byte[]> downloadFile(HttpServletRequest request) {
		String fileName = request.getParameter("file_name");
		return IOUtil.downloadFile("../upload", fileName);
	}

	/**
	 * 
	 * @Title: displayPDF
	 * @Description: 文件在线预览的功能
	 * @author 黄官易
	 * @param request
	 * @return
	 * @return ResponseEntity<byte[]>
	 * @date 2018年6月23日
	 * @version 1.0
	 */
	@RequestMapping("/disPdf.do")
	@ResponseBody
	@SystemControllerLog(description = "在线预览")
	public ResponseEntity<byte[]> displayPDF(HttpServletRequest request) {
		String fileInfo = request.getParameter("file_info");
		String[] fileInfos = fileInfo.split(",");
		try {
			ResponseEntity<byte[]> downloadPdf = fs.downloadPdf("../upload", fileInfos[0], fileInfos[1]);
			return downloadPdf;
		} catch (Exception e) {
			log.error("文件在线预览异常,异常原因【" + e.toString() + "】");
			return null;
		}
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

	/**
	 * 
	 * @Title: selectFileForDetail
	 * @Description: 详细页展示
	 * @author 黄官易
	 * @param request
	 * @return
	 * @return Result<List<Map<String,Object>>>
	 * @date 2018年6月25日
	 * @version 1.0
	 */
	@RequestMapping("/pfd.do")
	public String projectFileDetail(HttpServletRequest request, HttpSession session) {
		try {
			String projectCode = request.getParameter("project_code");
			String projectLevel = request.getParameter("project_level");
			String userCode = session.getAttribute("user_code").toString();// 用户编码
			String rootCode = StrUtil.isBlank(request.getParameter("root_code")) ? projectCode
					: request.getParameter("root_code");// 根路径
			Map<String, String> fileSelMap = new HashMap<String, String>();
			String fileName = request.getParameter("form_file_name");
			String startDate = request.getParameter("form_start_date");
			String endDate = request.getParameter("form_end_date");
			fileSelMap.put("fileName", fileName);
			fileSelMap.put("startDate", startDate);
			fileSelMap.put("endDate", endDate);
			Result<Map<String, Object>> result = fs.getProjectDetailData(projectCode, Integer.parseInt(projectLevel),
					userCode, fileSelMap);
			if (Result.SUCCESS_0 == result.getCode()) {
				Map<String, Object> ResultMap = result.getData();
				request.setAttribute("files", ResultMap.get("files"));
				request.setAttribute("projectSonInfos", ResultMap.get("projectSonInfos"));
				request.setAttribute("ratio", ResultMap.get("ratio"));
				request.setAttribute("per", ResultMap.get("per"));
				request.setAttribute("projectInfo", ResultMap.get("projectInfo"));
				request.setAttribute("rootCode", rootCode);
				return "view/project_detail";
			}
				request.setAttribute("error", result);
		} catch (NullPointerException | NumberFormatException e) {
			log.error("非法登录,非法ip：" + IpUtil.getIp(request));
			return "view/login";
		} catch (Exception e) {
			log.error("文件查询异常,异常原因:【" + e.toString() + "】");
			request.setAttribute("error", rtnErrorResult(Result.ERROR_6000, "系统异常,请联系系统管理员"));
		}
		return "view/error";
	}

	/**
	 * 
	 * @Title: projectFileDetailBack
	 * @Description: 详细页展示(返回上一页)
	 * @author 黄官易
	 * @param request
	 * @param session
	 * @return
	 * @return String
	 * @date 2018年6月30日
	 * @version 1.0
	 */
	@RequestMapping("/pfdb.do")
	public String projectFileDetailBack(HttpServletRequest request, HttpSession session) {
		try {
			String projectCode = request.getParameter("project_code");
			String projectLevel = request.getParameter("project_level");
			String userCode = session.getAttribute("user_code").toString();// 用户编码
			String rootCode = StrUtil.isBlank(request.getParameter("root_code")) ? ""
					: request.getParameter("root_code");// 根路径
			projectCode = fs.selectSuperiorProjectCodeByProjectCode(Integer.parseInt(projectLevel), projectCode);//赋值 父类项目编码
			if (StrUtil.notBlank(projectCode)) {
				Result<Map<String, Object>> result = fs.getProjectDetailData(projectCode,
						Integer.parseInt(projectLevel) - 1, userCode, null);
				if (Result.SUCCESS_0 == result.getCode()) {
					Map<String, Object> ResultMap = result.getData();
					request.setAttribute("files", ResultMap.get("files"));
					request.setAttribute("projectSonInfos", ResultMap.get("projectSonInfos"));
					request.setAttribute("ratio", ResultMap.get("ratio"));
					request.setAttribute("per", ResultMap.get("per"));
					request.setAttribute("projectInfo", ResultMap.get("projectInfo"));
					request.setAttribute("rootCode", rootCode);
					return "view/project_detail";
				}
				request.setAttribute("error", result);
			}else {
				request.setAttribute("error", rtnErrorResult(Result.ERROR_6000, "项目不存在,请联系系统管理员"));
			}
		} catch (NullPointerException | NumberFormatException e) {
			log.error("非法登录,非法ip：" + IpUtil.getIp(request));
			return "view/login";
		} catch (Exception e) {
			log.error("文件查询异常,异常原因:【" + e.toString() + "】");
			request.setAttribute("error", rtnErrorResult(Result.ERROR_6000, "系统异常,请联系系统管理员"));
		}
		return "view/error";
	}

	/**
	 * 
	 * @Title: shareFile
	 * @Description: 文件分享
	 * @author 黄官易
	 * @param request
	 * @return void
	 * @date 2018年6月27日
	 * @version 1.0
	 */
	@RequestMapping("/sf.do")
	public String shareFile(HttpServletRequest request) {
		String fileCode = request.getParameter("file_code");// 文件编码
		String fileLevel = request.getParameter("file_level"); // 文件等级
		String projectCode = request.getParameter("project_code");// 项目编码
		try {
			// 获得项目名称与分享文件
			Map<String, Object> shareMap = fs.getShareFile(fileCode, Integer.parseInt(fileLevel), projectCode);
			if (!shareMap.isEmpty()) {
				request.setAttribute("shareProject", shareMap.get("shareProject"));
				request.setAttribute("shareFile", shareMap.get("shareFile"));
				return "view/share_file";
			} else {
				request.setAttribute("error", rtnErrorResult(Result.SUCCESS_0, "糟糕，分享文件不存在"));
			}
		} catch (NumberFormatException e) {
			log.error("非法登录,非法ip：" + IpUtil.getIp(request));
			request.setAttribute("error", rtnErrorResult(Result.ERROR_4300, "分享链接不合法"));
			return "view/error";
		} catch (Exception e) {
			log.error("文件查询异常,异常原因:【" + e.toString() + "】");
			request.setAttribute("error", rtnErrorResult(Result.ERROR_6000, "系统异常,请联系系统管理员"));
		}
		return "view/error";
	}

	/**
	 * 
	 * @Title: toInsertFile
	 * @Description: 跳转文件上传页面
	 * @author 黄官易
	 * @param request
	 * @param session
	 * @return
	 * @return String
	 * @date 2018年6月27日
	 * @version 1.0
	 */
	@RequestMapping("/insFileJsp.do")
	public String toInsertFile(HttpServletRequest request, HttpSession session) {
		try {
			String userDeptCode = session.getAttribute("user_dept_code").toString();// 获得部门信息
			String projectCode = request.getParameter("project_code");// 获得父类编码
			String projectLevel = StrUtil.isBlank(request.getParameter("project_level")) ? "0"
					: request.getParameter("project_level"); // 获得父类等级
			Result<List<KbUser>> userResult = us.selUsersByUserDeptCode(userDeptCode); // 获得员工信息
			request.setAttribute("userList", userResult.getData());
			request.setAttribute("projectLevel", projectLevel);
			request.setAttribute("projectCode", projectCode);
		} catch (NumberFormatException | NullPointerException e) {
			log.error("非法登录,登录IP：" + IpUtil.getIp(request));
			return "view/login";
		} catch (Exception e) {
			log.error("跳转项目添加页面异常,异常原因:【" + e.toString() + "】");
		}
		return "view/insert_file";
	}

	// 批量分享生成链接
	// 并且返回分享码
	@RequestMapping("/insShareFiles.do")
	@ResponseBody
	public Result<String> getBatchShare(HttpServletRequest request, HttpSession session) {
		// 获得项目编号
		String projectCode = request.getParameter("project_code");
		// 获得项目层级
		String projectLevel = request.getParameter("project_level");
		// 获得文件S编号
		String fileCodes = request.getParameter("file_codes");
		try {
			KbBatchShare kbs = new KbBatchShare();
			kbs.setShareCode(
					"SHA" + DateUtil.curDateYMDHMSForService() + StrUtil.getRandom((int) (Math.random() * 100), 2));
			kbs.setProjectCode(projectCode);
			kbs.setFileCodes(fileCodes);
			kbs.setProjectLevel(Integer.parseInt(projectLevel));
			kbs.setShareDate(DateUtil.curDateYMD());
			kbs.setCreateTime(DateUtil.curDateYMDHMS());
			kbs.setCreateUserCode(session.getAttribute("user_code").toString());
			return fs.insBatchProject(kbs);
		} catch (NullPointerException e) {
			log.error("非法登录,登录IP：" + IpUtil.getIp(request));
			return rtnErrorResult(Result.ERROR_6000, "登录信息已失效,请重新登录");
		} catch (Exception e) {
			log.error("新增文件批量分享信息异常,异常原因:【" + e.toString() + "】");
			return rtnErrorResult(Result.ERROR_6000, "服务器异常,请联系系统管理员");
		}
	}
	// 根据分享编码查询分享信息
	@RequestMapping("/shareFiles.do")
	public String selectShareFilesData(HttpServletRequest request) {
		String shareCode = request.getParameter("share_code");
		try {
			Result<Map<String, Object>> resultData = fs.selectShareFilesData(shareCode);
			if(Result.SUCCESS_0 == resultData.getCode()) {
				Map<String, Object> dataMap = resultData.getData();
				request.setAttribute("shareProject", dataMap.get("project"));
				request.setAttribute("shareFiles", dataMap.get("files"));
				return "view/share_files";
			}else {
				request.setAttribute("error", resultData);
				return "view/error";
			}
		} catch (Exception e) {
			log.error("文件批量分享异常,异常原因:【"+e.toString()+"】");
			request.setAttribute("error", rtnErrorResult(Result.ERROR_6000, "系统异常,请联系系统管理员"));
			return "view/error";
		}
	}

}
