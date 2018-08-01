package com.xh.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xh.aop.SystemControllerLog;
import com.xh.base.BaseController;
import com.xh.base.Constant;
import com.xh.entity.KbProject;
import com.xh.entity.KbProjectTable;
import com.xh.entity.KbProjectUser;
import com.xh.entity.KbUser;
import com.xh.service.IDeptService;
import com.xh.service.IProjectService;
import com.xh.service.IUserAdviceService;
import com.xh.service.IUserService;
import com.xh.uitl.DateUtil;
import com.xh.uitl.IpUtil;
import com.xh.uitl.Result;
import com.xh.uitl.StrUtil;

@Controller
@RequestMapping("/pro")
public class ProjectController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(FileController.class);// 日志对象
	private static final String PROJECTTAG = Constant.PROJECTTAG;// 项目标识头
	private static final String PROJECTTABLETAG = Constant.PROJECTTABLETAG;// 项目表标识表头
	private static final String TABELTAG = Constant.TABELTAG;// 数据库表头

	@Autowired
	@Qualifier("projectServiceImpl")
	private IProjectService ps;
	@Autowired
	@Qualifier("userServiceImpl")
	private IUserService us;// 用户服务层
	@Autowired
	@Qualifier("userAdviceServiceImpl")
	private IUserAdviceService ads;
	@Autowired
	@Qualifier("deptServiceImpl")
	private IDeptService ds;

	/**
	 * 
	 * @Title: insertProjectTable
	 * @Description: 动态创建项目信息表的信息
	 * @author 陈专懂
	 * @return Result<Object>
	 * @date 2018年6月26日
	 * @version 1.0
	 */
	@RequestMapping("/inpt.do")
	@ResponseBody
	public Result<Object> insertProjectTable(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		try {
			String ptName = request.getParameter("pt_name");
			String ptLevel = request.getParameter("pt_level");
			KbProjectTable kpt = new KbProjectTable();
			kpt.setPtCode(PROJECTTABLETAG + DateUtil.curDateYMDHMSForService()
					+ StrUtil.getRandom((int) (Math.random() * 1000), 3));
			kpt.setPtName(TABELTAG + ptName);
			kpt.setProjectLevel(Integer.parseInt(ptLevel));
			kpt.setCreateUserCode(session.getAttribute("userCode").toString());
			kpt.setCreateTime(DateUtil.curDateYMDHMS());
			return ps.createProjectList(kpt);
		} catch (Exception e) {
			log.error("新增项目表信息及项目表接口异常,异常原因:【" + e.toString() + "】");
			return rtnErrorResult(Result.ERROR_6000, "新增项目表信息及项目表接口异常,请联系系统管理员");
		}
	}

	/**
	 * 
	 * @Title: addProject
	 * @Description: 新增项目信息及项目员工信息，项目的直属领导关联直属于主项目创建的人员的直属领导。
	 * @author 黄官易
	 * @param request
	 * @param session
	 * @return
	 * @return Result<Object>
	 * @date 2018年7月10日
	 * @version 1.0
	 */
	@RequestMapping("/insPro.do")
	@ResponseBody
	@SystemControllerLog(description = "创建项目", logType = "insertProject", isAdvice = "true")
	public Result<Object> addProject(HttpServletRequest request, HttpSession session) {
		try {
			// 项目信息主体
			String projectName = request.getParameter("project_name");// 项目名称
			// String projectType = request.getParameter("project_type");//项目类型
			String projectInfo = request.getParameter("project_info");// 项目信息
			// String projectRemark = request.getParameter("project_remark");
			String projectParentCode = request.getParameter("project_parent_code");// 项目父类编码
			String projectParentLevel = request.getParameter("project_level");// 项目父类级别
			int projectLevel = Integer.parseInt(projectParentLevel) + 1;// 当前项目级别
			String userCode = session.getAttribute("user_code").toString();// 创建人编码
			String createUserDeptCode = "";// 创建人所属部门编码
			// 为了获取projectCode，自己存入request
			String projectCode = PROJECTTAG + DateUtil.curDateYMDHMSForService()
					+ StrUtil.getRandom((int) (Math.random() * 10000), 4);// 项目编码
			String[] projectMainInfos = null;
			if (projectLevel > 0) {
				// 当项目不是主项目时 获得主项目信息
				projectMainInfos = ps.selectProjectMainInfo(userCode, projectParentCode);
				// 项目所属上级 均为 主方法 上级
				if (projectMainInfos.length < 1) {
					return rtnFailResult(Result.ERROR_4000, "非法的项目信息");
				}
				createUserDeptCode = ps.selectDeptCodeByProjectMainCode(projectMainInfos[0]);
			} else {
				// 主项目时 使用当前项目信息
				projectMainInfos = new String[] { projectCode, projectName, "N" };
				createUserDeptCode = session.getAttribute("user_dept_code").toString();// 创建人所属部门编码
			}
			request.setAttribute("project_code", projectCode);
			request.setAttribute("log_event_value", projectName);
			KbProject kp = new KbProject();
			kp.setProjectCode(projectCode);
			kp.setProjectName(projectName);
			kp.setProjectType("pub");
			kp.setProjectInfo(projectInfo);
			kp.setProjectRemark("");
			kp.setProjectParentCode(projectParentCode);
			kp.setProjectLevel(projectLevel);
			kp.setProjectStatus("progress");
			kp.setCreateUserCode(userCode);
			kp.setCreateTime(DateUtil.curDateYMDHMS());
			kp.setUpdateUserCode(userCode);
			kp.setUpdateTime(DateUtil.curDateYMDHMS());
			// 参与者关联
			List<KbProjectUser> kpuList = new ArrayList<KbProjectUser>();
			String[] projectEdits = request.getParameterValues("project_edit");
			if (null != projectEdits && projectEdits.length > 0) {
				for (String editUser : projectEdits) {
					String[] editUserInfos = editUser.split(",");
					KbProjectUser kpu = new KbProjectUser();
					kpu.setProjectCode(projectCode);
					kpu.setProjectName(projectName);
					kpu.setProjectPermission("write");
					kpu.setProjectLevel(projectLevel);
					kpu.setUserCode(editUserInfos[0]);
					kpu.setUserName(editUserInfos[1]);
					kpu.setUserDeptCode(editUserInfos[2]);
					kpu.setProjectMainCode(projectMainInfos[0]);
					kpu.setProjectMainName(projectMainInfos[1]);
					kpu.setProjectIsCollect(projectMainInfos[2]);
					kpu.setCreateUserCode(userCode);
					kpu.setCreateTime(DateUtil.curDateYMDHMS());
					kpuList.add(kpu);
				}
			}
			// 预览者关联
			String[] projectReads = request.getParameterValues("project_read");
			if (null != projectReads && projectReads.length > 0) {
				for (String readUser : projectReads) {
					String[] readUserInfos = readUser.split(",");
					KbProjectUser kpu = new KbProjectUser();
					kpu.setProjectCode(projectCode);
					kpu.setProjectName(projectName);
					kpu.setProjectPermission("read");
					kpu.setProjectLevel(projectLevel);
					kpu.setUserCode(readUserInfos[0]);
					kpu.setUserName(readUserInfos[1]);
					kpu.setUserDeptCode(readUserInfos[2]);
					kpu.setProjectMainCode(projectMainInfos[0]);
					kpu.setProjectMainName(projectMainInfos[1]);
					kpu.setProjectIsCollect(projectMainInfos[2]);
					kpu.setCreateUserCode(userCode);
					kpu.setCreateTime(DateUtil.curDateYMDHMS());
					kpuList.add(kpu);
				}
			}
			return ps.insProject(kp, kpuList, createUserDeptCode, projectMainInfos);
		} catch (NullPointerException | NumberFormatException e) {
			log.error("非法登录,非法ip：" + IpUtil.getIp(request));
			return rtnErrorResult(Result.ERROR_4200, "非法登录!");
		} catch (Exception e) {
			log.error("新增项目异常,异常原因：【" + e.toString() + "】");
			return rtnErrorResult(Result.ERROR_6000, "新增项目异常,请联系系统管理员");
		}
	}

	/**
	 * 
	 * @Title: getShareProject
	 * @Description: 项目分享
	 * @author 黄官易
	 * @param request
	 * @return
	 * @return String
	 * @date 2018年6月27日
	 * @version 1.0
	 */
	@RequestMapping("/sp.do")
	public String getShareProject(HttpServletRequest request) {
		// 所见即所得理念
		String projectCode = request.getParameter("project_code");// 项目编码
		String projectLevel = request.getParameter("project_level");// 项目等级
		String userCode = request.getParameter("user_code");// 用户编码
		try {
			Map<String, Object> shareMap = ps.getShareProject(projectCode, Integer.parseInt(projectLevel), userCode);
			if (!shareMap.isEmpty()) {
				request.setAttribute("shareProject", shareMap.get("shareProject"));
				request.setAttribute("shareFiles", shareMap.get("shareFiles"));
				return "view/share_project";
			}
			request.setAttribute("error", rtnErrorResult(Result.ERROR_6000, "糟糕，分享项目不存在"));
		} catch (NumberFormatException e) {
			log.error("非法登录,非法ip：" + IpUtil.getIp(request));
			request.setAttribute("error", rtnErrorResult(Result.ERROR_4300, "分享链接不合法"));
		} catch (Exception e) {
			log.error("文件查询异常,异常原因:【" + e.toString() + "】");
			request.setAttribute("error", rtnErrorResult(Result.ERROR_6000, "系统异常,请联系系统管理员"));
		}
		return "view/error";
	}

	/**
	 * 
	 * @Title: toInsertProject
	 * @Description: 跳转项目添加页面
	 * @author 黄官易
	 * @param request
	 * @param session
	 * @return
	 * @return String
	 * @date 2018年6月27日
	 * @version 1.0
	 */
	@RequestMapping("/insProJsp.do")
	public String toInsertProject(HttpServletRequest request, HttpSession session) {
		try {
			String projectParentCode = StrUtil.isBlank(request.getParameter("project_code")) ? ""
					: request.getParameter("project_code");// 获得父类编码
			String projectParentLevel = StrUtil.isBlank(request.getParameter("project_level")) ? "-1"
					: request.getParameter("project_level");// 获得父类等级
			// 使用部门协同功能
			// 获得部门信息
			Result<List<Map<String, String>>> deptResult = ds.selDeptForAll();
			// 获得所有人员信息
			Result<List<KbUser>> userResult = us.selUsersForAll();
			request.setAttribute("deptList", deptResult.getData());
			request.setAttribute("userList", userResult.getData());
			request.setAttribute("projectParentCode", projectParentCode);
			request.setAttribute("projectLevel", projectParentLevel);
			return "view/insert_project";
		} catch (NumberFormatException | NullPointerException e) {
			log.error("非法登录,登录IP：" + IpUtil.getIp(request));
			return "view/login";
		} catch (Exception e) {
			log.error("跳转项目添加页面异常,异常原因:【" + e.toString() + "】");
			request.setAttribute("error", rtnErrorResult(Result.ERROR_6000, "系统异常,请联系系统管理员"));
			return "view/error";
		}
	}

	/**
	 * 
	 * @Title: index
	 * @Description: 主页面（当前用户参与的所有项目的0级项目）
	 * @author 黄官易
	 * @param request
	 * @param session
	 * @return
	 * @return String
	 * @date 2018年6月28日
	 * @version 1.0
	 */
	@RequestMapping("/index.do")
	public String index(HttpServletRequest request, HttpSession session) {
		try {
			String userCode = session.getAttribute("user_code").toString();// 用户编码
			String fromProjectName = request.getParameter("form_project_name");
			String method = request.getParameter("method");
			List<Map<String, Object>> result = ps.selectProjectByUserCodeAndMethod(userCode,
					StrUtil.isBlank(method) ? "self" : method, fromProjectName);// 查询用户关联的所有项目
			request.setAttribute("projectList", result);
		} catch (NullPointerException e) {
			log.error("非法登录,登录IP：" + IpUtil.getIp(request));
			return "view/login";
		} catch (Exception e) {
			log.error("主页面（当前用户参与的所有项目）查询异常,异常原因:【" + e.toString() + "】");
			request.setAttribute("error", rtnErrorResult(Result.ERROR_6000, "系统异常,请联系系统管理员"));
			return "view/error";
		}
		return "view/index";
	}

	/**
	 * 
	 * @Title: changeCollect
	 * @Description: 收藏/取消收藏项目
	 * @author 黄官易
	 * @param request
	 * @param session
	 * @return
	 * @return Result<Object>
	 * @date 2018年7月9日
	 * @version 1.0
	 */
	@RequestMapping("/collect.do")
	@ResponseBody
	public Result<Object> changeCollect(HttpServletRequest request, HttpSession session) {
		try {
			String isCollect = request.getParameter("is_collect");
			String projectMainCode = request.getParameter("project_main_code");
			String userCode = session.getAttribute("user_code").toString();
			return ps.changeCollect(isCollect, userCode, projectMainCode);
		} catch (NullPointerException e) {
			log.error("非法登录,登录IP：" + IpUtil.getIp(request));
		} catch (Exception e) {
			log.error("收藏/取消收藏项目异常,异常原因：【" + e.toString() + "】");
		}
		return rtnErrorResult(Result.ERROR_6000, "收藏失败");
	}

	/**
	 * 
	 * @Title: changeProjectStatus
	 * @Description: 完成项目按钮
	 * @author 黄官易
	 * @param request
	 * @param session
	 * @return
	 * @return Result<Object>
	 * @date 2018年7月10日
	 * @version 1.0
	 */
	@RequestMapping("/proStatus.do")
	@ResponseBody
	public Result<Object> changeProjectStatus(HttpServletRequest request, HttpSession session) {
		try {
			// 订单号
			String projectCode = request.getParameter("project_code");
			// 员工编号
			String userCode = session.getAttribute("user_code").toString();
			// 项目等级
			String projectLevel = request.getParameter("project_level");
			// 主体方法
			return ps.changeProjectStatus(Integer.parseInt(projectLevel), projectCode, userCode);
		} catch (NullPointerException e) {
			log.error("非法登录,登录IP：" + IpUtil.getIp(request));
		} catch (Exception e) {
			log.error("项目状态更新异常,异常原因:【" + e.toString() + "】");
		}
		return rtnErrorResult(Result.ERROR_6000, "服务器异常,请联系系统管理员");
	}

	// 需要重写的通知已读接口
	@RequestMapping("/isRead.do")
	@ResponseBody
	public Result<Object> isReadAdviceMsg(HttpServletRequest request, HttpSession session)
			throws JsonParseException, JsonMappingException, IOException {
		try {
			String list = request.getParameter("list");
			ObjectMapper mapper = new ObjectMapper();
			JavaType jt = mapper.getTypeFactory().constructParametricType(ArrayList.class, String.class);
			List<String> advCodeList = mapper.readValue(list, jt);
			return ads.updateAdviceStatusByAdviceCode(advCodeList);
		} catch (Exception e) {
			log.error("已读业务方法异常，异常原因:【" + e.toString() + "】");
			return rtnErrorResult(Result.ERROR_6000, "修改为已读业务异常，请联系管理员");
		}
	}

	/**
	 * 
	 * @Title: delProject
	 * @Description: 删除项目
	 * @author 黄官易
	 * @param request
	 * @param session
	 * @return
	 * @return Result<Object>
	 * @date 2018年7月21日
	 * @version 1.0
	 */
	@RequestMapping("/delPro.do")
	@ResponseBody
	public Result<Object> delProject(HttpServletRequest request, HttpSession session) {
		String projectCode = request.getParameter("project_code");
		String projectLevel = request.getParameter("project_level");
		try {
			String userCode = session.getAttribute("user_code").toString();// 如果为null 返回登录页面
			return ps.delProject(Integer.parseInt(projectLevel), projectCode, userCode);
		} catch (NullPointerException e) {
			log.error("非法登录,登录IP：" + IpUtil.getIp(request));
			return rtnFailResult(Result.ERROR_4200, "");
		} catch (Exception e) {
			log.error("删除项目异常,异常原因:【" + e.toString() + "】");
			return rtnErrorResult(Result.ERROR_6000, "服务器异常,请联系系统管理员");
		}
	}

	/**
	 * 
	 * @Title: lockProject
	 * @Description: 项目锁定
	 * @author 黄官易
	 * @param request
	 * @param session
	 * @return
	 * @return Result<Object>
	 * @date 2018年7月23日
	 * @version 1.0
	 */
	@RequestMapping("/lockPro.do")
	@ResponseBody
	public Result<Object> lockProject(HttpServletRequest request, HttpSession session) {
		String projectLevel = request.getParameter("project_level");
		String projectCode = request.getParameter("project_code");
		try {
			String userCode = session.getAttribute("user_code").toString();// 防止非法登录
			return ps.lockProject(Integer.parseInt(projectLevel), projectCode, userCode);
		} catch (NullPointerException e) {
			log.error("非法登录,登录IP：" + IpUtil.getIp(request));
			return rtnFailResult(Result.ERROR_4200, "");
		} catch (Exception e) {
			log.error("锁定项目异常,异常原因:【" + e.toString() + "】");
			return rtnErrorResult(Result.ERROR_6000, "服务器异常,请联系系统管理员");
		}
	}
}
