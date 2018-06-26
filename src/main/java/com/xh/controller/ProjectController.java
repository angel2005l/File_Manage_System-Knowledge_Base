package com.xh.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xh.base.BaseController;
import com.xh.base.Constant;
import com.xh.entity.KbProject;
import com.xh.entity.KbProjectTable;
import com.xh.entity.KbProjectUser;
import com.xh.entity.KbUser;
import com.xh.service.IProjectService;
import com.xh.uitl.DateUtil;
import com.xh.uitl.Result;
import com.xh.uitl.StrUtil;

@Controller
@RequestMapping("/pro")
public class ProjectController extends BaseController{
	private static final Logger log = LoggerFactory.getLogger(FileController.class);// 日志对象
	private static final String PROJECTTABLETAG = Constant.PROJECTTABLETAG;// 项目表标识表头
	private static final String TABELTAG = Constant.TABELTAG;// 数据库表头
	
	@Autowired
	private IProjectService ps;
	
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
	public Result<Object> insertProjectTable(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String ptName = request.getParameter("pt_name");
		String ptLevel = request.getParameter("pt_level");
		KbProjectTable kpt = new KbProjectTable();
		kpt.setPtCode(
				PROJECTTABLETAG + DateUtil.curDateYMDHMSForService() + StrUtil.getRandom((int) (Math.random() * 1000), 3));
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
	 */
	@RequestMapping("/addPro.do")
	@ResponseBody
	public Result<Object> selProjectTable(HttpServletRequest request, HttpServletResponse response){
		int ptLevel=Integer.parseInt(request.getParameter("pt_level"));
		String formName=ps.selectProjectTableNameByProjectLevel(ptLevel).getData().toString();//表名
		
		String projectCode="P"+DateUtil.curDateYMDHMSForService()+StrUtil.getRandom((int) (Math.random() * 1000),4);
		KbProject kbObj=new KbProject();//new 一个kbPeoject的对象
		kbObj.setProjectCode(projectCode);
		kbObj.setProjectName(request.getParameter("project_name"));
		kbObj.setProjectType(request.getParameter("project_type"));
		kbObj.setProjectInfo(request.getParameter("project_info"));
		kbObj.setProjectRemark(request.getParameter("project_remark"));
		kbObj.setProjectParentCode(request.getParameter("project_parent_code"));//需要从数据库中取出动态显示在前端
		kbObj.setProjectStatus(request.getParameter("project_status"));
		kbObj.setCreateUserCode(request.getParameter("create_user_code"));
		kbObj.setCreateTime(DateUtil.curDateYMDHMS());
		kbObj.setUpdateUserCode(request.getParameter("update_user_code"));
		
		List<String> strList=new ArrayList<String>();//获取write或read
		strList.add("820046");
		strList.add("820032");
		strList.add("820033");
		strList.add("820055");
		Object rs=ps.selectUserByUserCode(strList).getData();
		List<KbUser> taskList = (ArrayList<KbUser>)rs;//员工信息的list集合
		List<KbProjectUser> listUser=new ArrayList<KbProjectUser>();
		for(int i=0;i<taskList.size();i++){
			KbProjectUser proUser=new KbProjectUser();
			proUser.setProjectCode(projectCode);
			proUser.setProjectName(kbObj.getProjectName());
			proUser.setProjectLevel(ptLevel);
			proUser.setUserName(taskList.get(i).getUserName());
			proUser.setUserCode(taskList.get(i).getUserCode());
			proUser.setUserDeptCode(taskList.get(i).getUserDeptCode());
			proUser.setProjectPermission("write");
			proUser.setCreateUserCode(kbObj.getCreateUserCode());
			proUser.setCreateTime(DateUtil.curDateYMDHMS());
			listUser.add(proUser);
		}
		ps.insertProject(kbObj,listUser,formName);
		return rtnSuccessResult("添加项目信息及项目涉及人员成功");
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
	public Result<Object> selectAllPro(HttpServletRequest request, HttpServletResponse response){
		String obj=ps.selectProjectTableNameByProjectLevel(0).getData().toString();//表名
		System.err.println("表名:"+obj);
		String projectParentCode="-1";
		if(obj==null){
			return rtnErrorResult(Result.ERROR_4000, "找不到项目最根目录");
		}
		Object kpro=ps.selectAllPro(obj,projectParentCode).getData();
		List<KbProject> list=(ArrayList<KbProject>)kpro;
		System.err.println("信息："+list);
		if(list!=null){
			return rtnSuccessResult("获取该等级项目信息成功", list);
		}
		return rtnErrorResult(Result.ERROR_4000, "获取该等级项目信息失败，请联系管理员。");
	}
	
}
