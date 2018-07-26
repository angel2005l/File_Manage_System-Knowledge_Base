package com.xh.service;

import java.util.List;
import java.util.Map;

import com.xh.uitl.Result;

public interface IDeptService {

	// 获得部门全部信息
	public Result<List<Map<String, String>>> selDeptForAll() throws Exception;
}
