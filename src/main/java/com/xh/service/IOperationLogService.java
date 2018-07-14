/**
 * 
 */
package com.xh.service;

import java.util.List;

import com.xh.entity.KbOperationLog;
import com.xh.entity.KbUserAdvice;
import com.xh.uitl.Result;

/**
 * @author 陈专懂
 * @date 2018年7月4日下午4:28:00
 * @version 1.0
 */
public interface IOperationLogService {

	
	/**  
	 * @Title: add  
	 * @Description: TODO(这里用一句话描述这个方法的作用)  
	 * @author 陈专懂 
	 * @return void 
	 * @date 2018年7月5日  
	 * @version 1.0    
	*/
	public int addLog(KbOperationLog log);

	public int updateLogByRead(String logUserCode);
	
	/**
	 * 
	 * @Title: parentUserCodeByCode  
	 * @Description: 获得父类项目下所有关联的人员的usercode 
	 * @author 陈专懂 
	 * @return List<String> 
	 * @date 2018年7月9日  
	 * @version 1.0
	 * @throws Exception 
	 */
	public Result<List<String>> parentUserCodeByCode(String parentProjectCode) throws Exception;
	
	public Result<Object> addUserAdvice(List<KbUserAdvice> kbUserAdvice) throws Exception;
}
