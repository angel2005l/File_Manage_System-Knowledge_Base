package com.xh.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xh.base.BaseController;
import com.xh.entity.KbDic;
import com.xh.service.IDicService;
import com.xh.uitl.Result;

@RequestMapping("/dic")
@Controller
public class DicController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(DicController.class);
	@Autowired
	@Qualifier("dicServiceImpl")
	IDicService ds;

	/**
	 * 
	 * @Title: getFileEventType
	 * @Description: 全部的文件事件类型
	 * @author 黄官易
	 * @return
	 * @return Result<List<KbDic>>
	 * @date 2018年7月26日
	 * @version 1.0
	 */
	@RequestMapping("/getFet.do")
	@ResponseBody
	public Result<List<KbDic>> getFileEventType() {
		try {
			return ds.selFileEventTypeForAll();
		} catch (Exception e) {
			log.error("查询全部文件事件类型业务接口异常,异常原因:【" + e.toString() + "】");
			return rtnErrorResult(Result.ERROR_6000, "服务器异常,请联系系统管理员");
		}
	}

	/**
	 * 
	 * @Title: getFileEventTypeValueByCode
	 * @Description: 根据编码查询文件事件类型的值
	 * @author 黄官易
	 * @param request
	 * @return
	 * @return Result<String>
	 * @date 2018年7月26日
	 * @version 1.0
	 */
	@RequestMapping("/getFetByCode.do")
	@ResponseBody
	public Result<String> getFileEventTypeValueByCode(HttpServletRequest request) {
		String code = request.getParameter("code");
		try {
			return ds.selFileEventTypeValueByCode(code);
		} catch (Exception e) {
			log.error("根据编码查询文件事件类型值业务接口异常,异常原因:【" + e.toString() + "】");
			return rtnErrorResult(Result.ERROR_6000, "服务器异常,请联系系统管理员");
		}
	}

	/**
	 * 
	 * @Title: getFileEventLevel
	 * @Description: 全部的文件事件等级
	 * @author 黄官易
	 * @return
	 * @return Result<List<KbDic>>
	 * @date 2018年7月26日
	 * @version 1.0
	 */
	@RequestMapping("/getFel.do")
	@ResponseBody
	public Result<List<KbDic>> getFileEventLevel() {
		try {
			return ds.selFileEventLevelForAll();
		} catch (Exception e) {
			log.error("根据编码查询文件事件类型值业务接口异常,异常原因:【" + e.toString() + "】");
			return rtnErrorResult(Result.ERROR_6000, "服务器异常,请联系系统管理员");
		}
	}

	/**
	 * 
	 * @Title: getFileEventLevelValueByCode
	 * @Description: 根据编码查询文件事件等级的值
	 * @author 黄官易
	 * @param request
	 * @return
	 * @return Result<String>
	 * @date 2018年7月26日
	 * @version 1.0
	 */
	@RequestMapping("/getFelByCode.do")
	@ResponseBody
	public Result<String> getFileEventLevelValueByCode(HttpServletRequest request) {
		String code = request.getParameter("code");
		try {
			return ds.selFileEventLevelValueByCode(code);
		} catch (Exception e) {
			log.error("根据编码查询文件事件等级值业务接口异常,异常原因:【" + e.toString() + "】");
			return rtnErrorResult(Result.ERROR_6000, "服务器异常,请联系系统管理员");
		}
	}

	/**
	 * 
	 * @Title: getDicKvForAll
	 * @Description: 获得特定的数据字典集合
	 * @author 黄官易
	 * @param request
	 * @return
	 * @return Result<Map<String,String>>
	 * @date 2018年7月26日
	 * @version 1.0
	 */
	@RequestMapping("/getDic.do")
	@ResponseBody
	public Result<List<Map<String, String>>> getDicKvForAll(HttpServletRequest request) {
		String parentCode = request.getParameter("parent_code");
		try {
			return ds.selDicKvForAll(parentCode);
		} catch (Exception e) {
			log.error("查询特定的数据字典集合业务接口异常,异常原因:【" + e.toString() + "】");
			return rtnErrorResult(Result.ERROR_6000, "服务器异常,请联系系统管理员");
		}
	}

	/**
	 * 
	 * @Title: getDicValueByCode
	 * @Description: 根据编码查询数据字典值
	 * @author 黄官易
	 * @param request
	 * @return
	 * @return Result<String>
	 * @date 2018年7月26日
	 * @version 1.0
	 */
	@RequestMapping("/getDicVByCode.do")
	@ResponseBody
	public Result<String> getDicValueByCode(HttpServletRequest request) {
		String code = request.getParameter("code");
		String parentCode = request.getParameter("parent_code");
		try {
			return ds.selDicValueByCode(parentCode, code);
		} catch (Exception e) {
			log.error("根据编码查询数据字典值业务接口异常,异常原因:【" + e.toString() + "】");
			return rtnErrorResult(Result.ERROR_6000, "服务器异常,请联系系统管理员");
		}
	}

}
