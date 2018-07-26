package com.xh.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xh.base.BaseService;
import com.xh.dao.KbDicMapper;
import com.xh.entity.KbDic;
import com.xh.service.IDicService;
import com.xh.uitl.Result;

@Service("dicServiceImpl")
public class DicServiceImpl extends BaseService implements IDicService {
	private static Logger log = LoggerFactory.getLogger(DicServiceImpl.class);
	@Autowired
	KbDicMapper kdm;

	@Override
	public Result<List<KbDic>> selFileEventTypeForAll() throws Exception {
		try {
			return rtnSuccessResult("", kdm.selectFileEventTypeForAll());
		} catch (SQLException e) {
			log.error("查询全部文件事件类型数据接口异常,异常原因:【" + e.toString() + "】");
			return rtnErrorResult(Result.ERROR_6000, "服务器异常,请联系系统管理员");
		}

	}

	@Override
	public Result<String> selFileEventTypeValueByCode(String code) throws Exception {
		try {
			return rtnSuccessResult("", kdm.selectFileEventTypeValueByCode(code));
		} catch (SQLException e) {
			log.error("查询特定文件事件类型值数据接口异常,异常原因:【" + e.toString() + "】");
			return rtnErrorResult(Result.ERROR_6000, "服务器异常,请联系系统管理员");
		}

	}

	@Override
	public Result<List<KbDic>> selFileEventLevelForAll() throws Exception {
		try {
			return rtnSuccessResult("", kdm.selectFileEventLevelForAll());
		} catch (SQLException e) {
			log.error("查询全部文件事件等级数据接口异常,异常原因:【" + e.toString() + "】");
			return rtnErrorResult(Result.ERROR_6000, "服务器异常,请联系系统管理员");
		}

	}

	@Override
	public Result<String> selFileEventLevelValueByCode(String code) throws Exception {
		try {
			return rtnSuccessResult("", kdm.selectFileEventLevelvalueByCode(code));
		} catch (SQLException e) {
			log.error("查询特定文件事件等级值数据接口异常,异常原因:【" + e.toString() + "】");
			return rtnErrorResult(Result.ERROR_6000, "服务器异常,请联系系统管理员");
		}

	}

	@Override
	public Result<String> selDicKvForAll(String dicParentCode) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<String> selDicValueByCode(String dicParentCode, String code) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
