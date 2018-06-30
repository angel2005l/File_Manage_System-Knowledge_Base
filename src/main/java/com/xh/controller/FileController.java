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
//			String projectLevel = "0";
//			String projectCode = "P201806221307125412";
			 String userCode = session.getAttribute("user_code").toString();
			 String userDeptCode = session.getAttribute("user_dept_code").toString();
//			String userDeptCode = "D201806230935390372";
//			String userCode = "820046";
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
			kf.setFileLevel(Integer.parseInt(projectLevel)-1);
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
		String userCode = session.getAttribute("user_code").toString();// 用户编码
		try {
			String obj=ps.selectProjectTableNameByProjectLevel(Integer.parseInt(projectLevel)).getData().toString();//获得表名
			List<KbProject> kpro=ps.selectAllProByUser(obj,projectCode,userCode).getData();
			int proCount=0;//项目进行中的数量
			int completed=0;//项目已完成的数量
			if(null!=kpro&&!kpro.isEmpty()){
				for (KbProject kb : kpro) {
					if(kb.getProjectStatus().equals("progress")){
						proCount++;
					} else {
						completed++;
					}
				}
			}
			int sum=proCount+completed;//该项目下所有的项目
			String ratio=completed+"/"+sum;//已完成项目/项目总数
			int per=(completed*100)/(sum*100);//已完成项目所占百分比
			Result<List<Map<String, Object>>> fileResult = fs.selectFile(Integer.parseInt(projectLevel), userCode,
					projectCode);
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
		String userCode = session.getAttribute("user_code").toString();// 用户编码
		double proCount=0;//项目进行中的数量
		double completed=0;//项目已完成的数量
		String ratio = null;//已完成项目/项目总数
		int per = 0;//已完成项目所占百分比
		List<KbProject> kpro = new ArrayList<KbProject>();//实例化一个对象
		try {
			Map<String,Object> map = ps.selectSuperiorAllPro(userCode, projectCode, projectLevel);
			Result<List<Map<String, Object>>> fileResult = fs.selectFile(projectLevel-1, userCode,
					map.get("code").toString());
			if(null!=map||!map.isEmpty()){
				kpro=(List<KbProject>) map.get("list");
				proCount=0;//项目进行中的数量
				completed=0;//项目已完成的数量
				for (KbProject kb : kpro) {
					if(kb.getProjectStatus().equals("progress")){
						proCount++;
					} else {
						completed++;
					}
				}
			}
			int sum=(int) (proCount+completed);//该项目下所有的项目
			if(sum!=0){
				ratio=(int)completed+"/"+sum;//已完成项目/项目总数
				per=(int) ((completed/sum)*100);//已完成项目所占百分比
			}
			request.setAttribute("files", fileResult.getData());
			request.setAttribute("projects", kpro);
			request.setAttribute("ratio", ratio);
			request.setAttribute("per", per);
			request.setAttribute("projectCode", map.get("code").toString());
			request.setAttribute("projectLevel", map.get("parProjectLevel").toString());
			request.setAttribute("projectName", map.get("parProjectName").toString());
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
			String userDeptCode = session.getAttribute("user_dept_code").toString();// 获得部门信息
			// String userDeptCode = "D201806230935390372";// 获得部门信息
			String projectParentCode = request.getParameter("project_code");// 获得父类编码
			// String projectName = request.getParameter("project_name"); // 获得父类名称
			String projectParentLevel = StrUtil.isBlank(request.getParameter("project_level")) ? "0"
					: request.getParameter("project_level"); // 获得父类等级
			Result<List<KbUser>> userResult = us.selUsersByUserDeptCode(userDeptCode); // 获得员工信息
			request.setAttribute("userList", userResult.getData());
			request.setAttribute("projectLevel", projectParentLevel);
			request.setAttribute("projectCode", projectParentCode);
		} catch (NumberFormatException | NullPointerException e) {
			log.error("非法登录,登录IP：" + IpUtil.getIp(request));
			return "view/login";
		} catch (Exception e) {
			log.error("跳转项目添加页面异常,异常原因:【" + e.toString() + "】");
		}
		return "view/insert_file";
	}
}
