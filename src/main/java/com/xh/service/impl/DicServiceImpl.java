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
			
			return null;
		}

	}

	@Override
	public Result<KbDic> selFileEventTypeValueByCode(String code) throws Exception {
		return rtnSuccessResult("", kdm.selectFileEventTypeValueByCode(code));

	}

	@Override
	public Result<List<KbDic>> selFileEventLevelForAll() throws Exception {
		return rtnSuccessResult("", kdm.selectFileEventLevelForAll());

	}

	@Override
	public Result<KbDic> selFileEventLevelValueByCode(String code) throws Exception {
		return rtnSuccessResult("", kdm.selectFileEventLevelvalueByCode(code));
	}

}
