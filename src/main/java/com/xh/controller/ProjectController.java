package com.xh.controller;

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
import com.xh.entity.KbFileTable;
import com.xh.entity.KbProjectTable;
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

	
	
}
