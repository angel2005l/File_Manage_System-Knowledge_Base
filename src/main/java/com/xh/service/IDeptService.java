package com.xh.service;

import java.util.List;
import java.util.Map;

import com.xh.uitl.Result;

public interface IDeptService {

	/**
	 * 
	 * @Title: selDeptForAll  
	 * @Description: 获得部门全部信息(按等级分组)
	 * @author 黄官易
	 * @return
	 * @throws Exception    
	 * @return Result<List<Map<String,String>>> 
	 * @date 2018年7月27日  
	 * @version 1.0
	 */
	public Result<List<Map<String, String>>> selDeptForAll() throws Exception;
}
