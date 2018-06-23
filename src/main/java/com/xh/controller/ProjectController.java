package com.xh.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.xh.base.BaseController;
import com.xh.base.Constant;
import com.xh.entity.KbFileTable;
import com.xh.entity.KbProject;
import com.xh.entity.KbProjectTable;
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

	@RequestMapping("/addPro.do")
	@ResponseBody
	public Result<Object> selProjectTable(HttpServletRequest request, HttpServletResponse response){
		int ptLevel=Integer.parseInt(request.getParameter("pt_level"));
		String obj=ps.selectProjectTableNameByProjectLevel(ptLevel).getData().toString();//表名
		Map<String,Object> map=new HashMap<String, Object>();
		String projectCode="P"+DateUtil.curDateYMDHMSForService()+StrUtil.getRandom((int) (Math.random() * 1000),4);
		map.put("formName", obj);
		map.put("projectCode", projectCode);
		map.put("projectName", "fffffff");
		map.put("projectType", "pub");
		map.put("projectInfo", "aaaaaa");
		map.put("projectRemark", "aaaaaa");
		map.put("projectParentCode", "P2018062213071254");//需要从数据库中取出动态显示在前端
		map.put("projectStatus", "progress");
		map.put("createUserCode", "12312313");
		map.put("createTime", DateUtil.curDateYMDHMS());
		map.put("updateUserCode", "1231321313");
		
		List<String> strList=new ArrayList<String>();
		strList.add("820046");
		strList.add("820032");
		strList.add("820033");
		strList.add("820055");
		Object rs=ps.selectUserByUserCode(strList).getData();
		List<KbUser> taskList = (ArrayList<KbUser>)rs;
		for(int i=0;i<taskList.size();i++){
			map.put("userName", taskList.get(i).getUserName());
			map.put("userCode", taskList.get(i).getUserCode());
			map.put("userDeptCode", taskList.get(i).getUserDeptCode());
			map.put("projectPermission", "write");
			ps.insertProject(map);
		}
		return rtnSuccessResult("添加项目信息及项目涉及人员成功");
	}
	
	
}
