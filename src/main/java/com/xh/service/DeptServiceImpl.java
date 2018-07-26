package com.xh.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.xh.base.BaseService;
import com.xh.uitl.Result;

@Service("DeptServiceImpl")
public class DeptServiceImpl extends BaseService implements IDeptService {

	@Override
	public Result<List<Map<String, String>>> selDeptForAll() throws Exception {
		return null;
	}

}
