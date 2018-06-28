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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xh.base.BaseController;
import com.xh.base.Constant;
import com.xh.entity.KbFile;
import com.xh.entity.KbFileTable;
import com.xh.entity.KbFileUser;
import com.xh.entity.KbProject;
import com.xh.entity.KbUser;
import com.xh.service.IFileService;
import com.xh.service.IProjectService;
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
	private IProjectService ps;
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
	@Transactional(rollbackFor = { Exception.class })
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
			// String projectLevel = request.getParameter("project_level");
			// String projectCode = request.getParameter("project_code");
			String projectLevel = "0";
			String projectCode = "P201806221307125412";
			// String userCode = session.getAttribute("user_code").toString();
			// String userDeptCode = session.getAttribute("userDeptCode").toString();
			String userDeptCode = "D201806230935390372";
			String userCode = "820032";
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
			if (null != fileShow && fileShow.length > 0) {
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
			}
			String[] fileDownload = request.getParameterValues("file_download");
			if (null != fileDownload && fileDownload.length > 0) {
				for (String downloadUserCode : fileDownload) {
					KbFileUser kfu = new KbFileUser();
					kfu.setFileCode(fileCode);
					kfu.setFileName(fileName);
					kfu.setFileType(fileType);
					kfu.setUserCode(downloadUserCode);
					kfu.setFilePermission("download");
					kfu.setCreateUserCode(userCode);
					kfu.setCreateTime(DateUtil.curDateYMDHMS());
					kfus.add(kfu);
				}
			}
			Result<Object> insResult = fs.insFile(kf, projectLevel, kfus);
			Result<Object> insSupResult = fs.insSuperiorUserFileWithOnlyRead(kf, userDeptCode);
			if (Result.SUCCESS_0 == insResult.getCode() && Result.SUCCESS_0 == insSupResult.getCode()) {
				return rtnSuccessResult("文件保存成功");
			} else {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();// 手动回滚
				return rtnFailResult(Result.ERROR_4000, "文件保存失败");
			}
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();// 手动回滚
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
	 * @Description: 文件查询
	 * @author 黄官易
	 * @param request
	 * @return
	 * @return Result<List<Map<String,Object>>>
	 * @date 2018年6月25日
	 * @version 1.0
	 */
	@RequestMapping("/pfd.do")
	public String selectFileForDetail(HttpServletRequest request, HttpSession session) {
		
		String projectName = request.getParameter("project_name");
		String projectCode = request.getParameter("project_code");
		String projectLevel = request.getParameter("project_level");
		System.err.println("projectCode:"+projectCode+"----projectLevel:"+projectLevel);
		String userCode = "820032";
		try {
			String obj=ps.selectProjectTableNameByProjectLevel(Integer.parseInt(projectLevel)).getData().toString();//获得表名
			List<KbProject> kpro=ps.selectAllProByUser(obj,projectCode,userCode).getData();
			System.err.println("list:"+kpro);
			double proCount=0;//项目进行中的数量
			double completed=0;//项目已完成的数量
			for (KbProject kb : kpro) {
				if(kb.getProjectStatus().equals("progress")){
					proCount++;
				} else {
					completed++;
				}
			}
			int sum=(int) (proCount+completed);//该项目下所有的项目
			String ratio=(int)completed+"/"+sum;//已完成项目/项目总数
			int per=(int) ((completed/sum)*100);//已完成项目所占百分比
			System.err.println("ratio:"+ratio+";per:"+per);

			Result<List<Map<String, Object>>> fileResult = fs.selectFile(Integer.parseInt(projectLevel), userCode,
					projectCode);
			System.err.println(fileResult);
			request.setAttribute("files", fileResult.getData());
			request.setAttribute("projects", kpro);
			request.setAttribute("ratio", ratio);
			request.setAttribute("per", per);
			request.setAttribute("projectCode", projectCode);
			request.setAttribute("projectLevel", projectLevel);
			request.setAttribute("projectName", projectName);
		} catch (NumberFormatException e) {
			log.error("非法登录,非法ip：" + IpUtil.getIp(request));
			return "view/index";
		} catch (Exception e) {
			log.error("文件查询异常,异常原因:【" + e.toString() + "】");
			return "view/error";
		}
		return "view/project_detail";
	}
	
	/**
	 * 
	 * @Title: backFileForDetail  
	 * @Description: 详情单的返回功能（可以和上面的进入下一层方法合并，后续）
	 * @author 陈专懂 
	 * @return String 
	 * @date 2018年6月28日  
	 * @version 1.0
	 */
	@RequestMapping("/back.do")
	public String backFileForDetail(HttpServletRequest request, HttpSession session,HttpServletResponse response) {
		String projectCode = request.getParameter("project_code");
		int projectLevel = Integer.parseInt(request.getParameter("project_level"));
		String userCode = "820032";
		try {
			List<KbProject> kpro=ps.selectSuperiorAllPro(userCode, projectCode, projectLevel);
			int level=0;
			if(projectLevel==0){
//				System.err.println("123");
//				response.sendRedirect("pro/AllProInMain.do");
				level=0;
			}else{
				level=projectLevel-1;
			}
			List<KbProject> TOPkpro=ps.selectSuperiorAllPro(userCode, projectCode, level);
			System.err.println("1:"+TOPkpro.get(0).getProjectCode()+",,,,,:"+TOPkpro.get(0).getProjectName());
			System.err.println("list:"+kpro);
			double proCount=0;//项目进行中的数量
			double completed=0;//项目已完成的数量
			for (KbProject kb : kpro) {
				if(kb.getProjectStatus().equals("progress")){
					proCount++;
				} else {
					completed++;
				}
			}
			int sum=(int) (proCount+completed);//该项目下所有的项目
			String ratio=(int)completed+"/"+sum;//已完成项目/项目总数
			int per=(int) ((completed/sum)*100);//已完成项目所占百分比
			System.err.println("ratio:"+ratio+";per:"+per);

			Result<List<Map<String, Object>>> fileResult = fs.selectFile(TOPkpro.get(0).getProjectLevel(), userCode,
					TOPkpro.get(0).getProjectCode());
			System.err.println(fileResult);
			request.setAttribute("files", fileResult.getData());
			request.setAttribute("projects", kpro);
			request.setAttribute("ratio", ratio);
			request.setAttribute("per", per);
			request.setAttribute("projectCode", TOPkpro.get(0).getProjectCode());
			request.setAttribute("projectLevel", TOPkpro.get(0).getProjectLevel());
			request.setAttribute("projectName", TOPkpro.get(0).getProjectName());
		} catch (NumberFormatException e) {
			log.error("非法登录,非法ip：" + IpUtil.getIp(request));
			return "view/index";
		} catch (Exception e) {
			log.error("文件查询异常,异常原因:【" + e.toString() + "】");
			return "view/error";
		}
		return "view/project_detail";
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
			} else {
				return "view/not_share";
			}
		} catch (NumberFormatException e) {
			log.error("非法登录,非法ip：" + IpUtil.getIp(request));
			return "view/not_share";
		} catch (Exception e) {
			log.error("文件查询异常,异常原因:【" + e.toString() + "】");
			return "view/not_share";
		}
		return "view/share_file";
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
			// 获得部门信息
			// String userDeptCode = session.getAttribute("user_dept_code").toString();
			String userDeptCode = "D201806230935390372";
			// 获得父类编码
			String projectParentCode = request.getParameter("project_code");
			// 获得父类等级
			String projectParentLevel = StrUtil.isBlank(request.getParameter("project_level")) ? "0"
					: request.getParameter("project_level");
			System.err.println("projectParentCode:"+projectParentCode+"----projectParentLevel:"+projectParentLevel);
			Result<List<KbUser>> userResult = us.selUsersByUserDeptCode(userDeptCode); // 获得员工信息
			request.setAttribute("userList", userResult.getData());
			request.setAttribute("projectParentCode", projectParentCode);
			request.setAttribute("projectLevel", Integer.parseInt(projectParentLevel) + 1);
		} catch (NumberFormatException | NullPointerException e) {
			log.error("非法登录,登录IP：" + IpUtil.getIp(request));
			return "view/login";
		} catch (Exception e) {
			log.error("跳转项目添加页面异常,异常原因:【" + e.toString() + "】");
		}
		return "view/insert_file";
	}
}
