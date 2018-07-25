package com.xh.service;

import java.util.List;

import com.xh.entity.KbDic;
import com.xh.uitl.Result;

public interface IDicService {
	/**
	 * 
	 * @Title: selFileEventTypeForAll  
	 * @Description: TODO(这里用一句话描述这个方法的作用)  
	 * @author 黄官易
	 * @return
	 * @throws Exception    
	 * @return Result<List<KbDic>> 
	 * @date 2018年7月25日  
	 * @version 1.0
	 */
	public Result<List<KbDic>> selFileEventTypeForAll() throws Exception;
	/**
	 * 
	 * @Title: selFileEventTypeValueByCode  
	 * @Description: TODO(这里用一句话描述这个方法的作用)  
	 * @author 黄官易
	 * @param code
	 * @return
	 * @throws Exception    
	 * @return Result<KbDic> 
	 * @date 2018年7月25日  
	 * @version 1.0
	 */
	public Result<KbDic> selFileEventTypeValueByCode(String code) throws Exception;
	/**
	 * 
	 * @Title: selFileEventLevelForAll  
	 * @Description: TODO(这里用一句话描述这个方法的作用)  
	 * @author 黄官易
	 * @return
	 * @throws Exception    
	 * @return Result<List<KbDic>> 
	 * @date 2018年7月25日  
	 * @version 1.0
	 */
	public Result<List<KbDic>> selFileEventLevelForAll() throws Exception;
	/**
	 * 
	 * @Title: selFileEventLevelValueForAll  
	 * @Description: TODO(这里用一句话描述这个方法的作用)  
	 * @author 黄官易
	 * @return
	 * @throws Exception    
	 * @return Result<KbDic> 
	 * @date 2018年7月25日  
	 * @version 1.0
	 */
	public Result<KbDic> selFileEventLevelValueByCode(String Code) throws Exception;
}
