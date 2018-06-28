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
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aspose.p2cbca448.re;
import com.aspose.slides.pa2137a2a.in;
import com.xh.base.BaseController;
import com.xh.base.Constant;
import com.xh.entity.KbProject;
import com.xh.entity.KbProjectTable;
import com.xh.entity.KbProjectUser;
import com.xh.entity.KbUser;
import com.xh.service.IProjectService;
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
		String ptName = request.getParameter("pt_name");
		String ptLevel = request.getParameter("pt_level");
		KbProjectTable kpt = new KbProjectTable();
		kpt.setPtCode(PROJECTTABLETAG + DateUtil.curDateYMDHMSForService()
				+ StrUtil.getRandom((int) (Math.random() * 1000), 3));
		kpt.setPtName(TABELTAG + ptName);
		kpt.setProjectLevel(Integer.parseInt(ptLevel));
		// kft.setCreateUserCode(session.getAttribute("userCode").toString());
		kpt.setCreateUserCode("kb_system");
		kpt.setCreateTime(DateUtil.curDateYMDHMS());
		try {
			return ps.createProjectList(kpt);
		} catch (Exception e) {
			log.error("新增项目表信息及项目表接口异常,异常原因:【" + e.toString() + "】");
			return rtnErrorResult(Result.ERROR_6000, "新增项目表信息及项目表接口异常,请联系系统管理员");
		}
	}

	/**
	 * 
	 * @Title: selProjectTable
	 * @Description: 新增项目表信息及项目员工表信息
	 * @author 陈专懂
	 * @return Result<Object>
	 * @date 2018年6月26日
	 * @version 1.0
	 *//*
		 * @RequestMapping("/addPro.do")
		 * 
		 * @ResponseBody public Result<Object> selProjectTable(HttpServletRequest
		 * request, HttpSession session) { int ptLevel =
		 * Integer.parseInt(request.getParameter("pt_level")); String formName =
		 * ps.selectProjectTableNameByProjectLevel(ptLevel).getData().toString();// 表名
		 * String userCode = session.getAttribute("user_code").toString();//创建人用户编码
		 * String projectCode = PROJECTTAG + DateUtil.curDateYMDHMSForService() +
		 * StrUtil.getRandom((int) (Math.random() * 10000), 4); KbProject kbObj = new
		 * KbProject();// new 一个kbPeoject的对象 kbObj.setProjectCode(projectCode);
		 * kbObj.setProjectName(request.getParameter("project_name"));
		 * kbObj.setProjectType(request.getParameter("project_type"));
		 * kbObj.setProjectInfo(request.getParameter("project_info"));
		 * kbObj.setProjectParentCode(request.getParameter("project_parent_code"));//
		 * 需要从数据库中取出动态显示在前端
		 * kbObj.setProjectStatus(request.getParameter("project_status"));
		 * kbObj.setCreateUserCode(userCode);
		 * kbObj.setCreateTime(DateUtil.curDateYMDHMS());
		 * kbObj.setUpdateUserCode(userCode);
		 * 
		 * List<String> strList = new ArrayList<String>();// 获取write或read
		 * strList.add("820046"); strList.add("820032"); strList.add("820033");
		 * strList.add("820055"); List<KbUser> taskList =
		 * ps.selectUserByUserCode(strList).getData(); List<KbProjectUser> listUser =
		 * new ArrayList<KbProjectUser>(); for (int i = 0; i < taskList.size(); i++) {
		 * KbProjectUser proUser = new KbProjectUser();
		 * proUser.setProjectCode(projectCode);
		 * proUser.setProjectName(kbObj.getProjectName());
		 * proUser.setProjectLevel(ptLevel);
		 * proUser.setUserName(taskList.get(i).getUserName());
		 * proUser.setUserCode(taskList.get(i).getUserCode());
		 * proUser.setUserDeptCode(taskList.get(i).getUserDeptCode());
		 * proUser.setProjectPermission("write");
		 * proUser.setCreateUserCode(kbObj.getCreateUserCode());
		 * proUser.setCreateTime(DateUtil.curDateYMDHMS()); listUser.add(proUser); }
		 * ps.insertProject(kbObj, listUser, formName); return
		 * rtnSuccessResult("添加项目信息及项目涉及人员成功"); }
		 */

	/**
	 * 
	 * @Title: addProject
	 * @Description: 新增项目表信息及项目员工表信息
	 * @author 黄官易
	 * @param request
	 * @return
	 * @return Result<Object>
	 * @date 2018年6月28日
	 * @version 1.0
	 */
	public Result<Object> addProject(HttpServletRequest request, HttpSession session) {
		try {
			// 项目信息主体
			String projectName = request.getParameter("project_name");
			String projectType = request.getParameter("project_type");
			String projectInfo = request.getParameter("project_info");
			// String projectRemark = request.getParameter("project_remark");
			String projectParentCode = request.getParameter("project_parent_code");
			String projectParentLevel = request.getParameter("project_level");
			int projectLevel = Integer.parseInt(projectParentLevel) + 1;
			String projectCode = PROJECTTAG + DateUtil.curDateYMDHMSForService() + StrUtil.getRandom(10000, 4);
			String userCode = session.getAttribute("user_code").toString();
			KbProject kp = new KbProject();
			kp.setProjectCode(projectCode);
			kp.setProjectName(projectName);
			kp.setProjectType(projectType);
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
				for (String editUserCode : projectEdits) {
					KbProjectUser kpu = new KbProjectUser();
					kpu.setProjectCode(projectCode);
					kpu.setProjectName(projectName);
					kpu.setProjectPermission("write");
					kpu.setProjectLevel(projectLevel);
					kpu.setUserCode(editUserCode);
//					kpu.
					
					
					
					
					
					
					
					
					
				}

			}

			// 预览者关联
			String[] projectReads = request.getParameterValues("project_read");

			if (null != projectReads && projectReads.length > 0) {
				for (String string : projectReads) {

				}

			}

		} catch (NumberFormatException e) {
			log.error("非法登录,非法ip：" + IpUtil.getIp(request));
		}
		return null;
	}

	/**
	 * 
	 * @Title: selectAllPro
	 * @Description: 主页、显示所有的项目
	 * @author 陈专懂
	 * @return Result<Object>
	 * @date 2018年6月25日
	 * @version 1.0
	 */
	@RequestMapping("/selectAllPro.do")
	@ResponseBody
	public Result<Object> selectAllPro(HttpServletRequest request, HttpServletResponse response) {
		String obj = ps.selectProjectTableNameByProjectLevel(0).getData().toString();// 表名
		System.err.println("表名:" + obj);
		String projectParentCode = "-1";
		if (obj == null) {
			return rtnErrorResult(Result.ERROR_4000, "找不到项目根目录");
		}
		List<KbProject> list = ps.selectAllPro(obj, projectParentCode).getData();
		System.err.println("信息：" + list);
		if (list != null) {
			return rtnSuccessResult("获取该等级项目信息成功", list);
		}
		return rtnErrorResult(Result.ERROR_4000, "获取该等级项目信息失败，请联系管理员。");
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
		// 所见即所得原理
		String projectCode = request.getParameter("project_code");// 项目编码
		String projectLevel = request.getParameter("project_level");// 项目等级
		String userCode = request.getParameter("user_code");// 用户编码
		try {
			Map<String, Object> shareMap = ps.getShareProject(projectCode, Integer.parseInt(projectLevel), userCode);
			if (!shareMap.isEmpty()) {
				request.setAttribute("shareProject", shareMap.get("shareProject"));
				request.setAttribute("shareFiles", shareMap.get("shareFiles"));
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
		return "view/share_project";
	}

	@RequestMapping("/AllProInMain.do")
	public String selectAllProInMain(HttpServletRequest request, HttpServletResponse response) {

		List<List<KbProject>> proList = ps.selectAllProInMain();
		List<KbProject> projectList = new ArrayList<KbProject>();
		for (List<KbProject> list : proList) {
			for (KbProject kbProject : list) {
				projectList.add(kbProject);
			}
		}
		if (!projectList.isEmpty()) {
			request.setAttribute("ProList", projectList);
		} else {
			return "view/not_share";
		}
		return "view/xh_kb";
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
			String userDeptCode = session.getAttribute("user_dept_code").toString();// 获得部门信息
			String projectParentCode = StrUtil.isBlank(request.getParameter("project_code")) ? ""
					: request.getParameter("project_code");// 获得父类编码
			String projectParentLevel = StrUtil.isBlank(request.getParameter("project_level")) ? "-1"
					: request.getParameter("project_level");// 获得父类等级
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
		return "view/insert_project";
	}

	/**
	 * 
	 * @Title: index
	 * @Description: 主页面（当前用户参与的所有项目）
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
			List<Map<String, Object>> result = ps.selectProjectByUserCode(userCode);// 查询用户关联的所有项目
			request.setAttribute("projectList", result);
		} catch (NullPointerException e) {
			log.error("非法登录,登录IP：" + IpUtil.getIp(request));
			return "view/login";
		} catch (Exception e) {
			log.error("主页面（当前用户参与的所有项目）查询异常,异常原因:【" + e.toString() + "】");
		}
		return "view/index";
	}

}
