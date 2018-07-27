package com.xh.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xh.base.BaseService;
import com.xh.dao.KbDeptMapper;
import com.xh.service.IDeptService;
import com.xh.uitl.Result;

@Service("deptServiceImpl")
public class DeptServiceImpl extends BaseService implements IDeptService {
	private static final Logger log = LoggerFactory.getLogger(DicServiceImpl.class);

	@Autowired
	private KbDeptMapper kdm; // 部门数据接口对象

	@Override
	public Result<List<Map<String, String>>> selDeptForAll() throws Exception {
		try {
			return rtnSuccessResult("", kdm.selectDeptForAll());
		} catch (SQLException e) {
			log.error("获得部门全部信息数据接口异常,异常原因:【" + e.toString() + "】");
			return rtnErrorResult(Result.ERROR_6000, "系统异常,请联系系统管理员");
		}
	}

}
