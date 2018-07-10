/**
 * 
 */
package com.xh.service;


import java.util.List;


import com.xh.entity.KbUserAdvice;
import com.xh.uitl.Result;

/**
 * @author 陈专懂
 * @date 2018年7月9日下午1:04:28
 * @version 1.0
 */
public interface IUserAdviceService {

	/**
	 * 
	 * @Title: getAdviceMsgByUser  
	 * @Description: 获取该用户下所有的未读通知（显示在前端界面）
	 * @author 陈专懂 
	 * @return KbUserAdvice 
	 * @date 2018年7月9日  
	 * @version 1.0
	 */
	public List<KbUserAdvice> getAdviceMsgByUser(String userCode);
	
	/**
	 * 
	 * @Title: updateAdviceStatusByAdviceCode  
	 * @Description: 已读（修改通知的状态改成Y）  
	 * @author 陈专懂 
	 * @return int 
	 * @date 2018年7月9日  
	 * @version 1.0
	 */
	public Result<Object> updateAdviceStatusByAdviceCode(List<String> adviceCode);
}
